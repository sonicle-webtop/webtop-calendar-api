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

import com.sonicle.commons.qbuilders.properties.concrete.BooleanProperty;
import com.sonicle.commons.qbuilders.properties.concrete.InstantProperty;
import com.sonicle.commons.qbuilders.properties.concrete.IntegerProperty;
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCFields;

/**
 *
 * @author malbinola
 */
public class EventQuery extends QueryBuilderWithCFields<EventQuery> {
	public static final String ID = "id";
	public static final String CREATED_AT = "createdAt";
	public static final String UPDATED_AT = "updatedAt";
	public static final String ROW_STATUS = "rowStatus";
	public static final String STATUS = "status";
	public static final String ORGANIZER = "organizer";
	public static final String ORGANIZER_ID = "organizerId";
	public static final String TITLE = "title";
	public static final String LOCATION = "location";
	public static final String DESCRIPTION = "description";
	public static final String TIMEZONE = "timezone";
	public static final String ALL_DAY = "allDay";
	public static final String START = "start";
	public static final String END = "end";
	public static final String VISIBILITY = "visibility";
	public static final String TRANSPARENCY = "transparency";
	public static final String REMINDER = "reminder";
	//public static final String CONTACT = "contact";
	//public static final String CONTACT_ID = "contactId";
	//public static final String COMPANY = "company";
	//public static final String COMPANY_ID = "companyId";
	public static final String TAG_ID = "tagId";
	
	public StringProperty<EventQuery> id() {
		return string(ID);
	}
	
	public InstantProperty<EventQuery> createdAt() {
		return instant(CREATED_AT);
	}
	
	public InstantProperty<EventQuery> updatedAt() {
		return instant(UPDATED_AT);
	}
	
	public StringProperty<EventQuery> rowStatus() {
		return string(ROW_STATUS);
	}
	
	public StringProperty<EventQuery> status() {
		return string(STATUS);
	}
	
	public StringProperty<EventQuery> organizer() {
		return string(ORGANIZER);
	}
	
	public StringProperty<EventQuery> organizerId() {
		return string(ORGANIZER_ID);
	}
	
	public StringProperty<EventQuery> title() {
		return string(TITLE);
	}
	
	public StringProperty<EventQuery> location() {
		return string(LOCATION);
	}
	
	public StringProperty<EventQuery> description() {
		return string(DESCRIPTION);
	}
	
	public StringProperty<EventQuery> timezone() {
		return string(TIMEZONE);
	}
	
	public BooleanProperty<EventQuery> allDay() {
		return bool(ALL_DAY);
	}
	
	public InstantProperty<EventQuery> start() {
		return instant(START);
	}
	
	public InstantProperty<EventQuery> end() {
		return instant(END);
	}
	
	public StringProperty<EventQuery> visibility() {
		return string(VISIBILITY);
	}
	
	public StringProperty<EventQuery> transparency() {
		return string(TRANSPARENCY);
	}
	
	public IntegerProperty<EventQuery> reminder() {
		return intNum(REMINDER);
	}
	
	/*
	public StringProperty<EventQueryNew> contact() {
		return string(CONTACT);
	}
	
	public StringProperty<EventQueryNew> contactId() {
		return string(CONTACT_ID);
	}
	
	public StringProperty<EventQueryNew> company() {
		return string(COMPANY);
	}
	
	public StringProperty<EventQueryNew> companyId() {
		return string(COMPANY_ID);
	}
	*/
	
	public StringProperty<EventQuery> tagId() {
		return string(TAG_ID);
	}
}
