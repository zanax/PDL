<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Chat
</div>

<div class="course" id="create-chat">
    <div id="course-content">
        <table class="visible">
            <tr>
                <td style="text-align: right;">
                    <label class="chat-label">
                        <span class="form-span">Name:</span>
                        <input class="form-input" type="text" id="conference-name" placeholder="Chatroom name">
                    </label>
                </td>
            </tr>
            <button class="button right" id="start-conferencing" href="#">Create chat</button>
        </table>
    </div>
</div>

<div class="course" id="chat-rooms">
    <div id="course-content">
        Available chatrooms:
        <table id="rooms-list" class="visible"></table>
    </div>
</div>

<div class="course" id="private-chat">
    <div id="course-content">
        <table class="visible">
            <tr>
                <td>
                    <strong>Private chat-hangout</strong> ?? <a href="/chat-hangout/" target="_blank" title="Open this link in new tab. Then your chat-hangout room will be private!"><code>/chat-hangout/<strong id="unique-token">#123456789</strong></code></a>
                </td>
            </tr>
        </table>
    </div>
</div>
<script>
    $('private-chat').hide();
</script>


<div class="course" id="chat-part">
    <div id="course-content">
        <table id="chat-table" class="center-table hidden">
            <tr>
                <td>
                    <label class="chat-label">
                        <span class="form-span">Message:</span>
                        <input class="form-input" name="message" type="text" id="chat-message" disabled style="width: 597px;">
                    </label>
                </td>
            </tr>
            <button class="button right" id="post-chat-message">Post Message</button>
        </table>
        
        <table id="chat-output" class="hidden"></table>
    </div>
</div>
<script>
    $('chat-part').hide();
</script>

<div class="course" id="leave-chat">
    <div id="course-content">
        <a href="">
            <div class="button full-width cancel">
                Leave chat
            </div>
        </a>
    </div>
</div>
<script>$('leave-chat').hide();</script> 

<%@include file="/includes/footer.jsp" %>

<script src="https://www.webrtc-experiment.com/firebase.js"> </script>
<script src="https://www.webrtc-experiment.com/RTCPeerConnection-v1.5.js"> </script>
<script src="https://www.webrtc-experiment.com/chat-hangout/hangout.js"> </script>
<script src="https://www.webrtc-experiment.com/chat-hangout/hangout-ui.js"> </script>

<script>
    window.addEvent('domready', function() {
        $('start-conferencing').addEvent('click', function(){
            toggleContent();
        });
        
        alterDynamicChatButtons();
    });
</script>