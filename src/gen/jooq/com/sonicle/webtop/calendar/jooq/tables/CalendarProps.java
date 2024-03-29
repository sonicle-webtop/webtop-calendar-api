/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CalendarProps extends org.jooq.impl.TableImpl<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>calendar.calendar_props</code>
     */
    public static final CalendarProps CALENDAR_PROPS = new CalendarProps();

    /**
     * The class holding records for this type
     */
    @java.lang.Override
    public java.lang.Class<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> getRecordType() {
        return com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord.class;
    }

    /**
     * The column <code>calendar.calendar_props.domain_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.String> DOMAIN_ID = createField(org.jooq.impl.DSL.name("domain_id"), org.jooq.impl.SQLDataType.VARCHAR(20).nullable(false), this, "");

    /**
     * The column <code>calendar.calendar_props.user_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.String> USER_ID = createField(org.jooq.impl.DSL.name("user_id"), org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "");

    /**
     * The column <code>calendar.calendar_props.calendar_id</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.Integer> CALENDAR_ID = createField(org.jooq.impl.DSL.name("calendar_id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>calendar.calendar_props.hidden</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.Boolean> HIDDEN = createField(org.jooq.impl.DSL.name("hidden"), org.jooq.impl.SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>calendar.calendar_props.color</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.String> COLOR = createField(org.jooq.impl.DSL.name("color"), org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * The column <code>calendar.calendar_props.sync</code>.
     */
    public final org.jooq.TableField<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord, java.lang.String> SYNC = createField(org.jooq.impl.DSL.name("sync"), org.jooq.impl.SQLDataType.VARCHAR(1), this, "");

    private CalendarProps(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> aliased) {
        this(alias, aliased, null);
    }

    private CalendarProps(org.jooq.Name alias, org.jooq.Table<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> aliased, org.jooq.Field<?>[] parameters) {
        super(alias, null, aliased, parameters, org.jooq.impl.DSL.comment(""), org.jooq.TableOptions.table());
    }

    /**
     * Create an aliased <code>calendar.calendar_props</code> table reference
     */
    public CalendarProps(java.lang.String alias) {
        this(org.jooq.impl.DSL.name(alias), CALENDAR_PROPS);
    }

    /**
     * Create an aliased <code>calendar.calendar_props</code> table reference
     */
    public CalendarProps(org.jooq.Name alias) {
        this(alias, CALENDAR_PROPS);
    }

    /**
     * Create a <code>calendar.calendar_props</code> table reference
     */
    public CalendarProps() {
        this(org.jooq.impl.DSL.name("calendar_props"), null);
    }

    public <O extends org.jooq.Record> CalendarProps(org.jooq.Table<O> child, org.jooq.ForeignKey<O, com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> key) {
        super(child, key, CALENDAR_PROPS);
    }

    @java.lang.Override
    public org.jooq.Schema getSchema() {
        return com.sonicle.webtop.calendar.jooq.Calendar.CALENDAR;
    }

    @java.lang.Override
    public java.util.List<org.jooq.Index> getIndexes() {
        return java.util.Arrays.<org.jooq.Index>asList(com.sonicle.webtop.calendar.jooq.Indexes.CALENDAR_PROPS_AK1);
    }

    @java.lang.Override
    public org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord> getPrimaryKey() {
        return com.sonicle.webtop.calendar.jooq.Keys.CALENDAR_PROPS_PKEY;
    }

    @java.lang.Override
    public java.util.List<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord>> getKeys() {
        return java.util.Arrays.<org.jooq.UniqueKey<com.sonicle.webtop.calendar.jooq.tables.records.CalendarPropsRecord>>asList(com.sonicle.webtop.calendar.jooq.Keys.CALENDAR_PROPS_PKEY);
    }

    @java.lang.Override
    public CalendarProps as(java.lang.String alias) {
        return new CalendarProps(org.jooq.impl.DSL.name(alias), this);
    }

    @java.lang.Override
    public CalendarProps as(org.jooq.Name alias) {
        return new CalendarProps(alias, this);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public CalendarProps rename(java.lang.String name) {
        return new CalendarProps(org.jooq.impl.DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @java.lang.Override
    public CalendarProps rename(org.jooq.Name name) {
        return new CalendarProps(name, null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row6<java.lang.String, java.lang.String, java.lang.Integer, java.lang.Boolean, java.lang.String, java.lang.String> fieldsRow() {
        return (org.jooq.Row6) super.fieldsRow();
    }
}
