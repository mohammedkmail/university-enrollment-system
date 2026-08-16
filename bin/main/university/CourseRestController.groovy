package university

import grails.rest.RestfulController

class CourseRestController extends RestfulController<Course> {

    static responseFormats = ['json', 'html']

    CourseRestController() {
        super(Course)
    }
}