<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">添加</h1>
	<div align="center">

		<form action="addMessage.htmls"	method="post">
			<table>
				<tr>
					<td>标题</td>
					<td colspan="2">
						<input type="text" name="title" id="title">
					</td>

				</tr>
				<tr>
					<td>内容</td>
					<td colspan="2">
						<input type="text" name="content">
					</td>
				</tr>
				<tr>
					<td>用户</td>
					<td colspan="2">
						<input type="text" name="toUsers" value="0">
					</td>
				</tr>
				<tr>
					<td>渠道</td>
					<td colspan="2">
						<input type="text" name="channel" value="0">
					</td>
				</tr>
				<tr>
					<td>推送时间</td>
					<td colspan="2">
						<input type="text" name="pushTime">
					</td>
				</tr>
				<tr>
					<td>推送类型</td>
					<td><input type="radio" name="pushType" checked value="0">普通</td>
					<td><input type="radio" name="pushType" value="1">高级</td>
				</tr>
				<tr>
					<td>表达式</td>
					<td colspan="2">
						<input type="text" name="cronExpression">
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit"></td>
				</tr>
			
			</table>
		
		</form>

	</div>
</body>
</html>