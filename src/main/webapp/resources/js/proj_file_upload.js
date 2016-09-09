//<![CDATA[
$(function () {
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
$("#button_div2").on("click","#upload_btn",function(){
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10) {dd='0'+dd}
    if(mm<10) {mm='0'+mm}
    today = yyyy+'-'+mm+'-'+dd;
    $("#file_date").val(today);
    $("#upload_file_modal_dialog").modal("toggle");
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
    $("#file_date").val(null);
}
function changeFileDate(input){
    //alert(input.value);
    $("#file_date_submit").val(input.value);
}
//]]>