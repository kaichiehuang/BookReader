let rating = $("#ratingInput").value()
let review = $("#commentInput").text()

function review() {
        $.ajax({
            url: '/books/' + bookIdentifier + "/review",
            type: "POST",
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify({rating: rating, review: review, title: bookTitle,
            page: bookPage, author: bookAuthor, cover: bookCover, description: bookDescription}),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
//                 location.reload();
                console.log('success')
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    }
