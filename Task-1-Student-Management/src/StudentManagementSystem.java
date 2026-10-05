import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class StudentManagementSystem {
    ArrayList<Student> students=new ArrayList<>();
    Scanner scanner=new Scanner(System.in);
    public void addStudent(){
        int id;
        while(true) {
            System.out.println("Enter Student ID:");
            try {
                id = scanner.nextInt();
                scanner.nextLine();
                boolean exists = false;
                for (Student s : students) {
                    if (s.getId() == id) {
                        exists = true;
                        break;

                    }

                }
                if (exists) {
                    System.out.println("Student ID already exists.Please enter a different ID.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid ID.Please enter a number");
                scanner.nextLine();
            }
        }


        System.out.println("Enter Student Name:");
        String name=scanner.nextLine();
        //scanner.nextLine();
        System.out.println("Enter Student Marks");
        int marks;
        while(true) {
            try {
                marks = scanner.nextInt();
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks.please enter between 0 to 100.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid marks.");
                return;
            }

        }
        Student student =new Student(id,name,marks);
        students.add(student);
        System.out.println("Student added successfully");
    }
    public void viewStudents() {
        if (students.isEmpty()){
            System.out.println("No Students Found.");
        return;
    }
        System.out.println("Students are Listed Below..");


        for(Student student:students)
        {
            System.out.println("ID: "+student.getId());
            System.out.println("Name: "+student.getName());
            System.out.println("Marks: "+student.getMarks());
        }
    }
    public void searchById(){
        System.out.println("Enter the student ID: ");
        int id=scanner.nextInt();
        boolean found=false;
        for(Student  student: students){
            if(id==student.getId()) {
                System.out.println("Student Found:");
                System.out.println("ID: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Marks: " + student.getMarks());
                found=true;
                break;
            }

            }
        if(!found)
        {
            System.out.println("Student not found");
        }
    }
    public static void main(String[] args)
    {
        StudentManagementSystem system=new StudentManagementSystem();
        while(true)
        {
            System.out.println("1.Add Student");
            System.out.println("2.View Students");
            System.out.println("3. Search Student");
            System.out.println("4.Exit");
            System.out.println("Enter our choice:");
            int choice=system.scanner.nextInt();
            switch(choice)
            {
                case 1:system.addStudent();
                       break;
                case 2:system.viewStudents();
                       break;
                case 3:system.searchById();
                        break;
                case 4:System.out.println("Exiting....");
                       break;
                    default:System.out.println("Invalid Choice.");
            }
        }
    }
}
