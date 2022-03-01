$(document).ready(function(){
    $("#login-form").submit(function(e) {
        e.preventDefault();
        let loginId = $("#loginId").val().toUpperCase();
        let loginPwd = $("#loginPwd").val();
        var loginIdInitial = loginId.slice(0, 3);
//        if(loginIdInitial != "EMP") {
//            alert("User name should start with 'emp'");
//            return;
//        }
        if(loginId !== "" && loginPwd !== "" ) {
            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({"userID": loginId, "password" : loginPwd}),
                type: 'POST',
                url: '/login/request',
                passwordType: true,
                success: function(data, response){
                    console.log(response)
                    if (data.userType === "ADMIN" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/admin.html");
                    } else if (data.userType === "SUPERVISOR" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/supervisor.html");
                    } else if (data.userType === 'STAFF' && data.status === 'SUCCESS'){
                        $(location).attr('href',"/views/staff.html");
                    } else if (data.status === 'INCORRECT_PASSWORD'){
                        //Respond with Error
                        alert("Incorrect Password for ID: " + loginId);
                    } else {
                        alert("Invalid Credentials");
                    }
                },error: function(response) {
                     console.log("Error status", response.status, "Error text", response.statusText);
                 }
            });
        }
    });

});
