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

import com.github.rutledgepaulv.qbuilders.conditions.Condition;
import com.github.rutledgepaulv.qbuilders.properties.concrete.BooleanProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.InstantProperty;
import com.github.rutledgepaulv.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.DateTimeUtils;
import com.sonicle.commons.web.json.CompId;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCValues;
import com.sonicle.webtop.core.app.sdk.WTUnsupportedOperationException;
import com.sonicle.webtop.core.model.CustomField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class EventQuery extends QueryBuilderWithCValues<EventQuery> {

	public EventQuery() {
		super(true);
	}
	
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

	public BooleanProperty<EventQuery> isBusy() {
		return bool("busy");
	}
	
	public BooleanProperty<EventQuery> isPrivate() {
		return bool("private");
	}
	
	public StringProperty<EventQuery> tag() {
		return string("tag");
	}

	public StringProperty<EventQuery> any() {
		return string("any");
	}
	
	public static Condition<EventQuery> toCondition(String pattern) {
		if (!StringUtils.isBlank(pattern)) {
			return new EventQuery().any().eq(StringUtils.replace(pattern, "%", "*"));
		} else {
			return null;
		}
	}
	
	public static Condition<EventQuery> toCondition(QueryObj query, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		Condition<EventQuery> result = null;
		
		for (Map.Entry<String, Collection<QueryObj.Condition>> entry : query.getConditionsMap().entrySet()) {
			EventQuery q = (result == null) ? new EventQuery() : result.and();
			
			ArrayList<Condition<EventQuery>> cndts = new ArrayList<>();
			for (QueryObj.Condition queryCondition : entry.getValue()) {
				if ("title".equals(queryCondition.keyword)) {
					cndts.add(new EventQuery().title().eq(q.asSmartStringValue(queryCondition.value)));
					
				} else if ("location".equals(queryCondition.keyword)) {
					cndts.add(new EventQuery().location().eq(q.asSmartStringValue(queryCondition.value)));
					
				} else if ("description".equals(queryCondition.keyword)) {
					cndts.add(new EventQuery().description().eq(queryCondition.value));
					
				} else if ("after".equals(queryCondition.keyword)) {
					String after = StringUtils.replace(queryCondition.value, "/", "-");
					cndts.add(new EventQuery().after().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(after), DateTimeUtils.toZoneId(timezone))));
					
				} else if ("before".equals(queryCondition.keyword)) {
					String before = StringUtils.replace(queryCondition.value, "/", "-");
					cndts.add(new EventQuery().before().eq(DateTimeUtils.toInstant(DateTimeUtils.parseLocalDate(before), DateTimeUtils.toZoneId(timezone))));
					
				} else if ("is".equals(queryCondition.keyword)) {
					switch (queryCondition.value) {
						case "busy":
							cndts.add(new EventQuery().isBusy().isTrue());
							break;
						case "private":
							cndts.add(new EventQuery().isPrivate().isTrue());
							break;
						default:
							throw new UnsupportedOperationException(queryCondition.keyword + ":" + queryCondition.value);
					}
					
				} else if ("tag".equals(queryCondition.keyword)) {
					cndts.add(new EventQuery().tag().eq(queryCondition.value));
					
				} else if (StringUtils.startsWith(queryCondition.keyword, "cfield")) {
					Condition<EventQuery> cond = null;
					CompId cf = new CompId(2).parse(queryCondition.keyword, false);
					if (!cf.isTokenEmpty(1)) {
						String cfId = cf.getToken(1);
						if (customFieldTypeMapping.containsKey(cfId)) {
							cond = new EventQuery().customValueCondition(cfId, customFieldTypeMapping.get(cfId), queryCondition.value, queryCondition.negated, timezone);
						}
					}
					if (cond != null) cndts.add(cond);					
					
				} else {
					throw new WTUnsupportedOperationException("Unsupported keyword '{}'", queryCondition.keyword);
				}
			}
			result = q.or(cndts);
		}
		
		if (!StringUtils.isBlank(query.allText)) {
			EventQuery q = (result == null) ? new EventQuery() : result.and();
			result = q.any().eq(q.asSmartStringValue(query.allText));
		}
		
		return result;
	}
}
