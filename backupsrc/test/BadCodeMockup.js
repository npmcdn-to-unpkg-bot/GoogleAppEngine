// Pre load images for rollover
if (document.images) {
	smile = new Image
	nosmile = new Image

	smile.src = "http://www.quackit.com/pix/smile.gif"
	nosmile.src = "http://www.quackit.com/pix/nosmile.gif"
}


function swapImage(thisImage,newImage) {
	if (document.images) {
		document[thisImage].src = eval(newImage + ".src")
	}
}
<a href="http://www.quackit.com/javascript/image_rollovers.cfm"
onMouseOver="swapImage('jack','smile')"
onMouseOut="swapImage('jack','nosmile')">
<img src="http://www.quackit.com/pix/nosmile.gif"
	width="100"
	height="100"
	border="0"
	alt="Picture of Jack"
	name="jack">
</a>
<a href="javascript:fetchProducts(this)" class="vProducts current" >View Products</a>
