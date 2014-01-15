<%@include file="/includes/header.jsp" %>

<div class="content-header">
    <%= Helper.translateWord(language, "Register")%>
</div>
<div class="course">
    <div id="course-content">
        <c:forEach var="error" items="${errors}">
            <div class="error_message">
                ${error}
            </div>
        </c:forEach>
        <form method="post" action="register">
            <label class="label">
            </label>
            <div class="form-input-radio">
                <span class="form-span"><%= Helper.translateWord(language, "Gender")%>:</span>
                <%= Helper.translateWord(language, "Male")%><input name="gender" type="radio" value="m" required <c:if test="${user.gender == 109}">checked</c:if>> <!-- 109 == ASCII 'm' -->
                <%= Helper.translateWord(language, "Female")%><input name="gender" type="radio" value="f" required <c:if test="${user.gender == 102}">checked</c:if>> <!-- 102 == ASCII 'f' -->
                    <input type="hidden" name="gender" value="empty">
                </div>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "Firstname")%>:</span>
                <input type="text" name="firstname" placeholder="<%= Helper.translateWord(language, "Firstname")%>" class="form-input" required value="${firstname}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Surname")%>:</span>
                <input type="text" name="surname" placeholder="<%= Helper.translateWord(language, "Surname")%>" class="form-input" required value="${surname}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "City")%>:</span>
                <input type="text" name="city" placeholder="<%= Helper.translateWord(language, "City")%>" class="form-input" required value="${city}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Country")%>:</span>
                <input type="text" name="country" placeholder="<%= Helper.translateWord(language, "Country")%>" class="form-input" required value="${country}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Zipcode")%>:</span>
                <input type="text" name="zipcode" placeholder="<%= Helper.translateWord(language, "Zipcode")%>" class="form-input" required value="${zipcode}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Address")%>:</span>
                <input type="text" name="address" placeholder="<%= Helper.translateWord(language, "Address")%>" class="form-input" required value="${address}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Language")%>:</span>
                <select class="select form-input" name="language" required>
                    <option value=""><%= Helper.translateWord(language, "Select language")%>...</option>
                    <option value="0" <c:if test="${language_id == 0}">checked</c:if>>English (UK)</option>
                    <option value="1" <c:if test="${language_id == 1}">checked</c:if>>Nederlands</option>
                    </select>
                </label>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "E-mail")%>:</span>
                <input type="text" name="email" placeholder="you@mail.com" class="form-input" required value="${email}">
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Password")%>:</span>
                <input type="password" name="password" placeholder="********" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span"><%= Helper.translateWord(language, "Confirm password")%>:</span>
                <input type="password" name="confirm_password" placeholder="********" class="form-input" required>
            </label>

            <input type="submit" class="button" id="button" value="<%= Helper.translateWord(language, "Register")%>">
            <a href="Index" class="button cancel" id="button"><%= Helper.translateWord(language, "Cancel")%></a>

        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
