<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 03/10/14
  Time: 12:19 PM
--%>

<%@ page import="yachay.contratacion.Solicitud" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <title>Reunión de aprobación</title>

        <style type="text/css">
        textarea {
            width : 910px;
        }

        label {
            width : auto;
        }

        .wide {
            height       : 29px;
            padding-left : 5px;
        }

        .short {
            width : 85px;
        }

        .solicitud {
            border        : solid 2px #555;
            margin-bottom : 15px;
        }

        .solicitudHeader {
            padding : 5px;
            cursor  : pointer;
        }
        </style>
    </head>

    <body>
        <g:set var="puedeEditar" value="${perfil.codigo == 'GP'}"/>
        <g:if test="${params.show.toString() == '1'}">
            <g:set var="puedeEditar" value="${false}"/>
        </g:if>
        <g:set var="solicitudes" value="${Solicitud.findAllByAprobacion(reunion, [sort: 'fecha'])}"/>
        <div id="" class="toolbar ui-widget-header ui-corner-all" style="margin-bottom: 15px;">
            <g:link class="button list" controller="solicitud" action="list">
                Lista de solicitudes
            </g:link>
            <g:link class="button create" action="list">
                Lista de reuniones de aprobación
            </g:link>
            <g:if test="${puedeEditar}">
                <a href="#" id="btnSave" class="button" style="float: right;">Guardar</a>
            </g:if>
            <a href="#" class="button upload" id="uploadActa" style="float: right;">
                Archivar acta
            </a>
            <a href="#" id="btnPrint" class="button" style="float: right;">Imprimir</a>
        </div> <!-- toolbar -->

        <g:form action="saveAprobacion" name="frmAprobacion" id="${reunion.id}">
            <g:each in="${solicitudes}" var="solicitud" status="i">
                <div class="solicitud ui-corner-all">
                    <div class="ui-widget ui-widget-header ui-corner-all solicitudHeader" title="Clic para ocultar/mostrar">
                        Solicitud ${i + 1} de ${solicitudes.size()}: ${solicitud.objetoContrato}
                    </div>

                    <div class="solicitudBody">
                        <slc:showSolicitud solicitud="${solicitud}" perfil="${perfil}" aprobacion="${puedeEditar}" multiple="true"/>
                    </div>
                </div>
            </g:each>

            <table>
                <tr>
                    <td class="label">Asistentes</td>
                    <td colspan="4">
                        <g:if test="${puedeEditar}">
                            <g:textArea class="ui-widget-content ui-corner-all required" name="asistentes" rows="5" cols="5" value="${reunion.asistentes}"/>
                        </g:if>
                        <g:else>
                            ${reunion.asistentes ?: '-Sin asistentes-'}
                        </g:else>
                    </td>
                </tr>
                <tr>
                    <td class="label">Observaciones</td>
                    <td colspan="4">
                        <g:if test="${puedeEditar}">
                            <g:textArea class="ui-widget-content ui-corner-all " name="observaciones" rows="5" cols="5" value="${reunion.observaciones}"/>
                        </g:if>
                        <g:else>
                            ${reunion.observaciones ?: '-Sin observaciones-'}
                        </g:else>
                    </td>
                </tr>
                <g:if test="${reunion.pathPdf}">
                    <tr>
                        <td class="label">Archivo</td>
                        <td>
                            <g:link controller="solicitud" action="downloadActa" id="${reunion.id}">
                                ${reunion.pathPdf}
                            </g:link>
                        </td>
                    </tr>
                </g:if>
            </table>
        </g:form>

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
                %{--<tr>--}%
                %{--<th>Requirente</th>--}%
                %{--<td>--}%
                %{--<g:select from="${firmaRequirente}" optionKey="id" optionValue="${{--}%
                %{--it.persona.nombre + ' ' + it.persona.apellido--}%
                %{--}}" name="firmaRQ"/>--}%
                %{--</td>--}%
                %{--</tr>--}%
            </table>
        </div>

        <div id="dialogUpload" title="Seleccione un archivo" class="ui-helper-hidden">
            <p>
                <g:uploadForm id="${reunion.id}" name="frmPdf" controller="solicitud" action="uploadActa">
                    <input type="file" name="pdf" class="required"/>
                    <g:if test="${reunion.pathPdf}">
                        <br/>Archivo cargado: ${reunion.pathPdf}
                    </g:if>
                </g:uploadForm>
            </p>
        </div>

        <script type="text/javascript">
            $(function () {

                $("#frmAprobacion").validate({
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

                $("#dialogUpload").dialog({
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
                            var url = "${createLink(controller: 'reporteSolicitud', action: 'imprimirActaReunionAprobacion')}/?id=${reunion.id}";
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

                $("#btnPrint").button("option", "icons", {primary : 'ui-icon-print'}).click(function () {
                    $("#dialogFirmas").dialog("open");
                    return false;
                });

                $(".solicitudBody").each(function () {
                    var $body = $(this);
                    $body.hide();
                    $body.addClass("escondido");
                });

                $(".solicitudHeader").click(function () {
                    var $body = $(this).siblings(".solicitudBody");
                    if ($body.hasClass("escondido")) {
                        $body.show();
                        $body.removeClass("escondido");
                    } else {
                        $body.hide();
                        $body.addClass("escondido");
                    }
                });

                $(".collapsed").each(function () {
                    var $c = $(this);
                    var $tbody = $c.parents("thead").siblings("tbody");
                    $tbody.addClass("escondido");
                    $tbody.hide();
                });
                $(".collapsible").click(function () {
                    var $c = $(this);
                    var $tbody = $c.parents("thead").siblings("tbody");
                    if ($tbody.hasClass("escondido")) {
                        $tbody.show();
                        $tbody.removeClass("escondido");
                    } else {
                        $tbody.hide();
                        $tbody.addClass("escondido");
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
                    $("#dialogUpload").dialog("open");
                    return false;
                });

                $(".tipoAprobacion").selectmenu({width : 210});

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