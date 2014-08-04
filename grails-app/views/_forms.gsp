
<style type="text/css">
html>body {
    font-size  : 11px;
    font-family: Arial;

}

body {
    /*#font-size : 68%;*/
}

.botones {
    height : 30px;
}

.left {
    float : left;
}

.right {
    float : right;
}



.fg-toolbar {
    padding : 5px;
}

.list {
    margin-top : 10px;
}

.list table {
    border-collapse : collapse;
    font-size       : 12px;
}

.list th {
    font-size   : 13px;
    font-weight : bold;
    line-height : 17px;
    /*padding     : 2px 6px;*/
    cursor      : pointer;
    padding     : 5px 10px 5px 10px;
}

.list td {
    padding : 5px;
}

.list th a:link, .list th a:visited, .list th a:hover {
    display         : block;
    font-size       : 13px;
    text-decoration : none;
    /*width           : 100%;*/
}

.list th.asc a, .list th.desc a {
    background-position : right;
    background-repeat   : no-repeat;
    padding-right       : 10px;
}

.list th.asc a {
    background-image : url(${resource(dir:"images/skin" ,file: "skin/sorted_asc.gif")});
    /*color            : red !important;*/
}

.list th.desc a {
    background-image :url(${resource(dir:"images/skin" ,file: "skin/sorted_desc.gif")});
    /*color            : yellow !important;*/
}

.odd {
    background : #C0DEED;
}

.even {
    background : #f5f5f5;
}

td {
    padding-left  : 5px;
    padding-right : 5px;
}

tbody tr:nth-child(even) {
    background : #f5f5f5;
}



tbody tr:nth-child(odd) {
<g:if test="${session.color=='cafe'}">
    background : #fff;
</g:if>
<g:else>
    background : #D3E8F2;
</g:else>
}


tfoot tr {
    background : none !important;
}


.paginateButtons {
    /*background : #fff url(../images/skin/shadow.jpg) bottom repeat-x;*/
    border     : 1px solid #ccc;
    border-top : 0;
    color      : #666;
    /*font-size  : 10px;*/
    overflow   : hidden;
    padding    : 10px 3px;
}

.paginateButtons a {
    /*background   : #fff;*/
    /*border       : 1px solid #ccc;*/
    /*border-color : #ccc #aaa #aaa #ccc;*/
    color   : #666;
    margin  : 0 3px;
    padding : 2px 6px;
}

.paginateButtons span {
    padding : 2px 3px;
}



.message, .errors {
    padding : 5px;
    margin  : 5px;
}

.body {
    float  : left;
    margin : 0 15px 10px 15px;
}

.formTitle {
    padding : 3px;
    width   : 100%;
}


fieldset {
    border  : 1px solid #ccc;
    margin  : 1em 0;
    padding : .5em 1em;
}

legend {
    color       : #006dba;
    font-weight : normal;
    font-size   : 120%;
    padding     : 0.25em !important;
    margin-top  : 0.125em !important;
}

.toolbar {
    padding : 4px;
}



.blanco {
    /*background:  #b3c0d4;*/
    background : white;
}

fieldset .prop {
    clear   : both;
    margin  : 1em 0;
    padding : 0;
}

.label, .indicator {
    /*display      : inline-block;*/
    /*position     : relative;*/
    text-align    : right;
    /*width        : 8em;*/
    /*background: #dae0e7;*/
    background    : white;
    font-weight   : bold;
    width         : 10em;
    /*padding-right : 1.5em;*/
}

.etiqueta {
    text-align    : right;
    background    : white;
    font-weight   : bold;
    width         : 15em;
}


.labelshow {
    text-align    : right;
    background    : white;
    font-weight   : bold;
    width         : 15em;
}


label {
    display      : inline-block;
    position     : relative;
    text-align   : right;
    width        : 8em;
    margin-right : 0.5em;
    background   : #dae0e7;
}

.indicator {
    width   : 10px;
    padding : 3px;
    margin  : 0;
}

.mandatory .indicator {
    color : #cc0000;
    /*position : absolute;*/
    /*right    : -1.25em;*/
    /*float : right;*/
    /*margin-right : 1.5em;*/
}

.field {
    width : 200px;
}

select.field {
    width : 220px;
}

.datepicker {
    background    : url(${resource(dir:"images/img" ,file: "calendar.png")}) right no-repeat !important;
    padding-right : 17px;
    width         : 233px;
    cursor        : pointer;

}

select.hourMin {
    width : 55px;
}

.campo {
    display : inline;
}


fieldset input, fieldset select, fieldset textarea {
    margin : 0;
}

fieldset input, fieldset select {
    vertical-align : middle;
}


fieldset input[type='text'], fieldset select, fieldset textarea {
    background-color : #fcfcfc;
    border           : 1px solid #ccc;
    padding          : 2px 4px;
}

fieldset input[type='text']:focus, fieldset select:focus, fieldset textarea:focus {
    border : 1px solid #b2d1ff;
}

select {
    padding : 2px 2px 2px 0;
}

textarea {
    vertical-align : top;
}


em.error {
    /*color   : #cc0000;*/
    /*clear   : both;*/
    /*display : block;*/
}

input[type='text'].error, select.error, textarea.error {
    background : #fff3f3;
    border     : 1px solid #cc0000;
}


input.error {
    *background : #fff3f3;
    *border     : 1px solid #cc0000;
}


.ui-tabs-vertical {
    width : 55em;
}

.ui-tabs-vertical .ui-tabs-nav {
    padding : .2em .1em .2em .2em;
    float   : left;
    width   : 12em;
}

.ui-tabs-vertical .ui-tabs-nav li {
    clear               : left;
    width               : 100%;
    border-bottom-width : 1px !important;
    border-right-width  : 0 !important;
    margin              : 0 -1px .2em 0;
}

.ui-tabs-vertical .ui-tabs-nav li a {
    display : block;
}

.ui-tabs-vertical .ui-tabs-nav li.ui-tabs-selected {
    padding-bottom     : 0;
    padding-right      : .1em;
    border-right-width : 1px;
    border-right-width : 1px;
}

.ui-tabs-vertical .ui-tabs-panel {
    padding : 1em;
    float   : right;
    width   : 40em;
}


.tabs-bottom {
    position : relative;
}

.tabs-bottom .ui-tabs-panel {
    height   : 140px;
    overflow : auto;
}

.tabs-bottom .ui-tabs-nav {
    position : absolute !important;
    left     : 0;
    bottom   : 0;
    right    : 0;
    padding  : 0 0.2em 0.2em 0;
}

.tabs-bottom .ui-tabs-nav li {
    margin-top          : -2px !important;
    margin-bottom       : 1px !important;
    border-top          : none;
    border-bottom-width : 1px;
}

.ui-tabs-selected {
    margin-top : -3px !important;
}
</style>