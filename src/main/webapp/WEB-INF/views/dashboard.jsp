<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/navi.jsp"%>

<div class="modal fade" id="notification_detail_modal_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="notification_detail_modal_dialog_title">About Me</h4>
            </div>
            <div class="modal-body">
                <blockquote>
                    <p id="notification_detail_modal_dialog_content">No Detail.</p>
                </blockquote>
            </div>
        </div>
    </div>
</div>
<script type="javascript">

</script>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Notifications</h4>
            <c:forEach items="${notiList}" var="noti" varStatus="status">
              <p><c:out value="${status.count}. ${noti.title}"/> <a href="#" data-toggle="modal" data-target="#notification_detail_modal_dialog" onclick="toggleDetailDialog('${noti.title}','${noti.content}')">Detail</a></p>
            </c:forEach>
        </div>
    </div>
</div>


<%@include file="/WEB-INF/views/dashboard_content_timesheet.jsp"%>
<%@include file="/WEB-INF/views/dashboard_content_doc.jsp"%>
<%@include file="/WEB-INF/views/dashboard_content_project.jsp"%>


<%@include file="/WEB-INF/views/includes/footer.jsp"%>
