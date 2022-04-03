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
        for(var day = 1; day < 6; day++) {
            var shiftDate = new Date(weekStart);
            shiftDate.setDate(shiftDate.getDate()+day);
            var date = shiftDate.toLocaleDateString('en-CA');
            for(var i = 0; i < shifts.length; i++) {
                var timeArr = shifts[i].split("-");
                var startTime = convertTime12to24(timeArr[0].trim());
                var endTime = convertTime12to24(timeArr[1].trim());
                endTime = endTime == "00:00" ? "24:00": endTime;
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
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridWeek',
                themeSystem: 'bootstrap4',
                businessHours: false,
                editable: true,
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
                    var hour = time[0].length == 1 ? '0' + time[0] + ':' + time[1] : shiftTime;
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



    // $('#calendar').fullCalendar({
    //     themeSystem: 'bootstrap4',
    //     // emphasizes business hours
    //     businessHours: false,
    //     defaultView: 'month',
    //     // event dragging & resizing
    //     editable: true,
    //     // header
    //     header: {
    //         left: 'title',
    //         center: 'month,agendaWeek,agendaDay',
    //         right: 'today prev,next'
    //     },
    //     events: [
    //         {
    //             title: 'Dentist',
    //             description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras eu pellentesque nibh. In nisl nulla, convallis ac nulla eget, pellentesque pellentesque magna.',
    //             start: '2022-03-18T11:30:00',
    //             end: '2022-03-27T012:30:00',
    //             className: 'fc-bg-blue',
    //             icon : "medkit",
    //             allDay: false
    //         }
    //     ],
    //     eventRender: function(event, element) {
    //         if(event.icon){
    //             element.find(".fc-title").prepend("<i class='fa fa-"+event.icon+"'></i>");
    //         }
    //     },
    //     dayClick: function() {
    //
    //     },
    //     eventClick: function(event, jsEvent, view) {
    //         $('.event-icon').html("<i class='fa fa-"+event.icon+"'></i>");
    //         $('.event-title').html(event.title);
    //         $('.event-body').html(event.description);
    //         $('.eventUrl').attr('href',event.url);
    //         $('#modal-view-event').modal();
    //     },
    // })
});

