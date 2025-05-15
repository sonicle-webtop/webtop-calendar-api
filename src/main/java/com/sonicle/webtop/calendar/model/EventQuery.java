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

import com.sonicle.commons.qbuilders.conditions.Condition;
import com.sonicle.commons.qbuilders.properties.concrete.BooleanProperty;
import com.sonicle.commons.qbuilders.properties.concrete.InstantProperty;
import com.sonicle.commons.qbuilders.properties.concrete.StringProperty;
import com.sonicle.commons.time.JavaTimeUtils;
import com.sonicle.commons.web.json.CId;
import com.sonicle.commons.web.json.bean.QueryObj;
import com.sonicle.webtop.core.app.sdk.QueryBuilderWithCValues;
import com.sonicle.webtop.core.app.sdk.WTUnsupportedOperationException;
import com.sonicle.webtop.core.model.CustomField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTimeZone;

/**
 *
 * @author malbinola
 */
public class EventQuery extends QueryBuilderWithCValues<EventQuery> {
	
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
	
	public StringProperty<EventQuery> masterDataId() {
		return string("masterDataId");
	}
	
	public StringProperty<EventQuery> statMasterDataId() {
		return string("statMasterDataId");
	}
	
	public StringProperty<EventQuery> activityId() {
		return string("activityId");
	}
	
	public StringProperty<EventQuery> causalId() {
		return string("causalId");
	}
	
	public StringProperty<EventQuery> tag() {
		return string("tag");
	}

	public StringProperty<EventQuery> any() {
		return string("any");
	}
	
	public static Condition<EventQuery> createCondition(String pattern) {
		if (!StringUtils.isBlank(pattern)) {
			return new EventQuery().any().eq(StringUtils.replace(pattern, "%", "*"));
		} else {
			return null;
		}
	}
	
	public static Condition<EventQuery> createCondition(QueryObj query, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone) {
		boolean smartStringComparison = true;
		
		Condition<EventQuery> last = new EventQuery().trueCondition();
		for (Map.Entry<QueryObj.Condition, List<String>> entry : query.groupConditions(Arrays.asList("is")).entrySet()) {
			final QueryObj.Condition key = entry.getKey();
			final List<String> values = entry.getValue();
			
			if (values.isEmpty() || values.size() == 1) {
				last = new EventQuery().and(last, createCondition(key, values.isEmpty() ? null : values.get(0), customFieldTypeMapping, timezone, smartStringComparison));
			} else {
				List<Condition<EventQuery>> conds = new ArrayList<>();
				for (String value : entry.getValue()) {
					conds.add(createCondition(key, value, customFieldTypeMapping, timezone, smartStringComparison));
				}
				last = new EventQuery().and(last, new EventQuery().or(conds));
			}
		}
		
		if (!StringUtils.isBlank(query.getAllText())) {
			return new EventQuery().and(last, new EventQuery().any().eq(asStringValue(query.getAllText(), smartStringComparison)));
		} else {
			return last;
		}
	}
	
	private static Condition<EventQuery> createCondition(QueryObj.Condition condition, String value, Map<String, CustomField.Type> customFieldTypeMapping, DateTimeZone timezone, boolean smartStringComparison) {
		if ("title".equals(condition.keyword)) {
			return new EventQuery().title().eq(asStringValue(value, smartStringComparison));

		} else if ("location".equals(condition.keyword)) {
			return new EventQuery().location().eq(asStringValue(value, smartStringComparison));

		} else if ("description".equals(condition.keyword)) {
			return new EventQuery().description().eq(value);

		} else if ("after".equals(condition.keyword)) {
			String after = StringUtils.replace(value, "/", "-");
			return new EventQuery().after().eq(JavaTimeUtils.toInstant(JavaTimeUtils.parseLocalDateYMD(after), JavaTimeUtils.toZoneId(timezone)));

		} else if ("before".equals(condition.keyword)) {
			String before = StringUtils.replace(value, "/", "-");
			return new EventQuery().before().eq(JavaTimeUtils.toInstant(JavaTimeUtils.parseLocalDateYMD(before), JavaTimeUtils.toZoneId(timezone)));

		} else if ("masterDataId".equals(condition.keyword)) {
			return new EventQuery().masterDataId().eq(asStringValue(value, smartStringComparison));

		} else if ("statMasterDataId".equals(condition.keyword)) {
			return new EventQuery().masterDataId().eq(asStringValue(value, smartStringComparison));

		} else if ("activityId".equals(condition.keyword)) {
			return new EventQuery().activityId().eq(asStringValue(value, smartStringComparison));

		} else if ("causalId".equals(condition.keyword)) {
			return new EventQuery().causalId().eq(asStringValue(value, smartStringComparison));

		} else if ("busy".equals(condition.keyword)) {
			return condition.negated ? new EventQuery().isBusy().isFalse() : new EventQuery().isBusy().isTrue();

		} else if ("private".equals(condition.keyword)) {
			return condition.negated ? new EventQuery().isPrivate().isFalse() : new EventQuery().isPrivate().isTrue();

		} else if ("tag".equals(condition.keyword)) {
			return new EventQuery().tag().eq(value);

		} else if (StringUtils.startsWith(condition.keyword, "cfield")) {
			CId cf = new CId(condition.keyword, 2);
			if (!cf.isTokenEmpty(1)) {
				String cfId = cf.getToken(1);
				if (customFieldTypeMapping.containsKey(cfId)) {
					return new EventQuery().customValueCondition(cfId, customFieldTypeMapping.get(cfId), value, condition.negated, smartStringComparison, timezone);
				}
			}
		}
		
		throw new WTUnsupportedOperationException("Unsupported keyword '{}'", condition.keyword);
	}
}
