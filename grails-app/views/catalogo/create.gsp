

<%@ page import="yachay.parametros.Catalogo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'catalogo.label', default: 'Catalogo')}" />
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
            <g:hasErrors bean="${catalogoInstance}">
            <div class="errors">
                <g:renderErrors bean="${catalogoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="catalogo.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: catalogoInstance, field: 'nombre', 'errors')}">
                                    <g:textField  name="nombre" id="nombre" title="Nombre del catálogo" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${catalogoInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="catalogo.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: catalogoInstance, field: 'codigo', 'errors')}">
                                    <g:textField  name="codigo" id="codigo" title="Código del catálogo" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="8" value="${catalogoInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="estado"><g:message code="catalogo.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: catalogoInstance, field: 'estado', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="estado" title="Estado del catálogo" id="estado" from="${catalogoInstance.constraints.estado.inList}" value="${fieldValue(bean: catalogoInstance, field: 'estado')}" valueMessagePrefix="catalogo.estado"  />
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
