/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class HistoryCalendars extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.history_calendars</code>
     */
    public static final HistoryCalendars HISTORY_CALENDARS = new HistoryCalendars();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord.class;
    }

    /**
     * The column <code>calendar.history_calendars.id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, java.lang.Long> ID = createField(org.jooq.impl.DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('calendar.seq_history_calendars'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>calendar.history_calendars.domain_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, java.lang.String> DOMAIN_ID = createField(org.jooq.impl.DSL.name("domain_id"), org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>calendar.history_calendars.user_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, java.lang.String> USER_ID = createField(org.jooq.impl.DSL.name("user_id"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>calendar.history_calendars.calendar_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, java.lang.Integer> CALENDAR_ID = createField(org.jooq.impl.DSL.name("calendar_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>calendar.history_calendars.change_timestamp</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, org.joda.time.DateTime> CHANGE_TIMESTAMP = createField(org.jooq.impl.DSL.name("change_timestamp"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(org.jooq.impl.DSL.field("'1970-01-01 01:00:00+01'::timestamp with time zone", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.history_calendars.change_type</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord, java.lang.String> CHANGE_TYPE = createField(org.jooq.impl.DSL.name("change_type"), org.jooq.impl.SQLDataType.CHAR(1).nullable(false), this, "");

    private HistoryCalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> aliased) {
        this(alias, aliased, null);
    }

    private HistoryCalendars(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.history_calendars</code> table reference
     */
    public HistoryCalendars(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), HISTORY_CALENDARS);
    }

    /**
     * Create an aliased <code>calendar.history_calendars</code> table reference
     */
    public HistoryCalendars(org.jooq.Name alias) {
        this(alias, HISTORY_CALENDARS);
    }

    /**
     * Create a <code>calendar.history_calendars</code> table reference
     */
    public HistoryCalendars() {
        this(org.jooq.impl.DSL.name("history_calendars"), null);
    }

    public <O extends org.jooq.Record> HistoryCalendars(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> key) {
        super(child, key, HISTORY_CALENDARS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.calendar.jooq.Indexes.HISTORY_CALENDARS_AK1, com.sonicle.webtop.calendar.jooq.Indexes.HISTORY_CALENDARS_AK2);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.HISTORY_CALENDARS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.HistoryCalendarsRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.HISTORY_CALENDARS_PKEY);
    }

    @java.lang.Override
    public HistoryCalendars as(java.lang.String alias) {
        return new HistoryCalendars(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public HistoryCalendars as(org.jooq.Name alias) {
        return new HistoryCalendars(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public HistoryCalendars rename(java.lang.String name) {
        return new HistoryCalendars(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public HistoryCalendars rename(org.jooq.Name name) {
        return new HistoryCalendars(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row6<java.lang.Long, java.lang.String, java.lang.String, java.lang.Integer, org.joda.time.DateTime, java.lang.String> fieldsRow() {
        return (org.jooq.Row6) super.fieldsRow();
    }
}
