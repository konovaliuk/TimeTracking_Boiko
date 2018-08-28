<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mirosha
  Date: 22.08.2018
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<div>
    <form action="controller" method="post">
        <select name="assignment" style="width:auto;" multiple size="5">
            <option disabled>Assignments</option>

            <c:forEach items="${requestScope.assignments}" var="assignment">
                <option>
                    <c:out value="${assignment.userEmail} :"/>
                    <c:out value="${assignment.activityDescription}"/>
                </option>
            </c:forEach>
        </select>
        <c:forEach var="i" begin="1" end="${requestScope.pagesCount}">
            <a href="controller?command=showActiveAssignments&page=${i}"><c:out value="${i}"/></a>
        </c:forEach>
        <input type="hidden" name="command" value="adminPage">
        <input type="submit" value="Return">
    </form>
</div>
