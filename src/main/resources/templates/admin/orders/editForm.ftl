<!DOCTYPE html>
<html>
<head>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>
<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">Order Edit Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/admin/orders/edit"  method="post" style="width: 100%">

        <input type="text" name="id" value=${order.id} hidden>

        Price:<br>
        <input type="text" name="price" value=${order.price} >
        <br><br>

        Note:<br>
        <input type="text" name="note" value=${order.note}>
        <br><br>

        <br><br><br>
    <input type="submit" value="Submit">
</form>
</div>


</body>
</html>
