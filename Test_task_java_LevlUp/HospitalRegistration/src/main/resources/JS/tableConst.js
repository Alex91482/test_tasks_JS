
function requestServ(){
    let requestURL = 'http://localhost:8081/allRecord';    //куда будем делать запрос
    let request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function (){
        let jsonDate = request.response;
        let htmlTable = getTable(jsonDate);
    }
}

function getTable(jsonDate){
    console.log(jsonDate);
}