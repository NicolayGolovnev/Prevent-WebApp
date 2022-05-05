function giveIdToModal(id) {
    $(".btn-modal-delete").attr("value", id)
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
    if (window.innerWidth < 768 )
        $('.navbar-toggler').click()

    //TODO сделать догрузку модального окна
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

function openQuizListContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getQuizList",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.main-container');
        container.empty();
        container.replaceWith(response);
    });
    if (window.innerWidth < 768 )
        $('.navbar-toggler').click()

    //TODO сделать догрузку модального окна
}