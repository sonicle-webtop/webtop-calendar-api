/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EventsTags implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.String eventId;
    private java.lang.String tagId;

    public EventsTags() {}

    public EventsTags(EventsTags value) {
        this.eventId = value.eventId;
        this.tagId = value.tagId;
    }

    public EventsTags(
        java.lang.String eventId,
        java.lang.String tagId
    ) {
        this.eventId = eventId;
        this.tagId = tagId;
    }

    /**
     * Getter for <code>calendar.events_tags.event_id</code>.
     */
    public java.lang.String getEventId() {
        return this.eventId;
    }

    /**
     * Setter for <code>calendar.events_tags.event_id</code>.
     */
    public void setEventId(java.lang.String eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter for <code>calendar.events_tags.tag_id</code>.
     */
    public java.lang.String getTagId() {
        return this.tagId;
    }

    /**
     * Setter for <code>calendar.events_tags.tag_id</code>.
     */
    public void setTagId(java.lang.String tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("EventsTags (");

        sb.append(eventId);
        sb.append(", ").append(tagId);

        sb.append(")");
        return sb.toString();
    }
}
