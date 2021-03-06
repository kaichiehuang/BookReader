$(function(){

    // $(".bookshelfChoice").unbind('click').bind('click', function (event) {
    //     let bookshelf = $(this).text();
    //
    //     
    //
    //     // let id = $(this).closest()
    //     // console.log(title);
    //     // console.log(author);
    //     // console.log(pages);
    //     // console.log(description);
    //     // console.log(bookIdentifier)
    //     // console.log("BookIdentifier", bookIdMap[title+pages])
    //     // let bookIdentifier = bookIdMap[title+pages]
    //
    //     let data = {
    //         bookIdentifier: bookIdentifier,
    //         title: title,
    //         author: author,
    //         page: parseInt(pages),
    //         description: description,
    //         link: link
    //     };
    //
    //     console.log(data)
    //
    //     $.ajax({
    //         url: '/book/shelf/' + bookshelf.toLowerCase(),
    //         type: 'POST',
    //         cache: false,
    //         contentType: 'application/json; charset=utf-8',
    //         data: JSON.stringify(data),
    //         headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
    //         success: function(res) {
    //             window.location.replace("/book/shelf");
    //         },
    //         error: (xhr, resp, text) => console.log(xhr),
    //     });
    // })
    $('.alert').hide()
    $("body").on("click","#addBookBtn", function(){
        
        let link= $(this).closest("#bookRow").find("#bookImg").attr('src');
        let title = $(this).closest("#bookRow").find("#title").text();
        let author = $(this).closest("#bookRow").find("#author").text();
        let pages = $(this).closest("#bookRow").find("#pages").text();
        let description = $(this).closest("#bookRow").find("#description").text();
        let bookIdentifier = $(this).attr('data-book-identifier');
      
        let data = {
            bookIdentifier: bookIdentifier,
            title: title,
            author: author,
            page: parseInt(pages),
            description: description,
            link: link
        };

        console.log(data)

        $.ajax({
            // url: '/book/shelf/' + bookshelf.toLowerCase(),
            url: '/book/shelf/addBook',
            type: 'PUT',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                $('.alert').fadeTo(2000, 500).slideUp(500, function() {
                    $('.alert').slideUp(500);
                })
                window.location.replace("/book/shelf");
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
        // $.ajax({
        //     url: '/book/shelf/' + defaultShelf,
        //     type: 'POST',
        //     cache: false,
        //     contentType: 'application/json; charset=utf-8',
        //     data: JSON.stringify({bookId:bookId}),
        //     headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
        //     success: function(res) {
        //         console.log(bookId)
        //
        //     },
        //     error: (xhr, resp, text) => console.log(xhr),
        // });
    })
})
