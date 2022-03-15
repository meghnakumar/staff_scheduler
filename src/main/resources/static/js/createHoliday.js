$(document).ready(function(){
    $("#holiday-form").submit(function(e) {
    e.preventDefault();

        var badCreds = function () {
            var inputs = document.getElementsByName("employeeNumber")
            if (inputs.length > 0) {
                inputs[0].classList.add(["is-invalid"]);
            }
            $("#error").show();
            var valid = false;
            return false;
        };

    let formData = {};
    var valid = false;
    var values = $("#holiday-form :input").serializeArray();
    values.map( input => formData[input.name] = input.value);

    if(!formData.endDate){
        formData["endDate"] = endDate
        formData[endDate] = formData.startDate;
    }

    if(formData.holidayTitle === ''){
        badCreds();
    }  else{
        valid = true;
    }

    if(valid){

        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            type: 'POST',
            url: '/admin/create/holiday',
            passwordType: false,
            success: function(data, response){
                console.log(response);
                if(data.status === 'SUCCESS' && data.created === true){
                    $("#informSuccess").modal('show');
                } else {
                    $("#informFailure").modal('show');
                }
            },error: function(response) {
                $("#informFailure").modal('show');
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    }
    });
});