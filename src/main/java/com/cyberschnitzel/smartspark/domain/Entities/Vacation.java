package com.cyberschnitzel.smartspark.domain.Entities;

import java.util.Date;

public class Vacation {
    private Date start_date;
    private Date end_date;
    private int employee_id;

    public Vacation(Date start_date, Date end_date, int employee_id) {
        this.start_date = start_date;
        this.end_date = end_date;
        this.employee_id = employee_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "start_date=" + start_date +
                ", end_date=" + end_date +
                ", employee_id=" + employee_id +
                '}';
    }
}
