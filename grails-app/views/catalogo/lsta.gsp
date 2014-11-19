<style type="text/css">
.boton {
    background: #fff url(../images/skin/shadow.jpg) bottom repeat-x;
    border: 1px solid #ccc;
    color: #666;
    font-size: 10px;
    margin-top: 2px;
    overflow: hidden;
    padding: 3px;
}

.botonNuevo {
    background: #fff url(../images/skin/shadow.jpg) bottom repeat-x;
    border: 1px solid #ccc;
    color: #666;
    font-size: 10px;
    overflow: hidden;
    padding: 5px;
}

a:link, a:visited, a:hover {
    color: #666;
    font-weight: bold;
    text-decoration: none;
}

.edit {
    background: transparent url(../images/skin/database_edit.png) 5px 50% no-repeat;
    padding-left: 28px;
}
.borrar {
    background: transparent url(../images/skin/database_delete.png) 5px 50% no-repeat;
    padding-left: 28px;
}
.nuevo {
    background: transparent url(../images/skin/database_add.png) 5px 50% no-repeat;
    padding-left: 28px;
}
</style>

<input type="button" class="botonNuevo nuevo" id="${catalogoInstance?.id}" value="Crear Item"/>

<g:form action="grabar" method="post" style="margin-top: 10px;">
    <!-- <div style="height: 400px; overflow: auto;"> -->
    <input type="hidden" id="mdlo__id" value="${mdlo__id}">
    <input type="hidden" id="tpac__id" value="${mdlo__id}">
    <g:if test="${datos?.size()>0}">
        <div class="ui-corner-all" style="width: 940px; height: 520px; overflow:auto; margin-bottom: 5px; margin-left: -10px; background-color: #efeff8;
             border-style: solid; border-color: #AAA; border-width: 1px; ">
            <table border="0" cellpadding="0" width="900px">
%{--                <thead style="color: #101010; background-color: #69b0d3">--}%
                <thead style="color: #101010; background-color: #69b0e3">
                <tr>
                    <th "width="100px">Código</th>
                    <th "width="400px">Descripción</th>
                    <th "width="100px">Estado</th>
                    <th "width="80px">Orden</th>
                    <th "width="100px">Original</th>
                    <th "width="100px">Operaciones</th>
                </tr>
                </thead>
                <tbody>
                <!-- <hr>Hola ${lista}</hr> -->
                <g:each in="${datos}" status="i" var="d">
                    <tr class="${(i % 2) == 0 ? 'odd' : 'even'}" iden="${d[0]}"  style="height: 27px;">
                    %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}" style="background: ${(d[0]) ? '#a0c9e2' : ''}">--}%
                        <td>${d[1]?.encodeAsHTML()}</td>
                        <td>${d[2]?.encodeAsHTML()}</td>
                        <td>${d[3]?.encodeAsHTML()}</td>
                        <td>${d[4]?.encodeAsHTML()}</td>
                        <td>${d[5]?.encodeAsHTML()}</td>
                        <td width="150px;">
                            %{--<g:link class="boton edit" action="edit" id="${catalogoInstance?.id}">Editar</g:link>--}%
                            %{--<g:link class="boton borrar" action="edit" id="${catalogoInstance?.id}">Borrar</g:link>--}%
                            <input class="modulo editar" type="button" id="editar1" iden="${d[0]}" cata="${catalogo}" value="Editar"/>
                            <input class="modulo borrar" type="button" id="borrar1" iden="${d[0]}" value="Borrar"/>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:if>
    %{--<input id="aceptaAJX" type="button" class="grabaPrms" value="Fijar permisos del Menú">--}%

</g:form>

<div id="editar_dlg">
    <table>
        <tbody>
        <tr class="prop">
            <td valign="top" class="codigo">
                <label for="codigo">Código</label>
            </td>
            <td valign="top">
                <g:textField  name="codigo" id="codigo" title="Código del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="" />
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="descripcion">
                <label for="descripcion">Descripción</label>
            </td>
            <td valign="top">
                <g:textField  name="descripcion" id="descripcion" title="Descripción del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="" />
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="estado">
                <label for="estado">Estado</label>
            </td>
            <td valign="top">
                <g:textField  name="estado" id="estado" title="Estado del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="1" value="" />
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="orden">
                <label for="orden">Orden</label>
            </td>
            <td valign="top">
                <g:textField  name="orden" id="orden" title="Orden del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="" />
            </td>
        </tr>
        <tr class="prop">
            <td valign="top" class="original">
                <label for="original">Original</label>
            </td>
            <td valign="top">
                <g:textField  name="original" id="original" title="Original del item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="255" value="" />
            </td>
        </tr>

        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function() {
        $("#aceptar").click(function() {
            alert("ohhhhh")
        });

    });

    $(".nuevo").click(function() {
        if (confirm("Actualizar los permisos.. \n Seguro??")) {
            var data = armarAccn()
            alert("armado: " + data);
            $.ajax({
                type: "POST", url: "${createLink(action:'creaItem',controller:'itemCatalogo')}",
                data: "&ids=" + data + "&ctlg=" + $('#catalogo').val(),
                success: function(msg) {
                    $("#ajx_item").dialog("option","title","Editar Item")
                    $("#ajx_item").html(msg).show("puff", 100)
                }
            });
            $("#ajx_item").dialog("open");
        }
    });

    $("#ajx_item").dialog({
        autoOpen: false,
        resizable:false,
        title: 'Crear un Item',
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

    //editar item

    $("#editar_dlg").dialog({
        autoOpen: false,
        resizable:false,
        title: 'Editar un Item',
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
                    type: "POST", url:  "${createLink(action:'saveItem',controller:'itemCatalogo')}",
                    data: "&id=" + $('#editar').attr("iden") + "&cata=" + $("#catalogo").val(),
                    success: function(msg) {
                        location.reload(true);
                    }
                });
            },
            "Cancelar": function() {
                $(this).dialog("close");
            }
        }
    });

    $(".editar").button().click(function() {
        %{--console.log("entro!!" + $("#editar").attr("iden") + " cata "  + $('#catalogo').val())--}%
        var $btn = $(this);
        var $tr = $btn.parents('tr');
        var $td = $tr.children('td')
        var codigo = $td[0]
        var descripcion = $td[1]
        var estado = $td[2]
        var orden = $td[3]
        var original = $td[4]
        var idFila = $("#editar").attr("iden")
        var catalogo = $("#catalogo").val()
        $("#editar_dlg").dialog("open")
    });

    $(".borrar").button().click(function () {
       console.log("entro borrar!");


    });


    function armarAccn() {
        var datos = new Array()
        $('[type=checkbox]:checked').each(
        /*$(':checkbox[checked="true"]').each(*/
                function() {
                    datos.push($(this).val());
                }
        )
        datos += "&menu=" + $('#mdlo__id').val() + "&grabar=S"
        return datos
    }




</script>