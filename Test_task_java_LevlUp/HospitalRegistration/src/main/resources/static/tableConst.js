let arrTime = ['9:00','9:20','9:40','10:00','10:20','10:40','11:00','11:20','11:40','13:00','13:20','13:40','14:00','14:20','14:40','15:00','15:20','15:40','16:00','16:20'];

function requestServ(){
    /*
    let requestURL = 'http://localhost:8081/allRecord';     //куда будем делать запрос
    let request = new XMLHttpRequest();                     //можно возвращать только записи на конкретный день но для этого нужно формировать урл в процессе
    request.open('GET', requestURL);                //предлогаю закидывать в функцию дату и делать конктатацию к урл запросу
    request.responseType = 'json';
    request.send();

    request.onload = function (){
        let jsonDate = request.response;
        let htmlTable = getTable(jsonDate);
    }
     */
}

function getTable(jsonDate){
    console.log(jsonDate);
}

function setDateTime(date){ //функция получает год-месяц-день в формате стринг
    //формируем запрос к серверу
    let req = 'http://localhost:8081/getRecord/' + date.value;
    let requestURL = req;
    //console.log(req);
    let request = new XMLHttpRequest();
    //request.open('GET', requestURL);
    request.open('POST', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function () {
        let jsonDate = request.response;
        document.getElementById('style').innerHTML = tableStyle();
       document.getElementById('content').innerHTML=getTableHtml(jsonDate);
    }
}

function getDateTime(){ //функция где происходит конктатация строк что бы получилось (год-месяц-день час:мин) возвращает строку
    //
}

function getTableHtml(jsonDate){ //создание таблицы с свободными/зарезервированными данными

    let html = '<table id = "tabl">';
    html += '<thead><tr><th>' + 'Time' + '</th></tr></thead>';
    html += '<tbody>';
    for(let i = 0; i < arrTime.length; i++){
        let time = arrTime[i];
        html += reserved(jsonDate,time);
    }
    html += '</tbody></table>';
    html += '<p><input type="submit" value="Submit" />';
    return html;
}

function reserved(jsonDate, dateArrTime){

    let html = '';
    for(let i = 0; i < jsonDate.length; i++){
        let value = jsonDate[i];
        if(value == dateArrTime){
            html += '<td>';
            html += 'reserved';
            html += '</td>';
            return html;
        }
    }
    html += '<td onclick="oneClick(this.innerHTML)">';
    html += dateArrTime;
    html += '</td>';
    return html;
}

function oneClick(time){ //после нажати я на время возвращает строчку со временем
    //return time;
    console.log("Тут должен был быть вызов сохранения " + time);
}

function tableStyle(){ //задает стиль таблицы
    let html1 = '';
    html1 += '<style>';
    html1 += 'td {cursor: pointer;}';
    html1 += '</style>';
    return html1;
}