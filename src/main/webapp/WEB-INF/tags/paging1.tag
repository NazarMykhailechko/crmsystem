<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ tag import="org.springframework.util.StringUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pagedListHolder1" required="true"
              type="org.springframework.beans.support.PagedListHolder"%>
<%@ attribute name="pagedLink1" required="true" type="java.lang.String"%>
<link rel="stylesheet"
      href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
        src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
        src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<c:if test="${pagedListHolder1.pageCount > 1}">
    <ul class="pagination">
        <c:if test="${!pagedListHolder1.firstPage}">
            <li class="previous"><a
                    href="<%=StringUtils.replace(pagedLink1, "~", String.valueOf(pagedListHolder1.getPage() - 1))%>"><</a></li>
        </c:if>
        <c:if test="${pagedListHolder1.firstLinkedPage > 0}">
            <li><a href="<%=StringUtils.replace(pagedLink1, "~", "0")%>">1</a></li>
        </c:if>
        <c:if test="${pagedListHolder1.firstLinkedPage > 1}">
        <li><span class="pagingDots">...</span>
        <li>
            </c:if>
            <c:forEach begin="${pagedListHolder1.firstLinkedPage}"
                       end="${pagedListHolder1.lastLinkedPage}" var="i">
            <c:choose>
            <c:when test="${pagedListHolder1.page == i}">
        <li class="active"><a href="#">${i+1}</a></li>
        </c:when>
        <c:otherwise>
            <li><a
                    href="<%=StringUtils.replace(pagedLink1, "~", String.valueOf(jspContext.getAttribute("i")))%>">${i+1}</a>
            </li>
        </c:otherwise>
        </c:choose>
        </c:forEach>
        <c:if
                test="${pagedListHolder1.lastLinkedPage < pagedListHolder1.pageCount - 2}">
            <li><span class="pagingDots">...</span></li>
        </c:if>
        <c:if
                test="${pagedListHolder1.lastLinkedPage < pagedListHolder1.pageCount - 1}">
            <li><a
                    href="<%=StringUtils.replace(pagedLink1, "~", String.valueOf(pagedListHolder1.getPageCount() - 1))%>">${pagedListHolder1.pageCount}</a></li>
        </c:if>
        <c:if test="${!pagedListHolder1.lastPage}">
            <li class="next"><a
                    href="<%=StringUtils.replace(pagedLink1, "~", String.valueOf(pagedListHolder1.getPage() + 1))%>">></a></li>
        </c:if>
    </ul>
</c:if>