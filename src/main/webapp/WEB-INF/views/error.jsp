<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@include file="/WEB-INF/views/includes/navi.jsp"%>
<div class="container">
    <div class="alert alert-danger">
        <p>Sorry something is wrong. Maybe your session is time out.</p>
        <a href="<c:url value="/login"/>" class="">Click here to retry.</a>
    </div>
</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
