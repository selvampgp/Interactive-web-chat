<html>
<head>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script language="javascript">
        var UserID = "<%= request.getAttribute("userID") %>";
        var UserName = "<%= request.getAttribute("userNick") %>";
    </script>
    <title>
        Chat
    </title>
</head>

<body>
<div align="center">
    <div id="greeting"></div>

    <div style="text-align: left; float: left; width: 60%">
        <h2>Chat</h2>

        <div id="messagesDivId"></div>
        <p>
            <label for="messageText">Enter you message:</label>
            <input type="text" size=60 id="messageText"/>
            <button id="sendMessageButton">Send</button>
        </p>
    </div>

    <div style="text-align: left; float: left; margin-left: 10%">
        <h2>Other users online:</h2>

        <div id="usersDivId"></div>
    </div>

    <p>
        <button id="logoutButton">Logout</button>
    </p>
</div>
</body>
</html>


<script language="javascript">
    $(function () {
        document.getElementById("greeting").innerHTML = "<h1>Hello, " + UserName + "!</h1>";

        setInterval(onTimeOutFunction, 1000);
        function onTimeOutFunction() {
            $.getJSON("/messages/",
                    function (data) {
                        if (data !== null) {
                            var messageList = document.getElementById("messagesDivId");
                            while (messageList.firstChild) {
                                messageList.removeChild(messageList.firstChild);
                            }
                            $.each(data, function (index, value) {
                                var newNode = document.createElement('div');
                                newNode.innerHTML = "[" + value.time + "] " + value.postedBy + " : " + value.textResp;
                                newNode.style.color = value.color;
                                messageList.appendChild(newNode)
                            });
                        }
                    });
            $.getJSON("/messages/users/" + UserID,
                    function (data) {
                        if (data !== null) {
                            var usersList = document.getElementById("usersDivId");
                            while (usersList.firstChild) {
                                usersList.removeChild(usersList.firstChild);
                            }
                            $.each(data, function (index, value) {
                                var newNode = document.createElement('div');
                                newNode.innerHTML = value.nickName;
                                newNode.style.color = value.color;
                                usersList.appendChild(newNode)
                            });
                        }
                    });
        }

        $("#sendMessageButton").click(function () {
            var messageText = $("#messageText").val();
            var message = {text: messageText, userId: UserID};
            var messageString = JSON.stringify(message);
            $.ajax({
                        type: "POST",
                        url: "/messages/",
                        contentType: "application/json; charset=utf-8",
                        data: messageString
                    }
            );
            $("#messageText").val("");
        });

        $("#logoutButton").click(function () {
            $.ajax({
                        type: "POST",
                        url: "/messages/users/" + UserID,
                        contentType: "application/json; charset=utf-8"
                    }
            );
            window.location.replace("/");
        });

    });

</script>