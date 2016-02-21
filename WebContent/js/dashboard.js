var form = $('.form-wrapper form');

/* Handling edit note click */
$('.actionEdit').on('click',function(e){
	e.preventDefault();
	
	// Changing the form title
	$('.form-header span').text("Editer une note");
	
	// Changing the form Action
	form.attr("action",$(this).attr("href"));
	
	// Storing the ID of the current note
	var currentNoteId = $(this).attr("data-id");
	
	// Storing the current note id in the hidden input
	$('#noteId').attr('value',currentNoteId);
	
	// Changing the submit button text
	form.find("input[type='submit']").attr("value","Editer");
	
	// Filling the form with the targeted note values
	var targetedNote = e.target.parentElement.parentElement;
	
	form.find("textarea").val($(targetedNote).find("p").text());
	
	// Getting the tagreted note importance
	var targetedNoteImportance = $(targetedNote).attr("data-importance");
	
	var options = form.find("option");
	
	$.each(options,function(key,option){
		if(option.value == targetedNoteImportance){
			option.selected = true;
		}
	});
	
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling add note click */
$('.actionAdd').on('click',function(e){
	e.preventDefault();
	
	// Changing the form title
	$('.form-header span').text("Ajouter une note");
	
	// Changing the form Action
	form.attr("action",$(this).attr("href"));
	
	// Changing the submit button text
	form.find("input[type='submit']").attr("value","Ajouter");
	
	// Removing the hidden input
	form.find("input[type='hidden']").remove();
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling the Add/Edit form closing */
$('.form-close').on('click',function(e){
	
	// Hidding the Add/Edit form
	$('.form-wrapper').css('display','none');
});