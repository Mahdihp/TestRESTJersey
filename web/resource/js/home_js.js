$(document).ready(function () {
    $("#form").hide()
});

function loadDoc() {
    var xhttp = new XMLHttpRequest();
    var txt = "";
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var myObj = JSON.parse(this.responseText);
            txt += "<table border='1px' class='table table-hover'>"

            txt += "<thead>"
            txt += "<tr>"
            txt += "<th> ID </th>"
            txt += "<th> First Name </th>"
            txt += "<th> Last Name </th>"
            txt += "<th> Style </th>"
            txt += "<th> Delete </th>"
            txt += "<th> Update </th>"
            txt += "</tr>"
            txt += "</thead>"
            var i = 1;
            txt += "<tbody>";
            for (x in myObj) {

                txt += "<tr>"
                txt += "<td>" + i + "</td>";
                txt += "<td>" + myObj[x].fname + "</td>";
                txt += "<td>" + myObj[x].lname + "</td>";
                txt += "<td>" + myObj[x].style + "</td>";
                txt += "<td><button class='btn btn-danger' name=\"delete\" onclick=\"deleteById(" + myObj[x].id + ")\" type=\"button\" > Delete </button></td>";
                txt += "<td><button class='btn btn-secondary' name=\"update\" onclick=\"update(" + myObj[x].id + ")\" type=\"button\" > Update </button></td>";
                txt += "</tr>"
                i++;
            }
            txt += "<tbody>";

            txt += "</table>"
            document.getElementById("teachers").innerHTML = txt;
        }
    };
    xhttp.open("GET", "http://localhost:8080/api/teacher/teachers", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send();
}

function deleteById(id) {
    if (confirm("آیا مایل به حذف این معلم هستید؟")) {
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
}

function update(id) {
    var updateUrl = "http://localhost:8080/api/teacher/teachers/";

    $.get(updateUrl + id, function (data, status) {
        // console.log(data["fname"]);

        $("#form").show();
        $("#id").val(data["id"]);
        $("#fname").val(data["fname"]);
        $("#lname").val(data["lname"]);
        $("#style").val(data["style"]);
    })

}

function save() {
    var id = $("#id").val();
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var style = $("#style").val();
    var json = "{ \"id\":" + id + ",\"fname\":\"" + fname + "\",\"lname\":\"" + lname + "\",\"style\":\"" + style + "\"}";
    // console.log(json);
    var updateUrl = "http://localhost:8080/api/teacher/update";
    $.ajax({
        url: updateUrl,
        type: "POST",
        data: json,
        contentType: "application/json",
        ataType: "json",
        success: function (data, status, xhr) {
            console.log(data);
            loadDoc();
        },
        error: function (jqXhr, textStatus, errorMessage) { // error callback
            console.log("ErrorIng: " + errorMessage);
        }
    });
}

function insertTacher() {
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var style = $("#style").val();
    if (fname == "" || lname == ""){
        return;
    }
    // var json = "{\"id\":" + 0 + ",\"fname\":\"" + fname + "\",\"lname\":\"" + lname + "\",\"style\":\"" + style + "\"}";
    var json = {"id": 0, "fname": fname, "lname": lname, "style": style};
    // console.log(json);
    var updateUrl = "http://localhost:8080/api/teacher/add";
    $.ajax({
        url: updateUrl,
        type: "POST",
        data: JSON.stringify(json),
        contentType: "application/json",
        dataType: "json",
        success: function (data, status, xhr) {
            // console.log(data);
            homePage();

        }
        ,
        error: function (jqXhr, textStatus, errorMessage) { // error callback
            console.log("ErrorIng: " + errorMessage);
        }
    });
}

function search() {
    var updateUrl = "http://localhost:8080/api/teacher/teachersname?name=";
    var name = $("#searchField").val();
    updateUrl += name;

    console.log(updateUrl);
    $.ajax({
        url: updateUrl,
        type: "GET",
        data: "query",
        contentType: "application/json",
        dataType: "json",
        success: function (myObj) {
            // var myObj = JSON.stringify(result);
            var txt = "";
            console.log(myObj);
            txt += "<table border='1px' class='table table-hover'>"

            txt += "<tr>"
            txt += "<th> ID </th>"
            txt += "<th> First Name </th>"
            txt += "<th> Last Name </th>"
            txt += "<th> Style </th>"
            // txt += "<th> Delete </th>"
            // txt += "<th> Update </th>"
            txt += "</tr>"
            var i = 1;
            for (x in myObj) {
                txt += "<tr>"
                txt += "<td>" + i + "</td>";
                txt += "<td>" + myObj[x].fname + "</td>";
                txt += "<td>" + myObj[x].lname + "</td>";
                txt += "<td>" + myObj[x].style + "</td>";
                // txt += "<td><button name=\"delete\" onclick=\"deleteById(" + myObj[x].id + ")\" type=\"button\" > Delete </button></td>";
                // txt += "<td><button name=\"update\" onclick=\"update(" + myObj[x].id + ")\" type=\"button\" > Update </button></td>";
                txt += "</tr>"
                i++;
            }


            txt += "</table>"
            $("#teachers").append(txt);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus);
        }
    });
}

function cancel() {
    $("#form").hide();
}

function insertPage() {
    window.location = "http://localhost:8080/insert.html";
}

function homePage() {
    window.location = "http://localhost:8080/home.html";

}

function seachPage() {
    this.location = "http://localhost:8080/search.html";
}
