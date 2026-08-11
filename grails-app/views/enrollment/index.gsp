<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Enrollments</title>
</head>

<body>

<div class="container mt-4">

    <h1>Enrollments</h1>

    <g:if test="${enrollmentList}">
        <ul>
            <g:each in="${enrollmentList}" var="enrollment">
                <li>
                    Student: ${enrollment.student.name}<br>
                    Course: ${enrollment.course.title}<br>
                    Grade: ${enrollment.grade}<br>
                    Enrolled At: ${enrollment.enrolledAt}<br>

                    <g:link
                        controller="enrollment"
                        action="unenroll"
                        params="[id: enrollment.id]"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                        Delete
                    </g:link>
                </li>
            </g:each>
        </ul>
    </g:if>

    <g:else>
        <p>No enrollments found.</p>
    </g:else>

</div>

</body>
</html>