
function getCurMon(){ //получить текуший месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth(),1);
    document.getElementById('monthInput').value = d;

}

function getCurMoUp(){ //получить следующий месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth()+1,1);
    document.getElementById('monthInput').value = d;

}

function getCurMoLater(){ //получить какой месяц через месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth()+2,1);
    document.getElementById('monthInput').value = d;

}