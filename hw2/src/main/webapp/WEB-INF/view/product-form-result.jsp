<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>

<body>
    <h1>All products:</h1>
    <ul>
        <c:forEach var="product" items="${products}">
            <li>
                <br>
                Id: ${product.id}
                <br>
                Title: ${product.title}
                <br>
                Cost: ${product.cost}
            </li>
        </c:forEach>
    </ul>
</body>

</html>
