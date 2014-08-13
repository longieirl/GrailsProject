<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Landing Page</title>
</head>
<body>

Status ::: ${ msg }

<g:form controller="submitForm" action="save">
    <label>First Name: </label>
    <g:textField name="firstName"/><br/>

    <label>Last Name: </label>
    <g:textField name="lastName"/><br/>

    <g:submitButton name="create" value="Save"/>
</g:form>

</body>
</html>