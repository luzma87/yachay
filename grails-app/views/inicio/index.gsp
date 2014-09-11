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
        width: 400px;height: 220px;float: left;margin: 8px;
        /*border:1px solid #A6C9E2;*/
        /*background-color: white;*/

    }
    .imagen{
        width: 170px;
        height: 120px;
        margin: auto;
        margin-top: 15px;
    }
    .texto{
        width: 90%;
        height: 50px;
        padding-top: 10px;
        /*border: solid 1px black;*/
        margin: auto;
        margin: 12px;
        /*font-family: fantasy; */
        font-size: 12px;
/*
        font-weight: bolder;
*/
        font-style: oblique;
        /*text-align: justify;*/
    }
    .fuera{
        margin-left: 15px;
        margin-top: 20px;
        /*background-color: #317fbf; */
        background-color: #262626;
    }
    .desactivado{
        color: #bbc;
    }
    </style>
</head>
<body>
%{-- TODO: Cambiar a: proyectos, biblioteca, entidades, asiganaciones, alertas, informes, --}%
<div class="dialog">
    <div style="text-align: center;"><h1>Sistema de Gestión de Planificación Institucional</h1></div>
    <div class="body" style="width: 1000px;height: 580px;position: relative;">
        <div style="width: 90%;height: 90%;float: left;padding-left: 80px;  margin-top: 0px;">
            <g:link  controller="proyecto" action="list" title="Gestión de proyectos">
                <div  class="ui-corner-all  item fuera">
                    <div  class="ui-corner-all ui-widget-content item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'proyecto.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><b>Gestión de Proyectos</b>: Marco lógico, metas, indicadores,
                        cronograma de inversión, fuentes de financiamiento, programación de inversiones plurianual.</div>
                    </div>
                </div>
            </g:link>
            <g:link  controller="revisionAval" action="listaAvales"  id="${session.unidad.id}" title="Administración del POA: Avales">
                <div  class="ui-corner-all item fuera">
                    <div  class="ui-corner-all ui-widget-content item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'administracion.jpg')}" width="100%" height="100%"/>
                        </div>
                    <div class="texto"><b>Administración del POA</b>: gestión de avales, reformas, reprogramaciones y documentación de respaldo.</div>
                </div>
                </div>
            </g:link>

            <g:link  controller="solicitud" action="list"  id="${session.unidad.id}" title="Solicitudes de contratación">
                <div  class="ui-corner-all item fuera">
                    <div  class="ui-corner-all ui-widget-content item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'contratos.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><b>Contrataciones</b>: sistematización del proceso de aprobación de contrataciones ligado al POA</div>
                    </div>
                </div>



            </g:link>

            <g:link  controller="documento" action="list" title="Seguimiento">
                <div  class="ui-corner-all  item fuera">
                    <div  class="ui-corner-all ui-widget-content item">
                        <div class="imagen">
                            <img src="${resource(dir: 'images', file: 'seguimiento.jpg')}" width="100%" height="100%"/>
                        </div>
                        <div class="texto"><b>Seguimiento</b>: seguimiento y evaluación del POA, detalle de subactividades, control de avales, anvances físico y económico</div>
                    </div>
                </div>
            </g:link>

        </div>
    </div>
    <div style="height: 25px;width: 200px;position:absolute;bottom: 1px;right: 5px;text-align: left">&copy; TEDEIN S.A. Versión ${message(code: 'version', default: '1.1.0x')}</div>
</div>
<script type="text/javascript">
    $(".fuera").hover(function(){
        var d =  $(this).find(".imagen")
        d.width(d.width()+10)
        d.height(d.height()+10)
//        $.each($(this).children(),function(){
//            $(this).width( $(this).width()+10)
//        });
    },function(){
        var d =  $(this).find(".imagen")
        d.width(d.width()-10)
        d.height(d.height()-10)
    })
</script>
</body>
</html>