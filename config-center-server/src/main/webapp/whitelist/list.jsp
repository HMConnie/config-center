<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="manager/whitelist/go_add_pager.html" target="dialog"><span>添加白名单</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="manager/whitelist/delete.html?id={id}" target="ajaxTodo" title="确定要删除吗?"><span>删除白名单</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr style="text-align:center;">
				<th width="20%">IP</th>
				<th width="60%">描述</th>
				<th width="20%">创建时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="whitelist" items="${model.list}">
				<tr style="text-align:center;" target="id" rel="${whitelist.id}">
					<td>${whitelist.ip}</td>
					<td>${whitelist.description}</td>
					<td><fmt:formatDate value="${whitelist.createTime}" pattern="yyyy-MM-dd"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
