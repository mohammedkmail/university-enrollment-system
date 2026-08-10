package university

class Student {

    String name
    String email
    String studentNumber

    static constraints = {
        name blank: false
        email email: true, unique: true
        studentNumber nullable: false
    }

    static hasMany=[enrollments:Enrollment]
}