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
function checkFileSize(input){
    var overallSize = 0;
    for(var i = 0; i < input.files.length; i++){
        overallSize+=input.files[i].size;
        if(input.files[i].size>5242880){
            alert("Możesz przesłać maksymalnie 5 plików ważących maksymalnie 5MB każdy! Jeden z plików waży więcej niż 5MB.");
            document.getElementById("AddPhoto").value = "";
        }
    }
    if(overallSize > 26214400){
        alert("Możesz przesłać maksymalnie 5 plików ważących maksymalnie 5MB każdy!");
        document.getElementById("AddPhoto").value = "";
    };
}
function getWidthAndHeight(id, link){
    var img = new Image();
    img.src = link;
    var identifier = "image-text-" + id;
    var height = img.height;
    var width = img.width;
    document.getElementById(identifier).innerHTML = width + "x" + height + " px";
}
function getAllImageSizes() {
    var containers = document.getElementsByClassName("image-container");
    for (var i = 0; i < containers.length; i++) {
        var src = containers[i].getElementsByClassName("fixed-image-size")[0].getAttribute('src');
        var span = containers[i].getElementsByClassName("image-text")[0];
        var img = new Image();
        img.src = src;
        span.innerHTML = img.width + "x" + img.height + " px";
    }
}
document.getElementsByTagName("MediaManager").onload = setTimeout("getAllImageSizes()", 1000);
function copyToClipboard(image) {
    var el = document.createElement('textarea');
    el.value = image.getAttribute('src');

    el.setAttribute('readonly', '');
    el.style = {position: 'absolute', left: '-9999px'};
    document.body.appendChild(el);

    el.select();
    el.setSelectionRange(0, 99999); /*For mobile devices*/

    document.execCommand('copy');

    document.getElementById("InfoMsg").innerHTML = "Link do obrazka został skopiowany! Link: " + el.value;
    document.body.removeChild(el);
}
function mediaManagerDisplay(button) {
    var frameStyle = document.getElementById("MediaFrame");
    if(frameStyle.style.display==="none") {
        button.value = "Schowaj";
        button.style.backgroundColor = "#ff5a4d";
        frameStyle.style.display = "block";
    } else {
        button.value = "Wybierz";
        button.style.backgroundColor = "#c0e06c";
        frameStyle.style.display = "none";
    }
}
function copyToInput(image){
    document.getElementById("PhotoLinkOne").value = image.getAttribute('src');
}