/*
* Тестируются две функции 'treatmentArray' которая преобразует одиночный массив чисел в отформатированную строку
* и функция 'getPromise' предназначенная для получения массива с массивом чисел и преобразования массивов в отформатированные строки
*/


describe('treatmentArray', function() {
    it('Преобразование массива чисел в строку в которой чиса идущие по порядку заменяются тире. В консоль будет выведена дополнительная информация.', function(done) {
	
	var result;
	var w = [1,2,3,6,8,10,11,12,13,15,16];
	var e = '1-3,6,8,10-13,15,16';
	
	myFunction(w)
	.then((res)=>{result = res})
	.then(()=> {
		console.log('Test one function treatmentArray compares: result = ' + result + ' and ' + 'test = ' + e);
		assert.equal(result, e);
		done();
		});
    });
});
describe('getPromise test 1', function(){	
	it('Преобразования массива с массивами чисел в массив с отфарматированными строками. В консоль будет выведена дополнительная информация.', function(done){
		var l = [[1,2,3,6,8,10,11,12,13,15,16],[3,4],[1,4,6,8,9],[101,102,103,1001,1002,1003],[1]];
		var testArr = ['1-3,6,8,10-13,15,16','3,4','1,4,6,8,9','101-103,1001-1003','1'];
		var result =[];
		var i;
		
		var count = 0;
		var len = l.length;
		assert.doesNotThrow(function(){
			myFunction(l)
			.then((res)=>{result = res})
			.then(()=>{
				for(i=0; i<result.length; i++){
					console.log('Test tree function getPromise compares: result = ' + result[i] + ' and test = ' + testArr[i]);
					if(result[i] != testArr[i]){break;}
					count++;
				}
				assert.equal(count, len);
				done();
			}, 
			(err)=>{
				if(err) throw err;
				done();
			});
		});
	});
});
describe('getPromise test 2', function(){	
	it('Преобразования массива с массивами чисел в массив с отфарматированными строками. В консоль будет выведена дополнительная информация.', function(done){
		var l = [[1,2,3,4,5,6,7,8],[1,3,4,5,6,7,8],[1,3,4,5,6,7,8,10,11,12],[1,2,3],[1,2],[1,2,4],[1,2,4,5,6],[1,2,3,7,8,9,15,17,19,20,21],[1,2,3,4,5,6,100,1091,1999,2000,2001,2002],[1],[1,3,5,7,9,11]];
		var testArr = ['1-8','1,3-8','1,3-8,10-12','1-3','1,2','1,2,4','1,2,4-6','1-3,7-9,15,17,19-21','1-6,100,1091,1999-2002','1','1,3,5,7,9,11'];
		var result =[];
		var i;
		var count = 0;
		var len = l.length;
		assert.doesNotThrow(function(){
			myFunction(l)
			.then((res)=>{result = res})
			.then(()=>{
				for(i=0; i<result.length; i++){
					console.log('Test tree function getPromise compares: result = ' + result[i] + ' and test = ' + testArr[i]);
					if(result[i] != testArr[i]){break;}
					count++;
				}
				assert.equal(count, len);
				done();
			}, 
			(err)=>{
				if(err) throw err;
				done();
			});
		});
	});
});


