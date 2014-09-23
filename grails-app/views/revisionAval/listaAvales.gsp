<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Historial de avales</title>
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
<g:if test="${flash.message}">
    <div class="message ui-state-highlight ui-corner-all">
        ${flash.message}
    </div>
</g:if>
<div class="fila" style="font-size: 10px">
    <div class="labelSvt" style="width:40px;">Año:</div>
    <div class="fieldSvt-small" style="width: 80px">
        <g:select from="${yachay.parametros.poaPac.Anio.list([sort: 'anio'])}" id="anio" name="anio" optionKey="id" optionValue="anio" value="${actual.id}" class="ui-corner-all"></g:select>
    </div>
    <div class="labelSvt" style="width: 60px">Número:</div>
    <div class="fieldSvt-medium">${actual.anio}-GP No.<input type="text" id="numero" class="ui-corner-all" style="width: 100px"></div>
    <div class="labelSvt" style="width: 65px">Proceso:</div>
    <div class="fieldSvt-xl" ><input type="text" id="descProceso" class="ui-corner-all" style="width: 90%"></div>
    <div class="fieldSvt-small" style="width: 80px">
        <a href="#" id="buscar">Buscar</a>
    </div>
</div>
<div id="detalle" style="width: 100%;height: 500px;overflow: auto"></div>
<script>
    function cargarHistorial(anio,numero,proceso){


        $.ajax({
            type:"POST", url:"${createLink(action:'historialAvales', controller: 'revisionAval')}",
            data:{
                anio:anio,
                numero:numero,
                proceso:proceso
            },
            success:function (msg) {
                $("#detalle").html(msg)

            }
        });

    }
    function cargarHistorialSort(anio,numero,proceso,sort,order){
        $.ajax({
            type:"POST", url:"${createLink(action:'historialAvales', controller: 'revisionAval')}",
            data:{
                anio:anio,
                numero:numero,
                proceso:proceso,
                sort:sort,
                order:order
            },
            success:function (msg) {
                $("#detalle").html(msg)

            }
        });

    }
    $("#buscar").button().click(function(){
        cargarHistorial($("#anio").val(),$("#numero").val(),$("#descProceso").val())
    })
</script>
</body>
</html>