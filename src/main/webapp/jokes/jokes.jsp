<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Jokes</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
    <div id="app">
        <div class="container">
            <h1>Cool Jokes</h1>
            <nav class="mb-2 mt-2 d-flex align-items-center justify-content-end">
                <input class="form-control flex-grow-1" type="number" id="joke-input-id" placeholder="Joke id...">
                <div class="d-flex pl-2">
                    <button class="btn btn-primary text-nowrap" onclick="fetchJokes(document.querySelector('#joke-input-id').value)">
                        Fetch Id
                    </button>
                    <button class="btn btn-info text-nowrap ml-2" onclick="fetchJokes()">
                        Fetch All
                    </button>
                    <button class="btn btn-warning text-nowrap ml-2" onclick="fetchJokes('random')">
                        Fetch Random
                    </button>
                </div>
            </nav>
            <table id="joke-table" class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Category</th>
                        <th>Joke</th>
                        <th>Answer</th>
                    </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
    <script>
        const _pathContext = "${pageContext.request.contextPath}";
    </script>
    <script src="./jokes.js"></script>
</body>
</html>