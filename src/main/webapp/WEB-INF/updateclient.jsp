<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <title>Кредитні заявки</title>

    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2-bootstrap-css/1.4.6/select2-bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/css/select2.min.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.2.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.4/js/select2.min.js"></script>

    <style>
        .blue-button{
            background: #25A6E1;
            padding:3px 5px;
            color:#fff;
            font-family:'Helvetica Neue',sans-serif;
            font-size:12px;
            border-radius:2px;
            -moz-border-radius:2px;
            -webkit-border-radius:4px;
            border:1px solid #1A87B9
        }
        table {
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            width: 50%;
        }
        th {
            background: SteelBlue;
            color: white;
        }
        td,th{
            border: 1px solid gray;
            width: 25%;
            text-align: left;
            padding: 5px 10px;
        }
        .text {
            text-align:  center;
        }
        h1 {
            color: red;
        }




        body {
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            /*font-weight: bold;*/
            font-size: small;
        }
        .select2-container {
            min-width: 105%;
            margin-bottom: 3px
        }

        .select2-results__option {
            padding-right: 20px;
            vertical-align: middle;
        }
        .select2-results__option:before {
            content: "";
            display: inline-block;
            position: relative;
            height: 20px;
            width: 20px;
            border: 2px solid #e9e9e9;
            border-radius: 4px;
            background-color: #fff;
            margin-right: 20px;
            vertical-align: middle;
        }
        .select2-results__option[aria-selected=true]:before {
            font-family:fontAwesome;
            content: "\f00c";
            color: #fff;
            background-color: red;
            border: 0;
            display: inline-block;
            padding-left: 3px;
        }


        .select2-container--default .select2-results__option[aria-selected=true] {
            background-color: #fff;
        }
        .select2-container--default .select2-results__option--highlighted[aria-selected] {
            background-color: #eaeaeb;
            color: #272727;
        }
        .select2-container--default .select2-selection--multiple {
            margin-bottom: 10px;
        }
        .select2-container--default.select2-container--open.select2-container--below .select2-selection--multiple {
            border-radius: 4px;
        }

        .select2-container--default.select2-container--focus .select2-selection--multiple {
            border-color: red;
            border-width: 2px;
        }

        .select2-container--open .select2-dropdown--below {

            border-radius: 6px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);

        }
        .select2-selection .select2-selection--multiple:after {
            content: 'hhghgh';
        }

        /* select with icons badges single*/
        .select-icon .select2-selection__placeholder .badge {
            display: none;
        }

        .select-icon .placeholder {
            /* 	display: none; */
        }
        .select-icon .select2-results__option:before,
        .select-icon .select2-results__option[aria-selected=true]:before {
            display: none !important;
            /* content: "" !important; */
        }
        .select-icon  .select2-search--dropdown {
            display: none;
        }

        fieldset.inline {
            display: inline;
        }


        .column {
            float: left;
            width: 50%;
            padding: 10px;
            height: 300px; /* Should be removed. Only for demonstration */
        }

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }


             /*Style your div container, must specify height*/
         .content {width:100%; height:70%}
        /*Style the p tag inside your div container with half the with of your container, and float left*/
        .content p {width:48%; margin-right:5px;padding-right: 15px; float:left;}



/*        body {
            font-family: Arial, sans-serif;
            background: url(http://www.shukatsu-note.com/wp-content/uploads/2014/12/computer-564136_1280.jpg) no-repeat;
            background-size: cover;
            height: 100vh;
        }

        h1 {
            text-align: center;
            font-family: Tahoma, Arial, sans-serif;
            color: #06D85F;
            margin: 80px 0;
        }*/

        .box {
            width: 40%;
            margin: 0 auto;
            background: rgba(255,255,255,0.2);
            padding: 35px;
            border: 2px solid #fff;
            border-radius: 20px/50px;
            background-clip: padding-box;
            text-align: center;
        }

        .button {
            font-size: 1em;
            padding: 10px;
            color: #fff;
            border: 2px solid #06D85F;
            border-radius: 20px/50px;
            text-decoration: none;
            cursor: pointer;
            transition: all 0.3s ease-out;
        }
        .button:hover {
            background: #06D85F;
        }

        .overlay {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            right: 0;
            background: rgba(0, 0, 0, 0.7);
            transition: opacity 500ms;
            visibility: hidden;
            opacity: 0;
        }
        .overlay:target {
            visibility: visible;
            opacity: 1;
        }

        .popup {
            margin: 70px auto;
            padding: 20px;
            background: #fff;
            border-radius: 5px;
            width: 30%;
            position: relative;
            transition: all 5s ease-in-out;
        }

        .popup h2 {
            margin-top: 0;
            color: #333;
            font-family: Tahoma, Arial, sans-serif;
        }
        .popup .close {
            position: absolute;
            top: 20px;
            right: 30px;
            transition: all 200ms;
            font-size: 30px;
            font-weight: bold;
            text-decoration: none;
            color: #333;
        }
        .popup .close:hover {
            color: #06D85F;
        }
        .popup .content {
            max-height: 32%;
            overflow: auto;
        }

        @media screen and (max-width: 700px){
            .box{
                width: 70%;
            }
            .popup{
                width: 70%;
            }
        }


    </style>
</head>
<body>

<div class="text">
    <img src="data:image/svg+xml;base64,PHN2ZyBpZD0ibC11LWciIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiIHg9IjBweCIgeT0iMHB4IiB3aWR0aD0iMTg3LjdweCIgaGVpZ2h0PSI0NC44cHgiIHZpZXdCb3g9IjAgMCAxODcuNyA0NC44IiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCAxODcuNyA0NC44OyIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSI+DQo8c3R5bGUgdHlwZT0idGV4dC9jc3MiPg0KCS5zdDB7ZmlsbC1ydWxlOmV2ZW5vZGQ7Y2xpcC1ydWxlOmV2ZW5vZGQ7ZmlsbDojQ0MwMDAwO30NCgkuc3Qxe2ZpbGwtcnVsZTpldmVub2RkO2NsaXAtcnVsZTpldmVub2RkO2ZpbGw6Izk5OTk5OTt9DQo8L3N0eWxlPg0KPHBhdGggY2xhc3M9InN0MCIgZD0iTTQzLjYsMjcuNUwyMi4xLDlMMC42LDI3LjVDMC4yLDI1LjgsMCwyNC4yLDAsMjIuNEMwLDEwLDkuOSwwLDIyLjEsMGMxMi4yLDAsMjIuMSwxMCwyMi4xLDIyLjQgQzQ0LjIsMjQuMiw0NCwyNS44LDQzLjYsMjcuNSBNMTAxLDM1LjFjMy41LDAsNS4yLTAuMiw2LjQtMC41YzIuNC0wLjcsNC0yLjMsNC44LTQuOGMwLjMtMC44LDAuNC0xLjUsMC40LTIuMiBjMC0yLjMtMC45LTQuNC0yLjYtNS45Yy0xLjYtMS40LTMuNi0yLTYtMmgtNC4xYy0wLjUsMC4xLTEsMC4zLTEuMiwwLjdjLTAuMiwwLjQtMC40LDAuOC0wLjUsMS4zdjE5LjZoMi44VjM1LjEgTTEwOC4yLDIyLjggYzAuOCwxLjMsMS4zLDMuMSwxLjMsNC45YzAsMS43LTAuNCwzLjEtMS4zLDQuNGMtMSwxLjQtMi4zLDIuMS0zLjksMi4xbC0zLjMsMFYyMS4xYzAtMC4zLDAuMi0wLjUsMC41LTAuNWwyLjcsMCBDMTA1LjksMjAuNSwxMDcuMywyMS4zLDEwOC4yLDIyLjh6IE04OC4yLDM1LjVjMi4zLDAsNC4yLTAuNyw1LjctMi4yYzEuNS0xLjUsMi4zLTMuNCwyLjMtNS44YzAtMi4zLTAuOC00LjItMi4zLTUuNiBjLTEuNS0xLjUtMy40LTIuMi01LjYtMi4yYy0yLjMsMC00LjIsMC43LTUuNiwyLjJjLTEuNSwxLjUtMi4yLDMuNC0yLjIsNS43YzAsMi4zLDAuNyw0LjIsMi4zLDUuN0M4NC4xLDM0LjgsODYsMzUuNSw4OC4yLDM1LjUgTTkyLjMsMzIuMWMtMSwxLjctMi4zLDIuNi00LDIuNmMtMS43LDAtMy0wLjgtNC0yLjNDODMuNSwzMSw4MywyOS40LDgzLDI3LjZjMC0xLjksMC40LTMuNSwxLjItNC45YzEtMS42LDIuMy0yLjQsNC0yLjQgYzEuNiwwLDIuOSwwLjcsMy45LDIuMmMwLjksMS4zLDEuMywyLjksMS4zLDQuNkM5My41LDI5LjEsOTMuMSwzMC43LDkyLjMsMzIuMXogTTcyLjgsMjQuNmw1LjUtNWgtMS4ybC02LjcsNi4xdi02LjFoLTIuOHYxNS41IGwyLjgtMC4xdi04LjJsMC41LTAuNWw2LjksOC44aDMuMUw3Mi44LDI0LjZ6IE01Ni4yLDEzbC05LjgsMjIuMWgxbDIuOC02LjNoMTBsMi44LDYuM2gzTDU2LjIsMTN6IE01MC43LDI3LjlsNC42LTEwLjNsNC41LDEwLjMgSDUwLjd6IE0xMTQuNSwzNC4ybDUtMTIuM2MwLjEtMC4yLDAuMy0wLjQsMC41LDBjMS42LDQsMy4zLDguMyw0LjksMTIuM0gxMTQuNXogTTExMi4xLDMzLjljMCwwLjUsMC4xLDAuOCwwLjEsMWMwLDAuNiwwLDEtMC4xLDEuMiBoMC4yYzAuMy0wLjMsMC42LTAuNSwxLjEtMC43YzAuNC0wLjEsMC45LTAuMiwxLjUtMC4zYzAuNiwwLDEuNCwwLDIuNSwwaDUuOGMxLjEsMCwyLjYsMCwzLjIsMGMwLjUsMCwxLDAuMSwxLjQsMC4zIGMwLjQsMC4xLDAuNywwLjQsMC45LDAuN2gwLjJjMC0wLjMtMC4xLTAuNy0wLjEtMS4yYzAtMC40LDAtMC43LDAuMS0xaC0xbC01LjUtMTMuMWMtMC4xLTAuMy0wLjMtMC42LTAuNS0wLjggCWMtMC4yLTAuMi0wLjQtMC40LTAuNi0wLjVjLTAuNS0wLjItMC45LTAuMi0xLjQsMGMtMC4yLDAuMS0wLjMsMC4yLTAuNSwwLjRjLTAuMiwwLjItMC4zLDAuNC0wLjUsMC42bC01LjgsMTMuM0gxMTIuMXoiLz4NCjxwYXRoIGNsYXNzPSJzdDEiIGQ9Ik01LjQsMzcuMWwxNi43LTIzbDE2LjcsMjNjLTQuMSw0LjctMTAsNy43LTE2LjcsNy43QzE1LjQsNDQuOCw5LjQsNDEuOCw1LjQsMzcuMSBNMTcyLDE5LjZoLTIuOHY2LjRoLTguMXYtNi40IGgtMi44djE1LjVoMi44di04LjJoOC4xdjguMmgyLjhWMTkuNnogTTE1Mi4xLDIxTDE1Mi4xLDIxYy0wLjItMC40LTAuNC0wLjctMC42LTAuOWMtMC4yLTAuMi0wLjUtMC40LTAuNy0wLjVjLTAuNC0wLjEtMC43LTAuMS0xLjEsMGMtMC4yLDAuMS0wLjQsMC4yLTAuNiwwLjRjLTAuNCwwLjQtMC42LDAuOS0wLjgsMS41bC00LjUsMTMuNmgxLjFsMS4yLTMuN2g2LjhsMS4yLDMuN2gyLjdMMTUyLjEsMjF6ICBNMTQ2LjQsMzAuNGMxLTMsMi02LDMtOWMwLTAuMSwwLjItMC4zLDAuMywwbDIuOCw5SDE0Ni40eiBNMTMxLDIxLjdjMC4xLTAuNSwwLjItMC45LDAuNS0xLjNjMC4zLTAuNSwwLjgtMC43LDEuNC0wLjhsNy44LDB2MC45IGMtMi4yLDAtNC40LDAtNi42LDBjLTAuMiwwLTAuMywwLjEtMC4zLDAuNGwwLDUuNmMwLjIsMCwwLjYsMCwxLjIsMGMwLjUsMCwwLjksMCwxLjIsMGMyLDAsMy41LDAuMiw0LjUsMC43IGMxLjUsMC43LDIuMiwxLjksMi4yLDMuN2MwLDEuMy0wLjUsMi40LTEuNiwzLjJjLTEsMC43LTIuMSwxLjEtMy41LDEuMUgxMzFWMjEuOEMxMzEsMjEuOCwxMzEsMjEuNywxMzEsMjEuN0wxMzEsMjEuNyBNMTM2LjksMjcuNSBjMSwwLDEuOSwwLjMsMi42LDAuOWMwLjcsMC42LDEuMSwxLjQsMS4xLDIuNGMwLDEtMC40LDEuOC0xLjEsMi40Yy0wLjcsMC42LTEuNiwwLjktMi42LDAuOWgtM3YtNi42SDEzNi45eiBNMTc5LjcsMjQuNmw1LjUtNSBsLTEuMiwwbC02LjcsNnYtNi4xaC0yLjh2MTUuNWwyLjgtMC4xdi04LjJsMC41LTAuNWw2LjksOC44aDMuMUwxNzkuNywyNC42eiIvPg0KPC9zdmc+">
</div>
<%--<h5 class="text">Created by Nazar Mykhailechko</h5>
<br>--%>

<br>
<%--<h2 class="text">Нова кредитна заявка:</h2>--%>
<%--<% String homeDirectory = System.getProperty("user.name"); %>--%>
<%--<h3 class="text"><%=homeDirectory%></h3>--%>


<form:form action="/update" method="POST" modelAttribute="database" id="myform">
    <form:hidden path="pr" style="width:10%" placeholder="example" />

    <fieldset class="inline" style="margin-left: 10px;width:47%;height:40%;border: 1px solid crimson">
        <legend style="text-decoration: underline;color: crimson"><strong>ІНФОРМАЦІЯ ПРО КЛІЄНТА:</strong></legend>
        <%--<div style="white-space:nowrap">--%>
        <label for="client_name">Назва клієнта:</label>
        <form:input id="sel1" disabled="true" path="client_name" style="width:100%;margin-bottom: 5px" placeholder="example"/><br>
        <%--</div>--%>
        <label for="employee_num">Штат персоналу:</label>
        <form:input id="sel1" disabled="true" path="employee_num" style="width:100%;margin-bottom: 5px" placeholder="example"/><br>
        <label for="tender_num">К-ть тендерів за один рік:</label>
        <form:input id="sel1" disabled="true" path="tender_num" style="width:100%;margin-bottom: 5px" placeholder="example"/><br>
        <label for="tender_sum">Сума тендерів:</label>
        <form:input id="sel1" disabled="true" path="tender_sum" style="width:100%;margin-bottom: 5px" placeholder="example"/><br>
        <label for="full_address">Повна адреса:</label>
        <form:input id="sel" disabled="true" path="full_address" style="width:100%;margin-bottom: 5px" placeholder="example" /><br>
        <label for="person_details">Контактні телефони, особи, їх посади:</label>
        <form:input id="sel" disabled="true" path="person_details" style="width:100%;margin-bottom: 5px" placeholder="example" /><br>

    </fieldset>

    <fieldset class="inline" style="margin-left: 10px;width:47%;height:40%;border: 1px solid crimson">
        <legend style="text-decoration: underline;color: crimson"><strong>ФІНАНСОВА ДІЯЛЬНІСТЬ:</strong></legend>
            <%--<div style="white-space:nowrap">--%>
        <label for="kved_name">Сфера діяльності:</label>
        <form:input id="sel1" disabled="true" path="kved_name" style="width:100%;margin-bottom: 5px" placeholder="example"/>
        <label for="revenue_uah">Виручка,грн:</label>
        <form:input id="sel1" disabled="true" path="revenue_uah" style="width:100%;margin-bottom: 5px" placeholder="example"/>
        <label for="forex_per_year_usd">Річний потенціал по форексу, дол.США:</label>
        <form:input id="sel1" disabled="true" path="forex_per_year_usd" style="width:100%;margin-bottom: 5px" placeholder="example"/>
        <label for="passive_value_uah">Потенційний обсяг пасивів, грн.екв.:</label>
        <form:input id="sel1" disabled="true" path="passive_value_uah" style="width:100%;margin-bottom: 5px" placeholder="example"/>
        <label for="loan_value_uah">Рівень закредитованості:</label>
        <form:input id="sel1" disabled="true" path="loan_value_uah" style="width:100%;margin-bottom: 5px" placeholder="example"/>
            <%--</div>--%>
    </fieldset>

    <fieldset class="inline" style="margin-left: 10px;width:96.5%;height:40%;border: 1px solid crimson">


        <legend style="text-decoration: underline;color: crimson"><strong>РОБОТА З КЛІЄНТОМ:</strong></legend>

        <div class="content">
            <p><label for="last_date_contact">Дата останнього контакту за клієнтом:</label>
                <form:input id="sel1" type="date" disabled="false" path="last_date_contact" style="width:101%;margin-bottom: 5px" placeholder="example"/>
                <label for="client_history">Історія взаємовідносин з клієнтом/коментарі:</label>
                <form:textarea id="clienthistory" readonly="true" disabled="false" path="client_history" style="width:101%;height:100%;margin-bottom: 5px" placeholder="example"/></p>
            <p><label for="status">Статус:</label>

            <form:select id="status" name="sel" path="status" style="width:105%;margin-bottom: 5px;height:12%">
                <%--            <form:option value="NONE">Оберіть відповідь</form:option>--%>
                <form:option value="не був оброблений">не був оброблений</form:option>
                <form:option value="запланований повторний дзвінок">запланований повторний дзвінок</form:option>
                <form:option value="запланована зустріч">запланована зустріч</form:option>
                <form:option value="відмова">відмова</form:option>
                <form:option value="відкритий рахунок/продана послуга">відкритий рахунок/продана послуга</form:option>
            </form:select>
                <%--<form:input id="sel1"  disabled="true" path="status" style="width:105%;margin-bottom: 5px"  placeholder="example"/>--%>
                <label for="last_client_result">Результат останнього контакту з клієнтом:</label>
                <%--<form:input id="data" readonly="true" disabled="false" path="last_client_result" style="width:105%;margin-bottom: 5px;height:40%" placeholder="example"/>--%>
                <form:input id="data" readonly="true" disabled="false" path="last_client_result" style="width:105%;margin-bottom: 5px;" placeholder="example"/>
                <label for="comments">Коментарі:</label>
                <form:textarea id="comments" readonly="true" disabled="false" path="comments" style="width:105%;margin-bottom: 5px;height:78%;word-wrap: break-word;word-break: break-all;" placeholder="example"/></p>
        </div>
</fieldset>
    <input type="submit" value="Зберегти дані по клієнту" style="width: 98%;  margin-left: 10px;margin-top: 5px;background-color: crimson;color: white">
</form:form>


<div id="popup1" class="overlay">
    <div class="popup">
        <h2>Here i am</h2>
        <a class="close" href="#" onclick="MyFunction();return true;">&times;</a>
        <div class="content">
            <label>Введіть дату:</label><br/>
            <input type="date" id="dates"><br/><br/>
            <label>Коментарі:</label><br/>
            <textarea rows="10" cols="55" id="comment"></textarea>
            <%--<input type="text" id="data">--%>
            <%--Thank to pop me out of that button, but now i'm done so you can close this window.--%>
        </div>
    </div>
</div>
<div id="popup2" class="overlay">
    <div class="popup">
        <h2>Here i am</h2>
        <a class="close" href="#" onclick="MyFunction();return true;">&times;</a>
        <div class="content">
            <label>Коментарі:</label><br/>
            <textarea rows="13" cols="55" id="comment1"></textarea>
            <%--Thank to pop me out of that button, but now i'm done so you can close this window.--%>
        </div>
    </div>
</div>

</body>

<script language="javascript">


    function JSGetSelectedItem() {
        let dropdownIndex = document.getElementById('sel').selectedIndex;
        let dropdownValue = document.getElementById('sel')[dropdownIndex].text;
        /*alert("Hello JSCript " + dropdownValue);*/
        if (dropdownValue === 'Так'){
            document.getElementById("divId").innerHTML= dropdownValue + "<h3>Питання 2: Якщо відповідь «Так», то надайти свої дані:</h3><p><label for='firstname'>ПІБ (повна назва): </label><input name='pib'  type='text' required/></p><p><label for='date'>Дата народження: </label><input name='birth' type='date' value='2000-01-01' required/></p>"
        }else{
            document.getElementById("divId").innerHTML=""
        }
    }


    function JSGetSelectedItem1() {

        let dropdownIndex = document.getElementById('sel1').selectedIndex;
        let dropdownValue = document.getElementById('sel1')[dropdownIndex].text;
if (dropdownValue === 'Оберіть відповідь'){

}

        alert(dropdownValue);

    }

    function validateSelects()
    {
        let m = false;


        if ( $('#sel').val() !== '' && $('#sel1').val() !== '' && $('#sel2').val().length !== 0 && $('#sel3').val() !== '' && $('#sel4').val() !== ''){  m=true; }
        else{
        alert("Ви не заповнили всі поля!");
        return false
    }
        alert("Дякуємо! Кредитну заявку оновлено!");
        return(m);
    }
    // For the functionality.
    $('#myform').on('submit',function()
    {
        return validateSelects();
    });


    $(".js-select2").select2({
        closeOnSelect : false,
        placeholder : "Оберіть відповідь",
        //allowHtml: true,
        //allowClear: true,
        multiple: true
        // tags: true // создает новые опции на лету
    })

    $(".js-select").select2({
        placeholder: "Оберіть відповідь",
        //allowClear: true
         tags: false // создает новые опции на лету
    })


    $("#status").change(function () {

        var selectedvalue = this.value;

        if (selectedvalue === 'запланована зустріч' || selectedvalue === "запланований повторний дзвінок"){
            window.location.href = "#popup1";
        }else{
            window.location.href = "#popup2";
        }

/*
        $("#comments").val(selectedvalue);*/
    });
    function MyFunction(){

        if ($("#status").val() === 'запланована зустріч' || $("#status").val() === "запланований повторний дзвінок"){
            $("#data").val(new Date($("#dates").val()).toLocaleDateString("be-BY"));
            //$("#data").val($("#dates").val());
            $("#comments").val($("#comment").val());
        }else{
            $("#data").val("");
            $("#comments").val($("#comment1").val());
        }
        //new Date().toLocaleTimeString('en-US', { hour12: false, hour: "numeric", minute: "numeric", secend: "numeric"});
        $("#clienthistory").val($("#clienthistory").val() + String.fromCharCode(8730) + " " + $("#status").val() + "\n" +
            String.fromCharCode(174) + " " + new Date().toLocaleDateString("be-BY") + " " +
            new Date().toLocaleTimeString('en-US', { hour12: false, hour: "numeric", minute: "numeric", secend: "numeric"}) + "\n" + "\n");

    }
</script>

</html>