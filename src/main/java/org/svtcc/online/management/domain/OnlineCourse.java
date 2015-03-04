package org.svtcc.online.management.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created on 2/9/15.
 */
@SuppressWarnings("serial")
@Entity(name = "OnlineCourse")
@Table(name = "svtcc_online_course")
public class OnlineCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "svtcc_online_course_departments",
            joinColumns = {@JoinColumn(name = "online_course_id")},
            inverseJoinColumns = {@JoinColumn(name = "department_id")})
    private List<Department> departmentList;
    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "svtcc_online_course_majors",
            joinColumns = {@JoinColumn(name = "online_course_id")},
            inverseJoinColumns = {@JoinColumn(name = "major_id")})
    private List<Major> majorList;
    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "svtcc_online_course_grades",
            joinColumns = {@JoinColumn(name = "online_course_id")},
            inverseJoinColumns = {@JoinColumn(name = "grade_id")})
    private List<Grade> gradeList;
    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "svtcc_online_course_users",
            joinColumns = {@JoinColumn(name = "online_course_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> excludeUserList;

    @OneToMany(mappedBy = "onlineCourse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Document> documentList;

    @Column(name = "enablePublic")
    private Boolean enablePublic;
    @Column(name = "enableShowInFront")
    private Boolean enableShowInFront;
    @Column(name = "enableAutoApprove")
    private Boolean enableAutoApprove;
    @Column(name = "status")
    private STATUS status;
    @Column(name = "description")
    private String description;

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

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Major> getMajorList() {
        return majorList;
    }

    public void setMajorList(List<Major> majorList) {
        this.majorList = majorList;
    }

    public List<Grade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<Grade> gradeList) {
        this.gradeList = gradeList;
    }

    public List<User> getExcludeUserList() {
        return excludeUserList;
    }

    public void setExcludeUserList(List<User> excludeUserList) {
        this.excludeUserList = excludeUserList;
    }

    public Boolean getEnablePublic() {
        return enablePublic;
    }

    public void setEnablePublic(Boolean enablePublic) {
        this.enablePublic = enablePublic;
    }

    public Boolean getEnableShowInFront() {
        return enableShowInFront;
    }

    public void setEnableShowInFront(Boolean enableShowInFront) {
        this.enableShowInFront = enableShowInFront;
    }

    public Boolean getEnableAutoApprove() {
        return enableAutoApprove;
    }

    public void setEnableAutoApprove(Boolean enableAutoApprove) {
        this.enableAutoApprove = enableAutoApprove;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Document> getDocumentList() {
        return documentList;
    }

    public void setDocumentList(List<Document> documentList) {
        this.documentList = documentList;
    }

    public enum STATUS {
        PROCESSING(1), UNAPPROVED(2), UNPUBLISHED(3), PUBLISHED(4);
        private int value = 0;

        private STATUS(int value) {
            this.value = value;
        }

        public static STATUS valueOf(int value) {
            switch (value) {
                case 1:
                    return PROCESSING;
                case 2:
                    return UNAPPROVED;
                case 3:
                    return UNPUBLISHED;
                case 4:
                    return PUBLISHED;
                default:
                    return UNAPPROVED;
            }
        }

        public int value() {
            return this.value;
        }
    }

}
