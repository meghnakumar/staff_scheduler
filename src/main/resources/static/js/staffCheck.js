$(document).ready(function() {
    var userType = sessionStorage.getItem("userType");
    if (userType !== "STAFF") {
        if (userType == "ADMIN") {
            window.location.href = "/views/admin.html";
        } else if (userType == "SUPERVISOR") {
            window.location.href = "/views/supervisorHome.html";
        }
    }
});