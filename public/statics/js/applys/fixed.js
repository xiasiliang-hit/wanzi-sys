$(window).scroll(function(event) {
    fixed_left();
});
function fixed_left(){
    var val=$(document).scrollTop();
    if(val<0){
        $(".fix_left").css({"position":"static"});
    }
    else if(($(document).height()-$(document).scrollTop()-$('.footer-bg').height()-$('#ma-to').height())<=$('.fix_left').height())
    {
        $('.fix_left').css({'position':'absolute','top':$(document).height()-$('.footer-bg').height()-$('.fix_left').height()-$('#ma-to').height()-31})
    }
    else
    {
        $(".fix_left").css({"position":"fixed","top":"85px"});
    }
}
fixed_left();