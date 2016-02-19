//jQuery.noConflict();
// 
//jQuery( document ).ready(function( $ ) {
//    // You can use the locally-scoped $ in here as an alias to jQuery.
	$("#loginButton").click(function() {
		$('#loginModalWindow').modal('show');
	});
	$("#signInButton").click(function() {
		$('#signInModalWindow').modal('show');
	});
	$('#toc').sidebar('show');
	
	// modal aggiungi servizio
	$("#addBaseServiceBtn").click(function() {
		$('#modalBaseService').modal('show');
	});
	
	//modal aggiungi servizio
	$("#addPlusServiceBtn").click(function() {
		$('#modalPlusService').modal('show');
	});
	
	$('.ui.radio.checkbox').checkbox();
	
	$('.special.cards .image').dimmer({
		on : 'hover'
	});
	
	$('.ui.accordion').accordion();
//});

