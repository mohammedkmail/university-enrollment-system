package university

import grails.rest.Resource

@Resource(uri = '/api/students')
class Student {

    String name
    String email
    String studentNumber

    static responseFormats = ['json', 'html']

    static constraints = {
        name blank: false
        email email: true, unique: true
        studentNumber nullable: false
    }

    static hasMany = [enrollments: Enrollment]
}