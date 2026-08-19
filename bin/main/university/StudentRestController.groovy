package university
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class StudentRestController extends RestfulController<Student> {

    static responseFormats = ['json', 'html']

    EnrollmentService enrollmentService

    StudentRestController() {
        super(Student)
    }

    // GET /api/students
    // Filtering:
    // ?name=Ahmad
    // ?email=gmail
    // Pagination:
    // ?max=10&offset=0
    @Override
    def index() {

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        if (max < 1) {
            max = 10
        }

        if (max > 100) {
            max = 100
        }

        if (offset < 0) {
            offset = 0
        }

        def students
        def total

        if (params.name && params.email) {

            students = Student.findAllByNameIlikeAndEmailIlike(
                    "%${params.name}%",
                    "%${params.email}%",
                    [
                            max   : max,
                            offset: offset
                    ]
            )

            total = Student.countByNameIlikeAndEmailIlike(
                    "%${params.name}%",
                    "%${params.email}%"
            )

        } else if (params.name) {

            students = Student.findAllByNameIlike(
                    "%${params.name}%",
                    [
                            max   : max,
                            offset: offset
                    ]
            )

            total = Student.countByNameIlike(
                    "%${params.name}%"
            )

        } else if (params.email) {

            students = Student.findAllByEmailIlike(
                    "%${params.email}%",
                    [
                            max   : max,
                            offset: offset
                    ]
            )

            total = Student.countByEmailIlike(
                    "%${params.email}%"
            )

        } else {

            students = Student.list(
                    max: max,
                    offset: offset
            )

            total = Student.count()
        }

        def page = (offset.intdiv(max)) + 1

        respond([
                total: total,
                page : page,
                data : students
        ])
    }


    // GET /api/students/:id/courses
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


    // GET /api/students/:id/gpa
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


    // POST /api/students
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

        student.save(flush: true)

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


    
    def list() {

        def json = request.JSON

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        def students

        if (json.email) {

            students = Student.findAllByEmailIlike(
                    "%${json.email}%",
                    [
                            max   : max,
                            offset: offset
                    ]
            )

        } else {

            students = Student.list(
                    max: max,
                    offset: offset
            )
        }

        respond([
                data: students
        ])
    }


        @Secured(['ROLE_ADMIN'])
        @Override
        def delete() {
                super.delete()
        }


}