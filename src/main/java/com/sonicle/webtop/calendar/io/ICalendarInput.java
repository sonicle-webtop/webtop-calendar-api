/*
 * Copyright (C) 2018 Sonicle S.r.l.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation with the addition of the following permission
 * added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED
 * WORK IN WHICH THE COPYRIGHT IS OWNED BY SONICLE, SONICLE DISCLAIMS THE
 * WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, see http://www.gnu.org/licenses or write to
 * the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301 USA.
 *
 * You can contact Sonicle S.r.l. at email address sonicle[at]sonicle[dot]com
 *
 * The interactive user interfaces in modified source and object code versions
 * of this program must display Appropriate Legal Notices, as required under
 * Section 5 of the GNU Affero General Public License version 3.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License
 * version 3, these Appropriate Legal Notices must retain the display of the
 * Sonicle logo and Sonicle copyright notice. If the display of the logo is not
 * reasonably feasible for technical reasons, the Appropriate Legal Notices must
 * display the words "Copyright (C) 2018 Sonicle S.r.l.".
 */
package com.sonicle.webtop.calendar.io;

import com.sonicle.commons.InternetAddressUtils;
import com.sonicle.commons.LangUtils;
import com.sonicle.commons.time.JodaTimeUtils;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttachment;
import com.sonicle.webtop.calendar.model.EventAttachmentWithBytes;
import com.sonicle.webtop.calendar.model.EventAttachmentWithInputStream;
import com.sonicle.webtop.calendar.model.EventAttendee;
import com.sonicle.webtop.calendar.model.EventBase;
import com.sonicle.webtop.calendar.model.EventEx;
import com.sonicle.webtop.calendar.model.EventRecurrence;
import com.sonicle.webtop.core.app.ical4j.LazyCalendarComponentConsumer;
import com.sonicle.webtop.core.app.ical4j.XCustomFieldValue;
import com.sonicle.webtop.core.app.ical4j.XTag;
import com.sonicle.webtop.core.app.util.log.BufferingLogHandler;
import com.sonicle.webtop.core.app.util.log.LogEntry;
import com.sonicle.webtop.core.app.util.log.LogHandler;
import com.sonicle.webtop.core.app.util.log.LogMessage;
import com.sonicle.webtop.core.model.CustomFieldValue;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.ICal4jUtils;
import com.sonicle.webtop.core.util.ICalendarUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import jakarta.mail.internet.InternetAddress;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attach;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.XProperty;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class ICalendarInput implements EventStreamReader {
	private DateTimeZone defaultTz;
	private Map<String, String> tagNamesById = null;
	private Map<String, List<String>> tagIdsByName = null;
	private boolean ignoreClassification = false;
	private boolean ignoreTrasparency = false;
	private boolean ignoreAlarms = false;
	private boolean ignoreCategories = false;
	private boolean ignoreAttachments = false;
	private boolean ignoreCustomValues = false;
	private EventBase.Visibility defaultVisibility = EventBase.Visibility.PUBLIC;
	private EventBase.Transparency defaultTransparency = EventBase.Transparency.OPAQUE;
	private boolean defaultAttendeeNotify = false;
	private boolean includeSourceComponentInOutput = false;
	private LogHandler logHandler = null;
	
	public ICalendarInput(DateTimeZone defaultTz) {
		this.defaultTz = defaultTz;
	}
	
	public ICalendarInput(DateTimeZone defaultTz, Map<String, String> tagNamesById, Map<String, List<String>> tagIdsByName) {
		this.defaultTz = defaultTz;
		this.tagNamesById = tagNamesById;
		this.tagIdsByName = tagIdsByName;
	}
	
	public ICalendarInput withDefaultTimezone(DateTimeZone defaultTz) {
		this.defaultTz = defaultTz;
		return this;
	}
	
	public ICalendarInput withIgnoreClassification(boolean ignoreClassification) {
		this.ignoreClassification = ignoreClassification;
		return this;
	}
	
	public ICalendarInput withIgnoreTransparency(boolean ignoreTrasparency) {
		this.ignoreTrasparency = ignoreTrasparency;
		return this;
	}
	
	public ICalendarInput withIgnoreAlarms(boolean ignoreAlarms) {
		this.ignoreAlarms = ignoreAlarms;
		return this;
	}
	
	public ICalendarInput withIgnoreCategories(boolean ignoreCategories) {
		this.ignoreCategories = ignoreCategories;
		return this;
	}
	
	public ICalendarInput withIgnoreAttachments(boolean ignoreAttachments) {
		this.ignoreAttachments = ignoreAttachments;
		return this;
	}
	
	public ICalendarInput withIgnoreCustomValues(boolean ignoreCustomValues) {
		this.ignoreCustomValues = ignoreCustomValues;
		return this;
	}
	
	public ICalendarInput withDefaultVisibility(EventBase.Visibility defaultVisibility) {
		this.defaultVisibility = defaultVisibility;
		return this;
	}
	
	public ICalendarInput withDefaultTransparency(EventBase.Transparency defaultTransparency) {
		this.defaultTransparency = defaultTransparency;
		return this;
	}
	
	public ICalendarInput withDefaultAttendeeNotify(boolean defaultAttendeeNotify) {
		this.defaultAttendeeNotify = defaultAttendeeNotify;
		return this;
	}
	
	public ICalendarInput withIncludeSourceComponentInOutput(boolean includeSourceComponentInOutput) {
		this.includeSourceComponentInOutput = includeSourceComponentInOutput;
		return this;
	}
	
	public ICalendarInput withLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
		return this;
	}
	
	@Override
	public List<EventInput> read(final InputStream is) throws IOException, WTException {
		return parseICalendar(is);
	}
	
	public ArrayList<EventInput> parseICalendar(final InputStream is) throws IOException, WTException {
		try {
			return parseEventObjects(ICalendarUtils.parse(is));
		} catch (ParserException ex) {
			throw new IOException("Unable to parse", ex);
		}	
	}
	
	public ArrayList<EventInput> parseEventObjects(final Calendar calendar) throws WTException {
		// See http://www.kanzaki.com/docs/ical/
		ArrayList<EventInput> results = new ArrayList<>();
		
		int count = 0;
		for (Iterator xi = calendar.getComponents().iterator(); xi.hasNext();) {
			final Component component = (Component) xi.next();
			if (component instanceof VEvent) {
				handleEventObjectEntry((VEvent)component, count++, (input) -> {
					results.add(input);
				});
			}
		}
		return results;
	}
	
	@Deprecated
	public ArrayList<EventInput> parseEventObjects999(final Calendar calendar) throws WTException {
		// See http://www.kanzaki.com/docs/ical/
		ArrayList<EventInput> results = new ArrayList<>();
		
		int count = 0;
		for (Iterator xi = calendar.getComponents().iterator(); xi.hasNext();) {
			final Component component = (Component) xi.next();
			if (component instanceof VEvent) {
				count++;
				final VEvent ve = (VEvent)component;
				final int veNo = count;
				
				BufferingLogHandler buffLogHandler = null;
				if (logHandler != null) {
					buffLogHandler = new BufferingLogHandler() {
						@Override
						public List<LogEntry> first() {
							return Arrays.asList(new LogMessage(0, LogEntry.Level.INFO, "VEVENT #{} [{}]", veNo, ICal4jUtils.printDump(ve)));
						}
					};
				}
				
				try {
					final EventInput result = parseEventObject(ve, buffLogHandler);
					if (result.event.trimFieldLengths()) {
						LogHandler.log(buffLogHandler, 1, LogEntry.Level.WARN, "Some fields were truncated due to max-length");
					}
					results.add(result);
					
				} catch(Throwable t) {
					LogHandler.log(buffLogHandler, 0, LogEntry.Level.ERROR, "Reason: {}", LangUtils.getThrowableMessage(t));
				}
				
				if (logHandler != null && buffLogHandler != null) {
					final List<LogEntry> entries = buffLogHandler.flush();
					if (entries != null) logHandler.handle(entries);
				}
			}
		}
		return results;
	}
	
	public void parseEventObjects(final InputStream is, final EventInputConsumer consumer) throws WTException, ParserException, IOException {
		final AtomicInteger count = new AtomicInteger(0);
		ICalendarUtils.createLazyCalendarBuilder(
			new LazyCalendarComponentConsumer() {
				@Override
				public void consume(CalendarComponent component) {
					if (component instanceof VEvent) {
						handleEventObjectEntry((VEvent)component, count.incrementAndGet(), consumer);
					}
				}					
			}
		).build(is);
	}
	
	private void handleEventObjectEntry(final VEvent vevent, final int no, final EventInputConsumer consumer) {
		BufferingLogHandler buffLogHandler = null;
		if (logHandler != null) {
			buffLogHandler = new BufferingLogHandler() {
				@Override
				public List<LogEntry> first() {
					return Arrays.asList(new LogMessage(0, LogEntry.Level.INFO, "VEVENT #{} [{}]", no, ICal4jUtils.printDump(vevent)));
				}
			};
		}
		
		try {
			final EventInput result = parseEventObject(vevent, buffLogHandler);
			if (result.event.trimFieldLengths()) {
				LogHandler.log(buffLogHandler, 1, LogEntry.Level.WARN, "Some fields were truncated due to max-length");
			}
			consumer.consume(result);

		} catch (Throwable t) {
			LogHandler.log(buffLogHandler, 0, LogEntry.Level.ERROR, "Reason: {}", LangUtils.getThrowableMessage(t));
		}

		if (logHandler != null && buffLogHandler != null) {
			final List<LogEntry> entries = buffLogHandler.flush();
			if (entries != null) logHandler.handle(entries);
		}
	}
	
	public EventInput parseEventObject(final VEvent vevent) throws WTException {
		return parseEventObject(vevent, null);
	}
	
	public EventInput parseEventObject(final VEvent vevent, final LogHandler logHandler) throws WTException {
		// See http://www.kanzaki.com/docs/ical/vevent.html
		EventEx event = new EventEx();
		
		//TODO: pass string field lengths in constructor or take them from db field definitions
		
		// UID: globally unique identifier
		// http://www.kanzaki.com/docs/ical/uid.html
		String uid = ICalendarUtils.getUidValue(vevent);
		if (!StringUtils.isBlank(uid)) {
			event.setPublicUid(uid);
		} else {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Uid is missing");
		}
		
		// ORGANIZER: who have organized the entity
		// https://www.kanzaki.com/docs/ical/organizer.html
		try {
			InternetAddress iaOrg = ICalendarUtils.getOrganizerAddress(vevent);
			//TODO: should we raise an exception when null?
			if (iaOrg != null) event.setOrganizer(InternetAddressUtils.toFullAddress(iaOrg));
		} catch (Exception ex) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Organizer invalid or empty [{}]", ICal4jUtils.getPropertyValue(vevent.getOrganizer()));
		}
		
		// CREATED: the date and time when the entity was created in the store
		// https://www.kanzaki.com/docs/ical/created.html
		event.setCreationTimestamp(ICalendarUtils.getPropertyValueAsDateTime(vevent.getCreated(), org.joda.time.DateTimeZone.UTC));
		
		// LAST-MODIFIED: the date and time when the entity was last-revised in the store
		// http://www.kanzaki.com/docs/ical/lastModified.html
		event.setRevisionTimestamp(ICalendarUtils.getPropertyValueAsDateTime(vevent.getLastModified(), org.joda.time.DateTimeZone.UTC));
		
		// SEQUENCE: the revision sequence number
		// http://www.kanzaki.com/docs/ical/sequence.html
		Sequence sequence = vevent.getSequence();
		if (sequence != null) {
			event.setRevisionSequence(sequence.getSequenceNo());
		}
		
		DateTimeZone eventTimezone = null;
		org.joda.time.DateTime start = null;
		org.joda.time.DateTime end = null;
		boolean isAllDay = ICal4jUtils.isAllDay(vevent);
		if (isAllDay) {
			event.setAllDay(true);
			org.joda.time.LocalDate localStart = ICal4jUtils.toJodaLocalDate(ICal4jUtils.getDatePropertyValue(vevent.getStartDate()), DateTimeZone.UTC);
			if (localStart == null) {
				LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "DTSTART must be set");
				throw new WTException("DTSTART must be set");
			}
			start = localStart.toDateTimeAtStartOfDay(defaultTz);
			event.setStart(start);
			
			org.joda.time.LocalDate localEnd = ICal4jUtils.toJodaLocalDate(ICal4jUtils.getDatePropertyValue(vevent.getEndDate()), DateTimeZone.UTC);
			if (localEnd == null) localEnd = localStart.plusDays(1);
			end = localEnd.toDateTimeAtStartOfDay(defaultTz);
			event.setEnd(end);
			
		} else {
			event.setAllDay(false);
			start = ICal4jUtils.toJodaDateTime((DateTime)ICal4jUtils.getDatePropertyValue(vevent.getStartDate()), defaultTz);
			if (start == null) {
				LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "DTSTART must be set");
				throw new WTException("DTSTART must be set");
			}
			event.setStart(start);
			
			end = ICal4jUtils.toJodaDateTime((DateTime)ICal4jUtils.getDatePropertyValue(vevent.getEndDate()), defaultTz);
			if (end == null) end = start.plusHours(1);
			event.setEnd(end);
		}
		eventTimezone = event.getStart().getZone();
		event.setTimezone(eventTimezone.getID());

		// SUMMARY: short summary or subject for the calendar component
		// https://www.kanzaki.com/docs/ical/summary.html
		String summary = ICalendarUtils.getSummary(vevent);
		if (!StringUtils.isBlank(summary)) {
			event.setTitle(summary);
		} else {
			event.setTitle("");
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Title is empty");
		}
		
		// LOCATION: the intended venue for the activity defined by a calendar component
		// https://www.kanzaki.com/docs/ical/location.html
		String location = ICal4jUtils.getPropertyValue(vevent.getLocation());
		if (!StringUtils.isBlank(location)) {
			event.setLocation(location);
		} else {
			event.setLocation(null);
		}
		
		// DESCRIPTION: the complete description
		// http://www.kanzaki.com/docs/ical/description.html
		String description = ICal4jUtils.getPropertyValue(vevent.getDescription());
		if (!StringUtils.isBlank(description)) {
			event.setDescription(description);
		} else {
			event.setDescription(null);
		}
		//TODO: add support to X-ALT-DESC for HTML descriptions
		
		// CLASS: capture the scope of the access the owner intends for information
		// https://www.kanzaki.com/docs/ical/class.html
		// https://appgenix.uservoice.com/forums/280499-business-calendar-2/suggestions/18698599-i-would-like-to-see-another-privacy-option-confid
		String clazz = ICal4jUtils.getPropertyValue(vevent.getClassification());
		if (!ignoreClassification && !StringUtils.isBlank(clazz)) {
			// CONFIDENTIAL or PRIVATE are private synonyms
			if (StringUtils.equals(clazz, Clazz.CONFIDENTIAL.getValue()) || StringUtils.equals(clazz, Clazz.PRIVATE.getValue())) {
				event.setVisibility(EventBase.Visibility.PRIVATE);
			} else {
				event.setVisibility(EventBase.Visibility.PUBLIC);
			}
		} else {
			event.setVisibility(defaultVisibility);
		}
		if (ignoreClassification) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "CLASS property ignored by ICalendarInput configuration");
		
		// TRANSP: whether an event is transparent or not to busy time searches
		// https://www.kanzaki.com/docs/ical/transp.html
		String transp = ICal4jUtils.getPropertyValue(vevent.getTransparency());
		if (!ignoreTrasparency && !StringUtils.isBlank(transp)) {
			EventBase.Transparency transparency = defaultTransparency;
			if (StringUtils.equals(transp, Transp.TRANSPARENT.getValue())) {
				transparency = EventBase.Transparency.TRANSPARENT;
			} else if (StringUtils.equals(transp, Transp.OPAQUE.getValue())) {
				transparency = EventBase.Transparency.OPAQUE;
			}
			event.setTransparency(transparency);
		} else {
			event.setTransparency(defaultTransparency);
		}
		if (ignoreTrasparency) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "TRANSP property ignored by ICalendarInput configuration");
		
		// VALARM: grouping of component properties that define an alarm
		// https://www.kanzaki.com/docs/ical/valarm.html
		if (!ignoreAlarms && !vevent.getAlarms().isEmpty()) {
			if (vevent.getAlarms().size() > 1) {
				LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Many VALARMs found, the first compatible will be used");
			}
			Event.Reminder rem = null;
			Iterator it = vevent.getAlarms().iterator();
			while (it.hasNext()) {
				rem = toEventReminder((VAlarm)it.next(), logHandler);
				if (rem != null) break;
			}
			event.setReminder(rem);
		}
		if (ignoreAlarms) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "VALARM properties ignored by ICalendarInput configuration");
		
		// RRULE, EXDATE: defines the repeating pattern and the list of exceptions
		// https://www.kanzaki.com/docs/ical/rrule.html
		// https://www.kanzaki.com/docs/ical/exdate.html
		ICalendarUtils.RecurInfo recurInfo = ICalendarUtils.extractRecurInfo(vevent);
		if (recurInfo.recur != null && start != null) {
			event.setRecurrence(new EventRecurrence(recurInfo.recur.toString(), start, recurInfo.exDates));
			
		} else if (recurInfo.recur != null) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Recurrence rule ignored: Start is missing");
		}
		
		// RECURRENCE-ID: identifies a specific instance of a recurring master entry
		// https://www.kanzaki.com/docs/ical/recurrenceId.html
		ICalendarUtils.RecurringRefs recurringRefs = ICalendarUtils.extractRecurringRefs(vevent);
		
		// ATTENDEE
		// https://www.kanzaki.com/docs/ical/attendee.html
		PropertyList attendees = vevent.getProperties(Property.ATTENDEE);
		if (!attendees.isEmpty()) {
			ArrayList<EventAttendee> eventAttendees = new ArrayList<>();
			for (Object o: attendees) {
				try {
					eventAttendees.add(toEventAttendee((Attendee)o));
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
			event.setAttendees(eventAttendees);
		}
		
		// ATTACH
		// https://www.kanzaki.com/docs/ical/attach.html
		PropertyList attachs = vevent.getProperties(Property.ATTACH);
		if (!ignoreAttachments && !attachs.isEmpty()) {
			ArrayList<EventAttachment> eventAttachments = new ArrayList<>();
			for (Object o: attachs) {
				try {
					eventAttachments.add(toEventAttachment((Attach)o));
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
			event.setAttachments(eventAttachments);
		}
		if (ignoreAttachments) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "ATTACH properties ignored by ICalendarInput configuration");
		
		if (!ignoreCategories) {
			Set<String> eventTags = new HashSet<>();
			
			// X-WT-TAG
			PropertyList xTags = vevent.getProperties(XTag.PROPERTY_NAME);
			for (Object o : xTags) {
				try {
					String[] tag = toTag((XProperty)o);
					if (!eventTags.contains(tag[0])) {
						if (validateTagId(tag[0])) {
							eventTags.add(tag[0]);
						} else {
							LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "XTag '{}' ignored: invalid ID '{}'", tag[1], tag[0]);
						}
					} else {
						LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "XTag '{}' ignored: mapping for ID '{}' already present", tag[1], tag[0]);
					}
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
			
			// CATEGORIES: specified categories or tags
			// https://www.kanzaki.com/docs/ical/categories.html
			Set<String> categories = ICalendarUtils.toCategoriesSet((Categories)vevent.getProperty(Property.CATEGORIES));
			if (categories != null && !categories.isEmpty()) {
				if (tagIdsByName != null) {
					for (String tagName : categories) {
						if (tagIdsByName.containsKey(tagName)) {
							for (String tagId : tagIdsByName.get(tagName)) {
								if (!eventTags.contains(tagId)) {
									if (validateTagId(tagId)) {
										eventTags.add(tagId);
									} else {
										LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "XTag '{}' ignored: invalid ID '{}'", tagName, tagId);
									}
								} else {
									LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Category '{}' ignored: mapping for '{}' already present", tagName, tagId);
								}
							}
						}
					}
				} else {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Categories ignored: NO tagName->tagId map provided");
				}
			}
			
		} else {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "CATEGORIES/X-WT-TAG properties ignored by ICalendarInput configuration");
		}
		
		// X-WT-CUSTOMFIELDVALUE
		PropertyList xCustomFieldValues = vevent.getProperties(XCustomFieldValue.PROPERTY_NAME);
		if (!ignoreCustomValues && !xCustomFieldValues.isEmpty()) {
			HashMap<String, CustomFieldValue> eventCustomValues = new HashMap<>();
			for (Object o : xCustomFieldValues) {
				try {
					CustomFieldValue cfv = toCustomFieldValue((XProperty)o);
					if (!eventCustomValues.containsKey(cfv.getFieldId())) {
						eventCustomValues.put(cfv.getFieldId(), cfv);
					} else {
						LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "CustomValue '{}' ignored: already present", cfv.getFieldId());
					}
					
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
			event.setCustomValues(eventCustomValues);
		}
		if (ignoreCustomValues) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "X-WT-CUSTOMFIELDVALUE properties ignored by ICalendarInput configuration");
		
		// RDATE, EXRULE, RSTATUS -> Not Supported!
		
		Set<String> names = new LinkedHashSet(Arrays.asList(Property.CONTACT, Property.GEO, Property.URL, Property.COMMENT, Property.RESOURCES));
		PropertyList extraProps = ICalendarUtils.extractProperties(vevent, names, true, null);
		
		return new EventInput(event, recurringRefs, extraProps, includeSourceComponentInOutput ? vevent : null);
	}
	
	private Event.Reminder toEventReminder(final VAlarm alarm, final LogHandler logHandler) {
		//TODO: Maybe add support to ACTION property [DISPLAY, EMAIL, AUDIO, PROCEDURE]
		// We only support one time reminders (REPEAT=1), we'll treat all in this way!
		if (alarm.getTrigger() == null) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Invalid TRIGGER");
			return null;
		}
		if ((alarm.getTrigger().getDate() != null) || (alarm.getTrigger().getDateTime()!= null)) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Date/Time TRIGGERs are not supported");
		}
		if (alarm.getTrigger().getDuration() == null) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Invalid TRIGGER: duration is missing");
			return null;
		}
		
		Dur duration = alarm.getTrigger().getDuration();
		int minutes = (duration.getWeeks() * 7 * 24 * 60) + (duration.getDays() * 24 * 60) + (duration.getHours() * 60) + duration.getMinutes();
		if (duration.getSeconds() > 0) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "TRIGGER seconds ignored");
		}
		if (!duration.isNegative()) {
			LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Ahead TRIGGERs are not supported, start instant will be used");
			minutes = 0;
		}
		return Event.Reminder.valueOf(minutes);
	}
	
	private String[] toTag(XProperty xTag) throws Exception {
		String id = XTag.getParamTagId(xTag);
		if (StringUtils.isEmpty(id)) throw new WTException("Unsupported tag: missing {} parameter", XTag.PARAM_ID);
		return new String[]{id, XTag.getTagName(xTag)};
	}
	
	private CustomFieldValue toCustomFieldValue(XProperty xCustomFieldValue) throws Exception {
		final String id = XCustomFieldValue.getFieldId(xCustomFieldValue);
		final String type = XCustomFieldValue.getFieldType(xCustomFieldValue);
		final String value = XCustomFieldValue.getFieldValue(xCustomFieldValue);
		
		CustomFieldValue cfv = null;
		if (XCustomFieldValue.TYPE_STRING.equals(type)) {
			cfv = new CustomFieldValue(id);
			cfv.setStringValue(LangUtils.value(value, (String)null));
		} else if (XCustomFieldValue.TYPE_NUMBER.equals(type)) {
			cfv = new CustomFieldValue(id);
			cfv.setNumberValue(LangUtils.value(value, (Double)null));
		} else if (XCustomFieldValue.TYPE_BOOLEAN.equals(type)) {
			cfv = new CustomFieldValue(id);
			cfv.setBooleanValue(LangUtils.value(value, (Boolean)null));
		} else if (XCustomFieldValue.TYPE_DATE.equals(type)) {
			cfv = new CustomFieldValue(id);
			cfv.setDateValue(JodaTimeUtils.parseDateTimeISO(value));
		} else if (XCustomFieldValue.TYPE_TEXT.equals(type)) {
			cfv = new CustomFieldValue(id);
			cfv.setTextValue(LangUtils.value(value, (String)null));
		} else {
			throw new WTException("Unsupported custom-value type [{}]", type);
		}
		return cfv;
	}
	
	private EventAttachment toEventAttachment(final Attach attach) throws Exception {
		EventAttachment eventAttachment = null;
		
		if (attach.getBinary() != null) {
			eventAttachment = new EventAttachmentWithBytes(attach.getBinary());
			eventAttachment.setFilename(ICal4jUtils.getParameterValue(attach.getParameter("FILENAME")));
			eventAttachment.setMediaType(eventAttachment.getFilename());
			
		} else if (attach.getUri() != null) {
			URL url = attach.getUri().toURL();
			eventAttachment = new EventAttachmentWithInputStream(url.openStream());
			eventAttachment.setFilename(url.getFile());
			eventAttachment.setMediaType(eventAttachment.getFilename());
		}
		return eventAttachment;
	}
	
	private EventAttendee toEventAttendee(final Attendee attendee) throws Exception {
		// See http://www.kanzaki.com/docs/ical/attendee.html
		EventAttendee eventAttendee = new EventAttendee();
		
		// Evaluates attendee details
		// Extract email and common name (CN)
		// Eg: CN=Henry Cabot:MAILTO:hcabot@host2.com -> drop ":MAILTO:"
		URI uri = attendee.getCalAddress();
		Cn cn = (Cn)attendee.getParameter(Parameter.CN);
		if (uri != null) {
			String address = uri.getSchemeSpecificPart();
			InternetAddress ia = InternetAddressUtils.toInternetAddress(address, (cn == null) ? address : cn.getValue());
			eventAttendee.setRecipient(InternetAddressUtils.toFullAddress(ia));
		} else {
			throw new WTException("Attendee must be valid [{}]", attendee.toString());
			//log.add(new MessageLogEntry(LogEntry.Level.WARN, "Attendee must have a valid address [{0}]", attendee.toString()));
		}
		
		// Evaluates cuType
		CuType cuType = (CuType)attendee.getParameter(Parameter.CUTYPE);
		eventAttendee.setRecipientType(toAttendeeRecipientType(cuType));
		
		// Evaluates attendee role
		Role role = (Role)attendee.getParameter(Parameter.ROLE);
		eventAttendee.setRecipientRole(toAttendeeRecipientRole(role));
		
		// Evaluates attendee response status
		PartStat partstat = (PartStat)attendee.getParameter(Parameter.PARTSTAT);
		eventAttendee.setResponseStatus(toAttendeeResponseStatus(partstat));
		
		eventAttendee.setNotify(defaultAttendeeNotify);
		return eventAttendee;
	}
	
	public static EventAttendee.RecipientRole toAttendeeRecipientRole(final Role role) {
		if (Role.CHAIR.equals(role)) {
			return EventAttendee.RecipientRole.CHAIR;
		} else if (Role.REQ_PARTICIPANT.equals(role)) {
			return EventAttendee.RecipientRole.REQUIRED;
		} else if (Role.REQ_PARTICIPANT.equals(role)) {
			return EventAttendee.RecipientRole.OPTIONAL;
		} else {
			return EventAttendee.RecipientRole.OPTIONAL;
		}
	}
	
	public static EventAttendee.RecipientType toAttendeeRecipientType(final CuType cuType) {
		if (CuType.INDIVIDUAL.equals(cuType)) {
			return EventAttendee.RecipientType.INDIVIDUAL;
		} else if (CuType.RESOURCE.equals(cuType)) {
			return EventAttendee.RecipientType.RESOURCE;
		} else if (CuType.ROOM.equals(cuType)) {
			return EventAttendee.RecipientType.RESOURCE;
		} else {
			return EventAttendee.RecipientType.INDIVIDUAL;
		}
	}
	
	public static EventAttendee.ResponseStatus toAttendeeResponseStatus(final PartStat partStat) {
		if (PartStat.ACCEPTED.equals(partStat)) {
			return EventAttendee.ResponseStatus.ACCEPTED;
		} else if (PartStat.TENTATIVE.equals(partStat)) {
			return EventAttendee.ResponseStatus.TENTATIVE;
		} else if (PartStat.DECLINED.equals(partStat)) {
			return EventAttendee.ResponseStatus.DECLINED;
		} else {
			return EventAttendee.ResponseStatus.NEEDS_ACTION;
		}
	}
	
	private static boolean isAllDay(org.joda.time.DateTime start, org.joda.time.DateTime end) {
		// Checks if a date range can be considered as an all-day event.
		
		org.joda.time.DateTime dt = null;
		dt = start.withZone(org.joda.time.DateTimeZone.UTC);
		if (!dt.isEqual(dt.withTimeAtStartOfDay())) return false;
		dt = end.withZone(org.joda.time.DateTimeZone.UTC);
		if (!dt.isEqual(dt.withTimeAtStartOfDay())) return false;
		if (start.plusDays(1).getDayOfMonth() != end.getDayOfMonth()) return false;
		return true;
	}
	
	private boolean validateTagId(String tagId) {
		if (tagNamesById == null || tagNamesById.isEmpty()) {
			return true;
		} else {
			return tagNamesById.containsKey(tagId);
		}
	}
	
	
	
	
	
	
	
	
	/*
	public <T extends EventInput> void fromICalendarFile2(InputStream is, BeanHandler<T> handler) throws WTException {
		try {
			final Calendar calendar = ICalendarUtils.parseRelaxed(is);
			fromICalendarFile2(calendar, handler);
		} catch(IOException | ParserException ex) {
			throw new WTException(ex, "Unable to read stream");
		}
	}
	
	public <T extends EventInput> void fromICalendarFile2(Calendar calendar, BeanHandler<T> handler) throws WTException {
		// See http://www.kanzaki.com/docs/ical/
		
		for (Iterator xi = calendar.getComponents().iterator(); xi.hasNext();) {
			final Component component = (Component) xi.next();
			if (component instanceof VEvent) {
				final VEvent ve = (VEvent)component;
				
				EventInput result = null;
				try {
					final LogEntries ilog = writeLog ? new LogEntries() : null;
					result = fromVEvent(ve, ilog);
					if ((log != null) && (ilog != null)) {
						if (!ilog.isEmpty()) {
							log.addMaster(new MessageLogEntry(LogEntry.Level.WARN, "VEVENT ['{1}', {0}]", ve.getUid(), ve.getSummary()));
							log.addAll(ilog);
						}
					}
				} catch(Exception ex) {
					if (log != null) log.addMaster(new MessageLogEntry(LogEntry.Level.ERROR, "VEVENT ['{1}', {0}]. Reason: {3}", ve.getUid(), ve.getSummary(), t.getMessage()));
				} finally {
					try {
						handler.handle(result, log);
					} catch(Exception ex) {
						throw new WTException(ex);
					}
				}
			}
		}
	}
	*/
}
