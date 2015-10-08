var graphics = new app.Packages.com.google.code.kss.adapter.fios.api.Graphics();
var jvm = new kss.Packages.com.google.code.kss.core.util.JVM();

HG_ERR_NONE = 0
HG_ERR_FAILED = -1
var anyWindow = []

//providing Javascript implementation
function SetJava(j) {
	javaFlag = j
}

function DrawBackground() {
	debug.debug('Graphics.DrawBackground()')
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.drawBackground(0, 0, 1920, 1080) //draw the window 0
}

function InitWindowAttributes() {
	debug.debug('Graphics.InitWindowAttributes()')
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.InitWindowAttributes();
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.initResolution(1920, 1080);	//HDTV
	debug.debug('app.Packages.com.google.code.kss.adapter.fios.api.Graphics.isUpdateDisplay ' + app.Packages.com.google.code.kss.adapter.fios.api.Graphics.isUpdateDisplay())
}

function CreateWindowAttrib() {
	debug.debug('Graphics.CreateWindowAttrib()')
	
	return -1
}

// This is a convenient debug function to dump out the parameters
function d1() {
	debug.debug("iLayerNumber "+iLayerNumber)
	debug.debug(iAttributeID)
	debug.debug(lParentHandle)
	debug.debug(iX)
	debug.debug(iY)
	debug.debug(iWidth)
	debug.debug(iHeight)
}

function CreateWindow(iLayerNumber, iAttributeID, lParentHandle, iX, iY, iWidth, iHeight) {
	debug.debug('Graphics.CreateWindow()')
	if(iLayerNumber != 2 && iLayerNumber < 3) {
		jvm.raiseError('Graphics.CreateWindow layer number can not be less than 3')
	}
	if(iAttributeID === null) {
		iAttributeID = -1	//if null, luajava will fail 	
	}
	
	//iX, iY = handleParent(iLayerNumber, iAttributeID, lParentHandle, iX, iY, iWidth, iHeight)
	//TBD
	//app.Packages.com.google.code.kss.adapter.fios.api.Graphics.CreateWindow(iLayerNumber, iAttributeID, lParentHandle, iX, iY, iWidth, iHeight)
	JSHandleCreateWindow(lParentHandle, iX, iY, iWidth, iHeight)
	
	newWinID = getWindowCount() + 1
	
	lParentHandle = newWinID
	
	return lParentHandle
}

function ClearWindow(o) {
	if(o == g_FDT_Text_Window_Handle) {
//		textContext.clearRect(0, 0, textCanvas.width, textCanvas.height);
		clearElement('kssTextArea')
	} else {
		mainContext.clearRect(0, 0, mainCanvas.width, mainCanvas.height);
	}
	debug.debug('Graphics.ClearWindow(' + o + ')')
}

// This is a convenient debug function to dump out the parameters
function d2() {
	debug.debug("g_Window_Attribute "+g_Window_Attribute)
	debug.debug(g_Window_Handle)
	debug.debug(lImageID)
	debug.debug(x)
	debug.debug(y)
	debug.debug(width)
	debug.debug(height)
	debug.debug(alphaBlending)
}

function DrawImageExByID(g_Window_Attribute, g_Window_Handle, lImageID, x, y, width, height, alphaBlending) {
	debug.debug('Graphics.DrawImageExByID()')
	if(g_Window_Attribute === null) {
		g_Window_Attribute = 1	//if null, luajava will fail 	
	}
	//x, y = handleParent(g_Window_Handle, x, y)
	//x, y = handleParent(-1, -1, g_Window_Handle, x, y, width, height)
	
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.CreateWindow(-1, g_Window_Attribute, g_Window_Handle, x, y, width, height)
	JSHandleDrawImage(g_Window_Handle, lImageID, x, y, width, height)
}

function DrawImageByID(iAttributeID, iWinID, lImageID, x, y, alphaBlending) {
	debug.debug('Graphics.DrawImageByID()')
	if(iAttributeID === null) {
		iAttributeID = -1	//legacy code (if null, luajava will fail)
	}
	//x, y, parentWidth, parentHeight = handleParent(-1, -1, iWinID, x, y, -1, -1)
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.CreateWindow(-1, iAttributeID, -1, x, y, parentWidth*.7, 50)
}

function UpdateDisplay() {
	debug.debug('Graphics.UpdateDisplay()');
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.setUpdateDisplay(true);
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.updateDisplay();
	//debug.debug('app.Packages.com.google.code.kss.adapter.fios.api.Graphics.isUpdateDisplay ' + app.Packages.com.google.code.kss.adapter.fios.api.Graphics.isUpdateDisplay())
	debug.debug('total window = ' + getWindowCount());
}

// This is a convenient debug function to dump out the parameters
function d3() {
	debug.debug(iAttributeID)
	debug.debug(iWinID)
	debug.debug(x)
	debug.debug(y)
	debug.debug(width)
	debug.debug(height)
	debug.debug(pFontColor)
	debug.debug(pFontBgColor)
	debug.debug(wFontCode)
	debug.debug(dwFlags)
	debug.debug(szText)
}

function DrawString(iAttributeID, iWinID, x, y, width, height, pFontColor, pFontBgColor, wFontCode, dwFlags, szText) {
	debug.debug('Graphics.DrawString() text '+ szText + ' iWinID ' + iWinID)
	debug.debug('pFontBgColor ' + pFontBgColor + ' wFontCode ' + wFontCode)
	if(iAttributeID === null) {
		iAttributeID = -1	//if null, luajava will fail 	
	}
	if(iWinID === null) {
		iWinID = -1	//if null, luajava will fail 	
	}
	//x, y = handleParent(-1, -1, iWinID, x, y, width, height)
	app.Packages.com.google.code.kss.adapter.fios.api.Graphics.DrawString(iAttributeID, iWinID, x, y, width, height, pFontColor, pFontBgColor, wFontCode, dwFlags, szText)
	JSHandleDrawString(iWinID, x, y, width, height, pFontColor, pFontBgColor, wFontCode, szText)
}

function SetWindowPosition(iWinID, x, y) {
	if(iWinID === null) {
		iWinID = -1	//if null, luajava will fail 	
	}
	debug.debug('iWinID '+iWinID)
	debug.debug('x '+x)
	debug.debug('y '+y)
	//TBD
	//x, y, w, h = getWindow(iWinID)
	debug.debug('width '+w)
	debug.debug('height '+h)	
	//app.Packages.com.google.code.kss.adapter.fios.api.Graphics.CreateWindow(-1, -1, -1, x, y, w, h)
}

function GetBitmapList(param1, param2) {
}

function SetWindowVisibility(iWinID, bVisible) {
}

Graphics = new Object()
Graphics.javaFlag = false
Graphics.SetJava = SetJava
Graphics.ClearWindow = ClearWindow
Graphics.CreateWindow = CreateWindow
Graphics.CreateWindowAttrib = CreateWindowAttrib
Graphics.DrawBackground = DrawBackground
Graphics.DrawImageByID = DrawImageByID
Graphics.DrawImageExByID = DrawImageExByID
Graphics.DrawString = DrawString
Graphics.GetBitmapList = GetBitmapList
Graphics.InitWindowAttributes = InitWindowAttributes
Graphics.SetWindowPosition = SetWindowPosition
Graphics.SetWindowVisibility = SetWindowVisibility
Graphics.UpdateDisplay = UpdateDisplay
