//var AudioContext;
//var audioContext;
//
//window.onload = function() {
//    navigator.mediaDevices.getUserMedia({ audio: true }).then(() => {
//        AudioContext = window.AudioContext || window.webkitAudioContext;
//        audioContext = new AudioContext();
//    }).catch(e => {
//        console.error(`Audio permissions denied: ${e}`);
//    });
//}

const myAudio = document.getElementById("myAudio")

var body = document.getElementsByTagName('BODY')[0];
// CONDITION DOES NOT WORK
if ((body && body.readyState == 'loaded') || (body &&  body.readyState == 'complete') ) {
    console.log(1)
    DoStuffFunction();
} else {
    // CODE BELOW WORKS
    if (window.addEventListener) {
        console.log(2)
        window.addEventListener('load', DoStuffFunction, false);
    } else {
        console.log(3)
        window.attachEvent('onload',DoStuffFunction);
    }
}

function DoStuffFunction() {
//    console.log(11)
//    wait(3);
//    console.log(22)
    fncSoundPlay();
}

function fncSoundPlay()
{
    console.log(33)
    myAudio.play(); // 음원 재생
//	var audio = new Audio("mp3/voice1.mp3");
//	audio.autoplay = true;
//	audio.load();
//	audio.loop = false; // 반복재생하지 않음
//	audio.volume = 1;
//	audio.muted = true;
//    audio.play();
//    audio.muted = false;
}

function wait(sec) {
    let start = Date.now(), now = start;
    while (now - start < sec * 1000) {
        now = Date.now();
    }
}