package Eneity;

import java.sql.Timestamp;

public class Student {
    private Integer id;
    private String name;
    private char gender;
    private Integer age;
    private String studentNo;
    private Timestamp createTime;


    public Student() {
    }

    public Student(Integer id, String name, char gender, Integer age, String studentNo, Timestamp createTime) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.studentNo = studentNo;
        this.createTime = createTime;
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(char gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取
     * @return studentNo
     */
    public String getStudentNo() {
        return studentNo;
    }

    /**
     * 设置
     * @param studentNo
     */
    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    /**
     * 获取
     * @return createTime
     */
    public Timestamp getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", gender = " + gender + ", age = " + age + ", studentNo = " + studentNo + ", createTime = " + createTime + "}";
    }
}
