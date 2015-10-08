//var fiosConverter = new tool.Packages.com.google.code.kss.tool.fios.FIOSConverter();
//[
//	This function offset the x and y based on the parent handle. It also resize the width and height
//  based on the parent (optional).
//	If the parent does not exist, FDT recognize the handle by saving it to a buffer.
//]
/*
function handleParent(iLayerNumber, iAttributeID, lParentHandle, iX, iY, iWidth, iHeight) {
	found = false
	if(lParentHandle != null) {
		for key,value in pairs(anyWindow) do
			--print(key..": "..tostring(value))
			if(value.name == lParentHandle) {
				print("found "..lParentHandle.." "..value.name)
				found = true
				iX = iX + value.x
				iY = iY + value.y
				w = value.width
				h = value.height
			}
		}
		if(not found) {
			parentTable = {}
			parentTable["name"] = lParentHandle
			parentTable["x"] = iX
			parentTable["y"] = iY
			parentTable["width"] = iWidth
			parentTable["height"] = iHeight
			table.insert(anyWindow, parentTable)
			w = iWidth
			h = iHeight
		}
		print('Graphics.CreateWindow() coordinates x='..iX..' y='..iY..' width='..iWidth..' height='..iHeight)
	}
	
	return iX, iY, w, h
}

--[
--	This function uses the x and y based of the parent handle. It also resize the width and height
--  based on the parent window.
--]
function fitParent(iLayerNumber, iAttributeID, lParentHandle, iX, iY, iWidth, iHeight) {
	if(lParentHandle != null) {
		for key,value in pairs(anyWindow) do
			--print(key..": "..tostring(value))
			if(value.name == lParentHandle) {
				print("found "..lParentHandle.." "..value.name)
				iX = value.x
				iY = value.y
				iWidth = value.width
				iHeight = value.height
			}
		}
	}
	
	print(iX..' '..iY..' '..iWidth..' '..iHeight)
	return iX, iY, iWidth, iHeight
}

function getWindow(iWinID) {
	iX=-1
	iY=-1
	iWidth=-1
	iHeight=-1
	if(iWinID != null) {
		for key,value in pairs(anyWindow) do
			--print(key..": "..tostring(value))
			if(value.name == iWinID) {
				print("found "..iWinID.." "..value.name)
				iX = value.x
				iY = value.y
				iWidth = value.width
				iHeight = value.height
			}
		}
	}
	
	print(iX..' '..iY..' '..iWidth..' '..iHeight)
	return iX, iY, iWidth, iHeight
}
*/
function getWindowCount() {
	count = 0
	for (i=0; i < anyWindow.length; i++) {
		count = count + 1
	}
	
	return count
}
function getCanvasColorCode(fontColor) {
	a = fontColor.substr(2,2)
	b = fontColor.substr(4,2)
	g = fontColor.substr(6,2)
	r = fontColor.substr(8,2)

	return '#' + r + g + b
}
function getCSS3ColorCode(fontColor) {
	a = fontColor.substr(2,2)
	b = fontColor.substr(4,2)
	g = fontColor.substr(6,2)
	r = fontColor.substr(8,2)

	return 'rgba('+ parseInt(b,16) +','+ parseInt(g,16) +','+ parseInt(r,16) +','+ parseInt(a,16) +')'
}
function removeElement(div){
	var d1=document.getElementById(div).parentNode;
	var d2=document.getElementById(div);
	d1.removeChild(d2); 
}
function clearElement(div){
	var d1=document.getElementById(div);
	d1.innerHTML = "";
}
//=== Modified codes thanks to Mad Irish (http://www.madirish.net/?article=141) -->
function addTextArea(x,y,width,height,fontColor,fontBgColor,fontCode,text) {
	var newArea = addElement();
	debug.info('getCSS3ColorCode ' + getCSS3ColorCode(fontColor))
	//var area = document.getElementById('kssTextArea').innerHTML;
	var area = "<textarea style='width:" + width + "px; height:" + height + "px; position:absolute; left:" + x + "px; top:" + y + "px; z-index:1000; overflow:hidden; border:0; resize:none; background-color:transparent;" + 
	"color:" + getCSS3ColorCode(fontColor) + "; font-size:" + fontCode + "pt; " +
	"font-family: arial; " +
	"' rows='0' cols='0'>" + text + "</textarea>";
	document.getElementById(newArea).innerHTML = area;
}
function addElement() {
	  var ni = document.getElementById('kssTextArea');
	  var numi = document.getElementById('kssIntVal');
	  var num = (document.getElementById('kssIntVal').value -1)+ 2;
	  numi.value = num;
	  var newdiv = document.createElement('div');
	  var divIdName = 'my'+num+'Div';
	  newdiv.setAttribute('id',divIdName);
	  ni.appendChild(newdiv);
	  return divIdName;
}

function JSDrawFDTString(ctx) {
	 // Gradient text with shadow
	 var gradient = ctx.createLinearGradient(0,160,0,300);
	 gradient.addColorStop(0, "#0080FF"); 
	 gradient.addColorStop(1, "#FFFFFF");
	 ctx.fillStyle = gradient;
	 ctx.font = '127px "Segoe UI" bold';
	 ctx.fillText("FDT supports HTML5 Canvas", 0, ctx.canvas.top + ctx.canvas.height);
	 ctx.strokeText("FDT supports HTML5 Canvas", 0, ctx.canvas.top + ctx.canvas.height);
	 ctx.restore();
}

function JSCreateWindow(context,x,y,w,h) {
	context.fillStyle = 'rgba(173,216,230,0.5)'; //for pink 255,0,0 :)
	context.fillRect(x,y,w,h);
}

function JSHandleCreateWindow(iWindowHandle,x,y,w,h) {
	if(iWindowHandle == g_FDT_Text_Window_Handle) {
		JSCreateWindow(textContext, x,y,w,h);
	} else {
		JSCreateWindow(mainContext, x,y,w,h);
	}
}

function JSDrawImage(context, imgFile, x, y, width, height, alpha) {
 	var img = new Image();
    img.onload = function() {
        context.globalAlpha = alpha;	//0.5 will make the image transparent, good for debugging
        context.drawImage(img, x, y, width, height);	//scale image based on width and height
    }
    img.src = imgFile;
}

function JSHandleDrawImage(iWindowHandle, lImageID, x, y, width, height) {
	imgFile = JSGetImageSrc(lImageID)
	if(iWindowHandle == g_FDT_Text_Window_Handle) {
// seems like z-index/zIndex do not work for HTML 5 canvas for Chrome 6/FF 4beta5
//		JSDrawImage(textContext, imgFile, x, y, width, height, 0.9)
//	} else {
		JSDrawImage(mainContext, imgFile, x, y, width, height, 1)
	}
}

function JSHandleDrawString(iWindowHandle, x, y, width, height, fontColor, fontBgColor, fontCode, text) {
	if(iWindowHandle == g_FDT_Text_Window_Handle) {
		JSDrawString(textContext, x, y, width, height, fontColor, fontBgColor, fontCode, text)
	} else {
		JSDrawString(mainContext, x, y, width, height, fontColor, fontBgColor, fontCode, text)
	}
}

function JSDrawString(context, x, y, width, height, fontColor, fontBgColor, fontCode, text) {
	debug.info('fontColor ' + fontColor + ' fontBgColor ' + fontBgColor + ' fontCode ' + fontCode)
	//fSize = app.Packages.com.google.code.kss.adapter.fios.api.Graphics.getFontStyle(fontCode)
	debug.debug('colorCode ' + getCanvasColorCode(fontColor) + ' fontCode ' + fontCode)
// 	context.font="normal " + fontCode + "px sans-serif";
//	context.fillStyle = getCanvasColorCode(fontColor);	//text does not appear on top of image!!! :(
//	context.fillText(text, x, y + 12)	//XXX not sure why the text need some y offset!!?? also difficult to implement word wrapping using canvas! :(
	addTextArea(x, y, width, height, fontColor, fontBgColor, fontCode, text);
}
