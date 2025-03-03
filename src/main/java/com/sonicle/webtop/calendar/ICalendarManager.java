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
package com.sonicle.webtop.calendar;

import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.LangUtils;
import com.sonicle.commons.flags.BitFlags;
import com.sonicle.commons.flags.BitFlagsEnum;
import com.sonicle.commons.time.DateRange;
import com.sonicle.commons.time.DateTimeRange;
import com.sonicle.commons.time.DateTimeRange2;
import com.sonicle.webtop.calendar.model.GetEventScope;
import com.sonicle.webtop.calendar.model.Calendar;
import com.sonicle.webtop.calendar.model.CalendarBase;
import com.sonicle.webtop.calendar.model.CalendarFSFolder;
import com.sonicle.webtop.calendar.model.CalendarFSOrigin;
import com.sonicle.webtop.calendar.model.CalendarPropSet;
import com.sonicle.webtop.calendar.model.ComparableEventBounds;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttachmentWithBytes;
import com.sonicle.webtop.calendar.model.EventInstance;
import com.sonicle.webtop.calendar.model.EventKey;
import com.sonicle.webtop.calendar.model.EventObject;
import com.sonicle.webtop.calendar.model.EventQuery;
import com.sonicle.webtop.calendar.model.SchedEventInstance;
import com.sonicle.webtop.calendar.model.UpdateEventTarget;
import com.sonicle.webtop.calendar.model.UpdateTagsOperation;
import com.sonicle.webtop.core.app.sdk.WTConstraintException;
import com.sonicle.webtop.core.app.sdk.WTNotFoundException;
import com.sonicle.webtop.core.app.sdk.WTParseException;
import com.sonicle.webtop.core.model.CustomFieldValue;
import com.sonicle.webtop.core.app.model.FolderSharing;
import com.sonicle.webtop.core.sdk.UserProfileId;
import com.sonicle.webtop.core.sdk.WTException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public interface ICalendarManager {
	
	@Deprecated public Set<Integer> listCalendarIds() throws WTException;
	@Deprecated public Map<Integer, Calendar> listCalendars() throws WTException;
	@Deprecated public Event addEventFromICal(int calendarId, net.fortuna.ical4j.model.Calendar ical) throws WTException;
	@Deprecated public void updateEventFromICal(net.fortuna.ical4j.model.Calendar ical) throws WTException;
	@Deprecated public void updateEventInstance(UpdateEventTarget target, EventInstance event, boolean processAttachments, boolean notifyAttendees) throws WTException;
	public Set<FolderSharing.SubjectConfiguration> getFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope) throws WTException;
	public void updateFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope, final Set<FolderSharing.SubjectConfiguration> configurations) throws WTException;
	public Map<UserProfileId, CalendarFSOrigin> listIncomingCalendarOrigins() throws WTException;
	public CalendarFSOrigin getIncomingCalendarOriginByFolderId(final int calendarId) throws WTException;
	public Map<Integer, CalendarFSFolder> listIncomingCalendarFolders(final CalendarFSOrigin origin) throws WTException;
	public Map<Integer, CalendarFSFolder> listIncomingCalendarFolders(final UserProfileId originProfileId) throws WTException;
	public Set<Integer> listMyCalendarIds() throws WTException;
	public Set<Integer> listIncomingCalendarIds() throws WTException;
	public Set<Integer> listAllCalendarIds() throws WTException;
	public Map<Integer, Calendar> listMyCalendars() throws WTException;
	public Map<Integer, DateTime> getCalendarsItemsLastRevision(Collection<Integer> calendarIds) throws WTException;
	public UserProfileId getCalendarOwner(int calendarId) throws WTException;
	public Integer getDefaultCalendarId() throws WTException;
	public Integer getBuiltInCalendarId() throws WTException;
	public boolean existCalendar(int calendarId) throws WTException;
	public Calendar getCalendar(int calendarId) throws WTException;
	public Calendar getBuiltInCalendar() throws WTException;
	public Calendar addCalendar(final CalendarBase calendar) throws WTException;
	public Calendar addBuiltInCalendar() throws WTException;
	public Calendar addBuiltInCalendar(final String color) throws WTException;
	public void updateCalendar(final int calendarId, final CalendarBase calendar) throws WTNotFoundException, WTException;
	public void deleteCalendar(final int calendarId) throws WTNotFoundException, WTException;
	public CalendarPropSet getCalendarCustomProps(int calendarId) throws WTException;
	public Map<Integer, CalendarPropSet> getCalendarCustomProps(Collection<Integer> calendarIds) throws WTException;
	public CalendarPropSet updateCalendarCustomProps(int calendarId, CalendarPropSet propertySet) throws WTException;
	public List<ComparableEventBounds> listEventsBounds(final Collection<Integer> calendarIds, final DateTimeRange2 viewRange, final DateTimeZone targetTimezone) throws WTException;
	public List<EventObject> listEventObjects(int calendarId, EventObjectOutputType outputType) throws WTException;
	public List<EventObject> listEventObjects(int calendarId, DateTime since, EventObjectOutputType outputType) throws WTException;
	public List<EventObject> getEventObjects(final int calendarId, final Collection<String> hrefs, final EventObjectOutputType outputType) throws WTException;
	public EventObject getEventObject(int calendarId, String eventId, EventObjectOutputType outputType) throws WTException;
	public void addEventObject(int calendarId, String href, net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public void updateEventObject(int calendarId, String href, net.fortuna.ical4j.model.Calendar iCalendar) throws WTNotFoundException, WTException;
	public void deleteEventObject(int calendarId, String href) throws WTNotFoundException, WTException;
	public boolean existEventInstance(Collection<Integer> calendarIds, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, int days, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateRange range, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateRange range, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateTimeRange range, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateTimeRange range, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public Event getEvent(String eventId) throws WTException;
	public Event getEvent(GetEventScope scope, String publicUid) throws WTException;
	public EventAttachmentWithBytes getEventAttachment(String eventId, String attachmentId) throws WTNotFoundException, WTException;
	public Map<String, CustomFieldValue> getEventCustomValues(String eventId) throws WTException;
	public Event addEvent(Event event) throws WTException;
	public Event addEvent(Event event, boolean notifyAttendees) throws WTException;
	public Event handleInvitationFromICal(final net.fortuna.ical4j.model.Calendar ical, final Integer calendarId, final BitFlags<HandleICalInviationOption> options) throws WTParseException, WTNotFoundException, WTConstraintException, WTException;
	public String getEventInstanceKey(String eventId) throws WTException;
	public EventInstance getEventInstance(String eventKey) throws WTException;
	public void updateEventInstance(UpdateEventTarget target, EventInstance event, boolean processAttachments, boolean processTags, boolean processCustomValues, boolean notifyAttendees) throws WTException;
	public void updateEventInstance(UpdateEventTarget target, EventKey key, DateTime newStart, DateTime newEnd, String newTitle, boolean notifyAttendees) throws WTException;
	public void deleteEventInstance(UpdateEventTarget target, String eventKey, boolean notifyAttendees) throws WTException;
	public void deleteEventInstance(UpdateEventTarget target, EventKey key, boolean notifyAttendees) throws WTException;
	public void restoreEventInstance(EventKey key) throws WTException;
	public void moveEventInstance(EventKey key, int targetCalendarId) throws WTNotFoundException, WTException;
	public Event cloneEventInstance(EventKey key, Integer newCalendarId, DateTime newStart, DateTime newEnd, boolean notifyAttendees) throws WTException;
	public void updateEventCalendarTags(UpdateTagsOperation operation, int calendarId, Set<String> tagIds) throws WTException;
	public void updateEventTags(UpdateTagsOperation operation, Collection<String> eventIds, Set<String> tagIds) throws WTException;
	public void deleteEvent(String publicUid, Integer calendarId, boolean notifyAttendees) throws WTException;
	
	public static enum EventGetOption implements BitFlagsEnum<EventGetOption> {
		ATTACHMENTS(1<<0), TAGS(1<<1), CUSTOM_VALUES(1<<2);
		
		private int mask = 0;
		private EventGetOption(int mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
	
	public static enum EventUpdateOption implements BitFlagsEnum<EventUpdateOption> {
		ATTACHMENTS(1<<0), TAGS(1<<1), CUSTOM_VALUES(1<<2);
		
		private int mask = 0;
		private EventUpdateOption(int mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
	
	public static enum HandleICalInviationOption implements BitFlagsEnum<HandleICalInviationOption> {
		CONSTRAIN_AVAILABILITY(1<<1), EVENT_LOOKUP_SCOPE_STRICT(1<<2), IGNORE_ICAL_CLASSIFICATION(1<<3), IGNORE_ICAL_TRASPARENCY(1<<3), IGNORE_ICAL_ALARMS(1<<4);
		
		private int mask = 0;
		private HandleICalInviationOption(int mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
}
