document.getElementById('doTest').onclick = function() {
    document.getElementById('chooseTest').hidden = false;
    document.getElementById('chooseCompleteTest').hidden = true;
}

document.getElementById('showTest').onclick = function() {
    document.getElementById('chooseTest').hidden = true;
    document.getElementById('chooseCompleteTest').hidden = false;
}