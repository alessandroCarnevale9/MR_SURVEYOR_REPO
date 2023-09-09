<%@page import="model.bean.Manager.Role"%>
<%@page import="model.bean.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Manager manager = (Manager)session.getAttribute("manager");
    
    if(manager == null) {
    	response.sendRedirect(getServletContext().getContextPath()+"/authentication_manager.jsp");
    	return;
    }
    else {
    	System.out.println(manager.getRole());
    }
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
if(manager.getRole().equals(Role.CATALOG_MANAGER)) {
%>
<title>Home page Gestore Catalogo</title>
</head>
<body>
	<jsp:include page="header_manager.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<%
} else if(manager.getRole().equals(Role.ORDER_MANAGER)){
%>
<title>Home page Gestore Ordini</title>
</head>
<body>
	<jsp:include page="header_manager.jsp"></jsp:include>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<%
}
%>
</html>