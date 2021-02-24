const url = _pathContext + "/api/groupmembers/all"

fetch(url).then(response => response.json()).then(data => {
    console.log("data", data);
})

function createTable() {

    const data = fetch(url)
        .then((res) => res.json())
        .then(members => {
            const tableRows = members.map(member => {
                const row = `<tr>
                                            <th>${member.id} </th> 
                                            <td>${member.name} </td> 
                                            <td>${member.favoriteGames.join("<br>")} </td> 
                                           <tr/> `
                return row;
            })
            const rowsAsStr = tableRows.join("");
            document.getElementById("members").innerHTML = rowsAsStr
            return "?????"
        });
    console.log(data)
}
createTable()
document.getElementById("reloadButton").onclick = createTable;