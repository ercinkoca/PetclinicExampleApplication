<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Insert title here</title>
</head>
<body>

	<table>
		
		<thead>
			<tr style="font-weight: bold;" bgcolor="lightblue">
				<td>Id</td>
				<td>First Name</td>
				<td>Last Name</td>
			</tr>
		</thead>
		
		<c:forEach items="${owners}" var="owner"> <!-- owners nesnesi kontroller'dan gelen nesne. owner ise bu nesnenin jsp'deki karşılığı. Yani artık owner,owners nesnesini gösteriyor. -->
			<tr>
				<td>${owner.id }</td>
				<td>${owner.firstName}</td>
				<td>${owner.lastName}</td>
			</tr>
		</c:forEach>
	
	</table>
	

</body>
</html>