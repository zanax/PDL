<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    Review Test
</div>

<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <c:if test="${show != null && show}">
            <table class="table">
                <th style="width: 120px;">
                    Title
                </th>
                <th style="width: auto;">
                    Description
                </th>
                <th style="width: 30px;">
                    Actions
                </th>
                <c:forEach items="${tests}" var="test">
                    <tr>
                        <td>${test.title}</td>
                        <td>${test.description}</td>
                        <td style="text-align: center;">
                            <a href="reviewTest?id=${test.id}"><img src="img/edit.png"></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>