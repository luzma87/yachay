<%--
  Created by IntelliJ IDEA.
  User: luz
  Date: 7/7/11
  Time: 5:16 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="app.Zona" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName"
               value="${message(code: 'zona.label', default: 'Zona')}"/>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'jquery.validate.min.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'additional-methods.js')}"></script>
        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/validation', file: 'messages_es.js')}"></script>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.min.js')}"></script>

        <script type="text/javascript"
                src="${resource(dir: 'js/jquery/plugins', file: 'jquery.livequery.min.js')}"></script>

        <link rel="stylesheet" href="${resource(dir: 'js/jquery/plugins/qtip', file: 'jquery.qtip.css')}"/>

        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.hotkeys.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.cookie.js')}"></script>
        <script type="text/javascript" src="${resource(dir: 'js/jquery/plugins', file: 'jquery.jstree.js')}"></script>

        <script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyBpasnhIQUsHfgCvC3qeJpEgcB9_ppWQI0&sensor=true"></script>

        <title>División Política del Ecuador</title>


        <style type="text/css">
        .div {
            width      : 424px;
            min-height : 150px;
            border     : solid 3px #768CC1;
            padding    : 5px;
            background : #EAF2FF !important;
        }

        .info {
            margin-left : 15px;
        }

        #infoCont {
            margin-top : 5px;
        }

        .label {
            padding     : 0 5px 0 15px;
            font-weight : bold;
        }

        td {
            vertical-align : middle;
        }
        </style>

    </head>

    <body>
        <div class="dialog">

            <div class="body">
                <g:if test="${flash.message}">
                    <div class="message">${flash.message}</div>
                </g:if>

                <div id="tree" class="div left ui-corner-all"
                     style="height:600px; width: 400px;overflow-y: auto;overflow-x: hidden;">

                </div>

                <div id="info" class="div info left ui-corner-all" style="width: 595px;">
                    <div id="infoTitle"></div>

                    <div id="infoCont"></div>
                </div>

                <div id="dlg_editar"></div>

            </div> <!-- body -->
        </div> <!-- dialog -->

        <script type="text/javascript">

            var icons = {
                add       : "${resource(dir: 'images/ico', file:'Add.png')}",
                edit      : "${resource(dir: 'images/ico', file:'Edit.png')}",
                remove    : "${resource(dir: 'images/ico', file:'Delete.png')}",
                pais      : "${resource(dir:'images/ico', file:'pais.png')}",
                zona      : "${resource(dir:'images/ico', file:'zona.png')}",
                provincia : "${resource(dir:'images/ico', file:'provincia.png')}",
                canton    : "${resource(dir:'images/ico', file:'canton_16.png')}",
                parroquia : "${resource(dir:'images/ico', file:'parroquia.png')}"
            };

            var lrg_icons = {
                add       : "${resource(dir: 'images/ico', file:'Add.png')}",
                edit      : "${resource(dir: 'images/ico', file:'Edit.png')}",
                remove    : "${resource(dir: 'images/ico', file:'Delete.png')}",
                pais      : "${resource(dir:'images/ico', file:'pais.png')}",
                zona      : "${resource(dir:'images/ico', file:'zona_32.png')}",
                provincia : "${resource(dir:'images/ico', file:'provincia_32.png')}",
                canton    : "${resource(dir:'images/ico', file:'canton_32.png')}",
                parroquia : "${resource(dir:'images/ico', file:'parroquia_32.png')}"
            };

            function createContextmenu(node) {

                var parent = node.parent().parent();

                var textNode = trim(node.children("a").text());
                var textParent = trim(parent.children("a").text());

                var strIdNode = node.attr("id");
                var strIdParent = parent.attr("id");

                var parts = strIdNode.split("_");
                var idNode = parts[1];
                parts = strIdParent.split("_");
                var idParent = parts[1];

                var tipoNode = node.attr("rel");
                var tipoParent = parent.attr("rel");

                var submenu;

                switch (tipoNode) {
                    case "pais":
                        submenu = {
                            "zona" : {
                                "label"  : "Zona",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "zona",
                                            "crear" : true
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear zona");
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action zona
                                "icon"   : icons.zona
                            } //zona
                        };
                        break;
                    case "zona":
                        submenu = {
                            "zona"      : {
                                "label"  : "Zona",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "zona",
                                            "crear" : true
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear zona");
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action zona
                                "icon"   : icons.zona
                            }, //zona
                            "provincia" : {
                                "label"  : "Provincia",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "provincia",
                                            "crear" : true,
                                            "padre" : idNode
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear provincia en la zona " + textNode);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action provincia
                                "icon"   : icons.provincia
                            }, //provincia
                            "canton"    : {
                                "label"  : "Cantón",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"      : "canton",
                                            "crear"     : true,
                                            "padre"     : idNode,
                                            "tipoPadre" : "zona"
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear cantón en la zona " + textNode);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action canton
                                "icon"   : icons.canton
                            } //canton
                        };
                        break;
                    case "provincia":
                        submenu = {
                            "provincia" : {
                                "label"  : "Provincia",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "provincia",
                                            "crear" : true,
                                            "padre" : idParent
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear provincia en la zona " + textParent);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action provincia
                                "icon"   : icons.provincia
                            }, //provincia
                            "canton"    : {
                                "label"  : "Cantón",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"      : "canton",
                                            "crear"     : true,
                                            "padre"     : idNode,
                                            "tipoPadre" : "provincia"
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear cantón en la provincia " + textNode);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action canton
                                "icon"   : icons.canton
                            } //canton
                        };
                        break;
                    case "canton":
                        submenu = {
                            "canton"    : {
                                "label"  : "Cantón",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"      : "canton",
                                            "crear"     : true,
                                            "padre"     : idParent,
                                            "tipoPadre" : tipoParent
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear cantón en la " + tipoParent + " " + textParent);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action canton
                                "icon"   : icons.canton
                            }, //canton
                            "parroquia" : {
                                "label"  : "Parroquia",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "parroquia",
                                            "crear" : true,
                                            "padre" : idNode
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear parroquia en el cantón " + textNode);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action parroquia
                                "icon"   : icons.parroquia
                            } //parroquia
                        };
                        break;
                    case "parroquia":
                        submenu = {
                            "parroquia" : {
                                "label"  : "Parroquia",
                                "action" : function (obj) {
                                    var url = "${createLink(controller: 'zona', action: 'editar')}";
                                    $.ajax({
                                        "type"    : "POST",
                                        "url"     : url,
                                        "data"    : {
                                            "tipo"  : "parroquia",
                                            "crear" : true,
                                            "padre" : idParent
                                        },
                                        "success" : function (msg) {
                                            $("#dlg_editar").dialog("option", "title", "Crear parroquia en el cantón " + textParent);
                                            $("#dlg_editar").html(msg);
                                            $("#dlg_editar").dialog("open");
                                        }
                                    }); //ajax
                                }, //action parroquia
                                "icon"   : icons.parroquia
                            } //parroquia
                        };
                        break;
                }

                var nuevo = {
                    "label"            : "Crear",
                    "_disabled"        : false, // clicking the item won't do a thing
                    "_class"           : "class", // class is applied to the item LI node
                    "separator_before" : false, // Insert a separator before the item
                    "separator_after"  : true, // Insert a separator after the item
                    "icon"             : icons.add,
                    "submenu"          : submenu //submenu
                };
                var items = {
                    "create" : false,
                    "remove" : false,
                    "rename" : false,
                    "ccp"    : false,

                    "nuevo" : nuevo //nuevo
                } //items

                if (tipoNode != "pais") {
                    items.editar = {
                        // The item label
                        "label"            : "Editar",
                        // The function to execute upon a click
                        "action"           : function (obj) {
                            var tipo = $(obj).attr("rel");
                            var str = $(obj).attr("id");
                            var parts = str.split("_");
                            var id = parts[1];
                            var url = "${createLink(controller: 'zona', action: 'editar')}";

                            $.ajax({
                                "type"    : "POST",
                                "url"     : url,
                                "data"    : {
                                    "tipo"      : tipo,
                                    "id"        : id,
                                    "tipoPadre" : tipoParent
                                },
                                "success" : function (msg) {
                                    $("#dlg_editar").dialog("option", "title", "Editar " + ((tipo == "canton") ? "cantón" : tipo));
                                    $("#dlg_editar").html(msg);
                                    $("#dlg_editar").dialog("open");
                                }
                            });

                        },
                        // All below are optional
                        "_disabled"        : false, // clicking the item won't do a thing
                        "_class"           : "class", // class is applied to the item LI node
                        "separator_before" : false, // Insert a separator before the item
                        "separator_after"  : false, // Insert a separator after the item
                        // false or string - if does not contain `/` - used as classname
                        "icon"             : icons.edit
                    }; //editar

                    items.eliminar = {
                        // The item label
                        "label"            : "Eliminar",
                        // The function to execute upon a click
                        "action"           : function (obj) {
                            var tipo = $(obj).attr("rel");
                            var str = "";
                            switch (tipo) {
                                case "zona":
                                    str = "Está seguro de querer eliminar esta zona?\nEsta acción no se puede deshacer...";
                                    break;
                                case "provincia":
                                    str = "Está seguro de querer eliminar esta provincia?\nEsta acción no se puede deshacer...";
                                    break;
                                case "canton":
                                    str = "Está seguro de querer eliminar este cantón?\nEsta acción no se puede deshacer...";
                                    break;
                                case "parroquia":
                                    str = "Está seguro de querer eliminar esta parroquia?\nEsta acción no se puede deshacer...";
                                    break;
                            }

                            if (confirm(str)) {
                                var str = $(obj).attr("id");
                                var parts = str.split("_");
                                var id = parts[1];

                                var url = "${createLink(action: 'deleteFromTree')}";
                                $.ajax({
                                    "type"    : "POST",
                                    "url"     : url,
                                    "data"    : {
                                        tipo : tipo,
                                        id   : id
                                    },
                                    "success" : function (msg) {
                                        if (msg == "OK") {
                                            $("#infoCont").html("");
                                            $("#infoTitle").html("");
                                            reloadTree();
                                        } else {
                                            alert(msg);
                                        }
                                    }
                                });
                            }
                        },
                        // All below are optional
                        "_disabled"        : false, // clicking the item won't do a thing
                        "_class"           : "class", // class is applied to the item LI node
                        "separator_before" : false, // Insert a separator before the item
                        "separator_after"  : false, // Insert a separator after the item
                        // false or string - if does not contain `/` - used as classname
                        "icon"             : icons.remove
                    }; //eliminar
                }
                return items;
            } //createContextmenu

            function initTree() {
                $("#tree").jstree({
                    "plugins"     : ["themes", "html_data", "ui", "hotkeys", "cookies", "types", "contextmenu", "json_data", "search"/*, "crrm", "wholerow"*/],
                    open_parents  : false,
                    "html_data"   : {
                        "data" : "<ul type='pais'><li id='pais_' class='pais jstree-closed' rel='pais'><a href='#' class='label_arbol'>División política</a></ul>",
                        "ajax" : {
                            "url"   : "${createLink(action: 'loadTreePart')}",
                            "data"  : function (n) {
                                var obj = $(n);
                                var id = obj.attr("id");
                                var parts = id.split("_");
                                var tipo = parts[0];
                                var id = 0;
                                if (parts.length > 1) {
                                    id = parts[1]
                                }
                                return {tipo : tipo, id : id}
                            },
                            success : function (data) {

                            },
                            error   : function (data) {
                                ////console.log("error");
                                ////console.log(data);
                            }
                        }
                    },
                    "types"       : {
                        "valid_children" : [ "root" ],
                        "types"          : {
                            "pais"      : {
                                "icon"           : {
                                    "image" : icons.pais
                                },
                                "valid_children" : [ "zona" ]
                            },
                            "zona"      : {
                                "icon"           : {
                                    "image" : icons.zona
                                },
                                "valid_children" : [ "provincia", "canton" ]
                            },
                            "provincia" : {
                                "icon"           : {
                                    "image" : icons.provincia
                                },
                                "valid_children" : [ "canton" ]
                            },
                            "canton"    : {
                                "icon"           : {
                                    "image" : icons.canton
                                },
                                "valid_children" : ["parroquia"]
                            },
                            "parroquia" : {
                                "icon"           : {
                                    "image" : icons.parroquia
                                },
                                "valid_children" : [""]
                            }
                        }
                    },
                    "themes"      : {
                        "theme" : "default"
                    },
                    "contextmenu" : {
                        select_node : true,
                        "items"     : createContextmenu
                    }, //contextmenu
                    "ui"          : {
                        "select_limit" : 1
                    }
                })//js tree
                        .bind("select_node.jstree", function (event, data) {
                            // `data.rslt.obj` is the jquery extended node that was clicked
//                        //console.log(data.rslt.obj);
//                        //console.log(data.rslt.obj.attr("id"));
                            var obj = data.rslt.obj;
//                            $("#tree").jstree("toggle_node", "#" + obj.attr("id"));
                            var title = obj.children("a").text();
                            var tipo = $(obj).attr("rel");
                            var str = $(obj).attr("id");
                            var parts = str.split("_");
                            var id = parts[1];

                            var img = "<img src='" + lrg_icons[tipo] + "' alt='" + tipo + "' />";
                            $("#infoTitle").html("<h1>" + img + "  " + title + "</h1>");

                            var url = "${createLink(action: 'infoForTree')}";

                            switch (tipo) {
                                case "zona":
                                    url = "${createLink(action: 'infoZona')}";
                                    break;
                                case "provincia":
                                    url = "${createLink(action: 'infoProvincia')}";
                                    break;
                                case "canton":
                                    url = "${createLink(action: 'infoCanton')}";
                                    break;
                                case "parroquia":
                                    url = "${createLink(action: 'infoParroquia')}";
                                    break;
                            }

                            updateCounter();

                            $.ajax({
                                "type"    : "POST",
                                "url"     : url,
                                "data"    : {
                                    tipo : tipo,
                                    id   : id
                                },
                                "success" : function (msg) {
                                    $("#infoCont").html(msg);
                                },
                                "error"   : function () {
                                    $("#infoCont").html("");
                                }
                            }); //ajax
                        }); //click en los nodos

                var h = $("#tree").height();
                var h2 = $("#info").height();

                var extra = 0;

                $("#info").height(Math.max(h, h2) + extra);
                $("#tree").height(Math.max(h, h2) + extra);

            } //init tree

            function reloadTree() {
                var url = "${createLink(controller: 'zona', action: 'renderArbol')}";
                $.ajax({
                    "type"    : "POST",
                    "url"     : url,
                    "success" : function (msg) {
                        $("#tree").html(msg);
                        initTree();
                    }
                });
            }

            $(function () {

                $("#dlg_editar").dialog({
                    autoOpen    : false,
                    modal       : true,
                    width       : 700,
                    buttons     : {
                        "Cancelar" : function () {
                            $("#dlg_editar").dialog("close");
                        },
                        "Guardar"  : function () {
                            var url = "${createLink(action: 'saveFromTree')}";
                            if ($(".frm_editar").valid()) {
                                var data = $(".frm_editar").serialize();
                                $.ajax({
                                    "type"    : "POST",
                                    "url"     : url,
                                    "data"    : data,
                                    "success" : function (msg) {
                                        if (msg == "OK") {
                                            reloadTree();
                                            $('.jstree-clicked').click();
                                            $("#dlg_editar").dialog("close");
                                        } else {
                                            alert("Ha ocurrido un error al guardar");
                                        }
                                    }
                                });
                            }
                        }
                    },
                    beforeClose : function () {
                        $(".ui-tooltip-rounded").hide();
                        return true;
                    }
                });

                initTree();

                $(".linkArbol").livequery(function () {
                    $(this).click(function () {
                        $("#link_" + $(this).attr("tipo")).click();
                        return false;
                    });
                });

            });
        </script>

    </body>
</html>