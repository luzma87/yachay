<g:select name="asg" from="${asgs}" optionKey="id" id="asignacion"  optionValue='${{"Responsable: "+it.unidad+" Partida:"+it.presupuesto.numero+" Monto:"+it.priorizado}}' style="width: 100%" noSelection="['-1':'Seleccione..']"></g:select>
<script>
    $("#asignacion").change(function(){
       getMaximo($("#asignacion").val())
    });
</script>