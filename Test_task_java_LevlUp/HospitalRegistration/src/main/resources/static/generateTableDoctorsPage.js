/*<table id="1">
    <tr>
        <th>Specialization</th>
        <th>Last name</th>
        <th>First name</th>
    </tr>
    <tr th:each="doctorInfo : ${doctorInfos}">
        <td th:utext="${doctorInfo.specialization}">..</td>
        <td th:utext="${doctorInfo.lastName}">..</td>
        <td th:utext="${doctorInfo.firstName}">..</td>
    </tr>
</table>*/

function tableGenerate(){
    console.log('script start');

    let html1 = "";
    let html2 = "";
    let html3 = "";
/*
    for (){
        html1 += "<td id='col1' th:text=\"${doctorInfo.specialization}\"></td>";
        html2 += "<td id='col2' th:text=\"${doctorInfo.lastName}\"></td>";
        html3 += "<td id='col3' th:text=\"${doctorInfo.firstName}\"></td>";
    }
*/
    //document.getElementById('c1').innerHTML = html1;
    //document.getElementById('c2').innerHTML = html2;
    //document.getElementById('c3').innerHTML = html3;
}