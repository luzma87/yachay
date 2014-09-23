<%@ page import="yachay.parametros.proyectos.ObjetivoGobiernoResultado" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'objetivoGobiernoResultado.label', default: 'ObjetivoGobiernoResultado')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${objetivoGobiernoResultadoInstance}">
        <div class="errors">
            <g:renderErrors bean="${objetivoGobiernoResultadoInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="descripcion"><g:message code="objetivoGobiernoResultado.descripcion.label"
                                                            default="Descripcion"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: objetivoGobiernoResultadoInstance, field: 'descripcion', 'errors')}">
                        <g:textField name="descripcion" id="descripcion" title="Descripción"
                                     class="field required ui-widget-content ui-corner-all"
                                     value="${objetivoGobiernoResultadoInstance?.descripcion}"/>
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
