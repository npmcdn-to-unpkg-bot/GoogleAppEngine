var AppIframe = React.createClass({
    render: function() {
        if(this.props.show || !this.props.hide) {
            return (
                <Iframe {...this.props} />
            );
        } else {
            return null;
        }
    }
});
