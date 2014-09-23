<%@ page import="yachay.parametros.TipoResponsable" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'tipoResponsable.label', default: 'TipoResponsable')}"/>
        <title><g:message code="default.create.label" args="[entityName]"/></title>
    </head>

    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message
                    code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                                   args="[entityName]"/></g:link></span>
        </div>

        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${tipoResponsableInstance}">
                <div class="errors">
                    <g:renderErrors bean="${tipoResponsableInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="tipoResponsable.codigo.label"
                                                                   default="Codigo"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tipoResponsableInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" id="codigo" title="Código del tipo de responsable"
                                                 class="field required ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="1" value="${tipoResponsableInstance?.codigo}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="tipoResponsable.descripcion.label"
                                                                        default="Descripcion"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tipoResponsableInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" id="descripcion"
                                                 title="Descripción del ripo de responsable"
                                                 class="field required ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="15" value="${tipoResponsableInstance?.descripcion}"/>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>

                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save"
                                                         value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
