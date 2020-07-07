
let crypt = new JSEncrypt();

function setCookie(c_name, value) {
    document.cookie = c_name + "=" + value;
}

function getCookie(name) {
    let arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name) {
    let exp = new Date();
    exp.setTime(exp.getTime() - 1);
    let cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "=" + "";
}

/*js实现sleep功能 单位：毫秒*/
function sleep(numberMillis) {
    let now = new Date();
    let exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

function unixToDate(unixTimeTem, isFull, timeZoneTem) {
    let unixTime = unixTimeTem + "";
    let timeZone = timeZoneTem + "";

    if (typeof (timeZone) == 'number')
    {
        unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
    }
    let time = new Date(unixTime * 1);
    let ymdhis = "";
    ymdhis += time.getUTCFullYear() + "-";
    ymdhis += ((time.getUTCMonth()+1) < 10 ? "0" + (time.getUTCMonth()+1) : (time.getUTCMonth()+1)) + "-";
    ymdhis += (time.getUTCDate() < 10 ? "0" + time.getUTCDate() : time.getUTCDate()) + " ";
    if (isFull === true)
    {
        ymdhis += (time.getHours() < 10 ? "0" + time.getHours() : time.getHours()) + ":";
        ymdhis += (time.getUTCMinutes() < 10 ? "0" + time.getUTCMinutes() : time.getUTCMinutes()) + ":";
        ymdhis += (time.getUTCSeconds() < 10 ? "0" + time.getUTCSeconds() : time.getUTCSeconds());
    }
    return ymdhis;
}


function customAlert(alertText, okFunction) {
    $.alert({
        title: 'Alert',
        content: alertText,
        useBootstrap: false,
        boxWidth: '300px',
        draggable: false,
        buttons: {
            ok:{
                text: "确认",
                action: function () {
                    if(okFunction !== undefined) {
                        okFunction();
                    } else {
                        return ;
                    }
                }
            }
        }
    });
}

function operateConfirm(text, okFunction) {
    $.confirm({
        title: '确认',
        content: text,
        type: 'red',
        useBootstrap: false,
        boxWidth: '300px',
        draggable: false,
        // columnClass: 'small',
        icon: 'glyphicon glyphicon-heart',
        buttons: {
            ok: {
                text: '确认',
                // btnClass: 'btn-primary',
                action: function () {
                    okFunction();
                }
            },
            cancel: {
                text: '取消',
                // btnClass: 'btn-primary'
            }
        }
    });
}

function encryInit() {
    let pubKey = '-----BEGIN PUBLIC KEY-----' +
        'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDg6pZm2SmddLm1BKyeXhTDkaVg' +
        'nW4fxP6YHQckIu2ghVY6D1NURco7fhYorNG4PCpU0UUK/uRwEoyvIIyNxNf4TYQl' +
        'thx6jN8jayF3kZn3skk2fAK7XSseGlMhC2pPdc5tgsnvvJYkOdIl4MIt9GOv7TjA' +
        'wDrDWG0ohmcBeSGSlQIDAQAB' +
        '-----END PUBLIC KEY-----';

    crypt.setPublicKey(pubKey);

    // let prikey = "private key";
    //
    // crypt.setPrivateKey(prikey);
}

function encryptPass(pass) {
    return crypt.encrypt(pass);
}

function decryptPass(encryPass) {
    return crypt.decrypt(encryPass);
}
