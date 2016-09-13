<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h4>Recent uploaded Documents</h4>
		</div>
		<div class="panel-body">
			<table class="table table-condensed table-hover"
				   style="border-collapse: collapse;">

				<thead>
				<tr>
					<th>&nbsp;</th>
					<th>File Name</th>
					<th>Project</th>
					<th>Uploaded time</th>
					<th>Uploaded by</th>

				</tr>
				</thead>

				<tbody>
				<c:forEach items="${docList}" var="doc">
					<tr>
						<td><a onclick="downloadFile(${doc.fileId});"> <button class="btn btn-default btn-md" type="button">
							<span class="glyphicon glyphicon-save"></span>
						</button></a></td>
						<td>${doc.fileName}</td>
						<td>${doc.projectName}</td>
						<td><fmt:formatDate value="${doc.uploadedTime}" pattern="MM/dd/yyyy h:mm a" /></td>
						<td>${doc.uploadedBy}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>		</div>

	</div>
	<form id="file_download_form" action="/doc/download" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<input type="hidden" id="file_download_id" name="fileId"/>
	</form>
</div>
<script>
	function downloadFile(id){
		$("#file_download_id").val(id);
		$("#file_download_form").submit();
	}
</script>


