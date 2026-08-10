<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'student.label', default: 'Student')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>

<a href="#list-student" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}">
                <g:message code="default.home.label"/>
            </a>
        </li>

        <li>
            <g:link class="create" action="create">
                <g:message code="default.new.label" args="[entityName]"/>
            </g:link>
        </li>
    </ul>
</div>

<div id="list-student" class="content scaffold-list" role="main">

    <h1>
        <g:message code="default.list.label" args="[entityName]"/>
    </h1>

    <g:if test="${flash.message}">
        <div class="message" role="status">
            ${flash.message}
        </div>
    </g:if>

    <section class="row">
        <div class="col-12">

            <g:form action="index" method="GET">
                <div class="mb-3">
                    <g:textField
                        name="name"
                        value="${params.name}"
                        placeholder="Search by name"/>

                    <g:submitButton
                        name="search"
                        value="Search"
                        class="btn btn-primary"/>
                </div>
            </g:form>

            <f:table
                class="scaffold table table-striped table-sm"
                controller="${controllerName}"
                collection="${studentList}"
                properties="name,email"/>

            <g:if test="${studentCount > params.int('max')}">
                <div class="btn-toolbar mb-3"
                     role="toolbar"
                     aria-label="Toolbar with button groups">

                    <g:paginate
                        activeClass="active"
                        class="btn"
                        total="${studentCount ?: 0}"
                        params="[name: params.name]"/>

                </div>
            </g:if>

        </div>
    </section>

</div>

</body>
</html>