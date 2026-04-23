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
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.webtop.core.app.sdk.QueryBuilder;

/**
 *
 * @author malbinola
 */
public class CalendarQuery extends QueryBuilder<CalendarQuery> {
	public static final String ID = "id";
	public static final String CREATED_AT = "createdAt";
	public static final String UPDATED_AT = "updatedAt";
	public static final String USER_ID = "userId";
	public static final String BUILT_IN = "builtIn";
	public static final String PROVIDER = "provider";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String COLOR = "color";
	public static final String EAS_SYNC = "easSync";
	
	public StringProperty<CalendarQuery> id() {
		return string(ID);
	}
	
	public InstantProperty<CalendarQuery> createdAt() {
		return instant(CREATED_AT);
	}
	
	public InstantProperty<CalendarQuery> updatedAt() {
		return instant(UPDATED_AT);
	}
	
	public StringProperty<CalendarQuery> userId() {
		return string(USER_ID);
	}
	
	public BooleanProperty<CalendarQuery> builtIn() {
		return bool(BUILT_IN);
	}
	
	public StringProperty<CalendarQuery> provider() {
		return string(PROVIDER);
	}

	public StringProperty<CalendarQuery> name() {
		return string(NAME);
	}
	
	public StringProperty<CalendarQuery> description() {
		return string(DESCRIPTION);
	}

	public StringProperty<CalendarQuery> color() {
		return string(COLOR);
	}

	public StringProperty<CalendarQuery> easSync() {
		return string(EAS_SYNC);
	}
}
