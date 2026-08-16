package university

import grails.rest.RestfulController

class EnrollmentRestController extends RestfulController<Enrollment> {

    static responseFormats = ['json', 'html']

    EnrollmentRestController() {
        super(Enrollment)
    }
}