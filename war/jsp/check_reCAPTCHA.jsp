<%@ page import="net.tanesha.recaptcha.ReCaptchaImpl" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaResponse" %>
<%
	String remoteAddr = request.getRemoteAddr();
	ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
    String hostname = request.getServerName();

    String realPrivateKey = null;
    System.out.println("1.0reCAPTCHA private key = [" + realPrivateKey + "]");
    if(hostname.indexOf("radiant") > -1) {
        realPrivateKey = "6LeZ7OcSAAAAAFH_AttVtTJwY-f3Bg-tZ_IuR79L";
    }
    else
    if(hostname.indexOf("cbiit") > -1 || hostname.indexOf("cadsr") > -1) {
        realPrivateKey = "6Ld4gOYSAAAAAH94C6nMhn2p0TlL0TtnuLVXS414";
    }
    else
    if(hostname.indexOf("hudoone") > -1) {
        realPrivateKey = "6LfNgeESAAAAACd10oSkMyArG2y5Icf7b2fWk1Rf";
    }
    else
    if(hostname.indexOf("service") > -1) {
        realPrivateKey = "6LfOiMASAAAAAJ_heOGly7Tmj3vaFBEeZ1HamcRV";
    }
    else
    if(hostname.indexOf("2-") > -1) {
        realPrivateKey = "6LeiHegSAAAAAFMFLzMc3r08ugSad1cezY3rqW8f";
    }
    else
    if(hostname.indexOf("oo.tv") > -1) {
        realPrivateKey = "6Ld7s-4SAAAAAJiNSV0OI19dm9WnyMauDOnVJEZ0";
    }
    else
    if(hostname.indexOf("localhost") > -1) {
        realPrivateKey = "6LckguYSAAAAAMpSPydbai26jUkyNIrsMBTAP62H";
    }
    System.out.println("2.0reCAPTCHA private key set to [" + realPrivateKey + "] based on host [" + hostname + "]");

	reCaptcha.setPrivateKey(realPrivateKey);
	String challenge = request.getParameter("recaptcha_challenge_field");
	String uresponse = request.getParameter("recaptcha_response_field");
	ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);
	if (reCaptchaResponse.isValid()) {
	  //out.print("Answer was entered correctly!");
	  out.print("OK");
	} else {
	  //out.print("Answer is wrong");
	}
%>