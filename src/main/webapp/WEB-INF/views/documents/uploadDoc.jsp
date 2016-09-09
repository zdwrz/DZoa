<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/doc_upload.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/doc_upload.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//themes/default/style.min.css"/>
<script src="${pageContext.request.contextPath}/resources/js/jstree.min.js"></script>

<div class="row">
    <div class="col-md-1" id="button_div1">
        <button type="button" class="btn btn-primary" id="refresh_btn"><span class="glyphicon glyphicon-refresh"></span></button>
    </div>
    <div class="col-md-6" id="button_div2">
        <button type="button" style="display: none" class="btn btn-primary" id="upload_btn" data-toggle="modal" data-target="#upload_file_modal_dialog"><span class="glyphicon glyphicon-cloud-upload"></span>Upload</button>
        <button type="button" style="display: none" class="btn btn-primary" id="download_btn"><span class="glyphicon glyphicon-save"></span>Download</button>
        <button type="button" style="display: none" class="btn btn-danger" id="delete_btn"><span class="glyphicon glyphicon-trash"></span>Delete</button>
        <button type="button" style="display: none" class="btn btn-primary" id="rename_btn"><span class="glyphicon glyphicon-italic"></span>Rename</button>
    </div>

</div>
<div class="row">
    <div class="col-md-6">
        <div id="jstree">
        </div>
    </div>
</div>
<div class="modal fade" id="upload_file_modal_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                <h4 class="modal-title">Upload New Document</h4>
            </div>

            <div class="panel-body">

                <form action="<c:url value="/doc/upload"/>" class="dropzone dz-clickable" id="file_upload_form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="project_id" id="project_id"/>
                    <div class="dz-default dz-message"><span>Click or Drop files here to upload</span></div>
                </form>

                <input type="file" multiple="multiple" class="dz-hidden-input" style="visibility: hidden; position: absolute; top: 0px; left: 0px; height: 0px; width: 0px;">
                <div class="col-lg-12" style="margin-top: 20px" id="done_button_div">
                    <button class="btn btn-primary" data-dismiss="modal" id="done_button">Done</button>
                </div>
            </div>
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
                'force_text' : true,
                'check_callback' : true,
                'multiple': false,
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
            'plugins' : ['wholerow','types']
        });
        // 7 bind to events triggered on the tree
        $('#jstree').on("changed.jstree", function (e, data) {
            docTreeNodeSelected(e,data);
        });
        // 8 interact with the tree - either way is OK
//        $('button').on('click', function () {
//            $('#jstree').jstree(true).select_node('child_node_1');
//            $('#jstree').jstree('select_node', 'child_node_1');
//            $.jstree.reference('#jstree').select_node('child_node_1');
//        });
    });
    function docTreeNodeSelected(e,data){
        var selectedNode = data.instance.get_node(data.selected[0]);
        console.log(selectedNode.id   + " " + selectedNode.type);
        //
        switch(selectedNode.type){
            case 'file':
                if ( $("#download_btn").css('display') == 'none' ){
                    disableAllButtons();
                    $("#download_btn, #rename_btn, #delete_btn").fadeToggle();
                }
                break;
            case 'folder':
                if ( $("#upload_btn").css('display') == 'none' ) {
                    disableAllButtons();
                    $("#upload_btn").fadeIn();
                }
                $("#project_id").val(selectedNode.id );
                break;
            default:
                disableAllButtons();
        }
    }
    function disableAllButtons() {
        $("#button_div2 :input").hide();
    }
    $("#button_div1").on("click","#refresh_btn",function(){
        refreshTree();
    });
    $("#done_button_div").on("click","#done_button",function(){
        refreshTree();
        clearFileUploadArea();
    });
    function refreshTree(){
        var tree = $.jstree.reference("#jstree");
        // var currentNode = tree._get_node(null, false);
        tree.refresh();
    }
    function clearFileUploadArea(){
        Dropzone.forElement("#file_upload_form").removeAllFiles(true);
    }
</script>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
