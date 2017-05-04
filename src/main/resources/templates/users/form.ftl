<!DOCTYPE html>
<html>

<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">User Add Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/users/add"  method="post" style="width: 100%">
    Name:<br>
    <input a="text" name="name" >
    <br><br>
        Password:<br>
        <input type="password" name="password" >
        <br><br>
    Age:<br>
    <input type="text" name="age" >
    <br><br>
        Gender:<br>
        <input type="radio" name="gender" value="male"> Male
        <input type="radio" name="gender" value="female"> Female

        <br><br>
        Phone:<br>
        <input type="text" name="phone" >
        <br><br>
        Email:<br>
        <input type="text" name="email" >


    <br><br><br>
    <input type="submit" value="Submit">
</form>
</div>


</body>
</html>
