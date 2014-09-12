<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 14/08/14
  Time: 12:54 PM
--%>

<%@ page import="app.Asignacion; app.Anio; app.Fuente; app.Financiamiento; app.TipoAprobacion" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <meta name="layout" content="main"/>
        <title>Aprobar solicitud</title>

        <style type="text/css">
        textarea {
            width : 910px;
        }

        label {
            width : auto;
        }
        </style>
    </head>

    <body>

        <g:set var="puedeEditar" value="${perfil.codigo == 'GP'}"/>

        <div class="dialog" title="${title}">
            <div id="" class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button list" action="list">
                    Lista de solicitudes
                </g:link>
                <g:link class="button create" action="ingreso">
                    Nueva solicitud
                </g:link>
                <g:if test="${puedeEditar}">
                    <a href="#" id="btnSave" class="button" style="float: right;">Guardar</a>
                </g:if>
                <a href="#" class="button upload" id="uploadActa" style="float: right;">
                    Archivar acta
                </a>
                <a href="#" id="btnPrint" class="button" style="float: right;">Imprimir</a>
            </div> <!-- toolbar -->

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>

                <g:form action="saveAprobacion" name="frmAprobacion" id="${aprobacion.id}">
                    <g:hiddenField name="solicitud.id" value="${solicitud.id}"/>
                    <slc:showSolicitud solicitud="${solicitud}" perfil="${perfil}" aprobacion="true"/>
                </g:form>
            </div> <!-- body -->
        </div> <!-- dialog -->


        <div id="dialogFirmas" title="Seleccione las firmas para el acta" class="ui-helper-hidden">
            <p>
                Firmas para el acta:
            </p>

            <table border="0">
                <tr>
                    <th>Gerencia de Planificación</th>
                    <td>
                        <g:select from="${firmaGerenciaPlanif}" optionKey="id" optionValue="${{
                            it.persona.nombre + ' ' + it.persona.apellido
                        }}" name="firmaGP"/>
                    </td>
                </tr>
                <tr>
                    <th>Dirección de Planificación</th>
                    <td>
                        <g:select from="${firmaDireccionPlanif}" optionKey="id" optionValue="${{
                            it.persona.nombre + ' ' + it.persona.apellido
                        }}" name="firmaDP"/>
                    </td>
                </tr>
                <tr>
                    <th>Gerencia Técnica</th>
                    <td>
                        <g:select from="${firmaGerenciaTec}" optionKey="id" optionValue="${{
                            it.persona.nombre + ' ' + it.persona.apellido
                        }}" name="firmaGT"/>
                    </td>
                </tr>
                <tr>
                    <th>Requirente</th>
                    <td>
                        <g:select from="${firmaRequirente}" optionKey="id" optionValue="${{
                            it.persona.nombre + ' ' + it.persona.apellido
                        }}" name="firmaRQ"/>
                    </td>
                </tr>
            </table>
        </div>


        <div id="dialog" title="Seleccione un archivo" class="ui-helper-hidden">
            <p>
                <g:uploadForm id="${aprobacion.id}" name="frmPdf" action="uploadActa">
                    <input type="file" name="pdf" class="required"/>
                    <g:if test="${aprobacion.pathPdf}">
                        <br/>Archivo cargado: ${aprobacion.pathPdf}
                    </g:if>
                </g:uploadForm>
            </p>
        </div>

        <script type="text/javascript">
            $(function () {

                $("#dialog").dialog({
                    autoOpen  : false,
                    modal     : true,
                    resizable : false,
                    buttons   : {
                        "Cancelar"      : function () {
                            $(this).dialog("close");
                        },
                        "Subir archivo" : function () {
                            $("#frmPdf").submit();
                            $(this).dialog("close");
                        }
                    }
                });

                $("#dialogFirmas").dialog({
                    autoOpen  : false,
                    modal     : true,
                    resizable : false,
                    width     : 400,
                    buttons   : {
                        "Cancelar" : function () {
                            $(this).dialog("close");
                        },
                        "Imprimir" : function () {
                            var url = "${createLink(controller: 'reporteSolicitud', action: 'imprimirActaAprobacion')}/?id=${solicitud.id}";
                            var firmaGP = $("#firmaGP").val();
                            var firmaDP = $("#firmaDP").val();
                            var firmaGT = $("#firmaGT").val();
                            var firmaRQ = $("#firmaRQ").val();
                            url += "Wfgp=" + firmaGP + "Wfdp=" + firmaDP + "Wfgt=" + firmaGT + "Wfrq=" + firmaRQ;
//                            console.log(url);
                            location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url + "&filename=acta_aprobacion.pdf";
                        }
                    }
                });

                $("#frmPdf").validate({
//                    rules    : {
//                        pdf : {
//                            accept : "pdf"
//                        }
//                    },
//                    messages : {
//                        pdf : {
//                            accept : "Por favor seleccione un archivo PDF"
//                        }
//                    }
                });

                $("#uploadActa").click(function () {
                    $("#dialog").dialog("open");
                    return false;
                });

                $("#tipoAprobacion").selectmenu({width : 210});
                $("#fuente").selectmenu({width : 387});

                $(".button").button();

                $("#btnSave").button("option", "icons", {primary : 'ui-icon-disk'}).click(function () {
                    $("#frmAprobacion").submit();
                });
                $("#btnPrint").button("option", "icons", {primary : 'ui-icon-print'}).click(function () {
                    $("#dialogFirmas").dialog("open");
                    return false;
                });
                $(".list").button("option", "icons", {primary : 'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});
                $(".revision").button("option", "icons", {primary : 'ui-icon-check'});

            });
        </script>

    </body>
</html>