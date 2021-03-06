<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            $( ".draggable" ).draggable({
                revert: "invalid",
                helper: "clone"
            });
            $( "#mon, #tue, #wed, #thu, #fri, #sat, #sun" ).droppable({
                accept: ".media",
                drop: Drop
            });

            function Drop(event, ui) {
                var draggableId = ui.draggable.attr("id");
                var droppableId = $(this).attr("id");
                $( this )
                        .addClass( "ui-state-highlight" )
                        .find( "p" )
                        .html( droppableId );
                updateForm(draggableId, droppableId);
            }

            function updateForm(draggableId, droppableId) {
                $("#id_"+droppableId).val($("#id_"+droppableId).val() + "."+draggableId + ",");
                console.log($("#id_mon").attr("value"));
            }
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
                    <sec:authorize access="!isAuthenticated()">
                        <a href="${pageContext.request.contextPath}/login">login</a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <a href="<c:url value='/j_spring_security_logout' />">logout</a>
                    </sec:authorize>
                </li>
                <li>
                    <sec:authorize access="!isAuthenticated()">
                        <a href="${pageContext.request.contextPath}/register">register</a>
                    </sec:authorize>
                </li>
                <li>
                    <a href="#">search</a>
                </li>
                <li>
                    <a href="#">contact</a>
                </li>
                <li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <a href="<c:url value='/admin' />">Admin</a>
                    </sec:authorize>
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
                <div id="${recipe.id}" class="draggable clearfix media" style="cursor: pointer;">
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
        <div class="col-centered">
            <div class="form-group">
                <div id="mon" class="droppable form-control">
                    <p>MONDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="tue" class="droppable form-control">
                    <p>TUESDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="wed"  class="droppable form-control">
                    <p>WEDNESDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="thu"  class="droppable form-control">
                    <p>THURSDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="fri"  class="droppable form-control">
                    <p>FRIDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="sat"  class="droppable form-control">
                    <p>SATURDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="sun"  class="droppable form-control">
                    <p>SUNDAY</p>
                </div>
            </div>
        </div>
        <div class="col-centered">
            <div class="form-group">
                <div id="weekRecipeForm"  class="droppable form-control">
                    <sf:form action="${pageContext.request.contextPath}/savecollection" method="post" autocomplete="off" commandName="weeklyRecipe">
                        <sf:input id="id" name="id" type="hidden" path="id" />
                        <sf:input id="id_mon" type="hidden" name="id_mon" value="" path="id_mon"/>
                        <sf:input id="id_tue" type="hidden" name="id_tue" value="" path="id_tue"/>
                        <sf:input id="id_wed" type="hidden" name="id_wed" value="" path="id_wed"/>
                        <sf:input id="id_thu" type="hidden" name="id_thu" value="" path="id_thu"/>
                        <sf:input id="id_fri" type="hidden" name="id_fri" value="" path="id_fri"/>
                        <sf:input id="id_sat" type="hidden" name="id_sat" value="" path="id_sat"/>
                        <sf:input id="id_sun" type="hidden" name="id_sun" value="" path="id_sun"/>
                        <input type="submit" value="save" />
                    </sf:form>
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->
    </div>

    <c:forEach var="weekRecipe" items="${weeklyRecipeList}">
            <c:out value="${weekRecipe.id}}" />
            <c:out value="${weekRecipe.id_mon}}" />
            <c:out value="${weekRecipe.id_tue}}" />
            <c:out value="${weekRecipe.id_wed}}" />
            <c:out value="${weekRecipe.id_thu}}" />
    </c:forEach>

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
