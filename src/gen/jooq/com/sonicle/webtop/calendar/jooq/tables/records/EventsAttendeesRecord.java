/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.records;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsAttendeesRecord extends org.jooq.impl.UpdatableRecordImpl<EventsAttendeesRecord> implements org.jooq.Record7<java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>calendar.events_attendees.attendee_id</code>.
     */
    public void setAttendeeId(java.lang.String value) {
        set(0, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.attendee_id</code>.
     */
    public java.lang.String getAttendeeId() {
        return (java.lang.String) get(0);
    }

    /**
     * Setter for <code>calendar.events_attendees.event_id</code>.
     */
    public void setEventId(java.lang.Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.event_id</code>.
     */
    public java.lang.Integer getEventId() {
        return (java.lang.Integer) get(1);
    }

    /**
     * Setter for <code>calendar.events_attendees.recipient</code>.
     */
    public void setRecipient(java.lang.String value) {
        set(2, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.recipient</code>.
     */
    public java.lang.String getRecipient() {
        return (java.lang.String) get(2);
    }

    /**
     * Setter for <code>calendar.events_attendees.recipient_type</code>.
     */
    public void setRecipientType(java.lang.String value) {
        set(3, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.recipient_type</code>.
     */
    public java.lang.String getRecipientType() {
        return (java.lang.String) get(3);
    }

    /**
     * Setter for <code>calendar.events_attendees.recipient_role</code>.
     */
    public void setRecipientRole(java.lang.String value) {
        set(4, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.recipient_role</code>.
     */
    public java.lang.String getRecipientRole() {
        return (java.lang.String) get(4);
    }

    /**
     * Setter for <code>calendar.events_attendees.response_status</code>.
     */
    public void setResponseStatus(java.lang.String value) {
        set(5, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.response_status</code>.
     */
    public java.lang.String getResponseStatus() {
        return (java.lang.String) get(5);
    }

    /**
     * Setter for <code>calendar.events_attendees.notify</code>.
     */
    public void setNotify(java.lang.Boolean value) {
        set(6, value);
    }

    /**
     * Getter for <code>calendar.events_attendees.notify</code>.
     */
    public java.lang.Boolean getNotify() {
        return (java.lang.Boolean) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Record1<java.lang.String> key() {
        return (org.jooq.Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean> fieldsRow() {
        return (org.jooq.Row7) super.fieldsRow();
    }

    @java.lang.Override
    public org.jooq.Row7<java.lang.String, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean> valuesRow() {
        return (org.jooq.Row7) super.valuesRow();
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field1() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.ATTENDEE_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field2() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.EVENT_ID;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field3() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.RECIPIENT;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field4() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.RECIPIENT_TYPE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field5() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.RECIPIENT_ROLE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field6() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.RESPONSE_STATUS;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field7() {
        return com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES.NOTIFY;
    }

    @java.lang.Override
    public java.lang.String component1() {
        return getAttendeeId();
    }

    @java.lang.Override
    public java.lang.Integer component2() {
        return getEventId();
    }

    @java.lang.Override
    public java.lang.String component3() {
        return getRecipient();
    }

    @java.lang.Override
    public java.lang.String component4() {
        return getRecipientType();
    }

    @java.lang.Override
    public java.lang.String component5() {
        return getRecipientRole();
    }

    @java.lang.Override
    public java.lang.String component6() {
        return getResponseStatus();
    }

    @java.lang.Override
    public java.lang.Boolean component7() {
        return getNotify();
    }

    @java.lang.Override
    public java.lang.String value1() {
        return getAttendeeId();
    }

    @java.lang.Override
    public java.lang.Integer value2() {
        return getEventId();
    }

    @java.lang.Override
    public java.lang.String value3() {
        return getRecipient();
    }

    @java.lang.Override
    public java.lang.String value4() {
        return getRecipientType();
    }

    @java.lang.Override
    public java.lang.String value5() {
        return getRecipientRole();
    }

    @java.lang.Override
    public java.lang.String value6() {
        return getResponseStatus();
    }

    @java.lang.Override
    public java.lang.Boolean value7() {
        return getNotify();
    }

    @java.lang.Override
    public EventsAttendeesRecord value1(java.lang.String value) {
        setAttendeeId(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value2(java.lang.Integer value) {
        setEventId(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value3(java.lang.String value) {
        setRecipient(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value4(java.lang.String value) {
        setRecipientType(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value5(java.lang.String value) {
        setRecipientRole(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value6(java.lang.String value) {
        setResponseStatus(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord value7(java.lang.Boolean value) {
        setNotify(value);
        return this;
    }

    @java.lang.Override
    public EventsAttendeesRecord values(java.lang.String value1, java.lang.Integer value2, java.lang.String value3, java.lang.String value4, java.lang.String value5, java.lang.String value6, java.lang.Boolean value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached EventsAttendeesRecord
     */
    public EventsAttendeesRecord() {
        super(com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES);
    }

    /**
     * Create a detached, initialised EventsAttendeesRecord
     */
    public EventsAttendeesRecord(java.lang.String attendeeId, java.lang.Integer eventId, java.lang.String recipient, java.lang.String recipientType, java.lang.String recipientRole, java.lang.String responseStatus, java.lang.Boolean notify) {
        super(com.sonicle.webtop.calendar.jooq.tables.EventsAttendees.EVENTS_ATTENDEES);

        setAttendeeId(attendeeId);
        setEventId(eventId);
        setRecipient(recipient);
        setRecipientType(recipientType);
        setRecipientRole(recipientRole);
        setResponseStatus(responseStatus);
        setNotify(notify);
    }
}
