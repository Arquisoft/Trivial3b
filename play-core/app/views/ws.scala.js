var app = angular.module('index', [])

			app.run(function($rootScope, $interval) {
			if(sessionStorage.getItem("datosGlobales")!=null){
		var datosGlobales=sessionStorage.getItem("datosGlobales");
				}
			else{
				var datosGlobales="";
			}
$(function(){
    // get websocket class, firefox has a different way to get it
    var WS = window['MozWebSocket'] ? window['MozWebSocket'] : WebSocket;
    
    // open pewpew with websocket
    var socket = new WS('@routes.Juego.wsInterface().webSocketURL(request)');
    
    var writeMessages = function(event){
    	var datos=event.data.split("%");
    	datosGlobales=datosGlobales+event.data+"!";
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
    		sessionStorage.setItem("datosGlobales", datosGlobales);
    	}
    	else{
        location.href="/respuestaIncorrecta";
    	}
    }
    
    socket.onmessage = writeMessages;
    
    $('.fallo').click(function(event){
    	var usuario = $(this).data('usuario');
		var game=$(this).data('game');
    	socket.send(usuario+"%"+"El "+usuario+" ha fallado"+"%"+game+"%"+"Chat");
        socket.send("Turno");  
    });
    $('#logout').click(function(event){
    	sessionStorage.removeItem("datosGlobales");
    });
    $('#acierto').click(function(event){
    	var usuario = $(this).data('usuario');
		var quesito=$(this).data('quesito');
		var categoria=$(this).data('categoria');
		if(quesito=="QUESITO"){
			socket.send(usuario+"%"+"El "+usuario+" ha conseguido el quesito de "+categoria+"%"+""+"%"+"Chat");
		}
		else{
			socket.send(usuario+"%"+"El "+usuario+" ha acertado"+"%"+""+"%"+"Chat");
		}
    });
    $('#btn-chat').click(function(event){
    		var usuario = $('#btn-chat').data('usuario');
    		var game=$('#btn-chat').data('game');
    		var texto=document.getElementById("btn-input").value;
    		socket.send(usuario+"%"+texto+"%"+game+"%"+"Chat");
    		document.getElementById("btn-input").value="";
    });
    $('#btn-input').keyup(function(event){
    var charCode = (event.which) ? event.which : event.keyCode ;
    	if(charCode==13){
    		var usuario = $('#btn-input').data('usuario');
    		var game=$('#btn-input').data('game');
    		var texto=document.getElementById("btn-input").value;
    		socket.send(usuario+"%"+texto+"%"+game+"%"+"Chat");
    		document.getElementById("btn-input").value="";
    		
    	}
    });
    $(document).ready(function(event){
    	if(datosGlobales!=""){
    	var carga=datosGlobales.split("!");
    	for(i=0;i<(carga.length-1);i++){
    		var datosL=carga[i].split("%");
    	if(datosL[2]!=undefined || datosGlobales!=undefined){
    	var nodoPadre=document.getElementById("contenedor");
		var div1=document.createElement("div");
		if(datosL[0]==datosL[2]){
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
		if(datosL[0]==datosL[2]){
		div3.className="messages msg_sent";
		}
		else{
			div3.className="messages msg_receive";
		}
		div2.appendChild(div3);
		var parrafo=document.createElement("p");
		div3.appendChild(parrafo);
		var texto=document.createTextNode(datosL[1]);
		var time=document.createElement("time");
		var persona=document.createTextNode(datosL[0]+"  "+new Date().getHours()+":"+new Date().getMinutes());
		parrafo.appendChild(texto);
		time.appendChild(persona);
		div3.appendChild(time);
    	}
    	}
    	}
    });
});
			})