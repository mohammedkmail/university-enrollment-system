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
    <div class="container">

        <div class="navbar-nav">

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