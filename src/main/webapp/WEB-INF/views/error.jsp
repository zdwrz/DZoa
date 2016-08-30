<%@ page import="org.apache.log4j.Logger" %>
<%@ page isErrorPage="true" %>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<div class="container">
    <div class="alert alert-danger">
        <h1>Opps...</h1>
        <p>Sorry, an error occurred.</p><a href="<c:url value="/login"/>" class="">Click here to retry.</a>
        <p>Here is the exception stack trace: </p>
        <%=exception%>
        <%Logger logger = Logger.getLogger("error.jsp");
            logger.error("error in jsp: " + pageContext.getErrorData().getRequestURI());
            logger.error("exception: "  + exception.getLocalizedMessage());
        %>
    </div>

</div>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
