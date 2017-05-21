<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">product Edit Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/admin/products/edit"  method="post" enctype="multipart/form-data" style="width: 100%">

        <input type="text" name="id" value=${product.id} hidden>

        Name:<br>
        <input type="text" name="name" value=${product.name} >
        <br><br>
        Price:<br>
        <input type="text" name="price" value=${product.price} >
        <br><br>
        Quantity:<br>
        <input type="text" name="quantity" value=${product.quantity}>
        <br><br>

        Image:<br>
        <input type="file" name="file" value=${product.image} />
        <img src="/${product.image}" style="width: 150px;"/>
        <br><br>

        <br><br><br>
    <input type="submit" value="Submit">
</form>
</div>


</body>
</html>
