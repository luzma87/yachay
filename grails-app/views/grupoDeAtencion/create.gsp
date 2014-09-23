

<%@ page import="app.proyectos.GrupoDeAtencion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'grupoDeAtencion.label', default: 'GrupoDeAtencion')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${grupoDeAtencionInstance}">
            <div class="errors">
                <g:renderErrors bean="${grupoDeAtencionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="tipoGrupo"><g:message code="grupoDeAtencion.tipoGrupo.label" default="Tipo Grupo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: grupoDeAtencionInstance, field: 'tipoGrupo', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="tipoGrupo.id" title="tipoGrupo" from="${app.TipoGrupo.list()}" optionKey="id" value="${grupoDeAtencionInstance?.tipoGrupo?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="proyecto"><g:message code="grupoDeAtencion.proyecto.label" default="Proyecto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: grupoDeAtencionInstance, field: 'proyecto', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="proyecto.id" title="proyecto" from="${app.Proyecto.list()}" optionKey="id" value="${grupoDeAtencionInstance?.proyecto?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="hombre"><g:message code="grupoDeAtencion.hombre.label" default="Hombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: grupoDeAtencionInstance, field: 'hombre', 'errors')}">
                                    <g:textField  name="hombre" id="hombre" title="hombre" class="field ui-widget-content ui-corner-all" value="${grupoDeAtencionInstance?.hombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mujer"><g:message code="grupoDeAtencion.mujer.label" default="Mujer" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: grupoDeAtencionInstance, field: 'mujer', 'errors')}">
                                    <g:textField  name="mujer" id="mujer" title="mujer" class="field ui-widget-content ui-corner-all" value="${grupoDeAtencionInstance?.mujer}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
