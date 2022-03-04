/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Calendar extends org.jooq.impl.SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar</code>
     */
    public static final Calendar CALENDAR = new Calendar();

    /**
     * The table <code>calendar.calendar_props</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.CalendarProps CALENDAR_PROPS = com.sonicle.webtop.calendar.jooq.tables.CalendarProps.CALENDAR_PROPS;

    /**
     * The table <code>calendar.calendars</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.Calendars CALENDARS = com.sonicle.webtop.calendar.jooq.tables.Calendars.CALENDARS;

    /**
     * The table <code>calendar.events</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.Events EVENTS = com.sonicle.webtop.calendar.jooq.tables.Events.EVENTS;

    /**
     * The table <code>calendar.events_attachments</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsAttachments EVENTS_ATTACHMENTS = com.sonicle.webtop.calendar.jooq.tables.EventsAttachments.EVENTS_ATTACHMENTS;

    /**
     * The table <code>calendar.events_attachments_data</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsAttachmentsData EVENTS_ATTACHMENTS_DATA = com.sonicle.webtop.calendar.jooq.tables.EventsAttachmentsData.EVENTS_ATTACHMENTS_DATA;

    /**
     * The table <code>calendar.events_attendees</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsAttendees EVENTS_ATTENDEES = com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES;

    /**
     * The table <code>calendar.events_custom_values</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsCustomValues EVENTS_CUSTOM_VALUES = com.sonicle.webtop.calendar.jooq.tables.EventsCustomValues.EVENTS_CUSTOM_VALUES;

    /**
     * The table <code>calendar.events_icalendars</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsIcalendars EVENTS_ICALENDARS = com.sonicle.webtop.calendar.jooq.tables.EventsIcalendars.EVENTS_ICALENDARS;

    /**
     * The table <code>calendar.events_tags</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.EventsTags EVENTS_TAGS = com.sonicle.webtop.calendar.jooq.tables.EventsTags.EVENTS_TAGS;

    /**
     * The table <code>calendar.recurrences</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.Recurrences RECURRENCES = com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES;

    /**
     * The table <code>calendar.recurrences_broken</code>.
     */
    public final com.sonicle.webtop.calendar.jooq.tables.RecurrencesBroken RECURRENCES_BROKEN = com.sonicle.webtop.calendar.jooq.tables.RecurrencesBroken.RECURRENCES_BROKEN;

    /**
     * No further instances allowed
     */
    private Calendar() {
        super("calendar", null);
    }


    @java.lang.Override
    public org.jooq.Catalog getCatalog() {
        return com.sonicle.webtop.calendar.jooq.DefaultCatalog.DEFAULT_CATALOG;
    }

    @java.lang.Override
    public final java.util.List<org.jooq.Sequence<?>> getSequences() {
        return java.util.Arrays.<org.jooq.Sequence<?>>asList(
            com.sonicle.webtop.calendar.jooq.Sequences.SEQ_CALENDARS,
            com.sonicle.webtop.calendar.jooq.Sequences.SEQ_EVENTS,
            com.sonicle.webtop.calendar.jooq.Sequences.SEQ_RECURRENCES);
    }

    @java.lang.Override
    public final java.util.List<org.jooq.Table<?>> getTables() {
        return java.util.Arrays.<org.jooq.Table<?>>asList(
            com.sonicle.webtop.calendar.jooq.tables.CalendarProps.CALENDAR_PROPS,
            com.sonicle.webtop.calendar.jooq.tables.Calendars.CALENDARS,
            com.sonicle.webtop.calendar.jooq.tables.Events.EVENTS,
            com.sonicle.webtop.calendar.jooq.tables.EventsAttachments.EVENTS_ATTACHMENTS,
            com.sonicle.webtop.calendar.jooq.tables.EventsAttachmentsData.EVENTS_ATTACHMENTS_DATA,
            com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES,
            com.sonicle.webtop.calendar.jooq.tables.EventsCustomValues.EVENTS_CUSTOM_VALUES,
            com.sonicle.webtop.calendar.jooq.tables.EventsIcalendars.EVENTS_ICALENDARS,
            com.sonicle.webtop.calendar.jooq.tables.EventsTags.EVENTS_TAGS,
            com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES,
            com.sonicle.webtop.calendar.jooq.tables.RecurrencesBroken.RECURRENCES_BROKEN);
    }
}