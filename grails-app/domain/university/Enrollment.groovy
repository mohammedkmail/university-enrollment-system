package university


class Enrollment {

    Student student
    Course course
    String grade
    Date enrolledAt = new Date()

    static responseFormats = ['json', 'html']

    static belongsTo = [
        student: Student,
        course: Course
    ]

    static constraints = {
        grade nullable: true
    }
}