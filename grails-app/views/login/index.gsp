<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Finix - app</title>
        <link rel="shortcut icon" href="${resource(dir: 'images', file: 'logo-1.jpg')}" type="image/x-icon"/>
        <script type="text/javascript" src="${resource(dir: 'js/jquery/js', file: 'jquery-1.5.1.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/js', file: 'jquery-ui-1.8.13.custom.min.js')}"></script>
        <link rel="stylesheet" href="${resource(dir: 'js/jquery/css/start', file: 'jquery-ui-1.8.13.custom.css')}"/>
        <style type="text/css">
        .esquinas {
            -moz-border-radius    : 10px;
            -webkit-border-radius : 10px;
            border-radius         : 10px;

        }

        input {
            height       : 30px;
            background   : #c7c7c7;
            width        : 200px;
            float        : left;
            margin-top   : 0px;
            margin-left  : 35px;
            color        : #555;
            padding-left : 5px;
            /*font-family: fantasy;*/
            font-weight  : bold;
            font-size    : 13px
        }
        </style>
    </head>

    <body background="${resource(dir: 'images', file: 'yachay_fndo.jpg')}" style="no-repeat center center fixed;
  -webkit-background-size: cover;  -moz-background-size: cover;  -o-background-size: cover;  background-size: cover;"
  %{--style="background-repeat: no-repeat; background-position: center; width: 100%; height: 100%; resize: "--}%>
    %{--<body background="${resource(dir: 'images', file: 'yachay_fndo.jpg')}" style="background-repeat: no-repeat; background-position: center;">--}%
    <div style="font-size: 28px; font-family: 'Arial'; color: #fff;
    background-color: #1b97a7; height: 40px; padding: 20px; text-align: left; width: 100%;">SISTEMA DE GESTIÓN DE PLANIFICACIÓN INSTITUCIONAL
        <img src="${resource(dir: 'images', file: 'logo-transp.png')}" alt="Yachay" width="148px;" style="float: right; margin-top: -10px; margin-right: 40px;">
    </div>

        %{--<div class="esquinas" style="width: 800px;height: 500px; margin: auto;margin-top:100px;background: #ffffff; border:medium solid #262626; background-image: \"${resource(dir: 'images', file: 'yachay-logo.png')}\"">--}%
        %{--<div class="esquinas" style="width: 1360px;height: 884px; margin: auto;margin-top:100px;border:medium solid #262626; ">--}%
        <div class="esquinas" style="width: 800px;height: 460px; margin: 100px; background: #1e4f5a; float: right; opacity: 0.9">


            <div style="margin-left: 30px;margin-top: 20px;float: left;width: 420px; height: 400px; font-size: 14px;text-align: justify; color: #fff" class="ui-corner-all">
                <ul><li><b>Manejo de proyectos</b>: marco lógico, metas, indicadores, cronograma de inversión, fuentes
                    de financiamiento, programación de inversiones plurianual.</li>
                    <li><b>Formulación del Plan Operativo Anual - POA de inversión</b>: manejo de actividades, metas,
                    indicadores, programación de inversión, programa presupuestario compatible con el Sistema Financiero
                    Institucional.</li>
                    <li><b>Formulación del Plan Anual de Contrataciones - PAC inicial</b>: programación de compras públicas por cuatrimestres.</li>
                    <li><b>Cruce de información financiera</b> para determinar la ejecución presupuestaria.</li>
                    <li><b>Ejecución del POA y del Plan Anual de Inversión - PAI</b>, control de avales, modificaciones, reformas y reprogramación del POA.</li>
                    <li><b>Administración de avales</b>: emisión, seguimiento y liquidación.</li>
                    <li><b>Reportes inteligentes</b> de indicadores, POA, seguimiento y evaluación del POA.
                    </li></ul>
                %{--<img src="${resource(dir: 'images', file: 'logo_app2.jpg')}" alt="Finix - Plan" >--}%
            </div>
                %{--<div style=" width: 250px;height: 260px;margin-top: 60px;float: left;margin-left: 60px; ;background:#0066cc"--}%
                %{--<div style=" width: 260px; height: 320px;margin-top: 30px;float: left;margin-left: 60px; ;background:#ffffff; border-style: solid; border-color: #666; border-width: 1px;"--}%
                <div style=" width: 260px; height: 400px;margin-top: 30px;float: left;margin-left: 60px; border-style: solid; border-color: #666; border-width: 1px; background-color: #DDDDDD"

            %{--<div style=" width: 260px;height: 260px;margin-top: 60px;float: left;margin-left: 60px; ;background:#a5815f"--}%
                 class="esquinas">

                <div style="height: 80px;margin-top: 25px;margin-left:30px;width: 200px;color:#f9f9f9;font-style:
                italic;text-align: center; " class="ui-corner-all">
                    <img src="${resource(dir: 'images', file: 'logo_v.png')}" alt="Yachay" width="180px;">
                    %{--<b>Sistema de Planificación Institucional<br/> Yachay</b>--}%
                </div>

                <div style="margin-top: 150px;float: left;position: relative">
                    <g:form action="login" controller="login">
                        <g:if test="${!session.usuario}">
                            <input type="text" class="ui-corner-all" id="usr" name="usuario" value="usuario">
                            <input type="password" style="margin-top: 10px" class="ui-corner-all" id="psw" name="password">

                            <div id="mascara"
                                 style="position: absolute;left:35px;top:40px;width: 200px;height: 30px;line-height: 30px;padding-left: 5px;color: #444;font-size: 13px;">contraseña</div>
                            <g:submitButton name="entrar" value="Validar"
                                            style="height: 30px;margin-top: 15px;width: 70px;border: 1px solid black;float:right;margin-right:25px; background-color: #1e4f5a; color: #fff"
                                            class="ui-corner-all"/>
                        </g:if>
                        <g:else>
                            <g:select from="${perfiles}" name="perfil" optionKey="id"
                                      style="height: 25px;width: 230px;margin-top: 10px;background: #c7c7c7;margin-left: 20px"/>
                            <g:submitButton value="Entrar" name="entrar"
                                            style="height: 30px;margin-top: 15px;width: 70px;border: 1px solid black;float:right;margin-right:25px;"
                                            class="ui-corner-all"/>
                        </g:else>
                    </g:form>
                </div>

        </div>
            <div style="position: absolute; margin-top:410px; margin-left:580px; text-align: left; font-size: 12px;">
            %{--&copy; TEDEIN S.A. Versión ${message(code: 'version', default: '1.1.0x')}</div>--}%
            Versión ${message(code: 'version', default: '1.1.0x')}</div>
        </div>
    <div style="text-align: center; font-size: 14px; font-family: 'Arial'; color: #fff;
    background-color: #1b97a7; height: 20px; padding: 5px; width: 100%; display: block; position: absolute; bottom: 0 ">2014 Todos los derechos reservados. Empresa Pública Yachay</div>
        <script>
            $(document).ready(function () {
                if ($("#psw").val() != "")
                    $("#mascara").hide();
                $("#usr").focus(function () {
                    $("#usr").val("")
                });
                $("#usr").blur(function () {
                    if ($(this).val() == "")
                        $("#usr").val("usuario")
                });

                $("#mascara").click(function () {
                    $("#mascara").hide();
                    $("#psw").focus();
                });
                $("#psw").focus(function () {
                    $("#mascara").hide();
                });
                $("#psw").blur(function () {
                    if ($(this).val() == "")
                        $("#mascara").show();
                });

            });

        </script>

    </body>
</html>