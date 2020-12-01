function getCookie(name) {
    // Split cookie string and get all individual name=value pairs in an array
    var cookieArr = document.cookie.split(";");

    // Loop through the array elements
    for(var i = 0; i < cookieArr.length; i++) {
        var cookiePair = cookieArr[i].split("=");

        /* Removing whitespace at the beginning of the cookie name
        and compare it with the given string */
        if(name == cookiePair[0].trim()) {
            // Decode the cookie value and return
            return decodeURIComponent(cookiePair[1]);
        }
    }
    // Return null if not found
    return null;
}

$(function() {
    $.ajax({
        url: '/user/requests',
        type: 'GET',
        cache: false,
        contentType: 'application/json; charset=utf-8',
        headers: {'Authorization': 'Bearer ' + getCookie("jwt")},
        success: function(res) {
            let json = JSON.parse(res);
            let requestCnt = json['requests'].split(',').length;
            if (json['requests'].split(',') == "[]") {
                requestCnt = 0;
            }
            document.getElementById("requestCnt").innerHTML = requestCnt.toString();
        },
        error: (xhr, resp, text) => console.log(xhr),
    });
})