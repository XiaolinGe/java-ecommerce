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

<div style="width: 300px; margin: 20px auto; font-size: 22px;">Shooping Cart Page</div>
<div style="width:80%; margin: 20px auto;">
    <br>
    <br>

    <form action="" method="post">
    <table style="width: 100%">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Quantity In Cart</th>
        </tr>
    <#list productVOs as productVO>
        <tr>
            <td>${productVO.id}</td>
            <td>${productVO.name}</td>
            <td><input type="number" value=${productVO.quantityInCart} ></td>
        </tr>
    </#list>

    </table>

        <br>
        <input type="submit" value="Submit">
    </form>

</div>


</body>
</html>


