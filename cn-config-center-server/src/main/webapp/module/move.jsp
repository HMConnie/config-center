<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="pageContent">
	<form method="post" action="manager/module/move.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<input type="hidden" value="${model.module.id}" name="id"/>
			<p style="width:100%;">
				<label>名称：</label>
				<input name="name" type="text" size="50" value="${model.module.name}" class="required"/>
			</p>
			<p style="width:100%;">
				<label>英文名：</label>
				<input name="enName" type="text" size="50" value="${model.module.enName}"  readonly="readonly" class="required"/>
			</p>
			<p style="width:100%;">
				<label>模块：</label>
				<select name="parentId" class="required">
					<option value="">请选择</option>
					<option value="-1">主模块</option>
					<c:forEach items="${model.modules}" var="module">
						<option value="${module.id}">${module.name}</option>
					</c:forEach>
				</select>
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