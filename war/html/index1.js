//define("index1", function () { //begin of AMD style - commented out due to Parse is not defined issue
    rgBuild = '0001';
	gMovieURL = '/mcrud/movie.html';

//Parse.initialize("Ld70ODLxjXFkhhRux6kQqVCiJ4rQeXU8dISafNJa", "dLHhDNKnLimXHzSzxvQcGuwle5iLwnn3bFahDS9q");
//window.console && console.log("Parse initialized (index1.js).");

    function handleUser(type) {
        $("#status").show();
        $('#warning').hide();

        if(type === 'signup') {
            var isNotABot = false;

            //https://code.google.com/p/recaptcha/wiki/HowToSetUpRecaptcha
            //TBD - user id and password restrictions (https://parse.com/questions/user-id-andor-password-restrictions)
            //For look and feel, change it at main-index1.js (https://developers.google.com/recaptcha/docs/customization)
            //=== handled by server side now
            $.ajax({
                type: "POST",
                url: "/jsp/check_reCAPTCHA.jsp",  // read the action attribute of the form
                data: $('#captchaForm').serialize(),  // what data should go there?
                async: false,
                success: function(data) {
                    if(window.console) console.log("captcha check returned by server = '" + data + "'");
                    if(data.indexOf("OK") > -1) {
                        isNotABot = true;
                    }
                    $("#status").hide();
                }
            });

            if(isNotABot) {
                //source: https://parse.com/questions/user-signup-authentication-using-javascript-throws-error-1-with-no-message
                var user = new Parse.User();
                user.set("username", $('#username').val());
                user.set("password", $('#password').val());
                //user.set("email", $email);

                // other fields can be set just like with Parse.Object
                //user.set("phone", $phone);

                user.signUp({ ACL: new Parse.ACL() }, {
                    success: function(user) {
                        alert('Sucess! You can start using login id [' + user.getUsername() + "] right away.");
                    },
                    error: function(user, error) {
                        window.console && console.dir(error);
                        window.console && console.dir(user);
                        // Show the error message somewhere and let the user try again.
                        alert("Sign-up Error: " + error.message + " Code: " + error.code + " User [" + user + "]");
                    }
                });

//                Parse.User.signUp($('#username').val(), $('#password').val(), { ACL: new Parse.ACL() }, {
//                    success: function(user) {
//                        $("#status").hide();
//                        alert($('#username').val() + " signed up successfully!");
//                    },
//                    error: function(user, error) {
//                        $("#status").hide();
//                        alert('Error ' + error.message + ": Name entered was [" + $('#username').val() + "]");
//                    }
//                });
            } else {
                //need to refresh reCAPTCHA thus use KISS approach
                window.location.reload();
                $('#warning').show();
                alert($('#warning').text());
            }
        }
        if(window.console) console.log("handleUser " + rgBuild);
    }

    //http://stackoverflow.com/questions/979662/how-to-detect-pressing-enter-on-keyboard-using-jquery
    $(document).keypress(function(event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        //alert('keycode [' + keycode + ']');
        if(keycode === 13) {
            $("#status").show();
            $("#status").val('signing up ...');
            handleUser('signup');
        }
    });

    $("#signMeUpNow").click(function() {
        handleUser('signup');
    });


    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function Index1(one, two) {
//        this.one = one;
//        this.two = two;
//    };
//}); //end of AMD style