package university

import grails.rest.RestfulController

class StudentRestController extends RestfulController<Student> {

    static responseFormats = ['json', 'html']

    StudentRestController() {
        super(Student)
    }

    def courses(Long id) {

        Student student = Student.get(id)

        if (!student) {
            response.status = 404
            respond([
                error: "Student not found"
            ])
            return
        }

        def enrolledCourses = student.enrollments.collect { enrollment ->
            [
                id         : enrollment.course.id,
                title      : enrollment.course.title,
                code       : enrollment.course.code,
                creditHours: enrollment.course.creditHours,
                grade      : enrollment.grade
            ]
        }

        respond([
            id           : student.id,
            name         : student.name,
            email        : student.email,
            studentNumber: student.studentNumber,
            courses      : enrolledCourses
        ])
    }
}