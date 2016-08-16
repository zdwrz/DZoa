<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/navi.jsp"%>

<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3>Manage Department</h3>
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
			 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div class="col-lg-3">
				<label for="name">Name:</label>
			    <input type="text" class="form-control" id="name" name="name" placeholder="Name" required="required"/>
			</div>
			<div class="col-lg-4">
				<label for="email">Email address:</label>
			    <input type="email" class="form-control" id="email" name="email" placeholder="Enter email"/>
			    <small class="text-muted">We'll never share your email with anyone else.</small>
			</div>
			<div class="col-lg-4">
				<label for="contact">Contact:</label>
			    <input type="text" class="form-control" id="contact" name="contact" placeholder="Contact"/>
			</div>
			<div class="col-lg-12">
				<label for="desc">Description:</label>
			    <textarea class="form-control" id="desc" name="desc" placeholder="Description"></textarea>
			</div>
			<div class="col-lg-12"  style="margin-top: 20px">
				<button type="submit" class="btn btn-primary">Add</button>
				<button type="reset" class="btn">Reset</button>
			</div>
			</form>
		</div>

	</div>

</div>

<%@include file="/WEB-INF/views/includes/footer.jsp"%>
