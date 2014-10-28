<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="App"/></title>
        %{--<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" />--}%

        <g:render template="../forms"/>
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'logo-1.jpg')}" type="image/x-icon"/>

        %{--<script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery-1.6.2.min.js')}"></script>--}%
        <script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery-1.7.2.min.js')}"></script>
        %{--<script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery-ui-1.8.16.custom.min.js')}"></script>--}%


        %{--<link rel="stylesheet" href="${resource(dir: 'js/jquery/kendo/styles', file: 'kendo.common.min.css')}"/>--}%
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/kendo/styles', file: 'kendo.common.min.css')}"/>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/css/twit', file: 'jquery-ui-1.8.21.custom.css')}"/>
        <link rel="stylesheet/less" type="text/css" href="${resource(dir: 'js/jquery/kendo/styles', file: 'kendo_twit.css.less')}">


        <!-- Font Awesome -->
        <link rel="stylesheet" href="${resource(dir: 'css/font-awesome-4.2.0/css', file: 'font-awesome.min.css')}">

        %{--

                <g:if test="${session.color}">
                    <link rel="stylesheet" href="${resource(dir: 'js/jquery/css/' + session.color, file: 'jquery-ui-1.8.21.custom.css')}"/>
                    <link rel="stylesheet/less" type="text/css" href="${resource(dir: 'js/jquery/kendo/styles', file: 'kendo_' + session.color + '.css.less')}">
                </g:if>
                <g:else>
                    <link rel="stylesheet" href="${resource(dir: 'js/jquery/css/twit', file: 'jquery-ui-1.8.21.custom.css')}"/>
                    <link rel="stylesheet/less" type="text/css" href="${resource(dir: 'js/jquery/kendo/styles', file: 'kendo_twit.css.less')}">
                </g:else>
        --}%

        <script src="${resource(dir: 'js', file: 'less-1.1.3.min.js')}" type="text/javascript"></script>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/kendo/js', file: 'kendo.all.min.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'menuHorizontal.css')}"/>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery-ui-1.8.21.custom.min.js')}"></script>

        <script type="text/javascript" src="${resource(dir: 'js', file: 'funciones.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js', file: 'date-es-EC.js')}"></script>

        %{--<link rel="stylesheet" href="${resource(dir: 'js/jquery/css/start', file: 'jquery-ui-1.8.16.custom.css')}"/>--}%


        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/countdown', file: 'jquery.countdown.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/countdown', file: 'jquery.countdown.css')}"/>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery.ui.datepicker-es.js')}"></script>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/box/js', file: 'jquery.luz.box.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/box/css', file: 'jquery.luz.box.css')}"/>


        <g:layoutHead/>
        <g:javascript library="application"/>
    </head>

    <body>

        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir: 'images', file: 'spinner.gif')}"
                 alt="${message(code: 'spinner.alt', default: 'Loading...')}"/>
        </div>

        <div style="height: 20px; background:none; margin-bottom: 5px; width: 100%;"></div>

        %{--
                <div id="treeMenu"
                     style="margin-left: 10px;margin-top: 0px;float: left; position: absolute; left:20px; top: 3px;background: none">
                    <g:generarMenuHorizontal/>
                </div>
        --}%

        %{--
                <div style="width: 60px;height: 30px;position: absolute;top:3px;left: 845px;font-size: 11px;line-height: 15px;" title="Cambiar color">

                    <div style="width: 20px;height: 20px;background-color: black;float: left;margin-top: 5px;margin-right: 10px;cursor: pointer " id="twit" class="color"></div>

                    <div style="width: 20px;height: 20px;background-color: #C2B072;float: left;margin-top: 5px;cursor: pointer" id="cafe" class="color"></div>

                </div>
        --}%



        <div class="ui-dialog ui-widget ui-widget-content ui-corner-all"
             style="height: 740px;  width: 1100px; margin-left:10px; position: absolute; left: 20px; top:2px;overflow-y: hidden">
            <div class=" ui-helper-clearfix" style="background-color: #1b97a7; color: #fff; height: 21px; font-size: 14px; font-weight: bold; padding: 1px; margin-top: -1px;">
                <span class="ui-dialog-title" style="float: left; text-align: left">SISTEMA DE GESTIÓN DE PLANIFICACIÓN INSTITUCIONAL</span>
                <span class="ui-dialog-title" style="float: right; text-align: center; width: 50%">- <g:layoutTitle default=""/> -</span>
            </div>

            <div id="treeMenu"
                 style="margin-left: 5px;margin-top: 5px;float: left; width: 100%;height: 40px;background: none">
                <div style="width: 60px; float: left"><img src="${resource(dir: 'images', file: 'logo.jpg')}" alt="Yachay" width="50px;" style="float: left; margin: 2px;">
                </div>
                <g:generarMenuHorizontal/>
            </div>

            <div id="divDlgBody" class="ui-dialog-content ui-widget-content" style="height: 620px;">
                <g:layoutBody/>

            </div>

            <div style="position: fixed; bottom: 0; width: 100%; height: 10px; text-align: center; background-color: #1b97a7; color: #fff; height: 14px; font-size: 10px; padding: 2px;">
                <span style="float: none; ">2014 Todos los derechos reservados. Empresa Pública Yachay</span>
            </div>
            <script type="text/javascript">


                function alerta() {
                    $("#texto_count").css("color", "red").show("pulsate")
                    $("#countdown").css("color", "red")
                }

                $(".color").click(function () {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'cambiarColor',controller:'inicio')}",
                        data    : "color=" + $(this).attr("id"),
                        success : function (msg) {
                            if (msg == "ok") {
                                window.location.reload(true)
                            } else
                                location.href = "${createLink(controller:'login',action:'logout')}"
                        }
                    });
                });


            </script>
    </body>

</html>