package university

class Student {

    String name
    String email
    String studentNumber

    byte[] profilePhoto
    String contentType

    static responseFormats = ['json', 'html']

    static constraints = {
        name blank: false
        email email: true, unique: true
        studentNumber nullable: false

        profilePhoto nullable: true
        contentType nullable: true
    }

    static hasMany = [enrollments: Enrollment]

    static mapping = {
        profilePhoto sqlType: 'LONGBLOB'
    }
}