$( document ).ready(function() {
	
});

function toggleDetailDialog(title, content){
    $('#notification_detail_modal_dialog_content').text(content);
    $('#notification_detail_modal_dialog_title').text(title);
}

function setSuccessMsg(msg){
    $("#success_msg").text(msg);
    $("#success_alert").fadeIn().delay(3000).fadeOut();
}
function setErrorMsg(msg){
    $("#error_msg").text(msg);
    $("#error_alert").fadeIn().delay(3000).fadeOut();
}