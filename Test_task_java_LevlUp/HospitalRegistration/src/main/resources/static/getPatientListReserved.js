function getList(){

    let req = 'http://localhost:8080/allRecord';
    let requestURL = req;
    let request = new XMLHttpRequest();
    request.open('POST', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function () {
        let jsonDate = request.response;

        let html = "";
        for(let i=0; i<jsonDate.length; i++){
            html += jsonDate[i] + "<br>";
        }
        document.getElementById('getListHtml').innerHTML=html; //создание списка на странице
    }
}