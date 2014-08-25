<g:select from="${acts}" optionKey="id"  id="actividad" name="actividad" optionValue='${{""+it.numero+" - "+it.objeto}}' style="width:200px" noSelection="['-1':'Seleccione']"></g:select>
<script>
    $("#actividad").change(function(){
        $.ajax({
            type: "POST",
            url: "${createLink(action:'cargarAsignaciones')}",
            data: {
                id:$("#actividad").val(),
                anio:$("#anio").val()
            },
            success: function(msg) {
                $("#divAsg").html(msg)
            }
        });
    });
</script>