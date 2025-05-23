/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsAttendees extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.events_attendees</code>
     */
    public static final EventsAttendees EVENTS_ATTENDEES = new EventsAttendees();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord.class;
    }

    /**
     * The column <code>calendar.events_attendees.attendee_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> ATTENDEE_ID = createField(org.jooq.impl.DSL.name("attendee_id"), org.jooq.impl.SQLDataType.VARCHAR(36).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.event_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> EVENT_ID = createField(org.jooq.impl.DSL.name("event_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.recipient</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> RECIPIENT = createField(org.jooq.impl.DSL.name("recipient"), org.jooq.impl.SQLDataType.VARCHAR(320).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.recipient_type</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> RECIPIENT_TYPE = createField(org.jooq.impl.DSL.name("recipient_type"), org.jooq.impl.SQLDataType.VARCHAR(3).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.recipient_role</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> RECIPIENT_ROLE = createField(org.jooq.impl.DSL.name("recipient_role"), org.jooq.impl.SQLDataType.VARCHAR(3).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.response_status</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.String> RESPONSE_STATUS = createField(org.jooq.impl.DSL.name("response_status"), org.jooq.impl.SQLDataType.VARCHAR(2).nullable(false), this, "");

    /**
     * The column <code>calendar.events_attendees.notify</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, java.lang.Boolean> NOTIFY = createField(org.jooq.impl.DSL.name("notify"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    private EventsAttendees(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> aliased) {
        this(alias, aliased, null);
    }

    private EventsAttendees(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.events_attendees</code> table reference
     */
    public EventsAttendees(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), EVENTS_ATTENDEES);
    }

    /**
     * Create an aliased <code>calendar.events_attendees</code> table reference
     */
    public EventsAttendees(org.jooq.Name alias) {
        this(alias, EVENTS_ATTENDEES);
    }

    /**
     * Create a <code>calendar.events_attendees</code> table reference
     */
    public EventsAttendees() {
        this(org.jooq.impl.DSL.name("events_attendees"), null);
    }

    public <O extends org.jooq.Record> EventsAttendees(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> key) {
        super(child, key, EVENTS_ATTENDEES);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_ATTENDEE_AK1);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ATTENDEES_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ATTENDEES_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsAttendeesRecord, ?>>asList(com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ATTENDEES__EVENTS_ATTENDEES_EVENT_ID_FKEY);
    }

    private transient com.sonicle.webtop.calendar.jooq.tables.Events _events;

    public com.sonicle.webtop.calendar.jooq.tables.Events events() {
        if (_events == null)
            _events = new com.sonicle.webtop.calendar.jooq.tables.Events(this, com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ATTENDEES__EVENTS_ATTENDEES_EVENT_ID_FKEY);

        return _events;
    }

    @java.lang.Override
    public EventsAttendees as(java.lang.String alias) {
        return new EventsAttendees(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public EventsAttendees as(org.jooq.Name alias) {
        return new EventsAttendees(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public EventsAttendees rename(java.lang.String name) {
        return new EventsAttendees(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public EventsAttendees rename(org.jooq.Name name) {
        return new EventsAttendees(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean> fieldsRow() {
        return (org.jooq.Row7) super.fieldsRow();
    }
}
