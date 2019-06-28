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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author malbinola
 */
public class Event extends BaseEvent {
	protected String href;
	protected String etag;
	protected Integer activityId;
	protected String masterDataId;
	protected String statMasterDataId;
	protected Integer causalId;
	protected String recurrenceRule;
	protected LocalDate recurrenceStartDate;
	protected Set<LocalDate> excludedDates;
	protected List<EventAttendee> attendees = new ArrayList<>();
	protected List<EventAttachment> attachments = new ArrayList<>();
	
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

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getMasterDataId() {
		return masterDataId;
	}

	public void setMasterDataId(String masterDataId) {
		this.masterDataId = masterDataId;
	}

	public String getStatMasterDataId() {
		return statMasterDataId;
	}

	public void setStatMasterDataId(String statMasterDataId) {
		this.statMasterDataId = statMasterDataId;
	}

	public Integer getCausalId() {
		return causalId;
	}

	public void setCausalId(Integer causalId) {
		this.causalId = causalId;
	}

	public String getRecurrenceRule() {
		return recurrenceRule;
	}
	
	public LocalDate getRecurrenceStartDate() {
		return recurrenceStartDate;
	}
	
	public void setRecurrenceStartDate(LocalDate recurrenceStartDate) {
		this.recurrenceStartDate = recurrenceStartDate;
	}
	
	public Set<LocalDate> getExcludedDates() {
		return excludedDates;
	}
	
	public void setExcludedDates(Set<LocalDate> excludedDates) {
		this.excludedDates = excludedDates;
	}

	public List<EventAttendee> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<EventAttendee> attendees) {
		this.attendees = attendees;
	}
	
	public List<EventAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<EventAttachment> attachments) {
		this.attachments = attachments;
	}
	
	public void setDatesAndTimes(boolean allDay, String timezone, DateTime startDate, DateTime endDate) {
		this.allDay = allDay;
		this.timezone = timezone;
		this.startDate = startDate;
		this.endDate = endDate;
		ensureCoherence();
	}
	
	public void setRecurrence(String rule, LocalDate startDate, Set<LocalDate> excludedDates) {
		this.recurrenceRule = rule;
		this.recurrenceStartDate = startDate;
		this.excludedDates = excludedDates;
	}
	
	public boolean hasRecurrence() {
		return !StringUtils.isEmpty(recurrenceRule);
	}
	
	public boolean hasExcludedDates() {
		return (excludedDates != null) && !excludedDates.isEmpty();
	}
	
	public boolean trimFieldLengths() {
		MutableBoolean trimmed = new MutableBoolean(false);
		setTitle(trimStringLength(getTitle(), 255, trimmed));
		setLocation(trimStringLength(getLocation(), 255, trimmed));
		setOrganizer(trimStringLength(getOrganizer(), 650, trimmed));
		return trimmed.booleanValue();
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append(getEventId())
				.append(getTitle())
				.toString();
	}
	
	public EventFootprint getFootprint() {
		return new EventFootprint(this);
	}
	
	private static String trimStringLength(String value, int maxLength, MutableBoolean trimmed) {
		if (StringUtils.length(value) > maxLength) {
			trimmed.setTrue();
			return StringUtils.left(value, maxLength);
		} else {
			return value;
		}
	}
}
