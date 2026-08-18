<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <title>
        <g:layoutTitle default="University System"/>
    </title>

    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
          rel="stylesheet">
</head>

<body>

<header class="container mt-3">
    <h1>University Enrollment System</h1>
</header>

<nav class="navbar navbar-expand-md">
    <div class="container d-flex justify-content-between align-items-center">

        <!-- Navigation Links -->
        <div class="navbar-nav gap-2">

            <g:link uri="/" class="nav-link">
                Home
            </g:link>

            <g:link controller="student" action="index" class="nav-link">
                Students
            </g:link>

            <g:link controller="course" action="index" class="nav-link">
                Courses
            </g:link>

            <g:link controller="enrollment" action="index" class="nav-link">
                Enrollments
            </g:link>

            <g:link controller="dashboard" action="index" class="nav-link">
                Dashboard
            </g:link>

        </div>


        <!-- User Section -->
        <div class="d-flex align-items-center gap-3">

            <sec:ifLoggedIn>
                <span class="navbar-text">
                    Welcome, <sec:loggedInUserInfo field="username"/>
                </span>

                <a href="${createLink(uri: '/logoff')}"
                   class="btn btn-danger">
                    Logout
                </a>
            </sec:ifLoggedIn>

            <sec:ifNotLoggedIn>
                <a href="${createLink(uri: '/login/auth')}"
                   class="btn btn-primary">
                    Login
                </a>
            </sec:ifNotLoggedIn>


            <sec:ifAnyGranted roles="ROLE_ADMIN">
               <span>ADMIN ROLE ACTIVE</span>
            </sec:ifAnyGranted>
        </div>

    </div>
</nav>

<main>
    <g:layoutBody/>
</main>

<footer class="container mt-4">
    <p>University Enrollment System</p>
</footer>

<asset:javascript src="application.js"/>

</body>
</html>