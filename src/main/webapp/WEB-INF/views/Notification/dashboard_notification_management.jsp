<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<script>
    function setNotificationToDelete(id) {
        $("#notification_to_remove_id").val(id);
    }
</script>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Notifications</h4>
        </div>
        <div class="container-fluid" style="margin-top: 10px">
            <div class="row">
                <div class="col-md-4">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#new_or_update_notification_modal_dialog">Add</button>
                </div>
            </div>
        </div>


        <div class="panel-body">
            <table class="table table-condensed"
                   style="border-collapse: collapse;">

                <thead>
                <tr>
                    <th>&nbsp;</th>
                    <th>Title</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Type</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${fullNotificationList}" var="notification">
                    <tr
                        class="accordion-toggle">
                        <td>
                            <button class="btn btn-default btn-xs" data-toggle="collapse" data-target="#notification_${notification.id}">
                                <span class="glyphicon glyphicon-eye-open"></span>
                            </button>
                            <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#delete_notification_modal_dialog" onclick="setNotificationToDelete(${notification.id})">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                            <button class="btn btn-default btn-xs" >
                                <span class="glyphicon glyphicon-pencil"></span>
                            </button>
                        </td>
                        <td>${notification.title}</td>
                        <td>${notification.startDate}</td>
                        <td>${notification.expirationDate}</td>
                        <td>${notification.type.value}</td>
                    </tr>
                    <tr>
                        <td colspan="12" class="hiddenRow"><div class="accordian-body collapse" id="notification_${notification.id}">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Detail</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${notification.content}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>

</div>

<div class="modal fade" id="delete_notification_modal_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Delete</h4>
            </div>
            <form id="delete_form" class="form-horizontal"  method="post" action="/notification/remove">
                <input id="notification_to_remove_id" name="notification_to_remove_id" type="hidden"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                <div class="modal-body">
                    <fieldset>
                        <p class="col-lg-9 ">Are you sure you want to delete this Notification?</p>
                    </fieldset>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                    <button type="submit" class="btn btn-danger" id="delete_yes_button" data-loading-text="<i class='icon-spinner icon-spin icon-large'></i> Deleting...">Yes</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div class="modal fade" id="new_or_update_notification_modal_dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Create a New Notification</h4>
            </div>
            <form id="create_new_form" class="form-horizontal" action="/notification/createOrUpdate" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <div class="modal-body">
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">Title</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="title" placeholder="Title" required="true">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="content" class="col-md-2 control-label">Content</label>
                            <div class="col-md-10">
                                <textarea class="form-control" rows="10" id="content" required="true"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="category" class="col-md-2 control-label">Type</label>
                            <div class="col-md-10">
                                <select class="form-control" id="category">
                                    <c:forEach items="${notificationTypeList}" var="type">
                                        <option value="${type.id}">${type.value}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary" id="save_new_button" data-loading-text="<i class='icon-spinner icon-spin icon-large'></i> Saving...">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
