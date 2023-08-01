/*!
* Start Bootstrap - Personal v1.0.1 (https://startbootstrap.com/template-overviews/personal)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-personal/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
$(window).load(function() {
    $('#load').hide();
});


// Audio 객체 설정
const myAudio = new Audio();
myAudio.src = "mp3/voice1.mp3";

// 오디오 재생: 크롬브라우저에서 작동 안함
myAudio.play();

// 버튼 취득
const btnPlay = document.getElementById("btnPlay");
const btnPause = document.getElementById("btnPause");
//const btnStop = document.getElementById("btnStop");
const btnSearch = document.getElementById("btnSearch");

// 재생 버튼
btnPlay.onclick = function () {
    myAudio.currentTime = 0; // 재생시간을 처음으로 설정
    myAudio.play();
}

// 일시정지 버튼
btnPause.onclick = function () {
    myAudio.pause();
}

// 정지 버튼
//btnStop.onclick = function () {
//    myAudio.pause();
//    myAudio.currentTime = 0; // 재생시간을 처음으로 설정
//}

btnSearch.onclick = function () {
    myAudio.pause();
    myAudio.currentTime = 0; // 재생시간을 처음으로 설정
}

if (window.addEventListener) {
    window.addEventListener('click', DoStuffFunction, false);
}

function DoStuffFunction() {
    btnPlay.click();
    console.log(11)
    window.removeEventListener('click', DoStuffFunction, false);
}
