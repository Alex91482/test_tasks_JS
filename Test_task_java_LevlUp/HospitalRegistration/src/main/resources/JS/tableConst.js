
function requestServ(){
    let requestURL = 'http://localhost:8081/allRecord';     //куда будем делать запрос
    let request = new XMLHttpRequest();                     //можно возвращать только записи на конкретный день но для этого нужно формировать урл в процессе
    request.open('GET', requestURL);                //предлогаю закидывать в функцию дату и делать конктатацию к урл запросу
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

function setDateTime(date){ //функция получает год-месяц-день в формате стринг
    //формируем запрос к серверу
    let req = 'http://localhost:8081/getRecord/' + date;
    //let requestURL = 'http://localhost:8081/allRecord';
    let requestURL = req;
    let request = new XMLHttpRequest();
    request.open('GET', requestURL);
    request.responseType = 'json';
    request.send();
}

function getDateTime(){ //функция где происходит конктатация строк что бы получилось (год-месяц-день час:мин) возвращает строку
    //
}