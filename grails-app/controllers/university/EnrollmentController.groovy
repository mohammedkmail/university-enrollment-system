package university

class EnrollmentController {

    def index() { 
        [enrollmentList: Enrollment.list()]

        
    }

    def create(){
        [students: Student.list(), courses: Course.list()]

    }

    def save() {
    Student student = Student.get(params.long('studentId'))
    Course course = Course.get(params.long('courseId'))

    def existingEnrollment = Enrollment.findByStudentAndCourse(student, course)

    if (existingEnrollment) {
        redirect action: 'create'
        return
    }

    def enrollment = new Enrollment(
        student: student,
        course: course,
        grade: params.grade
    )

    enrollment.save()

    redirect action: 'index'
}




    def delete(Long id) {
    Enrollment.withTransaction {
        def enrollment = Enrollment.get(id)

        if (enrollment) {
            enrollment.delete()
        }
    }

    redirect action: 'index'
}
}
