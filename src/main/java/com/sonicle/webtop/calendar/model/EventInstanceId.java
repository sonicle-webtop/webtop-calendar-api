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

import com.sonicle.commons.Check;
import com.sonicle.commons.LangUtils;
import com.sonicle.commons.time.JodaTimeUtils;
import com.sonicle.commons.web.json.CId;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author malbinola
 */
public class EventInstanceId extends CId {
	public static final String DUMMY_EVENT_ID = "00000000000000000000000000000000";
	public static final String NO_INSTANCE_DATE = "00000000";
	private static final DateTimeFormatter fmtYMD = JodaTimeUtils.createFormatter("yyyyMMdd", DateTimeZone.UTC);
	
	public EventInstanceId(String id) {
		super(".", id, 2);
		if (!StringUtils.contains(id, ".")) throw new IllegalArgumentException("Not a valid instance ID");
	}
	
	protected EventInstanceId(CId.AbstractBuilder builder) {
		super(builder);
	}
	
	public String getEventId() {
		return getToken(0);
	}
	
	public String getInstance() {
		return getToken(1);
	}

	public LocalDate getInstanceAsDate() {
		if (hasNoInstance()) {
			return null;
		} else {
			return parseInstancePart(getInstance());
		}
	}

	public boolean hasNoInstance() {
		return NO_INSTANCE_DATE.equals(getInstance());
	}
	
	public static LocalDate parseInstancePart(final String s) {
		return JodaTimeUtils.parseDateTime(fmtYMD, s).toLocalDate();
	}

	public static EventInstanceId parse(final String s) {
		try {
			return new EventInstanceId(s);
		} catch(IllegalArgumentException ex) {
			return null;
		}
	}
	
	public static EventInstanceId asSeriesMasterInstanceId(final EventInstanceId iid) {
		Check.notNull(iid, "iid");
		return buildMaster(iid.getEventId());
	}
	
	public static boolean isSingleOrSeriesMaster(final EventInstanceId iid, final String originalEventId) {
		Check.notNull(iid, "iid");
		return iid.getEventId().equals(originalEventId) && NO_INSTANCE_DATE.equals(iid.getInstance());
	}
	
	public static boolean isSeriesMaster(final EventInstanceId iid, final String originalEventId) {
		Check.notNull(iid, "iid");
		return iid.getEventId().equals(originalEventId) && NO_INSTANCE_DATE.equals(iid.getInstance());
	}
	
	public static boolean isSeriesOverride(final EventInstanceId iid, final String originalEventId) {
		Check.notNull(iid, "iid");
		return !StringUtils.isBlank(originalEventId) && !iid.getEventId().equals(originalEventId) && !NO_INSTANCE_DATE.equals(iid.getInstance());
	}
	
	public static boolean isSeriesVirtual(final EventInstanceId iid, final String originalEventId) {
		Check.notNull(iid, "iid");
		return !isSeriesMaster(iid, originalEventId) && !isSeriesOverride(iid, originalEventId) && iid.getEventId().equals(originalEventId);
	}
	
	@Deprecated
	public static EventInstanceId buildSingleInstance(final String singleEventId) {
		return buildSingle(singleEventId);
	}
		
	public static EventInstanceId buildSingle(final String singleEventId) {
		return build(singleEventId, NO_INSTANCE_DATE);
	}
	
	public static EventInstanceId buildMaster(final String masterEventId) {
		return build(masterEventId, NO_INSTANCE_DATE);
	}
	
	public static EventInstanceId buildInstance(final String masterEventId, final LocalDate instance) {
		return build(masterEventId, JodaTimeUtils.print(fmtYMD, instance));
	}
	
	public static EventInstanceId buildInstance(final String masterEventId, final DateTime instance, final DateTimeZone targetTimezone) {
		return build(masterEventId, JodaTimeUtils.print(JodaTimeUtils.createFormatter("yyyyMMdd", targetTimezone), instance));
	}
	
	public static EventInstanceId buildDummy() {
		return build(DUMMY_EVENT_ID, (String)null);
	}
	
	public static EventInstanceId build(final String eventId, final String masterEventId, final String instance) {
		return build(LangUtils.coalesceStrings(masterEventId, eventId), instance);
	}
	
	public static EventInstanceId build(final String eventId, final String instance) {
		return new Builder()
			.withSeparator(".")
			.withTokens(Check.notNull(eventId, "eventId"), StringUtils.defaultIfBlank(instance, NO_INSTANCE_DATE))
			.build();
	}

	private static class Builder extends CId.AbstractBuilder<Builder, EventInstanceId> {
		@Override
		public EventInstanceId build() {
			return new EventInstanceId(this);
		}
	}
}
