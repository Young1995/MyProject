package org.svtcc.online.management.domain;

/**
 * Created by hanrenwei on 2/28/15.
 */

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Table(name = "svtcc_documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content")
    @Lob
    private Blob content;
    @Column(name = "link")
    private String link;

    @Column(name = "type")
    private Integer type;

    @Column(name = "category")
    private Integer category;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "created")
    private Date created;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH,
            CascadeType.MERGE}, fetch = FetchType.LAZY, optional = true)
    @JoinTable(name = "svtcc_document_online_course", joinColumns = {@JoinColumn(name = "document_id")}, inverseJoinColumns = {@JoinColumn(name = "online_course_id")})
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Blob getContent() {
        return content;
    }

    public void setContent(Blob content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public OnlineCourse getOnlineCourse() {
        return onlineCourse;
    }

    public void setOnlineCourse(OnlineCourse onlineCourse) {
        this.onlineCourse = onlineCourse;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public enum TYPE {
        FILE(1), LINK(2);
        private int value = 0;

        private TYPE(int value) {
            this.value = value;
        }

        public static TYPE valueOf(int value) {
            switch (value) {
                case 1:
                    return FILE;
                case 2:
                    return LINK;
                default:
                    return LINK;
            }
        }

        public int value() {
            return this.value;
        }
    }

    public enum CATEGORY {
        INTRODUCTION(1), PRINCIPAL(2);
        private int value = 0;

        private CATEGORY(int value) {
            this.value = value;
        }

        public static CATEGORY valueOf(int value) {
            switch (value) {
                case 1:
                    return INTRODUCTION;
                case 2:
                    return PRINCIPAL;
                default:
                    return INTRODUCTION;
            }
        }

        public int value() {
            return this.value;
        }
    }

}