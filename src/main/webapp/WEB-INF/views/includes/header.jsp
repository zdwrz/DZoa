<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html xmlns:c="http://java.sun.com/jsp/jstl/core"
>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css"/>
	<link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery-ui.css"/>
	 <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
	<title>XZY Engineering</title>
	<link rel="icon" href="/resources/favicon.ico"/>
</head>

<body>
<div class="container fixed-top">
	<div class="alert alert-success " style="display:none;" id="success_alert" ><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><p id="success_msg">Success</p></div>
	<div class="alert alert-danger " id="error_alert" style="display:none;"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a><p id="error_msg">Error</p></div>
</div>
<c:if test="${not empty errorMsg}">
	<script type="text/javascript">
		setErrorMsg("${errorMsg}");
	</script>
</c:if>
<c:if test="${not empty successMsg}">
	<script type="text/javascript">
		setSuccessMsg("${successMsg}");
	</script>
</c:if>

</body>