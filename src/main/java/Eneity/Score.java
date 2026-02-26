package Eneity;

import javax.xml.crypto.Data;
import java.sql.Date;

public class Score {
    private Integer id;
    private Integer studentId;
    private String subject;
    private Integer score;
    private Date examTime;


    public Score() {
    }

    public Score(Integer id, Integer studentId, String subject, Integer score, Date examTime) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.score = score;
        this.examTime = examTime;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return studentId
     */
    public Integer getStudentId() {
        return studentId;
    }

    /**
     * 设置
     * @param studentId
     */
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    /**
     * 获取
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取
     * @return score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置
     * @param score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取
     * @return examTime
     */
    public Date getExamTime() {
        return examTime;
    }

    /**
     * 设置
     * @param examTime
     */
    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public String toString() {
        return "Score{id = " + id + ", studentId = " + studentId + ", subject = " + subject + ", score = " + score + ", examTime = " + examTime + "}";
    }
}
