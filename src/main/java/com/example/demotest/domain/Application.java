package com.example.demotest.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long application_Id;

    //@Column(name="id_user")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Person userId;

    @Column(name = "applicationType")
    private String applicationType;
    @Column(name = "dateSubmission")
    private Date dateSubmission;
    @Column(name = "startDate")
    private Date startDate;
    @Column(name = "lastDate")
    private Date lastDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_SubstitutePerson")
    private Person id_SubstitutePerson;
    @Column(name = "active")
    private boolean active;

    public Long getApplication_Id() {
        return application_Id;
    }

    public void setApplication_Id(Long application_Id) {
        this.application_Id = application_Id;
    }

    public Person getUserId() {
        return userId;
    }

    public void setUserId(Person userId) {
        this.userId = userId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public Date getDateSubmission() {
        return dateSubmission;
    }

    public void setDateSubmission(Date dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Person getId_SubstitutePerson() {
        return id_SubstitutePerson;
    }

    public void setId_SubstitutePerson(Person id_SubstitutePerson) {
        this.id_SubstitutePerson = id_SubstitutePerson;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
