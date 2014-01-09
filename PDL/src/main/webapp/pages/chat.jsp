<%@include file="/includes/header.jsp" %>       

<div class="content-header">
    Chat
</div>

<div class="course">
    <div id="course-content">
        <table class="visible">
            <tr>
                <td style="text-align: right;">
                    <input type="text" id="conference-name" placeholder="Hangout Name...">
                </td>
                <td>
                    <button id="start-conferencing" href="#">Start Chat-Hangout</button>
                </td>
            </tr>
        </table>

        <table id="rooms-list" class="visible"></table>

        <table class="visible">
            <tr>
                <td style="text-align: center;">
                    <strong>Private chat-hangout</strong> ?? <a href="/chat-hangout/" target="_blank"
                                                                title="Open this link in new tab. Then your chat-hangout room will be private!"><code>/chat-hangout/<strong
                                                                                                                                                                        id="unique-token">#123456789</strong></code></a>
                </td>
            </tr>
        </table>

        <table id="chat-table" class="center-table hidden">
            <tr>
                <td style="text-align: center;">
                    <input type="text" id="chat-message" disabled>
                    <button id="post-chat-message">Post Message</button>
                </td>
            </tr>
        </table>

        <table id="chat-output" class="hidden"></table>
    </div>
</div>

<%@include file="/includes/footer.jsp" %>

<script src="https://www.webrtc-experiment.com/firebase.js"> </script>
<script src="https://www.webrtc-experiment.com/RTCPeerConnection-v1.5.js"> </script>
<script src="https://www.webrtc-experiment.com/chat-hangout/hangout.js"> </script>
<script src="https://www.webrtc-experiment.com/chat-hangout/hangout-ui.js"> </script>