

<%@ page import="app.DocumentoProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'documentoProceso.label', default: 'DocumentoProceso')}" />
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
            <g:hasErrors bean="${documentoProcesoInstance}">
            <div class="errors">
                <g:renderErrors bean="${documentoProcesoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${documentoProcesoInstance?.id}" />
                <g:hiddenField name="version" value="${documentoProcesoInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="liquidacion"><g:message code="documentoProceso.liquidacion.label" default="Liquidacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'liquidacion', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="liquidacion.id" title="Liquidacion" from="${app.Liquidacion.list()}" optionKey="id" value="${documentoProcesoInstance?.liquidacion?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="descripcion"><g:message code="documentoProceso.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'descripcion', 'errors')}">
                                    <g:textField  name="descripcion" id="descripcion" title="Descripción del documento" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${documentoProcesoInstance?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="tipo"><g:message code="documentoProceso.tipo.label" default="Tipo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'tipo', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="tipo.id" title="Tipo del documento" from="${app.TipoDocumento.list()}" optionKey="id" value="${documentoProcesoInstance?.tipo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="clave"><g:message code="documentoProceso.clave.label" default="Clave" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'clave', 'errors')}">
                                    <g:textField  name="clave" id="clave" title="Palabras clave" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${documentoProcesoInstance?.clave}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="resumen"><g:message code="documentoProceso.resumen.label" default="Resumen" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'resumen', 'errors')}">
                                    <g:textArea class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="1024" name="resumen" id="resumen" title="Resumen" cols="40" rows="5" value="${documentoProcesoInstance?.resumen}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="documento"><g:message code="documentoProceso.documento.label" default="Documento" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: documentoProcesoInstance, field: 'documento', 'errors')}">
                                    <g:textField  name="documento" id="documento" title="Ruta del documento" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="${documentoProcesoInstance?.documento}" />
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
