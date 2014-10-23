
<script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
<link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

<g:select from="${acts}" optionKey="id"  id="actividad" name="actividad" optionValue='${{""+it.numero+" - "+it.objeto}}' style="width:100%" noSelection="['-1':'Seleccione']"></g:select>
<script>
    $("#actividad").change(function(){
        $.ajax({
            type: "POST",
            url: "${createLink(action:'cargarAsignaciones',controller: 'avales')}",
            data: {
                id:$("#actividad").val(),
                anio:$("#anio").val()
            },
            success: function(msg) {

                $("#divAsg").html(msg)
            }
        });
    }).selectmenu({width : 600});
</script>