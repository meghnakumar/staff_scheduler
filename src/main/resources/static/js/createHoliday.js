$(document).ready(function(){
    $("#holiday-form").submit(function(e) {
    e.preventDefault();

    let formData = {};
    var values = $("#holiday-form :input").serializeArray();
    values.map( input => formData[input.name] = input.value);

    if(!formData.endDate){
        formData["endDate"] = endDate
        formData[endDate] = formData.startDate;
    }

        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            type: 'POST',
            url: '/admin/create/holiday',
            passwordType: false,
            success: function(data, response){
                console.log(response);
                alert("Holiday Added.")
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    });
});