<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>
<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Notifications</h4>
        </div>
        <div class="container-fluid" style="margin-top: 10px">
            <div class="row">
                <div class="col-md-4">
                    <button type="button" class="btn btn-primary">Add</button>
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
                            <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#delete_notification_modal_dialog">
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
                <input id="notification_to_remove_id" type="hidden"/>
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
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
