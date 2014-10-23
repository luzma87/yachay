<script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
<link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

<g:select name="asg" from="${asgs}" optionKey="id" id="asignacion_dest" optionValue='${{
    "<b>Responsable:</b> " + it.unidad + " <b>Partida:</b> " + it.presupuesto.numero + " <b>Monto:</b> " + g.formatNumber(number: it.priorizado, type: "currency")
}}' style="width: 100%" noSelection="['-1': 'Seleccione..']"></g:select>
<script>
    $("#asignacion_dest").change(function () {

    }).selectmenu({width : 600});
</script>