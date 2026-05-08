/* 
 * Copyright (C) 2014 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2014 Sonicle S.r.l.".
 */
package com.sonicle.webtop.calendar.model;

import com.sonicle.commons.Check;
import com.sonicle.commons.time.JodaTimeUtils;
import com.sonicle.webtop.core.util.ICal4jUtils;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import net.fortuna.ical4j.model.Recur;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

/**
 *
 * @author malbinola
 */
public class EventRecurrence {
	protected String rule;
	protected DateTime start;
	protected Set<LocalDate> excludedDates;
	
	public EventRecurrence(String rule, DateTime start, Set<LocalDate> excludedDates) {
		this(rule, start);
		this.excludedDates = excludedDates;
	}
	
	public EventRecurrence(String rule, DateTime start) {
		this.rule = Check.notNull(rule, "rule");
		this.start = Check.notNull(start, "start");
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = Check.notNull(rule, "rule");
	}

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = Check.notNull(start, "start");
	}

	public Set<LocalDate> getExcludedDates() {
		return excludedDates;
	}

	public void setExcludedDates(Set<LocalDate> excludedDates) {
		this.excludedDates = excludedDates;
	}
	
	public boolean hasExcludedDates() {
		return (this.excludedDates != null) && (!this.excludedDates.isEmpty());
	}
	
	public Set<LocalDate> getExcludedDatesOrEmpty() {
		return this.excludedDates != null ? excludedDates : new LinkedHashSet<>(0);
	}
	
	public void addExcludedDates(Set<LocalDate> excludedDates) {
		if (this.excludedDates == null) {
			this.excludedDates = new LinkedHashSet<>(excludedDates);
		} else {
			this.excludedDates.addAll(excludedDates);
		}
	}
	
	public Recur getRecurRule() {
		return ICal4jUtils.parseRRule(getRule());
	}
	
	public static DateTime toRecurUntilDate(final LocalDate instancesUntilDate, final LocalTime startTime, final DateTimeZone timezone) {
		if (instancesUntilDate == null || startTime == null || timezone == null) return null;
		return instancesUntilDate.toDateTime(startTime, timezone);
	}
	
	public static LocalTime getRecurUntilTime(final EventBounds bounds) {
		Check.notNull(bounds, "bounds");
		return bounds.isAllDay() ? JodaTimeUtils.TIME_AT_STARTOFDAY : bounds.getStart().withZone(bounds.getTimezoneObject()).toLocalTime();
	}
	
	public static LocalDate getRecurUntilDate(final Recur recur, final DateTimeZone timezone) {
		Check.notNull(recur, "recur");
		Check.notNull(timezone, "timezone");
		DateTime until = ICal4jUtils.getRecurUntilDate(recur);
		return (until != null) ? until.withZone(timezone).toLocalDate() : null;
	}
	
	public static Set<LocalDate> filterExDates(final Set<LocalDate> exDates, final LocalDate inclusiveFrom) {
		if (exDates == null) return null;
		return exDates.stream()
			.filter((date) -> (date.equals(inclusiveFrom) || date.isAfter(inclusiveFrom))
			)
			.collect(Collectors.toSet());
	}
	
	public static Set<LocalDate> traslateExDates(final Set<LocalDate> exDates, final int days) {
		if (exDates == null) return null;
		Set<LocalDate> set = new LinkedHashSet(exDates.size());
		for (LocalDate ld : exDates) {
			set.add(ld.plusDays(days));
		}
		return set;
	}
}
