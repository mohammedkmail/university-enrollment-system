<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'course.label', default: 'Course')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<div id="content" role="main">
    <div class="container">
        <section class="row">
            <a href="#create-course" class="visually-hidden-focusable" tabindex="-1">
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
            <div id="create-course" class="col-12 content scaffold-create" role="main">

                <h1>
                    <g:message code="default.create.label" args="[entityName]" />
                </h1>

                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <g:hasErrors bean="${this.course}">
                    <ul class="alert alert-danger list-unstyled" role="alert">
                        <g:eachError bean="${this.course}" var="error">
                            <li>
                                <i class="bi-exclamation-circle"></i>
                                <g:message error="${error}"/>
                            </li>
                        </g:eachError>
                    </ul>
                </g:hasErrors>

                <g:form resource="${this.course}" controller="${controllerName}" method="POST">

                    <fieldset class="form">

                        <div class="mb-3">
                            <label class="form-label">Title</label>

                            <g:textField
                                    name="title"
                                    value="${course?.title}"
                                    class="form-control"/>

                            <g:hasErrors bean="${course}" field="title">
                                <div class="text-danger">
                                    <g:fieldError bean="${course}" field="title"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Code</label>

                            <g:textField
                                    name="code"
                                    value="${course?.code}"
                                    class="form-control"/>

                            <g:hasErrors bean="${course}" field="code">
                                <div class="text-danger">
                                    <g:fieldError bean="${course}" field="code"/>
                                </div>
                            </g:hasErrors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Credit Hours</label>

                            <g:select
                                    name="creditHours"
                                    from="${1..6}"
                                    value="${course?.creditHours}"
                                    class="form-select"/>

                            <g:hasErrors bean="${course}" field="creditHours">
                                <div class="text-danger">
                                    <g:fieldError bean="${course}" field="creditHours"/>
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