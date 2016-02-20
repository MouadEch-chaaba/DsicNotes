var form = $('.form-wrapper form');

/* Handling edit note click */
$('.actionEdit').on('click',function(e){
	e.preventDefault();
	
	// Changing the form title
	$('.form-header span').text("Editer une note");
	
	// Storing the ID of the current note
	var currentNoteId = $(this).attr("data-id");
	
	// Storing the current note id in the hidden input
	$('#noteId').attr('value',currentNoteId);
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling add note click */
$('.actionAdd').on('click',function(e){
	e.preventDefault();
	
	// Changing the form title
	$('.form-header span').text("Ajouter une note");
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling the Add/Edit form closing */
$('.form-close').on('click',function(e){
	
	// Hidding the Add/Edit form
	$('.form-wrapper').css('display','none');
});