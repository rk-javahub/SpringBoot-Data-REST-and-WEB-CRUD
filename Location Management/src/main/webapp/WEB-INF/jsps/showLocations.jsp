	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@page isELIgnored="false"%>
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="ISO-8859-1">
	<title>Locations</title>
	</head>
	<body>
		<h2>Locations</h2>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>CODE</th>
				<th>NAME</th>
				<th>TYPE</th>
				<th colspan="2">OPERATIONS</th>
			</tr>
			<c:forEach items="${locations}" var="location">
				<tr>
	
					<td>${location.id}</td>
					<td>${location.code}</td>
					<td>${location.name}</td>
					<td>${location.type}</td>
					<td><a href="deleteLocation?id=${location.id}">DELETE</a></td>
					<td><a href="updateLocation?id=${location.id}">UPDATE</a></td>
				</tr>
			</c:forEach>
		</table>
		<br />
		<a href="showCreate">Add Location</a>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
		<a href="showReport">Show Report</a>
	
	</body>
	</html>