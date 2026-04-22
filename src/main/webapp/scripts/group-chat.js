var wsProtocol = 'ws://';
if (window.location.protocol === 'https:') {
    wsProtocol = 'wss://';
}
const wsUri = wsProtocol + document.location.host + document.location.pathname + "/endpoint";
const websocket = new WebSocket(wsUri);
websocket.onopen = function (event) {
    console.log("opened websocket: " + wsUri);
};
websocket.onmessage = function (event) {
    updateTextArea(event.data, "in");
};

function displayError(msg) {
    const errorText = document.getElementById("errorText");
    errorText.innerText = msg;
    errorText.classList.remove("d-none"); // Displays the errors message
}

const messageForm = document.getElementById("messageForm");
messageForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Do not send WS data to a servlet
    // Remove any previous error message
    resetErrorMessage();
    // Require the user's name
    const userName = document.getElementById("userName").value;
    if(userName === "") {
        displayError("Name is required");
        return;
    }
    // Require the message form field
    const message = document.getElementById("message").value;
    if(message === "") {
        displayError("Message is required");
        return;
    }

    // Build a JSON object and convert it to a string so it can be sent
    const json = JSON.stringify({"name": userName, "message": message});
    // Send the message
    sendMessage(json);
    // Reset the next message so it's ready for the next message
    resetMessageBox();
    // Update the message output box just like we would with an incoming message
    updateTextArea(json, "out");
});

function resetErrorMessage() {
    const errorText = document.getElementById("errorText");
    errorText.classList.add("d-none"); // Hides the errors message
    errorText.innerText = ""; // Resets the previous error message
}

// this function is called to send a message to the server endpoint
function sendMessage(json) {
    // Only send JSON if the websocket is open
    if(websocket.readyState === websocket.OPEN) {
        websocket.send(json);
    }
}

function resetMessageBox() {
    const message = document.getElementById("message");
    message.value = ""; // removes any existing text from message box
    message.focus(); // moves the cursor's focus to the message form field
}

function updateTextArea(data, inOut) {
    // Parse the data as JSON so the fields can be accessed
    const json = JSON.parse(data);
    // Use the JSON notation to retrieve the data fields
    const name = json.name;
    const message = json.message;
    // Build the text to display then show it
    let result = (inOut === "in") ? '<div class="in">' : '<div class="out">';
    result += `<p>${message}</p>`;
    result += `<span>${(inOut === "in") ? name : "Me"}</span>`;
    result += "</div>";
    const messageBox = document.getElementById("messages");
    messageBox.innerHTML += result;
    // Attempt to move the scrolling of the textarea to show the lowest item
    messageBox.scrollTop = messageBox.scrollHeight;
    // TODO: Extra Credit, only scroll to the bottom if the scrollbar is already at the bottom. Don't scroll down if the user has scrolled up.
}