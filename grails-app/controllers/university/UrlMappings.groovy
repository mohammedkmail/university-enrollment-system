package university

class UrlMappings {

    static mappings = {

        // =========================
        // Students REST API
        // =========================

        get "/api/students"(
            controller: "studentRest",
            action: "index"
        )

        post "/api/students"(
            controller: "studentRest",
            action: "save"
        )

        get "/api/students/$id"(
            controller: "studentRest",
            action: "show"
        )

        put "/api/students/$id"(
            controller: "studentRest",
            action: "update"
        )

        delete "/api/students/$id"(
            controller: "studentRest",
            action: "delete"
        )

        // Custom Student endpoints

        get "/api/students/$id/courses"(
            controller: "studentRest",
            action: "courses"
        )

        get "/api/students/$id/gpa"(
            controller: "studentRest",
            action: "gpa"
        )

        get "/api/students/list"(
            controller: "studentRest",
            action: "list"
        )


        // =========================
        // Courses REST API
        // =========================

        get "/api/courses"(
            controller: "courseRest",
            action: "index"
        )

        post "/api/courses"(
            controller: "courseRest",
            action: "save"
        )

        get "/api/courses/$id"(
            controller: "courseRest",
            action: "show"
        )

        put "/api/courses/$id"(
            controller: "courseRest",
            action: "update"
        )

        delete "/api/courses/$id"(
            controller: "courseRest",
            action: "delete"
        )


        // =========================
        // Enrollments REST API
        // =========================

        get "/api/enrollments"(
            controller: "enrollmentRest",
            action: "index"
        )

        post "/api/enrollments"(
            controller: "enrollmentRest",
            action: "save"
        )

        get "/api/enrollments/$id"(
            controller: "enrollmentRest",
            action: "show"
        )

        put "/api/enrollments/$id"(
            controller: "enrollmentRest",
            action: "update"
        )

        delete "/api/enrollments/$id"(
            controller: "enrollmentRest",
            action: "delete"
        )


        // =========================
        // Default Grails Routes
        // =========================

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")

        "500"(view: "/error")
        "404"(view: "/notFound")
    }
}