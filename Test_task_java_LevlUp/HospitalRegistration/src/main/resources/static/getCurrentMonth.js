
function getCurMon(){ //получить текуший месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth(),1);
    //console.log(d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"}));
    document.getElementById('dateInput').value = d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"});

}

function getCurMoUp(){ //получить следующий месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth()+1,1);
    //console.log(d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"}));
    document.getElementById('dateInput').value = d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"});

}

function getCurMoLater(){ //получить какой месяц через месяц

    let date = new Date();
    let d = new Date(date.getFullYear(),date.getMonth()+2,1);
    //console.log(d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"}));
    document.getElementById('dateInput').value = d.toLocaleDateString("en-CA",{year:'numeric',month:"2-digit", day:"2-digit"});
}
