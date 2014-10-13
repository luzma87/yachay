<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Pantalla de Inicio</title>
    <meta name="layout" content="main"/>
    <style type="text/css">
    @page {
        size: 8.5in 11in;  /* width height */
        margin: 0.25in;
    }
    .item{
        width: 470px;
        height: 216px;
        float: left;
        /*margin: 10px;*/
        /*margin: 8px;*/
        /*border:1px solid #A6C9E2;*/
        background-color: white;

    }
    .imagen{
        width: 160px;
        height: 216px;
        float: right;
    }
    .texto{
        padding-top: 10px;
        margin: 12px;
        font-size: 12px;
        font-style: oblique;
        float: left;
        margin-left: 15px;
        width: 230px;
        text-align: justify;
    }
    .fuera{
        /*margin-left: 15px;*/
        margin-top: 15px;
        /*background-color: #262626;*/
    }
    .desactivado{
        color: #bbc;
    }
    </style>
</head>
<body>
%{-- TODO: Cambiar a: proyectos, biblioteca, entidades, asiganaciones, alertas, informes, --}%
<div class="dialog">
    %{--<div style="text-align: center;"><h1>SISTEMA DE GESTIÓN DE PLANIFICACIÓN INSTITUCIONAL</h1></div>--}%
    <div class="body" style="width: 1000px;height: 600px;position: relative;">
        <div style="width: 100%;height: 80%;float: left;padding-left: 15px;  margin-top: 0px; background-color: #e9e9e9">
            <g:link  controller="proyecto" action="list" title="Gestión de proyectos">
                <div  class="item fuera">
                    <div  class="item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'proyecto.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><h3>Gestión de Proyectos</h3>Marco lógico, metas, indicadores,
                        cronograma de inversión, fuentes de financiamiento, programación de inversiones plurianual.</div>
                    </div>
                </div>
            </g:link>
            <g:link  controller="revisionAval" action="listaAvales"  id="${session.unidad.id}" title="Administración del POA: Avales">
                <div  class="item fuera">
                    <div  class="item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'administracion.jpg')}" width="100%" height="100%"/>
                        </div>
                    <div class="texto"><h3>Administración del POA</h3>Gestión de avales, reformas, reprogramaciones y documentación de respaldo.</div>
                </div>
                </div>
            </g:link>

            <g:link  controller="solicitud" action="list"  id="${session.unidad.id}" title="Solicitudes de contratación">
                <div  class="item fuera">
                    <div  class="item" style="background-color: #d0d0d0">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'contratos.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><h3>Contrataciones</h3>Sistematización del proceso de aprobación de contrataciones ligado al POA</div>
                    </div>
                </div>



            </g:link>

            <g:link  controller="documento" action="list" title="Seguimiento">
                <div  class="item fuera">
                    <div  class="item" style="background-color: #d0d0d0">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'seguimiento.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><h3>Seguimiento</h3>Seguimiento y evaluación del POA, detalle de subactividades, control de avales, anvances físico y económico</div>
                    </div>
                </div>
            </g:link>

        </div>
    <div style="text-align: center; color: #fff;
        height: 40px; width: 100%; display: block; position: absolute; bottom: 40px">
        <img src="${resource(dir: 'images', file: 'escudo.jpg')}"/>
        <img src="${resource(dir: 'images', file: 'ecuador-ama.jpg')}"/>
    </div>

<script type="text/javascript">
    $(".fuera").hover(function(){
        var d =  $(this).find(".imagen")
        d.width(d.width()+10)
//        d.height(d.height()+10)
//        $.each($(this).children(),function(){
//            $(this).width( $(this).width()+10)
//        });
    },function(){
        var d =  $(this).find(".imagen")
        d.width(d.width()-10)
//        d.height(d.height()-10)
    })
</script>
</body>
</html>