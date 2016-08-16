<div class="navbar-wrapper">
	<div class="container-fluid">
		<nav class="navbar navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">XYZ Civil Engineering</a>
				</div>
					<ul class="nav navbar-nav">
						<li ><a href="<c:url value="/dashboard"/>" class="">Dashboard</a></li>

						<li class=" dropdown"><a href="#" class="dropdown-toggle "
												 data-toggle="dropdown" role="button" aria-haspopup="true"
												 aria-expanded="false">Project <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/project/create"/>">Create New Project</a></li>
								<li><a href="<c:url value="/project/manage"/>">View/Update Projects</a></li>
								<li><a href="<c:url value="/doc/create"/>">Upload New Documents</a></li>
								<li><a href="<c:url value="/doc/manage"/>">Find Documents</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle "
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false">Time Sheet <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/timesheet/new"/>">My Time Sheet</a></li>
								<li><a href="<c:url value="/timesheet/manage"/>">Manage Time Sheet</a></li>
							</ul></li>

						<li class=" dropdown"><a href="#" class="dropdown-toggle "
												 data-toggle="dropdown" role="button" aria-haspopup="true"
												 aria-expanded="false">Report Management <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/report/new"/>">Create Report</a></li>
								<li><a href="<c:url value="/report/submit"/>">Submit Report</a></li>
								<li><a href="<c:url value="/report/manage"/>">Manage Report</a></li>
							</ul></li>
						<li class=" dropdown"><a href="#" class="dropdown-toggle "
												 data-toggle="dropdown" role="button" aria-haspopup="true"
												 aria-expanded="false">Other <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/dashboard"/>">Inspector Check In</a></li>
								<li><a href="<c:url value="/location/view"/>">Project Map</a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav pull-right">
						<li class="dropdown"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"
							role="button" aria-haspopup="true" aria-expanded="false">Signed
								in as <sec:authentication property="principal.detailInfo.firstName"/> <span class="caret"></span>
						</a>
							<ul class="dropdown-menu">
								<li><a href="<c:url value="/changepwd"/>">Change Password</a></li>
								<li><a href="<c:url value="/profile"/>">My Profile</a></li>
							</ul></li>
						<li class=""><a href="<c:url value="/logout"/>">Logout</a></li>
					</ul>
			</div>
		</nav>
	</div>
</div>

