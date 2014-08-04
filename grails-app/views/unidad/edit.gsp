<%@ page import="app.Unidad" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'unidad.label', default: 'Unidad')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${unidadInstance}">
        <div class="errors">
            <g:renderErrors bean="${unidadInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${unidadInstance?.id}"/>
        <g:hiddenField name="version" value="${unidadInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="codigo"><g:message code="unidad.codigo.label" default="Codigo"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: unidadInstance, field: 'codigo', 'errors')}">
                        <g:textField name="codigo" id="codigo" title="codigo"
                                     class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="7"
                                     value="${unidadInstance?.codigo}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="descripcion"><g:message code="unidad.descripcion.label"
                                                            default="Descripcion"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: unidadInstance, field: 'descripcion', 'errors')}">
                        <g:textField name="descripcion" id="descripcion" title="descripcion"
                                     class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63"
                                     value="${unidadInstance?.descripcion}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
