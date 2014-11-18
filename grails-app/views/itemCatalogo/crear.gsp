

<%@ page import="yachay.parametros.ItemCatalogo" %>
            <g:form action="save" >
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
            </g:form>
