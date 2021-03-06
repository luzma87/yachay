<%@ page import="yachay.parametros.geografia.Parroquia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'parroquia.label', default: 'Parroquia')}"/>
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
            <g:hasErrors bean="${parroquiaInstance}">
                <div class="errors">
                    <g:renderErrors bean="${parroquiaInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="canton"><g:message code="parroquia.canton.label"
                                                                   default="Canton"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: parroquiaInstance, field: 'canton', 'errors')}">
                                    <g:select class="field ui-widget-content ui-corner-all" name="canton.id"
                                              title="canton" from="${yachay.parametros.geografia.Canton.list()}" optionKey="id"
                                              value="${parroquiaInstance?.canton?.id}" noSelection="['null': '']"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="numero"><g:message code="parroquia.numero.label"
                                                                   default="Numero"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: parroquiaInstance, field: 'numero', 'errors')}">
                                    <g:textField name="numero" id="numero" title="numero"
                                                 class="field ui-widget-content ui-corner-all"
                                                 value="${parroquiaInstance?.numero}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="parroquia.nombre.label"
                                                                   default="Nombre"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: parroquiaInstance, field: 'nombre', 'errors')}">
                                    <g:textField name="nombre" id="nombre" title="nombre"
                                                 class="field ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="63" value="${parroquiaInstance?.nombre}"/>
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
