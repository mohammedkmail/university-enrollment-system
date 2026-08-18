package university


import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_USER', 'ROLE_ADMIN'])
class EnrollmentController {

    EnrollmentService enrollmentService

    def index() {
        [enrollmentList: enrollmentService.listEnrollments()]
    }

    def create() {
        enrollmentService.getCreateData()
    }

    def save() {

        def enrollment = enrollmentService.saveEnrollment(
                params.long('studentId'),
                params.long('courseId'),
                params.grade
        )

        if (!enrollment) {
            redirect action: 'create'
            return
        }

        redirect action: 'index'
    }

    def enroll() {

    def enrollment = enrollmentService.enroll(
            params.long('studentId'),
            params.long('courseId'),
            params.grade
    )

    if (!enrollment) {
        redirect action: 'create'
        return
    }

    redirect action: 'index'
}

    def delete(Long id) {
        enrollmentService.deleteEnrollment(id)

        redirect action: 'index'
    }

    def unenroll(Long id) {
        enrollmentService.unenroll(id)

        redirect action: 'index'
    }
}