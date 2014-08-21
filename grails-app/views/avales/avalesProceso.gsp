<%@ page import="app.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Crear proceso</title>
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

</head>
<body>
<div class="fila">
    <g:link controller="avales" action="listaProcesos" class="btn">Procesos</g:link>
    <g:link controller="avales" action="solicitarAval" class="btn" id="${proceso.id}">Solicitar</g:link>
</div>
<div id="tabs" style="width: 1050px;margin-top: 10px;">
    <ul>
        <li><a href="#lista">Avales</a></li>
        <li><a href="#solicitudes">Solicitudes</a></li>
    </ul>
    <div id="lista" style="width: 960px;">
        <g:if test="${avales.size()>0}">
            <table style="width: 95%;margin-top: 10px" >
                <thead>
                <tr>
                    <th>Proceso</th>
                    <th>Conceto</th>
                    <th>Monto</th>
                    <th>Estado</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${avales}" var="p">
                    <tr>
                        <td>${p.proceso}</td>
                        <td>${p.concepto}</td>
                        <td style="text-align: right">${p.monto()}</td>
                        <td style="text-align: right">${p.estado?.descripcion}</td>
                        <td style="text-align: center">

                        </td>
                        <td style="text-align: center">

                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>
        </g:if>
    </div>
    <div id="solicitudes" style="width: 960px;">
        <g:if test="${solicitudes.size()>0}">
            <table style="width: 95%;margin-top: 10px" >
                <thead>
                <tr>
                    <th>Proceso</th>
                    <th>Conceto</th>
                    <th>Monto</th>
                    <th>Estado</th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${avales}" var="p">
                    <tr>
                        <td>${p.proceso}</td>
                        <td>${p.concepto}</td>
                        <td style="text-align: right">${p.monto()}</td>
                        <td style="text-align: right">${p.estado?.descripcion}</td>
                        <td style="text-align: center">

                        </td>
                        <td style="text-align: center">

                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>
        </g:if>
    </div>

</div>
<script>
    $(".btn").button()
    $("#tabs").tabs()
</script>
</body>
</html>