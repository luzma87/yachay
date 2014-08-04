

<%@ page import="app.Asignacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'asignacion.label', default: 'Asignacion')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${asignacionInstance}">
            <div class="errors">
                <g:renderErrors bean="${asignacionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${asignacionInstance?.id}" />
                <g:hiddenField name="version" value="${asignacionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="anio"><g:message code="asignacion.anio.label" default="Anio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'anio', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="anio.id" title="Año o “ejercicio”" from="${app.Anio.list()}" optionKey="id" value="${asignacionInstance?.anio?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fuente"><g:message code="asignacion.fuente.label" default="Fuente" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'fuente', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="fuente.id" title="Fuente de financiamiento" from="${app.Fuente.list()}" optionKey="id" value="${asignacionInstance?.fuente?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="marcoLogico"><g:message code="asignacion.marcoLogico.label" default="Marco Logico" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'marcoLogico', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="marcoLogico.id" title="Actividad del marco lógico" from="${app.MarcoLogico.list()}" optionKey="id" value="${asignacionInstance?.marcoLogico?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="actividad"><g:message code="asignacion.actividad.label" default="Actividad" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'actividad', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="actividad.id" title="Actividad de gasto corriente" from="${app.Actividad.list()}" optionKey="id" value="${asignacionInstance?.actividad?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="presupuesto"><g:message code="asignacion.presupuesto.label" default="Presupuesto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'presupuesto', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="presupuesto.id" title="Partida presupuestaria" from="${app.Presupuesto.list()}" optionKey="id" value="${asignacionInstance?.presupuesto?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipoGasto"><g:message code="asignacion.tipoGasto.label" default="Tipo Gasto" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'tipoGasto', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="tipoGasto.id" title="Tipo de gasto o grupo de gasto" from="${app.TipoGasto.list()}" optionKey="id" value="${asignacionInstance?.tipoGasto?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="planificado"><g:message code="asignacion.planificado.label" default="Planificado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'planificado', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="planificado" title="Planificado" id="planificado" value="${fieldValue(bean: asignacionInstance, field: 'planificado')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="redistribucion"><g:message code="asignacion.redistribucion.label" default="Redistribucion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: asignacionInstance, field: 'redistribucion', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="redistribucion" title="redistribuido (en + o en – según aumente o disminuya la asignación), lo asignado_real = valor_asignado + redistribuido." id="redistribucion" value="${fieldValue(bean: asignacionInstance, field: 'redistribucion')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
