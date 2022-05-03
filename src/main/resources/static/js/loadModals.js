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

function giveIdToModal(id) {
    $(".btn-modal-delete").attr("value", id)
}

function deletePatient() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/ajax/user/delete/' + id
    }).done( function (response) {
        location.reload();
    });
}