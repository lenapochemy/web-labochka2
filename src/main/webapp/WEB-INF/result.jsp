<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dots.Dot" %>
<%@ page import="dots.DotsStorage" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="java.util.stream.Collectors" %>


<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>labochka from lenochka</title>
    <link href="../style.css" rel="stylesheet" type="text/css">
</head>
<body >
<table width="100%">
    <!-- <tr>
         <th colspan="5" id="my_name">Русакова Елена Дмитриевна, P3217, вариант 2701</th>
     </tr> -->
    <h1 id="my_name">Русакова Елена Дмитриевна, P3217, вариант 1756</h1>

    <tr>
        <td colspan="5" id="result_in_table" >
            <%  DotsStorage dots = (DotsStorage) request.getSession().getAttribute("dots");
            %>
        <table  border="1" >
            <caption>Все результаты</caption>
            <thead>
                <th>x</th>
                <th>y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Текущее время</th>
            </thead>
            <tbody>
            <% List<Dot> list = dots.getDots(); %>
            <% list = list.stream().sorted(Comparator.comparing(Dot::getTime).reversed()).collect(Collectors.toList()); %>
            <% for(Dot dot : list) { %>
            <tr>
                <td> <%=dot.getX()%> </td>
                <td> <%=dot.getY()%> </td>
                <td> <%=dot.getR()%> </td>
                <td> <%= dot.isHit() ? "Точка попала"
                    : "Точка не попала"%>
                </td>
                <td> <%= dot.getTime() %> </td>
            </tr>
            <% } %>
            </tbody>
        </table>

        </td>
    </tr>
    <tr>
        <td>
            <a href="/labochka2/index.jsp" class="back_to_form">Обратно к форме</a>
        </td>
    </tr>


</table>

<script type="text/javascript" src="../main.js"></script>

</body>
</html>
