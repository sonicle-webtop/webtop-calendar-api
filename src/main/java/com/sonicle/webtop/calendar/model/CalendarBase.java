/*
 * Copyright (C) 2024 Sonicle S.r.l.
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
 * display the words "Copyright (C) 2024 Sonicle S.r.l.".
 */
package com.sonicle.webtop.calendar.model;

import com.google.gson.annotations.SerializedName;
import com.rits.cloning.Cloner;
import com.sonicle.commons.EnumUtils;
import com.sonicle.commons.LangUtils;
import com.sonicle.webtop.core.sdk.UserProfileId;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

/**
 *
 * @author malbinola
 */
public class CalendarBase {
	protected String domainId;
	protected String userId;
	protected Boolean builtIn;
	protected Provider provider;
	protected String name;
	protected String description;
	protected String color;
	protected Sync sync;
	protected Boolean isPrivate;
	protected Boolean defaultBusy;
	protected Integer defaultReminder;
	protected Boolean notifyOnExtUpdate;
	protected String parameters;
	protected Short remoteSyncFrequency;
	protected DateTime remoteSyncTimestamp;
	protected String remoteSyncTag;

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getBuiltIn() {
		return builtIn;
	}

	public void setBuiltIn(Boolean builtIn) {
		this.builtIn = builtIn;
	}
	
	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Sync getSync() {
		return sync;
	}

	public void setSync(Sync sync) {
		this.sync = sync;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public Boolean getDefaultBusy() {
		return defaultBusy;
	}

	public void setDefaultBusy(Boolean defaultBusy) {
		this.defaultBusy = defaultBusy;
	}

	public Integer getDefaultReminder() {
		return defaultReminder;
	}

	public void setDefaultReminder(Integer defaultReminder) {
		this.defaultReminder = defaultReminder;
	}
	
	public Boolean getNotifyOnExtUpdate() {
		return notifyOnExtUpdate;
	}

	public void setNotifyOnExtUpdate(Boolean notifyOnExtUpdate) {
		this.notifyOnExtUpdate = notifyOnExtUpdate;
	}
	
	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	
	public Short getRemoteSyncFrequency() {
		return remoteSyncFrequency;
	}

	public void setRemoteSyncFrequency(Short remoteSyncFrequency) {
		this.remoteSyncFrequency = remoteSyncFrequency;
	}
	
	public DateTime getRemoteSyncTimestamp() {
		return remoteSyncTimestamp;
	}

	public void setRemoteSyncTimestamp(DateTime remoteSyncTimestamp) {
		this.remoteSyncTimestamp = remoteSyncTimestamp;
	}
	
	public String getRemoteSyncTag() {
		return remoteSyncTag;
	}

	public void setRemoteSyncTag(String remoteSyncTag) {
		this.remoteSyncTag = remoteSyncTag;
	}
	
	public UserProfileId getProfileId() {
		return new UserProfileId(getDomainId(), getUserId());
	}
	
	public void setProfileId(UserProfileId pid) {
		setDomainId(pid.getDomainId());
		setUserId(pid.getUserId());
	}
	
	public <T> T getParametersAsObject(T defaultValue, Class<T> type) {
		return LangUtils.deserialize(getParameters(), defaultValue, type);
	}
	
	public <T> void setParametersAsObject(T value, Class<T> type) {
		setParameters(LangUtils.serialize(value, type));
	}
	
	public boolean isProviderRemote() {
		return CalendarBase.isProviderRemote(getProvider());
	}
	
	public static boolean isProviderRemote(String provider) {
		return CalendarBase.isProviderRemote(EnumUtils.forSerializedName(provider, Provider.class));
	}
	
	public static boolean isProviderRemote(Provider provider) {
		return Provider.WEBCAL.equals(provider) || Provider.CALDAV.equals(provider);
	}
	
	public static String getHexColor(String color) {
		return (StringUtils.indexOf(color, "#") == 0) ? StringUtils.substring(color, 1) : color;
	}
	
	public static enum Provider {
		@SerializedName("local") LOCAL,
		@SerializedName("webcal") WEBCAL,
		@SerializedName("caldav") CALDAV;
	}
	
	public static enum Sync {
		@SerializedName("O") OFF,
		@SerializedName("R") READ,
		@SerializedName("W") WRITE;
	}
}
