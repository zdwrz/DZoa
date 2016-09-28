
<style>
    .proj_name_ts{
        width:200px;
    }
    .bill_code_ts{
        width:150px;
    }
    .slot_ts{
        width:75px;
        text-align: center;
    }
    .header_table_ts{
        border-collapse: collapse;
    }
</style>
<body>
        <table border="solid" class="header_table_ts">
            <tr>

                <td class="proj_name_ts">
                    Project Name & Bill Code
                </td>
            </tr>
            <form>
                <c:forEach items="${projTsList}" var="projTs" varStatus="status">
                    <tr>
                        <td class="proj_name_ts">
                            <strong>${projTs.project.name}</strong>
                        </td>
                        <c:if test="${status.first}">
                            <c:forEach items="${dateList}" var="date" varStatus="statusOfHeader">
                                <td class="slot_ts"><fmt:formatDate value="${date.date}" pattern="MM/dd/yyyy" /> ${date.day}</td>
                                <c:if test="${statusOfHeader.first}">
                                    <input type="hidden" name="dateOfMonday" value="${date.date}"/>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <c:forEach items="${projTs.billCodeList}" var="billCodeTs">
                        <tr>
                            <td class="bill_code_ts">
                                ${billCodeTs.billCode.codeValue}
                            </td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.monday.value}" title="${billCodeTs.slots.monday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_monday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.tuesday.value}" title="${billCodeTs.slots.tuesday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_tuesday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.wednesday.value}" title="${billCodeTs.slots.wednesday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_wednesday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.thursday.value}" title="${billCodeTs.slots.thursday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_thursday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.friday.value}" title="${billCodeTs.slots.friday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_friday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.saturday.value}" title="${billCodeTs.slots.saturday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_saturday"/></td>
                            <td ><input type="text" class="slot_ts" value="${billCodeTs.slots.sunday.value}" title="${billCodeTs.slots.sunday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_sunday"/></td>
                        </tr>
                    </c:forEach>
                </c:forEach>

            </form>
        </table>
</body>
