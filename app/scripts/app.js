$(function() {
  "use strict";

  var URL = "http://localhost:8080/spike";

  var out = $("#output_console");

  function report(text, opt_class) {
    out.append(
        $("<p class='" + (opt_class || 'report' ) + "'></p>").append(text)
    );
  }

  var request = {
    url: URL,
    contentType: "application/json",
    logLevel: 'debug',
    transport: 'websocket',
    fallbackTransport: 'long-polling'
  };

  request.onOpen = function(response) {
    report('Atmosphere opened using ' +
           response.transport, "system");
  };

  request.onMessage = function(response) {
    var message = response.responseBody;
    try {
      report("Message received", "system");
      report(message);
    } catch (e) {
      report('BAD MESSAGE: ' + message, "system error");
    }
  };

  request.onClose = function(response) {
    report("Atmosphere closed..", "system");
  };

  request.onError = function(response) {
    report('Sorry, but there\'s some problem with your '
               + 'socket or the server is down', 'system error');
  };

  var subSocket = atmosphere.subscribe(request);

  var publishButton = $("button#publish");

  publishButton.click(function() {
    var msg = {
      title: 'Message Title',
      content: new Date().toString()
    };
    subSocket.push(JSON.stringify(msg));
  });

});