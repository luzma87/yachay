

<%@ page import="app.Contratista" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'contratista.label', default: 'Contratista')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${contratistaInstance}">
            <div class="errors">
                <g:renderErrors bean="${contratistaInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ruc"><g:message code="contratista.ruc.label" default="Ruc" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'ruc', 'errors')}">
                                    <g:textField  name="ruc" id="ruc" title="Ruc del Contratista" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="13" value="${contratistaInstance?.ruc}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="direccion"><g:message code="contratista.direccion.label" default="Direccion" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'direccion', 'errors')}">
                                    <g:textField  name="direccion" id="direccion" title="Direccion" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="${contratistaInstance?.direccion}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="fecha"><g:message code="contratista.fecha.label" default="Fecha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'fecha', 'errors')}">
                                    <input type="hidden" value="date.struct" name="fecha">
<input type="hidden" name="fecha_day" id="fecha_day"  value="${contratistaInstance?.fecha?.format('dd')}" >
<input type="hidden" name="fecha_month" id="fecha_month" value="${contratistaInstance?.fecha?.format('MM')}">
<input type="hidden" name="fecha_year" id="fecha_year" value="${contratistaInstance?.fecha?.format('yyyy')}">
<g:textField class="datepicker field ui-widget-content ui-corner-all" name="fecha" title="Fecha" id="fecha" value="${contratistaInstance?.fecha?.format('dd-MM-yyyy')}" />
<script type='text/javascript'>
    $('#fecha').datepicker({
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
                                    <label for="nombreCont"><g:message code="contratista.nombreCont.label" default="Nombre Cont" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'nombreCont', 'errors')}">
                                    <g:textField  name="nombreCont" id="nombreCont" title="Nombre Contratista" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${contratistaInstance?.nombreCont}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nombre"><g:message code="contratista.nombre.label" default="Nombre" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'nombre', 'errors')}">
                                    <g:textField  name="nombre" id="nombre" title="Nombre" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${contratistaInstance?.nombre}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="apellido"><g:message code="contratista.apellido.label" default="Apellido" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'apellido', 'errors')}">
                                    <g:textField  name="apellido" id="apellido" title="Apellido" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${contratistaInstance?.apellido}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="observaciones"><g:message code="contratista.observaciones.label" default="Observaciones" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'observaciones', 'errors')}">
                                    <g:textField  name="observaciones" id="observaciones" title="Observaciones" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="${contratistaInstance?.observaciones}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefono"><g:message code="contratista.telefono.label" default="Telefono" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'telefono', 'errors')}">
                                    <g:textField  name="telefono" id="telefono" title="Telefono" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="40" value="${contratistaInstance?.telefono}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="mail"><g:message code="contratista.mail.label" default="Mail" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'mail', 'errors')}">
                                    <g:textField  name="mail" id="mail" title="Mail" class="field ui-widget-content ui-corner-all" minLenght="1" maxLenght="40" value="${contratistaInstance?.mail}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="estado"><g:message code="contratista.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: contratistaInstance, field: 'estado', 'errors')}">
                                    <g:textField  name="estado" id="estado" title="Estado" class="field ui-widget-content ui-corner-all" value="${contratistaInstance?.estado}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
