<table style="width: 100%">
    <thead>
    <tr>
        <th>Componente</th>
        <th>Actividad</th>
        <th>Partida</th>
        <th>Priorizado</th>
        <th>Monto</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <g:set var="total" value="${0}"></g:set>
    <g:each in="${detalle}" var="asg">
        <g:set var="total" value="${total.toDouble()+asg.monto}"></g:set>
        <tr>
            <td>${asg.asignacion.marcoLogico.marcoLogico}</td>
            <td>${asg.asignacion.marcoLogico.numero} - ${asg.asignacion.marcoLogico}</td>
            <td>${asg.asignacion.presupuesto.numero}</td>
            <td style="text-align: right">${asg.asignacion.priorizado}</td>
            <td style="text-align: right" id="monto_${asg.id}">${asg.monto}</td>
            <td style="text-align: center"><a href="#" class="edit" iden="${asg.id}" asg="${asg.asignacion.id}">Editar</a></td>
            <td style="text-align: center"><a href="#" class="borrar" iden="${asg.id}" >Borrar</a></td>
        </tr>
    </g:each>
    <tr>
        <td colspan="4" style="font-weight: bold">TOTAL PROCESO</td>
        <td style="font-weight: bold;text-align: right">${total}</td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
</table>
<script>
    $(".edit").button({icons:{ primary:"ui-icon-pencil"},text:false}).click(function(){
        vaciar()
        $("#dlgMonto").val($("#monto_"+$(this).attr("iden")).html())
        $("#dlgId").val($(this).attr("iden"))
        getMaximo($(this).attr("asg"))
        $("#dlgEditar").dialog("open")
    });
    $(".borrar").button({icons:{ primary:"ui-icon-trash"},text:false}).click(function(){
        if(confirm("Esta seguro?")){
            $.ajax({
                type: "POST",
                url: "${createLink(action:'borrarDetalle')}",
                data: {
                    id : $(this).attr("iden")
                },
                success: function(msg) {
                    if(msg=="ok") {
                        getDetalle()
                        vaciar()
                    }else{
                        $.box({
                            title:"Error",
                            text:"No se puede borrar porque el proceso tiene un aval o una solicitud de aval pendiente",
                            dialog: {
                                resizable: false,
                                buttons  : {
                                    "Cerrar":function(){

                                    }
                                }
                            }
                        });
                    }
                }
            });
        }
    });
</script>