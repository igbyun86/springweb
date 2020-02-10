<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/jsp/include/scriptlib.jsp"%>
	<%@ include file="/WEB-INF/jsp/include/meta.jsp"%>
</head>
<body>
	<input type="button" id="excelDown" value="Excel 다운" />
</body>
<script type="text/javascript">

var initCtl = function() {
	$("#excelDown").on("click", function () {
		var headerColumns = ["COL1", "COL2", "COL3", "COL4", "COL5", "COL6", "COL7", "COL8", "COL9", "COL10"];

		$.igframework.EXCELDOWN({
			header : headerColumns.join("|"),
			url : "/excel/exceldown",
			fileName : "대용량excel_down",
			sheetName : "excel data"

		});
	});
};

$(document).ready(function() {
	initCtl();

});
</script>
</html>