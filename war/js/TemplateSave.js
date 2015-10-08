 	var keepHtml = false;
 	//source: http://stackoverflow.com/questions/822452/strip-html-from-text-javascript
	function strip(html) {
	   var tmp = document.createElement("DIV");
	   tmp.innerHTML = html;
	   return tmp.innerText||tmp.textContent;
	}
 	//source: http://snipplr.com/view.php?codeview&id=634
	function nl2br(text) {
		var retVal = '';
		retVal = text.replace(/(\r\n|\n|\r)/gm, '_NL_');	//thanks to http://www.textfixer.com/tutorials/javascript-line-breaks.php
		retVal = retVal.replace(/[\x00-\x1F\x80-\xFF]/g, "");
		return retVal;
	}
 	function handleText(text) {
 		//alert('handleText = '+text); 		
 		var retVal = text;
 		if(text != undefined) {
			retVal = retVal.replace(/=/g, "_EQUAL_");
			retVal = retVal.replace(/\./g, "_DOT_");
			retVal = retVal.replace(/\\/g, "_BCKSLASH_");
			retVal = retVal.replace(/\//g, "_SLASH_");
			retVal = retVal.replace(/:/g, "_COLON_");
			retVal = retVal.replace(/-/g, "_DASH_");
			if(!keepHtml) {
				retVal = strip(retVal);
			}
			//retVal = retVal.replace(/<t:outputraw value="literal: &lt;"/>/g, "_LT_");
			//retVal = retVal.replace(/<t:outputraw value="literal: &gt;"/>/g, "_GT_");
 			retVal = nl2br(retVal);
			retVal = retVal.replace(/[\W]+/g, " ");
 			//alert('safely replaced 22');
 		}
 		return retVal;
 	}
	function saveSettings() {
		//alert('saveSettings start');
	    localStorage.host = jQuery('#host').text();
	    localStorage.appid = jQuery('#appid').text();
	    var target = jQuery('#what').text();
	    if(target.indexOf(":") > -1) {
		    var whatCategory = target.split(":");
		    localStorage.what = whatCategory[0]+'-'+whatCategory[1]+'-'+jQuery('#appid').text();
		    localStorage.category = "";
	    } else {
		    localStorage.what = target;
		    localStorage.category = "";
	    }
	    localStorage.details = handleText(jQuery('#details').val());
	    localStorage.updated = jQuery('#lastUpdatedDate').val();
		//alert('saveSettings end');
	}
	function resetSettings() {
	    localStorage.clear();
	    //alert('settings cleared');
	}
	function normalBackup() {
		saveSettings();
		//alert(localStorage.host);
		post_to_url("https://"+localStorage.host+"/api/router-ge.php", {
			'appId': localStorage.appid, 'what': localStorage.what, 'category': localStorage.category,
			'details': localStorage.details
		});
		resetSettings();
	}
	function handleEmptyString(text) {
		if(text === '') {
			//text = ' ';
		}
		return text;
	}
	function xhrBackup() {
		saveSettings();
		alert("xhrBackup host '" + localStorage.host + "' 'appId':"+ localStorage.appid + ", 'what':" + localStorage.what+", 'category':"+ localStorage.category+", 'details':"+ localStorage.details);
		post_to_urlXHR("https://"+localStorage.host+"/api/router-ge.php", {
			'appId': localStorage.appid, 'what': localStorage.what, 'category': localStorage.category,
			'details': localStorage.details
		});
		resetSettings();
	}
 	/* source: http://stackoverflow.com/questions/298745/how-do-i-send-a-cross-domain-post-request-via-javascript/7605119#7605119 */
 	function post_to_url(url, params) {
    	var form = document.createElement('form');
    	form.action = url;
    	form.method = 'POST';

    	for (var i in params) {
        	if (params.hasOwnProperty(i)) {
            	var input = document.createElement('input');
            	input.type = 'hidden';
            	input.name = i;
            	input.value = params[i];
            	//alert("k '" + input.name + "' v '" + input.value + "'");
            	form.appendChild(input);
        	}
    	}
    	form.submit();
 	}
 	function handleSendError(xhr2) {
  		if(xhr2.status !== 0) { 
  			if(xhr2.status !== 200) {
  				alert("Error: Not able to backup properly, status: " + xhr2.status + " statusText: " + xhr2.statusText); //not ok
  			}
  		}
 	}
	function handleResponse(xhr2) {
		//alert("handleReponse");
	  	if (xhr2.readyState === 4) {
	  		if(xhr2.status === 200) {
	  			alert("Reponse is ok, status text: " + xhr2.statusText);	//ok
	  		} else {
	  			alert("Response: Not able to backup properly, response: " + xhr2.statusText); //not ok
	  		}
	  	}
	}
	function post_to_urlXHR(url, params) {
		var formObject = new FormData();
		for (var i in params) {
			formObject.append(i,params[i]);
    	}
   
		var xhr2 = new XMLHttpRequest();  
		xhr2.onerror = handleSendError(xhr2);
		xhr2.onreadystatechange = handleResponse(xhr2);
		xhr2.open("POST", url, true);	//true for asynchronous
		xhr2.send(formObject);
		//alert('xhr2 submited');
		jQuery('#form').submit();
	}
 	function backupSave() {
 		//alert('backupSave');
		saveSettings();
		xhrBackup();
		jQuery('#form').submit();
		resetSettings();
	}
 	function backupOnly() {
		saveSettings();
		normalBackup();
		resetSettings();
	}
	jQuery(document).ready(function(){
//		jQuery('#form').bind('submit', function() { 
//			alert('User clicked submit');
//			backup();
			resetSettings();
			saveSettings();
//		});
	});
