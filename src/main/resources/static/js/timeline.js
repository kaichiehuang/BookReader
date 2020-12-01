function toggleThumb(currentStatus, timelineId, userId, likes) {
    console.log("toggling: ", currentStatus, timelineId, userId, likes)
    if (!currentStatus) {
        // we want to like;
        $.ajax({
            url: `/timeline/` + timelineId + `/like`,
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({userId: userId}),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function (res) {
                likes++;
                document.querySelector(`#${'thumb-' + timelineId}`).innerHTML =
                    `<i class="fas fa-thumbs-down" onclick = "toggleThumb(true, ${timelineId}, ${userId}, ${likes})"> ${likes}</i>`
                // after like, we show thumb down button with count, so that user can unlike

            }
        })
    } else {
        // we want to unlike;
        $.ajax({
            url: `/timeline/` + timelineId + `/unlike`,
            type: 'PUT',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({userId: userId}),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function (res) {
                likes--;

                document.querySelector(`#${'thumb-' + timelineId}`).innerHTML =
                    `<i class="fas fa-thumbs-up" onclick = "toggleThumb(false, ${timelineId}, ${userId}, ${likes})"> ${likes}</i>`
                // after unlike, we show thumb up button with count, so that user can like
            }
        })
    }
}

function showForm(timelineId){
    $('#comment-form-' + timelineId).show()
}
function cancelComment(timelineId){
    $('#comment-form-' + timelineId).hide()
}
function postComment(timelineId, userId){
    console.log(timelineId, userId)

    let commentInput = document.getElementById("comment-input-" +timelineId).value
    console.log("comment", commentInput)
    $.ajax({
        url: `/timeline/` + timelineId + `/comment`,
        type: 'POST',
        cache: false,
        contentType: 'application/json; charset=utf-8',
        data: JSON.stringify({userId: userId, comment: commentInput}),
        headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
        success: function(res) {

            let newComment =
                `<h5>${res.username} </h5> <span style = "float: right;"> ${res.timestamp}</span>
                 <p> ${commentInput}</p>`
            console.log("new comment", newComment)
            document.querySelector(`#${'comment-area-' + timelineId}`).innerHTML += newComment
            document.getElementById("comment-input-" +timelineId).value = ""
        },
        error: (xhr, resp, text) => console.log(xhr),
    });
}