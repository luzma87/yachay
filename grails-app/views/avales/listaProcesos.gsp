<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <title>Lista de Procesos</title>
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

        <style type="text/css">

        th {
            background-color : #363636 !important;
        }

        </style>
    </head>

    <body>

        <div class="fila">
            <g:link controller="avales" action="crearProceso" class="btn">Crear nuevo Proceso de contratación</g:link>
        </div>
        <table style="width: 95%;margin-top: 10px">
            <thead>
                <tr>
                    <th>Proyecto</th>
                    <th>Nombre</th>
                    <th>Inicio</th>
                    <th>Fin</th>
                    <th>Monto</th>
                    <th></th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <g:each in="${procesos}" var="p">
                    <tr>
                        <td>${p.proyecto}</td>
                        <td>${p.nombre}</td>
                        <td style="text-align: center">${p.fechaInicio?.format("dd-MM-yyyy")}</td>
                        <td style="text-align: center">${p.fechaFin?.format("dd-MM-yyyy")}</td>
                        <td style="text-align: right">
                            <g:formatNumber number="${p.getMonto()}" format="###,##0" minFractionDigits="2" maxFractionDigits="2"></g:formatNumber>
                        </td>
                        <td style="text-align: center">
                            <a href="${g.createLink(action: 'crearProceso', id: p.id)}" class="btn">Editar</a>
                        </td>
                        <td style="text-align: center">
                            <a href="${g.createLink(action: 'avalesProceso', id: p.id)}" class="btn">Avales</a>
                        </td>
                        <td style="text-align: center">
                            <a href="${g.createLink(controller: 'avanceFisico', action: 'list', id: p.id)}" class="btn">Actividades</a>
                        </td>
                    </tr>
                </g:each>

            </tbody>
        </table>
        <script>
            $(".btn").button()
        </script>

    </body>
</html>