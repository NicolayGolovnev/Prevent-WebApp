function openForm() {
    $.ajax({
        type: 'GET',
        url: "/ajax/openForm",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.main-container');
        // alert(response.body)
        container.empty();
        container.replaceWith(response);
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
        container.replaceWith(response);
    });
}

function giveIdToModal(id) {
    $(".btn-modal-delete").attr("value", id)
}

function deletePatient() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/user/delete/' + id
    }).done( function (response) {
        alert(response);
        location.reload();
    });
}