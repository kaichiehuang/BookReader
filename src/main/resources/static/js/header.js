$(function(){
    $('.nav-link').each(function(){
        if ($(this).prop('href') == window.location.href) {
            $(this).parents('li').addClass('active');
        }
    });

    // $.ajax({
    //     url: '/user/requests',
    //     type: 'GET',
    //     cache: false,
    //     contentType: 'application/json; charset=utf-8',
    //     headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
    //     success: function(res) {
    //         console.log("yoyoyo"+res);
    //     },
    //     error: (xhr, resp, text) => console.log(xhr),
    // });

});