<%@ page import="yachay.parametros.TipoInstitucion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'tipoInstitucion.label', default: 'TipoInstitucion')}"/>
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
            <g:hasErrors bean="${tipoInstitucionInstance}">
                <div class="errors">
                    <g:renderErrors bean="${tipoInstitucionInstance}" as="list"/>
                </div>
            </g:hasErrors>
            <g:form action="save">
                <div class="dialog">
                    <table>
                        <tbody>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="codigo"><g:message code="tipoInstitucion.codigo.label"
                                                                   default="Codigo"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tipoInstitucionInstance, field: 'codigo', 'errors')}">
                                    <g:textField name="codigo" id="codigo" title="Código del tipo de institución"
                                                 class="field required ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="2" value="${tipoInstitucionInstance?.codigo}"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="descripcion"><g:message code="tipoInstitucion.descripcion.label"
                                                                        default="Descripcion"/></label>
                                </td>
                                <td valign="top"
                                    class="value ${hasErrors(bean: tipoInstitucionInstance, field: 'descripcion', 'errors')}">
                                    <g:textField name="descripcion" id="descripcion"
                                                 title="Descripción del tipo de institución"
                                                 class="field ui-widget-content ui-corner-all" minLenght="1"
                                                 maxLenght="63" value="${tipoInstitucionInstance?.descripcion}"/>
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
