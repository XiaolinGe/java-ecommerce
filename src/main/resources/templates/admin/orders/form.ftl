<!DOCTYPE html>
<html>

<body>
<div style="width: 200px; margin: 20px auto; font-size: 22px;">Order Add Page</div>
<div style="width: 40%; margin:20px auto;">
    <form action="/admin/orders/add"  method="post" style="width: 100%">
        Price:<br>
        <input type="text" name="price" >
        <br><br>
        Note:<br>
        <input type="text" name="note">
        <br><br>

        User:<br>
        <select name="userId" >
            <#list users as user>
                <option value=${user.id}>${user.name}</option>
            </#list>
        </select>
        <br><br>

        Products:<br>
        <select name="productIds" multiple>
        <#list products as product>
            <option value=${product.id}>${product.name}</option>
        </#list>
        </select>
        <br><br>


    <br><br><br>
    <input type="submit" value="Submit">
</form>
</div>


</body>
</html>
