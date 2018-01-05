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

import com.sonicle.webtop.calendar.model.GetEventScope;
import com.sonicle.webtop.calendar.model.Calendar;
import com.sonicle.webtop.calendar.model.ShareFolderCalendar;
import com.sonicle.webtop.calendar.model.ShareRootCalendar;
import com.sonicle.webtop.calendar.model.Event;
import com.sonicle.webtop.calendar.model.EventInstance;
import com.sonicle.webtop.core.sdk.UserProfileId;
import com.sonicle.webtop.core.sdk.WTException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

/**
 *
 * @author malbinola
 */
public interface ICalendarManager {
	
	public List<ShareRootCalendar> listIncomingCalendarRoots() throws WTException;
	public Map<Integer, ShareFolderCalendar> listIncomingCalendarFolders(String rootShareId) throws WTException;	
	public List<Integer> listCalendarIds() throws WTException;
	public List<Integer> listIncomingCalendarIds() throws WTException;
	public List<Calendar> listCalendars() throws WTException;
	public UserProfileId getCalendarOwner(int calendarId) throws WTException;
	public Calendar getCalendar(int calendarId) throws WTException;
	public Calendar getBuiltInCalendar() throws WTException;
	public Calendar addCalendar(Calendar cal) throws WTException;
	public Calendar addBuiltInCalendar() throws WTException;
	public Calendar updateCalendar(Calendar cal) throws Exception;
	public boolean deleteCalendar(int calendarId) throws WTException;
	public Event getEvent(int eventId) throws WTException;
	public Event getEvent(GetEventScope scope, boolean forceOriginal, String publicUid) throws WTException;
	public Event addEvent(Event event) throws WTException;
	public Event addEvent(Event event, boolean notifyAttendees) throws WTException;
	public Event addEventFromICal(int calendarId, net.fortuna.ical4j.model.Calendar ical) throws WTException;
	public void updateEventFromICalReply(net.fortuna.ical4j.model.Calendar ical) throws WTException;
	public void updateEventFromICal(net.fortuna.ical4j.model.Calendar ical) throws WTException;
	public String getEventInstanceKey(int eventId) throws WTException;
	public EventInstance getEventInstance(String eventKey) throws WTException;
	public void updateEventInstance(String target, EventInstance event) throws WTException;
	public void cloneEventInstance(String eventKey, DateTime startDate, DateTime endDate) throws WTException;
	public void updateEventInstance(String eventKey, DateTime startDate, DateTime endDate, String title) throws WTException;
	public void deleteEventInstance(String target, String eventKey) throws WTException;
	public void restoreEventInstance(String eventKey) throws WTException;
	public void moveEventInstance(boolean copy, String eventKey, int targetCalendarId) throws WTException;
}
