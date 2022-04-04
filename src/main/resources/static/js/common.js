$(document).ready(function(){
    if(!sessionStorage.getItem('userId')) {
        window.location.href = '/';
    }

    $("#logout").click(function() {
        sessionStorage.clear();
        window.location.href = '/';
    });
});

