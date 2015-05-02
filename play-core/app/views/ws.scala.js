var app = angular.module('index', [])

			app.run(function($rootScope, $interval) {
				var datosGlobal="";
$(function(){
    // get websocket class, firefox has a different way to get it
    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;
    
    // open pewpew with websocket
    var socket = new WS('@routes.Juego.wsInterface().webSocketURL(request)');
    
    var writeMessages = function(event){
    	var datos=event.data.split("%");
    	datosGlobal.concat(datos+"!");
    	alert(datosGlobal);
    	if(event.data!="Turno"){
    		var nodoPadre=document.getElementById("contenedor");
    		var div1=document.createElement("div");
    		if(datos[0]==datos[2]){
    		div1.className="row msg_container base_sent";
    		}
    		else{
    			div1.className="row msg_container base_receive";
    		}
    		nodoPadre.appendChild(div1);
    		var div2=document.createElement("div");
    		div2.className="col-xs-10 col-md-10";
    		div1.appendChild(div2);
    		var div3=document.createElement("div");
    		if(datos[0]==datos[2]){
    		div3.className="messages msg_sent";
    		}
    		else{
    			div3.className="messages msg_receive";
    		}
    		div2.appendChild(div3);
    		var parrafo=document.createElement("p");
    		div3.appendChild(parrafo);
    		var texto=document.createTextNode(datos[1]);
    		var time=document.createElement("time");
    		var persona=document.createTextNode(datos[0]+"  "+new Date().getHours()+":"+new Date().getMinutes());
    		parrafo.appendChild(texto);
    		time.appendChild(persona);
    		div3.appendChild(time);
    	}
    	else{
        location.href="/indexr/";
    	}
    }
    
    socket.onmessage = writeMessages;
    
    $('#fallo').click(function(event){
            socket.send("Turno");   
    });
    $('#btn-chat').click(function(event){
    		var usuario = $('#btn-chat').data('usuario');
    		var game=$('#btn-chat').data('game');
    		var texto=document.getElementById("btn-input").value;
    		socket.send(usuario+"%"+texto+"%"+game);
    		document.getElementById("btn-input").value="";
    });
});
			})