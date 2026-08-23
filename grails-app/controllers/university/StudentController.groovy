package university

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class StudentController {

    EnrollmentService enrollmentService
    StudentService studentService

    static allowedMethods = [
            save: "POST",
            update: "PUT",
            delete: "DELETE"
    ]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def students
        def studentCount

        if (params.name) {
            students = Student.findAllByNameIlike(
                    "%${params.name}%",
                    [max: params.max, offset: params.offset ?: 0]
            )

            studentCount =
                    Student.countByNameIlike("%${params.name}%")
        } else {
            students = studentService.list(params)
            studentCount = studentService.count()
        }

        respond students, model: [studentCount: studentCount]
    }

    def show(Long id) {

    if (id == null) {
        notFound()
        return
    }

    Student student = studentService.get(id)

    if (student == null) {
        notFound()
        return
    }

    double gpa = enrollmentService.calculateGpa(id)

    [
        student: student,
        gpa: gpa
    ]
    }

    def create() {
        respond new Student(params)
    }

    def save(Student student) {
        if (student == null) {
            notFound()
            return
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            log.error("Error saving student", e)

            respond student.errors, view: 'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.created.message',
                        args: [
                                message(
                                        code: 'student.label',
                                        default: 'Student'
                                ),
                                student.id
                        ]
                )
                redirect student
            }

            '*' {
                respond student, [status: CREATED]
            }
        }
    }

    def edit(Long id) {
        respond studentService.get(id)
    }

    def update(Student student) {
        if (student == null) {
            notFound()
            return
        }

        def upload = request.getFile('profilePhoto')

        if (upload && !upload.empty) {

            if (!upload.contentType.startsWith('image/')) {
                flash.message = "Only image files are allowed."
                respond student, view: 'edit'
                return
            }

            if (upload.size >= 2 * 1024 * 1024) {
                flash.message = "Image size must be less than 2MB."
                respond student, view: 'edit'
                return
            }

            student.profilePhoto = upload.bytes
            student.contentType = upload.contentType
        }

        try {
            studentService.save(student)
        } catch (ValidationException e) {
            log.error("Error updating student", e)

            respond student.errors, view: 'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.updated.message',
                        args: [
                                message(
                                        code: 'student.label',
                                        default: 'Student'
                                ),
                                student.id
                        ]
                )
                redirect student
            }

            '*' {
                respond student, [status: OK]
            }
        }
    }

    def renderPhoto(Long id) {
        Student student = studentService.get(id)

        if (!student || !student.profilePhoto) {
            render status: NOT_FOUND
            return
        }

        response.contentType = student.contentType
        response.outputStream << student.profilePhoto
        response.outputStream.flush()
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.deleted.message',
                        args: [
                                message(
                                        code: 'student.label',
                                        default: 'Student'
                                ),
                                id
                        ]
                )

                redirect action: "index", method: "GET"
            }

            '*' {
                render status: NO_CONTENT
            }
        }
    }

    def testGpa() {
        render enrollmentService.calculateGpa(1L)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(
                        code: 'default.not.found.message',
                        args: [
                                message(
                                        code: 'student.label',
                                        default: 'Student'
                                ),
                                params.id
                        ]
                )

                redirect action: "index", method: "GET"
            }

            '*' {
                render status: NOT_FOUND
            }
        }
    }


    def testCleanupJob() {
    new InactiveStudentCleanupJob().execute()
    render "Cleanup job executed. Check the logs."
}
}