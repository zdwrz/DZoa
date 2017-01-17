Submit History:
<c:forEach items="${histList}" var="hist" varStatus="status">
    <div>
        ${hist.status.value} on ${hist.statusDate} - submitted by ${hist.submitter.userDetails.firstName} - reviewer is ${hist.approver.userDetails.firstName}
    </div>
</c:forEach>