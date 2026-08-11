<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#show-student" class="visually-hidden-focusable" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <ul class="navbar-nav container-fluid">
                    <li class="nav-item"><a class="nav-link btn" aria-label="Home" href="${createLink(uri: '/')}">
                        <i class="bi-house"></i> <g:message code="default.home.label"/></a>
                    </li>
                    <li class="nav-item"><g:link class="nav-link btn" aria-label="List" action="index">
                        <i class="bi-database"></i> <g:message code="default.list.label" args="[entityName]" /></g:link>
                    </li>
                    <li class="nav-item me-lg-auto">
                        <g:link class="nav-link btn" aria-label="List" action="create"><i class="bi-database-add"></i> <g:message code="default.new.label" args="[entityName]" /></g:link>
                    </li>
                </ul>
            </nav>
        </section>
        <section class="row">
            <div id="show-student" class="col-12 content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
                <g:if test="${flash.message}">
                    <div class="alert alert-primary" role="alert"><i class="bi-info-circle"></i> ${flash.message}</div>
                </g:if>
                <f:display bean="student" listClass="container" listItemClass="row mb-3" labelClass="form-label col-sm-3 text-sm-end" valueClass="col-sm-9" />
                
                <p>
                      <strong>GPA:</strong> ${gpa}
                </p>
                <h3>Enrolled Courses</h3>

                    <g:if test="${student.enrollments}">
                     <ul>
                          <g:each in="${student.enrollments}" var="enrollment">
                       <li>
                           Title: ${enrollment.course.title}<br>
                           Code: ${enrollment.course.code}<br>
                           Credit Hours: ${enrollment.course.creditHours}<br>
                           Grade: ${enrollment.grade}<br>
                           Enrolled At: ${enrollment.enrolledAt}
            </li>
        </g:each>
    </ul>
</g:if>

<g:else>
    <p>No enrolled courses.</p>
</g:else>
                <g:form resource="${this.student}" controller="${controllerName}" method="DELETE">
                    <fieldset class="bg-body-tertiary">
                        <g:link class="btn btn-outline-primary" action="edit" resource="${this.student}" controller="${controllerName}">
                            <i class="bi-pencil-square"></i> <g:message code="default.button.edit.label" default="Edit" />
                        </g:link>
                        <button class="btn btn-outline-primary" type="submit" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');">
                            <i class="bi-trash"></i> ${message(code: 'default.button.delete.label', default: 'Delete')}
                        </button>
                    </fieldset>
                </g:form>
            </div>
        </section>
    </div>
</div>
</body>
</html>
