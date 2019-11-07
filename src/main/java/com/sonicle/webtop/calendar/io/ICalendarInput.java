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
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttendee;
import com.sonicle.webtop.calendar.model.EventRecurrence;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.ICal4jUtils;
import com.sonicle.webtop.core.util.ICalendarUtils;
import com.sonicle.webtop.core.util.LogEntries;
import com.sonicle.webtop.core.util.LogEntry;
import com.sonicle.webtop.core.util.MessageLogEntry;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.mail.internet.InternetAddress;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.NumberList;
import net.fortuna.ical4j.model.Parameter;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.WeekDayList;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.RecurrenceId;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author malbinola
 */
public class ICalendarInput {
	private DateTimeZone defaultTz;
	private boolean defaultIsPrivate = false;
	private boolean defaultAttendeeNotify = false;
	private boolean includeVEventSourceInOutput = false;
	
	public ICalendarInput(DateTimeZone defaultTz) {
		this.defaultTz = defaultTz;
	}
	
	public ICalendarInput withDefaultTz(DateTimeZone defaultTz) {
		this.defaultTz = defaultTz;
		return this;
	}
	
	public ICalendarInput withDefaultIsPrivate(boolean defaultIsPrivate) {
		this.defaultIsPrivate = defaultIsPrivate;
		return this;
	}
	
	public ICalendarInput withDefaultAttendeeNotify(boolean defaultAttendeeNotify) {
		this.defaultAttendeeNotify = defaultAttendeeNotify;
		return this;
	}
	
	public ICalendarInput withIncludeVEventSourceInOutput(boolean includeVEventSourceInOutput) {
		this.includeVEventSourceInOutput = includeVEventSourceInOutput;
		return this;
	}
	
	public ArrayList<EventInput> fromICalendarFile(InputStream is, LogEntries log) throws WTException {
		try {
			return fromICalendarFile(ICalendarUtils.parse(is), log);
		} catch(IOException | ParserException ex) {
			throw new WTException(ex, "Unable to read stream");
		}	
	}
	
	public ArrayList<EventInput> fromICalendarFile(Calendar calendar, LogEntries log) throws WTException {
		// See http://www.kanzaki.com/docs/ical/
		ArrayList<EventInput> results = new ArrayList<>();
		
		for (Iterator xi = calendar.getComponents().iterator(); xi.hasNext();) {
			final Component component = (Component) xi.next();
			if (component instanceof VEvent) {
				final VEvent ve = (VEvent)component;
				final LogEntries velog = (log != null) ? new LogEntries() : null;
				
				try {
					final EventInput result = fromVEvent(ve, velog);
					if (result.event.trimFieldLengths()) {
						if (velog != null) velog.add(new MessageLogEntry(LogEntry.Level.WARN, "Some fields were truncated due to max-length"));
					}
					results.add(result);
					if ((log != null) && (velog != null)) {
						if (!velog.isEmpty()) {
							log.addMaster(new MessageLogEntry(LogEntry.Level.WARN, "VEVENT [{0}]", ICalendarUtils.print(ve)));
							log.addAll(velog);
						}
					}
				} catch(Throwable t) {
					if (log != null) log.addMaster(new MessageLogEntry(LogEntry.Level.ERROR, "VEVENT [{0}]. Reason: {1}", ICalendarUtils.print(ve), t.getMessage()));
				}
			}
		}
		return results;
	}
	
	public EventInput fromVEvent(VEvent ve, LogEntries log) throws WTException {
		// See http://www.kanzaki.com/docs/ical/vevent.html
		Event event = new Event();
		String exRefersToPublicUid = null;
		LocalDate addsExceptionOnMaster = null;
		
		//TODO: pass string field lengths in constructor or take them from db field definitions
		String uid = ICalendarUtils.getUidValue(ve);
		if (!StringUtils.isBlank(uid)) {
			event.setPublicUid(uid);
		} else {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Missing Uid"));
		}
		
		DateTimeZone eventTimezone = null;
		boolean isAllDay = ICal4jUtils.isAllDay(ve);
		if (isAllDay) {
			event.setAllDay(true);
			org.joda.time.LocalDate startLd = ICal4jUtils.toJodaLocalDate(ICal4jUtils.getDate(ve.getStartDate()), DateTimeZone.UTC);
			if (startLd == null) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "DTSTART must be set"));
				throw new WTException("DTSTART must be set");
			}
			event.setStartDate(startLd.toDateTimeAtStartOfDay(defaultTz));
			
			org.joda.time.LocalDate endLd = ICal4jUtils.toJodaLocalDate(ICal4jUtils.getDate(ve.getEndDate()), DateTimeZone.UTC);
			if (endLd == null) endLd = startLd.plusDays(1);
			event.setEndDate(endLd.toDateTimeAtStartOfDay(defaultTz));
			
		} else {
			event.setAllDay(false);
			org.joda.time.DateTime startDt = ICal4jUtils.toJodaDateTime((DateTime)ICal4jUtils.getDate(ve.getStartDate()), defaultTz);
			if (startDt == null) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "DTSTART must be set"));
				throw new WTException("DTSTART must be set");
			}
			event.setStartDate(startDt);
			
			org.joda.time.DateTime endDt = ICal4jUtils.toJodaDateTime((DateTime)ICal4jUtils.getDate(ve.getEndDate()), defaultTz);
			if (endDt == null) endDt = startDt.plusHours(1);
			event.setEndDate(endDt);
		}
		eventTimezone = event.getStartDate().getZone();
		event.setTimezone(eventTimezone.getID());

		// Title
		if (ve.getSummary() != null) {
			event.setTitle(StringUtils.defaultString(ve.getSummary().getValue()));
		} else {
			event.setTitle("");
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Missing Title"));
		}

		// Description
		if (ve.getDescription() != null) {
			event.setDescription(StringUtils.defaultString(ve.getDescription().getValue()));
		} else {
			event.setDescription(null);
		}

		// Location
		if (ve.getLocation() != null) {
			event.setLocation(StringUtils.defaultString(ve.getLocation().getValue()));
		} else {
			event.setLocation(null);
		}

		event.setIsPrivate(defaultIsPrivate);

		// Busy flag
		if (ve.getTransparency() != null) {
			String transparency = ve.getTransparency().getValue();
			event.setBusy(!StringUtils.equals(transparency, "TRANSPARENT"));
		} else {
			event.setBusy(false);
		}
		
		// Reminder
		if (!ve.getAlarms().isEmpty()) {
			if (ve.getAlarms().size() > 1) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Many VALARMs found, the first compatible will be used"));
			}
			Event.Reminder rem = null;
			Iterator it = ve.getAlarms().iterator();
			while (it.hasNext()) {
				rem = fromVAlarm((VAlarm)it.next(), log);
				if (rem != null) break;
			}
			event.setReminder(rem);
		}
		
		// Others...
		event.setActivityId(null);
		event.setMasterDataId(null);
		event.setStatMasterDataId(null);
		event.setCausalId(null);

		// Extract recurrence (real definition or reference to a previous instance)
		RRule rr = (RRule)ve.getProperty(Property.RRULE);
		if (rr != null) {
			// Extract exDates
			Set<LocalDate> excludedDates = null;
			PropertyList exDates = ve.getProperties(Property.EXDATE); // We can have multiple ExDate occurrence!
			if (!exDates.isEmpty()) {
				excludedDates = new LinkedHashSet<>();
				for (Object o : exDates) {
					excludedDates.addAll(fromVEventExDate((ExDate)o, eventTimezone, log));
				}
			}
			event.setRecurrence(rr.getRecur().toString(), event.getStartDate().withZone(eventTimezone).toLocalDate(), excludedDates);
			
		}
		
		// Recurrence-id property references the date referred to the 
		// master-event on which operate the exception described by this event
		RecurrenceId recurrenceId = (RecurrenceId)ve.getProperty(Property.RECURRENCE_ID);
		if (recurrenceId != null) {
			exRefersToPublicUid = event.getPublicUid();
			addsExceptionOnMaster = ICal4jUtils.toJodaLocalDate(recurrenceId.getDate(), DateTimeZone.UTC);
		}
		
		// Extracts organizer
		Organizer org = (Organizer)ve.getProperty(Property.ORGANIZER);
		if (org != null) {
			try {
				event.setOrganizer(fromVEventOrganizer(org, log));
			} catch(Throwable t) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, t.getMessage()));
			}
		}

		// Extracts partecipants
		PropertyList atts = ve.getProperties(Property.ATTENDEE);
		if (!atts.isEmpty()) {
			ArrayList<EventAttendee> attendees = new ArrayList<>();
			for(Object o: atts) {
				try {
					attendees.add(fromVEventAttendee((Attendee)o, log));
				} catch(Exception ex) {
					if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, ex.getMessage()));
				}
			}
			event.setAttendees(attendees);
		}
		
		return new EventInput(event, exRefersToPublicUid, addsExceptionOnMaster, includeVEventSourceInOutput ? ve : null);
	}
	
	public Event.Reminder fromVAlarm(VAlarm alarm, LogEntries log) {
		//TODO: Maybe add support to ACTION property [DISPLAY, EMAIL, AUDIO, PROCEDURE]
		// We only support one time reminders (REPEAT=1), we'll treat all in this way!
		if (alarm.getTrigger() == null) {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Invalid TRIGGER"));
			return null;
		}
		if ((alarm.getTrigger().getDate() != null) || (alarm.getTrigger().getDateTime()!= null)) {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Date/Time TRIGGERs are not supported"));
		}
		if (alarm.getTrigger().getDuration() == null) {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Invalid TRIGGER: duration is missing"));
			return null;
		}
		
		Dur duration = alarm.getTrigger().getDuration();
		int minutes = (duration.getWeeks() * 7 * 24 * 60) + (duration.getDays() * 24 * 60) + (duration.getHours() * 60) + duration.getMinutes();
		if (duration.getSeconds() > 0) {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "TRIGGER seconds ignored"));
		}
		if (!duration.isNegative()) {
			if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Ahead TRIGGERs are not supported, start instant will be used"));
			minutes = 0;
		}
		return Event.Reminder.valueOf(minutes);
	}
	
	public EventRecurrence fromVEventRRule(RRule rr, org.joda.time.DateTimeZone etz, LogEntries log) {
		EventRecurrence rec = new EventRecurrence();
		
		Recur recur = rr.getRecur();
		String freq = recur.getFrequency();
		if (freq.equals(Recur.DAILY)) {
			WeekDayList dayList = recur.getDayList();
			if (!dayList.isEmpty()) {
				rec.setType(EventRecurrence.TYPE_DAILY_FERIALI);
			} else {
				rec.setType(EventRecurrence.TYPE_DAILY);
				int dfreq = (recur.getInterval() == -1) ? 1 : recur.getInterval();
				rec.setDailyFreq(dfreq);
			}
		} else if (freq.equals(Recur.WEEKLY)) {
			rec.setType(EventRecurrence.TYPE_WEEKLY);
			
			int wfreq = (recur.getInterval() == -1) ? 1 : recur.getInterval();
			rec.setWeeklyFreq(wfreq);
			rec.setWeeklyDay1(false);
			rec.setWeeklyDay2(false);
			rec.setWeeklyDay3(false);
			rec.setWeeklyDay4(false);
			rec.setWeeklyDay5(false);
			rec.setWeeklyDay6(false);
			rec.setWeeklyDay7(false);
			
			WeekDayList dayList = recur.getDayList();
			if (!dayList.isEmpty()) {
				for(Object o : dayList) {
					WeekDay weekday = (WeekDay)o;
					if (weekday.equals(WeekDay.MO)) rec.setWeeklyDay1(true);
					if (weekday.equals(WeekDay.TU)) rec.setWeeklyDay2(true);
					if (weekday.equals(WeekDay.WE)) rec.setWeeklyDay3(true);
					if (weekday.equals(WeekDay.TH)) rec.setWeeklyDay4(true);
					if (weekday.equals(WeekDay.FR)) rec.setWeeklyDay5(true);
					if (weekday.equals(WeekDay.SA)) rec.setWeeklyDay6(true);
					if (weekday.equals(WeekDay.SU)) rec.setWeeklyDay7(true);
				}
			}
		} else if (freq.equals(Recur.MONTHLY)) {
			rec.setType(EventRecurrence.TYPE_MONTHLY);
			
			int mfreq = recur.getInterval();
			rec.setMonthlyFreq(mfreq);
			
			NumberList monthDayList = recur.getMonthDayList();
			for(Object o : monthDayList) {
				rec.setMonthlyDay((Integer)o);
			}
			
			if (monthDayList.isEmpty()) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Invalid MONTHLY recurrence"));
				return null;
			}
			
		} else if (freq.equals(Recur.YEARLY)) {
			rec.setType(EventRecurrence.TYPE_YEARLY);
			
			NumberList monthList = recur.getMonthList();
			for(Object o : monthList) {
				rec.setYearlyFreq((Integer)o);
			}
			
			NumberList monthDayList = recur.getMonthDayList();
			for(Object o : monthDayList) {
				rec.setYearlyDay((Integer)o);
			}
			
			if (monthList.isEmpty() || monthDayList.isEmpty()) {
				if (log != null) log.add(new MessageLogEntry(LogEntry.Level.WARN, "Invalid YEARLY recurrence"));
				return null;
			}
			
		} else { // Frequency type not yet supported...skip RR!
			return null;
		}
		
		if (recur.getCount() != -1) {
			rec.setEndsMode(EventRecurrence.ENDS_MODE_REPEAT);
			rec.setRepeatTimes(recur.getCount());
		} else if (recur.getUntil() == null) {
			rec.setEndsMode(EventRecurrence.ENDS_MODE_NEVER);
		} else {
			org.joda.time.DateTime dt = new org.joda.time.DateTime(recur.getUntil(), etz);
			rec.setEndsMode(EventRecurrence.ENDS_MODE_UNTIL);
			rec.setUntilDate(dt.withTimeAtStartOfDay());
		}
		
		return rec;
	}
	
	public Set<LocalDate> fromVEventExDate(ExDate exDate, DateTimeZone eventTimezone, LogEntries log) {
		LinkedHashSet<LocalDate> dates = new LinkedHashSet<>();
		Iterator it = exDate.getDates().iterator();
		while (it.hasNext()) {
			Date date = (Date)it.next();
			if (date instanceof DateTime) {
				// Firstly the date-time is read using its own timezone; if omitted 
				// the event timezone will be used instead, and then the resulting
				// date-time will be moved to the event timezone (the desired one).
				// Finally a local date can be extracted!
				dates.add(ICal4jUtils.toJodaDateTime((DateTime)date, eventTimezone).withZone(eventTimezone).toLocalDate());
			} else {
				dates.add(ICal4jUtils.toJodaLocalDate(date, eventTimezone));
			}
		}
		return dates;
	}
	
	public String fromVEventOrganizer(Organizer org, LogEntries log) throws UnsupportedEncodingException, WTException {
		InternetAddress ia = null;
		// See http://www.kanzaki.com/docs/ical/organizer.html
		
		// Evaluates organizer details
		// Extract email and common name (CN)
		// Eg: CN=Henry Cabot:MAILTO:hcabot@host2.com -> drop ":MAILTO:"
		URI uri = org.getCalAddress();
		Cn cn = (Cn)org.getParameter(Parameter.CN);
		if (uri != null) {
			String address = uri.getSchemeSpecificPart();
			ia = InternetAddressUtils.toInternetAddress(address, (cn == null) ? address : cn.getValue());
		} else {
			throw new WTException("Organizer must be valid [{0}]", org.toString());
			//log.add(new MessageLogEntry(LogEntry.Level.WARN, "Organizer must have a valid address [{0}]", organizer.toString()));
		}
		
		return InternetAddressUtils.toFullAddress(ia);
	}
	
	public EventAttendee fromVEventAttendee(Attendee att, LogEntries log) throws Exception {
		EventAttendee attendee = new EventAttendee();
		// See http://www.kanzaki.com/docs/ical/attendee.html
		
		// Evaluates attendee details
		// Extract email and common name (CN)
		// Eg: CN=Henry Cabot:MAILTO:hcabot@host2.com -> drop ":MAILTO:"
		URI uri = att.getCalAddress();
		Cn cn = (Cn)att.getParameter(Parameter.CN);
		if (uri != null) {
			String address = uri.getSchemeSpecificPart();
			InternetAddress ia = InternetAddressUtils.toInternetAddress(address, (cn == null) ? address : cn.getValue());
			attendee.setRecipient(InternetAddressUtils.toFullAddress(ia));
		} else {
			throw new WTException("Attendee must be valid [{0}]", att.toString());
			//log.add(new MessageLogEntry(LogEntry.Level.WARN, "Attendee must have a valid address [{0}]", attendee.toString()));
		}
		
		// Evaluates cuType
		CuType cuType = (CuType)att.getParameter(Parameter.CUTYPE);
		attendee.setRecipientType(cuTypeToRecipientType(cuType));
		
		// Evaluates attendee role
		Role role = (Role)att.getParameter(Parameter.ROLE);
		attendee.setRecipientRole(roleToRecipientRole(role));
		
		// Evaluates attendee response status
		PartStat partstat = (PartStat)att.getParameter(Parameter.PARTSTAT);
		attendee.setResponseStatus(partStatToResponseStatus(partstat));
		
		attendee.setNotify(defaultAttendeeNotify);
		return attendee;
	}
	
	public EventAttendee.RecipientRole roleToRecipientRole(Role role) {
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
	
	public EventAttendee.RecipientType cuTypeToRecipientType(CuType cuType) {
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
	
	public EventAttendee.ResponseStatus partStatToResponseStatus(PartStat partStat) {
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
				} catch(Throwable t) {
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
