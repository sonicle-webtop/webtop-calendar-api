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

import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.sonicle.commons.LangUtils;
import com.sonicle.commons.time.DateRange;
import com.sonicle.commons.time.DateTimeRange;
import com.sonicle.webtop.calendar.model.GetEventScope;
import com.sonicle.webtop.calendar.model.Calendar;
import com.sonicle.webtop.calendar.model.CalendarPropSet;
import com.sonicle.webtop.calendar.model.ShareFolderCalendar;
import com.sonicle.webtop.calendar.model.ShareRootCalendar;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttachmentWithBytes;
import com.sonicle.webtop.calendar.model.EventInstance;
import com.sonicle.webtop.calendar.model.EventKey;
import com.sonicle.webtop.calendar.model.EventObject;
import com.sonicle.webtop.calendar.model.EventObjectChanged;
import com.sonicle.webtop.calendar.model.EventObjectWithICalendar;
import com.sonicle.webtop.calendar.model.EventQuery;
import com.sonicle.webtop.calendar.model.SchedEventInstance;
import com.sonicle.webtop.calendar.model.UpdateEventTarget;
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
	
	public List<ShareRootCalendar> listIncomingCalendarRoots() throws WTException;
	public Map<Integer, ShareFolderCalendar> listIncomingCalendarFolders(String rootShareId) throws WTException;	
	public Set<Integer> listCalendarIds() throws WTException;
	public Set<Integer> listIncomingCalendarIds() throws WTException;
	public Map<Integer, Calendar> listCalendars() throws WTException;
	public Map<Integer, DateTime> getCalendarsLastRevision(Collection<Integer> calendarIds) throws WTException;
	public UserProfileId getCalendarOwner(int calendarId) throws WTException;
	public boolean existCalendar(int calendarId) throws WTException;
	public Calendar getCalendar(int calendarId) throws WTException;
	public Calendar getBuiltInCalendar() throws WTException;
	public Calendar addCalendar(Calendar cal) throws WTException;
	public Calendar addBuiltInCalendar() throws WTException;
	public void updateCalendar(Calendar cal) throws WTException;
	public void deleteCalendar(int calendarId) throws WTException;
	public CalendarPropSet getCalendarCustomProps(int calendarId) throws WTException;
	public Map<Integer, CalendarPropSet> getCalendarCustomProps(Collection<Integer> calendarIds) throws WTException;
	public CalendarPropSet updateCalendarCustomProps(int calendarId, CalendarPropSet propertySet) throws WTException;
	public List<EventObject> listEventObjects(int calendarId, EventObjectOutputType outputType) throws WTException;
	public List<EventObject> listEventObjects(int calendarId, DateTime since, EventObjectOutputType outputType) throws WTException;
	public LangUtils.CollectionChangeSet<EventObjectChanged> listEventObjectsChanges(int calendarId, DateTime since, Integer limit) throws WTException;
	public EventObjectWithICalendar getEventObjectWithICalendar(int calendarId, String href) throws WTException;
	public List<EventObjectWithICalendar> getEventObjectsWithICalendar(int calendarId, Collection<String> hrefs) throws WTException;
	public EventObject getEventObject(int calendarId, int eventId, EventObjectOutputType outputType) throws WTException;
	public void addEventObject(int calendarId, String href, net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public void updateEventObject(int calendarId, String href, net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public void deleteEventObject(int calendarId, String href) throws WTException;
	public boolean existEventInstance(Collection<Integer> calendarIds, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listUpcomingEventInstances(Collection<Integer> calendarIds, DateTime now, int days, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateRange range, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateRange range, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateTimeRange range, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public List<SchedEventInstance> listEventInstances(Collection<Integer> calendarIds, DateTimeRange range, Condition<EventQuery> conditionPredicate, DateTimeZone targetTimezone, boolean sort) throws WTException;
	public Event getEvent(int eventId) throws WTException;
	public Event getEvent(GetEventScope scope, String publicUid) throws WTException;
	public EventAttachmentWithBytes getEventAttachment(int eventId, String attachmentId) throws WTException;
	public Event addEvent(Event event) throws WTException;
	public Event addEvent(Event event, boolean notifyAttendees) throws WTException;
	public Event addEventFromICal(int calendarId, net.fortuna.ical4j.model.Calendar ical) throws WTException;
	public void updateEventFromICal(net.fortuna.ical4j.model.Calendar ical) throws WTException;
	public String getEventInstanceKey(int eventId) throws WTException;
	public EventInstance getEventInstance(String eventKey) throws WTException;
	public void updateEventInstance(UpdateEventTarget target, EventInstance event, boolean notifyAttendees) throws WTException;
	public void updateEventInstance(UpdateEventTarget target, EventKey key, DateTime newStart, DateTime newEnd, String newTitle, boolean notifyAttendees) throws WTException;
	public void deleteEventInstance(UpdateEventTarget target, String eventKey, boolean notifyAttendees) throws WTException;
	public void deleteEventInstance(UpdateEventTarget target, EventKey key, boolean notifyAttendees) throws WTException;
	public void restoreEventInstance(EventKey key) throws WTException;
	public void moveEventInstance(EventKey key, int targetCalendarId) throws WTException;
	public Event cloneEventInstance(EventKey key, Integer newCalendarId, DateTime newStart, DateTime newEnd, boolean notifyAttendees) throws WTException;
}
