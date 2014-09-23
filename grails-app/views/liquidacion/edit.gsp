

<%@ page import="yachay.proyectos.Liquidacion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'liquidacion.label', default: 'Liquidacion')}" />
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
            <g:hasErrors bean="${liquidacionInstance}">
            <div class="errors">
                <g:renderErrors bean="${liquidacionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${liquidacionInstance?.id}" />
                <g:hiddenField name="version" value="${liquidacionInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="obra"><g:message code="liquidacion.obra.label" default="Obra" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'obra', 'errors')}">
                                    <g:select class="field required requiredCmb ui-widget-content ui-corner-all" name="obra.id" title="Obra" from="${yachay.proyectos.Obra.list()}" optionKey="id" value="${liquidacionInstance?.obra?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="valor"><g:message code="liquidacion.valor.label" default="Valor" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'valor', 'errors')}">
                                    <g:textField class="field number required ui-widget-content ui-corner-all" name="valor" title="Valor" id="valor" value="${fieldValue(bean: liquidacionInstance, field: 'valor')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaRegistro"><g:message code="liquidacion.fechaRegistro.label" default="Fecha Registro" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'fechaRegistro', 'errors')}">
                                    <input type="hidden" value="date.struct" name="fechaRegistro">
<input type="hidden" name="fechaRegistro_day" id="fechaRegistro_day"  value="${liquidacionInstance?.fechaRegistro?.format('dd')}" >
<input type="hidden" name="fechaRegistro_month" id="fechaRegistro_month" value="${liquidacionInstance?.fechaRegistro?.format('MM')}">
<input type="hidden" name="fechaRegistro_year" id="fechaRegistro_year" value="${liquidacionInstance?.fechaRegistro?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaRegistro" title="FechaRegistro" id="fechaRegistro" value="${liquidacionInstance?.fechaRegistro?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaRegistro').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaAdjudicacion"><g:message code="liquidacion.fechaAdjudicacion.label" default="Fecha Adjudicacion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'fechaAdjudicacion', 'errors')}">
                                    <input type="hidden" value="date.struct" name="fechaAdjudicacion">
<input type="hidden" name="fechaAdjudicacion_day" id="fechaAdjudicacion_day"  value="${liquidacionInstance?.fechaAdjudicacion?.format('dd')}" >
<input type="hidden" name="fechaAdjudicacion_month" id="fechaAdjudicacion_month" value="${liquidacionInstance?.fechaAdjudicacion?.format('MM')}">
<input type="hidden" name="fechaAdjudicacion_year" id="fechaAdjudicacion_year" value="${liquidacionInstance?.fechaAdjudicacion?.format('yyyy')}">
<g:textField class="datepicker field required ui-widget-content ui-corner-all" name="fechaAdjudicacion" title="FechaAdjudicacion" id="fechaAdjudicacion" value="${liquidacionInstance?.fechaAdjudicacion?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaAdjudicacion').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaInicio"><g:message code="liquidacion.fechaInicio.label" default="Fecha Inicio" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'fechaInicio', 'errors')}">
                                    <input type="hidden" value="date.struct" name="fechaInicio">
<input type="hidden" name="fechaInicio_day" id="fechaInicio_day"  value="${liquidacionInstance?.fechaInicio?.format('dd')}" >
<input type="hidden" name="fechaInicio_month" id="fechaInicio_month" value="${liquidacionInstance?.fechaInicio?.format('MM')}">
<input type="hidden" name="fechaInicio_year" id="fechaInicio_year" value="${liquidacionInstance?.fechaInicio?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaInicio" title="FechaInicio" id="fechaInicio" value="${liquidacionInstance?.fechaInicio?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaInicio').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="fechaFin"><g:message code="liquidacion.fechaFin.label" default="Fecha Fin" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: liquidacionInstance, field: 'fechaFin', 'errors')}">
                                    <input type="hidden" value="date.struct" name="fechaFin">
<input type="hidden" name="fechaFin_day" id="fechaFin_day"  value="${liquidacionInstance?.fechaFin?.format('dd')}" >
<input type="hidden" name="fechaFin_month" id="fechaFin_month" value="${liquidacionInstance?.fechaFin?.format('MM')}">
<input type="hidden" name="fechaFin_year" id="fechaFin_year" value="${liquidacionInstance?.fechaFin?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fechaFin" title="FechaFin" id="fechaFin" value="${liquidacionInstance?.fechaFin?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fechaFin').datepicker({
        changeMonth: true,
        changeYear:true,
        dateFormat: 'dd-mm-yy',
        onClose: function(dateText, inst) {
            var date = $(this).datepicker('getDate');
            var day, month, year;
            if(date != null) {
                day = date.getDate();
                month = parseInt(date.getMonth()) + 1;
                year = date.getFullYear();
            } else {
                day = '';
                month = '';
                year = '';
            }
            var id = $(this).attr('id');
            $('#' + id + '_day').val(day);
            $('#' + id + '_month').val(month);
            $('#' + id + '_year').val(year);
        }
    });
</script>
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
