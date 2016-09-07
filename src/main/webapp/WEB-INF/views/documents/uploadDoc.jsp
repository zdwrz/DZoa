<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/doc_upload.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/doc_upload.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//themes/default/style.min.css"/>
<script src="${pageContext.request.contextPath}/resources/js/jstree.min.js"></script>
<div class="row">
    <div class="col-md-4">
        <div id="jstree">
            <%--<!-- in this example the tree is populated from inline HTML -->--%>
            <%--<ul>--%>
                <%--<li>Root node 1--%>
                    <%--<ul>--%>
                        <%--<li data-jstree='{ "type" : "file" }'>Child node 1</li>--%>
                        <%--<li data-jstree='{ "type" : "file" }'>Child node 2</li>--%>
                        <%--<li>sfsdf--%>
                            <%--<ul>--%>
                                <%--<li data-jstree='{ "type" : "file" }'>Child node 1</li>--%>
                                <%--<li data-jstree='{ "type" : "file" }'>Child node 2</li>--%>
                            <%--</ul>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
            <%--</ul>--%>
        </div>
    </div>
</div>

<script>
    $(function () {
        // 6 create an instance when the DOM is ready
        $('#jstree').jstree({
            'core' : {
                'data' : {
                    'url' : function (node) {
                        return node.id === '#' ? '/projDoc'
                                : '/projDoc/doc';
                    },
                    'data' : function (node) {
                        return { 'id' : node.id };
                    }
                },
//                'data' : [
//
//                    {
//                        'text' : 'All Projects',
//                        'state' : {
//                            'opened' : true,
//                            'selected' : true
//                        },
//                        'children' : [
//                            { 'text' : 'project 1',
//                                'children' : [
//                                    {
//                                        'text' : 'project 1', 'type' : 'file'
//                                    }
//                                ],
//                             'type':'folder'
//                            },
//                            {'text' : 'project 2', 'type':'folder', 'type':'folder'},
//                            { 'text' : 'project 3' , 'type':'folder'}
//                        ]
//                        , 'type':'root'
//                    }
//                ],
                'force_text' : true,
                'check_callback' : true,
                'themes' : {
                    'responsive' : false
                }
            },
            "types" : {
                "#" : {
                    "max_children" : 1,
                    "max_depth" : 4,
                    "valid_children" : ["root"]
                },
                "root" : {
                    "valid_children" : ["folder"]
                },
                "folder" : {
                    "valid_children" : ["file"]
                },
                "file" : {
                    "icon" : "jstree-file",
                    "valid_children" : []
                }
            },
            'plugins' : ['state','dnd','contextmenu','wholerow','types']
        });
        // 7 bind to events triggered on the tree
        $('#jstree').on("changed.jstree", function (e, data) {
            console.log(data.selected);
        });
        // 8 interact with the tree - either way is OK
//        $('button').on('click', function () {
//            $('#jstree').jstree(true).select_node('child_node_1');
//            $('#jstree').jstree('select_node', 'child_node_1');
//            $.jstree.reference('#jstree').select_node('child_node_1');
//        });
    });
</script>


<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Upload New Document</h4>
        </div>
        <div class="panel-body">
            <c:if test="${success != null}">
                <div class="alert alert-success">
                    <p>${success}</p>
                </div>
            </c:if>
            <c:if test="${fail != null}">
                <div class="alert alert-danger">
                    <p>${fail}</p>
                </div>
            </c:if>

            <form action="<c:url value="/doc/upload"/>" class="dropzone dz-clickable">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="dz-default dz-message"><span>Click or Drop files here to upload</span></div>
            </form>

            <input type="file" multiple="multiple" class="dz-hidden-input" style="visibility: hidden; position: absolute; top: 0px; left: 0px; height: 0px; width: 0px;">



            <form action="<c:url value="/addDept"/>" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="col-lg-6">
                    <label for="desc">Description:</label>
                    <textarea class="form-control" id="desc" name="desc" placeholder="Description"></textarea>
                </div>
                <div class="col-lg-6">
                    <label for="type">Type:</label>
                    <select class="form-control" id="type" name="type">
                        <option value="1">type1</option>
                        <option value="2">type2</option>
                        <option value="3">type3</option>
                    </select>
                </div>
                <div class="col-lg-12" style="margin-top: 20px">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
