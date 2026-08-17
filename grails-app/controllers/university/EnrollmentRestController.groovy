package university

import grails.rest.RestfulController

class EnrollmentRestController extends RestfulController<Enrollment> {

    static responseFormats = ['json', 'html']

    EnrollmentRestController() {
        super(Enrollment)
    }

    // GET /api/enrollments
    // Pagination:
    // ?max=10&offset=0
    @Override
    def index() {

        def max = params.int('max') ?: 10
        def offset = params.int('offset') ?: 0

        if (max < 1) {
            max = 10
        }

        if (max > 100) {
            max = 100
        }

        if (offset < 0) {
            offset = 0
        }

        def enrollments = Enrollment.list(
            max: max,
            offset: offset
        )

        def total = Enrollment.count()

        def page = offset.intdiv(max) + 1

        respond([
            total: total,
            page : page,
            data : enrollments
        ])
    }
}