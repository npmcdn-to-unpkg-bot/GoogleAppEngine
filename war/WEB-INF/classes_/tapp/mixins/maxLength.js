var MaxLength = Class.create();
MaxLength.prototype = {
	initialize: function(id, counterId, max) {
		this.max = max;
		this.element = $(id);
		this.element.observe('keyup', this.keyup.bindAsEventListener(this));
		this.counterElement = $(counterId);
		this.updateCounter();		
	},
	keyup: function(event) {
		if (this.element.value.length > this.max) {
			this.element.value = this.element.value.substring(0, this.max);
		}
		this.updateCounter();
	},
	updateCounter: function() {
		var currentLength = this.element.value.length;
		this.counterElement.value = (this.max - currentLength);
	}
}