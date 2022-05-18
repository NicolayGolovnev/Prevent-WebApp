function giveIdToModal(id) {
    $(".btn-modal-delete").attr("value", id)
}

function openPatientListContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getPatientList",
        dataType: 'html',
        success: function (response) {
            let container = $('.main-container');
            container.replaceWith(response);
        }
    });
    if (window.innerWidth < 768)
        $('.navbar-toggler').click()

    $.ajax({
        type: 'GET',
        url: "/ajax/getPatientDeleteModal",
        dataType: 'html',
        success: function (response) {
            let container = $('.input-modal');
            container.replaceWith(response);
        }
    });
}

function deletePatient() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/user/delete/' + id,
        success: function (response) {
            alert(response);
            location.reload();
        }
    });
}


function openQuizListContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getQuizList",
        dataType: 'html',
        success: function (response) {
            let container = $('.main-container');
            container.replaceWith(response);
        }
    });
    if (window.innerWidth < 768)
        $('.navbar-toggler').click()

    $.ajax({
        type: 'GET',
        url: "/ajax/getQuizDeleteModal",
        dataType: 'html',
        success: function (response) {
            let container = $('.input-modal');
            container.replaceWith(response);
        }
    });
}

function openAssignPoolContainer() {
    $.ajax({
        type: 'GET',
        url: "/ajax/getAssignPool",
        dataType: 'html',
        success: function (response) {
            let container = $('.main-container');
            container.replaceWith(response);
        }
    });
    if (window.innerWidth < 768)
        $('.navbar-toggler').click()
}

function assignPool() {
    let form = {
        _csrf: $('input[name="_csrf"]').val(),
        user: $('input#forPatient').val(),
        quiz: $('input#forQuiz').val()
    };
    $.ajax({
        type: 'POST',
        url: '/user/assignPool',
        data: form,
        success: function (response) {
            alert(response);
            location.reload();
        },
        error: function (response) {
            alert(response.responseText);
        }
    });
}

function deleteQuiz() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/quiz/delete/' + id,
        success: function (response) {
            alert(response);
            location.reload();
        }
    });
}