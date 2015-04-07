function pagaAgora(id){
	$.get("pagaConta?id="+id, deuCerto);	
}

function pagaAgoraPost(id){
	$.post("pagaConta", {"id" : id}, function(){
		$("#conta_"+id).html("Paga");
	});
}

function deuCerto(){
	alert("Deu certo com get");
}