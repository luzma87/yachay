<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName"
           value="${message(code: 'catalogo.label', default: 'Catalogo')}" />
    <title>Catálogo del sistema</title>
</head>

<body>
<div style="width:960px; margin-bottom:5px; margin-top:10px; padding: 4px;" class="ui-corner-all ui-widget-content">
    <a href="#" id="creaCtlg">Crear Catálogo</a>
    <a href="#" id="editCtlg">Editar Catálogo</a>
    <a href="#" id="borraCtlg">Borrar Catálogo</a>
    <span style="font-size: 10pt; color: black; margin-left: 20px;">Seleccione un Catálogo
    <g:select optionKey="id" from="${yachay.parametros.Catalogo.list([sort: 'nombre'])}" name="catalogo"
              value="${cataloInstace?.id}" style="width: 280px;"></g:select>
    </span>
    <input class="modulo" type="button" id="ver" name="modulo" value="Ver Items"/>
</div>



<br/>

<div class="" id="parm">

    <div id="ajx" style="width:820px; padding-left: 20px; "></div>

    <div id="ajx_prfl" style="width:540px;"></div>

    <div id="ajx_menu" style="width:540px;"></div>

</div>

<div id="datosPerfil" class="container entero  ui-corner-bottom">
</div>


<script type="text/javascript">
    $(document).ready(function() {
        $("#tipo").buttonset();
        $("#botones").buttonset();

        $("#procesos").click(function() {
            var datos = armar()
            //alert(datos)
            $.ajax({
                type: "POST", url: "../ajaxPermisos",
                data: "ids=" + datos + "&tipo=P",
                success: function(msg) {
                    $("#ajx").html(msg)
                }
            });
        });

        $(".modulo").click(function() {
            var datos = armar()
//            alert(datos)
            $.ajax({
                type: "POST", url: "${createLink(action:'cargaItem',controller:'catalogo')}",
                data: "ids=" + datos + "&ctlg=" + $('#catalogo').val(),
                success: function(msg) {
                    $("#ajx").html(msg)
                }
            });
        })

        $(".rd_tipo").click(function(){
            $("#ajx").html('')
            //location.reload();
        })

        $("#perfil").click(function(){
            $("#ajx").html('')
            //location.reload();
        })


        $("#vermenu").button().click(function() {
            $.ajax({
                type: "POST", url: "../verMenu",
                data: "prfl=" + $('#perfil').val() + "&tpac=" + tipo(),
                success: function(msg) {
                    $("#ajx").html(msg)
                }
            });
        });

        function armar() {
            var datos = new Array()
            $('.modulo:checked').each(
                    function() {
                        datos.push($(this).val());
                    }
            )
            return datos
        }

        ;

        function tipo() {
            var datos = new Array()
            $('.rd_tipo:checked').each(
                    function() {
                        datos.push($(this).val());
                    }
            )
            return datos
        }

        ;


        $("#creaCtlg").button().click(function() {
            //alert("crear un perfil");
            $.ajax({
                type: "POST", url: "${createLink(action:'creaCtlg',controller:'catalogo')}",
                data: "&tbla=prfl",
                success: function(msg) {
                    $("#ajx_prfl").dialog("option","title","Crear Catálogo")
                    $("#ajx_prfl").html(msg).show("puff", 100)
                }
            });
            $("#ajx_prfl").dialog("open");
        });

        $("#editCtlg").button().click(function() {
            $.ajax({
                type: "POST", url: "${createLink(action:'editCtlg',controller:'catalogo')}",
                data: "&id=" + $('#catalogo').val(),
                success: function(msg) {
                    $("#ajx_prfl").dialog("option","title","Editar Catálogo")
                    $("#ajx_prfl").html(msg).show("puff", 100)
                }
            });
            $("#ajx_prfl").dialog("open");
        });

        $("#borraCtlg").button().click(function() {
            if (confirm("Seguro que desea Borrar el catálogo\n\n" + $('#catalogo :selected').text() )) {
                $.ajax({
                    type: "POST", url: "${createLink(action:'borraCtlg',controller:'catalogo')}",
                    data: "&id=" + $('#catalogo').val(),
                    success: function(msg) {
                        location.reload(true);
                    }
                });
            }
        });


        $("#ajx_prfl").dialog({
            autoOpen: false,
            resizable:false,
            title: 'Crear un Catálogo',
            modal:true,
            draggable:false,
            width:420,
            position: 'center',
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            buttons: {
                "Grabar": function() {
                    $(this).dialog("close");
                    $.ajax({
                        type: "POST", url:  "${createLink(action:'grabaCtlg',controller:'catalogo')}",
                        data: "&nombre=" + $('#nombre').val() + "&codigo=" + $('#codigo').val() +
                            "&estado=" + $('#estado').val() + "&id=" + $('#id_ctlg').val(),
                        success: function(msg) {
                            //$("#ajx").html(msg)
                            location.reload(true);

                        }
                    });
                },
                "Cancelar": function() {
                    $(this).dialog("close");
                }
            }
        });


        $("#ajx_menu").dialog({
            autoOpen: false,
            resizable:false,
            title: 'Crear un Módulo',
            modal:true,
            draggable:false,
            width:420,
            position: 'center',
            open: function(event, ui) {
                $(".ui-dialog-titlebar-close").hide();
            },
            buttons: {
                "Grabar": function() {
                    $(this).dialog("close");
                    $.ajax({
                        type: "POST", url: "../grabaMdlo",
                        data: "&nombre=" + $('#nombre').val() + "&descripcion=" + $('#descripcion').val() +
                                "&id=" + $('#id_mdlo').val() + "&orden=" + $('#orden').val(),
                        success: function(msg) {
                            $("#ajx").html(msg)
                            location.reload(true);

                        }
                    });
                },
                "Cancelar": function() {
                    $(this).dialog("close");
                }
            }
        });

    });

</script>

</body>
</html>