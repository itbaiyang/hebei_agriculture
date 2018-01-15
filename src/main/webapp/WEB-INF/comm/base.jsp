<%--
  Created by IntelliJ IDEA.
  User: zsj
  Date: 2016/8/10
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" language="java" %>
<!-- 基础文件, 包括公用CSS文件以及公共的JS文件 -->
<link type="text/css" rel="stylesheet" href="${basePath}css/basket/reset.css"/>
<link type="text/css" rel="stylesheet" href="${basePath}css/basket/style.css"/>
<script type="text/javascript" src="${basePath}js/jquery/jquery.min-1.11.3.js"></script>
<script type="text/javascript" src="${basePath}js/jquery/jquery.cookie.js"></script>
<script type="text/javascript" src="${basePath}js/common/commonfunc.js"></script>
<script type="text/javascript">
    $(document).ajaxSuccess(function (event, xhr, settings) {
        try {
            var b = JSON.parse(xhr.responseText);
            if (b.respCode == '1000007') {
                top.location.href = $basePath;
            }
        } catch (e) {
        }
    });
</script>