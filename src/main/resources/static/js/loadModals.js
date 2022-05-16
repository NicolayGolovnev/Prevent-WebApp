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
    $.ajax({
        type: 'GET',
        url: "/ajax/getPatientDeleteModal",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.input-modal');
        // container.empty();
        container.replaceWith(response);
    });
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
    $.ajax({
        type: 'GET',
        url: "/ajax/getQuizDeleteModal",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.input-modal');
        // container.empty();
        container.replaceWith(response);
    });
}

function openAssignPoolContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getAssignPool",
        dataType: 'html'
    }).done( function (response) {
        let container = $('.main-container');
        container.empty();
        container.replaceWith(response);
    });
    if (window.innerWidth < 768 )
        $('.navbar-toggler').click()

    // $("#forPatient").removeAttr("value");
    // $("#forQuiz").removeAttr("value");

}

function deleteQuiz() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/quiz/delete/' + id
    }).done( function (response) {
        alert(response);
        location.reload();
    });
}