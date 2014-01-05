<%@include file="/includes/header.jsp" %> 

<div class="content-header">
    <%= Helper.translateWord(language, "Contact") %>
</div>

<div class="course">
    <div id="course-content">

            <form method="post" action="contact">
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "Name") %>:</span>
                    <input name="name" type="text" placeholder="<%= Helper.translateWord(language, "Name") %>" value="${name}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "Subject") %>:</span>
                    <input name="subject" type="text" placeholder="<%= Helper.translateWord(language, "Subject") %>" value="${subject}" class="form-input" required>
                </label>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "Message") %>:</span>
                    <textarea name="message" placeholder="<%= Helper.translateWord(language, "Message") %>" class="form-input-textarea" required>${message}</textarea>
                </label>
                <label class="label">
                    <span class="form-span"><%= Helper.translateWord(language, "E-mail") %>:</span>
                    <input type="text" name="email" placeholder="you@mail.com" class="form-input" required value="${email}">
                </label>
                <input type="submit" class="button" id="button" name="contact" value="<%= Helper.translateWord(language, "Send") %>">
            </form>
    </div>

    <div id="course-content" style="max-height: 100%;">
        <div id="course-content-title">
            <h4><%= Helper.translateWord(language, "Contact information") %></h4>
        </div>
        <br/>
        <%= Helper.translateWord(language, "Phone") %>: 075-1234567<br/>
        <%= Helper.translateWord(language, "E-mail") %>: 32Learn.contact@gmail.com<br/>
        <%= Helper.translateWord(language, "Address") %>: Duivendrechtsekade 36-38<br/>
        <%= Helper.translateWord(language, "City") %>: Amsterdam<br/>
        <%= Helper.translateWord(language, "Zipcode") %>: 1111 AA<br/><br/><br/><br/>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>