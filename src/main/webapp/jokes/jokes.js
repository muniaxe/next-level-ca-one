function fetchJokes(path = "") {
    const api = _pathContext + "/api/jokes/"

    // Check if status OK, cus why not?
    const data = fetch(api + path)
        .then(res => res.ok ? res.json() : null)
        .then(data => {
            // Make sure we always work with an array to simplify the response data.
           const tmpData = Array.isArray(data) ? [...data] : [data]
           populateJokesTable(tmpData);
        });
}

function populateJokesTable(data) {
    // Build our table rows if we received any data... or display that no data was found.
    const tableRows = data[0] ? data.map(el => {
        return `
            <tr>
                <td>${el.id}</td>
                <td>${el.category}</td>
                <td>${el.joke}</td>
                <td>${el.answer}</td>
            </tr>`
    }).join("") : "<tr>No data found.</tr>";

    document.querySelector("#joke-table tbody").innerHTML = tableRows;
}