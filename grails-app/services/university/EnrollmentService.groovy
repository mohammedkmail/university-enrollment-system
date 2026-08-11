package university

import grails.gorm.transactions.Transactional

class EnrollmentService {

    List<Enrollment> listEnrollments() {
        log.info("listEnrollments started")
        Enrollment.list()
    }

    Map getCreateData() {
        log.info("getCreateData started")

        [
                students: Student.list(),
                courses : Course.list()
        ]
    }

    @Transactional
    Enrollment saveEnrollment(Long studentId, Long courseId, String grade) {
        log.info("saveEnrollment started")

        Student student = Student.get(studentId)
        Course course = Course.get(courseId)

        def existingEnrollment =
                Enrollment.findByStudentAndCourse(student, course)

        if (existingEnrollment) {
            return null
        }

        def enrollment = new Enrollment(
                student: student,
                course: course,
                grade: grade
        )

        enrollment.save(failOnError: true)

        return enrollment
    }

    @Transactional
    void deleteEnrollment(Long id) {
        log.info("deleteEnrollment started")

        def enrollment = Enrollment.get(id)

        if (enrollment) {
            enrollment.delete()
        }
    }

    @Transactional
Enrollment enroll(Long studentId, Long courseId, String grade) {
    log.info("enroll started")

    Student student = Student.get(studentId)
    Course course = Course.get(courseId)

    if (!student || !course) {
        return null
    }

    def existingEnrollment =
            Enrollment.findByStudentAndCourse(student, course)

    if (existingEnrollment) {
        return null
    }

    def enrollment = new Enrollment(
            student: student,
            course: course,
            grade: grade
    )

    enrollment.save(failOnError: true)

    return enrollment
}

    @Transactional
    void unenroll(Long enrollmentId) {
        log.info("unenroll started")

        def enrollment = Enrollment.get(enrollmentId)

        if (enrollment) {
            enrollment.delete()
        }
    }

    double calculateGpa(Long studentId) {
        log.info("calculateGpa started")

        Student student = Student.get(studentId)

        if (!student) {
            return 0.0
        }

        def enrollments = Enrollment.findAllByStudent(student)

        def grades = enrollments*.grade
                .findAll { it != null }
                .collect { it.toDouble() }

        if (!grades) {
            return 0.0
        }

        return grades.sum() / grades.size()
    }
}