$("#loginButton").click(function () {
	$('#loginModalWindow').modal('show');	
});
$("#signInButton").click(function () {
	$('#signInModalWindow').modal('show');	
});
$('#toc').sidebar('show');

// modal aggiungi servizio
$("#addBaseServiceBtn").click(function () {
	$('#modalBaseService').modal('show');	
});

//modal aggiungi servizio
$("#addPlusServiceBtn").click(function () {
	$('#modalPlusService').modal('show');	
});