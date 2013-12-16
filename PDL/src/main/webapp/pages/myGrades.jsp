<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    My grades
</div>
<div class="content-inner">
    <div id="content-inner-block">
        <div class="content-inner title">
            Grades
        </div>
        <table class="table">
            <th style="width: 25px;">
                User ID
            </th>
            <th style="width: 120px;">
                Test name
            </th>
            <th style="width: auto;">
                Grade
            </th>
            <th style="width: 100px;">
                Actions
            </th>


            <c:forEach items="${grades}" var="grade">

                <tr>
                    <td style="text-align: center;">${grade.userId}</td>
                    <td>${grade.testTitle}</td>
                    <td>${grade.grade}</td>
                    <td style="text-align: center;">
                        <a href="reviewTest?id=${grade.id}">Review Test</a>                    
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>