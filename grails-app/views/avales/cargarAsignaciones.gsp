
<script type="text/javascript" src="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.js')}"></script>
<link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/select', file: 'jquery.ui.selectmenu.css')}"/>

<g:select name="asg" from="${asgs}" optionKey="id" id="asignacion"  optionValue='${{"Responsable: "+it.unidad+" Partida:"+it.presupuesto.numero+" Monto:"+it.priorizado}}' style="width: 100%" noSelection="['-1':'Seleccione..']"></g:select>
<script>
    $("#asignacion").change(function(){
       getMaximo($("#asignacion").val())
    }).selectmenu({width : 600});
</script>