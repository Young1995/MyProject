package org.svtcc.online.management.domain;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by hanrenwei on 2/9/15.
 */
@SuppressWarnings("serial")
@Entity(name = "Grade")
@Table(name = "svtcc_grade")
public class Grade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "grade_name")
    private String name;

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
}
