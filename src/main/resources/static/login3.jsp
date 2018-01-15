<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="comm/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>江安县“智慧农安”管理平台</title>
    <link href="${basePath}css/layout/login.css" rel="stylesheet" type="text/css">
    <link href="${basePath}css/basket/ui-select.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${basePath}js/jquery/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="${basePath}js/jquery/jquery.cookie.js"></script>
    <script type="text/javascript" src="${basePath}js/base64/base64.js"></script>

    <script src="${basePath}js/basket/ui-select.js" type="text/javascript"></script>

    <script language="JavaScript">
        $(function () {
            var Winheight = window.innerHeight
                || (window.document.documentElement.clientHeight || window.document.body.clientHeight);
            var Winwidth = window.innerWidth || (window.document.documentElement.clientWidth || window.document.body.clientWidth);
            var docWidth = $(window).width();
            var docheight = $(window).height();
            $(".main").css("height", Winheight);
            $('.login3_form').css('left', Winwidth / 2 - 250 + 'px')
            $('.titleimg').css('left', Winwidth / 2 - 400 + 'px')
            $(window).resize(function () {
                var Winheight = window.innerHeight
                    || (window.document.documentElement.clientHeight || window.document.body.clientHeight);
                $(".main").css("height", Winheight);
            });
            $("#login").click(handleLogin);
            $("#register").click(to_register);

            $("#pwd").keydown(function (event) {
                if (event.keyCode == 13) {
                    handleLogin();
                }
            })


            detectBrowser(); // 检测浏览器类型和版本

            $.removeCookie('token');
            //窗口大小改变刷新页面
//			$(window).resize(function(){
//			    location.reload()
//			});
        })


        $(document).ready(function () {
            // 将所有.ui-select实例化
            $('.ui-select').ui_select();
        });


        /**
         * 检测浏览器版本和类型
         */
        function detectBrowser() {
            var html = "<div class=\"warning\"><p><img alt=\"Warning\" src=\"images/icon_warning.png\"><span>您当前的浏览器版本太低，请升级。</span>本系统支持浏览器：IE9,IE10,Chrome,Firefox,搜狗。</p></div>"

            var ua = navigator.userAgent.toLowerCase();
            var info = {
                ie: /msie/.test(ua) || /NET/.test(ua) || /net/.test(ua),
                op: /opera/.test(ua),
                sa: /safari/.test(ua),
                ch: /chrome/.test(ua),
                ff: /firefox/.test(ua)
            };

            if (!info.ff && !info.ch) {
                if (info.ie) {
                    var ie = ua.match(/msie ([\d.]+)/)[1];
                    if (ie < 9) {
                        $(".warning").css("display", "");
                    }
                } else {
                    $(".warning").css("display", "");
                }
            }
        }

        function handleLogin() {
            if ($("#uname").val() == '' || $("#uname").val() == '请输入用户名') {
                alert("用户名不能为空");
                return;
            }
            if ($("#pwd").val() == '') {
                alert("密码不能为空");
                return;
            }

            var theUrl = $("#area").val() + "/login?r=" + Math.random();

            $.ajax({
                type: "POST",
                dataType: "json",
                url: theUrl,
                data: {
                    "username": base64encode($("#uname").val()),
                    "password": base64encode($("#pwd").val())
                },
                beforeSend: function () {
                    $("#h3load").show();
                },
                complete: function () {
                    $("#h3load").hide();
                },
                success: function (json) {
                    if (json.respCode == "1000000") {
//						var date=new Date();
//						date.setDate(date.getDate()+7);
//						var datasToken = 'token='+json.token;
//						var datasArea = "area="+$("#area").val();
//						var datasTime = "expires="+date.toGMTString();
//						document.cookie = datasToken;
//						document.cookie = datasArea;
//						document.cookie = datasTime;
//						if(json.resultList.deptLevel != "4"){
//							alert("管理员用户请从信息数据库入口登录系统");
//						}else{
//							$.cookie('token', json.token);
//	                        enterManagePlatform();//进入不同的管理平台
//						}

                        $.cookie('token', json.token);
                        enterManagePlatform();//进入不同的管理平台

                    } else {
                        alert(json.respMsg + ',错误代码：[' + json.respCode + ']');
                    }
                },
                error: function (data) {
                    alert('登录失败!请联系系统管理员!');
                }
            });

        }

        /**
         * 初始化系统
         */
        function initSystem() {
            var indexPage = "${basePath}basket/mframe.jsp?area=" + $('#area').val();
            window.location.href = indexPage;
//			var welcomePage = "index.jsp";
//			window.location.href = welcomePage;
        }

        function install() {
            var installUrl = $basePath + "basket/vedio/ZrdClientSetup.msi";
            window.location.href = installUrl;
        }


        function enterManagePlatform() {
            var token = $.cookie('token');
            if (token == "" || typeof(token) == "undefined") {
                alert('请先登录!');
            } else {
                $.ajax({
                    type: "GET",
                    dataType: "json",
                    url: $basePath + $("#area").val() + "/getUserInfoByToken?r=" + Math.random(),
                    beforeSend: function (request) {
                        request.setRequestHeader('token', token);
                    },
                    complete: function () {
                    },
                    success: function (json) {
                        var deptLevel = json.deptLevel;
                        if (deptLevel == "4") {
                            window.location.href = $basePath + "basket/mframe_suppier.jsp?area=" + $('#area').val() + "&auditing=" + json.auditing;
                        } else {
                            window.location.href = $basePath + "basket/mframe.jsp?area=" + $('#area').val();
                        }
                    },
                    error: function (data) {
                        alert('登录失败!请联系系统管理员!');
                    }
                });
            }
        }

        function to_register() {
            var tegisterUrl = $basePath + "basket/bs14/bs14_producecompany_register.jsp?area=jiangan";
            window.location.href = tegisterUrl;
        }

    </script>

</head>
<body>
<div class="main login3main">
    <img class="titleimg" src="images/tangshan/ts1128/TS_0.png"/>
    <form class="login3_form">
        <p>登录管理系统</p>
        <table>
            <tr>
                <td>
                    <label for="uname" class="label_for_uname"/>
                    <input name="" type="text" class="login_input_user" id="uname" value="请输入用户名"
                           onfocus="if(value=='请输入用户名') {value=''}" onblur="if (value=='') {value='请输入用户名'}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="pwd" class="label_for_pwd"/>
                    <input name="" type="password" class="login_input_pwd" id="pwd" style="display:none"
                           onblur="if (value=='') {$('#pwd').hide(); $('#pwdtip').show()}"/>
                    <input name="" type="text" value="请输入密码" class="login_input_pwd" id="pwdtip"
                           onfocus="if (1) { $('#pwd').show(); $('#pwdtip').hide(); $('#pwd').focus()}"/>
                </td>
            </tr>
            <tr>
                <td style="padding: 110px 0 10px;">
                    <input type="hidden" name="area" id="area" value="jiangan"/>
                    <!--
			                <font color="#6f7782"><b>请选择地区：</b></font> &nbsp;
							<select  class="ui-select" style="width: 100px;height: 20px;font-style: normal;font-size:14px" name="area" id="area">
							<c:forEach var="item" items="${servletContextAreaMap}"> 
								<option value="${item.key}"> ${item.value}</option>
							</c:forEach></select>
							  -->
                    <input name="" type="button" value=" " id="register" class="login3_reg_btn"/>&nbsp;&nbsp;&nbsp;
                    <input name="" type="button" value=" " id="login" class="login3_btn"/>
                </td>
            </tr>
            <!-- <tr>
                  <td align="right" valign="top">
                      <a style="margin:0 10px;" onclick="install()" class="a_href">视频控件安装</a>
                  </td>
            </tr> -->
        </table>
        <%--<a href="ts1128.jsp" class="login_back">返回</a>--%>
    </form>
    <!--<h3 id="h3load" style="display:none; position:relative; top: 50%;text-align: center; color: rgb(208, 2, 2);">登录中•••</h3>-->
</div>

<div class="bottom-div" border="0" width="100%" style="padding-right: 10px; min-width:1000px;">
    版权所有：江安县畜牧水产局 、农业局&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：智锐达仪器科技南通有限公司
</div>

</body>

</html>
