let sortingState = 0; //вводим две глобальные переменные для "запоминания" состояния сортировки
let sortColumn = 0; //not sorted

function jsonRead4(jsonArr){
	let jsonDate =JSON.parse(jsonArr);
	let htmlTableStyle = tableStyle(); 								//запуск стлизации таблицы
	let htmlTable = getTableTwo(jsonDate);							//запускаем создание таблицы
	document.getElementById('stat').innerHTML = htmlTableStyle; 	//записываем на страницу
	document.getElementById('content').innerHTML = htmlTable;		//переносим на страницу
}

function jsonRead3(){								//получение с сервера файла json
													//в данном примере используется локальный сервер
	var requestURL ='http://localhost:8081/my_project_js/json/jsonTest.json'; //адрес сервера который предоставит json
	var request = new XMLHttpRequest();							
	request.open('GET', requestURL);				//отправляем запрос на сервер
	request.responseType = 'json';
	request.send();
	
	request.onload = function() {
	let jsonDate = request.response;
	let htmlTableStyle = tableStyle(); 								//запуск стлизации таблицы	
	let htmlTable = getTableTwo(jsonDate);							//запускаем создание таблицы
	document.getElementById('stat').innerHTML = htmlTableStyle; 	//записываем на страницу
	document.getElementById('content').innerHTML = htmlTable;		//переносим на страницу
	}
}

function getTableTwo(arrJson){

	var html = '<table id = "tabl">';				//таблица будет создана далее в цикле
	html += '<thead><tr><th onclick="sortTable(0)">' + 'firstName' + '</th><th onclick="sortTable(1)">' + 'lastName' + '</th><th onclick="sortAbout(2)">' + 'about' + '</th><th onclick="sortTable(3)">' + 'eyeColor' + '</th></tr></thead>';
	html += '<tbody>';
	for (var i = 0; i < arrJson.length; i++) {		//циклом переносим данные с json в таблицу
		var name1 = arrJson[i].name;
		html += '<tr onclick="defStr()">';
		html += '<td>' + name1.firstName + '</td>';
		html += '<td>' + name1.lastName + '</td>';
		html += '<td><div class="ab" id="ab">' + arrJson[i].about + '</div></td>';
		html += '<td>' + arrJson[i].eyeColor + '</td>';
		html += '</tr>';
	}
    html += '</tbody></table>';
	html +='<div class="layer1" id="layer1">Sorting state. Click on any line.</div>'; //создаем элемент в котором будет отображатся состояние сортировки при нажатии на строку
	return html;
}

function tableStyle(){								//задает стиль таблицы и ширину

	html1 = '<style>';
	html1 += 'table {border-collapse: collapse; border: 1px solid #333; margin-bottom: 1em; width: 50%; float: left;}'; //задание параметров тблицы
	html1 += 'th {cursor: pointer;}'; 				//при наведении на заголовки колоно отображает курсор
	html1 += '.layer1 {text-indent: 40px;}'; 		//отступ для строки справа которая отражает состояние сортировки при нажатии
	html1 += '.ab{ margin: 0; -webkit-line-clamp: 2; display: -webkit-box; -webkit-box-orient: vertical; overflow: hidden;}'; //обрезка текста с добовление многоточия
	html1 += '</style>';
	return html1;
}

function sortTable(n){								//сортировка производится по алфавиту и в обратном порядке
	
	var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
	table = document.getElementById("tabl");
	switching = true;
	dir = "asc";
	while (switching) {
		switching = false;
		rows = table.getElementsByTagName("TR"); 	// возвращает коллекцию всех элементов документа с указанным именем тега в виде объекта HTMLCollection
		for (i = 1; i < (rows.length - 1); i++) {
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("TD")[n];
			y = rows[i + 1].getElementsByTagName("TD")[n];
		if (dir == "asc") {							
			if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
			shouldSwitch = true;
			break;
        }
		} else if (dir == "desc") {				
			if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
			shouldSwitch = true;
			break;
        }
      }
    }
    if (shouldSwitch) {								//замена местами элементов
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);  //insertBefore меняет местами удаляет элемент с текущей позиции и перемещает вперед
      switching = true;
      switchcount ++;
    } else {
      if (switchcount == 0 && dir == "asc") { 		//если счетчик равен 0 а переменная равна от а - я
        dir = "desc";
        switching = true;
      }
    }
    }
	
	if(dir == "asc"){ 								//должно передоватся два значения как отсортированно по возрастанию либо по убыванию и по какому столбцу была сортировка
		sortingState = 10; 							//возрастание
		sortColumn = n; 							//номер столбца по которому проводилась сортировка
	}
	if(dir == "desc"){
		sortingState = 20; 							//убывание
		sortColumn = n;
	}
}

function sortAbout(n){								//сортировка по количеству символов в тексте
	
	var rows, table, arrTab = [], textCon, z, min, t, j, clonedEl1, clonedEl2;
	
	arrTab.push(0);								//добовляем в начало 0, этот элемент нужен для уравнивания количества записей из столбца и массива, при дальнейшей сортировки первый элемент не учитывается т.к. это заголовок
	table = document.getElementById("tabl");
	rows = table.getElementsByTagName("TR"); 	// возвращает коллекцию всех элементов документа с указанным именем тега в виде объекта HTMLCollectio
	for(var i = 1; i < rows.length; i++){
		textCon = rows[i].textContent;			//нужен только текст поэтому берем элемент textContent
		arrTab.push(textCon.length);			//определяем длину строки 
	}
	
	z = arrTab.length;							//применяем алгоритм "Сортировка выбором"
    for (var i = 1; i < z-1; i++){ 				//что бы не делать сортировку слишком затратной будет использоватся массив в котором сохрнен порядок длинны строк из таблицы (столбца about)
		min = i;
		for (j = i+1; j < z; j++){
			if (arrTab[j] < arrTab[min]){
				min = j;
			} 
		} 
		t = arrTab[min]; 						//получили два индекса min и i, поскольку ячейки массива с данным индексами соответствуют реальной длинне строк из столбца about, то далее будем менять строки из таблицы с данными индексами
		arrTab[min] = arrTab[ i ]; 				//алгоритм ищет наименьший элемент min и меняет его местами с элементом i с которого начинали поиск далее цикл начинается снова но поиск уже идет с элемента i+1
		arrTab[ i ] = t;

		clonedEl1 = rows[i].cloneNode(true);	//требуется создание клонов меняемых местами ячеек
		clonedEl2 = rows[min].cloneNode(true);
		rows[min].parentNode.replaceChild(clonedEl1, rows[min]); 	//rows[i] будет записан на место rows[min]
		rows[i].parentNode.replaceChild(clonedEl2, rows[i]);		//теперь в таблице два rows[i] так что берем клонированный rows[min] и ставим на "старое" место rows[i]
    }
	
	sortingState = 10; 							//возрастание
	sortColumn = n; 							//номер столбца по которому проводилась сортировка
}

function defStr(){ 	//определение по какому столбцу и какая сортировка

	let swSc = sortingState + sortColumn; //столбцы 0, 1, 2, 3; состояния 10, 20
	switch (swSc){
		case 10:
			document.getElementById("layer1").innerText='Sort column firstName ' + 'from A to Z';
		break;
		case 11: 
			document.getElementById("layer1").innerText='Sort column lastName ' + 'from A to Z';
		break;
		case 12: 
			document.getElementById("layer1").innerText='Sort column about ' + 'characters from Min to Max';
		break;
		case 13: 
			document.getElementById("layer1").innerText='Sort column eyeColor ' + 'from A to Z';
		break;
		case 20: 
			document.getElementById("layer1").innerText='Sort column firstName ' + 'from Z to A';
		break;
		case 21: 
			document.getElementById("layer1").innerText='Sort column lastName ' + 'from Z to A';
		break;
		case 22: 
			document.getElementById("layer1").innerText='Sort column about ' + 'characters from Max to Min';
		break;
		case 23: 
			document.getElementById("layer1").innerText='Sort column eyeColor ' + 'from Z to A';
		break;
		default: 
			document.getElementById("layer1").innerText='Not sorted';
		break;
	}	
}