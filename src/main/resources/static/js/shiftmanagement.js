$(document).ready(function(){

    $("#shift-form").submit(function (e){
        e.preventDefault();

        var formData = {};
        var values = $("#shift-form :input").serializeArray();
        values.map( input => formData[input.name] = input.value);

        if(formData.shiftDuration === ''){
            console.log("erorr")
        }

        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            type: 'POST',
            url: '/admin/shift',
            success: function(data, response){
                console.log(response);
                if(data.status === 'SUCCESS' && data.isLogged === true){
                    $("#informSuccess").modal('show');
                } else {
                    $("#informFailure").modal('show');
                }
            },error: function(response) {
                $("#informFailure").modal('show');
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });

    });

})