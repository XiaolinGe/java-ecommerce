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
    <a href="/users/list" style="color: cadetblue; margin-right: 20px">Users</a>
    <a href="/products/list" style="color: cadetblue; margin-right: 20px">Products</a>
    <a href="/orders/list" style="color: cadetblue; margin-right: 20px">Orders</a>
   </div>

<div style="width: 200px; margin: 20px auto; font-size: 22px;">User List Page</div>
<div style="width:80%; margin: 20px auto;">
<div>
    <a href="/users/form"><button>Add</button></a>
    <br>
    <br>
    <form  action="/users/list" method="get" >
        <input type="text" width="200"  name="name" placeholder="name">
        <input type="text" width="200"  name="age" placeholder="age">
        <input type="text" width="200"  name="password" placeholder="password">
        <input type="submit" value="Search">
    </form>

</div>
    <br>
<table style="width: 100%">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Password</th>
        <th>Age</th>
        <th>Gender</th>
        <th>Phone</th>
        <th >Email</th>
        <th>Actions</th>
    </tr>
<#list users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.password}</td>
        <td>${user.age}</td>
        <td>${user.gender}</td>
        <td>${user.phone}</td>
        <td>${user.email}</td>
        <td><a href="/users/delete/${user.id}"><button>Delete</button></a> &nbsp;&nbsp;<a href="/users/editForm/${user.id}"><button>Edit</button></a></td>
    </tr>
</#list>

</table>
</div>


</body>
</html>


