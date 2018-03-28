<%@ page import="com.axboot.jeho.utils.SessionUtils" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%
    String lastNavigatedPage = null;

    if (SessionUtils.isLoggedIn()) {
        lastNavigatedPage = "/jsp/main.jsp";
    }

    request.setAttribute("redirect", lastNavigatedPage);
%>

<c:if test="${redirect!=null}">
    <c:redirect url="${redirect}"/>
</c:if>

<ax:set key="axbody_class" value="setup"/>

<ax:layout name="empty">
    <jsp:attribute name="css">
    </jsp:attribute>

    <jsp:attribute name="js">
        <script type="text/javascript" src="<c:url value='/assets/js/axboot/dist/good-words.js' />"></script>
    </jsp:attribute>

    <jsp:attribute name="script">
        <script type="text/javascript">
            var fnObj = {
                pageStart: function () {
                    $('[data-btn="setup"]').click(function () {
                        axboot.ajax({
                            type: "GET",
                            url: "/setup/init",
                            data: {},
                            callback: function (response) {
                                alert("Initializing completed");
                                location.href = CONTEXT_PATH + "/";
                            }
                        });
                    });
                }
            };
        </script>
    </jsp:attribute>

    <jsp:body>
        <ax:flex-layout valign="top" align="center" style="width:100%;height:100%;">

            <div style="width: 360px;padding-top: 20px;">
                <div class="page-header">
                    <h1>AXBOOT
                        <small>Setup</small>
                    </h1>
                </div>

                <div class="panel panel-primary">
                    <div class="panel-heading"><ax:lang id="ax.setup.database.info"/></div>
                    <table class="table">
                        <colgroup>
                            <col width="100"/>
                        </colgroup>
                        <tr>
                            <th>
                                DatabaseType
                            </th>
                            <td>
                                    ${databaseType}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                JdbcUrl
                            </th>
                            <td>
                                    ${jdbcUrl}
                            </td>
                        </tr>
                        <tr>
                            <th>
                                UserName
                            </th>
                            <td>
                                    ${username}
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="alert alert-info" role="alert">
                    <ax:lang id="ax.setup.warning"/>
                </div>

                <!-- TODO : AJAX Call로 처리 -->
                <button class="btn btn-info btn-lg" data-btn="setup"><ax:lang id="ax.setup.start.btn"/></button>
            </div>


        </ax:flex-layout>
    </jsp:body>

</ax:layout>
