<%--
  Created by IntelliJ IDEA.
  User: gato
  Date: 04/09/14
  Time: 11:50 AM
--%>

%{--<%@ page contentType="text/html;charset=UTF-8" %>--}%
%{--<html>--}%
%{--<head>--}%
    %{--<title></title>--}%
%{--</head>--}%

%{--<body>--}%

%{--</body>--}%
%{--</html>--}%

<g:if test="${camp == 'Componente'}">
    &nbsp;&nbsp;&nbsp; <b>Componente </b><g:select from="${componentes.unique()}" name="comp" noSelection="['-1':'Seleccione']" />
</g:if>
<g:else>
    &nbsp;&nbsp;&nbsp; <b>Responsable </b><g:select from="${responsables.unique()}" name="resp" noSelection="['-1':'Seleccione']"/>
</g:else>

<script type="text/javascript">

    $("#resp").change(function () {
        if($("#resp").val()){
            location.href = "${createLink(controller:'asignacion',action:'asignacionProyectov2')}?id=${proyecto.id}&anio=" + ${anio} +"&resp=" + $(this).val()
        }

    });


    $("#comp").change(function () {
            if($("#comp").val()){
                location.href = "${createLink(controller:'asignacion',action:'asignacionProyectov2')}?id=${proyecto.id}&anio=" + ${anio} +"&comp=" + $(this).val()
            }

    })


</script>



