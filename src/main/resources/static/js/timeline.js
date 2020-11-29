// var timelineId = "[[${timeline.getId()}]]"
// var userId = "[[${timeline.getUserId()}]]"
console.log("timelineId", timelineId)
console.log("userId", userId)




function toggleLike(x) {
//     let numberLikes = document.getElementById("like").text
    console.log("Toggle like button + 1 like", numberLikes)
     $.ajax({
            url: `/timeline/` + timelineId + `/like`,
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({userId: userId}),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                console.log(res)
                let newNumberLikes = parseInt(numberLikes) + 1
                console.log("new number of likes", newNumberLikes)
//                 $("#like").val(newNumberLikes.toString())
                document.getElementById("like").value = newNumberLikes.toString()
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    x.classList.toggle("fa-thumbs-down");
}

function toggleUnlike(x) {
    let numberLikes = document.getElementById("unlike").value
    console.log("Toggle liked button - 1 like", numberLikes)
    $.ajax({
        url: `/timeline/` + timelineId + `/unlike`,
        type: 'PUT',
        cache: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({userId: userId}),
        headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
        success: function(res) {
            console.log(res)
            let newNumberLikes = parseInt(numberLikes) - 1
            console.log("new number of likes", newNumberLikes)
            document.getElementById("unlike").value = newNumberLikes.toString()
        },
        error: (xhr, resp, text) => console.log(xhr),
    });
    x.classList.toggle("fa-thumbs-up");
}

$("#commentButton").click(function(){
    console.log("comment button clicked")
    $("#commentForm").toggle();
  });

$("#postComment").submit(function(){
    let commentInput = document.getElementById("comment-input").value
    console.log("comment", commentInput)
    $.ajax({
        url: `/timeline/` + timelineId + `/comment`,
        type: 'POST',
        cache: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({userId: userId, comment: commentInput}),
        headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
        success: function(res) {
            let newComment = "<p>" + commentInput + "</p>"
            console.log("new comment", newComment)
            $(".comment-area").append(newComment)
        },
        error: (xhr, resp, text) => console.log(xhr),
    });
})