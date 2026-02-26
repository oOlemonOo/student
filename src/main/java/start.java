import Eneity.Score;
import Eneity.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class start {
    public static void main(String[] args) throws Exception {
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Score> scores = new ArrayList<>();
        try{
            utils.initializeData();//初始化数据
        }catch (Exception e){
                System.out.println("数据已存在，跳过初始化");
        }

        dao.syncData(students,scores);//同步数据

        Scanner sc= new Scanner(System.in);
        while (true) {
            utils.printMenu();
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    utils.addStudent(sc);
                    break;
                case 2:
                    utils.addScore(sc);
                    break;
                case 3:
                    utils.updateStudent(sc);
                    break;
                case 4:
                    utils.deleteScoresByStudentId(sc);
                    break;
                case 5:
                    utils.queryScores(sc);
                    break;
                case 6:
                    System.out.println("退出系统，感谢使用！");
                    sc.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
                    break;
            }
        }


    }
}
