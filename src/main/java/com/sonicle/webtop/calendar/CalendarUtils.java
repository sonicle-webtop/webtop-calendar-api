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
package com.sonicle.webtop.calendar;

import com.sonicle.commons.time.DateTimeWindow;
import com.sonicle.commons.time.JodaTimeUtils;
import com.sonicle.webtop.calendar.model.EventBase;
import com.sonicle.webtop.calendar.model.EventBoundsImpl;
import com.sonicle.webtop.calendar.model.EventInstanceId;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.IllegalInstantException;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import com.sonicle.webtop.calendar.model.EventBounds;

/**
 *
 * @author malbinola
 */
public class CalendarUtils {
	
	public static DateTimeZone toDateTimeZone(String timezoneId) {
		return StringUtils.isBlank(timezoneId) ? null : DateTimeZone.forID(timezoneId);
	}
	
	public static DateTimeWindow computeStartEndForEventInstance(final LocalDate instanceDate, final LocalDateTime mockEventStart, final LocalDateTime mockEventEnd, final DateTimeZone mockEventTimezone) {
		final int spanDays = JodaTimeUtils.daysBetween(mockEventStart, mockEventEnd, true);
		
		DateTime start = null;
		DateTime end = null;
		
		try {
			start = JodaTimeUtils.toDateTime(instanceDate, mockEventStart.toLocalTime(), mockEventTimezone, false);
			end = JodaTimeUtils.toDateTime(instanceDate.plusDays(spanDays), mockEventEnd.toLocalTime(), mockEventTimezone, false);
			
		} catch (IllegalInstantException ex) {
			if (start != null) { // end falls in the gap:
				if (spanDays == 0) { // ...move both forward if spanDays = 0: use new end as start and tweak end accordingly
					final DateTime pushedEnd = JodaTimeUtils.toDateTime(instanceDate.plusDays(spanDays), mockEventEnd.toLocalTime(), mockEventTimezone, true);
					start = JodaTimeUtils.toDateTime(instanceDate, pushedEnd.toLocalTime(), mockEventTimezone, false);
					final int spanMins = JodaTimeUtils.minutesBetween(mockEventStart.toLocalTime(), mockEventEnd.toLocalTime(), true);
					end = JodaTimeUtils.toDateTime(instanceDate, start.toLocalTime().plusMinutes(spanMins), mockEventTimezone, false);
					
				} else { // ...move end forward
					end = JodaTimeUtils.toDateTime(instanceDate.plusDays(spanDays), mockEventEnd.toLocalTime(), mockEventTimezone, true);
				}
				
			} else { // start falls in the gap: move start forward
				start = JodaTimeUtils.toDateTime(instanceDate, mockEventStart.toLocalTime(), mockEventTimezone, true);
				if (spanDays == 0) { // ...move forward also end if spanDays = 0
					final int spanMins = JodaTimeUtils.minutesBetween(mockEventStart.toLocalTime(), mockEventEnd.toLocalTime(), true);
					end = JodaTimeUtils.toDateTime(instanceDate, start.toLocalTime().plusMinutes(spanMins), mockEventTimezone, false);
				} else {
					end = JodaTimeUtils.toDateTime(instanceDate.plusDays(spanDays), mockEventEnd.toLocalTime(), mockEventTimezone, false);
				}
			}
		}
		
		return DateTimeWindow.builder().withStart(start).withEnd(end).build();
	}
	
	/**
	 * Calculates the number of calendar days an event should span when displayed in the UI.
	 * For all-day events represented using the standard convention
	 * (start at 00:00:00 and end at 00:00:00 of the following day),
	 * the end boundary is exclusive, therefore one day must be subtracted
	 * from the raw calendar-day difference.
	 * For legacy representations (for example 00:00:00 -> 23:59:59 or
	 * 09:00:00 -> 18:00:00 generated by older implementations), the raw
	 * calendar-day difference is used as-is.
	 * @param start Event start date.
	 * @param end Event end date.
	 * @param timezone The reference timezone.
	 * @return the number of days occupied by the event for display purposes, never negative
	 */
	public static int calculateDaysSpanForDisplay(DateTime start, DateTime end, DateTimeZone timezone) {
		int daysSpan;
		if (JodaTimeUtils.isMidnight(start) && JodaTimeUtils.isMidnight(end)) {
			// world convention: 00:00:00 -> 00:00:00+1
			daysSpan = JodaTimeUtils.calendarDaysBetween(start, end) -1;
		} else {
			// Here we have both 2 situations caused by old implementations:
			//  - 00:00:00 -> 23:59:59
			//  - 09:00:00 -> 18:00:00 (zpush backend impl. hard-coded default)
			daysSpan = JodaTimeUtils.calendarDaysBetween(start, end);
		}
		return Math.max(0, daysSpan);
	}
	
	public static Set<LocalDate> getDisplayDatesSpan(boolean allDay, DateTime start, DateTime end, DateTimeZone timezone) {
		HashSet<LocalDate> dates = new HashSet<>();
		int daySpan = calculateDaysSpanForDisplay(start, end, timezone) +1;
		LocalDate date = start.withZone(timezone).toLocalDate();
		for (int count = 1; count <= daySpan; count++) {
			dates.add(date);
			date = date.plusDays(1);
		}
		return dates;
	}
	
	public static EventBounds toEventBounds(EventBase event) {
		return toEventBounds(event.getAllDay(), event.getStart(), event.getEnd(), event.getTimezoneObject());
	}
	
	public static EventBounds toEventBounds(boolean allDay, DateTime start, DateTime end, DateTimeZone timezone) {
		if (allDay) {
			int daySpan = calculateDaysSpanForDisplay(start, end, timezone);
			LocalDate startLocalDate = start.withZone(timezone).toLocalDate();
			return new EventBoundsImpl(
				allDay,
				startLocalDate.toDateTimeAtStartOfDay(timezone),
				startLocalDate.plusDays(daySpan+1).toDateTimeAtStartOfDay(timezone),
				timezone
			);
		} else {
			return new EventBoundsImpl(
				allDay,
				start.withZone(timezone),
				end.withZone(timezone),
				timezone
			);
		}
	}
	
	/**
	 * Converts event boundaries from the persistence/backend representation
	 * to the format expected by the UI when reading event data.
	 * For all-day events, different historical representations must be
	 * normalized before being sent to the UI:
	 *  - Standard all-day format: 00:00:00 -> 00:00:00 of the next day
	 *    (exclusive end boundary).
	 *  - Legacy formats such as 00:00:00 -> 23:59:59 or other non-midnight
	 *    boundaries generated by older implementations.
	 * The returned bounds are adjusted so that the UI always receives
	 * a consistent day-based representation.
	 * @param allDay Whether the event is an all-day event.
	 * @param start Event start date.
	 * @param end Event end date.
	 * @param timezone The reference timezone.
	 * @return normalized event bound for UI consumption
	 */
	public static EventBounds toEventBoundsForUIRead(boolean allDay, DateTime start, DateTime end, DateTimeZone timezone) {
		if (allDay) {
			if (JodaTimeUtils.isMidnight(start) && JodaTimeUtils.isMidnight(end)) {
				int daySpan = calculateDaysSpanForDisplay(start, end, timezone);
				LocalDate startLocalDate = start.withZone(timezone).toLocalDate();
				return new EventBoundsImpl(
					allDay,
					startLocalDate.toDateTimeAtStartOfDay(timezone),
					startLocalDate.plusDays(daySpan).toDateTimeAtStartOfDay(timezone),
					timezone
				);
			} else {
				return new EventBoundsImpl(
					allDay,
					start.withZone(timezone).withTimeAtStartOfDay(),
					end.withZone(timezone).withTimeAtStartOfDay(),
					timezone
				);
			}
		} else {
			return new EventBoundsImpl(
				allDay,
				start.withZone(timezone),
				end.withZone(timezone),
				timezone
			);
		}
	}
	
	/**
	 * Converts event boundaries from the UI representation to the backend
	 * persistence format.
	 * For all-day events, the UI provides inclusive start/end dates,
	 * while the backend stores all-day ranges using an exclusive end
	 * boundary. Therefore the end date is shifted by one day and both
	 * boundaries are normalized to midnight in the target timezone.
	 * Timed events are simply converted to the requested timezone
	 * without changing their duration.
	 * @param allDay Whether the event is an all-day event.
	 * @param start Event start date.
	 * @param end Event end date.
	 * @param timezone The reference timezone.
	 * @return event bounds ready for backend persistence
	 */
	public static EventBounds toEventBoundsForUIWrite(boolean allDay, DateTime start, DateTime end, DateTimeZone timezone) {
		if (allDay) {
			return new EventBoundsImpl(
				allDay,
				start.toLocalDate().toDateTimeAtStartOfDay(timezone),
				end.toLocalDate().plusDays(1).toDateTimeAtStartOfDay(timezone),
				timezone
			);
		} else {
			return new EventBoundsImpl(
				allDay,
				start.withZone(timezone),
				end.withZone(timezone),
				timezone
			);
		}
	}
}
