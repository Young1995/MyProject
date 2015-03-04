package org.svtcc.online.management.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created on 2/9/15.
 */
@SuppressWarnings("serial")
@Table(name = "svtcc_online_course_principal")
public class OnlineCoursePrincipal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "userName")
    private String userName;
    @Column(name = "employeeId")
    private String employeeId;
    @Column(name = "gender")
    private Integer gender;
    @Column(name = "education")
    private String education;
    @Column(name = "title")
    private String title;
    @Column(name = "telNo")
    private String telNo;
    @Column(name = "email")
    private String email;
    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE}, fetch = FetchType.LAZY, optional = true)
    @JoinTable(name = "svtcc_principal_online_course", joinColumns = {@JoinColumn(name = "principal_id")}, inverseJoinColumns = {@JoinColumn(name = "online_course_id")})
    private OnlineCourse onlineCourse;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }
}
