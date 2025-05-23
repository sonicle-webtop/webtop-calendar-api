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
package com.sonicle.webtop.calendar.model;

import com.google.gson.annotations.SerializedName;
import com.sonicle.commons.InternetAddressUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import jakarta.mail.internet.InternetAddress;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class BaseEvent {
	protected String eventId;
	protected String publicUid;
	protected Integer calendarId;
	protected RevisionStatus revisionStatus;
	protected DateTime revisionTimestamp;
	protected DateTime creationTimestamp;
	protected Boolean readOnly;
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
	protected Reminder reminder;
	private boolean censorized = false;

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getPublicUid() {
		return publicUid;
	}

	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	
	public RevisionStatus getRevisionStatus() {
		return revisionStatus;
	}

	public void setRevisionStatus(RevisionStatus revisionStatus) {
		this.revisionStatus = revisionStatus;
	}

	public DateTime getRevisionTimestamp() {
		return revisionTimestamp;
	}

	public void setRevisionTimestamp(DateTime revisionTimestamp) {
		this.revisionTimestamp = revisionTimestamp;
	}
	
	public DateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(DateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Boolean getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
	}

	public DateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Boolean getAllDay() {
		return allDay;
	}

	public void setAllDay(Boolean allDay) {
		this.allDay = allDay;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Boolean getBusy() {
		return busy;
	}

	public void setBusy(Boolean busy) {
		this.busy = busy;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}
	
	public boolean isCensorized() {
		return censorized;
	}
	
	public DateTimeZone getDateTimeZone() {
		return DateTimeZone.forID(getTimezone());
	}
	
	public InternetAddress getOrganizerInternetAddress() {
		return InternetAddressUtils.toInternetAddress(getOrganizer());
	}
	
	public String getOrganizerAddress() {
		InternetAddress ia = getOrganizerInternetAddress();
		return (ia != null) ? ia.getAddress() : null;
	}
	
	public String getOrganizerCN() {
		InternetAddress ia = getOrganizerInternetAddress();
		return (ia != null) ? ia.getPersonal() : null;
	}
	
	public void ensureCoherence() {
		if ((startDate != null) && (endDate != null)) {
			// Ensure start < end
			if (startDate.compareTo(endDate) > 0) {
				final DateTime dt = endDate;
				setEndDate(startDate);
				setStartDate(dt);
			}
		}
	}
	
	public void censorize() {
		this.title = "(***)";
		this.location = null;
		this.description = null;
		this.reminder = null;
		this.censorized = true;
	}
	
	public static enum RevisionStatus {
		@SerializedName("N") NEW,
		@SerializedName("M") MODIFIED,
		@SerializedName("D") DELETED;
	}
	
	public static enum RecurInfo {
		@SerializedName("none") NONE,
		@SerializedName("broken") BROKEN,
		@SerializedName("recurring") RECURRING;
	}
	
	public static class Reminder {
		private static final Integer[] VALUES = new Integer[]{0,5,10,15,30,45,60,120,180,240,300,360,420,480,540,600,660,720,1080,1440,2880,10080,20160,43200};
		private static final Set<Integer> VALID_VALUES = new HashSet<>(Arrays.asList(VALUES));
		private final int minutes;
		
		public Reminder(int minutes) {
			if (minutes < 0) {
				this.minutes = VALUES[0];
			} else {
				this.minutes = findNearestMinutesValue(minutes);
			}
		}
		
		public int getMinutesValue() {
			return minutes;
		}
		
		public static int findNearestMinutesValue(int minutes) {
			if (VALID_VALUES.contains(minutes)) {
				return minutes;
			} else {
				for (int i=1; i < VALUES.length; i++) {
					if (minutes < VALUES[i]) return VALUES[i];
				}
				return VALUES[VALUES.length-1];
			}
		}
		
		public static Reminder valueOf(Integer minutes) {
			return (minutes == null) ? null : new Reminder(minutes);
		}
		
		public static Integer getMinutesValue(Reminder reminder) {
			return (reminder == null) ? null : reminder.getMinutesValue();
		}
	}
}
