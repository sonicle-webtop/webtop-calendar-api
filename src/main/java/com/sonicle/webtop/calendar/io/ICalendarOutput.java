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

import com.sonicle.commons.Check;
import com.sonicle.commons.ClassUtils;
import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.calendar.CalendarUtils;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttachment;
import com.sonicle.webtop.calendar.model.EventAttachmentWithBytes;
import com.sonicle.webtop.calendar.model.EventAttendee;
import com.sonicle.webtop.calendar.model.EventEx;
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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import java.net.URISyntaxException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.Encoding;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.parameter.XParameter;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attach;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Created;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.LastModified;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.XProperty;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;
import com.sonicle.webtop.calendar.model.EventBounds;
import net.fortuna.ical4j.model.property.RRule;

/**
 *
 * @author malbinola
 */
public class ICalendarOutput {
	private final String prodId;
	private Map<String, String> tagNamesById = null;
	private TagsMappingMode categoriesMappingMode = TagsMappingMode.NAME;
	private boolean ignoreAlarms = false;
	private boolean ignoreCategories = false;
	private boolean ignoreAttachments = false;
	private boolean ignoreCustomValues = false;
	private LogHandler logHandler = null;
	
	public ICalendarOutput(String prodId) {
		this.prodId = Check.notNull(prodId, "prodId");
	}
	
	public ICalendarOutput(String prodId, Map<String, String> tagNamesById) {
		this(prodId);
		this.tagNamesById = tagNamesById;
	}
	
	public ICalendarOutput withCategoriesMappingMode(TagsMappingMode categoriesMappingMode) {
		this.categoriesMappingMode = Check.notNull(categoriesMappingMode, "categoriesMappingMode");
		return this;
	}
	
	public ICalendarOutput withIgnoreAlarms(boolean ignoreAlarms) {
		this.ignoreAlarms = ignoreAlarms;
		return this;
	}
	
	public ICalendarOutput withIgnoreCategories(boolean ignoreCategories) {
		this.ignoreCategories = ignoreCategories;
		return this;
	}
	
	public ICalendarOutput withIgnoreAttachments(boolean ignoreAttachments) {
		this.ignoreAttachments = ignoreAttachments;
		return this;
	}
	
	public ICalendarOutput withIgnoreCustomValues(boolean ignoreCustomValues) {
		this.ignoreCustomValues = ignoreCustomValues;
		return this;
	}
	
	public ICalendarOutput withLogHandler(LogHandler logHandler) {
		this.logHandler = logHandler;
		return this;
	}
	
	/**
	 * @deprecated use ICalendarUtils.print() instead
	 */
	@Deprecated
	public String write(Calendar iCalendar) throws IOException {
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			CalendarOutputter outputter = new CalendarOutputter();
			outputter.output(iCalendar, baos);
			return baos.toString("UTF8");
		} finally {
			IOUtils.closeQuietly(baos);
		}
	}
	
	public String writeICalendar(final Method method, final EventEx event, final PropertyList extraProps) throws WTException, IOException {
		return ICalendarUtils.print(createCalendar(method, Arrays.asList(new EventOutput(event, extraProps))));
	}
	
	public Calendar createCalendar(final Method method, final EventEx event, final PropertyList extraProps) throws WTException {
		return createCalendar(method, Arrays.asList(new EventOutput(event, extraProps)));
	}
	
	public Calendar createCalendar(final Method method, final Collection<EventOutput> outputs) throws WTException {
		Calendar ical = null;
		if (method == null) {
			ical = ICalendarUtils.newCalendar(prodId, null);
		} else {
			ensureMethodRequestOrCancel(method);
			ical = ICalendarUtils.newCalendar(prodId, method);
		}
		
		int count = 0;
		for (EventOutput output : outputs) {
			count++;
			final EventEx event = output.event;
			final int eventNo = count;
			
			BufferingLogHandler buffLogHandler = null;
			if (logHandler != null) {
				buffLogHandler = new BufferingLogHandler() {
					@Override
					public List<LogEntry> first() {
						return Arrays.asList(new LogMessage(0, LogEntry.Level.INFO, "Event #{} [{}, {}]", eventNo, event.getPublicUid(), event.getTitle()));
					}
				};
			}
			
			try {
				ical.getComponents().add(createEventObject(method, output.event, output.extraProps, logHandler));
			} catch (Throwable t) {
				if (buffLogHandler != null) {
					LogHandler.log(buffLogHandler, 0, LogEntry.Level.ERROR, "Reason: {}", LangUtils.getThrowableMessage(t));
				} else {
					throw t;
				}
			}
			
			if (logHandler != null && buffLogHandler != null) {
				final List<LogEntry> entries = buffLogHandler.flush();
				if (entries != null) logHandler.handle(entries);
			}
		}
		return ical;
	}
	
	public VEvent createEventObject(final EventEx event, final PropertyList extraProps) throws WTException {
		return createEventObject(null, event, extraProps, null);
	}
	
	public VEvent createEventObject(final Method method, final EventEx event, final PropertyList extraProps, final LogHandler logHandler) throws WTException {
		boolean useUTCDateTimes = (method != null);
		VEvent vevent = new VEvent();
		
		Date start, end;
		EventBounds bounds = CalendarUtils.toNormalizedEventBounds(event);
		if (bounds.isAllDay()) {
			// If event is AD do not include time in start/end, the output will be a date only field (without time)
			start = ICal4jUtils.toIC4jDate(bounds.getStart().withZone(bounds.getTimezoneObject()).toLocalDate());
			end = ICal4jUtils.toIC4jDate(bounds.getEnd().withZone(bounds.getTimezoneObject()).toLocalDate());
		} else {
			if (useUTCDateTimes) {
				// Meeting requests should use UTC date/time values instead of notation with local timezones
				start = ICal4jUtils.toIC4jDateTimeUTC(bounds.getStart());
				end = ICal4jUtils.toIC4jDateTimeUTC(bounds.getEnd());
			} else {
				start = ICal4jUtils.toIC4jDateTime(bounds.getStart(), bounds.getTimezoneObject(), true);
				end = ICal4jUtils.toIC4jDateTime(bounds.getEnd(), bounds.getTimezoneObject(), true);
			}
		}
		
		// Status: meeting status
		if (Method.REQUEST.equals(method)) {
			ICal4jUtils.addProperty(vevent, Status.VEVENT_CONFIRMED);
		} else if (Method.CANCEL.equals(method)) {
			ICal4jUtils.addProperty(vevent, Status.VEVENT_CANCELLED);
		}
		
		// UID: globally unique identifier
		// http://www.kanzaki.com/docs/ical/uid.html
		ICal4jUtils.addProperty(vevent, new Uid(event.getPublicUid()));
		
		// ORGANIZER: who have organized the entity
		// https://www.kanzaki.com/docs/ical/organizer.html
		Organizer organizer = ICalendarUtils.toOrganizerProp(event.getOrganizerAddress(), event.getOrganizerCN());
		if (organizer != null) ICal4jUtils.addProperty(vevent, organizer);
		
		// CREATED: the date and time when the entity was created in the store
		// https://www.kanzaki.com/docs/ical/created.html
		ICal4jUtils.addProperty(vevent, new Created(ICal4jUtils.toIC4jDateTimeUTC(event.getCreationTimestamp())));
		
		// LAST-MODIFIED: the date and time when the entity was last-revised in the store
		// http://www.kanzaki.com/docs/ical/lastModified.html
		ICal4jUtils.addProperty(vevent, new LastModified(ICal4jUtils.toIC4jDateTimeUTC(event.getRevisionTimestamp())));
		
		// SEQUENCE: the revision sequence number
		// http://www.kanzaki.com/docs/ical/sequence.html
		ICal4jUtils.addProperty(vevent, new Sequence(event.getRevisionSequence()));
		
		// DTSTART: when the event begins
		// https://www.kanzaki.com/docs/ical/dtstart.html
		ICal4jUtils.addProperty(vevent, new DtStart(start));
		
		// DTEND: when the event ends
		// https://www.kanzaki.com/docs/ical/dtend.html
		ICal4jUtils.addProperty(vevent, new DtEnd(end));
		
		// SUMMARY: a brief description of the entity
		// https://www.kanzaki.com/docs/ical/summary.html
		ICal4jUtils.addProperty(vevent, new Summary(event.getTitle()));
		
		// LOCATION: the intended venue for the activity
		// http://www.kanzaki.com/docs/ical/location.html
		if (!StringUtils.isEmpty(event.getLocation())) {
			ICal4jUtils.addProperty(vevent, new Location(event.getLocation()));
		}
		
		// DESCRIPTION: the complete description
		// http://www.kanzaki.com/docs/ical/description.html
		if (!StringUtils.isEmpty(event.getDescription())) {
			ICal4jUtils.addProperty(vevent, new Description(event.getDescription()));
		}
		//TODO: add support to X-ALT-DESC for HTML descriptions
		
		// CLASS: capture the scope of the access the owner intends for information
		// https://www.kanzaki.com/docs/ical/class.html
		// https://appgenix.uservoice.com/forums/280499-business-calendar-2/suggestions/18698599-i-would-like-to-see-another-privacy-option-confid
		ICal4jUtils.addProperty(vevent, event.isVisibilityPrivate() ? Clazz.CONFIDENTIAL : Clazz.PUBLIC);
		
		// TRANSP: defines whether an event is transparent or not to busy time searches
		// http://www.kanzaki.com/docs/ical/transp.html
		ICal4jUtils.addProperty(vevent, event.isTransparencyOpaque() ? Transp.OPAQUE : Transp.TRANSPARENT);
		
		// VALARM: grouping of component properties that define an alarm
		// https://www.kanzaki.com/docs/ical/valarm.html
		if (!ignoreAlarms && event.getReminder() != null) {
			vevent.getAlarms().add(toVAlarm(event.getReminder(), event.getTitle()));
		}
		if (ignoreAlarms) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Reminder ignored by ICalendarOutput configuration");
		
		// RRULE, EXDATE: defines the repeating pattern and the list of exceptions
		// https://www.kanzaki.com/docs/ical/rrule.html
		// https://www.kanzaki.com/docs/ical/exdate.html
		if (event.hasRecurrence()) {
			Recur recur = event.getRecurrence().getRecurRule();
			if (recur != null) {
				vevent.getProperties().add(new RRule(recur));
				if (event.getRecurrence().getExcludedDates() != null) {
					LocalTime startTime = event.getStart().withZone(DateTimeZone.UTC).toLocalTime();
					vevent.getProperties().add(ICal4jUtils.toIC4jExDate(event.getRecurrence().getExcludedDates(), startTime, DateTimeZone.UTC, true));
				}
			} else {
				LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Recur rule is null");
			}
		}
		
		// RECURRENCE-ID: identifies a specific instance of a recurring master entry
		// https://www.kanzaki.com/docs/ical/recurrenceId.html
		
		// ATTENDEE
		// https://www.kanzaki.com/docs/ical/attendee.html
		if (event.hasAttendees()) {
			for (EventAttendee attendee : event.getAttendeesOrEmpty()) {
				try {
					if (attendee.hasEmailRecipient()) {
						vevent.getProperties().add(toAttendee(method, attendee));
					} else {
						LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Attendee ignored: {} does NOT have email-recipient", attendee.getAttendeeId());
					}
					
				} catch (AddressException ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
		}
		
		// ATTACH
		// https://www.kanzaki.com/docs/ical/attach.html
		if (!ignoreAttachments && event.hasAttachments()) {
			for (EventAttachment attachment : event.getAttachmentsOrEmpty()) {
				try {
					vevent.getProperties().add(toAttach(attachment));
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
		}
		if (ignoreAttachments) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Attachments ignored by ICalendarOutput configuration");
		
		if (!ignoreCategories && event.hasTags()) {
			// CATEGORIES: specified categories or tags
			// https://www.kanzaki.com/docs/ical/categories.html
			if (TagsMappingMode.NAME.equals(categoriesMappingMode)) {
				if (tagNamesById != null) {
					Categories categories = ICalendarUtils.toCategories(mapCategoryNames(event.getTags(), logHandler));
					if (categories != null) vevent.getProperties().add(categories);
				} else {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Tags ignored: NO tagId->tagName map provided");
				}
			} else if (TagsMappingMode.ID.equals(categoriesMappingMode)) {
				Categories categories = ICalendarUtils.toCategories(event.getTags());
				if (categories != null) vevent.getProperties().add(categories);
			}
			
			// X-WT-TAG
			for (String tagId : event.getTagsOrEmpty()) {
				String tagName = null;
				if (tagNamesById != null) tagName = tagNamesById.get(tagId);
				if (tagName == null) tagName = tagId;
				try {
					vevent.getProperties().add(XTag.toProperty(tagId, tagName));
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
			
		}
		if (ignoreCategories) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Tags ignored by ICalendarOutput configuration");
		
		// X-WT-CUSTOMFIELDVALUE
		Map<String, CustomFieldValue> customValues = event.getCustomValues();
		if (!ignoreCustomValues && customValues != null) {
			for (Entry<String, CustomFieldValue> entry : customValues.entrySet()) {
				try {
					vevent.getProperties().add(toXCustomFieldValue(entry.getValue()));
				} catch (Exception ex) {
					LogHandler.log(logHandler, 1, LogEntry.Level.WARN, ex.getMessage());
				}
			}
		}
		if (ignoreCustomValues) LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "CustomValues ignored by ICalendarOutput configuration");
		
		// RDATE, EXRULE, RSTATUS -> Not Supported!
		// CONTACT, GEO, URL, COMMENT, RESOURCES -> Not Supported! (from extra props)
		
		if (extraProps != null) {
			for (Property property : extraProps) {
				vevent.getProperties().add(property);
			}
		}
		
		return vevent;
	}
	
	public static VAlarm toVAlarm(final Event.Reminder reminder, final String description) {
		// We only support one time reminders (REPEAT=1)
		VAlarm alarm = new VAlarm(new Dur(0, 0, -reminder.getMinutesValue(), 0));
		alarm.getProperties().add(Action.DISPLAY);
		alarm.getProperties().add(new Description(description));
		return alarm;
	}
	
	public static Attendee toAttendee(final Method calMethod, final EventAttendee attendee) throws AddressException {
		// See http://www.kanzaki.com/docs/ical/attendee.html
		Attendee att = new Attendee();
		ParameterList params = att.getParameters();
		
		// CN and email: attendee main details
		InternetAddress iaRecipient = attendee.getRecipientInternetAddress();
		if (iaRecipient != null) {
			String mailto = "mailto:" + iaRecipient.getAddress();
			att.setCalAddress(URI.create(mailto));
			if (!StringUtils.isBlank(iaRecipient.getPersonal())) {
				params.add(new Cn(iaRecipient.getPersonal()));
			}
		}
		
		// CuType: calendar user type (INDIVIDUAL, RESOURCE, etc...)
		// http://www.kanzaki.com/docs/ical/cutype.html
		params.add(toCuType(attendee.getRecipientType()));
		
		// Role: attendee participation role
		// http://www.kanzaki.com/docs/ical/role.html
		params.add(toRole(attendee.getRecipientRole()));
		
		// PartStat: participation status for the calendar user
		// http://www.kanzaki.com/docs/ical/partstat.html
		params.add(toPartStat(attendee.getResponseStatus()));
		
		if (Method.REQUEST.equals(calMethod)) {
			params.add(Rsvp.TRUE);
		}
		return att;
	}
	
	public static Attach toAttach(final EventAttachment attachment) throws Exception {
		if (attachment instanceof EventAttachmentWithBytes) {
			EventAttachmentWithBytes attwb = (EventAttachmentWithBytes)attachment;
			ParameterList pl = new ParameterList();
			pl.add(Value.BINARY);
			pl.add(Encoding.BASE64);
			pl.add(new XParameter("FILENAME", attachment.getFilename()));
			return new Attach(pl, attwb.getBytes());
			
		} else {
			throw new WTException("Unsupported custom-value: {}", ClassUtils.getSimpleClassName(attachment.getClass()));
		}
	}
	
	public static Role toRole(final EventAttendee.RecipientRole recipientRole) {
		if (EventAttendee.RecipientRole.CHAIR.equals(recipientRole)) {
			return Role.CHAIR;
		} else if (EventAttendee.RecipientRole.REQUIRED.equals(recipientRole)) {
			return Role.REQ_PARTICIPANT;
		} else if (EventAttendee.RecipientRole.OPTIONAL.equals(recipientRole)) {
			return Role.OPT_PARTICIPANT;
		} else {
			return Role.REQ_PARTICIPANT;
		}
	}
	
	public static CuType toCuType(final EventAttendee.RecipientType recipientType) {
		if (EventAttendee.RecipientType.INDIVIDUAL.equals(recipientType)) {
			return CuType.INDIVIDUAL;
		} else if (EventAttendee.RecipientType.RESOURCE.equals(recipientType)) {
			return CuType.RESOURCE;
		} else {
			return CuType.INDIVIDUAL;
		}
	}
	
	public static PartStat toPartStat(final EventAttendee.ResponseStatus responseStatus) {
		if (EventAttendee.ResponseStatus.ACCEPTED.equals(responseStatus)) {
			return PartStat.ACCEPTED;
		} else if (EventAttendee.ResponseStatus.TENTATIVE.equals(responseStatus)) {
			return PartStat.TENTATIVE;
		} else if (EventAttendee.ResponseStatus.DECLINED.equals(responseStatus)) {
			return PartStat.DECLINED;
		} else {
			return PartStat.NEEDS_ACTION;
		}
	}
	
	public static XProperty toXCustomFieldValue(final CustomFieldValue customFieldValue) throws Exception {
		try {
			if (customFieldValue.getStringValue() != null) return XCustomFieldValue.toProperty(customFieldValue.getFieldId(), customFieldValue.getStringValue(), false);
			if (customFieldValue.getNumberValue()!= null) return XCustomFieldValue.toProperty(customFieldValue.getFieldId(), customFieldValue.getNumberValue());
			if (customFieldValue.getBooleanValue()!= null) return XCustomFieldValue.toProperty(customFieldValue.getFieldId(), customFieldValue.getBooleanValue());
			if (customFieldValue.getDateValue()!= null) return XCustomFieldValue.toProperty(customFieldValue.getFieldId(), customFieldValue.getDateValue());
			if (customFieldValue.getTextValue()!= null) return XCustomFieldValue.toProperty(customFieldValue.getFieldId(), customFieldValue.getTextValue(), true);
			throw new WTException("Unsupported custom-value: value is NULL");

		} catch (URISyntaxException ex) {
			throw new WTException(ex, "Unsupported custom-value");
		}
	}

	private void ensureMethodRequestOrCancel(Method method) throws WTException {
		if (!Method.REQUEST.equals(method) && !Method.CANCEL.equals(method)) {
			throw new WTException("Invalid method: only REQUEST or CANCEL are supported");
		}
	}
	
	private Set<String> mapCategoryNames(Set<String> tags, LogHandler logHandler) {
		if (tags == null || tagNamesById == null) return null;
		LinkedHashSet<String> catNames = new LinkedHashSet<>();
		for (String tag : tags) {
			if (tagNamesById.containsKey(tag)) {
				catNames.add(tagNamesById.get(tag));
			} else {
				LogHandler.log(logHandler, 1, LogEntry.Level.WARN, "Unable to find tag name [{}]", tag);
			}
		}
		return catNames;
	}
	
	public static enum TagsMappingMode {
		NAME, ID, NONE
	}
}
