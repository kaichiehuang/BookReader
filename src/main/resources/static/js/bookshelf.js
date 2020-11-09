$(function() {
    let srcShelf;
    $(".bookshelf #book").draggable({
        revert: "invalid",
        start: function (event, ui) {
            //NEW
            srcShelf = $(this).parents('.bookshelf').find('.heading').text();
            
        },
        stack: ".bookshelf #book"
    });

    $(".bookshelf").droppable({ 
        drop:function(ev, ui){ 
            let dstShelf = $(this).find('.heading').text()
            let bookId = ui.draggable.attr("data-book-id")

            // console.log(bookId); 
            // console.log(srcShelf);
            // console.log(dstShelf)

            $.ajax({
                url: '/book/shelf/' + dstShelf,
                type: 'PUT',
                cache: false,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({srcShelf: srcShelf, bookId: bookId}),
                headers: {'Authorization': 'Bearer '},
                success: function(res) {
                    location.reload();
                },
                error: (xhr, resp, text) => console.log(xhr),
            });
        } 
    }); 
});