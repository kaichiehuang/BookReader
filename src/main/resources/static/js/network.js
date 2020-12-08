$(function() {

    $("body").on("click", "#reqBtn", function() {
        console.log($(this).attr("data-friend-name")+"clicked")
        let friendName = $(this).attr("data-friend-name");
        console.log(friendName);

        $.ajax({
            url: '/user/friend',
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            data: JSON.stringify({requestedFriendName: friendName}),
            success: function(res) {
                location.reload();
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });

})