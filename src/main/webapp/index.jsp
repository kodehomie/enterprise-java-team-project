<%--
Author: Justin Hoang
Date: 2022-Apr-14-Thu
Time: 5:44 PM
--%>
<%@ page contentType = "text/html;charset=UTF-8" language = "java" %>
<!doctype html>
<html lang = "en">
<head>
  <!-- Required meta tags -->
  <meta charset = "utf-8">
  <meta name = "viewport" content = "width=device-width, initial-scale=1">

  <!-- Bootstrap CSS -->
  <link href = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
        rel = "stylesheet"
        integrity = "sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
        crossorigin = "anonymous">

  <title>Genre</title>
  <script type="text/javascript" >
    function runJSON() {
      let searcher = document.getElementsByName("searchTerm")[0].value;
      window.location.href = "http://localhost:8080/cosmic_hippo_war/api/json/" + searcher;
    }

    function runXML() {
      let search2 = document.getElementsByName("searchTerm2")[0].value;
      window.location.href = "http://localhost:8080/cosmic_hippo_war/api/xml/" + search2;
    }
  </script>

</head>
<body>
<h2>JSON Response</h2>
<form action = "javascript:runJSON()" class = "form-inline" method="post">
  <div class = "form-group">
    <label for = "searchTerm">Search</label>
    <input type = "text" class = "form-control" id = "searchTerm"
           name = "searchTerm" aria-describedby = "searchTermHelp"
           placeholder = "Enter genre">
  </div>
  <button type = "submit" name = "submit" value = "search"
          class = "btn btn-primary">Search
  </button>
</form>
<br><br>
<h2>XML Response</h2>
<form action = "javascript:runXML()" class = "form-inline" method="post">
  <div class = "form-group">
    <label for = "searchTerm">Search</label>
    <input type = "text" class = "form-control" id = "searchTerm2"
           name = "searchTerm2" aria-describedby = "searchTermHelp"
           placeholder = "Enter genre">
  </div>
  <button type = "submit" name = "submit" value = "search"
          class = "btn btn-primary">Search
  </button>
</form>
<!--Bootstrap Bundle with Popper-->
<script src = "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity = "sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin = "anonymous"></script>

</body>
</html>
