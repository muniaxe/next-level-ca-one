let boys = ["Peter", "Lars", "Ole"];
let girls = ["Janne", "Hanne", "Sanne"];
let all = boys.concat(girls);
let sorted = false;

//Creates table rows and table data from array
function toTableData(arr) {
    return arr.map(name => `<tr><td>` + name + `</td></tr>`).join("");
}

//Sets all tables with appropriate data
function populate() {
    document.getElementById("tableBoys").innerHTML = toTableData(boys);
    document.getElementById("tableGirls").innerHTML = toTableData(girls);
    console.log(all);
    if (sorted == true) {
        sortAll();
        document.getElementById("tableAll").innerHTML = toTableData(all);
    }
    else {
        document.getElementById("tableAll").innerHTML = toTableData(all);
    }

}

//Global button listener to repopulate tables
let container = document.getElementById("container");
container.addEventListener('click', (event) => {
    const isButton = event.target.nodeName === 'BUTTON';
    if (!isButton) {
        return;
    }
    populate();
})

//Button listener for adding names to tables
let addNameRow = document.getElementById("addName");
addNameRow.addEventListener('click', (event) => {
    const isButton = event.target.nodeName === 'BUTTON';
    if (!isButton) {
        return;
    }
    const btnPressed = event.target.id;
    let name = "";
    if (btnPressed == 'btnAddBoy') {
        name = document.getElementById("boyName").value;
        if (name.length <= 0) {
            return;
        }
        boys.push(name);
        document.getElementById("boyName").value = "";
    }
    else if (btnPressed == 'btnAddGirl') {
        let name = document.getElementById("girlName").value;
        if (name.length <= 0) {
            return;
        }
        girls.push(name);
        document.getElementById("girlName").value = "";
    }
    all = boys.concat(girls);
})

//Button listener for removing names from table
let alterTable = document.getElementById("alterTable");
alterTable.addEventListener('click', (event) => {
    if (! event.target.classList.contains("remove")) {
        return;
    }
    const radioChecked = document.querySelector('input[name="removeFrom"]:checked').value;
    const btnClicked = event.target.id;
    let name = "";
    if (btnClicked == 'btnRemoveBoy') {
        if (radioChecked == 'first') {
            name = boys.shift();
        }
        else if (radioChecked == 'last') {
            name = boys.pop();
        }
    }
    else if (btnClicked == 'btnRemoveGirl') {
        if (radioChecked == 'first') {
            name = girls.shift();
        }
        else if (radioChecked == 'last') {
            name = girls.pop();
        }
    }
    console.log(name);
    let index = all.indexOf(name);
    all.splice(index, 1);
})

//Reversing all names
document.getElementById("btnReverse").onclick = function(e) {
    all = all.reverse();
    sorted = false;
}

//Sorting all names
function sortAll() {
    all = all.sort((a, b) => a.localeCompare(b));
    sorted = true;
}

document.getElementById("btnSort").onclick = sortAll;