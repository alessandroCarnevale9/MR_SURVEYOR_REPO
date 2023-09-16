<%@page import="model.bean.Manager.Role"%>
<%@page import="java.util.Collection"%>
<%@page import="model.bean.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    Manager manager = (Manager)session.getAttribute("manager");
    Collection<?> assignedRoles = (Collection<?>)session.getAttribute("assignedRoles");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles/header_style.css">

<script src="js/jquery-3.7.1.js"></script>

</head>
<body>
	<header>
		<div class="logo">
			<a href="homepage_manager.jsp"><img src="images/logo.png" alt="Logo del sito"></a>
        </div>
        
        
        <div class="nav-right manager-nav">
                
                <span id="user-dropdown-toggle" class="material-symbols-outlined">person</span>
                <%
                if(manager == null) {
                %>
                <div id="user-dropdown" class="user-dropdown-content">
        			<a href="authentication_manager.jsp">Accedi</a>
    			</div>
    			<%
                } else {
    			%>	
    			<div id="user-dropdown" class="user-dropdown-content">
        			<ul class="header-menu">
						<%-- <li><a href="${pageContext.request.contextPath}/homepage_enduser.jsp">BACHECA</a></li>
						<li><a href="#">ORDINI</a></li>
						<li><a href="#">INDIRIZZO</a></li>
						<li><a href="#">DETTAGLI ACCOUNT</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/AuthenticationManagerServlet?invalidate">LOGOUT</a></li>
					</ul>
    			</div>
    			<%
                }
    			%>
    			<%
    			if(assignedRoles != null) {
    			%>
    				<span id="switch" class="material-symbols-outlined">switch</span>
    				<div id="manager_roles" class="roles-dropdown-content">
    					<ul id="roles-menu">
    					<%
    						if(assignedRoles.contains("catalog_manager")) {
    					%>
    						<li><a href="${pageContext.request.contextPath}/SwitchRoleServlet?catalog_manager">GESTORE CATALOGO</a></li>
    					<%
    						}
    						if(assignedRoles.contains("order_manager")) {
    					%>	
    						<li><a href="${pageContext.request.contextPath}/SwitchRoleServlet?order_manager">GESTORE ORDINI</a></li>
    					<%
    						}
    					%>	
    					</ul>
    				</div>
    			<%
    			}
    			%>	
                </div>
           
    </header>
    
    <script type="text/javascript" src="js/drop_down.js"></script>
    
</body>
</html>