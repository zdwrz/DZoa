<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4>Project Progress</h4>
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
				<c:forEach items="${deptList}" var="proj">
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
					<tr>
						<td colspan="12" class="hiddenRow"><div
								class="accordian-body collapse" id="dept_${proj.id}">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>First Name</th>
											<th>Last Name</th>
											<th>Email </th>
											<th>Telephone</th>
											<th>Address</th>
											<th>Status</th>
										</tr>
									</thead>
									<tbody>
									<c:if test="${empty proj.employeeList}">
											<tr>
												<td>There is no employee</td>
											</tr>
									</c:if>
									<c:if test="${!empty proj.employeeList}">
										<c:forEach items="${proj.employeeList}" var="emp">
											<tr>
												<td>${emp.firstName }</td>
												<td>${emp.lastName }</td>
												<td>${emp.lastName }</td>
												<td>${emp.lastName }</td>
												<td>${emp.lastName }</td>
												<td>${emp.age }</td>
											</tr>
										</c:forEach>
									</c:if>
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

