package university

class Course {

    String title
    String code
    Integer creditHours

    static constraints = {
        code unique: true
        creditHours range: 1..6
    }

    static hasMany=[enrollments:Enrollment]
}