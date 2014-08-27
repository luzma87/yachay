<%@ page import="app.ObjetivoUnidad" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'objetivoUnidad.label', default: 'ObjetivoUnidad')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            </span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
        </div>

        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${objetivoUnidadInstance}">
                <div class="errors">
                    <g:renderErrors bean="${objetivoUnidadInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="objetivoUnidad.descripcion.label" default="Descripcion"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: objetivoUnidadInstance, field: 'descripcion', 'errors')}">
                                    <g:textArea class="field required ui-widget-content ui-corner-all" minLenght="3" maxLenght="511" name="descripcion" id="descripcion" title="Descripcion" cols="40" rows="5" value="${objetivoUnidadInstance?.descripcion}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="orden"><g:message code="objetivoUnidad.orden.label" default="Orden"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: objetivoUnidadInstance, field: 'orden', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="orden" title="Orden" id="orden" value="${fieldValue(bean: objetivoUnidadInstance, field: 'orden')}"/>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>

                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
