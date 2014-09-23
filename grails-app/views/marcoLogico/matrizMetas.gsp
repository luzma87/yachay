<%@ page import="yachay.parametros.TipoElemento" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Componente: ${componente.objeto}</title>

    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'Base.css')}"
          type="text/css"/>
    <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/jBreadCrumb/Styles', file: 'BreadCrumb.css')}"
          type="text/css"/>

    <script src="${resource(dir: 'js/jquery/plugins/', file: 'jquery.easing.1.3.js')}" type="text/javascript"
            language="JavaScript"></script>
    <script src="${resource(dir: 'js/jquery/plugins/jBreadCrumb/js', file: 'jquery.jBreadCrumb.1.1.js')}"
            type="text/javascript" language="JavaScript"></script>
    <style type="text/css">
    .error {
        background: #ffa5a2 !important;

    }
    </style

</head>

<body>
<div class="body">
    <div class="dialog">

        <div class="breadCrumbHolder module">
            <div id="breadCrumb" class="breadCrumb module">
                <ul>
                    <li>
                        <g:link class="bc" controller="proyecto" action="show"
                                id="${componente.proyecto.id}">
                            Proyecto
                        </g:link>
                    </li>

                    <li>
                        <g:link class="bc" controller="marcoLogico" action="showMarco"
                                id="${componente.proyecto.id}">
                            Marco L&oacute;gico
                        </g:link>
                    </li>

                    <li>
                        <g:link class="bc" controller="marcoLogico" action="componentes"
                                id="${componente.proyecto.id}">
                            Componentes
                        </g:link>
                    </li>

                    <li>
                        Metas
                    </li>
                </ul>
            </div>
        </div>




        <div class="ui-corner-all " style="position: relative;border:1px solid black;padding: 10px;" id="paginate_container">
            <div id="mascara" style="position: absolute;left: 0px;right: 0px;width: 100%;height: 100%;display: hidden;background: rgba(0,0,0,0.4)">
                <img src="${resource(dir: 'images',file: 'loading.gif')}" alt="">
            </div>

            <table style="margin-top: 0px;width: 100%" id="paginate_table">
                <thead>
                <tr>
                    <th style="width: 20px;">#</th>
                    <th style="width: 100px;">Año</th>
                    <th style="width: 150px;">Indicador</th>
                    <th style="width: 150px;">Unidad</th>
                    <th style="width: 100px;">Meta</th>
                    <th style="width: 300px;">Parroquia</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${1..100}" var="i">
                    <tr class="">
                        <td>${i}</td>
                        <td><g:select class="field ui-widget-content ui-corner-all anio" name="anio.id"
                                      title="año" from="${anios}" optionKey="id"  noSelection="['null': '']"
                                      value="${metaInstance?.anio?.id}" style="width: 80px;"/></td>
                        <td>
                            <g:select class="field ui-widget-content ui-corner-all tipo" name="tipoMeta.id"
                                      title="tipoMeta" from="${tipos}" optionKey="id"
                                      noSelection="['null': '']"/>
                        </td>
                        <td style="width: 150px;">
                            <g:select class="field ui-widget-content ui-corner-all unidad" name="unidad.id"
                                      title="unidad"
                                      from="${unidades}" optionKey="id"
                                      noSelection="['null': '']" style="width: 150px;"/>
                        </td>
                        <td>
                            <input type="text" class="meta" style="width: 80px">
                        </td>
                        <td>
                            <a href="#" id="buscar" class="buscar btn" hid="parrId_${i}" div="parrNombre_${i}" style="float: left;width: 25px">Buscar</a>
                            <input type="hidden" id="parrId_${i}" name="parroquia.id" class="parroquia">
                            <div id="parrNombre_${i}" style="width: 250px;float: left;margin-left: 10px;"> </div>
                        </td>
                    </tr>
                </g:each>

                </tbody>
            </table>

        </div>

        <a href="#" class="btn " id="guardar" style="margin-top: 15px;">Guardar</a>
    </div>
</div>
<div id="dlg_buscar">
    <div style="width: 90%;height: 30px;">
        <input type="hidden" id="div">
        <input type="hidden" id="hid">
        <input type="text" id="txt_buscar">

        <a href="#" id="buscar_dlg" class="btn">Buscar</a>
    </div>

    <div id="parrs"
         style="width: 95%;height: 340px;overflow-y: auto;margin: auto;margin-top: 10px;border: 1px solid black;"
         class="ui-corner-all"></div>
</div>
<g:form class="frm_metas" action="guardarMetasMatriz">
    <input type="hidden" id="data"  name="data">
    <input type="hidden" name="id" value="${componente.id}">
</g:form>
<script type="text/javascript">
    function paginacion(){
        $("#mascara").show()
        var registros = 15; //registros por pagina
        var total = $("#paginate_table").find("tbody").find("tr").size()
        var paginas = Math.ceil(total/registros)

        var i = 0
        $.each($("#paginate_table").find("tbody").find("tr"),function(i){
            if(i<registros){
                $(this).show().addClass("activo")
            }else{
                $(this).hide().addClass("inactivo")
            }
            i++
        });
        var paginate = $("<div>")
        paginate.attr("id","paginate_controls")
        paginate.css({width:$("#paginate_container").width(),height:25})
        for(i=0;i<paginas;i++){
            var pag= $("<div>")
            pag.css({width:20,height:18,background:"#ddd",margin:2,float:"left",textAlign:"center",paddingTop:3,border:"1px solid black",cursor:"pointer"})
            if(i==0){
                pag.css({background:"#3684C4"})
            }
            pag.html(i+1);
            pag.attr("num",i)
            pag.attr("reg",registros)
            pag.addClass(""+i)
            pag.bind("click",function(){
                recargar($(this).attr("num")*1,$(this).attr("reg")*1);
            })
            paginate.append(pag)
        }
        $("#paginate_container").append(paginate)
        $("#mascara").hide("explode")
    }
    function recargar(num,registros){
        $("#mascara").show()
        var i = 0;
        var inicio = num*registros

        $.each($("#paginate_table").find("tbody").find("tr"),function(i){
            if(i<inicio || i>(inicio+registros-1)){
                $(this).hide().addClass("inactivo")
            }else{
                $(this).show().addClass("activo")
            }
            i++
        });
        $("#paginate_controls").find("div").css({background:"#ddd"})
        $("#paginate_controls").find("."+num).css({background:"#3684C4"})
        $("#mascara").hide("explode")
    }
    $(function () {

        paginacion();
        $(".buscar").button({
            icons:{
                primary:"ui-icon-search"
            },
            text:false
        }).click(function () {
                    $("#div").val($(this).attr("div"))
                    $("#hid").val($(this).attr("hid"))
                    $("#dlg_buscar").dialog("open")
                });
        $("#dlg_buscar").dialog({
            width:630,
            height:500,
            position:"center",
            modal:true,
            autoOpen:false,
            title:"Parroquias"
        })
        $("#buscar_dlg").click(function () {
            $.ajax({
                type:"POST",
                url:"${createLink(action:'buscarParroquias',controller:'meta')}",
                data:"parametro=" + $("#txt_buscar").val(),
                success:function (msg) {
                    $("#parrs").html(msg)

                }
            });
        });
        $(".btn").button();
        $("#guardar").click(function(){
            if(confirm("Esta seguro?")){
                $("#data").val("")
                $.each($("#paginate_table").find("tbody").find("tr"),function(data){
                    var band = true
                    var tipo = $(this).find(".tipo").val()
                    var anio =$(this).find(".anio").val()
                    var unidad  =$(this).find(".unidad").val()
                    var meta = $(this).find(".meta").val()
                    var parroquia =$(this).find(".parroquia").val()
                    $(this).removeClass("error")

                    if(anio!="null"){

                        if(tipo=="null")
                            band=false
                        if(unidad=="null")
                            band=false
                        if(meta=="" || isNaN(meta))
                            band=false
                        if(parroquia=="" || isNaN(parroquia))
                            band=false
                        if(band){
                            $("#data").val($("#data").val()+anio+"&&"+tipo+"&&"+unidad+"&&"+meta+"&&"+parroquia+"%%")
                        }else{
                            $(this).addClass("error")
                        }

                    }
                });
                if($(".error").size()>0){
                    alert("Algunos registros contienen errores, revice las filas resaltadas en color rojo")
                } else{
                    if($("#data").val().length>1)
                        $(".frm_metas").submit()
                }

            }
        });

    });
</script>

</body>
</html>