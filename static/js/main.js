﻿var loadImg = (new Image).src="/static/img/load.gif";

$(document).ready(function() {
    $("#create-customer-form button").click( function() {
        var new_user = new Object();

        new_user.nombre = $('input#nombre').val();

        console.log(new_user);

        $.ajax({
            type: "post",
            url: "/supermarche/clients",
            data: JSON.stringify(new_user),
            success: function(data){
                console.log(data);
                window.location = "/supermarche/clients";
            },
            dataType: "json",
            contentType : "application/json"
        });
    });

	// S'il y a le tableau des clients...
	if($('#clients-table').length){
		// ... on les affiche
		clientsTable();
	}
	
	// S'il y a le tableau des stocks...
	if($('#stocks-table').length){
		// ... on affiche le tout
		stocksTable();
	}
	
	// S'il y a le tableau des stocks...
	if($('#liste-course-client-table').length){
		// ... on affiche le tout
		infosClient();
	}
});

/**
 * La fonction permettant d'afficher les clients
 */
function clientsTable(){
	var customers_table = $('#clients-table tbody');
	
	// On affiche le gif qui montre qu'on charge
	afficher("load");

	$.ajax({
		type: "get",
		url: "/supermarche/clients",
		success: function(data){
			console.log(data);
	
			// On cache le gif qui montre qu'on charge
			cacher("load");

			$.each(data, function (item) {
				var name 		= data[item].nom;
				var state 		= data[item].etat;
				var id 			= data[item].id;
				var url 		= data[item].url;
				var nbArtDiff	= data[item].nbArticlesDifferents;

				customers_table.append(
				'<tr>' +
					'<th><a href="' + url + '">' + id + '</a></th>' +
					'<td>' + name 		+ '</td>' +
					'<td>' + state 		+ '</td>' +
					'<td>' + nbArtDiff 	+ '</td>' +
					'<td><a type="button" class="btn btn-success btn-xs" href = "'+ url + '">Consulter</a></td>' +
				'</tr>');
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("ajax error");
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
		dataType: "json",
		contentType : "application/json"
	});
};

/**
 * La fonction permettant d'afficher les stocks
 */
function stocksTable(){
	var stocks_table = $('#stocks-table tbody');
	
	// On affiche le gif qui montre qu'on charge
	afficher("load");

	$.ajax({
		type: "get",
		url: "/supermarche/stock",
		success: function(data){
			console.log(data);
	
			// On cache le gif qui montre qu'on charge
			cacher("load");

			$.each(data, function (item) {
				var art 	= data[item].article;
				var qte 	= data[item].quantite;

				stocks_table.append(
				'<tr>' +
					'<td>' + art + '</td>' +
					'<td>' + qte + '</td>' +
				'</tr>');
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("ajax error");
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
		dataType: "json",
		contentType : "application/json"
	});
};

/**
 * La fonction permettant d'afficher les informations d'un client
 */
function infosClient(){
	var stocks_table = $('#stocks-table tbody');
	// On prend l'id par rapport à l'url
	var id = window.location.href.split("/");
	// Si on a un /supermarche/clients/ID/ on prend l'indice FIN-2 et si on a un /supermarche/clients/ID on prend l'indice FIN-1
	id = id[id.length - 1].length == 0 ? id[id.length - 2] : id[id.length - 1];
	
	// On affiche le gif qui montre qu'on charge
	afficher("load");

	$.ajax({
		type: "get",
		url: "/supermarche/clients/" + id,
		success: function(data){
			console.log(data);
	
			// On cache le gif qui montre qu'on charge
			cacher("load");
			
			// On prend et on affiche les infos du client
			
			var liste			= data.liste;
			var listeAPrendre 	= data.listeAPrendre;
			var etat			= data.etat;
			var nom				= data.nom;
			var nbArtDiff		= data.nbArticlesDifferents;
			
			$(".nomClient").text(nom);
			
			$("#infosClient").html(	"<b>Nom  : </b>" + nom 	+ "<br/>" +
									"<b>État : </b>" + etat	+ "<br/>" +
									"<b>Nombre d'articles différents : </b>" + nbArtDiff	+ "<br/>");

			$.each(liste, function (item) {
				var art 	= liste[item].nom;
				var qte 	= liste[item].quantite;

				$("#liste-course-client-table").append(
				'<tr>' +
					'<td>' + art 		+ '</td>' +
					'<td>' + nbArtDiff 	+ '</td>' +
				'</tr>');
			});

			$.each(listeAPrendre, function (item) {
				var art 	= listeAPrendre[item].nom;
				var qte 	= listeAPrendre[item].quantite;

				$("#liste-course-a-prendre-client-table").append(
				'<tr>' +
					'<td>' + art + '</td>' +
					'<td>' + qte + '</td>' +
				'</tr>');
			});
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log("ajax error");
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		},
		dataType: "json",
		contentType : "application/json"
	});
};

function afficher(idObjet){
	$("#" + idObjet).removeAttr("style");
};

function cacher(idObjet){
	$("#" + idObjet).prop("style", "display: none");
}