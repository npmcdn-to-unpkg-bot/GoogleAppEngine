/*
Copyright (c) 2003-2011, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.fullPage = true;	//http://stackoverflow.com/questions/2440680/get-formatted-html-from-ckeditor
	//=== source: http://www.codecogs.com/latex/integration/ckeditor/install.php
//	config.extraPlugins = 'equation';

	config.toolbar_Full =
	[
	['Source','-','Save','NewPage','Preview','-','Templates'],
	['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	'/',
	['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
	['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	['BidiLtr', 'BidiRtl' ],
	['Link','Unlink','Anchor'],
	['equation','Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe'],
	'/',
	['Styles','Format','Font','FontSize'],
	['TextColor','BGColor'],
	['Maximize', 'ShowBlocks','-','About']
	];

	config.toolbar_Basic =
	[
//	['equation', '-', 'Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink','-','About']
	['Bold', 'Italic', '-', 'NumberedList', 'BulletedList', '-', 'Link', 'Unlink', 'Iframe','-','About']
	];

	//http://stackoverflow.com/questions/5143516/detecting-onchange-events-from-a-ckeditor-using-jquery
	//http://alfonsoml.blogspot.com/2011/03/onchange-event-for-ckeditor.html
	config.extraPlugins = 'onchange';
	
	//http://docs.cksource.com/Talk:CKEditor_3.x/Developers_Guide
	config.toolbarStartupExpanded = false;	//collapsed by default
};

//http://stackoverflow.com/questions/2547090/ckeditor-adds-unwanted-newline-to-p
CKEDITOR.on('instanceReady', function( ev ) {
	  var blockTags = ['div','h1','h2','h3','h4','h5','h6','p','pre','li','blockquote','ul','ol',
	  'table','thead','tbody','tfoot','td','th',];

	  for (var i = 0; i < blockTags.length; i++)
	  {
	     ev.editor.dataProcessor.writer.setRules( blockTags[i], {
	        indent : false,
	        breakBeforeOpen : false,	//DO NOT insert a new line BEFORE my text ! (http://docs.cksource.com/CKEditor_3.x/Developers_Guide/Output_Formatting)
	        breakAfterOpen : false,
	        breakBeforeClose : false,
	        breakAfterClose : true
	     });
	  }
	  
	  config.extraPlugins = 'onchange';	  
});

