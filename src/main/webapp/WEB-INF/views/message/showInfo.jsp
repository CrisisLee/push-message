<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.playhudong.model.Message"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">
		<a href="addPage.htmls">增加</a>
	</h3>
	<h1 align="center">消息列表</h1>
	<form action="POST">
		<table border=1 align="center">
			<tr>
				<td align="center">id</td>
				<td align="center">标题</td>
				<td align="center">内容</td>
				<td align="center">用户</td>
				<td align="center">渠道</td>
				<td align="center">推送时间</td>
				<td align="center">状态</td>
				<td align="center">创建人</td>
				<td align="center">创建时间</td>
				<td align="center">更新时间</td>
				<td align="center">推送类型</td>
				<td align="center">表达式</td>
				<td align="center">已推送次数</td>
				<td colspan="3" align="center">操作</td>
			</tr>
			<c:forEach items="${messageList}" var="message">
				<tr>
					<td align="center">${message.id}</td>
					<td align="center">${message.title}</td>
					<td align="center">${message.content}</td>
					<td align="center">${message.toUsers}</td>
					<td align="center">${message.channel}</td>
					<td align="center"><c:if test="${message.pushTime!=null}">${message.pushTime}</c:if>
						<c:if test="${message.pushTime==null}">--</c:if></td>
					<td align="center">${message.status}</td>
					<td align="center">${message.createUser}</td>
					<td align="center">${message.createTime}</td>
					<td align="center">${message.updateTime}</td>
					<td align="center">${message.pushType}</td>
					<td align="center"><c:if
							test="${message.cronExpression!=null}">${message.cronExpression}</c:if>
						<c:if test="${message.cronExpression==null}">--</c:if></td>
					<td align="center"><c:if test="${message.pushedCount!=-1}">${message.pushedCount}</c:if>
						<c:if test="${message.pushedCount==-1}">--</c:if></td>
					<td align="center">
						<c:if test="${message.status==0 }">
							<a href="addToPushList.htmls?id=${message.id }">加入推送列表</a>
						</c:if>
						<c:if test="${message.status==1 }">
							加入推送列表
						</c:if>
					</td>
					<td align="center">
						<c:if test="${message.status==0 }">
							<a href="updatePage.htmls?id=${message.id }">修改</a>
						</c:if>
						<c:if test="${message.status==1 }">
							修改
						</c:if>
					</td>
					<td align="center"><a href="deleteMessage.htmls?id=${message.id }">删除</a></td>
				</tr>
			</c:forEach>

		</table>
	</form>


</body>
</html>