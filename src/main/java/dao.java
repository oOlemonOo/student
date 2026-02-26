
import Eneity.Score;
import Eneity.Student;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class dao {
    //添加学生成员
    public static void addStudent(String name , char gender, int age, String studentNo) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "INSERT INTO student ( name, gender, age,student_no) VALUES ( ?,?,?,?)";
        PreparedStatement pstmt =conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, String.valueOf(gender));
        pstmt.setInt(3, age);
        pstmt.setString(4, studentNo);
        pstmt.executeUpdate();

        utils.closeResources(conn,pstmt,null);


    }

    //添加学生成绩
    public static void addScore(int studentId,String subject ,double score,String exam_time ) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "INSERT INTO score ( student_id, subject, score,exam_time) VALUES ( ?,?,?,? )";
        PreparedStatement pstmt =conn.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        pstmt.setString(2, subject);
        pstmt.setDouble(3, score);
        pstmt.setString(4, exam_time);
        pstmt.executeUpdate();

        utils.closeResources(conn,pstmt,null);
    }

    //根据id修改学生的姓名和年龄
    public static void updateStudent(int id,String name,int age) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "UPDATE student SET name=?, age=? WHERE id=?";
        PreparedStatement pstmt =conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setInt(3, id);
        pstmt.executeUpdate();

        utils.closeResources(conn,pstmt,null);
    }

    //根据student_id删除学生的所有成绩
    public static void deleteScoresByStudentId(int studentId) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "DELETE FROM score WHERE student_id=?";
        PreparedStatement pstmt =conn.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        pstmt.executeUpdate();

        utils.closeResources(conn,pstmt,null);
    }

    //根据学生id删除学生的数据及其所有成绩
    public static boolean deleteStudentById(int studentId) throws Exception {
        Connection conn = utils.getConnection();
        try {
            conn.setAutoCommit(false);//开启事务
            // 删除学生的成绩
            String sqlDeleteScores = "DELETE FROM score WHERE student_id=?";
            PreparedStatement pstmtDeleteScores = conn.prepareStatement(sqlDeleteScores);
            pstmtDeleteScores.setInt(1, studentId);
            pstmtDeleteScores.executeUpdate();

            // 删除学生数据
            String sqlDeleteStudent = "DELETE FROM student WHERE id=?";
            PreparedStatement pstmtDeleteStudent = conn.prepareStatement(sqlDeleteStudent);
            pstmtDeleteStudent.setInt(1, studentId);
            pstmtDeleteStudent.executeUpdate();

            conn.commit();

            pstmtDeleteScores.close();
            pstmtDeleteStudent.close();
            conn.close();
            return true;
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (Exception ex) {
                    System.out.println("回滚失败: " + ex.getMessage());
                }
            }
            return false;
        }
    }

    //根据学生id查询学生的所有成绩
    public static void queryScoresByStudentId(int studentId) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "SELECT subject, score, exam_time FROM score WHERE student_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, studentId);
        var rs = pstmt.executeQuery();

        while (rs.next()) {
            String subject = rs.getString("subject");
            double score = rs.getDouble("score");
            String examTime = rs.getString("exam_time");
            System.out.println("科目: " + subject + ", 分数: " + score + ", 考试时间: " + examTime);
        }

        utils.closeResources(conn,pstmt,rs);
    }

    //根据姓名模糊查询学生数据
    public static void queryStudentsByName(String name) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "SELECT id, name, gender, age, student_no FROM student WHERE name LIKE ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, "%" + name + "%");
        var rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String studentName = rs.getString("name");
            String gender = rs.getString("gender");
            int age = rs.getInt("age");
            String studentNo = rs.getString("student_no");
            System.out.println("ID: " + id + ", 姓名: " + studentName + ", 性别: " + gender + ", 年龄: " + age + ", 学号: " + studentNo);
        }

        utils.closeResources(conn,pstmt,rs);
    }

    //根据学号查询指定学生的所有科目成绩
    public static void queryScoresByStudentNo(String studentNo) throws Exception {
        Connection conn = utils.getConnection();

        String sql = "SELECT s.subject, s.score, s.exam_time FROM score s JOIN student st ON s.student_id = st.id WHERE st.student_no=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, studentNo);
        var rs = pstmt.executeQuery();

        while (rs.next()) {
            String subject = rs.getString("subject");
            double score = rs.getDouble("score");
            String examTime = rs.getString("exam_time");
            System.out.println("科目: " + subject + ", 分数: " + score + ", 考试时间: " + examTime);
        }

        utils.closeResources(conn,pstmt,rs);
    }

    //同步数据库内容到students集合中
    public static ArrayList<Student> syncStudents() throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        Connection conn = utils.getConnection();

        String sql = "SELECT id, name, gender, age, student_no,create_time FROM student";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        var rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            char gender = rs.getString("gender").charAt(0);
            int age = rs.getInt("age");
            String studentNo = rs.getString("student_no");
            Timestamp createTime = rs.getTimestamp("create_time");
            students.add(new Student(id, name, gender, age, studentNo,createTime));
        }

        utils.closeResources(conn,pstmt,rs);

        return students;
    }

    //同步数据库内容到scores集合中
    public static ArrayList<Score> syncScores() throws Exception {
        ArrayList<Score> scores = new ArrayList<>();
        Connection conn = utils.getConnection();

        String sql = "SELECT id, student_id, subject, score, exam_time FROM score";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        var rs = pstmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            int studentId = rs.getInt("student_id");
            String subject = rs.getString("subject");
            int score = rs.getInt("score");
            Date examTime = rs.getDate("exam_time");
            scores.add(new Score(id, studentId, subject, score, examTime));
        }

        utils.closeResources(conn,pstmt,rs);

        return scores;
    }

    //同步数据
    public static void syncData(ArrayList<Student> students, ArrayList<Score> scores) throws Exception {
        // 清空原有数据
        students.clear();
        scores.clear();

        // 获取新数据
        ArrayList<Student> newStudents = dao.syncStudents();
        ArrayList<Score> newScores = dao.syncScores();

        // 添加到原列表中
        students.addAll(newStudents);
        scores.addAll(newScores);
    }

}