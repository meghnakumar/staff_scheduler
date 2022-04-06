$(document).ready(function () {

    $("#employeeNumber").focusout(function () {
        let employeeNum = $("#employeeNumber").val();
        let warning = "";
        $("#error").hide();
        if (employeeNum != null) {
            if (employeeNum.length !== 0) {
                if (employeeNum.substring(0, 3) === 'EMP') {
                    $("error").hide();
                } else {
                    $("#error").text("Employee Number should start with 'EMP'.");
                    $("#error").show();
                }
            } else {
                $("#error").text("Employee Number can't be empty.");
                $("#error").show();
            }
        }
    });

    $("#departmentId").focusout(function () {
        let departmentId = $("#departmentId").val();
        let warning = "";
        $("#error-department-id").hide();
        console.log("departmentId", departmentId);
        if (departmentId.length === 0) {
            $("#error-department-id").text("Please select Department");
            $("#error-department-id").show();
        }
    });

    $("#register").submit(function (e) {
        e.preventDefault();

        var badCreds = function () {
            var inputs = document.getElementsByName("employeeNumber")
            if (inputs.length > 0) {
                inputs[0].classList.add(["is-invalid"]);
            }
            // $("#error").show();
            var valid = false;
            return false;
        };

        let formData = {};
        var valid = false;
        var values = $("#register :input").serializeArray();
        values.map(input => formData[input.name] = input.value);

        if (formData.employeeNumber === '') {
            badCreds();
        } else {
            valid = true;
        }

        if (formData.jobType === 'on') {
            formData["jobType"] = 1;
        } else {
            formData["jobType"] = 0;
        }
        if (valid) {

            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify(formData),
                type: 'POST',
                url: '/admin/create/employee',
                passwordType: false,
                success: function (data, response) {
                    console.log(response);
                    if (data.status === 'SUCCESS' && data.created === true) {
                        $("#informSuccess").modal('show');
                    } else if (data.status === 'INVALID_REQUEST' && data.created === false) {
                        $("#informClash").modal('show');
                    } else {
                        $("#informFailure").modal('show');
                    }
                }, error: function (response) {
                    $("#informFailure").modal('show');
                    console.log("Error status", response.status, "Error text", response.statusText);
                }
            });
        }
    });
});