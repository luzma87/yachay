<%@ page import="app.Proyecto" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName"
           value="${message(code: 'proyecto.label', default: 'Proyecto')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>

<div class="dialog" title="${title}">
<div class="fg-toolbar ui-toolbar ui-widget-header ui-corner-top ui-helper-clearfix" style="margin-bottom: 15px;">
    <g:link action="nuevoMarco" controller="marcoLogico" id="${proyectoInstance.id}"
            class="button">Marco Logico</g:link>
</div>

<div class="body">

<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
</g:if>
<div>

<fieldset class="ui-corner-all">
<legend class="ui-widget ui-widget-header ui-corner-all">
    <g:message code="proyecto.show.legend"
               default="Proyecto details"/>
</legend>


<div class="prop">
    <label>
        <g:message code="proyecto.id.label"
                   default="Id"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "id")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.unidadEjecutora.label"
                   default="Unidad Ejecutora"/>
    </label>

    <div class="campo">

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.etapa.label"
                   default="Etapa"/>
    </label>

    <div class="campo">

        <g:link controller="etapa" action="show"
                id="${proyectoInstance?.etapa?.id}">
            ${proyectoInstance?.etapa?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fase.label"
                   default="Fase"/>
    </label>

    <div class="campo">

        <g:link controller="fase" action="show"
                id="${proyectoInstance?.fase?.id}">
            ${proyectoInstance?.fase?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.tipoProducto.label"
                   default="Tipo Producto"/>
    </label>

    <div class="campo">

        <g:link controller="tipoProducto" action="show"
                id="${proyectoInstance?.tipoProducto?.id}">
            ${proyectoInstance?.tipoProducto?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.estadoProyecto.label"
                   default="Estado Proyecto"/>
    </label>

    <div class="campo">

        <g:link controller="estadoProyecto" action="show"
                id="${proyectoInstance?.estadoProyecto?.id}">
            ${proyectoInstance?.estadoProyecto?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.linea.label"
                   default="Linea"/>
    </label>

    <div class="campo">

        <g:link controller="linea" action="show"
                id="${proyectoInstance?.linea?.id}">
            ${proyectoInstance?.linea?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.tipoInversion.label"
                   default="Tipo Inversion"/>
    </label>

    <div class="campo">

        <g:link controller="tipoInversion" action="show"
                id="${proyectoInstance?.tipoInversion?.id}">
            ${proyectoInstance?.tipoInversion?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.cobertura.label"
                   default="Cobertura"/>
    </label>

    <div class="campo">

        <g:link controller="cobertura" action="show"
                id="${proyectoInstance?.cobertura?.id}">
            ${proyectoInstance?.cobertura?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.calificacion.label"
                   default="Calificacion"/>
    </label>

    <div class="campo">

        <g:link controller="calificacion" action="show"
                id="${proyectoInstance?.calificacion?.id}">
            ${proyectoInstance?.calificacion?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.programa.label"
                   default="Programa"/>
    </label>

    <div class="campo">

        <g:link controller="programa" action="show"
                id="${proyectoInstance?.programa?.id}">
            ${proyectoInstance?.programa?.encodeAsHTML()}
        </g:link>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.codigoProyecto.label"
                   default="Codigo Proyecto"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "codigoProyecto")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaRegistro.label"
                   default="Fecha Registro"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaRegistro}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaModificacion.label"
                   default="Fecha Modificacion"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaModificacion}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.nombre.label"
                   default="Nombre"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "nombre")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.monto.label"
                   default="Monto"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "monto")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.parroquiasDistrito.label"
                   default="Parroquias Distrito"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "parroquiasDistrito")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.descripcion.label"
                   default="Descripcion"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "descripcion")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaInicioPlanificada.label"
                   default="Fecha Inicio Planificada"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaInicioPlanificada}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaInicio.label"
                   default="Fecha Inicio"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaInicio}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaFinPlanificada.label"
                   default="Fecha Fin Planificada"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaFinPlanificada}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.fechaFin.label"
                   default="Fecha Fin"/>
    </label>

    <div class="campo">

        <g:formatDate date="${proyectoInstance?.fechaFin}" format="dd-MM-yyyy HH:mm"/>

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.mes.label"
                   default="Mes"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "mes")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.problema.label"
                   default="Problema"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "problema")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.informacionDias.label"
                   default="Informacion Dias"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "informacionDias")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="prop">
    <label>
        <g:message code="proyecto.subPrograma.label"
                   default="Sub Programa"/>
    </label>

    <div class="campo">

        ${fieldValue(bean: proyectoInstance, field: "subPrograma")}

    </div> <!-- campo -->
</div> <!-- prop -->


<div class="buttons">
    <g:link class="button edit" action="nuevoProyecto" id="${proyectoInstance?.id}">
        <g:message code="default.button.update.label" default="Edit"/>
    </g:link>
    <g:link class="button delete" action="delete" id="${proyectoInstance?.id}">
        <g:message code="default.button.delete.label" default="Delete"/>
    </g:link>
</div>

</fieldset>
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
