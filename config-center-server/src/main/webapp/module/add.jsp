<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="pageContent">
	<form method="post" action="manager/module/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p style="width:100%;">
				<label>名称：</label>
				<input name="name" type="text" size="50" class="required"/>
			</p>
			<input type="hidden" value="${model.parentId}" name="parentId"/>
			<p style="width:100%;">
				<label>英文名：</label>
				<input name="enName" type="text" size="50" class="required"/>
			</p>
			<p style="width:100%;">
				<label>描述：</label>
				<textarea cols="50" rows="8" name="description"></textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>