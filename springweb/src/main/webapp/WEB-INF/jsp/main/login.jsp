<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body onload="loginCheck();">
	<form action='/user/login' method="post">
		<table>
			<tr>
				<th>ID : </th>
				<td>
					<input type="text" name="user_id" id="user_id" value="${user_id}"/>
				</td>
			</tr>
			<tr>
				<th>PASSWORD : </th>
				<td>
					<input type="password" name="password" id="password"/>
				</td>
				<td rowspan="2">
					<input type="submit" value="·Î±×ÀÎ">
				</td>
			</tr>
		</table>
		<input type="hidden" name='_csrf' value="${_csrf.token}"></input>
	</form>
</body>
<script type="text/javascript">
function loginCheck() {

	var errMsg = "${errMsg}";
	if(errMsg != "" && errMsg != null) {
		alert(errMsg);
	}
}


</script>
</html>