<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><!DOCTYPE HTML>
<html>
<head>

<title>用户注册</title>
<jsp:include page="/inc.jsp"></jsp:include>
<script type="text/javascript" src="resources/jqBootstrapValidation.js"></script></head>

<body>
	<div class="panel panel-default"
		style="width: 600px; margin: 0 auto; margin-top: 10px;">
		<div class="panel-body">

			<form method="post" action="register.action" class="form-horizontal">
				<fieldset>
					<div id="legend" class="">
						<legend class="">用户注册</legend>
					</div>






					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">用户名</label>
						<div class="col-md-10">
							<input required="required"  name="userName" placeholder="请输入用户名" class="form-control" type="text">
							<p class="help-block">用户名为3-10位的字母和数组组成</p>
						</div>
					</div>

					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">登陆密码</label>
						<div class="col-md-10">
							<input name="userPwd" placeholder="请输入登陆密码" class="form-control" type="password">
							<p class="help-block">密码为3-10位的字符组成</p>
						</div>
					</div>

					<div class="control-group">

						<!-- Search input-->
						<label class="control-label col-md-2">密码确认</label>
						<div class="col-md-10">
							<input name="userPwdConfirm" placeholder="请再次输入密码" class="form-control" type="password">
							<p class="help-block">请再次输入登陆密码</p>
						</div>

					</div>



					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">真实姓名</label>
						<div class="col-md-10">
							<input name="userRealName" placeholder="请输入真实姓名" class="form-control" type="text">
							<p class="help-block">真实姓名为2-6个中文字符组成</p>
						</div>
					</div>



					<div class="control-group">
						<label class="control-label col-md-2">性别</label>
						<div class="col-md-10">
							<!-- Inline Radios -->
							<label class="radio-inline"> <input checked="checked"
								value="true" name="userSex" type="radio"> 男 </label> <label
								class="radio-inline"> <input value="false"
								name="userSex" type="radio"> 女 </label>
						</div>
					</div>

					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">生日</label>
						<div class="col-md-10">
							<input placeholder="请输入生日" class="form-control"
								style="width:120px;" type="text">
							<p class="help-block">生日格式:“yyyy-MM-dd”</p>
						</div>
					</div>

					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">邮箱</label>
						<div class="col-md-10">
							<input placeholder="userEmail" class="form-control" type="text">
							<p class="help-block">请输入常用邮箱</p>
						</div>
					</div>

					<div class="control-group">

						<!-- Text input-->
						<label class="control-label col-md-2">头像</label>
						<div class="col-md-10">
							<input type="file">
							<p class="help-block">请选择一张100px*100px的jpg图片</p>
						</div>
					</div>

					
					
					<div class="control-group">
						<label class="control-label col-md-2">手机号码</label>
						<div class="col-md-5">
							<input class="form-control"
								placeholder="请输入手机号码" type="text"> 
							
							<p class="help-block">请填写真实号码</p>
							
						</div>
						<div class="col-md-5"><a class="btn btn-default" href="">发送验证短信</a></div>

					</div>

					<div class="control-group" style="clear: both;">

						<!-- Text input-->
						<label class="control-label col-md-2">验证码</label>
						<div class="col-md-10">
							<input placeholder="请输入手机验证码" class="form-control" type="text">
							<p class="help-block">请输入6位手机验证码</p>
						</div>
					</div>



					<div class="control-group">
						<div class="col-md-5 col-md-offset-2">
							<button type="submit" class="btn btn-success">注册</button>
							<a class="btn btn-primary" href="login.jsp">返回登陆</a>
						</div>
					</div>

				</fieldset>
			</form>

		</div>
	</div>
<script type="text/javascript">
(function($){
	$(function(){
		var form = $("form");
		form.submit(function(){
			if(form.find(".has-error").size()>0){
				alert(form.find(".has-error:first").find("label:first").text()+"输入不正确");
				form.find(".has-error:first").find(":input").focus();
				return false;
			}else{
				return true;
			}
			
		});
		
		form.find("[name='userName']").on("keyup",function(){
			var the = $(this);
			if(/^[A-Za-z]\w{2,9}$/.test(the.val())){
				the.parents(".control-group:first").removeClass("has-error").addClass("has-success");
			}else{
				the.parents(".control-group:first").addClass("has-error");
			}
		});
		
		
		form.find("[name='userPwd']").on("keyup",function(){
			var the = $(this);
			if(/^\w{3,10}$/.test(the.val())){
				the.parents(".control-group:first").removeClass("has-error").addClass("has-success");
			}else{
				the.parents(".control-group:first").addClass("has-error");
			}
		});
		
		
		form.find("[name='userPwdConfirm'],[name='userPwd']").on("keyup",function(){
			var the = form.find("[name='userPwdConfirm']");
			if(the.val()==form.find("[name='userPwd']").val()){
				the.parents(".control-group:first").removeClass("has-error").addClass("has-success");
			}else{
				the.parents(".control-group:first").addClass("has-error");
			}
		});
		
		form.find("[name='userRealName']").on("keyup",function(){
			var the = $(this);
			if(/^[\u4e00-\u9fa5]{2,6}$/.test(the.val())){
				the.parents(".control-group:first").removeClass("has-error").addClass("has-success");
			}else{
				the.parents(".control-group:first").addClass("has-error");
			}
		});
		
		
	});
})(jQuery);
</script>
</body>
</html>