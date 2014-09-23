
<%@ page import="yachay.proyectos.DocumentoProceso" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'documentoProceso.label', default: 'DocumentoProceso')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>

    <body>

        <div class="dialog" title="${title}">


            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list" style="width: 600px;">
                    <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-top ui-helper-clearfix">
                        %{--<div id="example_length" class="dataTables_length">--}%
                            %{--<g:message code="show" default="Show" />&nbsp;--}%
                            %{--<g:select from="${[10,20,30,40,50,60,70,80,90,100]}" name="max" value="${params.max}" />&nbsp;--}%
                            %{--<g:message code="entries" default="entries" />--}%


                            %{--<g:select--}%
                                %{--from="['asc':message(code:'asc', default:'Ascendente'), 'desc':message(code:'desc', default:'Descendente')]"--}%
                                %{--name="order"--}%
                                %{--optionKey="key"--}%
                                %{--optionValue="value"--}%
                                %{--value="${params.order}"--}%
                                %{--class="ui-widget-content ui-corner-all"/>--}%
                        %{--</div>--}%
                    </div>
                    <table style="width: 600px;">
                        <thead>
                            <tr>
                                
                                <tdn:sortableColumn property="id" class="ui-state-default"
                                                  title="${message(code: 'documentoProceso.id.label', default: 'Id')}" />
                                
                                <th class="ui-state-default"><g:message code="documentoProceso.liquidacion.label"
                                               default="Liquidacion" /></th>
                                
                                <tdn:sortableColumn property="descripcion" class="ui-state-default"
                                                  title="${message(code: 'documentoProceso.descripcion.label', default: 'Descripcion')}" />
                                
                                <th class="ui-state-default"><g:message code="documentoProceso.tipo.label"
                                               default="Tipo" /></th>
                                
                                <tdn:sortableColumn property="clave" class="ui-state-default"
                                                  title="${message(code: 'documentoProceso.clave.label', default: 'Clave')}" />
                                
                                <tdn:sortableColumn property="resumen" class="ui-state-default"
                                                  title="${message(code: 'documentoProceso.resumen.label', default: 'Resumen')}" />
                                
                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${documentoProcesoInstanceList}" status="i" var="documentoProcesoInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    
                                    <td><g:link action="show"
                                                id="${documentoProcesoInstance.id}">${fieldValue(bean: documentoProcesoInstance, field: "id")}</g:link></td>
                                    
                                    <td>${fieldValue(bean: documentoProcesoInstance, field: "liquidacion")}</td>
                                    
                                    <td>${fieldValue(bean: documentoProcesoInstance, field: "descripcion")}</td>
                                    
                                    <td>${fieldValue(bean: documentoProcesoInstance, field: "tipo")}</td>
                                    
                                    <td>${fieldValue(bean: documentoProcesoInstance, field: "clave")}</td>
                                    
                                    <td>${fieldValue(bean: documentoProcesoInstance, field: "resumen")}</td>
                                    
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>

                <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bottom ui-helper-clearfix paginateButtons">
                    <tdn:paginate total="${documentoProcesoInstanceTotal}" />
                </div>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function() {
                $(".button").button();
                $(".home").button("option", "icons", {primary:'ui-icon-home'});
                $(".create").button("option", "icons", {primary:'ui-icon-document'});

                $(".edit").button("option", "icons", {primary:'ui-icon-pencil'});
                $(".delete").button("option", "icons", {primary:'ui-icon-trash'}).click(function() {
                    if(confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
