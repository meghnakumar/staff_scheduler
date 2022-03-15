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

        /* UNDER CONSTRUCTION var barProgress = function () {
            var i = 0;
            var re = $.Deferred;
            if (i === 0) {
                $('#bar').show();
                i = 1;
                var elem = document.getElementById("bar");
                var width = 10;

                 setTimeout(   function frame() {
                     var id = setInterval(frame, 10);
                     if (width >= 100) {
                         clearInterval(id);
                         i = 0;
                     } else {
                         width++;
                         elem.style.width = width + "%";
                     }
                 }, Math.random() * 4000)
            }

            return $.Deferred(function (def) {
                $.when(re).done(function () {
                    def.resolve();
                });
            });
        }*/

        if(loginId !== "" && loginPwd !== "" && valid) {

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
                        $("#invalid").modal('show');
                    }
                },error: function(response) {
                     console.log("Error status", response.status, "Error text", response.statusText);
                 }
            });
        }
    });

});
