
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
            <tr>
                <td class="proj_name_ts">
                    <strong>first project here with name</strong>
                </td>
                <%--<td class="slot_ts">09/29/2016 Monday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Tuesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Wednesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Thursday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Friday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Saturday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Sunday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Monday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Tuesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Wednesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Thursday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Friday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Saturday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Sunday</td>--%>
                <c:forEach items="${dateList}" var="date">
                    <td class="slot_ts"><fmt:formatDate value="${date.date}" pattern="MM/dd/yyyy" /> ${date.day}</td>
                </c:forEach>
            </tr>


            <tr>
                <td class="bill_code_ts">
                    code number 1 - Wall
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>
            <tr>
                <td class="bill_code_ts">
                    code number 2 - chairs
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>
            <tr>
                <td class="bill_code_ts">
                    code number 3 - Desk
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>

            <tr>
                <td class="proj_name_ts">
                    <strong>Here is the second project</strong>
                </td>
                <%--<td class="slot_ts">09/29/2016 Monday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Tuesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Wednesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Thursday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Friday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Saturday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Sunday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Monday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Tuesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Wednesday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Thursday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Friday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Saturday</td>--%>
                <%--<td class="slot_ts">09/29/2016 Sunday</td>--%>

            </tr>
            <tr>
                <td class="bill_code_ts">
                    code number 1 - Wall
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>
            <tr>
                <td class="bill_code_ts">
                    code number 2 - Desk
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>
            <tr>
                <td class="proj_name_ts">
                    <strong>Total:</strong>
                </td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
                <td ><input type="text" class="slot_ts"/></td>
            </tr>
        </table>
</body>
