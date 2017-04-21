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

import com.sonicle.commons.MailUtils;
import com.sonicle.webtop.calendar.model.EventRecurrence;
import com.sonicle.webtop.calendar.model.EventAttendee;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.joda.time.DateTime;

/**
 *
 * @author malbinola
 */
public class Event {
	protected Integer eventId;
	protected String publicUid;
	protected Integer calendarId;
	protected DateTime revisionTimestamp;
	protected DateTime startDate;
	protected DateTime endDate;
	protected String timezone;
	protected Boolean allDay;
	protected String organizer;
	protected String title;
	protected String description;
	protected String location;
	protected Boolean isPrivate;
	protected Boolean busy;
	protected Integer reminder;
	protected Integer activityId;
	protected String customerId;
	protected String statisticId;
	protected Integer causalId;
	protected EventRecurrence recurrence;
	protected List<EventAttendee> attendees = new ArrayList<>();
	
	public Event() {}
	
	public Integer getEventId() {
		return eventId;
	}
	
	public void setEventId(Integer value) {
		eventId = value;
	}
	
	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer value) {
		calendarId = value;
	}
	
	public DateTime getRevisionTimestamp() {
		return revisionTimestamp;
	}
	
	public void setRevisionTimestamp(DateTime value) {
		revisionTimestamp = value;
	}
	
	public String getPublicUid() {
		return publicUid;
	}
	
	public void setPublicUid(String value) {
		publicUid = value;
	}

	public DateTime getStartDate() {
		return startDate;
	}
	
	public void setStartDate(DateTime value) {
		startDate = value;
	}

	public DateTime getEndDate() {
		return endDate;
	}
	
	public void setEndDate(DateTime value) {
		endDate = value;
	}

	public String getTimezone() {
		return timezone;
	}
	
	public void setTimezone(String value) {
		timezone = value;
	}

	public Boolean getAllDay() {
		return allDay;
	}
	
	public void setAllDay(boolean value) {
		allDay = value;
	}
	
	public void setDatesAndTimes(boolean allDay, String timezone, DateTime startDate, DateTime endDate) {
		this.allDay = allDay;
		this.timezone = timezone;
		this.startDate = startDate;
		this.endDate = endDate;
		ensureCoherence(this);
	}
	
	public String getOrganizer() {
		return organizer;
	}
	
	public void setOrganizer(String value) {
		organizer = value;
	}
	
	public String getOrganizerAddress() {
		InternetAddress ia = MailUtils.buildInternetAddress(organizer);
		return (ia != null) ? ia.getAddress() : null;
	}
	
	public String getOrganizerCN() {
		InternetAddress ia = MailUtils.buildInternetAddress(organizer);
		return (ia != null) ? ia.getPersonal() : null;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String value) {
		title = value;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String value) {
		description = value;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String value) {
		location = value;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}
	
	public void setIsPrivate(boolean value) {
		isPrivate = value;
	}

	public Boolean getBusy() {
		return busy;
	}
	
	public void setBusy(boolean value) {
		busy = value;
	}

	public Integer getReminder() {
		return reminder;
	}
	
	public void setReminder(Integer value) {
		reminder = value;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStatisticId() {
		return statisticId;
	}

	public void setStatisticId(String statisticId) {
		this.statisticId = statisticId;
	}

	public Integer getCausalId() {
		return causalId;
	}

	public void setCausalId(Integer causalId) {
		this.causalId = causalId;
	}
	
	public EventRecurrence getRecurrence() {
		return recurrence;
	}
	
	public void setRecurrence(EventRecurrence value) {
		recurrence = value;
	}
	
	public boolean hasRecurrence() {
		return (recurrence != null);
	}
	
	public List<EventAttendee> getAttendees() {
		return attendees;
	}
	
	public void setAttendees(List<EventAttendee> value) {
		attendees = value;
	}
	
	public boolean hasAttendees() {
		return ((attendees != null) && !attendees.isEmpty());
	}
	
	public static void ensureCoherence(Event event) {
		// Ensure start < end
		if(event.getStartDate().compareTo(event.getEndDate()) > 0) {
			// Swap dates...
			DateTime dt = event.getEndDate();
			event.setEndDate(event.getStartDate());
			event.setStartDate(dt);
		}
	}
}
