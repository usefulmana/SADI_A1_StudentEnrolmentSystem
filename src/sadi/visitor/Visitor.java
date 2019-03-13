package sadi.visitor;

import sadi.enrollment.StudentEnrollment;

public interface Visitor {
    public void visit(StudentEnrollment studentEnrollment);
}
