/*
 * Copyright (C) 2019 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2019 Sonicle S.r.l.".
 */
package com.sonicle.webtop.calendar.model;

import com.github.rutledgepaulv.qbuilders.builders.QBuilder;
import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.properties.concrete.InstantProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.bean.QueryObj;
import java.time.LocalDate;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class EventQuery extends QBuilder<EventQuery> {
	
	public StringProperty<EventQuery> title() {
		return string("title");
	}

	public StringProperty<EventQuery> location() {
		return string("location");
	}

	public StringProperty<EventQuery> description() {
		return string("description");
	}
	
	public InstantProperty<EventQuery> after() {
		return instant("after");
	}
	
	public InstantProperty<EventQuery> before() {
		return instant("before");
	}

	public StringProperty<EventQuery> isBusy() {
		return string("busy");
	}
	
	public StringProperty<EventQuery> isPrivate() {
		return string("private");
	}

	public StringProperty<EventQuery> any() {
		return string("any");
	}
	
	/**
	 * Only for backward compatibility: it make possible to use like pattern 
	 * as query source. (remove when transition to new code is done)
	 * @param pattern
	 * @return
	 * @deprecated
	 */
	@Deprecated
	public static Condition<EventQuery> toCondition(String pattern) {
		Condition<EventQuery> result = null;
		if (!StringUtils.isBlank(pattern)) {
			EventQuery q = (result == null) ? new EventQuery() : result.and();
			result = q.any().eq(StringUtils.replace(pattern, "%", "*"));
		}
		return result;
	}
	
	public static Condition<EventQuery> toCondition(QueryObj query, DateTimeZone timezone) {
		Condition<EventQuery> result = null;
		for (QueryObj.Condition queryCondition : query.conditions) {
			EventQuery q = (result == null) ? new EventQuery() : result.and();
			switch(queryCondition.keyword) {
				case "title":
					result = q.title().eq(queryCondition.value);
					break;
				case "location":
					result = q.location().eq(queryCondition.value);
					break;
				case "description":
					result = q.description().eq(queryCondition.value);
					break;
				case "after":
					String after = StringUtils.replace(queryCondition.value, "/", "-");
					result = q.after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone)));
					break;
				case "before":
					String before = StringUtils.replace(queryCondition.value, "/", "-");
					result = q.before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone)));
					break;
				case "is":
					switch(queryCondition.value) {
						case "busy":
							result = q.isBusy().eq(queryCondition.value);
							break;
						case "private":
							result = q.isPrivate().eq(queryCondition.value);
							break;
						default:
							throw new UnsupportedOperationException(queryCondition.keyword + ":" + queryCondition.value);
					}
					break;
					
				default:
					throw new UnsupportedOperationException(queryCondition.keyword);
			}
		}
		
		if (!StringUtils.isBlank(query.allText)) {
			EventQuery q = (result == null) ? new EventQuery() : result.and();
			result = q.any().eq(query.allText);
		}
		
		return result;
	}
}
