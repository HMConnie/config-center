<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="pageContent">
	<table class="list" width="100%" layoutH="30">
		<thead>
			<tr style="text-align:center;">
				<th width="25%">应用名</th>
				<th width="25%">主机名</th>
				<th width="25%">PID</th>
				<th width="25%">版本是否一致</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach var="consumer" items="${model.consumers}">
				<tr style="text-align:center;">
					<td>${consumer.application}</td>
					<td>${consumer.host}</td>
					<td>${consumer.pid}</td>
					<c:if test="${consumer.configVersion == model.configVersion}">
						<td style="background-color: #15af00;">一致</td>
					</c:if>
					<c:if test="${consumer.configVersion != model.configVersion}">
						<td style="background-color: #ff0000;">不一致</td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
