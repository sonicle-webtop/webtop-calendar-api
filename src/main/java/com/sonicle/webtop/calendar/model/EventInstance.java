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
package com.sonicle.webtop.calendar.model;

import com.google.gson.annotations.SerializedName;
import com.rits.cloning.Cloner;
import com.sonicle.webtop.calendar.IEventInstanceStatable;
import net.sf.qualitycheck.Check;

/**
 *
 * @author malbinola
 */
public class EventInstance extends EventEx implements IEventInstanceStatable {
	protected EventInstanceId id;
	protected String originalEventId;
	
	public EventInstance() {}
	
	public EventInstance(EventInstanceId id, Event event) {
		this(id, Check.notNull(event, "event").getEventId(), event);
	}
	
	public EventInstance(EventInstanceId id, String originalEventId, EventEx event) {
		super();
		Cloner.standard().copyPropertiesOfInheritedClass(event, this);
		// Keep here otherwise data will be overridden by the above!
		this.id = Check.notNull(id, "id");
		this.originalEventId = Check.notEmpty(originalEventId, "originalEventId");
	}
	
	@Override
	public EventInstanceId getId() {
		return id;
	}

	public void setId(EventInstanceId id) {
		this.id = id;
	}
	
	@Override
	public String getOriginalEventId() {
		return originalEventId;
	}
	
	@Override
	public Boolean getHasRecurrence() {
		return getRecurrence() != null;
	}
	
	public Type getType() {
		return computeType(id, originalEventId, getHasRecurrence());
	}
	
	public static Type computeType(final EventInstanceId instanceId, final String originalEventId, final boolean hasRecurrence) {
		// NB: in order to return consistent results, recurrence MUST be read 
		// and set into instance object; otherwise there is no guarantee to 
		// identify type precisely!
		
		if (hasRecurrence && EventInstanceId.isSeriesMaster(instanceId, originalEventId)) {
			return Type.MASTER;
		} else if (hasRecurrence && EventInstanceId.isSeriesItem(instanceId, originalEventId)) {
			return Type.OCCURRENCE;
		} else if (EventInstanceId.isSeriesException(instanceId, originalEventId)) {
			return Type.EXCEPTION;
		} else {
			return Type.SINGLE;
		}
	}
	
	public static enum Type {
		@SerializedName("SI") SINGLE,
		@SerializedName("MA") MASTER,
		@SerializedName("OC") OCCURRENCE,
		@SerializedName("EX") EXCEPTION;
	}
}
