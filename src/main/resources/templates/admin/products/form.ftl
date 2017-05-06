<!DOCTYPE html>
<html>

<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">Product Add Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/admin/products/add" method="post"  enctype="multipart/form-data" style="width: 100%">
        Name:<br>
        <input a="text" name="name">
        <br><br>
        Price:<br>
        <input type="text" name="price">
        <br><br>
        Quantity:<br>
        <input type="text" name="quantity">
        <br><br>

        Image:<br>
        <input type="file" name="file">
        <br><br>

        <br><br><br>
        <input type="submit" value="Submit">
    </form>
</div>


</body>
</html>
