

<%@ page import="yachay.parametros.geografia.Distrito" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'distrito.label', default: 'Distrito')}" />
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
            <g:hasErrors bean="${distritoInstance}">
            <div class="errors">
                <g:renderErrors bean="${distritoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="distrito.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: distritoInstance, field: 'codigo', 'errors')}">
                                    <g:textField  name="codigo" id="codigo" title="codigo" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="5" value="${distritoInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="distrito.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: distritoInstance, field: 'nombre', 'errors')}">
                                    <g:textField  name="nombre" id="nombre" title="nombre" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${distritoInstance?.nombre}" />
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
