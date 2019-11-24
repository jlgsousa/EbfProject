//var employeeList = [{"id":0,"firstName":"John","lastName":"Doe","email":"johnDoe@gmail.com","address":"johnDoeStreet","salary":10000.0,"companyId":1},{"id":1,"firstName":"Charles","lastName":"Button","email":"charlesBut@gmail.com","address":"johnDoeStreet","salary":10000.0,"companyId":2},{"id":3,"firstName":"Darius","lastName":"Mud","email":"dariusMud@gmail.com","address":"johnDoeStreet","salary":10000.0,"companyId":4},{"id":2,"firstName":"Bruno","lastName":"Krazinski","email":"brunoKraz@gmail.com","address":"johnDoeStreet","salary":10000.0,"companyId":3}];
//var company = {"companyId":3, "name":"Google"}
//var avgSalary = 18000;
var employeeList;
var company;
var avgSalary;

var url = "http://localhost:8080";

  function buildEmployeesTable(selector) {
      
    document.getElementById("show").style.display = "none";

    loadEmployees();

    var columns = addAllColumnHeaders(employeeList[0], selector);
  
    for (var i = 0; i < employeeList.length; i++) {
      var row$ = $('<tr/>');
      var cellValue;

      //Omit the employee id
      for (var colIndex = 0; colIndex < columns.length; colIndex++) {
        cellValue = employeeList[i][columns[colIndex]];

        if (cellValue == null) cellValue = "";

        row$.append($('<td/>').html(cellValue));
      }
      //Last cell value corresponds to company id
      row$.append('<td><button type="button" onclick="showCompany(' + cellValue + ')">Company details</button></td>');
      $(selector).append(row$);
    }
  }

  function addAllColumnHeaders(employee, selector) {
    var columnSet = [];
    var headerTr$ = $('<tr/>');


    for (var key in employee) {
       if (key !== "id") {
            columnSet.push(key);
            headerTr$.append($('<th/>').html(key));
       }
    }
    $(selector).append(headerTr$);
  
    return columnSet;
  }

  function buildCompanyTable(selector) {
    $(selector).children("tbody").remove()
    var headerTr$ = $('<tr/>');

    headerTr$.append($('<th/>').html("Company Name"));
    headerTr$.append($('<th/>').html("Average Salary"));
    $(selector).append(headerTr$);

    var row$ = $('<tr/>');
    row$.append($('<td/>').html(company.name));
    row$.append($('<td/>').html(avgSalary));
    $(selector).append(row$);
  }

  function hideEmployeeIds() {
      var tbl = document.getElementById("employeesTable");
      var rows = tbl.getElementsByTagName('tr');

    for (var row=0; row<rows.length;row++) {
      var cels = rows[row].getElementsByTagName("td");
      cels[0].style.display="none";
    }
  }

function showCompany(companyId) {
    loadCompany(companyId);
    loadAverageSalary(companyId);

    buildCompanyTable('#companyOverlayTable');

    document.getElementById("companyOverlay").style.display = "block";
}

function hideCompany() {
    document.getElementById("companyOverlay").style.display = "none";
}

function loadEmployees() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.status == 200) {
            employeeList = eval(this.response);
        }
    };
    xhttp.open("GET", url + "/employees", false);
    xhttp.setRequestHeader("Access-Control-Allow-Origin", "localhost:8080/employees");
    xhttp.send();
}

function loadCompany(companyId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.status == 200) {
            company = JSON.parse(this.response);
        }
    };
    xhttp.open("GET", url + "/companies/" + companyId, false);
    xhttp.send();
}

function loadAverageSalary(companyId) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.status == 200) {
            avgSalary = this.responseText;
        }
    };
    xhttp.open("GET", url + "/companies/" + companyId + "/avgSalary", false);
    xhttp.send();
}