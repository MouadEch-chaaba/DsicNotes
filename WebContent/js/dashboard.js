var form = $('.form-wrapper form');

/* Handling edit note click */
$('.actionEdit').on('click',function(e){
	e.preventDefault();
	
	// Storing the ID of the current note
	var currentNoteId = $(this).attr("data-id");
	
	// Storing the current note id in the hidden input
	$('#noteId').attr('value',currentNoteId);
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling the Add/Edit form closing */
$('.form-close').on('click',function(e){
	
	// Hidding the Add/Edit form
	$('.form-wrapper').css('display','none');
});