function openForm() {
    $.ajax({
        type: 'GET',
        url: "/ajax/openForm",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.main-container');
        container.empty();
        container.append(response);
    });
}

function openPatientListContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getPatientList",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.main-container');
        container.empty();
        container.append(response);
    });
}