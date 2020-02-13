<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file="/WEB-INF/jsp/include/scriptlib.jsp"%>
	<%@ include file="/WEB-INF/jsp/include/meta.jsp"%>

	<style type="text/css">
		.content { height: 500px; }
		.inputContent {
			margin: 20px;
			height: 100%;
		}
		.batchTable {
			width: 410px;
			border-collapse: collapse;
		}
		.batchTable th { width: 140px; }
		.batchTable td { padding: 5px; }
		.quartzTable {
			width: 650px;
			border-collapse: collapse;
			margin-top: 30px;
		}
		.quartzTable td { padding: 5px; }
		.quartzTable th { width: 140px; }
		.inputBox { width: 150px; }
		.batchTitle {
			font-size: 22px;
			font-weight: bold;
		}
	</style>
</head>
<body>
	<div class="content">
		<div class="inputContent">
			<table class="batchTable">
				<tr>
					<td colspan="3" class="batchTitle">Spring Batch 실행</td>
				</tr>
				<tr>
					<th>Batch Job Id</th>
					<td>
						<input type="text" id="batchJobId" class="inputBox" />
					</td>
					<td>
						<input type="button" id="batchRun" value="batch 실행" />
					</td>
				</tr>
			</table>
			<table class="quartzTable">
				<tr>
					<td colspan="4" class="batchTitle">Quartz 실행</td>
				</tr>
				<tr>
					<th>Quartz Job Id</th>
					<td>
						<input type="text" id="jobKey" class="inputBox" value="btch01Job_quartz" />
					</td>
					<th>Batch Job Id</th>
					<td colspan="3">
						<input type="text" id="batchId" class="inputBox" value="btch01Job" />
					</td>
				</tr>
				<tr>
					<th>Quartz 시간 설정</th>
					<td>
						<input type="date" id="startDt" />
					</td>
					<td>
						<input type="time" id="quartzTime" />
					</td>
					<td>
						<input type="button" id="quartzRun" value="quartz 실행" />
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript">

var initCtl = function() {

	$("#batchRun").on("click", function () {
		var param = {};
		param.batchJobId = $("#batchJobId").val();

		$.igframework.ajax({
			url: "/batch/batchrun"
			,data: param
			,callback : function(data) {

			}
		});
	});

	$("#quartzRun").on("click", function () {
		var time = $("#quartzTime").val();
		var param = {};
		param.jobKey = $("#jobKey").val();
		param.batchJobId = $("#batchId").val();
		param.startDt = $("#startDt").val() + ":" + time.replace(":", "");
		//param.croneType = "CUSTOM";

		$.igframework.ajax({
			url: "/batch/quartzrun"
			,data: param
			,callback : function(data) {

			}
		});
	});
};

$(document).ready(function() {
	initCtl();
});
</script>
</html>