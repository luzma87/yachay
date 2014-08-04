
<%@ page import="app.SubSecretaria" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="${message(code: 'subSecretaria.label', default: 'SubSecretaria')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>

    <body>

        <div class="dialog" title="${title}">
            %{--<div id="" class="toolbar ui-widget-header ui-corner-all">--}%
                %{--<a class="button home" href="${createLinkTo(dir: '')}">--}%
                    %{--<g:message code="home" default="Home" />--}%
                %{--</a>--}%
                %{--<g:link class="button list" action="list">--}%
                    %{--<g:message code="subSecretaria.list" default="SubSecretaria List" />--}%
                %{--</g:link>--}%
                %{--<g:link class="button create" action="create">--}%
                    %{--<g:message code="default.new.label" args="[entityName]" />--}%
                %{--</g:link>--}%
            %{--</div> <!-- toolbar -->--}%

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>
                <div>

                        <table width="100%" class="ui-widget-content ui-corner-all">

                            <thead>
                                <tr>
                                    <td colspan="4" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                                         <g:message code="default.show.legend" args="${['SubSecretaria']}" default="Show SubSecretaria details"/>
                                    </td>
                                </tr>
                            </thead>
                            <tbody>
                        

                            
                                <tr class="prop ${hasErrors(bean: subSecretariaInstance, field: 'id', 'error')}">
                            
                                <td class="label">
                                    <g:message code="subSecretaria.id.label"
                                               default="Id" />
                                </td>

                                <td class="campo">
                                    
                                    ${fieldValue(bean: subSecretariaInstance, field: "id")}
                                    
                                </td> <!-- campo -->
                                 
                                

                            
                                <td class="label">
                                    <g:message code="subSecretaria.entidad.label"
                                               default="Entidad" />
                                </td>

                                <td class="campo">
                                    
                                    <g:link controller="entidad" action="show"
                                                         id="${subSecretariaInstance?.entidad?.id}">
                                        ${subSecretariaInstance?.entidad?.encodeAsHTML()}
                                    </g:link>
                                    
                                </td> <!-- campo -->
                                 
                                    </tr>
                                
                                

                            
                                <tr class="prop ${hasErrors(bean: subSecretariaInstance, field: 'nombre', 'error')}">
                            
                                <td class="label">
                                    <g:message code="subSecretaria.nombre.label"
                                               default="Nombre" />
                                </td>

                                <td class="campo">
                                    
                                    ${fieldValue(bean: subSecretariaInstance, field: "nombre")}
                                    
                                </td> <!-- campo -->
                                 
                                

                            
                                <td class="label">
                                    <g:message code="subSecretaria.titulo.label"
                                               default="Titulo" />
                                </td>

                                <td class="campo">
                                    
                                    ${fieldValue(bean: subSecretariaInstance, field: "titulo")}
                                    
                                </td> <!-- campo -->
                                 
                                    </tr>
                                
                                
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="4" class="buttons" style="text-align: right;">
                                        <g:link class="button edit" action="edit" id="${subSecretariaInstance?.id}">
                                            <g:message code="default.button.update.label" default="Edit" />
                                        </g:link>
                                        <g:link class="button delete" action="delete" id="${subSecretariaInstance?.id}">
                                            <g:message code="default.button.delete.label" default="Delete" />
                                        </g:link>
                                    </td>
                                </tr>
                            </tfoot>
                    </table>
                </div>
            </div> <!-- body -->
            </div> <!-- dialog -->

         <script type="text/javascript">
            $(function() {
                $(".button").button();
                $(".home").button("option", "icons", {primary:'ui-icon-home'});
                $(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
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
