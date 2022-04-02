$(document).ready(function(){
    $( "#department-type" ).hide();
    $( "#employee-detail" ).hide();
    fetchShifts();
    let employeeSectionCount = 1;
    let slotType = 0;
    let rolesObj = [{name: "Admin", "value": 0}, {name: "Supervisor", "value": 1},
        {name: "Staff", "value": 2}, {name: "Intern", value: 3}];
    addEmpSection(employeeSectionCount, rolesObj);

    $( "#department" ).val(sessionStorage.getItem('departmentId'));

    $('#supervisor-date-picker').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
        multiselect: true,
        minDate:0,
        dateFormat: 'yy-mm-dd',
        onSelect: function(selectedDate, instance) {
            $( "#department-type" ).show();
            $( "#employee-detail" ).show();
            console.log(selectedDate);
            var date = new Date(selectedDate);
            var weekStart = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
            console.log("weekStart", weekStart);
            var weekend = new Date();
            weekend.setDate(weekStart.getDate() + 6);
            console.log("weekend", weekend);
        }
    });

    $( "#department" ).change(function() {
        $( "#employee-detail" ).show();
    });

    $( "#add-emp-details").click(function() {
        var roleSelected = $( "#employee-type-" + employeeSectionCount).val();
        rolesObj = rolesObj.filter(function(role) { return role.value != roleSelected; });
        employeeSectionCount++;
        addEmpSection(employeeSectionCount, rolesObj);
    });

    $( "#scheduler-submit").click(function(e) {
        e.preventDefault();
        var formData = {};
        var empDetails = [];
        formData.shiftDate = $("#supervisor-date-picker").val();
        formData.departmentId = $("#department").val();
        var time = $("#slot").val();
        var timeArr = time.split("-");
        var startTime = convertTime12to24(timeArr[0]);
        var endTime = convertTime12to24(timeArr[1].trim());
        formData.startTime = startTime;
        formData.endTime = endTime;
        for(var i = 1; i <= employeeSectionCount; i++) {
            var roleVal = $("#employee-type-" + i).val();
            var countVal = $("#hoursCount-" + i).val();
            empDetails.push({"roleId": roleVal, "employeeHours": countVal});
        }
        formData.shiftRoleHours = empDetails;
        formData.slotType = slotType;
        console.log(formData)
        addShifts(formData);
    });

    $("#generateSchedule").onclick(function (e){
        e.preventDefault();

        $.ajax({
            type: 'GET',
            url: '/supervisor/generate/schedule',
            success: function(data, response){
                console.log("SUCESS")
            },
            error: function (response){
                console.log("FAILED");
            }
        });


    });

    const convertTime12to24 = (time12h) => {
        const [time, modifier] = time12h.split(' ');
        let [hours, minutes] = time.split(':');
        if (hours === '12') {
            hours = '00';
        }
        if (modifier === 'PM') {
            hours = parseInt(hours, 10) + 12;
        }

        return `${hours}:${minutes}`;
    }

    function addShifts(shiftsData){
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(shiftsData),
            type: 'POST',
            url: '/supervisor/save/shifts',
            passwordType: true,
            success: function(data, response){
                $("#shiftDetailsSaved").modal('show');
                console.log(data);
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    }

    function fetchShifts() {
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            type: 'get',
            url: '/utility/fetch/shifts',
            success: function(data, response){
                let shifts = data.shiftTimes;
                slotType = data.slotType;
                var option = '';
                for (var i=0; i<shifts.length; i++){
                    option += '<option value="'+ shifts[i] + '">' + shifts[i] + '</option>';
                }
                $('#slot').append(option);
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    }
});

function addEmpSection(index, rolesObj) {
    var html = '';
    html += '<div class="row">';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="employee-type-'  + index + '">Employee role:</label>';
    html += '        <select class="form-control form-select" id="employee-type-' + index + '">';
    for(var i = 0; i < rolesObj.length; i++) {
        html += '            <option value=" '+ rolesObj[i].value +' ">'+ rolesObj[i].name + '</option>';
    }
    html += '        </select>';
    html += '    </div>';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="hoursCount-'  + index + '">Number of Hours:</label>';
    html += '        <input type="number" class="form-control" id="hoursCount-'  + index + '" min="1" value="1">';
    html += '    </div>';
    html += '</div>';
    $('#add-emp-sec').append(html);
    if(rolesObj.length == 1) {
        $("#dynamic-emp").hide();
    }else {
        $("#dynamic-emp").show();
    }
}