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

import org.joda.time.DateTime;

/**
 *
 * @author malbinola
 */
public class EventFootprint extends EventFootprintBase {
	protected final String publicUid;
	protected final Integer calendarId;
	protected final DateTime revisionTimestamp;
	protected final DateTime creationTimestamp;
	protected final String title;
	protected final String location;
	
	public EventFootprint(String eventId, String publicUid, Integer calendarId, 
			DateTime revisionTimestamp, DateTime creationTimestamp, DateTime startDate, 
			DateTime endDate, String timezone, Boolean allDay, String rrule, String title, String location) {
		
		super(eventId, startDate, endDate, timezone, allDay, rrule);
		this.publicUid = publicUid;
		this.calendarId = calendarId;
		this.revisionTimestamp = revisionTimestamp;
		this.creationTimestamp = creationTimestamp;
		this.title = title;
		this.location = location;
	}
	
	public EventFootprint(Event event) {
		super(event);
		this.publicUid = event.getPublicUid();
		this.calendarId = event.getCalendarId();
		this.revisionTimestamp = event.getRevisionTimestamp();
		this.creationTimestamp = event.getCreationTimestamp();
		this.title = event.getTitle();
		this.location = event.getLocation();
	}

	public String getPublicUid() {
		return publicUid;
	}

	public Integer getCalendarId() {
		return calendarId;
	}

	public DateTime getRevisionTimestamp() {
		return revisionTimestamp;
	}
	
	public DateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public String getTitle() {
		return title;
	}

	public String getLocation() {
		return location;
	}
}
