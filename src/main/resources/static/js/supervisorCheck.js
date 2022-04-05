$(document).ready(function() {
    var userType = sessionStorage.getItem("userType");
    if (userType !== "SUPERVISOR") {
        if (userType == "ADMIN") {
            window.location.href = "/views/admin.html";
        } else if (userType == "STAFF") {
            window.location.href = "/views/staffHome.html";
        }
    }

});