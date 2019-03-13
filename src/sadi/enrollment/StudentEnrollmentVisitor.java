package sadi.enrollment;

import sadi.visitor.Visitor;

public class StudentEnrollmentVisitor implements Visitor {

    /*Visit object*/
    private String studentID;

    public StudentEnrollmentVisitor(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public void visit(StudentEnrollment studentEnrollment) {
        // Delete all enrollment details of a student
        studentEnrollment = StudentEnrollment.getStudentEnrolment(this.studentID);
        studentEnrollment.getStudentCourseA().clear();
        studentEnrollment.getStudentCourseB().clear();
        studentEnrollment.getStudentCourseC().clear();
    }
}
