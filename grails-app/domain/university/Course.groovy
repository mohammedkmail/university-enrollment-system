package university

import grails.rest.Resource

@Resource(uri = '/api/courses')
class Course {

    String title
    String code
    Integer creditHours

    static responseFormats = ['json', 'html']

    static constraints = {
        code unique: true
        creditHours range: 1..6
    }

    static hasMany = [enrollments: Enrollment]
}