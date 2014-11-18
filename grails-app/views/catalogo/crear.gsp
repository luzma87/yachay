<%@ page import="yachay.parametros.Catalogo" %>
            <g:form >
                <input type="hidden" name="id" id="id_ctlg" value="${catalogoInstance?.id}" />
                %{--<div class="dialog">--}%
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
                                    %{--<g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="estado" title="Estado" id="estado" from="${catalogoInstance.constraints.estado.inList}" value="${catalogoInstance?.estado}" valueMessagePrefix="catalogo.estado"  />--}%
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="estado" title="Estado"
                                              id="estado" from="${[1:'Activo', 0: 'Inactivo'].entrySet()}" optionKey='key' optionValue='value' value="${catalogoInstance?.estado}" valueMessagePrefix="catalogo.estado"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                %{--</div>--}%
            </g:form>
