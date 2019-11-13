function openTab(tabName, button) {
    var i;
    var x = document.getElementsByClassName("tabName");
    for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
    }
    document.getElementById(tabName).style.display = "block";
    var classList = document.getElementsByClassName("activate-tab-button");
    for (i = 0; i < classList.length; i++){
        classList[i].className = "activate-tab-button";
    }
    var className = button.getAttribute("class");
    button.className += " active";

}