$(function() {
    let srcShelf;
    $(".bookshelf #book").draggable({
        revert: "invalid",
        start: function (event, ui) {
            //NEW
            srcShelf = $(this).parents('.bookshelf').find('.heading').text();
            
        },
        stack: ".bookshelf #book",
    });

    $(".bookshelf").droppable({ 
        drop:function(ev, ui){ 
            let dstShelf = $(this).find('.heading').text()
            let bookId = ui.draggable.attr("data-book-id")


            $.ajax({
                url: '/book/shelf/' + dstShelf,
                type: 'PUT',
                cache: false,
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify({srcShelf: srcShelf, bookId: bookId}),
                headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
                success: function(res) {
                    location.reload();
                },
                error: (xhr, resp, text) => console.log(xhr),
            });
        } 
    });
//     $(".bookshelf #book").on("click", function() {
//         let bookId = ui.draggable.attr("data-book-id")
//         $.ajax({
//             type: "GET",
//             dataType: "json",
//             url: "/book?id="+bookId,
//             error: function(e){
//                 alert("Submit failed"+e);
//             }
//         })
//     })
});