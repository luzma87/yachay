<%@ page import="app.Proyecto" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>

    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/fg-menu', file: 'fg.menu.js')}"></script>

    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

    <script type="text/javascript"
            src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/fg-menu', file: 'fg.menu.css')}"/>

    <g:set var="entityName"
           value="${message(code: 'proyecto.label', default: 'Proyecto')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>

    <style type="text/css">
    .fg-button {
        padding-left : 13px !important;
    }

    .fg-button .ui-icon {
        position    : absolute;
        /*top: 50%;*/
        margin-top  : -3px;
        /*left: 50%;*/
        margin-left : -15px;
    }

    table.show td {
        padding-left  : 5px;
        padding-right : 5px;
    }

    .label {
        background  : white;
        font-weight : bold;
    }
    </style>

</head>

<body>

<div class="dialog">

<div id="" class="toolbar ui-widget-header ui-corner-all">

    <g:link class="button list" action="list">
        Lista de proyectos
    </g:link>

    <!-- FLYOUT MENU PROYECTO -->
    <a tabindex="0" href="#" id="menuProyecto">
        Proyecto
    </a>

    <div id="items-menuProyecto" class="hidden">
        <ul id="ul-menuProyecto">
            <li>
                <g:link class="button semplades fg-button" action="verIndicadoresSenplades" id="${proyectoInstance.id}">
                    <span class="ui-icon ui-icon-folder-open"></span>
                    Indicadores Senplades
                </g:link>
            </li>
            <li>
                <g:link class="button semplades fg-button" action="verEntidades" id="${proyectoInstance.id}">
                    <span class="ui-icon ui-icon-copy"></span>
                    Entidades del proyecto
                </g:link>
            </li>
            <li>
                <g:link class="button documentos fg-button" action="documentos" id="${proyectoInstance.id}">
                    <span class="ui-icon ui-icon-document"></span>
                    Documentos
                </g:link>
            </li>
            <li>
                <a href="#" class="button responsable fg-button" function="showResponsable">
                    <span class="ui-icon ui-icon-person"></span>
                    Responsables
                </a>

            </li>
            <li>
                <g:link controller="asignacion" action="asignacionesProyecto" id="${proyectoInstance.id}"  class="button  fg-button" >
                    <span class="ui-icon ui-icon-cart"></span>
                    Asignaciones
                </g:link>

            </li>
            <li>
                <g:link controller="obra" action="pacProyecto" id="${proyectoInstance.id}"  class="button  fg-button" >
                    <span class="ui-icon ui-icon-cart"></span>
                    P.A.C.
                </g:link>
            </li>
            %{--<li>--}%
            %{--<a href="#" class="button responsables fg-button" function="showHistorial">--}%
            %{--<span class="ui-icon ui-icon-clock"></span>--}%
            %{--Historial Responsables--}%
            %{--</a>--}%
            %{--</li>--}%
            <li>
                <a href="#" class="button estado fg-button" function="showEstado">
                    <span class="ui-icon ui-icon-radio-off"></span>
                    Estado
                </a>
            </li>
        </ul>
    </div>

    <!-- FIN FLYOUT MENU PROYECTO -->

    <!-- FLYOUT MENU MARCO -->
    <a tabindex="0" href="#" id="menuMarco">
        Marco lógico
    </a>

    <div id="items-menuMarco" class="hidden">
        <ul id="ul-menuMarco">
            <li>
                <g:link action="nuevoMarco" controller="marcoLogico" id="${proyectoInstance.id}"
                        class="button fg-button">
                    Editar Marco Lógico
                </g:link>
            </li>
            <li>
                <g:link action="verMarcoCompleto" controller="marcoLogico" id="${proyectoInstance.id}"
                        class="button fg-button">
                    Ver Marco Lógico
                </g:link>
            </li>
            <li>
                <g:link action="verCronograma" controller="cronograma" id="${proyectoInstance.id}"
                        class="button fg-button">
                    Cronograma
                </g:link>
            </li>
            <li>
                <g:link action="nuevoCronograma" controller="cronograma" id="${proyectoInstance.id}"
                        class="button fg-button">
                    Editar cronograma
                </g:link>
            </li>
        </ul>
    </div>
    <!-- FIN FLYOUT MENU MARCO -->
    <g:link class="button list" action="solicitarModificacion" controller="modificacionProyecto" id="${proyectoInstance.id}">
        Solicitar modificación
    </g:link>
</div> <!-- toolbar -->

<div class="body">
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
</g:if>
<div style="width: 1040px;float: left;margin-top: 15px">

<table width="1040px" class="ui-widget-content ui-corner-all">

<thead>
<tr>
    <td colspan="4" class="ui-widget ui-widget-header ui-corner-all" style="padding: 3px;">
        Detalles del proyecto
    </td>
</tr>
</thead>
<tbody>

<tr class="prop">
    <td class="label">
        <g:message code="proyecto.codigoProyecto.label" default="Código del Proyecto"/>
    </td>
    <td class="">
        ${fieldValue(bean: proyectoInstance, field: "codigoProyecto")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.fechaRegistro.label" default="Fecha de Registro"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaRegistro}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.fechaModificacion.label" default="Fecha de Modificación"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaModificacion}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.nombre.label" default="Nombre"/>
    </td>
    <td class="" colspan="3">
        ${fieldValue(bean: proyectoInstance, field: "nombre")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.monto.label" default="Monto"/>
    </td>
    <td class="">
        ${fieldValue(bean: proyectoInstance, field: "monto")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.parroquiasDistrito.label" default="Producto principal"/>
    </td>
    <td class="" colspan="3">
        ${fieldValue(bean: proyectoInstance, field: "producto")}
    </td> <!-- campo -->
</tr>

<tr>
    <td class="labelshow">
        <g:message code="proyecto.descripcion.label" default="Descripción"/>
    </td>
    <td class="" colspan="3">
        ${fieldValue(bean: proyectoInstance, field: "descripcion")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.fechaInicioPlanificada.label" default="Fecha inicio planificada"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaInicioPlanificada}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.fechaInicio.label" default="Fecha Inicio"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaInicio}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.fechaFinPlanificada.label" default="Fecha fin planificada"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaFinPlanificada}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.fechaFin.label" default="Fecha Fin"/>
    </td>
    <td class="">
        <g:formatDate date="${proyectoInstance?.fechaFin}" format="dd-MM-yyyy"/>
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.mes.label" default="Duración en meses"/>
    </td>
    <td class="">
        ${fieldValue(bean: proyectoInstance, field: "mes")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.problema.label" default="Problema"/>
    </td>
    <td class="" colspan="3">
        ${fieldValue(bean: proyectoInstance, field: "problema")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.informacionDias.label" default="Información Dias"/>
    </td>
    <td class="">
        ${fieldValue(bean: proyectoInstance, field: "informacionDias")}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.programa.label" default="Programa"/>
    </td>
    <td colspan="3" class="">
        ${proyectoInstance?.programa?.descripcion?.encodeAsHTML()}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.linea.label" default="Linea"/>
    </td>
    <td class="" colspan="3">
        ${proyectoInstance?.linea?.encodeAsHTML()}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.estadoProyecto.label" default="Estado del Proyecto"/>
    </td>
    <td class="">
        ${proyectoInstance?.estadoProyecto?.encodeAsHTML()}
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.calificacion.label" default="Calificación"/>
    </td>
    <td class="">
        ${proyectoInstance?.calificacion?.encodeAsHTML()}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow" style="width: 140px;">
        <g:message code="proyecto.etapa.label" default="Etapa"/>
    </td>
    <td class="" style="width: 270px;">
        ${proyectoInstance?.etapa?.encodeAsHTML()}
    </td> <!-- campo -->

    <td class="labelshow" style="width: 140px;">
        <g:message code="proyecto.fase.label" default="Fase"/>
    </td>
    <td class="">
        ${proyectoInstance?.fase?.encodeAsHTML()}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.tipoProducto.label" default="Tipo Producto"/>
    </td>
    <td class="">
        ${proyectoInstance?.tipoProducto?.encodeAsHTML()}
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.tipoInversion.label" default="Tipo de Inversión"/>
    </td>
    <td class="">
        ${proyectoInstance?.tipoInversion?.encodeAsHTML()}
    </td> <!-- campo -->
</tr>

<tr class="prop">
    <td class="labelshow">
        <g:message code="proyecto.unidadEjecutora.label" default="Unidad Ejecutora"/>
    </td>
    <td class="">
        ${proyectoInstance?.unidadEjecutora?.encodeAsHTML()}
    </td> <!-- campo -->

    <td class="labelshow">
        <g:message code="proyecto.cobertura.label" default="Cobertura"/>
    </td>
    <td class="">
        ${proyectoInstance?.cobertura?.encodeAsHTML()}

    </td> <!-- campo -->
</tr>

</tbody>
<tfoot>
<tr>
    <td colspan="4" class="buttons" style="text-align: right;">
        <g:link class="button edit" action="nuevoProyecto" id="${proyectoInstance?.id}">
            Editar
        </g:link>
    </td>
</tr>
</tfoot>
</table>

</div>
</div> <!-- body -->
</div> <!-- dialog -->


<div id="dlg_responsable"><div id="dlg_contenido"></div></div>

<script type="text/javascript">

    var botonesSave = {
        "Cerrar": function() {
            $("#dlg_responsable").dialog("close");
        }/*,
         "Guardar": function() {
         var data = $(".frmResponsableProyecto").serialize();
         var url = $(".frmResponsableProyecto").attr("action");
         $.ajax({
         type: "POST",
         url: url,
         data: data,
         success: function(msg) {
         if (msg == "OK") {
         $("#dlg_responsable").dialog("close");
         } else {
         alert("Ha ocurrido un error al guardar");
         }
         }
         });
         }*/
    };

    var botonesCerrar = {
        "Cerrar": function() {
            $("#dlg_responsable").dialog("close");
        }
    };

    var loading = $("<div style='width: 100%; height: 100px; margin-top: 50px; text-align: center;'>");
    var img = $("<img src='" + "${resource(dir:'images', file:'spinner.gif')}" + "' alt='cargando...'/>");
    loading.append(img);

    function showResponsable() {
        $("#dlg_contenido").html(loading);
        $("#dlg_responsable").dialog("open");

        $("#dlg_responsable").dialog("option", "title", 'Asignar responsable del proyecto ${proyectoInstance.nombre}');
        $("#dlg_responsable").dialog("option", "width", 745);
        $("#dlg_responsable").dialog("option", "buttons", botonesSave);
        $("#dlg_responsable").dialog("option", "position", [330,250]);

    %{--var url = "${createLink(action:'responsable', id:proyectoInstance.id)}";--}%
        var url = "${createLink(action:'responsables', id:proyectoInstance.id)}";

        $.ajax({
            type: "POST",
            url: url,
            success: function(msg) {
                $("#dlg_contenido").html(msg);
            }
        });
    }

    function showHistorial() {
        $("#dlg_contenido").html(loading);
        $("#dlg_responsable").dialog("open");

        $("#dlg_responsable").dialog("option", "title", 'Historial de responsables del proyecto ${proyectoInstance.nombre}');
        $("#dlg_responsable").dialog("option", "width", 645);
        $("#dlg_responsable").dialog("option", "buttons", botonesCerrar);
        $("#dlg_responsable").dialog("option", "position", [395,250]);

        var url = "${createLink(action:'historialResponsables', id:proyectoInstance.id)}";

        $.ajax({
            type: "POST",
            url: url,
            success: function(msg) {
                $("#dlg_contenido").html(msg);
            }
        });
    }

    function showEstado() {
        $("#dlg_contenido").html(loading);
        $("#dlg_responsable").dialog("open");

        $("#dlg_responsable").dialog("option", "title", 'Estado del proyecto ${proyectoInstance.nombre}');
        $("#dlg_responsable").dialog("option", "width", 380);
        $("#dlg_responsable").dialog("option", "buttons", botonesCerrar);
        $("#dlg_responsable").dialog("option", "position", [455,200]);

        var url = "${createLink(action:'estadoProyecto', id:proyectoInstance.id)}";

        $.ajax({
            type: "POST",
            url: url,
            success: function(msg) {
                $("#dlg_contenido").html(msg);
            }
        });
    }

    $(function() {

        /********** PARA LOS FLYOUT MENU *************************/
        $("#menuProyecto").button({
            icons: {
                primary: "ui-icon-clipboard",
                secondary: "ui-icon-triangle-1-s"
            }
        }).click(function() {
                    return false;
                });
        $('#menuProyecto').menu({
            content: $('#items-menuProyecto').html(),
            flyOut: true
        });


        $("#menuMarco").button({
            icons: {
                primary: "ui-icon-comment",
                secondary: "ui-icon-triangle-1-s"
            }
        }).click(function() {
                    return false;
                });
        $('#menuMarco').menu({
            content: $('#items-menuMarco').html(),
            flyOut: true
        });

        /********** FIN FLYOUT MENU *************************/

        $("#dlg_responsable").dialog({
            autoOpen: false,
            modal: true,
            resizable: false,
            position: [385,305]
        });

        $(".button").button();
        $(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
        $(".semplades").button("option", "icons", {primary:'ui-icon-folder-open'});

        $("#showResponsable").click(function(event) {

            $("#dlg_responsable").dialog("option", "title", 'Asignar responsable del proyecto ${proyectoInstance.nombre}');
            $("#dlg_responsable").dialog("option", "buttons", botonesSave);

            var url = "${createLink(action:'responsable', id:proyectoInstance.id)}";
            $("#dlg_responsable").dialog("open");

            $.ajax({
                type: "POST",
                url: url,
                success: function(msg) {
                    $("#dlg_contenido").html(msg);
                }
            });
            return false;
        });
        $(".responsables").button("option", "icons", {primary:'ui-icon-clock', secondary: 'ui-icon-person'}).click(function() {

            $("#dlg_responsable").dialog("option", "title", 'Historial de responsables del proyecto ${proyectoInstance.nombre}');
            $("#dlg_responsable").dialog("option", "buttons", botonesSave);

            var url = "${createLink(action:'historialResponsables', id:proyectoInstance.id)}";
            $("#dlg_responsable").dialog("open");

            $.ajax({
                type: "POST",
                url: url,
                success: function(msg) {
                    $("#dlg_contenido").html(msg);
                }
            });
            return false;
        });

        $(".documentos").button("option", "icons", {primary:'ui-icon-document'});

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
