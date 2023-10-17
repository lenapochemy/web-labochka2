import {Dotjs} from "./dots.js";
let x, y, r;
const canvas = document.querySelector("canvas");
const ctx = canvas.getContext("2d");
ctx.fillStyle = "deeppink";
let dots = [];

window.onload = function () {
    drawAxes(ctx);
    drawDots();

    let buttons = document.querySelectorAll("input[name=r]");
    buttons.forEach(click);
    function click(button){
        button.onclick = function (){
            r = this.value.replace(",", ".");
            drawArea(r);
        }
    }

    document.getElementById("check").onclick = function () {
        if (valid_x() && valid_y() && valid_r()) {
            send(x, y, r, "checkParam");
        }
    }

    canvas.addEventListener('click', function (event){
        if(r == null) createNot("Невозможно определить координаты, выберите сначала значение радиуса")
        else {
            let loc = windowToCanvas(canvas, event.clientX, event.clientY);
            let x = xFromCanvas(loc.x);
            let y = yFromCanvas(loc.y);
            sendToServer(x, y, r);
        }
    });
}

function send(x, y, r, action){
    let dot = new Dotjs(x, y);
    addDot(dot);
    dots.push(dot);

    let url = new URL("http://localhost:8080/labochka2/controller");
    url.searchParams.set("action", action);
    url.searchParams.set('x', x);
    url.searchParams.set('y', y);
    url.searchParams.set('r', r);
    fetchHtml(url);

}

function fetchHtml(url){
    fetch(url, {method: "get"})
        .then((response) => {
            return response.text();
        })
        .then((html) => {
            document.body.innerHTML = html;
        });
}

function drawAxes(){
    ctx.beginPath();
    ctx.moveTo(0, 250);
    ctx.lineTo(500, 250);
    ctx.moveTo(250, 0);
    ctx.lineTo(250, 500)
    ctx.stroke();
}

function xToCanvas(x){
    return (x * 50) + 250;
}
function yToCanvas(y){
    return 250 - (y * 50);
}
function xFromCanvas(x){
    return (x - 250)/50;
}
function yFromCanvas(y){
    return (250 - y)/50;
}
function rToCanvas(r){
    return (r/5) * 250;
}
function windowToCanvas(canvas, x, y){
    let bbox = canvas.getBoundingClientRect();
    return {x: x -bbox.left * (canvas.width / bbox.width),
            y: y - bbox.top * (canvas.height / bbox.height)
    };
}

function drawArea(r){
    ctx.clearRect(0,0, 500, 500);
    r = rToCanvas(r);

    ctx.beginPath();
    ctx.moveTo(250, 250);
    ctx.lineTo(250, 250 - r);
    ctx.lineTo(250-r,  250-r);
    ctx.lineTo(250-r, 250 )
    ctx.lineTo(250, 250);
    ctx.fill();

    ctx.beginPath();
    ctx.moveTo(250, 250);
    ctx.lineTo(250-r, 250);
    ctx.lineTo(250, 250 + (r/2));
    ctx.lineTo(250, 250);
    ctx.fill();

    ctx.beginPath();
    ctx.arc(250, 250, r/2, 0, Math.PI/2, false);
    ctx.moveTo(250, 250+(r/2));
    ctx.lineTo(250, 250);
    ctx.lineTo(250+(r/2), 250);
    ctx.fill();

    drawAxes();
    drawDots();
}

function drawDots(){
    dots.forEach(addDot);
}

function addDot(dot){
    x = xToCanvas(dot.getX());
    y = yToCanvas(dot.getY());
    ctx.fillStyle = "black";
    ctx.fillRect(x, y, 3, 3);
    ctx.fillStyle = "deeppink";

}

async function checkDot(x, y, r){
    const form = new FormData();
    form.append("action", "checkDot");
    form.append("x", x);
    form.append("y", y);
    form.append("r", r);

    const url = "controller?" + new URLSearchParams(form).toString();
    const response = await fetch(url, {method: "get"});

    if(!response.ok){
        createNot("Не получлось отпавить точку");
    }

    const data = await response.json();
    if(data.error) createNot(data.error);

    return data;
}

async function sendToServer(x, y, r){
    const data = await checkDot(x, y, r);

    if(!data.error) {
        addToTable(x, y, r, data.result, data.time);
        let dot = new Dotjs(x, y);
        addDot(dot);
        dots.push(dot);
    }
}


function addToTable(x, y, r, result, time){
    const table = document.getElementById("result");
    let newRow = table.insertRow(1);
    newRow.insertCell(0).innerText = x;
    newRow.insertCell(1).innerText = y;
    newRow.insertCell(2).innerText = r;
    newRow.insertCell(3).innerText = result ? "Точка попала"
        : "Точка не попала";
    newRow.insertCell(4).innerText = time;

}

function createNot(message){
    let outputContainer = document.getElementById("result_in_table");
    if(outputContainer.contains(document.querySelector(".notification"))){
        let stub = document.querySelector(".notification");
        stub.textContent = message;
        stub.classList.replace("outputStub", "errorStub");
    } else {
        let notificationTableRow = document.createElement("h4");
        notificationTableRow.innerHTML = "<span class='notification errorStub'></span>";
        outputContainer.prepend(notificationTableRow);
        let span = document.querySelector(".notification");
        span.textContent = message;
    }
}

function valid_x(){
    x = document.querySelector("input[type=radio]:checked").value.replace(",", ".");
     //console.log("x=" + x);
    if(isNumeric(x)) return true;
    else {
        createNot("Значение x не выбрано")
        return false;
    }
}

function valid_y(){
    y = document.querySelector("input[name=y]").value.replace(",",".");
    //console.log("y=" + y);
    if(y === undefined){
        createNot("Значение y не введено");
        return false;
    } else if (!isNumeric(y)){
        createNot("Значение y не число");
        return false;
    } else if (y.length >= 17) {
        createNot("Слишком много символов в значении y");
        return false;
    } else if(y <= -3 || y >= 5){
        createNot("Значение y не входит в область допустимых значений (-3; 5)");
        return false;
    } else return true;
}

function valid_r(){
         //console.log("r=" + r);
        if(r !== null) {
            return true;
        } else {
            createNot("Значение R не выбрано");
            return false;
        }
}

function isNumeric(n){
    return !isNaN(parseFloat(n)) && isFinite(n);
}
