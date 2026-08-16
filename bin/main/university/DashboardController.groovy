package university

class DashboardController {

    def index() {
        [
            totalStudents: Student.count(),
            totalCourses: Course.count(),
            totalEnrollments: Enrollment.count()
        ]
    }
}