$(document).ready(function(){
    $( "#department-type" ).hide();
    $( "#employee-detail" ).hide();
});

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
    var html = '';
    html += '<div class="row">';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="employee-type-1">Employee role:</label>';
    html += '        <select class="form-control" id="employee-type-1">';
    html += '            <option>Role 1</option>';
    html += '            <option>Role 2</option>';
    html += '        </select>';
    html += '    </div>';
    html += '    <div class="form-group col-md-6">';
    html += '        <label for="employeesCount-1">Number of Employees:</label>';
    html += '        <input type="number" class="form-control" id="employeesCount-1">';
    html += '    </div>';
    html += '</div>';

    $('#dynamic-emp').prepend(html);
});


