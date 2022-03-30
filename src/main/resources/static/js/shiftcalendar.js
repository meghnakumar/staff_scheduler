$(document).ready(function(){
    fetchShifts();

    let shifts;
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

    function createEvents(shifts) {
        var events = [];
        var date = new Date();
        var weekStart = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
        console.log("weekStart", weekStart);
        // var weekend = new Date();
        // weekend.setDate(weekStart.getDate() + 6);
        for(var day = 1; day < 6; day++) {
            var shiftDate = new Date();
            shiftDate.setDate(weekStart.getDate() + day);
            var date = shiftDate.toLocaleDateString('en-CA')
            // shiftDate.toISOString().slice(0, 10);
            for(var i = 0; i < shifts.length; i++) {
                var timeArr = shifts[i].split("-");
                var startTime = convertTime12to24(timeArr[0].trim());
                var endTime = convertTime12to24(timeArr[1].trim());
                endTime = endTime == "00:00" ? "24:00": endTime;
                events.push(
                    {
                        title: 'Slot',
                        start: date + 'T' + startTime,
                        end: date + 'T' + endTime,
                        className: 'fc-bg-blue',
                        allDay: false
                    }
                );
            }
        }


        return events;
    }

    const convertTime12to24 = (time12h) => {
        console.log(time12h);
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


    function openCalendar(events) {
            var calendarEl = document.getElementById('calendar');
            var calendar = new FullCalendar.Calendar(calendarEl, {
                initialView: 'timeGridWeek',
                themeSystem: 'bootstrap4',
                // emphasizes business hours
                businessHours: false,
                editable: true,
                headerToolbar: {
                    left: 'prev, next',
                    center: 'title',
                    right: 'timeGridWeek, timeGridDay'
                },
                events: events,
                eventRender: function(event, element) {
                    if(event.icon){
                        element.find(".fc-title").prepend("<i class='fa fa-"+event.icon+"'></i>");
                    }
                },
                dayClick: function() {

                },
                eventClick: function(event, jsEvent, view) {
                    $('.event-icon').html("<i class='fa fa-"+event.icon+"'></i>");
                    $('.event-title').html(event.title);
                    $('.event-body').html(event.description);
                    $('.eventUrl').attr('href',event.url);
                    $('#modal-view-event').modal();
                },
            });
            calendar.render();
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

