package com.example.demotest.domain;


import javax.persistence.*;
import java.util.Date;

//@Getter
//@Setter
@Entity
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long holiday_id;
    @Column(name = "holiday")
    private String holiday;
    @Column(name = "data")
    private Date data;

    public Long getHoliday_id() {
        return holiday_id;
    }

    public void setHoliday_id(Long holiday_id) {
        this.holiday_id = holiday_id;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
