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
package com.sonicle.webtop.calendar.io;

import com.sonicle.webtop.calendar.model.Event;
import jakarta.mail.internet.InternetAddress;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.supercsv.io.CsvListWriter;

/**
 *
 * @author gabriele.bulfon
 */
public class CSVOutput {
	
	DateFormat df = new SimpleDateFormat("yyyyMMdd");
	
	String headers[] = {
		"CalendarId",
		"Title",
		"Description",
		"StartDate",
		"EndDate",
		"Timezone",
		"Location",
		"Organizer",
		"OrganizerAddress",
		"OrganizerCN",
		"OrganizerInternetAddress",
		"AllDay",
		"RecurrenceRule",
		"RecurrenceStartDate"
	};
	
	public void writeHeader(CsvListWriter wr) throws IOException {
		wr.write(headers);
	}
	
	public void writeContact(Event e, String categoryName, CsvListWriter wr) throws IOException {
		wr.write(
			new String[] {
				n2b(e.getCalendarId()),
				n2b(e.getTitle()),
				n2b(e.getDescription()),
				d2s(e.getStartDate()),
				d2s(e.getEndDate()),
				n2b(e.getTimezone()),
				n2b(e.getLocation()),
				n2b(e.getOrganizer()),
				n2b(e.getOrganizerAddress()),
				n2b(e.getOrganizerCN()),
				n2b(e.getOrganizerInternetAddress()),
				e.getAllDay()?"Y":"N",
				n2b(e.getRecurrenceRule()),
				d2s(e.getRecurrenceStartDate())
			}
		);
	}
	
	private String n2b(String s) {
		return StringUtils.defaultIfBlank(s, "");
	}
	
	private String n2b(Integer i) {
		if (i!=null) return i.toString();
		return "";
	}
	
	private String n2b(InternetAddress ia) {
		if (ia!=null) return ia.toString();
		return "";
	}
	
	private String d2s(DateTime d) {
		if (d!=null) return df.format(d.toDate());
		return "";
	}
	
	private String d2s(LocalDate d) {
		if (d!=null) return df.format(d.toDate());
		return "";
	}
}
