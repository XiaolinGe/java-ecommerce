<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
</head>
<body>
<div style="width: 80%; margin: 20px auto; font-size: 17px;">
    <a href="/admin/users/list" style="color: cadetblue; margin-right: 20px">Users</a>
    <a href="/admin/products/list" style="color: cadetblue; margin-right: 20px">Products</a>
    <a href="/admin/orders/list" style="color: cadetblue; margin-right: 20px">Orders</a>
   </div>

<div style="width: 300px; margin: 20px auto; font-size: 22px;">Front Product List Page</div>
<div style="width:80%; margin: 20px auto;">
    <br>

    <div style="color: #ffa41e">User :  ${user.name}</div>
    <a href="/front/users/logout" style="color: #ffa41e; float: right;"> Logout</a>
    <a href="/front/orders/cart" style="color: #ffa41e; float: right; margin-right: 20px;"> Cart</a>


    <br>


<div>
    <a href="/admin/products/form"><button>Add</button></a>
    <br>
</div>
    <br>

    <form action="/front/orders/add" method="post">
<table style="width: 100%">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Add to cart</th>
    </tr>
<#list products as product>
    <tr>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td><a href="/front/orders/addtocart/${product.id}">Add to cart</a></td>
    </tr>
</#list>

</table>

        <br>
        <input type="submit" value="Submit">
    </form>
</div>


</body>
</html>


