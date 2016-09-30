
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
        cursor: text;
    }
    .table_ts{
        border-collapse: collapse;
    }

</style>
<body>
<div id="timesheet_body">
<form id="timesheet_form" action="${pageContext.request.contextPath}/timesheet/save/${weekId}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table border="solid" class="table_ts" id="table_ts">
            <tr>
                <td class="proj_name_ts">
                    Project Name & Bill Code
                </td>
            </tr>
                <c:forEach items="${projTsList}" var="projTs" varStatus="status">
                    <tr>
                        <td class="proj_name_ts">
                            <strong>${projTs.project.name}</strong>
                        </td>
                        <c:if test="${status.first}">
                            <c:forEach items="${dateList}" var="date" varStatus="statusOfHeader">
                                <td class="slot_ts"><fmt:formatDate value="${date.date}" pattern="MM/dd/yyyy" /> ${date.day}</td>
                                <c:if test="${statusOfHeader.first}">
                                    <input type="hidden" name="dateOfMonday" value="<fmt:formatDate value="${date.date}" pattern="MM/dd/yyyy" />"/>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </tr>
                    <c:forEach items="${projTs.billCodeList}" var="billCodeTs">
                        <tr class="slot_tr_ts">
                            <td class="bill_code_ts">
                                ${billCodeTs.billCode.codeValue}
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.monday.value}" name="${projTs.project.id}_${billCodeTs.billCode.id}_monday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.monday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_monday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.tuesday.value}"  name="${projTs.project.id}_${billCodeTs.billCode.id}_tuesday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.tuesday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_tuesday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.wednesday.value}"  name="${projTs.project.id}_${billCodeTs.billCode.id}_wednesday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.wednesday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_wednesday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.thursday.value}"  name="${projTs.project.id}_${billCodeTs.billCode.id}_thursday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.thursday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_thursday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.friday.value}" name="${projTs.project.id}_${billCodeTs.billCode.id}_friday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.friday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_friday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" readonly class="slot_ts" value="${billCodeTs.slots.saturday.value}"  name="${projTs.project.id}_${billCodeTs.billCode.id}_saturday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.saturday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_saturday_comment"/>
                            </td>
                            <td class="slot_td_ts">
                                <input type="text" class="slot_ts" value="${billCodeTs.slots.sunday.value}"  name="${projTs.project.id}_${billCodeTs.billCode.id}_sunday"/>
                                <input type="hidden" class="slot_ts_comment" value="${billCodeTs.slots.sunday.comment}" name="${projTs.project.id}_${billCodeTs.billCode.id}_sunday_comment"/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            <tr>
                <td class="proj_name_ts">
                    <strong>Total hours:</strong>
                </td>
                <c:forEach items="${dateList}">
                    <td style="text-align: center;" class="total_td_ts">
                        0
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <td class="proj_name_ts">
                    <strong>Sum:</strong>
                </td>
                <td style="text-align: center;" id="hrs_sum">
                    0
                </td>
            </tr>
        </table>
        <div class="row">
            <div class="col-md-4" style="margin-top: 20px">
                <button type="submit" class="btn btn-primary">Save</button>
                <button type="button" class="btn btn-primary">Save & Submit</button>
                <button type="button" class="btn btn-default">Cancel</button>
            </div>
            <div class="col-md-4" style="margin-top: 20px">
                <a role="button" class="btn btn-info" href="<c:url value="/timesheet/ts/${weekId - 1}"/>">Prev</a>
                <a role="button" class="btn btn-info" href="<c:url value="/timesheet/ts/0"/>">Current</a>
                <a role="button" class="btn btn-info" href="<c:url value="/timesheet/ts/${weekId + 1}"/>">Next</a>
            </div>
        </div>
        </form>
</div>

    <div class="modal fade" id="slot_input_modal_dialog" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Hours and Comment</h4>
                </div>
                    <div class="modal-body">
                            <label for="ts_hours" class="control-label">Hours:</label>
                            <input type="text" class="form-control" name="ts_hours" id="ts_hours" required="true" style="width:50px">
                            <label for="ts_comment" class="control-label">Comment:</label>
                            <textarea class="form-control" rows="5" name ="ts_comment" id="ts_comment" required="true"></textarea>

                        <div class="modal-footer" id="ts_dialog">
                            <button type="submit" class="btn btn-primary" id="ts_yes_button" data-loading-text="<i class='icon-spinner icon-spin icon-large'></i> saving...">OK</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</body>
<script src="${pageContext.request.contextPath}/resources/js/timesheet.js"></script>
