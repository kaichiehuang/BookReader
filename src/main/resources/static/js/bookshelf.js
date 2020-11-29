$(function() {
    let srcShelf;

    //setup popovers
    let bookOnSelf = document.getElementsByClassName("bookOnSelf");
    for (let i = 0; i < bookOnSelf.length; i++) {

        // console.log(bookOnSelf[i].getAttribute("data-book-page"));
        // console.log(bookOnSelf[i].getAttribute("data-book-id"));
        // console.log(bookOnSelf[i].parentElement.parentElement.getElementsByClassName("heading")[0].innerHTML);
        let bookPage = parseInt(bookOnSelf[i].getAttribute("data-book-page"), 10);
        let bookId = parseInt(bookOnSelf[i].getAttribute("data-book-id"), 10);
        let shelfName = bookOnSelf[i].parentElement.parentElement.getElementsByClassName("heading")[0].innerHTML;

        let progress = -1;
        let readPages = -1;
        //get progress
        $.ajax({
            url: '/book/progress?bookId='+bookId+'&bookshelf='+shelfName,
            type: 'GET',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            success: function(res) {
                progress = Math.round(JSON.parse(res)['progress']);
                readPages = Math.round(progress * bookPage / 100);
                console.log(progress)
                bookOnSelf[i].getElementsByClassName("c100")[0].classList.add("p"+progress);
                bookOnSelf[i].getElementsByClassName("popover_content_wrapper")[0].getElementsByTagName("span")[0].innerHTML = ''+progress+'%';
                bookOnSelf[i].getElementsByClassName("popover_content_wrapper")[0].getElementsByTagName("h3")[0].innerHTML = ''+readPages+' out of '+bookPage+' pages read';
                $(bookOnSelf[i].getElementsByTagName("a")[0]).popover({
                    toggle: 'click hover',
                	container: 'body',
                    html : true,
                    content: function() {
                        return $(bookOnSelf[i].getElementsByClassName("popover_content_wrapper")[0]).html();
                    },
                    sanitize: false,
                });
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    }

    $("body").on("click", "#update-page", function() {
        let pageNum = $('.popover-body').find('#page-input').val();
        console.log(pageNum);
        let bookId = $(this).attr("data-book-id");
        console.log(bookId);
        //update progress
        $.ajax({
            url: '/book/progress',
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            data: JSON.stringify({curPage: pageNum, bookId: bookId}),
            success: function(res) {
                location.reload();
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });
    
    $("body").on("click", "#recommend-book", function() {
        let friendName = $('.popover-body').find('#recomendee-input').val();
        let bookId = $(this).attr("data-book-id");
        $.ajax({
            url: '/book/'+friendName,
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            data: JSON.stringify({bookId: bookId}),
            success: function(res) {
            	if (res.msg == "success") {
            		location.reload();
                	alert("Recommendation Success")
            	}
            	else {
            		location.reload();
            		alert(res.msg)
            	}
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });
    
    $("body").on("click", "#exclude-book", function() {
        let friendName = $('.popover-body').find('#recomendee-input').val();
        let bookId = $(this).attr("data-book-id");
        $.ajax({
            url: '/book/exclude',
            type: 'POST',
            cache: false,
            contentType: 'application/json; charset=utf-8',
            headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
            data: JSON.stringify({bookId: bookId}),
            success: function(res) {
            	location.reload();
            },
            error: (xhr, resp, text) => console.log(xhr),
        });
    });

    $(".book-a").click(function(e){
        e.preventDefault();
    })
    
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

            // console.log(bookId); 
            // console.log(srcShelf);
            // console.log(dstShelf)
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
});