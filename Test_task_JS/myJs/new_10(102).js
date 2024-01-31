
/*
* Код должен быть размещен на github bitbucket
* Код должен выполнятся асинхронно
* Желательно наличие автоматических тестов, например на базе фреймворка Mocha
* Приложение должно быть оформелно в виде модуля. Возможность установки модуля с помощью npm 
*
* При вызове функции myFunction происходит проверка переданного значения на массив чисел либо массив с массивами 
* Происходит переработка полученных значениий через switchElseIf
* возвращаем строку (переработанный массив) либо массив с строками
*/

var intArrLeng, intOne, a, b, stringInterm, intIntermLeng, stringArr, counter1, myArray, checked, deletethis, myArray2, count;

function getStringArr(arr){				//эта функция является входной точкой скрипта если сразу хотите увидеть результат
	myFunction(arr)						//Поскольку функция асинхронная результат промиса можно получить только через then
	.then((res)=> console.log(res));	//result может использоватся как аргумент					
	/*.then(()=> {
		console.log(result);							
	});*/
}

async function myFunction(arr){					//обработка массива с массивами (№1)
	myArray = [];
	myArray2 = [];
	checked = Array.isArray(arr[0]);			//проверка на то что будем обрабатывать
	
	if(checked == true){						//если это массив с массивами
		const promisesT = await getPromise(arr);
		return promisesT;
	}
	else{										//если это массив чисел
		const promise = await treatmentArray(arr);
		return promise.toString();
	}
}

async function getPromise(arr){
		
	const promises = arr.map(async arrElement => {	//что бы массивы из массива обрабатывались паралельно создаем map и запускаем промисы
		count = await treatmentArray(arrElement);
		count = count.toString();
		return count;
	});
	return Promise.all(promises);					//вернет уже переработаный массив
}

async function treatmentArray(array){	//обработка одиночного массива (№2)
	
intArrLeng = array.length;
	
stringArr = [];		//массив из которого будет сформированна строка
stringInterm = [];	//промежуточная строка для значений с тире
		
	for(a = 0; a < intArrLeng; a++){
		intOne = array[a];
			
		if(a == intArrLeng - 1){ 				//это последнее число значит проверяем есть ли значения в промежуточном массиве
			intIntermLeng = stringInterm.length;
			switchElseIf(intOne, intIntermLeng);
		}
		else if(intOne + 1 != array[a+1]){		//если числа идут не по порядку тогда добовляем в строку intIntermLeng = stringInterm.length;
			intIntermLeng = stringInterm.length;
			switchElseIf(intOne, intIntermLeng);
		}
		else if(intOne + 1 == array[a+1]){		//если числа идут по порядку то добовляем в промежуточный массив
			stringInterm.push(intOne);
		}
		else{
			stringArr.push('Error values; Location nested loop');
		}
	}
	return stringArr;
}

async function switchElseIf(intOne, intIntermLeng){	//обработка событий при переборе массива (№2)
	switch(intIntermLeng){
		case 0:         							//если в промежуточном массиве нет элементов
			stringArr.push(intOne);
			break;
		case 1:										//если в прмежуточном массиве есть один элемент
			stringArr.push(stringInterm[0]);
			stringArr.push(intOne);
			stringInterm.length = 0;
			break;
		default:									//более одного элемента									
			stringArr.push(stringInterm[0] + '-' + intOne);
			stringInterm.length = 0;
	}
}
