$( document ).ready(function() {
    console.log( "ready!" );
    let userId = sessionStorage.getItem('userId');
    console.log("userID--", userId );
    // $("#user-welcome").text("Welcome userId")


    $.ajax({
        contentType: 'application/json',
        data:{'employeeNumber': sessionStorage.getItem('userId')},
        type: 'GET',
        url: '/staff/get/info',
        success: function(data, response){
            var text = response;
            var employeeDetails = data["response"];
            var name = "Welcome " + employeeDetails["firstName"] + " " + employeeDetails["lastName"];
            console.log(employeeDetails)
            $("#employee-name").text(name);
            $("#employee-id").text(employeeDetails["employeeNumber"]);
            $("#dept-id").text(employeeDetails["departmentId"]);
            $("#email-id").text(employeeDetails["emailId"]);
            $("#hours").text(employeeDetails["maxAvailabilityHours"]);
            $("#mobile-number").text(employeeDetails["phoneNumber"]);
            $("#joining-date").text(employeeDetails["dateOfJoining"]);
        },
        error: function (response){
            console.log("FAILED");
            console.log(response);
        }
    });

});