<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Enrollment</title>
</head>

<body>
<div class="container mt-4">

    <h1>Create Enrollment</h1>

    <g:form controller="enrollment" action="save" method="POST">

        <div class="mb-3">
            <label>Student</label>

            <g:select
                name="studentId"
                from="${students}"
                optionKey="id"
                optionValue="name"
                class="form-select"/>
        </div>

        <div class="mb-3">س
            <label>Course</label>

            <g:select
                name="courseId"
                from="${courses}"
                optionKey="id"
                optionValue="title"
                class="form-select"/>
        </div>

        <div class="mb-3">
            <label>Grade</label>
            <g:textField name="grade" class="form-control"/>
        </div>

        <g:submitButton
            name="submit"
            value="Enroll"
            class="btn btn-primary"/>

    </g:form>

</div>
</body>
</html>