<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#create-student" class="visually-hidden-focusable" tabindex="-1">
                <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
            </a>

            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <ul class="navbar-nav container-fluid">
                    <li class="nav-item">
                        <a class="nav-link btn" aria-label="Home" href="${createLink(uri: '/')}">
                            <i class="bi-house"></i>
                            <g:message code="default.home.label"/>
                        </a>
                    </li>

                    <li class="nav-item me-lg-auto">
                        <g:link class="nav-link btn" aria-label="List" action="index">
                            <i class="bi-database"></i>
                            <g:message code="default.list.label" args="[entityName]" />
                        </g:link>
                    </li>
                </ul>
            </nav>
        </section>

        <section class="row">
            <div id="create-student" class="col-12 content scaffold-create" role="main">

                <h1>
                    <g:message code="default.create.label" args="[entityName]" />
                </h1>

                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <g:hasErrors bean="${this.student}">
                    <ul class="alert alert-danger list-unstyled" role="alert">
                        <g:eachError bean="${this.student}" var="error">
                            <li>
                                <i class="bi-exclamation-circle"></i>
                                <g:message error="${error}"/>
                            </li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>

                <g:form resource="${this.student}" controller="${controllerName}" method="POST">

                    <fieldset class="form">

                        <div class="mb-3">
                            <label class="form-label">Name</label>

                            <g:textField
                                    name="name"
                                    value="${student?.name}"
                                    class="form-control"/>

                            <g:hasErrors bean="${student}" field="name">
                                <div class="text-danger">
                                    <g:fieldError bean="${student}" field="name"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Email</label>

                            <g:textField
                                    name="email"
                                    value="${student?.email}"
                                    class="form-control"/>

                            <g:hasErrors bean="${student}" field="email">
                                <div class="text-danger">
                                    <g:fieldError bean="${student}" field="email"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Student Number</label>

                            <g:textField
                                    name="studentNumber"
                                    value="${student?.studentNumber}"
                                    class="form-control"/>

                            <g:hasErrors bean="${student}" field="studentNumber">
                                <div class="text-danger">
                                    <g:fieldError bean="${student}" field="studentNumber"/>
                                </div>
                            </g:hasErrors>
                        </div>

                    </fieldset>

                    <fieldset class="bg-body-tertiary">
                        <button class="btn btn-outline-primary" type="submit">
                            <i class="bi-floppy"></i>
                            ${message(code: 'default.button.create.label', default: 'Create')}
                        </button>
                    </fieldset>

                </g:form>

            </div>
        </section>
    </div>
</div>
</body>
</html>