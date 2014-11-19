<%--
  Created by IntelliJ IDEA.
  User: gato
  Date: 18/11/14
  Time: 12:14 PM
--%>

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

<g:form >
    <table>
        <tbody>
        <tr class="prop">
            <td valign="top" class="name">
                <label for="codigo"><g:message default="Código" /></label>
            </td>
            <td valign="top" class="value ${hasErrors(bean: item, field: 'codigo', 'errors')}">
                <g:textField  name="codigo" id="codigo" title="Código del Item" class="field required ui-widget-content ui-corner-all" minLenght="1" maxLenght="63" value="${item?.codigo}" />
            </td>
        </tr>
       </tbody>
    </table>
</g:form>
