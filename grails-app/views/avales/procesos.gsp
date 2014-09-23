<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Procesos ${(proyecto)?' del proyecto '+proyecto:''}</title>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>
    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>

</head>

<body>
<div class="breadCrumbHolder module">
    <div id="breadCrumb" class="breadCrumb module">
        <ul>

        </ul>
    </div>
</div>
<div style="width: 100%;height: 35px">
   <b>Proyecto: </b>
    <g:select from="${yachay.proyectos.Proyecto.list([sort:'nombre'])}" optionKey="id" optionValue="nombre" name="proyecto" id="proyecto" style="width:450px;" class="ui-corner-all ui-widget-content"></g:select>
</div>
</body>
</html>