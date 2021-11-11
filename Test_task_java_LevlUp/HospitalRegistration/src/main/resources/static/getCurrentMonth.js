let currentMonth; //текуший месяц формат гггг.мм.дд


function getCurMon(){ //получить текуший месяц
    let date = new Date();
    currentMonth = date;
    return currentMonth.toString();
}

function getCurMoUp(){ //получить следующий месяц
    currentMonth.setDate(currentMonth.getMonth()+1);
    return currentMonth.toString();
}

function getCurMoDown(){ //получить предыдущий месяц
    currentMonth.setDate(currentMonth.getMonth()-1);
    return currentMonth.toString();
}