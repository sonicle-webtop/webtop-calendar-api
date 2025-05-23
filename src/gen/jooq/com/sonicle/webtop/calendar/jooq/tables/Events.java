/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Events extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.events</code>
     */
    public static final Events EVENTS = new Events();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord.class;
    }

    /**
     * The column <code>calendar.events.event_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> EVENT_ID = createField(org.jooq.impl.DSL.name("event_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('calendar.seq_events'::regclass)", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>calendar.events.calendar_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> CALENDAR_ID = createField(org.jooq.impl.DSL.name("calendar_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>calendar.events.revision_status</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> REVISION_STATUS = createField(org.jooq.impl.DSL.name("revision_status"), org.jooq.impl.SQLDataType.VARCHAR(1).nullable(false), this, "");

    /**
     * The column <code>calendar.events.revision_timestamp</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, org.joda.time.DateTime> REVISION_TIMESTAMP = createField(org.jooq.impl.DSL.name("revision_timestamp"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.events.revision_sequence</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> REVISION_SEQUENCE = createField(org.jooq.impl.DSL.name("revision_sequence"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>calendar.events.creation_timestamp</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, org.joda.time.DateTime> CREATION_TIMESTAMP = createField(org.jooq.impl.DSL.name("creation_timestamp"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE)), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.events.public_uid</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> PUBLIC_UID = createField(org.jooq.impl.DSL.name("public_uid"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>calendar.events.recurrence_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> RECURRENCE_ID = createField(org.jooq.impl.DSL.name("recurrence_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>calendar.events.read_only</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Boolean> READ_ONLY = createField(org.jooq.impl.DSL.name("read_only"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>calendar.events.start_date</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, org.joda.time.DateTime> START_DATE = createField(org.jooq.impl.DSL.name("start_date"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.events.end_date</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, org.joda.time.DateTime> END_DATE = createField(org.jooq.impl.DSL.name("end_date"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6).nullable(false), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.events.timezone</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> TIMEZONE = createField(org.jooq.impl.DSL.name("timezone"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>calendar.events.all_day</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Boolean> ALL_DAY = createField(org.jooq.impl.DSL.name("all_day"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>calendar.events.organizer</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> ORGANIZER = createField(org.jooq.impl.DSL.name("organizer"), org.jooq.impl.SQLDataType.VARCHAR(650), this, "");

    /**
     * The column <code>calendar.events.title</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> TITLE = createField(org.jooq.impl.DSL.name("title"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>calendar.events.description</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> DESCRIPTION = createField(org.jooq.impl.DSL.name("description"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>calendar.events.location</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> LOCATION = createField(org.jooq.impl.DSL.name("location"), org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>calendar.events.is_private</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Boolean> IS_PRIVATE = createField(org.jooq.impl.DSL.name("is_private"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>calendar.events.busy</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Boolean> BUSY = createField(org.jooq.impl.DSL.name("busy"), org.jooq.impl.SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>calendar.events.reminder</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> REMINDER = createField(org.jooq.impl.DSL.name("reminder"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>calendar.events.reminded_on</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, org.joda.time.DateTime> REMINDED_ON = createField(org.jooq.impl.DSL.name("reminded_on"), org.jooq.impl.SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "", new com.sonicle.jooq.jsr310.OffsetDateTimeJodaConverter());

    /**
     * The column <code>calendar.events.href</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> HREF = createField(org.jooq.impl.DSL.name("href"), org.jooq.impl.SQLDataType.VARCHAR(2048), this, "");

    /**
     * The column <code>calendar.events.etag</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> ETAG = createField(org.jooq.impl.DSL.name("etag"), org.jooq.impl.SQLDataType.VARCHAR(2048), this, "");

    /**
     * The column <code>calendar.events.activity_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> ACTIVITY_ID = createField(org.jooq.impl.DSL.name("activity_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>calendar.events.master_data_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> MASTER_DATA_ID = createField(org.jooq.impl.DSL.name("master_data_id"), org.jooq.impl.SQLDataType.VARCHAR(36), this, "");

    /**
     * The column <code>calendar.events.stat_master_data_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> STAT_MASTER_DATA_ID = createField(org.jooq.impl.DSL.name("stat_master_data_id"), org.jooq.impl.SQLDataType.VARCHAR(36), this, "");

    /**
     * The column <code>calendar.events.causal_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Integer> CAUSAL_ID = createField(org.jooq.impl.DSL.name("causal_id"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>calendar.events.handle_invitation</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.Boolean> HANDLE_INVITATION = createField(org.jooq.impl.DSL.name("handle_invitation"), org.jooq.impl.SQLDataType.BOOLEAN.defaultValue(org.jooq.impl.DSL.field("false", org.jooq.impl.SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>calendar.events.description_type</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord, java.lang.String> DESCRIPTION_TYPE = createField(org.jooq.impl.DSL.name("description_type"), org.jooq.impl.SQLDataType.VARCHAR(4).nullable(false).defaultValue(org.jooq.impl.DSL.field("'text'::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    private Events(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Events(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.events</code> table reference
     */
    public Events(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), EVENTS);
    }

    /**
     * Create an aliased <code>calendar.events</code> table reference
     */
    public Events(org.jooq.Name alias) {
        this(alias, EVENTS);
    }

    /**
     * Create a <code>calendar.events</code> table reference
     */
    public Events() {
        this(org.jooq.impl.DSL.name("events"), null);
    }

    public <O extends org.jooq.Record> Events(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> key) {
        super(child, key, EVENTS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_AK1, com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_AK2, com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_AK3, com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_AK4, com.sonicle.webtop.calendar.jooq.Indexes.EVENTS_AK99);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.EVENTS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.EventsRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.EVENTS_PKEY);
    }

    @java.lang.Override
    public Events as(java.lang.String alias) {
        return new Events(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public Events as(org.jooq.Name alias) {
        return new Events(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public Events rename(java.lang.String name) {
        return new Events(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public Events rename(org.jooq.Name name) {
        return new Events(name, null);
    }
}
