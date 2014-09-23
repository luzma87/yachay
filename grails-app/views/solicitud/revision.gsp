<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 12/08/14
  Time: 01:25 PM
--%>

<%@ page import="yachay.proyectos.Proyecto" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Revisar solicitud</title>

        <style type="text/css">
        textarea {
            width : 780px;
        }
        </style>

    </head>

    <body>

        <div class="dialog" title="${title}">
            <div id="" class="toolbar ui-widget-header ui-corner-all">
                <g:link class="button list" action="list">
                    Lista de solicitudes
                </g:link>
                <g:link class="button create" action="ingreso">
                    Nueva solicitud
                </g:link>
            %{--<g:if test="${perfil.codigo == 'GAF' || perfil.codigo == 'GJ' || perfil.codigo == 'GDP'}">--}%
                <g:if test="${perfil.codigo == 'ASAF' || perfil.codigo == 'ASGJ' || perfil.codigo == 'GAF' || perfil.codigo == 'GJ'}">
                    <a href="#" id="btnSave" class="button" style="float: right;">Guardar</a>
                </g:if>
                <a href="#" id="btnPrint" class="button" style="float: right;">Imprimir</a>
            </div> <!-- toolbar -->

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">${flash.message}</div>
                </g:if>

                <g:uploadForm action="saveRevision" name="frmRevision" id="${solicitud.id}">
                    <slc:showSolicitud solicitud="${solicitud}" editable="true" perfil="${perfil}"/>
                </g:uploadForm>
            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">
            $(function () {
                $(".button").button();

                $("#btnSave").button("option", "icons", {primary : 'ui-icon-disk'}).click(function () {
                    $("#frmRevision").submit();
                });
                $("#btnPrint").button("option", "icons", {primary : 'ui-icon-print'}).click(function () {
                    var url = "${createLink(controller: 'reporteSolicitud', action: 'imprimirSolicitud')}/?id=${solicitud.id}";
//                    console.log(url);
                    location.href = "${createLink(controller:'pdf',action:'pdfLink')}?url=" + url + "&filename=solicitud.pdf";
                    return false;
                });
                $(".list").button("option", "icons", {primary : 'ui-icon-clipboard'});
                $(".create").button("option", "icons", {primary : 'ui-icon-document'});
                $(".revision").button("option", "icons", {primary : 'ui-icon-check'});

            });
        </script>
    </body>
</html>