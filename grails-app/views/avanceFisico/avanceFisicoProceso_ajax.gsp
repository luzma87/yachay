<table border="1" width="100%" style="font-size: 10px">
    <thead>
        <tr>
            <th>Actividad</th>
            <th>Aporte</th>
            <th>Inicio</th>
            <th>Fin</th>
            <th>Completado</th>
            <th style="width:30px;"></th>
            <th style="width:30px;"></th>
            <th style="width:30px;"></th>
        </tr>
    </thead>
    <tbody>
        <g:set var="total" value="${0}"/>
        <g:set var="completado" value="${0}"/>
        <g:each in="${avances}" var="avance" status="i">
            <g:set var="total" value="${total + avance.avance}"/>
            <tr>
                <td>${avance.observaciones}</td>
                <td style="text-align: right;">
                    <g:formatNumber number="${avance.avance}" maxFractionDigits="2" minFractionDigits="2"/>%

                </td>
                <td style="text-align: center">${avance.inicio?.format("dd-MM-yyyy")}</td>
                <td style="text-align: center">${avance.fin?.format("dd-MM-yyyy")}</td>
                <g:set var="valor" value="${avance.getAvanceFisico()}"/>
                <td style="text-align: right"><g:formatNumber number="${valor}" maxFractionDigits="2" minFractionDigits="2"/>%</td>
                <td>
                    <g:set var="data" value="${avance.getColorSemaforo()}"/>
                    <div class="semaforo ${data[2]}" title="Avance esperado al ${new Date().format('dd/MM/yyyy')}: ${data[0].toDouble().round(2)}%, avance registrado: ${data[1].toDouble().round(2)}%">
                    </div>
                </td>
                <td>
                    <a href="#" class="btnCompletar" id="${avance.id}">Registrar avance</a>
                </td>
                <td>
                    <g:if test="${valor == 0}">
                        <a href="#" class="btnDelete" id="${avance.id}">Eliminar</a>
                    </g:if>
                </td>
            </tr>
        </g:each>
        <tr>
            <td style="font-weight: bold;text-align: right">
                Total  planificado:
            </td>
            <td style="font-weight: bold;text-align: right">
                <g:formatNumber number="${total}" minFractionDigits="2" maxFractionDigits="2"/>%
            </td>
            <td colspan="2" style="text-align: right;font-weight: bold">
                Total completado:
            </td>
            <td style="font-weight: bold;text-align: right">
                <g:formatNumber number="${proceso.getAvanceFisico()}" minFractionDigits="2" maxFractionDigits="2"/>%
            </td>
        </tr>
    </tbody>
</table>

<div id="dlgAvance">
    <div class="fila">
        <input type="hidden" id="maxAvance">
        <input type="hidden" id="idAvance">

        <div class="labelSvt">Avance:</div>

        <div class="fieldSvt-small">
            <g:textField name="avance" id="avanceAvance" class="ui-widget-content ui-corner-all" style="width: 50px;"/> %
        </div>

        <div class="labelSvt" style="width: 75px;">Fecha:</div>

        <div class="fieldSvt-small">
            ${new java.util.Date().format("dd/MM/yyyy")}
        </div>
    </div>
    <fieldset style="width:95%;margin-top: 15px;">
        <legend>Avances registrados</legend>

        <div id="detalleAv" style="width: 100%;height: 300px;overflow: auto"></div>
    </fieldset>

</div>
<script type="text/javascript">
    $(".btnDelete").button({
        text  : false,
        icons : {
            primary : "ui-icon-trash"
        }
    }).click(function () {
        var id = $(this).attr("id");
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'deleteAvanceFisicoProceso_ajax')}",
            data    : {
                id : id
            },
            success : function (msg) {
                updateAll(msg);
            }
        });
        return false;
    });

    $("#dlgAvance").dialog({
        autoOpen : false,
        modal    : true,
        width    : 550,
        title    : "Registrar avance",
        close    : function (event, ui) {
            $("#avanceAvance").val("");
        },
        buttons  : {
            "Guardar"  : function () {

                var avance = $.trim($("#avanceAvance").val());

                if (avance == "" || isNaN(avance) || avance * 1 < 0 || avance * 1 > 100) {
                    $.box({
                        imageClass : "box_info",
                        text       : "El avance debe ser un número positivo menor a 100",
                        title      : "Error",
                        iconClose  : false,
                        dialog     : {
                            resizable     : false,
                            draggable     : false,
                            closeOnEscape : false,
                            buttons       : {
                                "Aceptar" : function () {
                                }
                            }
                        }
                    });
                } else {
                    $.ajax({
                        type    : "POST",
                        url     : "${createLink(action:'agregarAvance')}",
                        data    : {
                            id     : $("#idAvance").val(),
                            avance : avance
                        },
                        success : function (msg) {
                            location.reload(true)

                        }
                    });
                }
                $("#dlgNuevo").dialog("close");
            },
            "Cancelar" : function () {
                $("#dlgAvance").dialog("close");
            }
        }
    });

    $(".btnCompletar").button({
        text  : false,
        icons : {
            primary : "ui-icon-check"
        }
    }).click(function () {
        $("#idAvance").val($(this).attr("id"))
        $.ajax({
            type    : "POST",
            url     : "${createLink(action:'detalleAv')}",
            data    : {
                id : $("#idAvance").val()
            },
            success : function (msg) {
                $("#detalleAv").html(msg)

            }
        });
        $("#dlgAvance").dialog("open")

        return false;
    });
</script>