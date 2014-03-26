<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="request" value="User Profile"/>
<%@ include file="header.jsp" %>

<div class="page-action">Your details</div>
        <div id="user-details" >
            ${userDetail.account_name} - ${userDetail.email_address}
                <c:if test="${not empty userDetail.country_id}">-${userDetail.getCountry().getCountry_name()}</c:if>
                <br />

        </div>

		<div class="page-action">Your Orders</div>
		<table class="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Description</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${items}" varStatus="row">
                <tr>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td><c:out value="${item.description}"/></td>
                    <td><c:out value="${item.type}"/></td>
                </tr>
             </c:forEach>
            </tbody>
        </table>

<%@ include file="footer.jsp" %>