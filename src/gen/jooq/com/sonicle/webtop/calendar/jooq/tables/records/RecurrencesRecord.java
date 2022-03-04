/*
 * This file is generated by jOOQ.
 */
package com.sonicle.webtop.calendar.jooq.tables.records;



/**
 * This class is generated by jOOQ.
 */
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RecurrencesRecord extends org.jooq.impl.UpdatableRecordImpl<RecurrencesRecord> implements org.jooq.Record20<java.lang.Integer, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.Integer, java.lang.Boolean, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>calendar.recurrences.recurrence_id</code>.
     */
    public void setRecurrenceId(java.lang.Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>calendar.recurrences.recurrence_id</code>.
     */
    public java.lang.Integer getRecurrenceId() {
        return (java.lang.Integer) get(0);
    }

    /**
     * Setter for <code>calendar.recurrences.start_date</code>.
     */
    public void setStartDate(org.joda.time.DateTime value) {
        set(1, value);
    }

    /**
     * Getter for <code>calendar.recurrences.start_date</code>.
     */
    public org.joda.time.DateTime getStartDate() {
        return (org.joda.time.DateTime) get(1);
    }

    /**
     * Setter for <code>calendar.recurrences.until_date</code>.
     */
    public void setUntilDate(org.joda.time.DateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>calendar.recurrences.until_date</code>.
     */
    public org.joda.time.DateTime getUntilDate() {
        return (org.joda.time.DateTime) get(2);
    }

    /**
     * Setter for <code>calendar.recurrences.repeat</code>.
     */
    public void setRepeat(java.lang.Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>calendar.recurrences.repeat</code>.
     */
    public java.lang.Integer getRepeat() {
        return (java.lang.Integer) get(3);
    }

    /**
     * Setter for <code>calendar.recurrences.permanent</code>.
     */
    public void setPermanent(java.lang.Boolean value) {
        set(4, value);
    }

    /**
     * Getter for <code>calendar.recurrences.permanent</code>.
     */
    public java.lang.Boolean getPermanent() {
        return (java.lang.Boolean) get(4);
    }

    /**
     * Setter for <code>calendar.recurrences.type</code>.
     */
    public void setType(java.lang.String value) {
        set(5, value);
    }

    /**
     * Getter for <code>calendar.recurrences.type</code>.
     */
    public java.lang.String getType() {
        return (java.lang.String) get(5);
    }

    /**
     * Setter for <code>calendar.recurrences.daily_freq</code>.
     */
    public void setDailyFreq(java.lang.Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>calendar.recurrences.daily_freq</code>.
     */
    public java.lang.Integer getDailyFreq() {
        return (java.lang.Integer) get(6);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_freq</code>.
     */
    public void setWeeklyFreq(java.lang.Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_freq</code>.
     */
    public java.lang.Integer getWeeklyFreq() {
        return (java.lang.Integer) get(7);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_1</code>.
     */
    public void setWeeklyDay_1(java.lang.Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_1</code>.
     */
    public java.lang.Boolean getWeeklyDay_1() {
        return (java.lang.Boolean) get(8);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_2</code>.
     */
    public void setWeeklyDay_2(java.lang.Boolean value) {
        set(9, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_2</code>.
     */
    public java.lang.Boolean getWeeklyDay_2() {
        return (java.lang.Boolean) get(9);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_3</code>.
     */
    public void setWeeklyDay_3(java.lang.Boolean value) {
        set(10, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_3</code>.
     */
    public java.lang.Boolean getWeeklyDay_3() {
        return (java.lang.Boolean) get(10);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_4</code>.
     */
    public void setWeeklyDay_4(java.lang.Boolean value) {
        set(11, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_4</code>.
     */
    public java.lang.Boolean getWeeklyDay_4() {
        return (java.lang.Boolean) get(11);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_5</code>.
     */
    public void setWeeklyDay_5(java.lang.Boolean value) {
        set(12, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_5</code>.
     */
    public java.lang.Boolean getWeeklyDay_5() {
        return (java.lang.Boolean) get(12);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_6</code>.
     */
    public void setWeeklyDay_6(java.lang.Boolean value) {
        set(13, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_6</code>.
     */
    public java.lang.Boolean getWeeklyDay_6() {
        return (java.lang.Boolean) get(13);
    }

    /**
     * Setter for <code>calendar.recurrences.weekly_day_7</code>.
     */
    public void setWeeklyDay_7(java.lang.Boolean value) {
        set(14, value);
    }

    /**
     * Getter for <code>calendar.recurrences.weekly_day_7</code>.
     */
    public java.lang.Boolean getWeeklyDay_7() {
        return (java.lang.Boolean) get(14);
    }

    /**
     * Setter for <code>calendar.recurrences.monthly_freq</code>.
     */
    public void setMonthlyFreq(java.lang.Integer value) {
        set(15, value);
    }

    /**
     * Getter for <code>calendar.recurrences.monthly_freq</code>.
     */
    public java.lang.Integer getMonthlyFreq() {
        return (java.lang.Integer) get(15);
    }

    /**
     * Setter for <code>calendar.recurrences.monthly_day</code>.
     */
    public void setMonthlyDay(java.lang.Integer value) {
        set(16, value);
    }

    /**
     * Getter for <code>calendar.recurrences.monthly_day</code>.
     */
    public java.lang.Integer getMonthlyDay() {
        return (java.lang.Integer) get(16);
    }

    /**
     * Setter for <code>calendar.recurrences.yearly_freq</code>.
     */
    public void setYearlyFreq(java.lang.Integer value) {
        set(17, value);
    }

    /**
     * Getter for <code>calendar.recurrences.yearly_freq</code>.
     */
    public java.lang.Integer getYearlyFreq() {
        return (java.lang.Integer) get(17);
    }

    /**
     * Setter for <code>calendar.recurrences.yearly_day</code>.
     */
    public void setYearlyDay(java.lang.Integer value) {
        set(18, value);
    }

    /**
     * Getter for <code>calendar.recurrences.yearly_day</code>.
     */
    public java.lang.Integer getYearlyDay() {
        return (java.lang.Integer) get(18);
    }

    /**
     * Setter for <code>calendar.recurrences.rule</code>.
     */
    public void setRule(java.lang.String value) {
        set(19, value);
    }

    /**
     * Getter for <code>calendar.recurrences.rule</code>.
     */
    public java.lang.String getRule() {
        return (java.lang.String) get(19);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Record1<java.lang.Integer> key() {
        return (org.jooq.Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record20 type implementation
    // -------------------------------------------------------------------------

    @java.lang.Override
    public org.jooq.Row20<java.lang.Integer, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.Integer, java.lang.Boolean, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String> fieldsRow() {
        return (org.jooq.Row20) super.fieldsRow();
    }

    @java.lang.Override
    public org.jooq.Row20<java.lang.Integer, org.joda.time.DateTime, org.joda.time.DateTime, java.lang.Integer, java.lang.Boolean, java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.String> valuesRow() {
        return (org.jooq.Row20) super.valuesRow();
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field1() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.RECURRENCE_ID;
    }

    @java.lang.Override
    public org.jooq.Field<org.joda.time.DateTime> field2() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.START_DATE;
    }

    @java.lang.Override
    public org.jooq.Field<org.joda.time.DateTime> field3() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.UNTIL_DATE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field4() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.REPEAT;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field5() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.PERMANENT;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field6() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.TYPE;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field7() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.DAILY_FREQ;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field8() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_FREQ;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field9() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_1;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field10() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_2;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field11() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_3;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field12() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_4;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field13() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_5;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field14() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_6;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Boolean> field15() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.WEEKLY_DAY_7;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field16() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.MONTHLY_FREQ;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field17() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.MONTHLY_DAY;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field18() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.YEARLY_FREQ;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.Integer> field19() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.YEARLY_DAY;
    }

    @java.lang.Override
    public org.jooq.Field<java.lang.String> field20() {
        return com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES.RULE;
    }

    @java.lang.Override
    public java.lang.Integer component1() {
        return getRecurrenceId();
    }

    @java.lang.Override
    public org.joda.time.DateTime component2() {
        return getStartDate();
    }

    @java.lang.Override
    public org.joda.time.DateTime component3() {
        return getUntilDate();
    }

    @java.lang.Override
    public java.lang.Integer component4() {
        return getRepeat();
    }

    @java.lang.Override
    public java.lang.Boolean component5() {
        return getPermanent();
    }

    @java.lang.Override
    public java.lang.String component6() {
        return getType();
    }

    @java.lang.Override
    public java.lang.Integer component7() {
        return getDailyFreq();
    }

    @java.lang.Override
    public java.lang.Integer component8() {
        return getWeeklyFreq();
    }

    @java.lang.Override
    public java.lang.Boolean component9() {
        return getWeeklyDay_1();
    }

    @java.lang.Override
    public java.lang.Boolean component10() {
        return getWeeklyDay_2();
    }

    @java.lang.Override
    public java.lang.Boolean component11() {
        return getWeeklyDay_3();
    }

    @java.lang.Override
    public java.lang.Boolean component12() {
        return getWeeklyDay_4();
    }

    @java.lang.Override
    public java.lang.Boolean component13() {
        return getWeeklyDay_5();
    }

    @java.lang.Override
    public java.lang.Boolean component14() {
        return getWeeklyDay_6();
    }

    @java.lang.Override
    public java.lang.Boolean component15() {
        return getWeeklyDay_7();
    }

    @java.lang.Override
    public java.lang.Integer component16() {
        return getMonthlyFreq();
    }

    @java.lang.Override
    public java.lang.Integer component17() {
        return getMonthlyDay();
    }

    @java.lang.Override
    public java.lang.Integer component18() {
        return getYearlyFreq();
    }

    @java.lang.Override
    public java.lang.Integer component19() {
        return getYearlyDay();
    }

    @java.lang.Override
    public java.lang.String component20() {
        return getRule();
    }

    @java.lang.Override
    public java.lang.Integer value1() {
        return getRecurrenceId();
    }

    @java.lang.Override
    public org.joda.time.DateTime value2() {
        return getStartDate();
    }

    @java.lang.Override
    public org.joda.time.DateTime value3() {
        return getUntilDate();
    }

    @java.lang.Override
    public java.lang.Integer value4() {
        return getRepeat();
    }

    @java.lang.Override
    public java.lang.Boolean value5() {
        return getPermanent();
    }

    @java.lang.Override
    public java.lang.String value6() {
        return getType();
    }

    @java.lang.Override
    public java.lang.Integer value7() {
        return getDailyFreq();
    }

    @java.lang.Override
    public java.lang.Integer value8() {
        return getWeeklyFreq();
    }

    @java.lang.Override
    public java.lang.Boolean value9() {
        return getWeeklyDay_1();
    }

    @java.lang.Override
    public java.lang.Boolean value10() {
        return getWeeklyDay_2();
    }

    @java.lang.Override
    public java.lang.Boolean value11() {
        return getWeeklyDay_3();
    }

    @java.lang.Override
    public java.lang.Boolean value12() {
        return getWeeklyDay_4();
    }

    @java.lang.Override
    public java.lang.Boolean value13() {
        return getWeeklyDay_5();
    }

    @java.lang.Override
    public java.lang.Boolean value14() {
        return getWeeklyDay_6();
    }

    @java.lang.Override
    public java.lang.Boolean value15() {
        return getWeeklyDay_7();
    }

    @java.lang.Override
    public java.lang.Integer value16() {
        return getMonthlyFreq();
    }

    @java.lang.Override
    public java.lang.Integer value17() {
        return getMonthlyDay();
    }

    @java.lang.Override
    public java.lang.Integer value18() {
        return getYearlyFreq();
    }

    @java.lang.Override
    public java.lang.Integer value19() {
        return getYearlyDay();
    }

    @java.lang.Override
    public java.lang.String value20() {
        return getRule();
    }

    @java.lang.Override
    public RecurrencesRecord value1(java.lang.Integer value) {
        setRecurrenceId(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value2(org.joda.time.DateTime value) {
        setStartDate(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value3(org.joda.time.DateTime value) {
        setUntilDate(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value4(java.lang.Integer value) {
        setRepeat(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value5(java.lang.Boolean value) {
        setPermanent(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value6(java.lang.String value) {
        setType(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value7(java.lang.Integer value) {
        setDailyFreq(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value8(java.lang.Integer value) {
        setWeeklyFreq(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value9(java.lang.Boolean value) {
        setWeeklyDay_1(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value10(java.lang.Boolean value) {
        setWeeklyDay_2(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value11(java.lang.Boolean value) {
        setWeeklyDay_3(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value12(java.lang.Boolean value) {
        setWeeklyDay_4(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value13(java.lang.Boolean value) {
        setWeeklyDay_5(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value14(java.lang.Boolean value) {
        setWeeklyDay_6(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value15(java.lang.Boolean value) {
        setWeeklyDay_7(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value16(java.lang.Integer value) {
        setMonthlyFreq(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value17(java.lang.Integer value) {
        setMonthlyDay(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value18(java.lang.Integer value) {
        setYearlyFreq(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value19(java.lang.Integer value) {
        setYearlyDay(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord value20(java.lang.String value) {
        setRule(value);
        return this;
    }

    @java.lang.Override
    public RecurrencesRecord values(java.lang.Integer value1, org.joda.time.DateTime value2, org.joda.time.DateTime value3, java.lang.Integer value4, java.lang.Boolean value5, java.lang.String value6, java.lang.Integer value7, java.lang.Integer value8, java.lang.Boolean value9, java.lang.Boolean value10, java.lang.Boolean value11, java.lang.Boolean value12, java.lang.Boolean value13, java.lang.Boolean value14, java.lang.Boolean value15, java.lang.Integer value16, java.lang.Integer value17, java.lang.Integer value18, java.lang.Integer value19, java.lang.String value20) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RecurrencesRecord
     */
    public RecurrencesRecord() {
        super(com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES);
    }

    /**
     * Create a detached, initialised RecurrencesRecord
     */
    public RecurrencesRecord(java.lang.Integer recurrenceId, org.joda.time.DateTime startDate, org.joda.time.DateTime untilDate, java.lang.Integer repeat, java.lang.Boolean permanent, java.lang.String type, java.lang.Integer dailyFreq, java.lang.Integer weeklyFreq, java.lang.Boolean weeklyDay_1, java.lang.Boolean weeklyDay_2, java.lang.Boolean weeklyDay_3, java.lang.Boolean weeklyDay_4, java.lang.Boolean weeklyDay_5, java.lang.Boolean weeklyDay_6, java.lang.Boolean weeklyDay_7, java.lang.Integer monthlyFreq, java.lang.Integer monthlyDay, java.lang.Integer yearlyFreq, java.lang.Integer yearlyDay, java.lang.String rule) {
        super(com.sonicle.webtop.calendar.jooq.tables.Recurrences.RECURRENCES);

        setRecurrenceId(recurrenceId);
        setStartDate(startDate);
        setUntilDate(untilDate);
        setRepeat(repeat);
        setPermanent(permanent);
        setType(type);
        setDailyFreq(dailyFreq);
        setWeeklyFreq(weeklyFreq);
        setWeeklyDay_1(weeklyDay_1);
        setWeeklyDay_2(weeklyDay_2);
        setWeeklyDay_3(weeklyDay_3);
        setWeeklyDay_4(weeklyDay_4);
        setWeeklyDay_5(weeklyDay_5);
        setWeeklyDay_6(weeklyDay_6);
        setWeeklyDay_7(weeklyDay_7);
        setMonthlyFreq(monthlyFreq);
        setMonthlyDay(monthlyDay);
        setYearlyFreq(yearlyFreq);
        setYearlyDay(yearlyDay);
        setRule(rule);
    }
}