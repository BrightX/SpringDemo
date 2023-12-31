let ws = {};
let ws2 = {};
(function () {
    ws = new WebSocket('ws://' + location.host + '/myHandler');
    ws.addEventListener('message', ev => {
        console.log(ev.data);
    });
    console.log('开始');

    const uid = '122';
    ws2 = new WebSocket('ws://' + location.host + '/hello/' + uid);
    ws2.addEventListener('message', ev => {
        console.log(ev.data);
    });
    console.log('开始2123');
})();
