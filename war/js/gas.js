var showLog = false;
var gManageColMainTitle;
var gManageShoMainTitle;
var gManageChaMainTitle;
function importGSS(json) {
    for (i = 0; i < json.feed.entry.length; i++) {
    entry = json.feed.entry[i];
    col1 = entry.gsx$index && entry.gsx$index.$t;
    col2 = entry.gsx$_cokwr && entry.gsx$_cokwr.$t;
    col3 = entry.gsx$_cpzh4 && entry.gsx$_cpzh4.$t;
    col4 = entry.gsx$_cre1l && entry.gsx$_cre1l.$t;
    col5 = entry.gsx$_chk2m && entry.gsx$_chk2m.$t;
    col6 = entry.gsx$_ciyn3 && entry.gsx$_ciyn3.$t;
    if (col1 && col2 && col3 && col4 && col5) {
    showLog && window.console && console.log('[' + (i + 1) + '] = ' + '[' + col1 + '] ' + '[' + col2 + '] ' + '[' + col3 + '] ' + '[' + col4 + '] ' + '[' + col5 + '] ' + '[' + col6 + '] ');
    if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col5 === 'text'
    ) {
    if (col4 === 'notice') {
    $('#notice').text(col6);
    }
    else if (col4 === 'firsttext') {
    $('#firsttext').text(col6);
    }
    else if (col4 === 'motto') {
    $('#motto').text(col6);
    }
    else if (col4 === 'title') {
    $(document).attr("title", col6);
    }
    else if (col4 === 'footer') {
    $("#footer").text(col6);
    }
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'brand' &&
    col5 === 'background'
    ) {
    $(".login-form").css("background", "url(" + col6 + ") no-repeat center center");
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'brand' &&
    col5 === 'background-size'
    ) {
    $(".login-form").css("background-size", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'footer' &&
    col5 === 'font-size'
    ) {
    $("#footer").css("font-size", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo1' &&
    col5 === 'background'
    ) {
    $("#logo1").css("background", "url(" + col6 + ") no-repeat center");
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo1' &&
    col5 === 'top'
    ) {
        $("#logo1").css("top", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo1' &&
    col5 === 'left'
    ) {
        $("#logo1").css("left", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo1' &&
    col5 === 'width'
    ) {
        $("#logo1").css("width", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo1' &&
    col5 === 'height'
    ) {
        $("#logo1").css("height", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo2' &&
    col5 === 'background'
    ) {
        $("#logo2").css("background", "url(" + col6 + ") no-repeat center");
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo2' &&
    col5 === 'top'
    ) {
        $("#logo2").css("top", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo2' &&
    col5 === 'left'
    ) {
        $("#logo2").css("left", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo2' &&
    col5 === 'width'
    ) {
        $("#logo2").css("width", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'logo2' &&
    col5 === 'height'
    ) {
        $("#logo2").css("height", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'firsttext' &&
    col5 === 'top'
    ) {
        $("#firsttext").css("top", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'firsttext' &&
    col5 === 'left'
    ) {
        $("#firsttext").css("left", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'brand' &&
    col5 === 'width'
    ) {
        $(".login-form").css("width", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'brand' &&
    col5 === 'height'
    ) {
        $(".login-form").css("height", col6);
    } else if (col1 === 'home' &&
        col2 === 'index' &&
        col3 === 'main' &&
        col4 === 'input#username' &&
        col5 === 'style'
    ) {
        $("input#username").css("style", col6);
    } else if (col1 === 'home' &&
        col2 === 'index' &&
        col3 === 'main' &&
        col4 === 'input#password' &&
        col5 === 'style'
    ) {
        $("input#password").css("style", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'motto' &&
    col5 === 'top'
    ) {
        $("#motto").css("top", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'motto' &&
    col5 === 'left'
    ) {
    $("#motto").css("left", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'form' &&
    col4 === 'h1' &&
    col5 === 'color'
    ) {
    $("h1").css("color", col6);
    } else if (col1 === 'home' &&
        col2 === 'index' &&
        col3 === 'form' &&
        col4 === 'h2' &&
        col5 === 'color'
    ) {
    $("h2").css("color", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'form' &&
    col4 === 'p' &&
    col5 === 'color'
    ) {
    $("p").css("color", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'form' &&
    col4 === 'a.scroll' &&
    col5 === 'color'
    ) {
    $("a.scroll").css("color", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'form' &&
    col4 === 'a.scroll:hover' &&
    col5 === 'color'
    ) {
    $("a.scroll:hover").css("color", col6);
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'facebook' &&
    col5 === 'active' &&
    col6 === 'TRUE'
    ) {
    $('.facebook').show();
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'twitter' &&
    col5 === 'active' &&
    col6 === 'TRUE'
    ) {
    $('.twitter').show();
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col4 === 'googleplus' &&
    col5 === 'active' &&
    col6 === 'TRUE'
    ) {
    $('#gSignInWrapper').show();
    $('#customBtn').show();
    $('#gSignInWrapper').css("display", "inline-block");
    $('#customBtn').css("display", "inline-block");
    initGoooglePlus();  //the tag needs to be shown first before this init is called???
    } else if (col1 === 'home' &&
    col2 === 'index' &&
    col3 === 'main' &&
    col5 === 'color'
    ) {
    if (col4 === 'background') {
    $('body').css('background-color', col6);
    } else if (col4 === 'brand') {
    $('#brand').css('color', col6);
    } else if (col4 === 'notice') {
    $('#notice').css('color', col6);
    } else if (col4 === 'brand') {
    $('#brand').css('color', col6);
    } else if (col4 === 'motto') {
    $('#motto').css('color', col6);
    } else if (col4 === 'footer') {
    $('#footer').css('color', col6);
    }
    }
    }
    }
    }
