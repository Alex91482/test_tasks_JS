let arrTime = ['9:00','9:20','9:40','10:00','10:20','10:40','11:00','11:20','11:40','13:00','13:20','13:40','14:00','14:20','14:40','15:00','15:20','15:40','16:00','16:20'];
let globalDate;

function setDateTime(date){ //функция получает год-месяц-день в формате стринг и обращается за списком
    //формируем запрос к серверу
    if(date.value.length == 0){ //проверка на случай если дата не задана
        console.log("Error incorrect date value = " + date.value.length);
        document.getElementById('content').innerHTML=""; //если поля очистили ресетом то список со временем нужно убрать
        return;
    }
    else {
        globalDate = date.value; //присваеваем переменной дату в конце полная дата + время будут передаватся в поле формы
    }

    let req = 'http://localhost:8080/getRecord/' + date.value;
    let requestURL = req;
    let request = new XMLHttpRequest();
    request.open('POST', requestURL);
    request.responseType = 'json';
    request.send();

    request.onload = function () {
        let jsonDate = request.response;
        document.getElementById('style').innerHTML = tableStyle(); //создание стиля таблицы
        document.getElementById('content').innerHTML=getTableHtml(jsonDate); //создание самой таблицы
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
        html += '<tr>';
        html += reserved(jsonDate,time); //метод создающий ячейку таблицы
        html += '</tr>';
    }
    html += '</tbody></table>';
    return html;
}

function reserved(jsonDate, dateArrTime){ //функция делает сопоставления, если более развернуто то сюда отправляется время и оно сравнивается поочередно со всеме "зарезервированными датами"
    let html = '<tr>';
    for(let i = 0; i < jsonDate.length; i++){
        let value = jsonDate[i]; //тут мы получили массив дат из контролера но это все string
        let dat = new Date(value);
        let stringHoursMinutes = dat.getHours() + ":" + dat.getMinutes();
        if(stringHoursMinutes == dateArrTime){ //если при переборе списка полученного из репозитория есть хоть какието значения значит будет, совпадение это значит что кто то уже зарегистрирован на эту дату
            html += '<td>';
            html += 'reserved';
            html += '</td></tr>';
            return html; //возвращаем просто строку без возможности вызова метода создания записи на этот день
        }
    }
    html += '<td onclick="oneClick(this.innerHTML)">'; //совпадения не найдены значит к этому времени можно добавить функцию резервирования даты/времени, так же передает значение которое вписанно в тег
    html += dateArrTime;
    html += '</td></tr>';
    return html;
}

function oneClick(time){ //после нажати я на время возвращает строчку со временем и датой

    console.log("Вызов сохранения " + time);

    let dateAndTime = globalDate + " " + time;
    console.log(dateAndTime);
    document.getElementById('dayTime').value = dateAndTime; //attributes

    let attributeDisabl = document.getElementById('submit');
    let getAttributeDisabl=attributeDisabl.getAttribute('disabled');
    if(getAttributeDisabl != null){ //если кнопка субмит заблокированна то снмаем блокировку
        attributeDisabl.removeAttribute('disabled');
    }
}

function tableStyle(){ //задает стиль таблицы
    let html1 = '';
    html1 += '<style>';
    html1 += 'td {cursor: pointer;}';
    html1 += '</style>';
    return html1;
}

function oneClickSetAttr(){ //выставляем блокировку на кнопку субмит

    let attributeDisabl = document.getElementById('submit');
    let getAttributeDisabl=attributeDisabl.getAttribute('disabled');

    if(getAttributeDisabl == null){ //проверяем заблокированна ли кнопка если нет то добовляем блокировку
        attributeDisabl.setAttribute('disabled', true);
    }
}