<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4>Project Progress</h4>
		</div>
		<div class="panel-body">
			<table class="table table-condensed table-hover"
				style="border-collapse: collapse;">

				<thead>
					<tr>
						<th>&nbsp;</th>
						<th>Project. Name</th>
						<th>Description</th>
						<th>Start Date</th>
						<th>Location</th>
						<th>Status</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${projList}" var="proj">
					<tr data-toggle="collapse" data-target="#dept_${proj.id}"
						class="accordion-toggle">
						<td><button class="btn btn-default btn-md">
								<span class="glyphicon glyphicon-pencil"></span>
							</button></td>
						<td>${proj.name}</td>
						<td>${proj.desc}</td>
						<td><fmt:formatDate value="${proj.startDate}" pattern="MM/dd/yyyy" /></td>
						<td>${proj.locationDetail.customAddress}</td>
						<td>${proj.statusStr}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

	</div>

</div>

