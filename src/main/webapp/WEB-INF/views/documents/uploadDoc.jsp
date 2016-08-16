<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<script src="${pageContext.request.contextPath}/resources/js/doc_upload.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/doc_upload.css" rel="stylesheet">

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
