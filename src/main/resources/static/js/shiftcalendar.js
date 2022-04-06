$(document).ready(function(){
    fetchShifts();

    function fetchShifts() {
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            type: 'get',
            url: '/utility/fetch/shifts',
            success: function(data, response){
                let shifts = data.shiftTimes;
                let events = createEvents(shifts);
                openCalendar(events);
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    }

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

    function createEvents(shifts) {
        var events = [];
        var date = new Date();
        var weekStart = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
        console.log("weekStart", weekStart);
        for(var day = 1; day < 13; day++) {
            if(day == 6 || day == 7) {
                continue;
            }
            var shiftDate = new Date(weekStart);
            shiftDate.setDate(shiftDate.getDate()+day);
            var date = shiftDate.toLocaleDateString('en-CA');
            for(var i = 0; i < shifts.length; i++) {
                var timeArr = shifts[i].split("-");
                var startTime = convertTime12to24(timeArr[0].trim());
                var endTime = convertTime12to24(timeArr[1].trim());
                endTime = endTime === "00:00" ? "24:00": endTime;
                events.push(
                    {
                        // title: 'Slot',
                        start: date + 'T' + startTime,
                        end: date + 'T' + endTime,
                        className: 'fc-bg-blue',
                        allDay: false,
                        editable: false
                    }
                );
            }
        }
        return events;
    }


    function openCalendar(events) {
        var date = new Date();
        var weekStart = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
        var weekEnd = new Date(weekStart);
        weekEnd.setDate(weekEnd.getDate()+14);
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridWeek',
                themeSystem: 'bootstrap4',
                businessHours: false,
                editable: true,
                validRange: {
                    start: weekStart,
                    end: weekEnd
                },
                headerToolbar: {
                    left: 'prev, next',
                    center: 'title',
                    right: 'timeGridWeek, timeGridDay'
                },
                events: events,
                selectAllow: true,
                eventClick: function(info) {
                    $("#emp-table-details").empty();
                    var shiftDate = info.event.start.toLocaleDateString('en-CA');
                    var shiftTime = convertTime12to24(info.event.start.toLocaleTimeString());
                    var time = shiftTime.split(':');
                    var hour = time[0].length === 1 ? '0' + time[0] + ':' + time[1] : shiftTime;
                    console.log("get date", shiftDate);
                    console.log("shift time", hour);
                    var scheduleObj = {
                        shiftDate: shiftDate,
                        shiftTime: hour,
                        departmentId: sessionStorage.getItem('departmentId')
                    }
                    addTable(scheduleObj);
                    $("#calendarModal").modal('show');
                },
            });
            calendar.render();
    }

    function addTable(scheduleRequest) {
        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(scheduleRequest),
            type: 'POST',
            url: '/schedule/fetch',
            success: function(data){
                console.log("schedule data", data.schedule);
                if(data.schedule.employees) {
                    var employees = data.schedule.employees;

                    var html = '';
                    for(var i = 0; i < employees.length; i++) {
                        html += '<tr>';
                        html += '   <td>'+ employees[i].employeeId + '</td>';
                        html += '   <td>'+ employees[i].startTime + '</td>';
                        html += '   <td>'+ employees[i].endTime + '</td>';
                        html += '   <td>'+ employees[i].roleId + '</td>';
                        html += '</tr>';
                    }
                    }
                $('#emp-table-details').append(html);
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    }

});

