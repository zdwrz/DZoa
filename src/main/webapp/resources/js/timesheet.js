//<![CDATA[
$(document).ready(function(){
    $(".slot_ts").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });
    $("#ts_hours").numeric({ decimal: false, negative: false }, function() { alert("Positive integers only"); this.value = ""; this.focus(); });
    var clicked_e_name;
    var clicked_e_comment_name
    $("#timesheet_body").on("click",".slot_ts",function(e){
        var field = $(e.target);
        clicked_e_name = ($(field).attr('name'));
        clicked_e_comment_name = clicked_e_name + '_comment';
        var hrs = $('[name="' + clicked_e_name + '"]').val();
        var cmnt = $('[name="' + clicked_e_comment_name + '"]').val();
        $("#ts_comment").val(cmnt);
        $("#ts_hours").val(hrs);
        $("#slot_input_modal_dialog").modal("toggle");
    });
    $('#slot_input_modal_dialog').on('shown.bs.modal', function () {
        $("#ts_hours").focus();
    })
    $("#ts_dialog").on("click","#ts_yes_button",function(){
        var newHrs = $("#ts_hours").val();
        var newCmnt = $("#ts_comment").val();
        // if(newHrs.length == 0 || newCmnt.length == 0){
        //     setErrorMsg("Both Hours and Comment are required.");
        //     return;
        // }
        //alert(newHrs + newCmnt);
        $('[name="' + clicked_e_name + '"]').val(newHrs);
        $('[name="' + clicked_e_comment_name + '"]').val(newCmnt);
        $("#slot_input_modal_dialog").modal("toggle");
        calculateTotal();
    });
    calculateTotal();
});
function calculateTotal(){
    var total = [0,0,0,0,0,0,0];
    $("#table_ts .slot_tr_ts").each(function(rowIndex){
        $(this).find(".slot_td_ts .slot_ts").each(function(colIndex) {
            total[colIndex] +=  parseInt($(this).val(),10)|| 0;
        });
    });
    var  sum = 0;
    for (var i = 0; i < total.length; i++) {
        $("#table_ts .total_td_ts").each(function(rowIndex){
            $(this).text(total[rowIndex]);
        });
        sum += parseInt(total[i])|| 0;
    }
    $("#hrs_sum").text(sum);
}
//]]>