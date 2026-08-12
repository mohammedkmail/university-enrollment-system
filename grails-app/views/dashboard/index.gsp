<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Dashboard</title>
</head>

<body>

<div class="container mt-4">

    <h1>Dashboard</h1>

    <div class="row">

        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Students</h5>
                    <p class="card-text">${totalStudents}</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Courses</h5>
                    <p class="card-text">${totalCourses}</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">Total Enrollments</h5>
                    <p class="card-text">${totalEnrollments}</p>
                </div>
            </div>
        </div>

    </div>

</div>

</body>
</html>