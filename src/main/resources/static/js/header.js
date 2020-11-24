$(function(){
    $('.nav-link').each(function(){
        if ($(this).prop('href') == window.location.href) {
            $(this).parents('li').addClass('active');
        }
    });
});