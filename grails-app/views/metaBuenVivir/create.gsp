<%@ page import="yachay.proyectos.MetaBuenVivir" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'metaBuenVivir.label', default: 'MetaBuenVivir')}"/>
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
            <g:hasErrors bean="${metaBuenVivirInstance}">
                <div class="errors">
                    <g:renderErrors bean="${metaBuenVivirInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="politica"><g:message code="metaBuenVivir.politica.label"
                                                                     default="Politica"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: metaBuenVivirInstance, field: 'politica', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all"
                                              name="politica.id" title="Meta del buen vivir"
                                              from="${yachay.parametros.proyectos.PoliticaBuenVivir.list()}" optionKey="id"
                                              value="${metaBuenVivirInstance?.politica?.id}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="metaBuenVivir.codigo.label"
                                                                   default="Codigo"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: metaBuenVivirInstance, field: 'codigo', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all"
                                                 name="codigo" title="Código de la meta" id="codigo"
                                                 value="${fieldValue(bean: metaBuenVivirInstance, field: 'codigo')}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="metaBuenVivir.descripcion.label"
                                                                        default="Descripcion"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: metaBuenVivirInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" id="descripcion" title="Descripción de la meta"
                                                 class="field ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="127" value="${metaBuenVivirInstance?.descripcion}"/>
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
