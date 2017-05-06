<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">User Edit Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/admin/users/edit"  method="post" style="width: 100%">

        <input type="text" name="id" value=${user.id} hidden>

        Name:<br>
        <input type="text" name="name" value=${user.name} >
        <br><br>
        Password:<br>
        <input type="password" name="password" value=${user.password} >
        <br><br>
        Age:<br>
        <input type="text" name="age" value=${user.age}>
        <br><br>
        Gender:<br>
        <input type="radio" name="gender" value="male" <#if user.gender == "male">checked="checked" </#if>> Male
        <input type="radio" name="gender" value="female" <#if user.gender == "female">checked="checked"</#if> > Female

        <br><br>
        Phone:<br>
        <input type="text" name="phone" value=${user.phone}>
        <br><br>
        Email:<br>
        <input type="text" name="email" value=${user.email}>

        <br><br><br>
    <input type="submit" value="Submit">
</form>
</div>


</body>
</html>
