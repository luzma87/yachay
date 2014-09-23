<%@ page import="yachay.parametros.proyectos.PoliticasProyecto" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'politicasProyecto.label', default: 'PoliticasProyecto')}"/>
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
    <g:hasErrors bean="${politicasProyectoInstance}">
        <div class="errors">
            <g:renderErrors bean="${politicasProyectoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${politicasProyectoInstance?.id}"/>
        <g:hiddenField name="version" value="${politicasProyectoInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="politicaAplicaProyecto"><g:message
                                code="politicasProyecto.politicaAplicaProyecto.label"
                                default="Politica Aplica Proyecto"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: politicasProyectoInstance, field: 'politicaAplicaProyecto', 'errors')}">
                        <g:select class="field ui-widget-content ui-corner-all" name="politicaAplicaProyecto.id"
                                  title="politicaAplicaProyecto" from="${yachay.parametros.proyectos.PoliticaAplicaProyecto.list()}"
                                  optionKey="id" value="${politicasProyectoInstance?.politicaAplicaProyecto?.id}"
                                  noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="proyecto"><g:message code="politicasProyecto.proyecto.label"
                                                         default="Proyecto"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: politicasProyectoInstance, field: 'proyecto', 'errors')}">
                        <g:select class="field ui-widget-content ui-corner-all" name="proyecto.id" title="proyecto"
                                  from="${yachay.proyectos.Proyecto.list()}" optionKey="id"
                                  value="${politicasProyectoInstance?.proyecto?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="porcentaje"><g:message code="politicasProyecto.porcentaje.label"
                                                           default="Porcentaje"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: politicasProyectoInstance, field: 'porcentaje', 'errors')}">
                        <g:textField class="field required ui-widget-content ui-corner-all" name="porcentaje"
                                     title="porcentaje" id="porcentaje"
                                     value="${fieldValue(bean: politicasProyectoInstance, field: 'porcentaje')}"/>
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
