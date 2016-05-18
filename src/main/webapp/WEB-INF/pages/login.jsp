<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <title>Login</title>
    <!-- Bootstrap core CSS -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/resources/bootstrap/css/sticky-footer.css" rel="stylesheet">
    <link href="/resources/bootstrap/css/signin.css" rel="stylesheet">

    <script>
        function checkPass() {
            with (document)
                getElementById('info').innerHTML = (getElementById('pass1').value != getElementById('pass2').value) ?
                        'Second password does not match!' : 'Ok!';
        }
    </script>

</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">On-line calculator</a>
        </div>
        <div class="navbar-collapse collapse">
            <form class="navbar-form navbar-right" role="form" method="post" action="/loginUser">
                <div class="form-group">
                    <input type="text" name="username" placeholder="Login" class="form-control">
                </div>
                <div class="form-group">
                    <input type="password" name="password"  placeholder="Password" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                </div>
                <button type="submit" class="btn btn-success">Sign in</button>
            </form>
        </div><!--/.navbar-collapse -->
    </div>
</div>

<!-- Begin page content -->
<div class="container">
    <p class="alert-info"><em>${result}</em></p>
    <div class="row">
        <div class=".col-md-4">
                    <form class="form-signin" role="form" method="post" action="/register">
                        <h2 class="form-signin-heading">Not a member</h2>
                        <h4>To register enter unique username and password twice</h4>
                        <input type="text" class="form-control" name="username" placeholder="Username" required
                               autofocus><br>
                        <input type="password" id="pass1" class="form-control" name="password" placeholder="Password"
                               required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"><br>
                        <label class="control-label" for="pass1"><em>Password must contain at least one number and one
                            uppercase
                            and lowercase letter, and at
                            least 8 or more characters</em>
                        </label>
                        <input type="password" id="pass2" class="form-control" name="" onchange="checkPass ()"
                               placeholder="Password again" required
                               pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}">
                        <b style="color: red" id="info"></b><br>
                        <a href="/">
                            <button class="btn btn-lg btn-success" type="submit">Register me</button>
                        </a>
                    </form>

        </div>
    </div>
</div>
<div id="footer">
    <div class="container">

    </div>
</div>
<script src="/resources/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
