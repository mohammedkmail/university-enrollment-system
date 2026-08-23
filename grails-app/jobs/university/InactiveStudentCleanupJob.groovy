package university

class InactiveStudentCleanupJob {

    static triggers = {
        cron(
            name: 'inactiveStudentCleanup',
            cronExpression: '0 0 1 * * ?'
        )
    }

    def execute() {

        Date startTime = new Date()
        log.info("InactiveStudentCleanup job started at ${startTime}")

        Date thirtyDaysAgo = new Date() - 30

        def inactiveStudents = Student.list().findAll { student ->
            !student.enrollments?.any { enrollment ->
                enrollment.enrolledAt >= thirtyDaysAgo
            }
        }

        inactiveStudents.each { student ->
            log.info("[DRY RUN] Would delete Student ${student.id} (${student.name})")
        }

        Date endTime = new Date()
        log.info("InactiveStudentCleanup job ended at ${endTime}")
    }
}