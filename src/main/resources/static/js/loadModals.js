function openForm() {
    $.ajax({
        type: 'GET',
        url: "/ajax/openForm",
        dataType: 'html'
    }).done( function (response) {
        // alert(response);
        // document.querySelector('#content')
        let container = $('.main-container');
        container.empty();
        container.append(response);
    });
}