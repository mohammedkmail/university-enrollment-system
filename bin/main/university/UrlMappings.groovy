package university

class UrlMappings {

    static mappings = {

        get "/api/students"(controller: "studentRest", action: "index")

        get "/api/students/$id/gpa"(
            controller: "studentRest",
            action: "gpa"
        )

        get "/api/students/$id/courses"(
            controller: "studentRest",
            action: "courses"
        )

        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}