package com.example.demotest.domain;

import javax.persistence.*;

@Entity
@Table(name = "equimpent")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long equipment_id;

    @Column(name = "category")
    private String category;

    @Column(name = "nameEquipment")
    private String nameEquipment;

    @Column(name = "model")
    private String model;

    // @OneToMany(fetch = FetchType.EAGER)
    // @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    @Column(name = "possession")
    private String possession;

    @Column(name = "active")
    private String active;

    @Column(name = "serialNumber")
    private String serialNumber;

    //@ManyToMany(mappedBy = "sprzets")
    //private Set<Person> users;

//    public Set<Person> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<Person> users) {
//        this.users = users;
//    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Person user;

    public Long getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(Long equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNameEquipment() {
        return nameEquipment;
    }

    public void setNameEquipment(String nameEquipment) {
        this.nameEquipment = nameEquipment;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPossession() {
        return possession;
    }

    public void setPossession(String possession) {
        this.possession = possession;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }
}
