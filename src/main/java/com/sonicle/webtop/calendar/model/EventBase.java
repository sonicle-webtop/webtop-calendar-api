/*
 * Copyright (C) 2026 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2026 Sonicle S.r.l.".
 */
package com.sonicle.webtop.calendar.model;

import com.google.gson.annotations.SerializedName;
import com.sonicle.commons.InternetAddressUtils;
import com.sonicle.commons.time.DateTimeWindow;
import com.sonicle.webtop.calendar.CalendarUtils;
import jakarta.mail.internet.InternetAddress;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author malbinola
 */
public class EventBase {
	protected Integer calendarId;
	protected String publicUid;
	protected RevisionStatus revisionStatus;
	protected DateTime revisionTimestamp;
	protected Integer revisionSequence;
	protected DateTime creationTimestamp;
	protected RowStatus rowStatus;
	protected Status status;
	protected String organizer;
	protected String organizerId;
	protected String timezone;
	protected Boolean allDay;
	protected DateTime start;
	protected DateTime end;
	protected String title;
	protected String location;
	protected BodyType descriptionType;
	protected String description;
	protected Visibility visibility;
	protected Transparency transparency;
	protected String href;
	protected String etag;
	protected Reminder reminder;
	protected boolean censorized = false;
	
	public Integer getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(Integer calendarId) {
		this.calendarId = calendarId;
	}
	
	public String getPublicUid() {
		return publicUid;
	}

	public void setPublicUid(String publicUid) {
		this.publicUid = publicUid;
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
	
	public Integer getRevisionSequence() {
		return revisionSequence;
	}

	public void setRevisionSequence(Integer revisionSequence) {
		this.revisionSequence = revisionSequence;
	}

	public DateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(DateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}
	
	public RowStatus getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(RowStatus rowStatus) {
		this.rowStatus = rowStatus;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	
	public String getOrganizerId() {
		return organizerId;
	}

	public void setOrganizerId(String organizerId) {
		this.organizerId = organizerId;
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

	public DateTime getStart() {
		return start;
	}

	public void setStart(DateTime start) {
		this.start = start;
	}

	public DateTime getEnd() {
		return end;
	}

	public void setEnd(DateTime end) {
		this.end = end;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public BodyType getDescriptionType() {
		return descriptionType;
	}

	public void setDescriptionType(BodyType descriptionType) {
		this.descriptionType = descriptionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Transparency getTransparency() {
		return transparency;
	}

	public void setTransparency(Transparency transparency) {
		this.transparency = transparency;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getEtag() {
		return etag;
	}

	public void setEtag(String etag) {
		this.etag = etag;
	}

	public Reminder getReminder() {
		return reminder;
	}

	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
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
	
	public DateTimeZone getTimezoneObject() {
		return DateTimeZone.forID(getTimezone());
	}
	
	public boolean isVisibilityPrivate() {
		return Visibility.PRIVATE.equals(getVisibility());
	}
	
	public boolean isTransparencyOpaque() {
		return Transparency.OPAQUE.equals(getTransparency());
	}
	
	public void setDatesAndTimes(boolean allDay, String timezone, DateTime start, DateTime end) {
		this.allDay = allDay;
		this.timezone = timezone;
		this.start = start;
		this.end = end;
		ensureCoherence();
	}
	
	public void ensureCoherence() {
		if ((start != null) && (end != null)) {
			// Ensure start < end
			if (start.compareTo(end) > 0) {
				final DateTime dt = end;
				setEnd(start);
				setStart(dt);
			}
		}
	}
	
	public boolean isCensorized() {
		return this.censorized;
	}
	
	public void censorize() {
		this.setOrganizer(null);
		this.setOrganizerId(null);
		this.setTitle("(***)");
		this.setLocation(null);
		this.setDescription(null);
		this.setReminder(null);
		this.censorized = true;	
	}
	
	public boolean trimFieldLengths() {
		MutableBoolean trimmed = new MutableBoolean(false);
		setTitle(trimStringLength(getTitle(), 255, trimmed));
		setLocation(trimStringLength(getLocation(), 255, trimmed));
		setOrganizer(trimStringLength(getOrganizer(), 650, trimmed));
		return trimmed.booleanValue();
	}
	
	public void prependToTitle(String prefix) {
		String subj = getTitle();
		if (!StringUtils.isBlank(prefix) && !StringUtils.isBlank(subj)) {
			setTitle(prefix + " " + subj);	
		}
	}
	
	public EventBounds getEventBounds() {
		return new EventBoundsImpl(this.allDay, this.start, this.end, getTimezoneObject());
	}
	
	public void recalculateStartEndForInstance(LocalDate instanceDate) {
		DateTimeZone thisTimezone = getTimezoneObject();
		DateTimeWindow window = CalendarUtils.computeStartEndForEventInstance(
			instanceDate,
			getStart().withZone(thisTimezone).toLocalDateTime(),
			getEnd().withZone(thisTimezone).toLocalDateTime(),
			thisTimezone);
		setStart(window.getStart());
		setEnd(window.getEnd());
	}
	
	private static String trimStringLength(String value, int maxLength, MutableBoolean trimmed) {
		if (StringUtils.length(value) > maxLength) {
			trimmed.setTrue();
			return StringUtils.left(value, maxLength);
		} else {
			return value;
		}
	}
	
	public static enum RevisionStatus {
		@SerializedName("N") NEW,
		@SerializedName("M") MODIFIED,
		@SerializedName("D") DELETED;
	}
	
	public static enum RowStatus {
		@SerializedName("DF") DEFAULT,
		@SerializedName("RO") READ_ONLY;
	}
	
	public static enum Status {
		@SerializedName("CF") CONFIRMED;
		//@SerializedName("DR") DRAFT //TODO: not yet supported
	}
	
	public static enum BodyType {
		@SerializedName("text") TEXT,
		@SerializedName("html") HTML
	}
	
	public static enum Visibility {
		@SerializedName("PU") PUBLIC,
		@SerializedName("PV") PRIVATE
	}
	
	public static enum Transparency {
		@SerializedName("TP") TRANSPARENT,
		@SerializedName("OP") OPAQUE
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
