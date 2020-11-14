function toggleResetPswd(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle() // display:block or none
    $('#logreg-forms .form-reset').toggle() // display:block or none
}

function toggleSignUp(e){
    e.preventDefault();
    $('#logreg-forms .form-signin').toggle(); // display:block or none
    $('#logreg-forms .form-signup').toggle(); // display:block or none
}

// function getCookie(name) {
//     // Split cookie string and get all individual name=value pairs in an array
//     var cookieArr = document.cookie.split(";");
//
//     // Loop through the array elements
//     for(var i = 0; i < cookieArr.length; i++) {
//         var cookiePair = cookieArr[i].split("=");
//
//         /* Removing whitespace at the beginning of the cookie name
//         and compare it with the given string */
//         if(name == cookiePair[0].trim()) {
//             // Decode the cookie value and return
//             return decodeURIComponent(cookiePair[1]);
//         }
//     }
//     // Return null if not found
//     return null;
// }


$(()=>{
    // Login Register Form
    $('#logreg-forms #forgot_pswd').click(toggleResetPswd);
    $('#logreg-forms #cancel_reset').click(toggleResetPswd);
    $('#logreg-forms #btn-signup').click(toggleSignUp);
    $('#logreg-forms #cancel_signup').click(toggleSignUp);

    $("#loginForm").submit(function(e) {
        e.preventDefault();
        console.log("fuck");
        console.log($("#loginForm").serializeArray());
        let formdata = $("#loginForm").serializeArray();
        console.log(formdata[0].value);
        let data = {};
        $(formdata).each(function(index, obj){
            data[obj.name] = obj.value;
        });
        console.log(data);
        $.ajax({
            url : "/login",
            type : 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            data : JSON.stringify({
                "username": formdata[0].value,
                "password": formdata[1].value
            }),
            success : function (response) {
                $("#noSuchUserMsg")[0].style.display = "none";
                document.cookie = "jwt="+response["jwt"];
                console.log(getCookie("jwt"));
                window.location.replace("/");
            },
            error : function (jqXHR, exception) {
                $("#noSuchUserMsg")[0].style.display = "block";
            }
        })
    })

    $("#signupForm").submit(function(e) {
        e.preventDefault();
        console.log($("#signupForm").serializeArray());
        let formdata = $("#signupForm").serializeArray();
        console.log(formdata[0].value);
        let data = {};
        $(formdata).each(function(index, obj){
            data[obj.name] = obj.value;
        });
        console.log(data);
        $.ajax({
            url : "/signup",
            type : 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            data : JSON.stringify({
                "username": formdata[0].value,
                "password": formdata[1].value
            }),
            success : function (response) {
                $("#userExistsMsg")[0].style.display = "none";
                console.log(response);
            },
            error : function (jqXHR, exception) {
                $("#userExistsMsg")[0].style.display = "block";
            }
        })
    })




})