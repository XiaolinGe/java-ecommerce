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

<div style="width: 200px; margin: 20px auto; font-size: 22px;">product List Page</div>
<div style="width:80%; margin: 20px auto;">
<div>
    <a href="/admin/products/form"><button>Add</button></a>
    <br>
    <br>
    <form  action="/admin/products/list" method="get" >
        <input type="text" width="200"  name="name" placeholder="name">
        <input type="text" width="200"  name="price" placeholder="price">
        <input type="text" width="200"  name="quantity" placeholder="quantity">
        <input type="submit" value="Search">
    </form>

</div>
    <br>
<table style="width: 100%">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Actions</th>
    </tr>
<#list products as product>
    <tr>
        <td>${product.id}</td>
        <td>${product.name}</td>
        <td>${product.price}</td>
        <td>${product.quantity}</td>
        <td><a href="/admin/products/delete/${product.id}"><button>Delete</button></a> &nbsp;&nbsp;<a href="/admin/products/editForm/${product.id}"><button>Edit</button></a></td>
    </tr>
</#list>

</table>
</div>


</body>
</html>


