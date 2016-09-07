<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//themes/default/style.min.css"/>
<script src="${pageContext.request.contextPath}/resources/js/jstree.min.js"></script>

<div id="jstree">
    <!-- in this example the tree is populated from inline HTML -->
    <ul>
        <li>Root node 1
            <ul>
                <li data-jstree='{ "type" : "file" }'>Child node 1</li>
                <li data-jstree='{ "type" : "file" }'>Child node 2</li>
                <li>sfsdf
                    <ul>
                        <li data-jstree='{ "type" : "file" }'>Child node 1</li>
                        <li data-jstree='{ "type" : "file" }'>Child node 2</li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</div>


<script>
    $(function () {
        // 6 create an instance when the DOM is ready
        $('#jstree').jstree({
            'core' : {
//                'data' : {
//                    'url' : '?operation=get_node',
//                    'data' : function (node) {
//                        return { 'id' : node.id };
//                    }
//                },
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
                    "icon" : "/static/3.3.2/assets/images/tree_icon.png",
                    "valid_children" : ["default"]
                },
                "default" : {
                    "valid_children" : ["file"]
                },
                "file" : {
                    "icon" : "glyphicon glyphicon-file",
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
        $('button').on('click', function () {
            $('#jstree').jstree(true).select_node('child_node_1');
            $('#jstree').jstree('select_node', 'child_node_1');
            $.jstree.reference('#jstree').select_node('child_node_1');
        });
    });
</script>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
