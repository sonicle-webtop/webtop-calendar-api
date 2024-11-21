/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RecurrencesBroken implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.String        eventId;
    private java.lang.Integer       recurrenceId;
    private org.joda.time.LocalDate eventDate;
    private java.lang.String        newEventId;

    public RecurrencesBroken() {}

    public RecurrencesBroken(RecurrencesBroken value) {
        this.eventId = value.eventId;
        this.recurrenceId = value.recurrenceId;
        this.eventDate = value.eventDate;
        this.newEventId = value.newEventId;
    }

    public RecurrencesBroken(
        java.lang.String        eventId,
        java.lang.Integer       recurrenceId,
        org.joda.time.LocalDate eventDate,
        java.lang.String        newEventId
    ) {
        this.eventId = eventId;
        this.recurrenceId = recurrenceId;
        this.eventDate = eventDate;
        this.newEventId = newEventId;
    }

    /**
     * Getter for <code>calendar.recurrences_broken.event_id</code>.
     */
    public java.lang.String getEventId() {
        return this.eventId;
    }

    /**
     * Setter for <code>calendar.recurrences_broken.event_id</code>.
     */
    public void setEventId(java.lang.String eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter for <code>calendar.recurrences_broken.recurrence_id</code>.
     */
    public java.lang.Integer getRecurrenceId() {
        return this.recurrenceId;
    }

    /**
     * Setter for <code>calendar.recurrences_broken.recurrence_id</code>.
     */
    public void setRecurrenceId(java.lang.Integer recurrenceId) {
        this.recurrenceId = recurrenceId;
    }

    /**
     * Getter for <code>calendar.recurrences_broken.event_date</code>.
     */
    public org.joda.time.LocalDate getEventDate() {
        return this.eventDate;
    }

    /**
     * Setter for <code>calendar.recurrences_broken.event_date</code>.
     */
    public void setEventDate(org.joda.time.LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * Getter for <code>calendar.recurrences_broken.new_event_id</code>.
     */
    public java.lang.String getNewEventId() {
        return this.newEventId;
    }

    /**
     * Setter for <code>calendar.recurrences_broken.new_event_id</code>.
     */
    public void setNewEventId(java.lang.String newEventId) {
        this.newEventId = newEventId;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("RecurrencesBroken (");

        sb.append(eventId);
        sb.append(", ").append(recurrenceId);
        sb.append(", ").append(eventDate);
        sb.append(", ").append(newEventId);

        sb.append(")");
        return sb.toString();
    }
}
