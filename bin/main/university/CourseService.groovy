package university

import grails.gorm.transactions.Transactional

@Transactional
class CourseService {

    Course get(Serializable id) {
        log.info("get started")
        Course.get(id)
    }

    List<Course> list(Map args) {
        log.info("list started")
        Course.list(args)
    }

    Long count() {
        log.info("count started")
        Course.count()
    }

    void delete(Serializable id) {
        log.info("delete started")

        Course course = Course.get(id)

        if (course) {
            course.delete()
        }
    }

    Course save(Course course) {
        log.info("save started")
        course.save(failOnError: true)
    }
}