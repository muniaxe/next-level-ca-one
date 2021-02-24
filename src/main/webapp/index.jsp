<!DOCTYPE html>
<html>
<head>
    <title>Next Level CA1 Assignment</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th>
            ID
        </th>
        <th>
            Navn
        </th>
        <th>
            Favorit Spil
        </th>
    </tr>
    </thead>
    <tbody id="members">

    </tbody>
</table>
<button id="reloadButton">
    Reload Names
</button>
<ul>
    <li>
        <a href="groupcontract.html">
            Group Contract & Individual work
        </a>
    </li>
    <li>
        <a href="jokes/jokes.jsp">
            Jokes page by Dyrhoi
        </a>
    </li>
    <li>
        <a href="manip.html">
            DOM Manipulation by Hvid
        </a>
    </li>
    <li>
        <a href="https://github.com/muniaxe/next-level-ca-one">
            GitHub repo
        </a>
    </li>
</ul>
</body>
<script>
    const _pathContext = "${pageContext.request.contextPath}";
</script>
<script src="members.js"></script>
</html>