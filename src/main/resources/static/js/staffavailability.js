$(document).ready(function () {
    console.log("ready!");
    let userId = sessionStorage.getItem('userId');
    console.log("userID--", userId);
    // document.getElementById("employeeNumber").value = userId;
    // var nextWorkingMondayDate = getNextWorkingMonday();
    var dayList = ["monday", "tuesday", "wednesday", "thursday", "friday"];
    // var dayList = ["monday", "tuesday"];
    var dateDetails = getDateDetails(dayList);
    // document.getElementById("userDate-monday").value = formatDate(nextWorkingMondayDate);
    dayList.forEach(item => {
        document.getElementById("userDate-" + item).value = dateDetails[item]
    });
    fetchShifts();
    const form = document.querySelector("#user-registration");
    if (form) {
        form.addEventListener("submit", function (e) {
            e.preventDefault()
            submitForm(e, this, userId, dateDetails);
        });
    }


});


function getDateDetails(dayList) {
    var dateList = {};

    let nextMonday = getNextWorkingMonday();
    // displayDate = formatDate(nextWorkingMondayDate);
    // let ye = new Intl.DateTimeFormat('en', {year: 'numeric'}).format(date);
    // let mo = new Intl.DateTimeFormat('en', {month: 'short'}).format(date);
    // let da = new Intl.DateTimeFormat('en', {day: '2-digit'}).format(date);
    // let day = new Intl.DateTimeFormat('en', { weekday: "long" }).format(date);
    // console.log(`${da}-${mo}-${ye}(${day})`);
    let nextDay = new Date(nextMonday);
    nextDay.setDate(nextMonday.getDate());
    // console.log(nextDay.getDate());
    let details;
    for (let index = 0; index < dayList.length; index++) {
        details = [formatDate(nextDay), nextDay.toISOString()];
        dateList[dayList[index]] = details;
        nextDay = new Date(nextDay);
        nextDay.setDate(nextDay.getDate() + 1);
    }
    console.log(dateList)
    return dateList;
}

function getNextWorkingMonday() {
    var d = new Date();
    d.setDate(d.getDate() + (((1 + 7 - d.getDay()) % 7) || 7));
    console.log(d);
    return d;
}


function formatDateSQL(d) {
    return [d.getMonth() + 1, d.getDate(), d.getFullYear()].join('/') + ' ' + [d.getHours(),d.getMinutes(),d.getSeconds()].join(':');
}

function formatDate(date) {
    // let d = new Date(2010, 7, 5);
    let ye = new Intl.DateTimeFormat('en', {year: 'numeric'}).format(date);
    let mo = new Intl.DateTimeFormat('en', {month: 'short'}).format(date);
    let da = new Intl.DateTimeFormat('en', {day: '2-digit'}).format(date);
    let day = new Intl.DateTimeFormat('en', {weekday: "long"}).format(date);
    // console.log(`${da}-${mo}-${ye}(${day})`);
    return `${da}-${mo}-${ye}(${day})`;
}

function submitForm(e, form, userId, dateDetails) {
    e.preventDefault()
    console.log("clicked-->", form);
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
            //     $(location).attr('href',"/views/staff.html");
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

function fetchShifts() {
    $.ajax({
        contentType: 'application/json',
        dataType: 'json',
        type: 'get',
        url: '/utility/fetch/shifts',
        success: function(data, response){
            let shifts = data.shiftTimes;
            var option = '';
            for (var i=0; i<shifts.length; i++){
                option += '<option value="'+ shifts[i] + '">' + shifts[i] + '</option>';
            }
            $('#monday-slot').append(option);
        },error: function(response) {
            console.log("Error status", response.status, "Error text", response.statusText);
        }
    });
}


function createRequestBody(form, userId, dateDetails) {
    const resultJSON = [];
    var entry = {};
    formData = new FormData(form);
    var count = 0;
    var count_day = 0;
    day = {0: "monday", 1: "tuesday", 2: "wednesday", 3: "thursday", 4: "friday"}
    // for (var item of formData.entries()) {
    //     console.log(item[0],":",item[1]);
    // }
    for (var item of formData.entries()) {
        console.log(item)
        if (count === 0) {
            entry["employeeNumber"] = userId;
            entry["availability"] = item[1];
            dateDetail = dateDetails[day[count_day]];
            entry["availableDate"] = dateDetail[1];
            entry["availableDay"] = day[count_day];
        } else if (count === 1) {
            entry["startTime"] = item[1];
        } else if (count === 2) {
            entry["endTime"] = item[1];
            count = -1;
            count_day = count_day + 1;
            resultJSON.push(entry);
            entry = {}
        }
        count++;
        if (count === 2) {

        }
    }
    // resultJSON.push(entry);
    return resultJSON;
}

function radioFunction(element, day) {
    console.log(element)
    if (document.getElementById(element.id).value === 'yes') {
        document.getElementById(day + "-group").style.display = "block";
    } else if (document.getElementById(element.id).value === 'no') {
        document.getElementById(day + "-group").style.display = "none";
    }

}


