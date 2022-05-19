document.getElementById('doTest').onclick = function() {
    document.getElementById('chooseTest').hidden = false;
    document.getElementById('chooseCompleteTest').hidden = true;
    if (window.innerWidth < 768 )
        $('.navbar-toggler').click()
}

document.getElementById('showTest').onclick = function() {
    document.getElementById('chooseTest').hidden = true;
    document.getElementById('chooseCompleteTest').hidden = false;
    if (window.innerWidth < 768 )
        $('.navbar-toggler').click()
}