$(function() {

    $("body").on("click", "#accBtn", function() {
        console.log($(this).attr("data-friend-name")+"clicked")
        let friendName = $(this).attr("data-friend-name");
        console.log(friendName);

        $.ajax({
            url: '/user/friend?acceptedFriendName='+friendName,
            type: 'PUT',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                location.reload();
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });

    $("body").on("click", "#rejBtn", function() {
        console.log($(this).attr("data-friend-name")+"clicked")
        let friendName = $(this).attr("data-friend-name");
        console.log(friendName);

        $.ajax({
            url: '/user/friend?rejectFriendName='+friendName,
            type: 'DELETE',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                location.reload();
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });

})