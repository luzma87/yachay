<% import grails.persistence.Event %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName"
               value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>

    <body>

        <div class="dialog" title="\${title}">


            <div class="body">
                <g:if test="\${flash.message}">
                    <div class="message ui-state-highlight ui-corner-all">\${flash.message}</div>
                </g:if>
                <div>

                    <fieldset class="ui-corner-all">
                        <legend class="ui-widget ui-widget-header ui-corner-all">
                            <g:message code="${domainClass.propertyName}.show.legend"
                                       default="${className} details" />
                        </legend>

                        <% excludedProps = Event.allEvents.toList() << 'version'
                        allowedNames = domainClass.persistentProperties*.name << 'id' << 'dateCreated' << 'lastUpdated'
                        props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
                        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                        props.each { p -> %>

                            <div class="prop">
                                <label>
                                    <g:message code="${domainClass.propertyName}.${p.name}.label"
                                               default="${p.naturalName}" />
                                </label>

                                <div class="campo">
                                    <% if (p.isEnum()) { %>
                                    \${${propertyName}?.${p.name}?.encodeAsHTML()}
                                    <% } else if (p.oneToMany || p.manyToMany) { %>
                                    <ul>
                                        <g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
                                            <li><g:link controller="${p.referencedDomainClass?.propertyName}"
                                                        action="show"
                                                        id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></li>
                                        </g:each>
                                    </ul>
                                    <% } else if (p.manyToOne || p.oneToOne) { %>
                                    <g:link controller="${p.referencedDomainClass?.propertyName}" action="show"
                                                         id="\${${propertyName}?.${p.name}?.id}">
                                        \${${propertyName}?.${p.name}?.encodeAsHTML()}
                                    </g:link>
                                    <% } else if (p.type == Boolean.class || p.type == boolean.class) { %>
                                    <g:formatBoolean boolean="\${${propertyName}?.${p.name}}" />
                                    <% } else if (p.type == Date.class || p.type == java.sql.Date.class || p.type == java.sql.Time.class || p.type == Calendar.class) { %>
                                    <g:formatDate date="\${${propertyName}?.${p.name}}" format="dd-MM-yyyy HH:mm" />
                                    <% } else if (!p.type.isArray()) { %>
                                    \${fieldValue(bean: ${propertyName}, field: "${p.name}")}
                                    <% } %>
                                </div> <!-- campo -->
                            </div> <!-- prop -->
                        <% } %>

                        <div class="buttons">
                            <g:link class="button edit" action="edit" id="\${${propertyName}?.id}">
                                <g:message code="default.button.update.label" default="Edit" />
                            </g:link>
                            <g:link class="button delete" action="delete" id="\${${propertyName}?.id}">
                                <g:message code="default.button.delete.label" default="Delete" />
                            </g:link>
                        </div>

                    </fieldset>
                </div>
            </div> <!-- body -->
            </div> <!-- dialog -->

         <script type="text/javascript">
            \$(function() {
                \$(".button").button();
                \$(".home").button("option", "icons", {primary:'ui-icon-home'});
                \$(".list").button("option", "icons", {primary:'ui-icon-clipboard'});
                \$(".create").button("option", "icons", {primary:'ui-icon-document'});

                \$(".edit").button("option", "icons", {primary:'ui-icon-pencil'});
                \$(".delete").button("option", "icons", {primary:'ui-icon-trash'}).click(function() {
                    if(confirm("\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}")) {
                        return true;
                    }
                    return false;
                });
            });
        </script>

    </body>
</html>
