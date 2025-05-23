/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsIcalendars extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.events_icalendars</code>
     */
    public static final EventsIcalendars EVENTS_ICALENDARS = new EventsIcalendars();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord.class;
    }

    /**
     * The column <code>calendar.events_icalendars.event_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord, java.lang.String> EVENT_ID = createField(org.jooq.impl.DSL.name("event_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false), this, "");

    /**
     * The column <code>calendar.events_icalendars.raw_data</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord, java.lang.String> RAW_DATA = createField(org.jooq.impl.DSL.name("raw_data"), org.jooq.impl.SQLDataType.CLOB, this, "");

    private EventsIcalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> aliased) {
        this(alias, aliased, null);
    }

    private EventsIcalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.events_icalendars</code> table reference
     */
    public EventsIcalendars(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), EVENTS_ICALENDARS);
    }

    /**
     * Create an aliased <code>calendar.events_icalendars</code> table reference
     */
    public EventsIcalendars(org.jooq.Name alias) {
        this(alias, EVENTS_ICALENDARS);
    }

    /**
     * Create a <code>calendar.events_icalendars</code> table reference
     */
    public EventsIcalendars() {
        this(org.jooq.impl.DSL.name("events_icalendars"), null);
    }

    public <O extends org.jooq.Record> EventsIcalendars(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> key) {
        super(child, key, EVENTS_ICALENDARS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ICALENDARS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ICALENDARS_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsIcalendarsRecord, ?>>asList(com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ICALENDARS__EVENTS_ICALENDARS_EVENT_ID_FKEY);
    }

    private transient com.sonicle.webtop.calendar.jooq.tables.Events _events;

    public com.sonicle.webtop.calendar.jooq.tables.Events events() {
        if (_events == null)
            _events = new com.sonicle.webtop.calendar.jooq.tables.Events(this, com.sonicle.webtop.calendar.jooq.Keys.EVENTS_ICALENDARS__EVENTS_ICALENDARS_EVENT_ID_FKEY);

        return _events;
    }

    @java.lang.Override
    public EventsIcalendars as(java.lang.String alias) {
        return new EventsIcalendars(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public EventsIcalendars as(org.jooq.Name alias) {
        return new EventsIcalendars(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public EventsIcalendars rename(java.lang.String name) {
        return new EventsIcalendars(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public EventsIcalendars rename(org.jooq.Name name) {
        return new EventsIcalendars(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row2<java.lang.String, java.lang.String> fieldsRow() {
        return (org.jooq.Row2) super.fieldsRow();
    }
}
