package university

import grails.rest.RestfulController

class StudentRestController extends RestfulController<Student> {

    static responseFormats = ['json', 'html']

    EnrollmentService enrollmentService

    StudentRestController() {
        super(Student)
    }

    @Override
    def index() {

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        def students

        if (params.name && params.email) {

            students = Student.findAllByNameIlikeAndEmailIlike(
                "%${params.name}%",
                "%${params.email}%",
                [max: max, offset: offset]
            )

        } else if (params.name) {

            students = Student.findAllByNameIlike(
                "%${params.name}%",
                [max: max, offset: offset]
            )

        } else if (params.email) {

            students = Student.findAllByEmailIlike(
                "%${params.email}%",
                [max: max, offset: offset]
            )

        } else {

            students = Student.list(
                max: max,
                offset: offset
            )
        }

        def total = Student.count()
        def page = (offset / max) + 1

        respond([
            total: total,
            page : page,
            data : students
        ])
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

    def gpa(Long id) {

        Student student = Student.get(id)

        if (!student) {
            response.status = 404
            respond([
                error: "Student not found"
            ])
            return
        }

        def gpa = enrollmentService.calculateGpa(id)

        respond([
            studentId: id,
            gpa      : gpa
        ])
    }

    @Override
def save() {
    def json = request.JSON

    Student student = new Student(
        name: json.name,
        email: json.email,
        studentNumber: json.studentNumber
    )

    if (!student.validate()) {
        response.status = 400
        respond([
            errors: student.errors.allErrors.collect { error ->
                [
                    field  : error.field,
                    message: message(error: error)
                ]
            }
        ])
        return
    }

    student.save()

    response.status = 201
    respond([
        data: [
            id           : student.id,
            name         : student.name,
            email        : student.email,
            studentNumber: student.studentNumber
        ]
    ])
}
}