$(document).ready(function(){
	$('.input_control').prop('checked', false);
		$('.input_control').click(function(){
			if($('input[name='+ $(this).prop('value')+']').prop('disabled') == false){
				$('input[name='+ $(this).prop('value')+']').prop('disabled', true);
			}else{
				$('input[name='+ $(this).prop('value')+']').prop('disabled', false);
			}
			if($('select[name='+ $(this).prop('value')+']').prop('disabled') == false){
            	$('select[name='+ $(this).prop('value')+']').prop('disabled', true);
            }else{
            	$('select[name='+ $(this).prop('value')+']').prop('disabled', false);
            }
		});
});
$("div.ldapConnect").click(function()
   {
      var input = $(this).find("#ldapAuth");
      var ldapAuthOpt = input.prop("value") == "true" ? "false" : "true";
      input.prop("value", ldapAuthOpt);
      });
      var yeslabel;
      var nolabel;
       $("div.ldapConnect").children('input:checkbox').each(function () {
           yeslabel = $(this).data("yes");
           nolabel = $(this).data("no");
             $(this).iphoneStyle({
                 checkedLabel:yeslabel,
                 uncheckedLabel:nolabel});
                      $(this).change(function()
                              {
                                $(this).closest("div.ldapConnect").trigger("click");
      });
});
function resetSettings() {
    $('#ldapSettings').jzAjax({
        url: "LdapController.resetSettings()"
    }).done(function(data) {
        $("#ldapUrl").val(data.ldapUrl);
        $("#ldapType").val(data.ldapType);
        $("#ldapAdminDN").val(data.ldapAdminDN);
        $("#ldapAdminPwd").val(data.ldapAdminPwd);
        $("#ldapAuthType").val(data.ldapAuthType);
        $("#ldapUBaseDn").val(data.ldapUBaseDN);
        $("#ldapGBaseDN").val(data.ldapGBaseDN);
        disableFields();

    });
}

function disableFields(){
  $("#ldapUrl").prop('disabled',true);
  $("#ldapType").prop('disabled',true);
  $("#ldapAdminDN").prop('disabled',true);
  $("#ldapAdminPwd").prop('disabled',true);
  $("#ldapAuthType").prop('disabled',true);
  $("#ldapUBaseDN").prop('disabled',true);
  $("#ldapGBaseDN").prop('disabled',true);
  $("#usersId").prop('disabled',true);
  $("#usersFilter").prop('disabled',true);
  $("#usersAttr").prop('disabled',true);
  $("#usersClasses").prop('disabled',true);
  $("#usersSearch").prop('disabled',true);
  $("#groupsId").prop('disabled',true);
  $("#groupsFilter").prop('disabled',true);
  $("#groupsAttr").prop('disabled',true);
  $("#groupsClasses").prop('disabled',true);
  $("#groupsSearch").prop('disabled',true);
  $('.input_control').prop('checked', false);
}
function checkConnection() {
var ldapUrl = $("#ldapUrl").val();
var ldapAdminDN = $("#ldapAdminDN").val();
var ldapAdminPwd = $("#ldapAdminPwd").val();
var ldapAuthType = $("#ldapAuthType").val();

    $('#ldapSettings').jzAjax({
        url: "LdapController.checkConnection()",
        data: {"ldapUrl":ldapUrl,"ldapAdminDN":ldapAdminDN,"ldapAdminPwd":ldapAdminPwd,"ldapAuthType":ldapAuthType}
    }).done(function(data) {
    if(data.message=="ok") {
        $("#actionsuccess").html('<i class="uiIconSuccess"></i>'+data.connectionSuccessLabel);
        $("#actionsuccess").width("600px")   ;
         $("#actionsuccess").css("left",($( window ).width()/2) - ($("#actionsuccess").width()/2));
        $("#actionsuccess").show().delay(10000).fadeOut();
    }
    else{
        $("#actionfail").html('<i class="uiIconError" style="align:right"></i>'+data.connectionFailureLabel);
        $("#actionfail").width("500px") ;
        $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
        $("#actionfail").css("position","absolute");
        $("#actionfail").show().delay(10000).fadeOut();
    }


    });
}
function searchUsers() {
var ldapUBaseDN = $("#ldapUBaseDN").val();
var usersFilter = $("#usersFilter").val();
var usersSearch  = $("#usersSearch").val();
    $('#ldapSettings').jzAjax({
        url: "LdapController.searchUsers()" ,
        data: {"ldapUBaseDN":ldapUBaseDN,"usersFilter":usersFilter,"usersSearch":usersSearch}
    }).done(function(data) {

    });
}