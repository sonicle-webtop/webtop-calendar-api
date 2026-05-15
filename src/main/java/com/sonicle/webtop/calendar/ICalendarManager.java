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

import com.google.gson.annotations.SerializedName;
import com.sonicle.commons.beans.ItemsListResult;
import com.sonicle.commons.beans.SortInfo;
import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.flags.BitFlags;
import com.sonicle.commons.flags.BitFlagsEnum;
import com.sonicle.commons.time.DateTimeRange2;
import com.sonicle.commons.time.DateTimeWindow;
import com.sonicle.commons.time.DateWindow;
import com.sonicle.commons.time.TimeRange;
import com.sonicle.webtop.calendar.model.GetEventScope;
import com.sonicle.webtop.calendar.model.Calendar;
import com.sonicle.webtop.calendar.model.CalendarBase;
import com.sonicle.webtop.calendar.model.CalendarFSFolder;
import com.sonicle.webtop.calendar.model.CalendarFSOrigin;
import com.sonicle.webtop.calendar.model.CalendarPropSet;
import com.sonicle.webtop.calendar.model.CalendarQuery;
import com.sonicle.webtop.calendar.model.ComparableEventRange;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventAttachmentWithBytes;
import com.sonicle.webtop.calendar.model.EventBase;
import com.sonicle.webtop.calendar.model.EventEx;
import com.sonicle.webtop.calendar.model.EventInstance;
import com.sonicle.webtop.calendar.model.EventInstanceId;
import com.sonicle.webtop.calendar.model.EventLookupInstance;
import com.sonicle.webtop.calendar.model.EventObject;
import com.sonicle.webtop.calendar.model.EventQuery;
import com.sonicle.webtop.calendar.model.UpdateEventTarget;
import com.sonicle.webtop.calendar.model.UpdateTagsOperation;
import com.sonicle.webtop.core.app.sdk.WTConstraintException;
import com.sonicle.webtop.core.app.sdk.WTNotFoundException;
import com.sonicle.webtop.core.app.sdk.WTParseException;
import com.sonicle.webtop.core.model.CustomFieldValue;
import com.sonicle.webtop.core.app.model.FolderSharing;
import com.sonicle.webtop.core.model.Delta;
import com.sonicle.webtop.core.sdk.UserProfileId;
import com.sonicle.webtop.core.sdk.WTException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

/**
 *
 * @author malbinola
 */
public interface ICalendarManager {
	
	public Set<FolderSharing.SubjectConfiguration> getFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope) throws WTException;
	public void updateFolderShareConfigurations(final UserProfileId originProfileId, final FolderSharing.Scope scope, final Set<FolderSharing.SubjectConfiguration> configurations) throws WTException;
	public Map<UserProfileId, CalendarFSOrigin> listIncomingCalendarOrigins() throws WTException;
	public CalendarFSOrigin getIncomingCalendarOriginByFolderId(final int calendarId) throws WTException;
	public Map<Integer, CalendarFSFolder> listIncomingCalendarFolders(final CalendarFSOrigin origin) throws WTException;
	public Map<Integer, CalendarFSFolder> listIncomingCalendarFolders(final UserProfileId originProfileId) throws WTException;
	public Set<Integer> listMyCalendarIds() throws WTException;
	public Set<Integer> listIncomingCalendarIds() throws WTException;
	public Set<Integer> listIncomingCalendarIds(final UserProfileId originProfile) throws WTException;
	public Set<Integer> listAllCalendarIds() throws WTException;
	public Integer getDefaultCalendarId() throws WTException;
	public Integer getBuiltInCalendarId() throws WTException;
	public ItemsListResult<Calendar> listCalendars(final Condition<CalendarQuery> filterQuery, final Set<SortInfo> sortInfo, final Integer page, final Integer limit, final boolean returnFullCount) throws WTException;
	public ItemsListResult<Calendar> listCalendars(final String filterQuery, final Set<SortInfo> sortInfo, final Integer page, final Integer limit, final boolean returnFullCount) throws WTException;
	public Map<Integer, Calendar> listMyCalendars() throws WTException;
	public Map<Integer, Calendar> listMyCalendars(final Collection<Integer> calendarIds) throws WTException;
	public Map<Integer, DateTime> getCalendarsItemsLastRevision(Collection<Integer> calendarIds) throws WTException;
	public UserProfileId getCalendarOwner(int calendarId) throws WTException;
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
	public List<ComparableEventRange> listEventsBounds(final Collection<Integer> calendarIds, final DateTimeRange2 viewRange, final DateTimeZone targetTimezone) throws WTException;
	public List<EventObject> listEventObjects(final int calendarId, final EventObjectOutputType outputType) throws WTException;
	public List<EventObject> listEventObjects(final int calendarId, final DateTime since, final EventObjectOutputType outputType) throws WTException;
	public Delta<EventObject> listEventObjectsDelta(final int calendarId, final String syncToken, final EventObjectOutputType outputType) throws WTException;
	public Delta<EventObject> listEventObjectsDelta(final int calendarId, final DateTime since, final EventObjectOutputType outputType) throws WTException;
	public List<EventObject> getEventObjects(final int calendarId, final Collection<String> hrefs, final EventObjectOutputType outputType) throws WTException;
	public EventObject getEventObject(final int calendarId, String eventId, final EventObjectOutputType outputType) throws WTException;
	public void addEventObject(final int calendarId, final String href, final net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public void updateEventObject(final int calendarId, final String href, final net.fortuna.ical4j.model.Calendar iCalendar) throws WTNotFoundException, WTException;
	public void deleteEventObject(final int calendarId, final String href) throws WTNotFoundException, WTException;
	public void deleteEventObject(final int calendarId, final String href, final BitFlags<EventNotifyOption> notifyOptions) throws WTNotFoundException, WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final DateWindow timeWindow, final boolean sort, final DateTimeZone targetTimezone) throws WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final DateWindow timeWindow, final Condition<EventQuery> conditionPredicate, final boolean sort, final DateTimeZone targetTimezone) throws WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final DateTimeZone targetTimezone) throws  WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final Condition<EventQuery> filterQuery, final boolean sort, final DateTimeZone targetTimezone) throws  WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final DateTimeWindow timeWindow, final Condition<EventQuery> filterQuery, final boolean sort, final DateTimeZone targetTimezone) throws  WTException;
	public List<EventLookupInstance> listEventInstances(final Collection<Integer> calendarIds, final DateTimeWindow timeWindow, final String filterQuery, final boolean sort, final DateTimeZone targetTimezone) throws WTException;
	public Set<LocalDate> listEventInstancesDates(final Collection<Integer> calendarIds, final DateTimeZone targetTimezone) throws WTException;
	public Set<LocalDate> listEventInstancesDates(final Collection<Integer> calendarIds, final DateTimeWindow timeWindow, final String filterQuery, final DateTimeZone targetTimezone) throws WTException;
	public Set<LocalDate> listEventDates(final Collection<Integer> calendarIds, final DateTimeWindow timeWindow, final DateTimeZone targetTimezone) throws WTException;
	public Map<String, DateTime> computeTransparencyTimeSpans(final UserProfileId targetProfileId, final EventBase.Transparency transparency, final DateWindow dateWindow, final TimeRange timeRange, final int minuteResolution, final DateTimeZone targetTimezone) throws WTException;
	
	@Deprecated public Event getEvent(GetEventScope scope, String publicUid) throws WTException;
	public Event addEvent(final EventEx event) throws WTException;
	public Event addEvent(final EventEx event, final boolean notifyAttendees) throws WTException;
	public Event addEvent(final EventEx event, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public Event addEvent(final EventEx event, final String rawICalendar, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public Event addEvent(final int calendarId, final net.fortuna.ical4j.model.Calendar iCalendar) throws WTException;
	public Event handleInvitationFromICal(final net.fortuna.ical4j.model.Calendar iCalendar, final Integer calendarId, final BitFlags<HandleICalInviationOption> options) throws WTParseException, WTNotFoundException, WTConstraintException, WTException;
	public EventInstance getEventInstance(final EventInstanceId instanceId) throws WTException;
	public EventInstance getEventInstance(final EventInstanceId instanceId, final BitFlags<EventGetOption> options) throws WTException;
	public EventAttachmentWithBytes getEventInstanceAttachment(final EventInstanceId instanceId, final String attachmentId) throws WTException;
	public Map<String, CustomFieldValue> getEventInstanceCustomValues(final EventInstanceId instanceId) throws WTException;
	public Event cloneEventInstance(final EventInstanceId sourceInstanceId, final Integer newCalendarId, final DateTime newStart, final DateTime newEnd, final String newTitle, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void updateEventInstance(final UpdateEventTarget target, final EventInstanceId instanceId, final EventEx event) throws WTException;
	public void updateEventInstance(final UpdateEventTarget target, final EventInstanceId instanceId, final EventEx event, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void updateEventInstance(final UpdateEventTarget target, final EventInstanceId instanceId, final EventEx event, final BitFlags<EventUpdateOption> options, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void updateEventInstanceQuick(final UpdateEventTarget target, final EventInstanceId instanceId, final DateTime newStart, final DateTime newEnd, final String newTitle) throws WTException;
	public void updateEventInstanceQuick(final UpdateEventTarget target, final EventInstanceId instanceId, final DateTime newStart, final DateTime newEnd, final String newTitle, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void updateEventInstanceTags(final UpdateTagsOperation operation, final Collection<EventInstanceId> instanceIds, final Set<String> tagIds) throws WTException;
	public void deleteEvent(final int calendarId, final String publicUid, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void deleteEventInstance(final UpdateEventTarget target, final EventInstanceId instanceId, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void deleteEventInstance(final UpdateEventTarget target, final Collection<EventInstanceId> instanceIds, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void restoreEventInstance(final EventInstanceId instanceId, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void moveEventInstance(final boolean copy, final Collection<EventInstanceId> instanceIds, final int targetCalendarId, final BitFlags<EventGetOption> copyOptions, final BitFlags<EventNotifyOption> notifyOptions) throws WTException;
	public void updateEventCalendarTags(final UpdateTagsOperation operation, final int calendarId, final Set<String> tagIds) throws WTException;
	public Map<String, DateTime> generateTimeSpans(final LocalDate fromDate, final LocalDate toDate, final TimeRange timeRange, final boolean applyTimeRangeForEachDay, final int minuteResolution, final DateTimeZone targetTimezone);
	
	public static enum ImportMode {
		@SerializedName("copy") COPY,
		@SerializedName("append") APPEND
	}
	
	public static enum EventNotifyOption implements BitFlagsEnum<EventNotifyOption> {
		NOTIFY_INDIVIDUAL_ATTENDEE(1<<1), NOTIFY_RESOURCE_ATTENDEE(1<<2);
		
		private long mask = 0;
		private EventNotifyOption(long mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
		
		public static BitFlags<EventNotifyOption> withDefaults() {
			return BitFlags.with(EventNotifyOption.NOTIFY_RESOURCE_ATTENDEE);
		}
		
		public static BitFlags<EventNotifyOption> withAllAttendeesNotifications() {
			return BitFlags.with(EventNotifyOption.NOTIFY_INDIVIDUAL_ATTENDEE, EventNotifyOption.NOTIFY_RESOURCE_ATTENDEE);
			//return enableAttendeesNotifications(BitFlags.noneOf(EventAddOption.class));
		}
		
		public static BitFlags<EventNotifyOption> enableAllAttendeesNotifications(BitFlags<EventNotifyOption> opts) {
			opts.set(EventNotifyOption.NOTIFY_INDIVIDUAL_ATTENDEE, EventNotifyOption.NOTIFY_RESOURCE_ATTENDEE);
			return opts;
		}
		
		public static BitFlags<EventNotifyOption> withoutAnyAttendeesNotifications() {
			return BitFlags.noneOf(EventNotifyOption.class);
			//return disableAttendeesNotifications(BitFlags.noneOf(EventAddOption.class));
		}
		
		public static BitFlags<EventNotifyOption> disableAllAttendeesNotifications(BitFlags<EventNotifyOption> opts) {
			opts.unset(EventNotifyOption.NOTIFY_INDIVIDUAL_ATTENDEE, EventNotifyOption.NOTIFY_RESOURCE_ATTENDEE);
			return opts;
		}
	}
	
	public static enum EventReminderOption implements BitFlagsEnum<EventReminderOption> {
		IGNORE(1<<0), DISARM_PAST(1<<1);
		
		private long mask = 0;
		private EventReminderOption(long mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
	
	public static enum EventGetOption implements BitFlagsEnum<EventGetOption> {
		ATTENDEES(1<<0), ATTACHMENTS(1<<1), TAGS(1<<2), CUSTOM_VALUES(1<<3);
		
		private long mask = 0;
		private EventGetOption(long mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
		
		public static BitFlags<EventGetOption> parseEventUpdateOptions(BitFlags<EventUpdateOption> flags) {
			BitFlags<EventGetOption> ret = new BitFlags<>(EventGetOption.class);
			if (flags.has(EventUpdateOption.ATTENDEES)) ret.set(EventGetOption.ATTENDEES);
			if (flags.has(EventUpdateOption.ATTACHMENTS)) ret.set(EventGetOption.ATTACHMENTS);
			if (flags.has(EventUpdateOption.TAGS)) ret.set(EventGetOption.TAGS);
			if (flags.has(EventUpdateOption.CUSTOM_VALUES)) ret.set(EventGetOption.CUSTOM_VALUES);
			return ret;
		}
	}
	
	public static enum EventUpdateOption implements BitFlagsEnum<EventUpdateOption> {
		ATTENDEES(1<<0), ATTACHMENTS(1<<1), TAGS(1<<2), CUSTOM_VALUES(1<<3);
		
		private long mask = 0;
		private EventUpdateOption(long mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
	
	public static enum HandleICalInviationOption implements BitFlagsEnum<HandleICalInviationOption> {
		CONSTRAIN_AVAILABILITY(1<<1), EVENT_LOOKUP_SCOPE_STRICT(1<<2), IGNORE_ICAL_CLASSIFICATION(1<<3), IGNORE_ICAL_TRASPARENCY(1<<3), IGNORE_ICAL_ALARMS(1<<4);
		
		private long mask = 0;
		private HandleICalInviationOption(long mask) { this.mask = mask; }
		@Override
		public long mask() { return this.mask; }
	}
}
