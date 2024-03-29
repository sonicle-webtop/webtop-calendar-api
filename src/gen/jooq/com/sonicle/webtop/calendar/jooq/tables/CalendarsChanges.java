/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CalendarsChanges extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.calendars_changes</code>
     */
    public static final CalendarsChanges CALENDARS_CHANGES = new CalendarsChanges();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord.class;
    }

    /**
     * The column <code>calendar.calendars_changes.id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, java.lang.Long> ID = createField(org.jooq.impl.DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('calendar.seq_calendars_changes'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>calendar.calendars_changes.calendar_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, java.lang.Integer> CALENDAR_ID = createField(org.jooq.impl.DSL.name("calendar_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>calendar.calendars_changes.event_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, java.lang.Integer> EVENT_ID = createField(org.jooq.impl.DSL.name("event_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>calendar.calendars_changes.timestamp</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, org.joda.time.DateTime> TIMESTAMP = createField(org.jooq.impl.DSL.name("timestamp"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.calendars_changes.operation</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, java.lang.String> OPERATION = createField(org.jooq.impl.DSL.name("operation"), org.jooq.impl.SQLDataType.CHAR(1).nullable(false), this, "");

    private CalendarsChanges(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> aliased) {
        this(alias, aliased, null);
    }

    private CalendarsChanges(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.calendars_changes</code> table reference
     */
    public CalendarsChanges(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), CALENDARS_CHANGES);
    }

    /**
     * Create an aliased <code>calendar.calendars_changes</code> table reference
     */
    public CalendarsChanges(org.jooq.Name alias) {
        this(alias, CALENDARS_CHANGES);
    }

    /**
     * Create a <code>calendar.calendars_changes</code> table reference
     */
    public CalendarsChanges() {
        this(org.jooq.impl.DSL.name("calendars_changes"), null);
    }

    public <O extends org.jooq.Record> CalendarsChanges(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> key) {
        super(child, key, CALENDARS_CHANGES);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.calendar.jooq.Indexes.CALENDARS_CHANGES_AK1, com.sonicle.webtop.calendar.jooq.Indexes.CALENDARS_CHANGES_AK2);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.CALENDARS_CHANGES_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.CALENDARS_CHANGES_PKEY);
    }

    @java.lang.Override
    public java.util.List<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, ?>> getReferences() {
        return java.util.Arrays.<org.jooq.ForeignKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarsChangesRecord, ?>>asList(com.sonicle.webtop.calendar.jooq.Keys.CALENDARS_CHANGES__CALENDARS_CHANGES_CALENDAR_ID_FKEY);
    }

    private transient com.sonicle.webtop.calendar.jooq.tables.Calendars _calendars;

    public com.sonicle.webtop.calendar.jooq.tables.Calendars calendars() {
        if (_calendars == null)
            _calendars = new com.sonicle.webtop.calendar.jooq.tables.Calendars(this, com.sonicle.webtop.calendar.jooq.Keys.CALENDARS_CHANGES__CALENDARS_CHANGES_CALENDAR_ID_FKEY);

        return _calendars;
    }

    @java.lang.Override
    public CalendarsChanges as(java.lang.String alias) {
        return new CalendarsChanges(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public CalendarsChanges as(org.jooq.Name alias) {
        return new CalendarsChanges(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public CalendarsChanges rename(java.lang.String name) {
        return new CalendarsChanges(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public CalendarsChanges rename(org.jooq.Name name) {
        return new CalendarsChanges(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row5<java.lang.Long, java.lang.Integer, java.lang.Integer, org.joda.time.DateTime, java.lang.String> fieldsRow() {
        return (org.jooq.Row5) super.fieldsRow();
    }
}
