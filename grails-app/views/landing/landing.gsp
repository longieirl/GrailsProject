<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"><!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width">
    <meta name="layout" content="landing"/>
    <title>Welcome to Landing Page</title>
    <asset:javascript src="application.js"/>
    <asset:stylesheet href="main.css"/>
    <asset:link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
</head>
<body ng-app="hackApp">

Status ::: ${ msg }

<div>
    {{ 'AngularJS World' | greet }}
</div>

<g:form controller="submitForm" action="save">
    <label>First Name: </label>
    <g:textField name="firstName"/><br/>

    <label>Last Name: </label>
    <g:textField name="lastName"/><br/>

    <g:submitButton name="create" value="Save"/>
</g:form>

<div id="content" class="container">
    <div ng-include="views/includes/footer.html"></div>
</div>

</body>
</html>