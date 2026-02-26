
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class utils {

    //获取数据库连接
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/work?useSSL=false&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        return DriverManager.getConnection(url, username, password);
    }

    //关闭资源
    public static void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstmt != null) {
            pstmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    //添加初始化数据
    public static void initializeData() throws Exception {
        //添加学生数据
        dao.addStudent("张三", '男', 20, "S001");
        dao.addStudent("李四", '女', 19, "S002");

        //添加成绩数据
        dao.addScore(1, "语文", 85.5, "2024-06-01");
        dao.addScore(1, "数学", 90.0, "2024-06-01");
        dao.addScore(2, "语文", 78.0, "2024-06-01");
        dao.addScore(2, "数学", 88.5, "2024-06-01");


    }

    //生成菜单
    public static void printMenu() {
        System.out.println("请选择操作：");
        System.out.println("1. 添加学生成员");
        System.out.println("2. 添加学生成绩");
        System.out.println("3. 修改学生信息");
        System.out.println("4. 删除");
        System.out.println("5. 查询");
        System.out.println("6. 退出系统");
    }

    //添加学生成员
    public static void addStudent(Scanner sc) throws Exception {
        System.out.println("请输入学生姓名：");
        String name = sc.next();
        System.out.println("请输入学生性别（男/女）：");
        char gender = sc.next().charAt(0);
        System.out.println("请输入学生年龄：");
        int age = sc.nextInt();
        System.out.println("请输入学生学号：");
        String studentNo = sc.next();
        dao.addStudent(name, gender, age, studentNo);
        System.out.println("学生添加成功！");
    }

    //添加学生成绩
    public static void addScore(Scanner sc) throws Exception {
        System.out.println("请输入学生ID：");
        int studentId = sc.nextInt();
        System.out.println("请输入科目：");
        String subject = sc.next();
        System.out.println("请输入成绩：");
        double score = sc.nextDouble();
        if(score<0 || score>100){
            System.out.println("成绩必须在0到100之间，请重新输入！");
            return;
        }
        System.out.println("请输入考试时间（格式：YYYY-MM-DD）：");
        String examTime = sc.next();
        dao.addScore(studentId, subject, score, examTime);
        System.out.println("成绩添加成功！");
    }

    //修改学生信息
    public static void updateStudent(Scanner sc) throws Exception {
        System.out.println("请输入学生ID：");
        int id = sc.nextInt();
        System.out.println("请输入新的学生姓名：");
        String name = sc.next();
        System.out.println("请输入新的学生年龄：");
        int age = sc.nextInt();
        dao.updateStudent(id, name, age);
        System.out.println("学生信息修改成功！");
    }

    //删除学生成绩
    public static void deleteScoresByStudentId(Scanner sc) throws Exception {
        System.out.println("请选择删除方式：");
        System.out.println("1. 根据studentId删除学生的所有成绩");
        System.out.println("2. 根据学生Id删除学生数据及其成绩");
        System.out.println("3. 返回上一层");
        while(true){
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("请输入studentId：");
                    int studentId = sc.nextInt();
                    dao.deleteScoresByStudentId(studentId);
                    System.out.println("学生成绩删除成功！");
                    return;
                case 2:
                    System.out.println("请输入学生Id：");
                    int Id = sc.nextInt();
                    boolean bl=dao.deleteStudentById(Id);
                    if(bl){
                        System.out.println("学生数据及其成绩删除成功！");
                    }else {
                        System.out.println("删除失败！");
                    }
                    return;
                case 3:
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
                    break;
            }
        }
    }

    //查询学生成绩
    public static void queryScores(Scanner sc) throws Exception {
        //查询方式
        System.out.println("请输入查询方式：");
        System.out.println("1. 根据学生id查询学生的所有成绩");
        System.out.println("2. 根据姓名模糊查询学生数据");
        System.out.println("3. 根据学号查询指定学生的所有科目成绩");
        System.out.println("4. 返回上一层");
        while(true){
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("请输入学生ID：");
                    int studentId = sc.nextInt();
                    dao.queryScoresByStudentId(studentId);
                    return;
                case 2:
                    System.out.println("请输入学生姓名：");
                    String name = sc.next();
                    dao.queryStudentsByName(name);
                    return;
                case 3:
                    System.out.println("请输入学生学号：");
                    String studentNo = sc.next();
                    dao.queryScoresByStudentNo(studentNo);
                    return;
                case 4:
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
        }
    }
}
