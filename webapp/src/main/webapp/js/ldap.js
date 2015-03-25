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
        $("#actionfail").html('<i class="uiIconError"></i>'+data.connectionFailureLabel);
        $("#actionfail").width("600px") ;
        $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
        $("#actionfail").show().delay(10000).fadeOut();
    }


    });
}
function searchUsers() {
var ldapUBaseDN = $("#ldapUBaseDN").val();
var usersFilter = $("#usersFilter").val();
var usersSearch  = $("#usersSearch").val();
var usersId= $("#usersId").val();
    $('#ldapSettings').jzAjax({
        url: "LdapController.searchUsers()" ,
        data: {"ldapUBaseDN":ldapUBaseDN,"usersFilter":usersFilter,"usersSearch":usersSearch,"usersId":usersId}
    }).done(function(data) {
     if(data.message=="ok") {
                $("#actionsuccess").html('<i class="uiIconSuccess"></i>'+data.searchResults+"users found --- Users sample:"+'</br>'+data.users);
                $("#actionsuccess").width("600px") ;
                $("#actionsuccess").css("left",($( window ).width()/2) - ($("#actionsuccess").width()/2));
                $("#actionsuccess").show().delay(10000).fadeOut();
            }
     else  {
                $("#actionfail").html('<i class="uiIconError"></i>'+"Failed fetching users:"+data.ldapUBaseDN);
                $("#actionfail").width("600px");
                $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
                $("#actionfail").show().delay(10000).fadeOut();
           }

    });
}

function searchGroups() {
var ldapGBaseDN = $("#ldapGBaseDN").val();
var groupsFilter = $("#groupsFilter").val();
var groupsSearch  = $("#groupsSearch").val();
var groupsId= $("#groupsId").val();
    $('#ldapSettings').jzAjax({
        url: "LdapController.searchGroups()" ,
        data: {"ldapGBaseDN":ldapGBaseDN,"groupsFilter":groupsFilter,"groupsSearch":groupsSearch,"groupsId":groupsId}
    }).done(function(data) {
     if(data.message=="ok") {
                $("#actionsuccess").html('<i class="uiIconSuccess"></i>'+data.searchResults+"groups found --- Groups sample:"+'</br>'+data.groups);
                $("#actionsuccess").width("600px") ;
                $("#actionsuccess").css("left",($( window ).width()/2) - ($("#actionsuccess").width()/2));
                $("#actionsuccess").show().delay(10000).fadeOut();
            }
     else  {
                $("#actionfail").html('<i class="uiIconError"></i>'+"Failed fetching groups:"+data.ldapGBaseDN);
                $("#actionfail").width("600px");
                $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
                $("#actionfail").show().delay(10000).fadeOut();
           }

    });
}
function createLdapConfig() {
var ldapType = $("#ldapType").val();
var ldapUrl = $("#ldapUrl").val();
var ldapAdminDN  = $("#ldapAdminDN").val();
var ldapAuthType= $("#ldapAuthType").val();
var ldapUBaseDN= $("#ldapUBaseDN").val();
var ldapGBaseDN=$("#ldapGBaseDN").val();
var usersId=$("#usersId").val();
var usersFilter=$("#usersFilter").val();
var usersMapping=$("#usersMapping").val();
var usersClasses=$("#usersClasses").val();
var usersSearchScope=$("#usersSearchScope").val();
var groupsId=$("#groupsId").val();
var groupsFilter=$("#groupsFilter").val();
var groupsMapping=$("#groupsMapping").val();
var groupsClasses=$("#groupsClasses").val();
var groupsSearchScope=$("#groupsSearchScope").val();
var connPool=$("#connPool").val();
var connPoolMax=$("#connPoolMax").val();
var connPoolTimeout=$("#connPoolTimeout").val();
var connPoolProtocol=$("#connPoolProtocol").val();
var ldapReadOnly=$("#ldapReadOnly").val() ;
var ldapInsensitive=$("#ldapInsensitive").val() ;
var searchLimit=$("#searchLimit").val() ;
var searchScope=$("#searchScope").val() ;

    $('#ldapSettings').jzAjax({
        url: "LdapController.createLdapConfig()" ,
        data: {"ldapType":ldapType,"ldapUrl":ldapUrl,"ldapAdminDN":ldapAdminDN,"ldapAuthType":ldapAuthType,"ldapUBaseDN":ldapUBaseDN}
    }).done(function(data) {
     if(data.message=="ok") {
                $("#actionsuccess").html('<i class="uiIconSuccess"></i>'+"LDAP parameters saved successfully");
                $("#actionsuccess").width("600px") ;
                $("#actionsuccess").css("left",($( window ).width()/2) - ($("#actionsuccess").width()/2));
                $("#actionsuccess").show().delay(10000).fadeOut();
            }
     else  {
                $("#actionfail").html('<i class="uiIconError"></i>'+"Failed to save LDAP parameters");
                $("#actionfail").width("600px");
                $("#actionfail").css("left",($( window ).width()/2) - ($("#actionfail").width()/2));
                $("#actionfail").show().delay(10000).fadeOut();
           }

    });
}
$(document).ready(function() {
    $("#accordion").accordion({
        autoHeight: false
     });
});