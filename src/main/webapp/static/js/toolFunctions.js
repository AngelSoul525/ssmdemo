function setCookie(c_name, value){
    document.cookie=c_name+ "=" + value;
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