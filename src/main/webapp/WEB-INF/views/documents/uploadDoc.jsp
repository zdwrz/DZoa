<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>


<div class="row">
    <div class="col-md-1" id="button_div1">
        <button type="button" class="btn btn-primary" id="refresh_btn"><span class="glyphicon glyphicon-refresh"></span></button>
    </div>
    <div class="col-md-6" id="button_div2">
        <button type="button" style="display: none" class="btn btn-primary" id="upload_btn"><span class="glyphicon glyphicon-cloud-upload"></span>Upload</button>
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
<div class="modal fade" id="upload_file_modal_dialog" data-backdrop="static" data-keyboard="false">
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
                    <input type="hidden" name="file_date" id="file_date_submit"/>
                    <div class="dz-default dz-message"><span>Click or Drop files here to upload</span></div>
                </form>
                <div class="col-md-2">
                    <label for="file_date" >File Date:</label>
                </div>
                <div class="col-md-4">
                    <input type="date" id="file_date" name="from" class="form-control" onchange="changeFileDate(this)">
                </div>

                <input type="file" multiple="multiple" class="dz-hidden-input" style="visibility: hidden; position: absolute; top: 0px; left: 0px; height: 0px; width: 0px;">
                <div class="col-lg-12" style="margin-top: 20px" id="done_button_div">
                    <button class="btn btn-primary" data-dismiss="modal" id="done_button">Done</button>
                </div>
            </div>
        </div>
    </div>
</div>
<form id="file_download_form" action="/doc/download" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="hidden" id="file_download_id" name="fileId"/>
</form>
<script src="${pageContext.request.contextPath}/resources/js/doc_upload.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/doc_upload.css" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources//themes/default/style.min.css"/>
<script src="${pageContext.request.contextPath}/resources/js/jstree.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/proj_file_upload.js"></script>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
