/*正则变量*/
//用户名
var re_LoginId = /^[a-zA-Z]{1}([a-zA-Z0-9]|[_]){5,19}$/;
//密码
var re_LoginPwd = /^(\w){6,20}$/;
//邮箱
var re_Email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
//手机号码
var re_Phone = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
//真实姓名
var re_Name = /^[\u4e00-\u9fa5]{2,4}$/;


/*登录验证*/
//验证状态
var check_Login_State=false;
//验证用户名
function check_Login_Id(dom){
	var val= dom.value;
	if(val==''){
		dom.placeholder='请输入账号';
		dom.style.border = "1px solid #FF8FAB"; 
		check_Login_State = false;
	}else{
		dom.style.border = "1px solid #d0d0d0";
		check_Login_State = true;
	}
}
//验证密码
function check_Login_Pwd(dom){
	var val = dom.value;
	if(val==''){
		dom.placeholder='请输入密码';
		dom.style.border = "1px solid #FF8FAB"; 
		check_Login_State = false;
	}else{
		dom.style.border = "1px solid #d0d0d0"; 
		check_Login_State = true;
	}
}
//提交验证
function check_Login_Submit(){
	if(check_Login_State==false){
		$('#login_tips').html('&nbsp;<i class="fa fa-exclamation-triangle"></i>&nbsp;请检查登录信息是否正确！');
	}
	return check_Login_State;
}



/*注册验证*/
//验证状态
var check_Rg_State = false;
//协议同意状态
var check_Rg_ArticleState=false;
var check_Rg_pwd='';
//用户名
function check_Rg_LoginId(dom){
	var val= dom.value;
	if(val=='' || !re_LoginId.exec(val)){
		$('#rg_LoginId_Tips').removeClass('collapse');
		$('#rg_LoginId_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_LoginId_Tips').addClass('collapse');
		$('#rg_LoginId_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//密码
function check_Rg_LoginPwd(dom){
	var val= dom.value;
	if(val=='' || !re_LoginPwd.exec(val)){
		$('#rg_LoginPwd_Tips').removeClass('collapse');
		$('#rg_LoginPwd_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_LoginPwd_Tips').addClass('collapse');
		$('#rg_LoginPwd_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_pwd = val;
		check_Rg_State = true;
	}
}
//确认密码
function check_Rg_LoginPwd_Success(dom){
	var val= dom.value;
	if(val=='' || check_Rg_pwd=='' || val!=check_Rg_pwd){
		$('#rg_LoginPwd2_Tips').removeClass('collapse');
		$('#rg_LoginPwd2_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_LoginPwd2_Tips').addClass('collapse');
		$('#rg_LoginPwd2_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//真实姓名
function check_Rg_Name(dom){
	var val= dom.value;
	if(val!='' && !re_Name.exec(val)){
		$('#rg_Name_Tips').removeClass('collapse');
		$('#rg_Name_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_Name_Tips').addClass('collapse');
		$('#rg_Name_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//电子邮箱
function check_Rg_Email(dom){
	var val= dom.value;
	if(val=='' || !re_Email.exec(val)){
		$('#rg_Email_Tips').removeClass('collapse');
		$('#rg_Email_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_Email_Tips').addClass('collapse');
		$('#rg_Email_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//手机号码
function check_Rg_Phone(dom){
	var val= dom.value;
	if(val=='' || !re_Phone.exec(val)){
		$('#rg_Phone_Tips').removeClass('collapse');
		$('#rg_Phone_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_Phone_Tips').addClass('collapse');
		$('#rg_Phone_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//地址
function check_Rg_Address(dom){
	var val= dom.value;
	if(val==''){
		$('#rg_Address_Tips').removeClass('collapse');
		$('#rg_Address_state').html('<i class="fa fa-close" style="color:#D10034;"></i>');
		check_Rg_State = false;
	}else{
		$('#rg_Address_Tips').addClass('collapse');
		$('#rg_Address_state').html('<i class="fa fa-check" style="color:#3ed4a7;"></i>');
		check_Rg_State = true;
	}
}
//同意协议
function check_Rg_Article(dom){
	if(check_Rg_ArticleState==false)
		check_Rg_ArticleState=true;
	else
		check_Rg_ArticleState=false;
}
//提交验证
function check_Rg_Submit(){
	if(check_Rg_ArticleState==false){
		alert('您不接受我们的协议,我们将无法为您提供更好的服务');
		return false;
	}
	if(check_Rg_State==true)
		return true;
	else
		return false;
}