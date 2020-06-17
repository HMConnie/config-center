<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<form id="pagerForm" method="post" action="manager/module/list.html">
	<input type="hidden" value="${model.moduleId}" name="moduleId" id="jbsxModuleId"/>
</form>


<div class="pageContent" >
	<div class="tabs" >
		<div class="tabsContent">
			<div>
				<div  layoutH="10" style="float:left; display:block; overflow:auto; width:240px; height:100%; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
				      ${model.treeHtml}
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
<script>
$(document).ready(function(){
	var moduleId=$("#jbsxModuleId").val();
	$("#jbsxBox").loadUrl("manager/module/detail.html",{"id": moduleId},	{});
});

function resetJBSXModuleId(moduleId){
	$("#jbsxModuleId").val(moduleId);
}
</script>