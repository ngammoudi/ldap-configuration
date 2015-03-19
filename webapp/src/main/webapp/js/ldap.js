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
    });
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
        $("#actionsuccess").html("Connection successful !! " + '<i class="uiIconSuccess"></i>');
        $("#actionsuccess").width("600px")   ;
         $("#actionsuccess").css("left",($( window ).width()/2) - ($("#actionsuccess").width()/2));
        $("#actionsuccess").show().delay(10000).fadeOut();
    }
    else{
        $("#actionfail").html("Connection failed -- Please check the logs for more information !!" +'<i class="uiIconError"></i>');
        $("#actionfail").width("500px") ;
        $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
        $("#actionfail").css("position","absolute");
        $("#actionfail").show().delay(10000).fadeOut();
    }


    });
}
