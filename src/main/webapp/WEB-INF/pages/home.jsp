<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FutureMeals | Welcome</title>

    <!-- jQuery Version 1.11.0 -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>

    <!-- Touch Punch UI JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/touch-punch.js"></script>

    <!-- JQuery UI JavaScript -->
    <script src="${pageContext.request.contextPath}/static/js/jquery-ui.js"></script>


    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/css/4-col-portfolio.css" rel="stylesheet">

    <!-- JQuery UI CSS -->
    <link href="${pageContext.request.contextPath}/static/css/jquery-ui.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        .draggable { background: #eee; }
        .droppable { }
    </style>
    <script>
        $(function() {
            $( ".draggable" ).draggable();
            $( ".droppable" ).droppable({
                drop: function( event, ui ) {
                    $( this )
                            .addClass( "ui-state-highlight" )
                            .find( "p" )
                            .html( "Dropped!" );
                }
            });
        });
    </script>

</head>

<body>

<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">futuremeals.me</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="#">about</a>
                </li>
                <li>
                    <a href="#">search</a>
                </li>
                <li>
                    <a href="#">contact</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Page Content -->
<div class="container">
    <!-- Page Heading -->
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Popular Recipes
                <small><%--Secondary Text--%></small>
            </h1>
        </div>
    </div>
    <!-- /.row -->

    <c:forEach var="recipe" items="${popularRecipes}" varStatus="loopStatus">
        <c:set var="imageLink" value="${recipe.images}" />
        <c:if test="${loopStatus.count % 4 == 0}">
        <!-- Projects Row -->
        <div class="row">
        </c:if>
            <div class="col-xs-3">
                <div class="draggable clearfix media">
                    <a href="#">
                        <img class="pull-left" src="${imageLink[0].hostedSmallUrl}" alt="" />
                    </a>
                    <div class="caption">
                        <p><c:out value="${recipe.name}" /></p>
                    </div>
                </div>
            </div>
        <c:if test="${loopStatus.count % 4 == 0}">
        </div>
        <!-- /.row -->
        </c:if>
    </c:forEach>

    <hr>

    <!-- Pagination -->
    <div class="row text-center">
        <div class="col-lg-12">
            <ul class="pagination">
                <li>
                    <a href="#">&laquo;</a>
                </li>
                <li class="active">
                    <a href="#">1</a>
                </li>
                <li>
                    <a href="#">2</a>
                </li>
                <li>
                    <a href="#">3</a>
                </li>
                <li>
                    <a href="#">4</a>
                </li>
                <li>
                    <a href="#">5</a>
                </li>
                <li>
                    <a href="#">&raquo;</a>
                </li>
            </ul>
        </div>
    </div>
    <!-- /.row -->

    <hr>

    <div class="container-fluid">
    <!-- Projects Row -->
    <div class="row row-centered">
        <div class="col-lg-1 col-centered">
            <form class="form-horizontal">
                <div class="form-group">
                    <input id="z" class="droppable form-control" value="MONDAY" />
                    <input id="a" class="form-control" value="TUESDAY" />
                    <input id="b" class="form-control" value="WEDNESDAY" />
                    <input id="c" class="form-control" value="THURSDAY" />
                    <input id="d" class="form-control" value="FRIDAY" />
                    <input id="e" class="form-control" value="SATURDAY" />
                    <input id="f" class="form-control" value="SUNDAY" />
                </div>
            </form>
        </div>
    </div>
    <!-- /.row -->
    </div>

    <div id="draggable" class="ui-widget-content">
        <p>Drag me to my target</p>
    </div>

    <div class="droppable ui-widget-header">
        <p>Drop here</p>
    </div>

    <hr>

    <!-- Footer -->
    <footer>
        <div class="row">
            <div class="col-lg-12">
                <p>Copyright &copy; futuremeals.me 2014</p>
            </div>
        </div>
        <!-- /.row -->
    </footer>

</div>
<!-- /.container -->

</body>

</html>
