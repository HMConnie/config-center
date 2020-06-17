<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h2 class="contentTitle">${model.moduleName}(${model.moduleEnName})</h2>

<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manager/configration/go_add_pager.html?moduleId=${model.id}" target="dialog"><span>添加配置</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="manager/configration/delete.html?id={id}" target="ajaxTodo" title="确定要删除吗?"><span>永久删除配置</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="manager/configration/go_modify_pager.html?id={id}" target="dialog"><span>修改配置</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="manager/configration/go_move_pager.html?id={id}" target="dialog"><span>移动配置</span></a></li>
			<li class="line">line</li>
			<li><a class="add" href="manager/module/go_add_pager.html?parentId=${model.id}" target="dialog"><span>添加子模块</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="manager/module/go_move_pager.html?id=${model.id}" target="dialog"><span>移动模块</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="manager/module/go_modify_pager.html?id=${model.id}" target="dialog"><span>修改模块</span></a></li>
			<li class="line">line</li>
			<li><a class="add" href="manager/module/consumers.html?moduleId=${model.id}" target="dialog"><span>查看消费者</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="60">
		<thead>
			<tr style="text-align:center;">
				<th width="30%">KEY</th>
				<th width="40%">VALUE</th>
				<th width="30%">描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${model.config}" var="config">
				<tr target="id" rel="${config.id}" style="text-align:center;">
					<td>${config.key}</td>
					<td>${config.value}</td>
					<td>${config.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>


<script>
$(document).ready(function(){
	resetJBSXModuleId("${model.id}");
});
</script>