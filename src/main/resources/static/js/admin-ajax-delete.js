function deletePatient() {
    const id = $(".btn-modal-delete").val();
    $.ajax({
        type: 'GET',
        url: '/user/delete/' + id,
        success: function (response) {
            alert(response);
            $('#deleteModal').modal('hide')
            window.location.href='/admin/'
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
            $('#deleteModal').modal('hide')
            window.location.href='/admin/'
        }
    });
}