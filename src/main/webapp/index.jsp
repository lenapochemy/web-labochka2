<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dots.Dot"%>
<%@ page import="dots.DotsStorage" %>

<!DOCTYPE html>
<html lang="ru-RU">
<head>
    <meta charset="UTF-8">
    <title>labochka from lenochka</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body >
<table width="100%">
    <!-- <tr>
         <th colspan="5" id="my_name">Русакова Елена Дмитриевна, P3217, вариант 2701</th>
     </tr> -->
    <h1 id="my_name">Русакова Елена Дмитриевна, P3217, вариант 1756</h1>
    <!-- <tr>
        <td colspan="5" id="instruction">Чтобы узнать, попала ли точка в закрашенную область, введите параметры:</td>
    </tr> -->
    <tr>
        <!-- выбор х -->
        <td class="param">
            <p>x:</p>
        </td>
        <td>

            <input type="radio" id="x1" name="x" value="-2" checked />
            <label for="x1">-2</label>

            <input type="radio" id="x2" name="x" value="-1,5" />
            <label for="x2">-1,5</label>

            <input type="radio" id="x3" name="x" value="-1" />
            <label for="x3">-1</label>

            <input type="radio" id="x4" name="x" value="-0,5" />
            <label for="x4">-0,5</label>

            <input type="radio" id="x5" name="x" value="0" />
            <label for="x5">0</label>

            <input type="radio" id="x7" name="x" value="0,5" />
            <label for="x7">0,5</label>

            <input type="radio" id="x8" name="x" value="1" />
            <label for="x8">1</label>

            <input type="radio" id="x9" name="x" value="1,5" />
            <label for="x9">1,5</label>

            <input type="radio" id="x10" name="x" value="2" />
            <label for="x10">2</label>

        </td>


        <!-- график -->
        <td rowspan="4">
            <canvas width="500" height="500" id="canvas" >

            </canvas>
        </td>
        <!-- просто картинка со свинкой -->
        <td rowspan="4">
            <img src="img/pig.jpg">
        </td>
    </tr>
    <tr>
        <!-- выбор у -->
        <td class="param">
            <p>y:</p>
        </td>
        <td>
            <input type="text" id="y" name="y">
        </td>
    </tr>
    <tr>
        <!-- выбор r -->
        <td class="param">
            <p>R:</p>
        </td>
        <td>
            <input type="button" id="r1" name="r" value="1">
            <input type="button" id="r2" name="r" value="1,5">
            <input type="button" id="r3" name="r" value="2">
            <input type="button" id="r4" name="r" value="2,5">
            <input type="button" id="r5" name="r" value="3">
        </td>
    </tr>
    <tr>
        <td colspan="3">
            <!-- кнопка для проверки данных
            <form action="php/script.php" method="GET"> -->
            <form>
                <button type="button" id="check" name="check_button">Проверить</button>
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2" id="result_in_table" >
            <h4>
                <span class="outputStub notification"></span>
            </h4>
            <%  DotsStorage dots = (DotsStorage) request.getSession().getAttribute("dots");
                if(dots == null) {
            %>
            <table border="1" id="result">
                <caption>Все результаты</caption>
                <thead>
                <th>x</th>
                <th>y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Текущее время</th>
                </thead>
            </table>
            <% } else { %>
            <table  border="1" id="result" >
                <caption>Все результаты</caption>
                <thead>
                <th>x</th>
                <th>y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Текущее время</th>
                </thead>
                <tbody>
                <% for(Dot dot : dots.getDots()) { %>
                <tr>
                    <td> <%=dot.getX()%> </td>
                    <td> <%=dot.getY()%> </td>
                    <td> <%=dot.getR()%> </td>

                    <td> <%= dot.isHit() ? "Точка попала"
                            : "Точка не попала"%>
                    </td>
                    <td> <%=dot.getTime()%> </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } %>
        </td>
    </tr>

</table>

<script type="module" src="main.js"></script>

</body>
</html>