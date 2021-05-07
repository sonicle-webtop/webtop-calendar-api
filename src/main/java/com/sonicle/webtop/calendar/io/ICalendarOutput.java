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

import com.sonicle.webtop.calendar.CalendarUtils;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttendee;
import com.sonicle.webtop.core.sdk.WTException;
import com.sonicle.webtop.core.util.ICal4jUtils;
import com.sonicle.webtop.core.util.ICalendarUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Date;
import net.fortuna.ical4j.model.DateList;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.CuType;
import net.fortuna.ical4j.model.parameter.PartStat;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.parameter.Value;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.ExDate;
import net.fortuna.ical4j.model.property.LastModified;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.RRule;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.Uid;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author malbinola
 */
public class ICalendarOutput {
	private final String prodId;
	
	public ICalendarOutput(String prodId) {
		this.prodId = prodId;
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
	
	public Calendar toCalendar(Event event) throws WTException {
		return toCalendar(null, Arrays.asList(event));
	}
	
	public Calendar toCalendar(Method method, Event event) throws WTException {
		return toCalendar(method, Arrays.asList(event));
	}
	
	public Calendar toCalendar(Collection<Event> events) throws WTException {
		return toCalendar(null, events);
	}
	
	public Calendar toCalendar(Method method, Collection<Event> events) throws WTException {
		Calendar ical = null;
		
		if (method == null) {
			ical = ICalendarUtils.newCalendar(prodId, null);
		} else {
			ensureMethodRequestOrCancel(method);
			ical = ICalendarUtils.newCalendar(prodId, method);
		}
		for (Event event : events) {
			ical.getComponents().add(toVEvent(method, event));
		}
		return ical;
	}
	
	public VEvent toVEvent(Method method, Event event) throws WTException {
		boolean useUTCDateTimes = (method != null);
		Date start, end;
		
		CalendarUtils.EventBoundary eventBoundary = CalendarUtils.getEventBoundary(event);
		if (eventBoundary.allDay) {
			// If event is AD do not include time in start/end, the output will be a date only field (without time)
			start = ICal4jUtils.toIC4jDate(eventBoundary.start.withZone(eventBoundary.timezone).toLocalDate());
			end = ICal4jUtils.toIC4jDate(eventBoundary.end.withZone(eventBoundary.timezone).toLocalDate());
		} else {
			if (useUTCDateTimes) {
				// Meeting requests should use UTC date/time values instead of notation with local timezones
				start = ICal4jUtils.toIC4jDateTimeUTC(eventBoundary.start);
				end = ICal4jUtils.toIC4jDateTimeUTC(eventBoundary.end);
			} else {
				start = ICal4jUtils.toIC4jDateTime(eventBoundary.start, eventBoundary.timezone, true);
				end = ICal4jUtils.toIC4jDateTime(eventBoundary.end, eventBoundary.timezone, true);
			}
		}
			
		VEvent ve = new VEvent(start, end, event.getTitle());
		
		// Status: meeting status
		if (Method.REQUEST.equals(method)) {
			ICal4jUtils.addProperty(ve, Status.VEVENT_CONFIRMED);
		} else if (Method.CANCEL.equals(method)) {
			ICal4jUtils.addProperty(ve, Status.VEVENT_CANCELLED);
		}
		
		// Uid: globally unique identifier
		// http://www.kanzaki.com/docs/ical/uid.html
		ICal4jUtils.addProperty(ve, new Uid(event.getPublicUid()));
		
		// LastModified: the date and time of the last information revision
		// http://www.kanzaki.com/docs/ical/lastModified.html
		ICal4jUtils.addProperty(ve, new LastModified(ICal4jUtils.createDateTime(event.getRevisionTimestamp().withZone(DateTimeZone.UTC))));
		
		// Sequence: the revision sequence number
		// http://www.kanzaki.com/docs/ical/sequence.html
		ICal4jUtils.addProperty(ve, new Sequence(0));
		
		// Description: the complete description
		// http://www.kanzaki.com/docs/ical/description.html
		ICal4jUtils.addProperty(ve, new Description(event.getDescription()));
		
		// Location: the intended venue for the activity
		// http://www.kanzaki.com/docs/ical/location.html
		if (!StringUtils.isEmpty(event.getLocation())) {
			ICal4jUtils.addProperty(ve, new Location(event.getLocation()));
		}
		
		// Classification: provides a method of capturing the scope of the access the calendar owner intends for information within an individual calendar entry
		// https://www.kanzaki.com/docs/ical/class.html
		// https://appgenix.uservoice.com/forums/280499-business-calendar-2/suggestions/18698599-i-would-like-to-see-another-privacy-option-confid
		ICal4jUtils.addProperty(ve, event.getIsPrivate() ? Clazz.CONFIDENTIAL : Clazz.PUBLIC);
		
		// Transparency: defines whether an event is transparent or not to busy time searches
		// http://www.kanzaki.com/docs/ical/transp.html
		ICal4jUtils.addProperty(ve, event.getBusy() ? Transp.OPAQUE : Transp.TRANSPARENT);
		
		// Organizer
		String mailto = "mailto:" + event.getOrganizerAddress();
		Organizer organizer = new Organizer(URI.create(mailto));
		if (!StringUtils.isBlank(event.getOrganizerCN())) {
			organizer.getParameters().add(new Cn(event.getOrganizerCN()));
		}
		ve.getProperties().add(organizer);
		
		// Alarm
		if (event.getReminder() != null) {
			ve.getAlarms().add(toVAlarm(event.getReminder(), event.getTitle()));
		}
		
		// Attendees
		for (EventAttendee attendee : event.getAttendees()) {
			try {
				if(attendee.hasEmailRecipient()) {
					ve.getProperties().add(toAttendee(method, attendee));
				}
			} catch(AddressException ex) {
				/* Do nothing...*/
			}
		}
		
		// Recerrence
		if (event.hasRecurrence()) {
			try {
				ve.getProperties().add(toRRule(event));
			} catch(ParseException ex) {
				throw new WTException(ex, "Unable to add recurrence");
			}
			if (event.hasExcludedDates()) {
				LocalTime startTime = event.getAllDay() ? null : event.getStartDate().withZone(event.getDateTimeZone()).toLocalTime();
				ve.getProperties().add(toExDate(useUTCDateTimes, event.getExcludedDates(), event.getDateTimeZone(), startTime));
			}
		}
		
		return ve;
	}
	
	public VAlarm toVAlarm(Event.Reminder reminder, String description) {
		// We only support one time reminders (REPEAT=1)
		VAlarm alarm = new VAlarm(new Dur(0, 0, -reminder.getValue(), 0));
		alarm.getProperties().add(Action.DISPLAY);
		alarm.getProperties().add(new Description(description));
		return alarm;
	}
	
	public Attendee toAttendee(Method calMethod, EventAttendee attendee) throws AddressException {
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
	
	public RRule toRRule(Event event) throws ParseException {
		return new RRule(event.getRecurrenceRule());
	}
	
	public ExDate toExDate(boolean useUTCDateTimes, Set<LocalDate> excludedDates, DateTimeZone eventTimezone, LocalTime eventStartTime) {
		DateList dateList = null;
		if (eventStartTime != null) {
			dateList = new DateList(Value.DATE_TIME);
			// Sets timezone info only if we have time defined, AD events must carry dates only!
			if (useUTCDateTimes) {
				dateList.setUtc(true);
			} else {
				dateList.setTimeZone(ICal4jUtils.toIC4jTimezone(eventTimezone));
			}
		} else {
			dateList = new DateList(Value.DATE);
		}
		for (LocalDate exclDate : excludedDates) {
			if (Value.DATE_TIME.equals(dateList.getType())) {
				DateTime dt = exclDate.toDateTime(eventStartTime, eventTimezone);
				if (useUTCDateTimes) {
					dateList.add(ICal4jUtils.toIC4jDateTimeUTC(dt));
				} else {
					// Skip forcing the zone reference, it's already set above on the collection!
					dateList.add(ICal4jUtils.toIC4jDateTime(dt, eventTimezone, false));
				}
			} else {
				dateList.add(ICal4jUtils.toIC4jDate(exclDate));
			}
		}
		return new ExDate(dateList);
	}
	
	public Role toRole(EventAttendee.RecipientRole recipientRole) {
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
	
	public CuType toCuType(EventAttendee.RecipientType recipientType) {
		if (EventAttendee.RecipientType.INDIVIDUAL.equals(recipientType)) {
			return CuType.INDIVIDUAL;
		} else if (EventAttendee.RecipientType.RESOURCE.equals(recipientType)) {
			return CuType.RESOURCE;
		} else {
			return CuType.INDIVIDUAL;
		}
	}
	
	public PartStat toPartStat(EventAttendee.ResponseStatus responseStatus) {
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
	
	private void ensureMethodRequestOrCancel(Method method) throws WTException {
		if (!Method.REQUEST.equals(method) && !Method.CANCEL.equals(method)) {
			throw new WTException("Invalid method: only REQUEST or CANCEL are supported");
		}
	}
}
