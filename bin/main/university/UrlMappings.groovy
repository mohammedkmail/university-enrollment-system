package university

class UrlMappings {

    static mappings = {

        // Students API
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

        get "/api/students/$id/gpa"(
            controller: "studentRest",
            action: "gpa"
        )

        get "/api/students/$id/courses"(
            controller: "studentRest",
            action: "courses"
        )

        // Default Grails routes
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