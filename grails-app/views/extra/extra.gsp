<%--
  Created by IntelliJ IDEA.
  User: svt
  Date: 8/4/2014
  Time: 4:30 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Aprobación de contrataciones</title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>
    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>
    <style>
    .label{
        font-weight: bold;
        text-align: left;
        width:10%;float: left;
    }
    .field{
        width:85%;float: left;
    }
    </style>
</head>

<body>

<div style="width:100%;height: 120px;float: left">
    <div style="width:10%;float: left" class="label">
        Proyecto:
    </div>
    <div style="width:85%;float: left " class="field">
        <g:select class="required requiredCmb ui-widget-content ui-corner-all" name="proyecto.id"
                  title="Proyecto"
                  from="${proys}" optionKey="id"
                  optionValue="nombre"
                  value="${proyecto?.id}"
                  style="width:100%"
                  />
    </div>
    <div style="width:10%;float: left" class="label">
        Componente:
    </div>
    <div style="width:85%;float: left " id="divComp">
        <g:select from="${app.Componente.list()}"  name="componente" optionKey="id" optionValue="descripcion" style="width:100%" id="comps"></g:select>
    </div>
    <div style="width:10%;float: left" class="label">
        Actividad:
    </div>
    <div style="width:85%;float: left " class="field" id="divAct" >
        <g:select from="${app.Actividad.list()}" name="proyecto" optionKey="id" optionValue="descripcion" style="width:100%"></g:select>
    </div>

</div>
<div style="width:100%;height: 30px;float: left">
    <a href="#" id="crear">Crear actividad</a>
</div>
<div style="width:100%;height: 30px;float: left;margin-top: 10px">
    <div class="label">Monto:</div>
    <div class="field" style="width: 150px"><input type="text" style="width:120px;" class="ui-corner-all"></div>
    <div class="label">TDR <input type="checkbox"/> </div>
    <div class="label">Arrastre <input type="checkbox"/> </div>
    <div class="label">Aprobado <input type="checkbox"/> </div>

</div>
<div style="width:100%;height: 30px;float: left;margin-top: 10px">
    <div class="label">Fuente:</div>
    <div class="field">
        <g:select from="${app.Fuente.list()}" name="fuente" optionKey="id" optionValue="descripcion" style="width:200px"></g:select>
    </div>
</div>
<div style="width:100%;height: 30px;float: left;margin-top: 10px">
    <div class="label" style="width: 300px">Revisión del Dir. Planificación <input type="checkbox"/> </div>
</div>
<div style="width:100%;height: 120px;float: left">
    <textarea style="width: 100%;height: 60px;">

    </textarea>
</div>
<div style="width:100%;height: 30px;float: left">
    <a href="#" id="guardar">Guardar</a>
</div>
<script>
    $("#crear").button();
    $("#guardar").button();
</script>
</body>
</html>