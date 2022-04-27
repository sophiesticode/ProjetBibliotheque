
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<table class="form docSimple">
    <tr>
        <td>
            <%=((Document) request.getAttribute("doc")).toString()%>
        </td>
    </tr>
</table>

