<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des locations</title>
<link href="css/style.css" rel="stylesheet">
<script src="js/location.js"></script>
</head>
<body>
		<c:forEach var="loc" items="${requestScope.LOCATIONS_LIST}">
			<form action="afficheLocation" method="get">
				<input type="hidden" name="id" value="${loc.id}" />
				<fieldset class="locationCliquable" onclick="alert('${loc.id}');"
					onmouseover="mouseOnLocation(this)"
					onmouseout="mouseOutLocation(this)">
					<legend><c:out value="${loc.city}" /> (<c:out value="${loc.zipCode}" />)</legend>
					<p>${loc.address} - ${loc.city} (${loc.zipCode})<br/><br/><br/>
					Prix pour une nuit : ${loc.nightPrice} euros<br/><br/><br/>
					
					<c:choose>
						<c:when test="${ loc.temperature == null }">
							Temp�rature actuelle : NULL
						</c:when>
						
						<c:otherwise>
							Temp�rature actuelle : <fmt:formatNumber type="number" maxFractionDigits="1" minFractionDigits="1" value="${loc.temperature - 273}"/> �C
						</c:otherwise>
					</c:choose>
					</p>
					<p><img src="data:image/jpg;base64,${loc.base64Image}"
							width="200px" /></p>
				</fieldset>
			</form>
		</c:forEach>
</body>
</html>