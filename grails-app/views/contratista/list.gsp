
<%@ page import="app.Contratista" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'contratista.label', default: 'Contratista')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>

    <body>

        <div class="dialog" title="Lista de Contratistas">


            <div class="body" style="margin-left: 150px">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>
                <div class="list" style="width: 700px;">
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
                    <table style="width: 700px;">
                        <thead>
                            <tr>
                                
                                <tdn:sortableColumn property="id" class="ui-state-default"
                                                  title="${message(code: 'contratista.id.label', default: 'Id')}" />

                                <tdn:sortableColumn property="ruc" class="ui-state-default"
                                                    title="${message(code: 'contratista.ruc.label', default: 'Ruc')}" />

                                <tdn:sortableColumn property="nombreCont" class="ui-state-default"
                                                    title="${message(code: 'contratista.nombreCont.label', default: 'Contratista')}" />


                                <tdn:sortableColumn property="nombre" class="ui-state-default"
                                                    title="${message(code: 'contratista.nombre.label', default: 'Nombre')}" />



                                <tdn:sortableColumn property="apellido" class="ui-state-default"
                                                    title="${message(code: 'contratista.apellido.label', default: 'Apellido')}" />



                                <tdn:sortableColumn property="direccion" class="ui-state-default"
                                                    title="${message(code: 'contratista.direccion.label', default: 'Direccion')}" />
                                <tdn:sortableColumn property="mail" class="ui-state-default"
                                                    title="${message(code: 'contratista.mail.label', default: 'Mail')}" />

                                <tdn:sortableColumn property="fecha" class="ui-state-default"
                                                    title="${message(code: 'contratista.fecha.label', default: 'Fecha')}" />
                                <tdn:sortableColumn property="estado" class="ui-state-default"
                                                    title="${message(code: 'contratista.estado.label', default: 'Estado')}" />



                            </tr>
                        </thead>
                        <tbody>
                            <g:each in="${contratistaInstanceList}" status="i" var="contratistaInstance">
                                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                                    
                                    <td><g:link action="show"
                                                id="${contratistaInstance.id}">${fieldValue(bean: contratistaInstance, field: "id")}</g:link></td>
                                    
                                    <td>${fieldValue(bean: contratistaInstance, field: "ruc")}</td>

                                    <td>${fieldValue(bean: contratistaInstance, field: "nombreCont")}</td>

                                    <td>${fieldValue(bean: contratistaInstance, field: "nombre")}</td>

                                    <td>${fieldValue(bean: contratistaInstance, field: "apellido")}</td>
                                    
                                    <td>${fieldValue(bean: contratistaInstance, field: "direccion")}</td>

                                    <td>${fieldValue(bean: contratistaInstance, field: "mail")}</td>
                                    
                                    <td><g:formatDate date="${contratistaInstance.fecha}" format="dd-MM-yyyy"/></td>

                                    <td>${fieldValue(bean: contratistaInstance, field: "estado")}</td>
                                    

                                    

                                    
                                </tr>
                            </g:each>
                        </tbody>
                    </table>
                </div>

                <div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-bottom ui-helper-clearfix paginateButtons">
                    <tdn:paginate total="${contratistaInstanceTotal}" />
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
