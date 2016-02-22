var form = $('.form-wrapper form');

/* Handling edit note click */
$('.actionEdit').on('click',function(e){
	e.preventDefault();
	
	// Getting the targeted note
	var targetedNote = e.target.parentElement.parentElement;
	
	// Storing the current note id in the appropriate hidden input
	$('#noteId').attr('value',$(targetedNote).attr("data-id"));
	
	// Storing the current note date in the appropriate hidden input
	$('#noteDate').attr('value',$(targetedNote).attr("data-date"));
	
	// Storing the current note owner in the appropriate hidden input
	$('#noteOwner').attr('value',$(targetedNote).attr("data-owner"));
	
	
	// Filling the form with the targeted note values
	form.find("textarea").val($(targetedNote).find("p").text());
	
	// Getting the targeted note importance
	var targetedNoteImportance = $(targetedNote).attr("data-importance");
	
	var options = form.find("option");
	
	$.each(options,function(key,option){
		if(option.value == targetedNoteImportance){
			option.selected = true;
		}
	});
	
	// Changing the form title
	$('.form-header span').text("Editer une note");
	
	// Changing the form Action
	form.attr("action",$(this).attr("href"));
	
	// Changing the submit button text
	form.find("input[type='submit']").attr("value","Editer");
	
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
	
	// Removing the hidden input's values
	var hiddenInputs = form.find("input[type='hidden']");
	
	hiddenInputs.each(function(key,input){
		input.value="";
	});
	
	// Showing the Edit form
	$('.form-wrapper').css('display','block');
});

/* Handling the Add/Edit form closing */
$('.form-close').on('click',function(e){
	
	// Hidding the Add/Edit form
	$('.form-wrapper').css('display','none');
});