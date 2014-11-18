

<%@ page import="yachay.parametros.ItemCatalogo" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'itemCatalogo.label', default: 'ItemCatalogo')}" />
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
            <g:hasErrors bean="${itemCatalogoInstance}">
            <div class="errors">
                <g:renderErrors bean="${itemCatalogoInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${itemCatalogoInstance?.id}" />
                <g:hiddenField name="version" value="${itemCatalogoInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="nombre"><g:message code="itemCatalogo.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'nombre', 'errors')}">
                                    <g:textField  name="nombre" id="nombre" title="Descripción del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="${itemCatalogoInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="catalogo"><g:message code="itemCatalogo.catalogo.label" default="Catalogo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'catalogo', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="catalogo.id" title="Catalogo" from="${yachay.parametros.Catalogo.list()}" optionKey="id" value="${itemCatalogoInstance?.catalogo?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="codigo"><g:message code="itemCatalogo.codigo.label" default="Codigo" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'codigo', 'errors')}">
                                    <g:textField  name="codigo" id="codigo" title="Codigo" class="field required ui-widget-content ui-corner-all" value="${itemCatalogoInstance?.codigo}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="descripcion"><g:message code="itemCatalogo.descripcion.label" default="Descripcion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'descripcion', 'errors')}">
                                    <g:textField  name="descripcion" id="descripcion" title="Descripcion" class="field required ui-widget-content ui-corner-all" value="${itemCatalogoInstance?.descripcion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="estado"><g:message code="itemCatalogo.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'estado', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="estado" title="Estado" id="estado" value="${fieldValue(bean: itemCatalogoInstance, field: 'estado')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="orden"><g:message code="itemCatalogo.orden.label" default="Orden" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'orden', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="orden" title="Orden" id="orden" value="${fieldValue(bean: itemCatalogoInstance, field: 'orden')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="original"><g:message code="itemCatalogo.original.label" default="Original" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: itemCatalogoInstance, field: 'original', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="original" title="Original" id="original" value="${fieldValue(bean: itemCatalogoInstance, field: 'original')}" />
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
