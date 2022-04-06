$(document).ready(function(){
    $("#login-form").submit(function(e) {
        e.preventDefault();

       var badCreds = function () {
           var inputs = document.getElementsByName("employeeNumber")
           if (inputs.length > 0) {
               inputs[0].classList.add(["is-invalid"]);
           }
           $("#error").show();
           return false;
       };

        let loginId = $("#loginId").val().toUpperCase();
        let loginPwd = $("#loginPwd").val();
        var loginIdInitial = loginId.slice(0, 3);
        var valid = false;
        if(loginIdInitial !== "EMP") {
          badCreds();
        } else{
            valid = true;
        }

        if(loginId !== "" && loginPwd !== "" && valid) {

            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({"userID": loginId, "password" : loginPwd}),
                type: 'POST',
                url: '/login/request',
                passwordType: true,
                success: function(data, response){
                    if(response === "success") {
                        sessionStorage.setItem('userId', loginId);
                        sessionStorage.setItem('departmentId', data.departmentId);
                        sessionStorage.setItem('userType', data.userType);
                    }

                    // Get userId from session storage which will be used in request body of APIs
                    let userId = sessionStorage.getItem('userId');
                    if (data.userType === "ADMIN" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/admin.html");
                    } else if (data.userType === "SUPERVISOR" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/supervisorHome.html");
                    } else if (data.userType === 'STAFF' && data.status === 'SUCCESS'){
                        $(location).attr('href',"/views/staffHome.html");
                    } else if (data.userType === 'INTERN' && data.status === 'SUCCESS') {
                        $(location).attr('href', "/views/staffHome.html");
                    } else if (data.status === 'INCORRECT_PASSWORD'){
                        //Respond with Error
                        $("#informFailure").modal('show');
                    } else {
                        $("#invalid").modal('show');
                    }
                },error: function(response) {
                     console.log("Error status", response.status, "Error text", response.statusText);
                 }
            });
        }
    });

});
