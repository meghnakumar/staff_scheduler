$(document).ready(function(){
    $( "#department-type" ).hide();
    $( "#employee-detail" ).hide();
    let employeeSectionCount = 1;
    let roles = ["Role 1", "Role 2"];
    addEmpSection(employeeSectionCount, roles);



    $('#supervisor-date-picker').datepicker( {
        showOtherMonths: true,
        selectOtherMonths: true,
        multiselect: true,
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
        employeeSectionCount++;
        addEmpSection(employeeSectionCount, roles);
    });

    $( "#scheduler-submit").click(function(e) {
        e.preventDefault();
        var formData = {};
        var empDetails = [];
        formData.selectedDate = $("#supervisor-date-picker").val();
        formData.department = $("#department").val();
        formData.startTime = $("#start-time-1").val();
        formData.endTime = $("#end-time-1").val();
        for(var i = 1; i <= employeeSectionCount; i++) {
            var roleVal = $("#employee-type-" + i).children("option:selected").val();
            var countVal = $("#employeesCount-" + i).val();
            empDetails.push({"role": roleVal, "numberOfEmployees": countVal});
        }
        formData.empDetails = empDetails;
        console.log(formData)
    });
});

function addEmpSection(index, roles) {
    var html = '';
    html += '<div class="row">';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="employee-type-'  + index + '">Employee role:</label>';
    html += '        <select class="form-control" id="employee-type-' + index + ' ">';
    html += '            <option>'+ roles[0] + '</option>';
    html += '            <option>' + roles[1] + '</option>';
    html += '        </select>';
    html += '    </div>';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="employeesCount-'  + index + '">Number of Employees:</label>';
    html += '        <input type="number" class="form-control" id="employeesCount-'  + index + '">';
    html += '    </div>';
    html += '</div>';
    $('#add-emp-sec').append(html);
}