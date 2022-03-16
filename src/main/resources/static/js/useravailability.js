$( document ).ready(function() {
    console.log( "ready!" );
    let userId = sessionStorage.getItem('userId');
    console.log("userID--", userId );
    const form = document.querySelector("#user-registration");
    if(form){
        form.addEventListener("submit",function (e){
            e.preventDefault()
            submitForm(e, this);
        });
    }


});

function submitForm(e, form){
    e.preventDefault()
    console.log("clicked-->", form);
    submit = document.getElementById("form-submit");
    submit.disabled = true;
    setTimeout(submit.disabled = false,2000);
    var staffAvailabilityArr = createRequestBody(form)//$('form').serializeArray();

    var request = {
        "staffAvailabilityRequest": staffAvailabilityArr
    }
    console.log(request);
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


function createRequestBody(form){
    const resultJSON = {};
    for(const item of new FormData(form)){
        resultJSON[item[0]] = item[1];
    }
    return resultJSON;
}