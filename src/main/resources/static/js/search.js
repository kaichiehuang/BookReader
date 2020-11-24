$(function(){
    $(".bookshelfChoice").unbind('click').bind('click', function (event) {
        let bookshelf = $(this).text();

        let title = $(this).closest("#bookRow").find("#title").text();
        let author = $(this).closest("#bookRow").find("#author").text();
        let pages = $(this).closest("#bookRow").find("#pages").text();
        let description = $(this).closest("#bookRow").find("#description").text();
        let id = $(this).closest()
        console.log(title);
        console.log(author);
        console.log(pages);
        console.log(description);
        console.log(bookIdentifier)

        let data = {
            name: title,
            author: author,
            page: pages,
            description: description
        };

        $.ajax({
            url: '/book/shelf/' + bookshelf.toLowerCase(),
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                window.location.replace("/book/shelf");
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });
})