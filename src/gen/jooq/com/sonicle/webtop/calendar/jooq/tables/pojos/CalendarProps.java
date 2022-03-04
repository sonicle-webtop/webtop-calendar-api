/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CalendarProps implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.String  domainId;
    private java.lang.String  userId;
    private java.lang.Integer calendarId;
    private java.lang.Boolean hidden;
    private java.lang.String  color;
    private java.lang.String  sync;

    public CalendarProps() {}

    public CalendarProps(CalendarProps value) {
        this.domainId = value.domainId;
        this.userId = value.userId;
        this.calendarId = value.calendarId;
        this.hidden = value.hidden;
        this.color = value.color;
        this.sync = value.sync;
    }

    public CalendarProps(
        java.lang.String  domainId,
        java.lang.String  userId,
        java.lang.Integer calendarId,
        java.lang.Boolean hidden,
        java.lang.String  color,
        java.lang.String  sync
    ) {
        this.domainId = domainId;
        this.userId = userId;
        this.calendarId = calendarId;
        this.hidden = hidden;
        this.color = color;
        this.sync = sync;
    }

    /**
     * Getter for <code>calendar.calendar_props.domain_id</code>.
     */
    public java.lang.String getDomainId() {
        return this.domainId;
    }

    /**
     * Setter for <code>calendar.calendar_props.domain_id</code>.
     */
    public void setDomainId(java.lang.String domainId) {
        this.domainId = domainId;
    }

    /**
     * Getter for <code>calendar.calendar_props.user_id</code>.
     */
    public java.lang.String getUserId() {
        return this.userId;
    }

    /**
     * Setter for <code>calendar.calendar_props.user_id</code>.
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }

    /**
     * Getter for <code>calendar.calendar_props.calendar_id</code>.
     */
    public java.lang.Integer getCalendarId() {
        return this.calendarId;
    }

    /**
     * Setter for <code>calendar.calendar_props.calendar_id</code>.
     */
    public void setCalendarId(java.lang.Integer calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * Getter for <code>calendar.calendar_props.hidden</code>.
     */
    public java.lang.Boolean getHidden() {
        return this.hidden;
    }

    /**
     * Setter for <code>calendar.calendar_props.hidden</code>.
     */
    public void setHidden(java.lang.Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * Getter for <code>calendar.calendar_props.color</code>.
     */
    public java.lang.String getColor() {
        return this.color;
    }

    /**
     * Setter for <code>calendar.calendar_props.color</code>.
     */
    public void setColor(java.lang.String color) {
        this.color = color;
    }

    /**
     * Getter for <code>calendar.calendar_props.sync</code>.
     */
    public java.lang.String getSync() {
        return this.sync;
    }

    /**
     * Setter for <code>calendar.calendar_props.sync</code>.
     */
    public void setSync(java.lang.String sync) {
        this.sync = sync;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("CalendarProps (");

        sb.append(domainId);
        sb.append(", ").append(userId);
        sb.append(", ").append(calendarId);
        sb.append(", ").append(hidden);
        sb.append(", ").append(color);
        sb.append(", ").append(sync);

        sb.append(")");
        return sb.toString();
    }
}