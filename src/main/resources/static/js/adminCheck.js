$(document).ready(function () {
    var userType = sessionStorage.getItem("userType");
    if (userType !== "ADMIN") {
        if (userType == "SUPERVISOR") {
            window.location.href = "/views/supervisorHome.html";
        } else if (userType == "STAFF") {
            window.location.href = "/views/staffHome.html";
        }
    }
})