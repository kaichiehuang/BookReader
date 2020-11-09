$(function() {
    let src_shelf;
    $(".bookshelf #book").draggable({
        revert: "invalid",
        start: function (event, ui) {
            //NEW
            src_shelf = $(this).parents('.bookshelf').find('.heading').text();
            
        },
        stack: ".bookshelf #book"
    });

    $(".bookshelf").droppable({ 
        drop:function(ev, ui){ 
            let dst_shelf = $(this).find('.heading').text()
            let book_id = ui.draggable.attr("data-book-id")

            // console.log(book_id); 
            // console.log(src_shelf);
            // console.log(dst_shelf)

            $.ajax({
                url: '/book/shelf/' + dst_shelf,
                type: 'PUT',
                cache: false,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({src_shelf: src_shelf, book_id: book_id}),
                headers: {'Authorization': 'Bearer '},
                success: function(res) {
                    location.reload();
                },
                error: (xhr, resp, text) => console.log(xhr),
            });
        } 
    }); 
});