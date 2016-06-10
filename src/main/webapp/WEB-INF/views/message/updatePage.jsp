<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">更新</h1>
	<div align="center">

		<form action="updateMessage.htmls" method="post">
			<table>
				<tr>
					<td>id</td>
					<td colspan="2"><input type="text" name="id"
						readonly="readonly" value="${message.id }"></td>

				</tr>
				<tr>
					<td>标题</td>
					<td colspan="2"><input type="text" name="title"
						value="${message.title }" id="title"></td>

				</tr>
				<tr>
					<td>内容</td>
					<td colspan="2"><input type="text" value="${message.content }"
						name="content"></td>
				</tr>
				<tr>
					<td>用户</td>
					<td colspan="2"><input type="text" name="toUsers"
						value="${message.toUsers }"></td>
				</tr>
				<tr>
					<td>渠道</td>
					<td colspan="2"><input type="text" name="channel"
						value="${message.channel }"></td>
				</tr>
				<tr>
					<td>推送时间</td>
					<td colspan="2"><input type="text"
						<c:if test="${message.pushType==0 }"> value="${message.pushTime }"</c:if>
						name="pushTime"></td>
				</tr>
				<tr>
					<td>推送类型</td>
					<td><input type="radio" name="pushType"
						<c:if test="${message.pushType==0 }"> checked</c:if> value="0">普通</td>
					<td><input type="radio" name="pushType"
						<c:if test="${message.pushType==1 }"> checked</c:if> value="1">高级</td>
				</tr>
				<tr>
					<td>表达式</td>
					<td colspan="2"><input type="text"
						<c:if test="${message.pushType==1 }"> value="${message.cronExpression }"</c:if>
						name="cronExpression"></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit"></td>
				</tr>

			</table>

		</form>

	</div>
</body>
</html>