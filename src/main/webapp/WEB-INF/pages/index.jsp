<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="description" content="Simple calculator, supported operators: +, -, *, /, (, ),">
    <title>Simple calculator of expression</title>
    <!-- Bootstrap core CSS -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/bootstrap/css/sticky-footer.css" rel="stylesheet">
</head>
<body>
    <!-- Begin page content -->
    <div class="container">
      <div class="page-header">
        <h1>Enter expression to calculate</h1>
        <h4>For example: 123.12 + 34 * (12 - 10)</h4>
      </div>

        <div class="col-xs-6 .col-md-4">
        <form method="post" action="/calculate">
          <div class="row">
            <input type="text" name="expression" placeholder="Put your expression" required/>
          </div><br>
          <div class="row">
                <input type="submit" value = "Calculate" class="btn btn-lg btn-success">
          </div><br>
        </form>

        <div class="row">
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">Result</h3>
            </div>
            <div class="panel-body">
              ${result}
            </div>
          </div>
            <button type="button" class="btn btn-lg btn-success"><a href="/">Try again</a></button>
        </div>

      </div>
    </div>

    <div id="footer">
      <div class="container">
        <div class=".col-xs-4"><a href="/login" class="text-muted">Sign in</a></div>
        <div class=".col-xs-4"><a href="/logout" class="text-muted">Sign out</a></div>
      </div>
    </div>
</body>
</html>
