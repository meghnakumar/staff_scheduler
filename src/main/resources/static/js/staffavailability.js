$(document).ready(function () {
    fetchShifts();

    let userId = sessionStorage.getItem('userId');
    console.log("userID--", userId);
    var dayList = ["monday", "tuesday", "wednesday", "thursday", "friday"];
    var dateDetails = getDateDetails(dayList);
    dayList.forEach(item => {
        var dateArr = dateDetails[item]
        $("#userDate-" + item).text(dateArr[0]);
    });

    const form = document.querySelector("#user-registration");
    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault()
            submitForm(e, this, userId, dateDetails);
        });
    }
    console.log("ready!");
});


function getDateDetails(dayList) {
    var dateList = {};
    let nextMonday = getNextWorkingMonday();
    let nextDay = new Date(nextMonday);
    nextDay.setDate(nextMonday.getDate());
    let details;
    for (let index = 0; index < dayList.length; index++) {
        details = [formatDate(nextDay), nextDay.toISOString()];
        dateList[dayList[index]] = details;
        nextDay = new Date(nextDay);
        nextDay.setDate(nextDay.getDate() + 1);
    }
    // console.log(dateList)
    return dateList;
}

function getNextWorkingMonday() {
    var d = new Date();
    d.setDate(d.getDate() + (((1 + 7 - d.getDay()) % 7) || 7));
    // console.log(d);
    return d;
}


function formatDateSQL(d) {
    return [d.getMonth() + 1, d.getDate(), d.getFullYear()].join('/') + ' ' + [d.getHours(), d.getMinutes(), d.getSeconds()].join(':');
}

function formatDate(date) {
    let ye = new Intl.DateTimeFormat('en', {year: 'numeric'}).format(date);
    let mo = new Intl.DateTimeFormat('en', {month: '2-digit'}).format(date);
    let da = new Intl.DateTimeFormat('en', {day: '2-digit'}).format(date);
    let day = new Intl.DateTimeFormat('en', {weekday: "long"}).format(date);
    return `${ye}-${mo}-${da} (${day})`;
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

function createRequestBody(form, userId, dateDetails) {
    const resultJSON = [];
    let entry = {};
    formData = new FormData(form);
    // dayList = {0: "monday", 1: "tuesday", 2: "wednesday", 3: "thursday", 4: "friday"};
    dayListArr = ["monday", "tuesday", "wednesday", "thursday", "friday"];
    // var count = 0;
    // var countDay = 0;
    for (var day of dayListArr) {
        entry = {}
        if (formData.get(day) === 'yes') {
            wholeTime = formData.get(day + '-slot')
            var timeArr = wholeTime.split("-");
            var wholeDateTime = $("#userDate-" + day).text().trim();
            var wholeDateTimeArr = wholeDateTime.split(" ");
            var availableDay = wholeDateTimeArr[1].slice(0, -1);
            availableDay = availableDay.substring(1)
            var startTime = convertTime12to24(timeArr[0].trim());
            var endTime = convertTime12to24(timeArr[1].trim());

            entry["employeeNumber"] = userId;
            entry["availability"] = formData.get(day);
            entry["availableDate"] = wholeDateTimeArr[0].trim();
            entry["availableDay"] = availableDay;
            entry["startTime"] = startTime;
            entry["endTime"] = endTime;
            resultJSON.push(entry);
        }
    }
    console.log("result:", resultJSON);
    return resultJSON;
}

function submitForm(e, form, userId, dateDetails) {
    e.preventDefault()
    // console.log("clicked-->", form);
    submit = document.getElementById("form-submit");
    submit.disabled = true;
    setTimeout(submit.disabled = false, 2000);
    var staffAvailabilityArr = createRequestBody(form, userId, dateDetails)//$('form').serializeArray();

    var request = {
        "staffAvailabilityRequest": staffAvailabilityArr
    }
    console.log(request);
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        data: JSON.stringify(request),
        type: 'POST',
        url: '/staff/input/availability',
        passwordType: true,
        success: function (data, response) {
            if (response == "success") {
                console.log("Success:", response)
                console.log("data:", data)
                // sessionStorage.setItem('userId', loginId);
            }

            // Get userId from session storage which will be used in request body of APIs
            // let userId = sessionStorage.getItem('userId');
            // if (data.userType === "ADMIN" && data.status === 'SUCCESS') {
            //     $(location).attr('href',"/views/admin.html");
            // } else if (data.userType === "SUPERVISOR" && data.status === 'SUCCESS') {
            //     $(location).attr('href',"/views/supervisorShifts.html");
            // } else if (data.userType === 'STAFF' && data.status === 'SUCCESS'){
            //     $(location).attr('href',"/views/staffHome.html");
            // } else if (data.status === 'INCORRECT_PASSWORD'){
            //     //Respond with Error
            //     alert("Incorrect Password for ID: " + loginId);
            // } else {
            //     $("#invalid").modal('show');
            // }
        }, error: function (response) {
            console.log("Error status", response.status, "Error text", response.statusText);
        }
    });


    /*
    {
"staffAvailabilityRequest": [
{
  "employeeNumber": "string",
  "startTime": "2022-03-16T03:41:02.788Z",
  "endTime": "2022-03-16T03:41:02.788Z",
  "availableDate": "2022-03-16T03:41:02.788Z",
  "availableDay": "string"
}
]
}
    */
}

function radioFunction(element, day) {
    console.log(element)
    if (document.getElementById(element.id).value === 'yes') {
        document.getElementById(day + "-group").style.display = "block";
    } else if (document.getElementById(element.id).value === 'no') {
        document.getElementById(day + "-group").style.display = "none";
    }

}

function fetchShifts() {
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'get',
        url: '/utility/fetch/shifts',
        success: function (data, response) {
            let shifts = data.shiftTimes;
            var option = '';
            for (var i = 0; i < shifts.length; i++) {
                option += '<option value="' + shifts[i] + '">' + shifts[i] + '</option>';
            }
            $('#monday-slot').append(option);
            $('#tuesday-slot').append(option);
            $('#wednesday-slot').append(option);
            $('#thursday-slot').append(option);
            $('#friday-slot').append(option);
        }, error: function (response) {
            console.log("Error status", response.status, "Error text", response.statusText);
        }
    });
}


