$('#supervisor-date-picker').datepicker( {
    showOtherMonths: true,
    selectOtherMonths: true,
    multiselect: true,
    onSelect: function(selectedDate, instance) {
        console.log(selectedDate);
        var date = new Date(selectedDate);
        var weekStart = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay());
        console.log("weekStart", weekStart);
        var weekend = new Date();
        weekend.setDate(weekStart.getDate() + 6);
        console.log("weekend", weekend);
    }
});