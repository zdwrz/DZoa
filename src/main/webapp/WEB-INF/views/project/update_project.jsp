<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Project List</h4>
        </div>
        <div class="panel-body">
            <table class="table table-condensed"
                   style="border-collapse: collapse;">

                <thead>
                <tr>
                    <th>&nbsp;</th>
                    <th>Project. Name</th>
                    <th>Description</th>
                    <th>Contact Email</th>
                    <th>Contact number</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${projectList}" var="proj">
                    <tr data-toggle="collapse" data-target="#dept_${proj.id}"
                        class="accordion-toggle">
                        <td><button class="btn btn-default btn-xs">
                            <span class="glyphicon glyphicon-eye-open"></span>
                        </button></td>
                        <td>${proj.name}</td>
                        <td>${proj.name}</td>
                        <td>${proj.email}</td>
                        <td>${proj.email}</td>
                        <td>In progress</td>
                    </tr>
                 </c:forEach>
                <tr data-toggle="collapse" data-target="#dept_test"
                    class="accordion-toggle">
                    <td><button class="btn btn-default btn-xs">
                        <span class="glyphicon glyphicon-eye-open"></span>
                    </button></td>
                    <td>test project </td>
                    <td>test description</td>
                    <td>abc@gmail.com</td>
                    <td>123123123</td>
                    <td>In progress</td>
                </tr>
                </tbody>
            </table>
        </div>

    </div>

</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>
