/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.pojos;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Events implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private java.lang.Integer      eventId;
    private java.lang.Integer      calendarId;
    private java.lang.String       revisionStatus;
    private org.joda.time.DateTime revisionTimestamp;
    private java.lang.Integer      revisionSequence;
    private org.joda.time.DateTime creationTimestamp;
    private java.lang.String       publicUid;
    private java.lang.Integer      recurrenceId;
    private java.lang.Boolean      readOnly;
    private org.joda.time.DateTime startDate;
    private org.joda.time.DateTime endDate;
    private java.lang.String       timezone;
    private java.lang.Boolean      allDay;
    private java.lang.String       organizer;
    private java.lang.String       title;
    private java.lang.String       description;
    private java.lang.String       location;
    private java.lang.Boolean      isPrivate;
    private java.lang.Boolean      busy;
    private java.lang.Integer      reminder;
    private org.joda.time.DateTime remindedOn;
    private java.lang.String       href;
    private java.lang.String       etag;
    private java.lang.Integer      activityId;
    private java.lang.String       masterDataId;
    private java.lang.String       statMasterDataId;
    private java.lang.Integer      causalId;
    private java.lang.Boolean      handleInvitation;

    public Events() {}

    public Events(Events value) {
        this.eventId = value.eventId;
        this.calendarId = value.calendarId;
        this.revisionStatus = value.revisionStatus;
        this.revisionTimestamp = value.revisionTimestamp;
        this.revisionSequence = value.revisionSequence;
        this.creationTimestamp = value.creationTimestamp;
        this.publicUid = value.publicUid;
        this.recurrenceId = value.recurrenceId;
        this.readOnly = value.readOnly;
        this.startDate = value.startDate;
        this.endDate = value.endDate;
        this.timezone = value.timezone;
        this.allDay = value.allDay;
        this.organizer = value.organizer;
        this.title = value.title;
        this.description = value.description;
        this.location = value.location;
        this.isPrivate = value.isPrivate;
        this.busy = value.busy;
        this.reminder = value.reminder;
        this.remindedOn = value.remindedOn;
        this.href = value.href;
        this.etag = value.etag;
        this.activityId = value.activityId;
        this.masterDataId = value.masterDataId;
        this.statMasterDataId = value.statMasterDataId;
        this.causalId = value.causalId;
        this.handleInvitation = value.handleInvitation;
    }

    public Events(
        java.lang.Integer      eventId,
        java.lang.Integer      calendarId,
        java.lang.String       revisionStatus,
        org.joda.time.DateTime revisionTimestamp,
        java.lang.Integer      revisionSequence,
        org.joda.time.DateTime creationTimestamp,
        java.lang.String       publicUid,
        java.lang.Integer      recurrenceId,
        java.lang.Boolean      readOnly,
        org.joda.time.DateTime startDate,
        org.joda.time.DateTime endDate,
        java.lang.String       timezone,
        java.lang.Boolean      allDay,
        java.lang.String       organizer,
        java.lang.String       title,
        java.lang.String       description,
        java.lang.String       location,
        java.lang.Boolean      isPrivate,
        java.lang.Boolean      busy,
        java.lang.Integer      reminder,
        org.joda.time.DateTime remindedOn,
        java.lang.String       href,
        java.lang.String       etag,
        java.lang.Integer      activityId,
        java.lang.String       masterDataId,
        java.lang.String       statMasterDataId,
        java.lang.Integer      causalId,
        java.lang.Boolean      handleInvitation
    ) {
        this.eventId = eventId;
        this.calendarId = calendarId;
        this.revisionStatus = revisionStatus;
        this.revisionTimestamp = revisionTimestamp;
        this.revisionSequence = revisionSequence;
        this.creationTimestamp = creationTimestamp;
        this.publicUid = publicUid;
        this.recurrenceId = recurrenceId;
        this.readOnly = readOnly;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timezone = timezone;
        this.allDay = allDay;
        this.organizer = organizer;
        this.title = title;
        this.description = description;
        this.location = location;
        this.isPrivate = isPrivate;
        this.busy = busy;
        this.reminder = reminder;
        this.remindedOn = remindedOn;
        this.href = href;
        this.etag = etag;
        this.activityId = activityId;
        this.masterDataId = masterDataId;
        this.statMasterDataId = statMasterDataId;
        this.causalId = causalId;
        this.handleInvitation = handleInvitation;
    }

    /**
     * Getter for <code>calendar.events.event_id</code>.
     */
    public java.lang.Integer getEventId() {
        return this.eventId;
    }

    /**
     * Setter for <code>calendar.events.event_id</code>.
     */
    public void setEventId(java.lang.Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * Getter for <code>calendar.events.calendar_id</code>.
     */
    public java.lang.Integer getCalendarId() {
        return this.calendarId;
    }

    /**
     * Setter for <code>calendar.events.calendar_id</code>.
     */
    public void setCalendarId(java.lang.Integer calendarId) {
        this.calendarId = calendarId;
    }

    /**
     * Getter for <code>calendar.events.revision_status</code>.
     */
    public java.lang.String getRevisionStatus() {
        return this.revisionStatus;
    }

    /**
     * Setter for <code>calendar.events.revision_status</code>.
     */
    public void setRevisionStatus(java.lang.String revisionStatus) {
        this.revisionStatus = revisionStatus;
    }

    /**
     * Getter for <code>calendar.events.revision_timestamp</code>.
     */
    public org.joda.time.DateTime getRevisionTimestamp() {
        return this.revisionTimestamp;
    }

    /**
     * Setter for <code>calendar.events.revision_timestamp</code>.
     */
    public void setRevisionTimestamp(org.joda.time.DateTime revisionTimestamp) {
        this.revisionTimestamp = revisionTimestamp;
    }

    /**
     * Getter for <code>calendar.events.revision_sequence</code>.
     */
    public java.lang.Integer getRevisionSequence() {
        return this.revisionSequence;
    }

    /**
     * Setter for <code>calendar.events.revision_sequence</code>.
     */
    public void setRevisionSequence(java.lang.Integer revisionSequence) {
        this.revisionSequence = revisionSequence;
    }

    /**
     * Getter for <code>calendar.events.creation_timestamp</code>.
     */
    public org.joda.time.DateTime getCreationTimestamp() {
        return this.creationTimestamp;
    }

    /**
     * Setter for <code>calendar.events.creation_timestamp</code>.
     */
    public void setCreationTimestamp(org.joda.time.DateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    /**
     * Getter for <code>calendar.events.public_uid</code>.
     */
    public java.lang.String getPublicUid() {
        return this.publicUid;
    }

    /**
     * Setter for <code>calendar.events.public_uid</code>.
     */
    public void setPublicUid(java.lang.String publicUid) {
        this.publicUid = publicUid;
    }

    /**
     * Getter for <code>calendar.events.recurrence_id</code>.
     */
    public java.lang.Integer getRecurrenceId() {
        return this.recurrenceId;
    }

    /**
     * Setter for <code>calendar.events.recurrence_id</code>.
     */
    public void setRecurrenceId(java.lang.Integer recurrenceId) {
        this.recurrenceId = recurrenceId;
    }

    /**
     * Getter for <code>calendar.events.read_only</code>.
     */
    public java.lang.Boolean getReadOnly() {
        return this.readOnly;
    }

    /**
     * Setter for <code>calendar.events.read_only</code>.
     */
    public void setReadOnly(java.lang.Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * Getter for <code>calendar.events.start_date</code>.
     */
    public org.joda.time.DateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Setter for <code>calendar.events.start_date</code>.
     */
    public void setStartDate(org.joda.time.DateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Getter for <code>calendar.events.end_date</code>.
     */
    public org.joda.time.DateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Setter for <code>calendar.events.end_date</code>.
     */
    public void setEndDate(org.joda.time.DateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for <code>calendar.events.timezone</code>.
     */
    public java.lang.String getTimezone() {
        return this.timezone;
    }

    /**
     * Setter for <code>calendar.events.timezone</code>.
     */
    public void setTimezone(java.lang.String timezone) {
        this.timezone = timezone;
    }

    /**
     * Getter for <code>calendar.events.all_day</code>.
     */
    public java.lang.Boolean getAllDay() {
        return this.allDay;
    }

    /**
     * Setter for <code>calendar.events.all_day</code>.
     */
    public void setAllDay(java.lang.Boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * Getter for <code>calendar.events.organizer</code>.
     */
    public java.lang.String getOrganizer() {
        return this.organizer;
    }

    /**
     * Setter for <code>calendar.events.organizer</code>.
     */
    public void setOrganizer(java.lang.String organizer) {
        this.organizer = organizer;
    }

    /**
     * Getter for <code>calendar.events.title</code>.
     */
    public java.lang.String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>calendar.events.title</code>.
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    /**
     * Getter for <code>calendar.events.description</code>.
     */
    public java.lang.String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>calendar.events.description</code>.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * Getter for <code>calendar.events.location</code>.
     */
    public java.lang.String getLocation() {
        return this.location;
    }

    /**
     * Setter for <code>calendar.events.location</code>.
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }

    /**
     * Getter for <code>calendar.events.is_private</code>.
     */
    public java.lang.Boolean getIsPrivate() {
        return this.isPrivate;
    }

    /**
     * Setter for <code>calendar.events.is_private</code>.
     */
    public void setIsPrivate(java.lang.Boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * Getter for <code>calendar.events.busy</code>.
     */
    public java.lang.Boolean getBusy() {
        return this.busy;
    }

    /**
     * Setter for <code>calendar.events.busy</code>.
     */
    public void setBusy(java.lang.Boolean busy) {
        this.busy = busy;
    }

    /**
     * Getter for <code>calendar.events.reminder</code>.
     */
    public java.lang.Integer getReminder() {
        return this.reminder;
    }

    /**
     * Setter for <code>calendar.events.reminder</code>.
     */
    public void setReminder(java.lang.Integer reminder) {
        this.reminder = reminder;
    }

    /**
     * Getter for <code>calendar.events.reminded_on</code>.
     */
    public org.joda.time.DateTime getRemindedOn() {
        return this.remindedOn;
    }

    /**
     * Setter for <code>calendar.events.reminded_on</code>.
     */
    public void setRemindedOn(org.joda.time.DateTime remindedOn) {
        this.remindedOn = remindedOn;
    }

    /**
     * Getter for <code>calendar.events.href</code>.
     */
    public java.lang.String getHref() {
        return this.href;
    }

    /**
     * Setter for <code>calendar.events.href</code>.
     */
    public void setHref(java.lang.String href) {
        this.href = href;
    }

    /**
     * Getter for <code>calendar.events.etag</code>.
     */
    public java.lang.String getEtag() {
        return this.etag;
    }

    /**
     * Setter for <code>calendar.events.etag</code>.
     */
    public void setEtag(java.lang.String etag) {
        this.etag = etag;
    }

    /**
     * Getter for <code>calendar.events.activity_id</code>.
     */
    public java.lang.Integer getActivityId() {
        return this.activityId;
    }

    /**
     * Setter for <code>calendar.events.activity_id</code>.
     */
    public void setActivityId(java.lang.Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * Getter for <code>calendar.events.master_data_id</code>.
     */
    public java.lang.String getMasterDataId() {
        return this.masterDataId;
    }

    /**
     * Setter for <code>calendar.events.master_data_id</code>.
     */
    public void setMasterDataId(java.lang.String masterDataId) {
        this.masterDataId = masterDataId;
    }

    /**
     * Getter for <code>calendar.events.stat_master_data_id</code>.
     */
    public java.lang.String getStatMasterDataId() {
        return this.statMasterDataId;
    }

    /**
     * Setter for <code>calendar.events.stat_master_data_id</code>.
     */
    public void setStatMasterDataId(java.lang.String statMasterDataId) {
        this.statMasterDataId = statMasterDataId;
    }

    /**
     * Getter for <code>calendar.events.causal_id</code>.
     */
    public java.lang.Integer getCausalId() {
        return this.causalId;
    }

    /**
     * Setter for <code>calendar.events.causal_id</code>.
     */
    public void setCausalId(java.lang.Integer causalId) {
        this.causalId = causalId;
    }

    /**
     * Getter for <code>calendar.events.handle_invitation</code>.
     */
    public java.lang.Boolean getHandleInvitation() {
        return this.handleInvitation;
    }

    /**
     * Setter for <code>calendar.events.handle_invitation</code>.
     */
    public void setHandleInvitation(java.lang.Boolean handleInvitation) {
        this.handleInvitation = handleInvitation;
    }

    @Override
    public String toString() {
        java.lang.StringBuilder sb = new java.lang.StringBuilder("Events (");

        sb.append(eventId);
        sb.append(", ").append(calendarId);
        sb.append(", ").append(revisionStatus);
        sb.append(", ").append(revisionTimestamp);
        sb.append(", ").append(revisionSequence);
        sb.append(", ").append(creationTimestamp);
        sb.append(", ").append(publicUid);
        sb.append(", ").append(recurrenceId);
        sb.append(", ").append(readOnly);
        sb.append(", ").append(startDate);
        sb.append(", ").append(endDate);
        sb.append(", ").append(timezone);
        sb.append(", ").append(allDay);
        sb.append(", ").append(organizer);
        sb.append(", ").append(title);
        sb.append(", ").append(description);
        sb.append(", ").append(location);
        sb.append(", ").append(isPrivate);
        sb.append(", ").append(busy);
        sb.append(", ").append(reminder);
        sb.append(", ").append(remindedOn);
        sb.append(", ").append(href);
        sb.append(", ").append(etag);
        sb.append(", ").append(activityId);
        sb.append(", ").append(masterDataId);
        sb.append(", ").append(statMasterDataId);
        sb.append(", ").append(causalId);
        sb.append(", ").append(handleInvitation);

        sb.append(")");
        return sb.toString();
    }
}
