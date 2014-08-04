<%@ page import="app.Avance" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'avance.label', default: 'Avance')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="dialog">
    <div id="" class="toolbar ui-widget-header ui-corner-all">
        <g:link class="button list" action="list">
            <g:message code="avance.list" default="Avance List"/>
        </g:link>
        <g:link class="button create" action="create">
            <g:message code="default.new.label" args="[entityName]"/>
        </g:link>
    </div> <!-- toolbar -->

    <div class="body">
        <g:if test="${flash.message}">
            <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
        </g:if>
        <div>

            <table style="width: 800px;" class="show ui-widget-content ui-corner-all">

                <thead>
                <tr>
                    <td colspan="4" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
                        <g:message code="default.show.legend" args="${['Avance']}" default="Show Avance details"/>
                    </td>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td colspan="4" class="blanco">&nbsp;</td>
                </tr>





                <tr class="prop ${hasErrors(bean: avanceInstance, field: 'informe', 'error')}">

                    <td class="label" style="width: 10em;">
                        <g:message code="avance.informe.label"
                                   default="Informe"/>:
                    </td>

                    <td class="">

                        <g:link controller="informe" action="show"
                                id="${avanceInstance?.informe?.id}">
                            ${avanceInstance?.informe?.encodeAsHTML()}
                        </g:link>

                    </td> <!-- campo -->




                    <td class="label" style="width: 10em;">
                        <g:message code="avance.meta.label"
                                   default="Meta"/>:
                    </td>

                    <td class="">

                        <g:link controller="meta" action="show"
                                id="${avanceInstance?.meta?.id}">
                            ${avanceInstance?.meta?.encodeAsHTML()}
                        </g:link>

                    </td> <!-- campo -->

                </tr>




                <tr class="prop ${hasErrors(bean: avanceInstance, field: 'tasa', 'error')}">

                    <td class="label" style="width: 10em;">
                        <g:message code="avance.tasa.label"
                                   default="Tasa"/>:
                    </td>

                    <td class="">

                        ${fieldValue(bean: avanceInstance, field: "tasa")}

                    </td> <!-- campo -->




                    <td class="label" style="width: 10em;">
                        <g:message code="avance.valor.label"
                                   default="Valor"/>:
                    </td>

                    <td class="">

                        ${fieldValue(bean: avanceInstance, field: "valor")}

                    </td> <!-- campo -->

                </tr>


                <tr>
                    <td colspan="4" class="label">&nbsp;</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="4" class="buttons" style="text-align: right;">
                        <g:link class="button edit" action="edit" id="${avanceInstance?.id}">
                            <g:message code="default.button.update.label" default="Edit"/>
                        </g:link>
                        <g:link class="button delete" action="delete" id="${avanceInstance?.id}">
                            <g:message code="default.button.delete.label" default="Delete"/>
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
            if (confirm("${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                return true;
            }
            return false;
        });
    });
</script>

</body>
</html>
