<%@include file="/includes/header.jsp" %>

<div class="content-header">
    Register
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
                <span class="form-span">Gender:</span>
                Male<input name="gender" type="radio" value="m" required <c:if test="${gender == 'm'}">checked</c:if>>
                Female<input name="gender" type="radio" value="f" required <c:if test="${gender == 'f'}">checked</c:if>>
                <input type="hidden" name="gender" value="empty">
            </div>
            <label class="label">
                <span class="form-span">Firstname:</span>
                <input type="text" name="firstname" placeholder="firstname" class="form-input" required value="${firstname}">
            </label>
            <label class="label">
                <span class="form-span">Surname:</span>
                <input type="text" name="surname" placeholder="surename" class="form-input" required value="${surname}">
            </label>
            <label class="label">
                <span class="form-span">City:</span>
                <input type="text" name="city" placeholder="City" class="form-input" required value="${city}">
            </label>
            <label class="label">
                <span class="form-span">Country:</span>
                <input type="text" name="country" placeholder="Country" class="form-input" required value="${country}">
            </label>
            <label class="label">
                <span class="form-span">Zipcode:</span>
                <input type="text" name="zipcode" placeholder="zipcode" class="form-input" required value="${zipcode}">
            </label>
            <label class="label">
                <span class="form-span">Address:</span>
                <input type="text" name="address" placeholder="address" class="form-input" required value="${address}">
            </label>
            <label class="label">
                <span class="form-span">Language:</span>
                <select class="form-input-select" name="language" required>
                    <option value="">Select language...</option>
                    <option value="1">English (UK)</option>
                    <option value="2">Nederlands</option>
                </select>
            </label>
            <label class="label">
                <span class="form-span">E-mail:</span>
                <input type="text" name="email" placeholder="you@mail.com" class="form-input" required value="${email}">
            </label>
            <label class="label">
                <span class="form-span">Password:</span>
                <input type="password" name="password" placeholder="********" class="form-input" required>
            </label>
            <label class="label">
                <span class="form-span">Confirm password:</span>
                <input type="password" name="confirm_password" placeholder="********" class="form-input" required>
            </label>

            <input type="submit" class="button" id="button" value="Register">
        </form>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>
