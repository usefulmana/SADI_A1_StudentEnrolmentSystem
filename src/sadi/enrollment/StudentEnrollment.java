package sadi.enrollment;

import sadi.command.Command;
import sadi.course.Course;
import sadi.course.CourseListA;
import sadi.course.CourseListB;
import sadi.course.CourseListC;
import sadi.person.Student;
import sadi.person.StudentList;
import sadi.visitor.Visitable;
import sadi.visitor.Visitor;

import java.util.*;


public class StudentEnrollment extends StudentEnrollmentManager implements Command, Visitable {
    private Student student;
    private static List<Student> studentSubscribers = new ArrayList<>();
    private CourseListA courseListA = CourseListA.getINSTANCE();
    private CourseListB courseListB = CourseListB.getINSTANCE();
    private CourseListC courseListC = CourseListC.getINSTANCE();
    private List<Course> studentCourseA = new ArrayList<>();
    private List<Course> studentCourseB = new ArrayList<>();
    private List<Course> studentCourseC = new ArrayList<>();
    private static Map<String, StudentEnrollment> cache = new HashMap<>(); // Flyweight cache
    private int creditLimit;

    public StudentEnrollment() {
    }

    public StudentEnrollment(Student student, int creditLimit) {
        this.student = student;
        this.creditLimit =creditLimit;
        cache.put(student.getId(),this); // Automatically put object into cache
    }

    // Flyweight Method
    public static StudentEnrollment getStudentEnrolment(String studentID)
    {
        StudentEnrollment studentEnrollment = cache.get(studentID);
        if (studentEnrollment != null)
        {
            return studentEnrollment;
        }
        return null;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Course> getStudentCourseA() {
        return studentCourseA;
    }

    public void setStudentCourseA(List<Course> studentCourseA) {
        this.studentCourseA = studentCourseA;
    }

    public List<Course> getStudentCourseB() {
        return studentCourseB;
    }

    public void setStudentCourseB(List<Course> studentCourseB) {
        this.studentCourseB = studentCourseB;
    }

    public List<Course> getStudentCourseC() {
        return studentCourseC;
    }

    public void setStudentCourseC(List<Course> studentCourseC) {
        this.studentCourseC = studentCourseC;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    private int creditCount(List<Course> courseList)
    {
        // Counting credits for each semester
        int count = 0;
        for (int i = 0; i < courseList.size(); i++) {
            count+= courseList.get(i).getCredits();
        }
        return count;
    }

    public void enrol(Course course, String semester) {
        if(semester.equals("A")){
        // Enrol method. Total credits each semester can not exceed 48. Can not allow students to have duplicate courses in the same semester or same year.
        if (courseListA.getCourseList().contains(course) && semester.equals("A") && !studentCourseA.contains(course) && (creditCount(studentCourseA)<=(this.creditLimit-course.getCredits()))
        && !studentCourseB.contains(course) && !studentCourseC.contains(course))
        {
            studentCourseA.add(course);
            for (Student student: studentSubscribers
                 ) {
               if(student == this.getStudent())
               {
                   System.out.println("Now enrolled in " + course.getCourseId() + " - " + course.getCourseName() +
                           " during semester " + semester);
               }
            }
        }
        else if(studentCourseA.contains(course) || studentCourseB.contains(course) || studentCourseC.contains(course))
        {
            System.out.println("The student is already enrolled in this course");
        }
        else if(creditCount(studentCourseA)>(this.creditLimit-course.getCredits()))
        {
            System.out.println("Students are not allowed to take more than 48 credits per semester");
        }}
        if(semester.equals("B")){
        if(courseListB.getCourseList().contains(course) && semester.equals("B") && !studentCourseB.contains(course) && creditCount(studentCourseB)<=(this.creditLimit-course.getCredits())
                && !studentCourseA.contains(course) && !studentCourseC.contains(course))
        {
            studentCourseB.add(course);
            for (Student student: studentSubscribers
            ) {
                if(student == this.getStudent())
                {
                    System.out.println("Now enrolled in " + course.getCourseId() + " - " + course.getCourseName() +
                            " during semester " + semester);
                }
            }
        }
        else if(studentCourseA.contains(course) || studentCourseB.contains(course) || studentCourseC.contains(course))
        {
            System.out.println("The student is already enrolled in this course");
        }
        else if(creditCount(studentCourseB)>(this.creditLimit-course.getCredits()))
        {
            System.out.println("Students are not allowed to take more than 48 credits per semester");
        }}
        if(semester.equals("C")){
        if(courseListC.getCourseList().contains(course) && semester.equals("C") && !studentCourseC.contains(course) && creditCount(studentCourseC)<=(this.creditLimit-course.getCredits())
                && !studentCourseB.contains(course) && !studentCourseA.contains(course))
        {
            studentCourseC.add(course);
            for (Student student: studentSubscribers
            ) {
                if(student == this.getStudent())
                {
                    System.out.println("Now enrolled in " + course.getCourseId() + " - " + course.getCourseName() +
                            " during semester " + semester);
                }
            }
        }
        else if(studentCourseA.contains(course) || studentCourseB.contains(course) || studentCourseC.contains(course))
        {
            System.out.println("The student is already enrolled in this course");
        }
        else if(creditCount(studentCourseC)>(this.creditLimit-course.getCredits()))
        {
            System.out.println("Students are not allowed to take more than 48 credits per semester");
        }}
    }

    public static List<Student> getStudentSubscribers() {
        return studentSubscribers;
    }

    public static void setStudentSubscribers(List<Student> studentSubscribers) {
        StudentEnrollment.studentSubscribers = studentSubscribers;
    }

    public Course studentCourseSearch(String courseID, String semester)
    {   // Looking into if a student is enrolled in a course in a particular semester
        if(semester.equals("A"))
        {
            for (int i = 0; i < studentCourseA.size(); i++) {
                if(studentCourseA.get(i).getCourseId().equals(courseID))
                {
                    return studentCourseA.get(i);
                }
            }
            return null;
        }
        else if(semester.equals("B"))
        {
            for (int i = 0; i < studentCourseB.size(); i++) {
                if(studentCourseB.get(i).getCourseId().equals(courseID))
                {
                    return studentCourseB.get(i);
                }
            }
            return null;
        }
        else if(semester.equals("C"))
        {
            for (int i = 0; i < studentCourseC.size(); i++) {
                if(studentCourseC.get(i).getCourseId().equals(courseID))
                {
                    return studentCourseC.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public void drop(Course course, String semester) {
        // Drop courses method
        if (semester.equals("A"))
        {
            studentCourseA.remove(course);
            for (Student student: studentSubscribers
            ) {
                if(student == this.getStudent())
                {
                    System.out.println("Successfully dropped " + course.getCourseId() + " - " + course.getCourseName() +
                            " during semester " + semester);
                }
            }
        }
        if(semester.equals("B"))
        {
            studentCourseB.remove(course);
            for (Student student: studentSubscribers
            ) {
                if(student == this.getStudent())
                {
                    System.out.println("Successfully dropped " + course.getCourseId() + " - " + course.getCourseName() +
                            " during semester " + semester);
                }
            }
        }
        if(semester.equals("C"))
        {
            studentCourseC.remove(course);
            for (Student student: studentSubscribers
            ) {
                if(student == this.getStudent())
                {
                    System.out.println("Successfully dropped " + course.getCourseId() + " - " + course.getCourseName() +
                            " during semester " + semester);
                }
            }
        }
    }

    @Override
    public List<Course> getCourses(String semester) {
        return null;
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
                "studentCourseA=" + studentCourseA +
                ", studentCourseB=" + studentCourseB +
                ", studentCourseC=" + studentCourseC +
                '}';
    }

    public boolean viewASemesterCourses(String semester)
    {   // Displaying current enrolment of a student in a particular semester
        if(semester.equals("A"))
        {
            if(studentCourseA.size() > 0)
            {
                for (int i = 0; i < studentCourseA.size(); i++) {
                    System.out.println(studentCourseA.get(i).getCourseId() + " - " + studentCourseA.get(i).getCourseName() + " - " +
                            studentCourseA.get(i).getCredits());
                }
                return true;
            }
            return false;
        }
        else if(semester.equals("B"))
        {
            if(studentCourseB.size()>0){
                for (int i = 0; i < studentCourseB.size(); i++) {
                    System.out.println(studentCourseB.get(i).getCourseId() + " - " + studentCourseB.get(i).getCourseName()+ " - " +
                            studentCourseB.get(i).getCredits());
                }
                return true;
            }
            return false;
        }
        else if(semester.equals("C"))
        {
            if(studentCourseC.size()>0){
                for (int i = 0; i < studentCourseC.size(); i++) {
                    System.out.println(studentCourseC.get(i).getCourseId() + " - " + studentCourseC.get(i).getCourseName()+ " - " +
                            studentCourseC.get(i).getCredits());
                }
                return true;
            }
            return false;
        }
        return false;
    }

    public void viewAllCourses()
    {
        // Display all enrollment details of a student in a year
        System.out.println("Semester A");
        viewASemesterCourses("A");
        System.out.println("Semester B");
        viewASemesterCourses("B");
        System.out.println("Semester C");
        viewASemesterCourses("C");
    }

    public static boolean continuePrompt(String message)
    {   // Reusable generic continue prompt
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toUpperCase();
        if(choice.equals("Y"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void printAllEnrolments(String semester)
    {   // Display all students' enrollment details in a particular semester
        for (String key: cache.keySet()) // Extracting key-value pair from cache
        {
            System.out.println(StudentList.searchStudent(key).getId() + " - " + StudentList.searchStudent(key).getName());
            cache.get(key).viewASemesterCourses(semester);
            System.out.println("---------------------------------------------");
        }
    }

    /*The command methods*/
    @Override
    public void executeEnrol(Course course, String semester) {
        enrol(course, semester);
    }

    @Override
    public void undoEnrol(Course course, String semester) {
        drop(course,semester);
    }

    @Override
    public void executeDrop(Course course, String semester) {
        drop(course,semester);
    }

    @Override
    public void undoDrop(Course course, String semester) {
        enrol(course,semester);
    }

    @Override
    public void accept(Visitor visitor) {
        // Accept visit from StudentEnrollmentVisitor
        visitor.visit(this);
    }
}
