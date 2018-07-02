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

import com.sonicle.commons.InternetAddressUtils;
import com.sonicle.commons.MailUtils;
import java.util.ArrayList;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author malbinola
 */
public class EventAttendee {
	public static final String RECIPIENT_TYPE_INDIVIDUAL = "IND";
	public static final String RECIPIENT_TYPE_RESOURCE = "RES";
	public static final String RECIPIENT_ROLE_CHAIR = "CHA";
	public static final String RECIPIENT_ROLE_OPTIONAL = "OPT";
	public static final String RECIPIENT_ROLE_REQUIRED = "REQ";
	public static final String RESPONSE_STATUS_NEEDSACTION = "NA"; // needsAction
	public static final String RESPONSE_STATUS_DECLINED = "DE"; // declined
	public static final String RESPONSE_STATUS_TENTATIVE = "TE"; // tentative
	public static final String RESPONSE_STATUS_ACCEPTED = "AC"; // accepted
	//public static final String RESPONSE_STATUS_NONE = "none"; // Synonym of needsAction
	//public static final String RESPONSE_STATUS_REFUSED = "refused"; // Synonym of declined
	
	protected String attendeeId;
	protected String recipient;
	protected String recipientType;
	protected String recipientRole;
	protected String responseStatus;
	protected Boolean notify;
	
	public EventAttendee() {}

	public String getAttendeeId() {
		return attendeeId;
	}

	public void setAttendeeId(String value) {
		attendeeId = value;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String value) {
		recipient = value;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	
	public String getRecipientRole() {
		return recipientRole;
	}

	public void setRecipientRole(String recipientRole) {
		this.recipientRole = recipientRole;
	}

	public String getResponseStatus() {
		return (responseStatus == null) ? RESPONSE_STATUS_NEEDSACTION : responseStatus;
	}

	public void setResponseStatus(String value) {
		responseStatus = (value == null) ? RESPONSE_STATUS_NEEDSACTION : value;
	}

	public Boolean getNotify() {
		return notify;
	}

	public void setNotify(Boolean value) {
		notify = value;
	}
	
	public boolean isResource() {
		return StringUtils.equals(getRecipientType(), RECIPIENT_TYPE_RESOURCE);
	}
	
	public boolean hasEmailRecipient() {
		return getRecipientAddress() != null;
	}
	
	public InternetAddress getRecipientInternetAddress() {
		return InternetAddressUtils.toInternetAddress(getRecipient());
	}
	
	public String getRecipientAddress() {
		InternetAddress ia = getRecipientInternetAddress();
		return (ia != null) ? ia.getAddress() : null;
	}
	
	public String getRecipientCN() {
		InternetAddress ia = getRecipientInternetAddress();
		return (ia != null) ? ia.getPersonal() : null;
	}
	
	@Deprecated
	public String getAddress() throws AddressException {
		return new InternetAddress(recipient).getAddress();
	}
	
	@Deprecated
	public String getCN() throws AddressException {
		return new InternetAddress(recipient).getPersonal();
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getAttendeeId())
			.append(getRecipient())
			.append(getRecipientType())
			.append(getRecipientRole())
			.append(getResponseStatus())
			.append(getNotify())
			.toHashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof EventAttendee == false) return false;
		if(this == obj) return true;
		final EventAttendee otherObject = (EventAttendee) obj;
		return new EqualsBuilder()
			.append(getAttendeeId(), otherObject.getAttendeeId())
			.isEquals();
	}
	
	public static class List extends ArrayList<EventAttendee> {
		public List() {
			super();
		}
	}
}
