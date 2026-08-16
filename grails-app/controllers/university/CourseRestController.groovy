package university

import grails.rest.RestfulController

class CourseRestController extends RestfulController<Course> {

    static responseFormats = ['json', 'html']

    CourseRestController() {
        super(Course)
    }

    @Override
    def index() {

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        def courses = Course.list(
            max: max,
            offset: offset
        )

        def total = Course.count()
        def page = (offset / max) + 1

        respond([
            total: total,
            page : page,
            data : courses
        ])
    }
}