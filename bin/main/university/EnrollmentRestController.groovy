package university

import grails.rest.RestfulController

class EnrollmentRestController extends RestfulController<Enrollment> {

    static responseFormats = ['json', 'html']

    EnrollmentRestController() {
        super(Enrollment)
    }

    @Override
    def index() {

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        def enrollments = Enrollment.list(
            max: max,
            offset: offset
        )

        def total = Enrollment.count()
        def page = (offset / max) + 1

        respond([
            total: total,
            page : page,
            data : enrollments
        ])
    }
}