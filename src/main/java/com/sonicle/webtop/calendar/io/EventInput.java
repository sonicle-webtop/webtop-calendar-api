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
package com.sonicle.webtop.calendar.io;

import com.sonicle.commons.Check;
import com.sonicle.commons.LangUtils;
import com.sonicle.commons.flags.BitFlags;
import com.sonicle.webtop.calendar.model.EventAttendee;
import com.sonicle.webtop.calendar.model.EventEx;
import com.sonicle.webtop.calendar.model.EventInstanceId;
import com.sonicle.webtop.core.util.ICalendarUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Status;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

/**
 *
 * @author malbinola
 */
public class EventInput implements Comparable<EventInput> {
	private static final Pattern PATTERN_LINE_DTSTAMP = Pattern.compile("\nDTSTAMP:.*(\r)?\n");
	public final EventEx event;
	public final ICalendarUtils.RRInstanceInfo recurringInstanceInfo;
	public final PropertyList extraProps;
	public final DateTime sourceLastModified;
	public final Integer sourceSequence;
	public final VEvent sourceObject;
	
	public EventInput(EventEx event, ICalendarUtils.RRInstanceInfo recurringInstanceInfo, PropertyList extraProps, DateTime sourceLastModified, Integer sourceSequence, VEvent sourceObject) {
		this.event = Check.notNull(event, "event");
		this.recurringInstanceInfo = recurringInstanceInfo;
		this.extraProps = extraProps;
		this.sourceLastModified = sourceLastModified;
		this.sourceSequence = sourceSequence;
		this.sourceObject = sourceObject;
	}
	
	public boolean isSeriesMaster() {
		if (recurringInstanceInfo != null) return false;
		return event.hasRecurrence();
	}
	
	public boolean isSourceEventCancelled() {
		if (sourceObject == null) return false;
		return Status.VEVENT_CANCELLED.equals(sourceObject.getProperty(Status.STATUS));
	}
	
	public String buildUniqueKey() {
		if (recurringInstanceInfo != null) {
			return EventInstanceId.buildInstance(event.getPublicUid(), recurringInstanceInfo.instanceDate).toString();
		} else if (event.hasRecurrence()) {
			return EventInstanceId.buildMaster(event.getPublicUid()).toString();
		} else {
			return EventInstanceId.buildSingle(event.getPublicUid()).toString();
		}
	}
	
	public String buildUidGroupKey() {
		if (recurringInstanceInfo != null) {
			return buildUidGroupKey(EventInstanceId.buildInstance(recurringInstanceInfo.masterUid, recurringInstanceInfo.instanceDate).getInstance());
		} else {
			return buildUidGroupKey(null);
		}
	}
	
	public String computeDataHash() {
		if (sourceObject == null) return null;
		final String s = PATTERN_LINE_DTSTAMP.matcher(sourceObject.toString()).replaceFirst("\n");
		return DigestUtils.md5Hex(s);
	}
	
	public void mergeFieldsForInvitation(final EventEx target) {
		target.setRevisionSequence(sourceSequence);
		target.setAllDay(event.getAllDay());
		target.setStart(event.getStart());
		target.setEnd(event.getEnd());
		target.setTimezone(event.getTimezone());
		target.setTitle(event.getTitle());
		target.setLocation(event.getLocation());
		target.setDescription(event.getDescription());
	}
	
	public void mergeRecurrence(final EventEx target) {
		target.setRecurrence(event.getRecurrence());
	}
	
	public void mergeAttendees(final EventEx target) {
		final Map<String, String> existingIdsByAddress = new HashMap<>();
		for (EventAttendee existing : target.getAttendeesOrEmpty()) {
			final String address = existing.getRecipientAddress();
			if (address != null) existingIdsByAddress.put(address.toLowerCase(), existing.getAttendeeId());
		}
		
		final List<EventAttendee> merged = new ArrayList<>();
		for (EventAttendee incoming : event.getAttendeesOrEmpty()) {
			final String address = incoming.getRecipientAddress();
			if (address != null) incoming.setAttendeeId(existingIdsByAddress.get(address.toLowerCase()));
			merged.add(incoming);
		}
		
		target.setAttendees(merged);
	}
	
	@Override
	public int compareTo(EventInput o) {
		boolean thisHasRRIInfo = this.recurringInstanceInfo == null;
		boolean otherIsHasRRIInfo = o.recurringInstanceInfo == null;
		if (thisHasRRIInfo == otherIsHasRRIInfo) {
			return 0; // both null or both non-null -> considered equal
		}
		return thisHasRRIInfo ? -1 : 1; // null comes first
	}
	
	public static String buildUidGroupKey(final String serieInstance) {
		return LangUtils.coalesceStrings(serieInstance, EventInstanceId.NO_INSTANCE_DATE);
	}
}
