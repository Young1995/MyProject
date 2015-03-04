package org.svtcc.online.management.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 常规任务
 * 
 * @author Administrator
 * 
 */
@SuppressWarnings("serial")
@Entity(name = "Task")
@Table(name = "svtcc_task")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name; // 任务名称

    @Column(name = "content")
    private String content; // 任务内容

    @Column(name = "warning")
    private String warning; // 注意事项

    @Column(name = "key_point")
    private String keyPoint; // 考核点

    @Column(name = "finish_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate; // 完成时间

    @Column(name = "pub_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate; // 发布时间

    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinColumn(name = "dep_id")
    private Department department; // 指定学院

    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE,
            CascadeType.PERSIST })
    @JoinColumn(name = "major_id")
    private Major major; // 指定专业

    @Column(name = "feedback")
    private String feedback; // 反馈

    @Column(name = "result")
    private String result; // 考核结果

    @Column(name = "status")
    private Integer status; // 当前状态： 1. 发布， 2. 执行， 3. 考核结束

    @Column(name = "type")
    private Integer type; // 类型： 1. 临时， 2. 常规

    @Column(name = "pub_user")
    private String pubUser;

    @Column(name = "execute_feedback")
    private String executeFeedback;

    @Column(name = "exam_feedback")
    private String examFeedback;

    @Column(name = "exam_score")
    private Integer examScore;

    // TODO: 附件

    /**
     * 操作记录的时间和用户
     */
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "update_user")
    private String updateUser;

    // 接收参数
    @Transient
    private Integer depId;
    @Transient
    private Integer majorId;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(String keyPoint) {
        this.keyPoint = keyPoint;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getPubUser() {
        return pubUser;
    }

    public void setPubUser(String pubUser) {
        this.pubUser = pubUser;
    }

    public String getExecuteFeedback() {
        return executeFeedback;
    }

    public void setExecuteFeedback(String executeFeedback) {
        this.executeFeedback = executeFeedback;
    }

    public String getExamFeedback() {
        return examFeedback;
    }

    public void setExamFeedback(String examFeedback) {
        this.examFeedback = examFeedback;
    }

    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

}
