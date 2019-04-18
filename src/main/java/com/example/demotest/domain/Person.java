package com.example.demotest.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "Person")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;


    @Column(name = "position")
    private String position;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "workplace")
    private String workplace;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASSWORD")
    private String password;

    private boolean active;

    @Column(name = "FIRSTNAME")
    private String firstName;
    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "phoneNumber")
    private int phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_role", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "person_position", joinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "position_id", referencedColumnName = "position_id"))
    private Set<Position> stanowiskos;

    public Set<Position> getStanowiskos() {
        return stanowiskos;
    }


//    @OneToMany
//    private List<Equipment> equipment;


//    @OneToMany(fetch = FetchType.LAZY)
//
//    private Set<Application> applications;
//
//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<Application> id_SubstitutePerson;

    //    public List<Equipment> getEquipment() {
//        return equipment;
//    }
    public void setStanowiskos(Set<Position> stanowiskos) {
        this.stanowiskos = stanowiskos;
    }
//    public void setEquipment(List<Equipment> equipment) {
//        this.equipment = equipment;
//    }
// @ManyToMany(fetch = FetchType.EAGER)
    // @JoinTable(name="user_sprzet", joinColumns=@JoinColumn(name="user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="sprzet_id", referencedColumnName = "id"))
    // private Set<Equipment> equipment;

    // public Set<Equipment> getEquipment() {
    //     return equipment;
    // }

    // public void setEquipment(Set<Equipment> equipment) {
    //  this.equipment = equipment;
    //  }

    /*public boolean isAdmin(){
                return roles.contains(Role.ROLE_ADMIN);
            }*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
        //return null;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }
}
