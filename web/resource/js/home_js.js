function loadDoc() {
    var xhttp = new XMLHttpRequest();
    var txt = "";
    var temp ="";
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            txt += "<table border='1'>"

            txt += "<tr>"
            txt += "<th> ID </th>"
            txt += "<th> First Name </th>"
            txt += "<th> Last Name </th>"
            txt += "<th> Style </th>"
            txt += "<th> Delete </th>"
            txt += "<th> Update </th>"
            txt += "</tr>"
            var i = 1;
            for (x in myObj) {
                txt += "<tr>"
                txt += "<td>" + i + "</td>";
                txt += "<td>" + myObj[x].fname + "</td>";
                txt += "<td>" + myObj[x].lname + "</td>";
                txt += "<td>" + myObj[x].style + "</td>";
                txt += "<td><button name=\"delete\" onclick=\"deleteById(" + myObj[x].id + ")\" type=\"button\" > Delete </button></td>";
                temp = myObj[x];
                txt += "<td><button name=\"update\" onclick=\"update(" + temp + ") type=\"button\" > Update </button></td>";
                txt += "</tr>"
                i++;
                console.log(temp);
            }


            txt += "</table>"
            document.getElementById("teachers").innerHTML = txt;
        }
    };
    xhttp.open("GET", "http://localhost:8080/api/teacher/teachers", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function deleteById(id) {
    var deleteUrl = "http://localhost:8080/api/teacher/delete/";

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
           // location.reload();
            loadDoc();
        }
    }
    xhttp.open("POST", deleteUrl + id, true);
    // xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function update(object) {
    var updateUrl = "http://localhost:8080/api/teacher/update/";
    console.log(object);
    /*var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            location.reload();
        }
    }
    xhttp.open("POST", updateUrl + object, true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();*/
}
