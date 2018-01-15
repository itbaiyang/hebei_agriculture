<%--<%@ page pageEncoding="UTF-8"%>--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 文档类型的声明必须放在文档的最前面，以确保IE浏览器以标准模式解析，否则将以quirks模式解析 --%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String contextPath = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + (request.getServerPort() == 80 ? "" : (":" + request.getServerPort()))
            + contextPath + "/";
    String fileURI = request.getRequestURI();
    String area = request.getParameter("area");
    request.setAttribute("basePath", basePath);
    request.setAttribute("fileURI", fileURI);
    request.setAttribute("area", area);
%>
<script type="text/javascript">
    window.$basePath = "${basePath}";
    window.$fileURI = "${fileURI}";
    window.$area = "${area}";
</script>