<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@include file="/WEB-INF/views/includes/navi.jsp" %>

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h4>Create New Project</h4>
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

            <form action="<c:url value="/addDept"/>" method="POST">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div class="col-lg-6">
                        <label for="p_name">Project Name:</label>
                        <input type="text" class="form-control" id="p_name" name="p_name" placeholder="Project Name"/>
                    </div>
                    <div class="col-lg-6">
                        <label for="p_location">Project Location:</label>
                        <input type="text" class="form-control" id="p_location" name="p_location" placeholder="Project Location"/>
                    </div>
                </div>
                <div class="row">
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
                </div>
                <div class="row">
                    <div class="col-lg-2">
                        <label for="start_date">Start Date:</label>
                        <input type="date" id="start_date" class="form-control">
                    </div>
                    <div class="col-lg-2">
                        <label for="end_date">End Date:</label>
                        <input type="date" id="end_date" class="form-control">
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12" style="margin-top: 20px">
                        <button type="submit" class="btn btn-primary">Add</button>
                        <a href="/dashboard"><button type="button" class="btn btn-primary">Cancel </button></a>
                    </div>
                </div>
            </form>
        </div>

    </div>

</div>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
