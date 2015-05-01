$(function(){

    // get websocket class, firefox has a different way to get it
    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;
    
    // open pewpew with websocket
    var socket = new WS('@routes.Juego.wsInterface().webSocketURL(request)');
    
    var writeMessages = function(event){
        location.href="/indexr/";
    }
    
    socket.onmessage = writeMessages;
    
    $('#fallo').click(function(event){
            socket.send("Hola");   
    }); 
});