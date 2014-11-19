<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Lista de hitos</title>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'svt.css')}"
          type="text/css"/>
    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>

    <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

    <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.meio.mask.js')}"></script>
    <style>
    .tipo{
        font-weight: bold;
    }
    </style>
</head>
<body>
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div class="fila">
    <g:link controller="hito" action="crearHito" class="btn">Crear nuevo</g:link>
</div>
<div style="width: 95%;height: 600px;overflow: auto;margin-top: 20px" class="ui-corner-all">
    <div id="detalle" style="width: 95%">
        <table style="width: 100%">
            <thead>
            <tr>
                <th>Fecha</th>
                <th>Descripción</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${hitos}" var="h">
                <tr>
                    <td>${h.fecha.format("dd-MM-yyyy")}</td>
                    <td>${h.descripcion}</td>
                    <td style="text-align: center">
                        <g:link controller="hito" action="crearHito" id="${h.id}" class="btn" >Ver</g:link>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
</div>
<script>
    $(".btn").button()
</script>
</body>
</html>