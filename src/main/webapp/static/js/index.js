let PAGE_SHOW = {
    HOME: 0,
    REGISTER: 1,
    LOGIN: 2,
    MODIFY_INFO: 3,
    MODIFY_PASS: 4,
    ABOUT_US: 5,
    MY_MESSAGES: 6,
};

let C = 10;//滚动条距离底部的距离

let checkUsernameResult = false;
let checkNicknameResult = false;
let checkPasswordResult = false;
let checkBorndateResult = true;
let checkEmailResult = true;
let checkPhoneResult = true;

let urlImgWrong = "static/image/wrong.png";
let urlImgCorrect = "static/image/duigou.png";

let flagModifyNickname = false;
let flagModifyBorndate = false;
let flagModifyEmail = false;
let flagModifyPhone = false;
let flagVerifyPass = false;
let flagShowPage = PAGE_SHOW.HOME;

let messagerInfoCache;

$(function () {
    let showRegister = $("#showRegister");
    let showHome = $("#showHome");
    let showHome2 = $("#showhome2");
    let showMyMessages = $("#showMyMessages");
    let showLogin = $("#showLogin");
    let showModifyInfo = $("#showMessagerInfo");
    let showModifyPass = $("#showModifyPass");
    let showAboutUs = $("#showaboutus");
    let btnToTop = $("#backToTop");

    let showMessagerOperate = $("#showNickname");
    let ulMessagerOperate = $("#messagerOperate");

    let requestRegister = $("#btnRegister");
    let requestLogin = $("#btnLogin");
    let requestLogout = $("#btnLogout");
    let requestForgetPwd = $("#btnForgetPwd");
    let requestModifyInfo = $("#btnModifyInfo");
    let requestModifyPass = $("#btnModifyPass");
    let requestNewMessage = $("#btnPublishMessage");

    let inputUsername = $(":input[name='username']");
    let inputNickname = $(":input[name='nickname']");
    let inputPassword = $(":input[name='password']");
    let inputRepwd = $(":input[name='repwd']");
    let inputBorndate = $(":input[name='borndate']");
    let inputEmail = $(":input[name='email']");
    let inputPhone = $(":input[name='phone']");
    let inputOriginalPass = $(":input[name='originalPass']");

    barInit();
    contentInit(0);
    loadflushEvent();

    showRegister.click("register", showDivReference);
    showHome.click("home", showDivReference);
    showHome2.click("home2", showDivReference);
    showMyMessages.click("myMessages", showDivReference);
    showLogin.click("login", showDivReference);
    showModifyInfo.click("messagerInfo", showDivReference);
    showModifyPass.click("modifyPassword", showDivReference);
    showAboutUs.click("aboutus", showDivReference);

    btnToTop.click(backToTop);

    showMessagerOperate.hover(function () {
        ulMessagerOperate.show();
    },function () {
        ulMessagerOperate.hide();
    });

    ulMessagerOperate.hover(function () {
        ulMessagerOperate.show();
    }, function () {
        ulMessagerOperate.hide();
    });

    $(window).scroll(function () {
        let scrollNum = 300;
        if($(window).scrollTop() >= scrollNum) {
            $(".shortcutBtn").show();
        } else {
            $(".shortcutBtn").hide();
        }
    });

    requestRegister.click(userRegister);
    requestLogin.click(userLogin);
    requestLogout.click(userLogout);
    requestForgetPwd.click(userForgetPwd);
    requestModifyInfo.click(requestModifyMessagerInfo);
    requestModifyPass.click(requestModifyPassword);
    requestNewMessage.click(requestPublishMessage);

    inputUsername.focus().blur(checkUsername);
    inputNickname.focus().blur(checkNickname);
    inputPassword.focus().blur(checkPassword);
    inputRepwd.focus().blur(checkRepwd);
    inputBorndate.focus().blur(checkBorndate);
    inputEmail.focus().blur(checkEmail);
    inputPhone.focus().blur(checkPhone);
    inputOriginalPass.focus().blur(verifyPassword);

    // let divBar = $(".div_bar");
    // let divBottom = $(".div_bottom");

    // divBar.load("bar.html");
    // divBottom.load("bottom.html");

     // $.get("http://localhost:8080/ssmdemo/bar.html").success(function (data) {
     //     div_bar.html(data);
     // });
     // $.get("http://localhost:8080/ssmdemo/bottom.html").success(function (data) {
     //     div_bottom.html(data);
     // });
 });

function initFlag() {
    checkUsernameResult = false;
    checkNicknameResult = false;
    checkPasswordResult = false;
    checkBorndateResult = true;
    checkEmailResult = true;
    checkPhoneResult = true;

    flagModifyNickname = false;
    flagModifyBorndate = false;
    flagModifyEmail = false;
    flagModifyPhone = false;
    flagVerifyPass = false;
}

function barInit() {
    let username = getCookie("username");
    let barLinks = $(".bar_link>ul>li:not(:first)");
    let divNewMess = $(".newMessaage");
    // barLinks.show();
    console.log(barInit.name);

    if(null != username && "" !== username) {
        let nickname = getCookie("nickname");
        console.log(username + "," + nickname);
        $("#showNickname>span").text(nickname);
        barLinks.hide();
        barLinks.not(".noLogin").show();
        divNewMess.show();
    } else {
        barLinks.hide();
        barLinks.filter(".noLogin").show();
        divNewMess.hide();
    }
}

function backToTop() {
    console.log(backToTop.name);

    window.scrollBy(0, -200);//每次滑动距离
    let scrollDelay = setTimeout('backToTop()', 30);//调用时间
    let stop = document.documentElement.scrollTop + document.body.scrollTop;
    if(stop === 0) {
        clearTimeout(scrollDelay);
    }
    scroll(0,0);
}

function showDivReference(event) {
    let divRegister = $(".main_register");
    let divHome = $(".content");
    let divLogin = $(".login");
    let divMessagerInfo = $(".messagerInfo");
    let divModifyPass = $(".modifyPass");
    let divNewMess = $(".newMessaage");
    let divAboutUs = $(".aboutus");
    let divMessageEnd = $(".messageEnd");
    let divLoading = $(".loading");

    console.log(showDivReference.name);
    console.log(event.data);

    divNewMess.hide();
    initFlag();
    switch (event.data) {
        case "home2":
            backToTop();
        case "home":
            divHome.show();
            divRegister.hide();
            divLogin.hide();
            divMessagerInfo.hide();
            divModifyPass.hide();
            divAboutUs.hide();
            divMessageEnd.hide();
            divLoading.show();

            let username = getCookie("username");
            if("" !== username && null !== username) {
                divNewMess.show();
            }

            flagShowPage = PAGE_SHOW.HOME;
            contentInit(0);
        break;
        case "myMessages":
            divHome.show();
            divRegister.hide();
            divLogin.hide();
            divMessagerInfo.hide();
            divModifyPass.hide();
            divAboutUs.hide();
            divMessageEnd.hide();
            divLoading.show();

            flagShowPage = PAGE_SHOW.MY_MESSAGES;
            contentInit(0);
        break;
        case "register":
            divHome.hide();
            divRegister.show();
            divLogin.hide();
            divMessagerInfo.hide();
            divModifyPass.hide();
            divAboutUs.hide();

            flagShowPage = PAGE_SHOW.REGISTER;
        break;
        case "login":
            divHome.hide();
            divRegister.hide();
            divLogin.show();
            divMessagerInfo.hide();
            divModifyPass.hide();
            divAboutUs.hide();

            flagShowPage = PAGE_SHOW.LOGIN;
        break;
        case "messagerInfo":
            divHome.hide();
            divRegister.hide();
            divLogin.hide();
            divMessagerInfo.show();
            divModifyPass.hide();
            divAboutUs.hide();

            flagShowPage = PAGE_SHOW.MODIFY_INFO;
            showMessagerInfo();
            break;
        case "modifyPassword":
            divHome.hide();
            divRegister.hide();
            divLogin.hide();
            divMessagerInfo.hide();
            divModifyPass.show();
            divAboutUs.hide();

            flagShowPage = PAGE_SHOW.MODIFY_PASS;
            break;
        case "aboutus":
            divHome.hide();
            divRegister.hide();
            divLogin.hide();
            divMessagerInfo.hide();
            divModifyPass.hide();
            divAboutUs.show();

            flagShowPage = PAGE_SHOW.ABOUT_US;
            break;

        default:
            break;
    }

    // if(event.data === "home") {
    // } else if(event.data === "register") {
    //
    // }
}
/*****************************留言显示相关***********************/
function loadflushEvent() {
    $(document).endlessScroll({
        fireOnce: true,
        fireDelay: 2000,
        loader: "<div class='loading'><div>",
        callback: function (p) {
            if(flagShowPage !== PAGE_SHOW.HOME && flagShowPage !== PAGE_SHOW.MY_MESSAGES) {
                return ;
            }

            //do something
            let last_message = $("#messagesUl li:last");
            let res = contentInit(last_message.data("messageID"));

            let divMsg = $(".messageEnd");
            if(res === null) {
                divMsg.show();
            } else {
                divMsg.hide();
            }
        }
    });

    /*$(window).scroll(function() {
        if(flagShowPage !== PAGE_SHOW.HOME && flagShowPage !== PAGE_SHOW.MY_MESSAGES) {
            return ;
        }

        let scrollTop = $(this).scrollTop(),scrollHeight = $(document).height(),windowHeight = $(this).height();
        let positionValue = (scrollTop + windowHeight) - scrollHeight;
        if (positionValue >= -C) {
            //do something
            let last_message = $("#messagesUl li:last");
            let res = contentInit(last_message.data("messageID"));

            let divMsg = $(".messageEnd");
            if(res === null) {
                divMsg.show();
            } else {
                divMsg.hide();
            }
        }
    });*/
}

function contentInit(lastMessageID) {
    let url = "message/queryMessage.do";
    let username = flagShowPage === PAGE_SHOW.HOME ? "HM" : getCookie("username");
    let data = {username: username, lastMessageID: lastMessageID};

    console.log(contentInit.name);
    console.log(data);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            if(result.data === null) {
                return null;
            }
            showMessageList(result.data, lastMessageID);
            return result.data.length;
        } else {
            alert(result.message);
        }
    });
    return null;
}

function showMessageList(messages, lastMessageID) {
    let template = "<li>" +
        "<div class=\"messInfo\">" +
        "<label>[messagerUsername]</label><span>[date]</span>" + // style="font-size:12px;" style="margin:0px 5px;margin-right:400px;"
        "</div>" +
        "<div class=\"message\">" +
        "<div>" +
        "[messageContent]" +
        "</div>" +
        "</div>" +
        "</li>";

    console.log(messages);

    let divLoading = $(".loading");

    let ul = lastMessageID === 0 ? $("#messagesUl").empty() : $("#messagesUl");

    for(let i = 0; i < messages.length; i ++) {
        let message = messages[i];

        let li = template.replace("[messagerUsername]", message.username);
        li = li.replace("[date]", unixToDate(message.createTime, true, 8));
        li = li.replace("[messageContent]", message.messageContent);
        $(li).addClass("messInfo span");
        li = $(li).data('messageID', message.messageID);
        ul.append(li);
    }

    divLoading.slideUp(1000);
}

/***************************用户操作相关*************************/
function userRegister() {
    let url = "user/register.do";
    let data = $("#registInfo").serialize();
    let showLogin = $("#showLogin");

    let checkResult = checkUsernameResult & checkNicknameResult & checkPasswordResult &
            checkBorndateResult & checkEmailResult & checkPhoneResult;

    console.log(checkUsernameResult + " " + checkNicknameResult + " " + checkPasswordResult + " " +
                checkBorndateResult + " " + checkEmailResult + " " + checkPhoneResult);

    if(! checkResult) {
        alert("信息输入有误，请重新输入");
        return false;
    }

    console.log(userRegister.name);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            alert("注册成功");
            showLogin.click();
        } else {
            alert(result.message);
        }
    })
}

function userLogin() {
    let url = "user/login.do";
    let loginUsername = $(":input[name='loginUsername']").val().trim();
    let loginPassword = $(":input[name='loginPassword']").val().trim();
    let data = {username:loginUsername, password:loginPassword};
    console.log(data);

    if(loginUsername == null || loginUsername === "") {
        alert("请输入用户名");
        return false;
    }

    if(loginPassword == null || loginPassword === "") {
        alert("请输入密码");
        return false;
    }

    console.log(userLogin.name);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            console.log(result.data);
            setCookie("username", result.data.username, 30);
            setCookie("nickname", result.data.nickname, 30);
            // alert("登录成功");
            window.location.reload();
        } else {
            alert(result.message);
        }
    })
}

function userLogout() {
    console.log(userLogout.name);
    delCookie("username");
    delCookie("nickname");
    window.location.reload();
}

function userForgetPwd() {
    let url = "user/forgetPwd.do";

    console.log(userRegister.name);
    $.getJSON(url, null, function (result) {
        if(result.state === 0) {
            alert("注册成功");
        } else {
            alert(result.message);
        }
    })
}

function checkUsername() {
    let inputUsernameVal = $(":input[name='username']").val().trim();
    let checkImg = $("#username_img");
    let checkmsg = $("#username_msg");
    let inputBox = $(":input[name='username']")[0];

    console.log("[" + checkUsername.name + "]" + inputUsernameVal);
    checkUsernameResult = false;
    console.log(checkUsernameResult);

    if(inputUsernameVal == null || inputUsernameVal === "") {
        checkmsg.html("请输入用户名");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }
    let regx = /^\w+$/;
    if(! regx.test(inputUsernameVal)) {
        checkmsg.html("有非法字符");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    regx = /^[A-Za-z]{1}/;
    if(! regx.test(inputUsernameVal)) {
        checkmsg.html("不允许数字开头");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    regx = /^[A-Za-z]{1}\w{2,15}$/;
    if(! regx.test(inputUsernameVal)) {
        checkmsg.html("长度不符");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    let url = "user/queryUsername.do";
    let username = $("#username").val();
    let data = {username:username};
    console.log(data);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
        } else {
            checkmsg.html(result.message);
            inputBox.style.border="1px solid red";
            checkImg.attr('src', urlImgWrong);
            checkUsernameResult = false;
            console.log(checkUsernameResult);
            return false;
        }
    })

    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    checkUsernameResult = true;
    return true;
}

function checkNickname() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 1: get modify messager info value
    let inputNicknameVal = $(":input[name='nickname']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#nickname_img") : $("#modifyNickname_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#nickname_msg") : $("#modifyNickname_msg");
    let inputBox = $(":input[name='nickname']")[numCheck];


    if(flagShowPage !== PAGE_SHOW.REGISTER && inputNicknameVal === messagerInfoCache.nickname) {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        flagModifyNickname = false;
        checkNicknameResult = true;
        return false;
    }
    console.log("[" + checkNickname.name + "]" + inputNicknameVal);
    checkNicknameResult = false;
    flagModifyNickname = true;

    if(inputNicknameVal == null || inputNicknameVal.trim() === "") {
        checkmsg.html("请输入昵称");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    let regx = /^.{1,16}$/;
    if(! regx.test(inputNicknameVal)) {
        checkmsg.html("长度不符");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    let url = "user/queryNickname.do";
    let nickname = $("#nickname").val();
    let data = {nickname:nickname};
    console.log(data);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
        } else {
            checkmsg.html(result.message);
            inputBox.style.border="1px solid red";
            checkImg.attr('src', urlImgWrong);
            checkNicknameResult = false;
            console.log(checkNicknameResult);
            return false;
        }
    });

    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    checkNicknameResult = true;
    return true;
}

function checkPassword() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 2: get new password value
    let inputPasswordVal = $(":input[name='password']").eq(numCheck).val();
    let inputOrinalPasswordVal = $(":input[name='originalPass']").val();
    let inputRepwdVal = $(":input[name='repwd']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#password_img") : $("#newPassword_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#password_msg") : $("#newPassword_msg");
    let inputBox = $(":input[name='password']")[numCheck];

    console.log("[" + checkPassword.name + "]" + inputPasswordVal);
    checkPasswordResult = false;

    if(inputOrinalPasswordVal !== null && inputOrinalPasswordVal !== "") {
        if(flagVerifyPass === true && inputOrinalPasswordVal === inputPasswordVal) {
            checkmsg.html("与原始密码一致");
            inputBox.style.border="1px solid red";
            checkImg.attr('src', urlImgWrong);
            return false;
        }
    }

    if(inputPasswordVal == null || inputPasswordVal === "") {
        checkmsg.html("请输入密码");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    let regx = /^\w+$/;;
    if(! regx.test(inputPasswordVal)) {
        checkmsg.html("有非法字符");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    regx = /^.{1,16}$/;
    if(! regx.test(inputPasswordVal)) {
        checkmsg.html("长度不符");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }
    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    checkPasswordResult = true;

    if(inputRepwdVal != null && inputRepwdVal !== "") {
        checkRepwd();
    }
    return true;
}

function checkRepwd() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 2: get new password value
    let inputPasswordVal = $(":input[name='password']").eq(numCheck).val();
    let inputRepwdVal = $(":input[name='repwd']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#repwd_img") : $("#newRepwd_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#repwd_msg") : $("#newRepwd_msg");
    let inputBox = $(":input[name='repwd']")[numCheck];

    console.log("[" + checkRepwd.name + "]" + inputRepwdVal);
    checkPasswordResult = false;

    if(inputRepwdVal == null || inputRepwdVal === "") {
        checkmsg.html("请再次输入密码");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }

    if(inputPasswordVal !== inputRepwdVal) {
        checkmsg.html("两次输入的密码不同");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        return false;
    }
    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    checkPasswordResult = true;
    return true;
}

function checkBorndate() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 1: get modify messager info value
    let inputBorndateVal = $(":input[name='borndate']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#borndate_img") : $("#modifyBorndate_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#borndate_msg") : $("#modifyBorndate_msg");
    let inputBox = $(":input[name='borndate']")[numCheck];

    if(flagShowPage !== PAGE_SHOW.REGISTER && inputBorndateVal === messagerInfoCache.borndate) {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        flagModifyBorndate = false;
        checkBorndateResult = true;
        return false;
    }
    console.log("[" + checkBorndate.name + "]" + inputBorndateVal);
    checkBorndateResult = true;
    flagModifyBorndate = true;

    if(inputBorndateVal == null || inputBorndateVal === "") {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        return false;
    }

    let regx = /^\d{4}\-{1}\d{2}\-{1}\d{2}$/;
    if(! regx.test(inputBorndateVal)) {
        checkmsg.html("输入有误");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        checkBorndateResult = false;
        return false;
    }
    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    return true;
}

function checkEmail() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 1: get modify messager info value
    let inputEmailVal = $(":input[name='email']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#email_img") : $("#modifyEmail_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#email_msg") : $("#modifyEmail_msg");
    let inputBox = $(":input[name='email']")[numCheck];

    if(flagShowPage !== PAGE_SHOW.REGISTER && inputEmailVal === messagerInfoCache.email) {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        flagModifyEmail = false;
        checkEmailResult = true;
        return false;
    }
    console.log("[" + checkEmail.name + "]" + inputEmailVal);
    checkEmailResult = true;
    flagModifyEmail = true;

    if(inputEmailVal == null || inputEmailVal === "") {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        return false;
    }

    let regx = /^\w+@{1}\w+(\.\w+)+$/;
    if(! regx.test(inputEmailVal)) {
        checkmsg.html("输入有误");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        checkEmailResult = false;
        return false;
    }
    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    return true;
}

function checkPhone() {
    let numCheck = flagShowPage === PAGE_SHOW.REGISTER ? 0 : 1;//0: get register form value; 1: get modify messager info value
    let inputPhoneVal = $(":input[name='phone']").eq(numCheck).val();
    let checkImg = flagShowPage === PAGE_SHOW.REGISTER ? $("#phone_img") : $("#modifyPhone_img");
    let checkmsg = flagShowPage === PAGE_SHOW.REGISTER ? $("#phone_msg") : $("#modifyPhone_msg");
    let inputBox = $(":input[name='phone']")[numCheck];

    if(flagShowPage !== PAGE_SHOW.REGISTER && inputPhoneVal === messagerInfoCache.phone) {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        flagModifyPhone = false;
        checkPasswordResult = true;
        return false;
    }
    console.log("[" + checkPhone.name + "]" + inputPhoneVal);
    checkPasswordResult = true;
    flagModifyPhone = true;

    if(inputPhoneVal == null || inputPhoneVal === "") {
        checkmsg.empty();
        inputBox.style.border="none";
        checkImg.attr('src', '');
        return false;
    }

    let regx = /^1[3456789]\d{9}$/;
    if(! regx.test(inputPhoneVal)) {
        checkmsg.html("输入有误");
        inputBox.style.border="1px solid red";
        checkImg.attr('src', urlImgWrong);
        checkEmailResult = false;
        return false;
    }
    checkmsg.empty();
    inputBox.style.border="none";
    checkImg.attr('src', urlImgCorrect);
    return true;
}

function showMessagerInfo() {
    let url = "user/getMessagerInfoByUsername.do";
    let username = getCookie("username");
    let data ={username:username};

    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            fillMessagerInfo(result.data);
        } else {
            alert(result.message);
        }
    })
}

function fillMessagerInfo(messagerInfo) {
    let infoList = $("#modifyInfo>ul>li");
    checkNicknameResult = checkBorndateResult = checkEmailResult = checkPhoneResult = true;
    flagModifyNickname = flagModifyBorndate = flagModifyEmail = flagModifyPhone = false;
    messagerInfoCache = messagerInfo;
    console.log(fillMessagerInfo.name + " " + messagerInfo.username);

    infoList.eq(0).find("label").html(messagerInfo.username);
    infoList.eq(2).find("input").val(messagerInfo.nickname);
    $("#modifyNickname_msg").empty();
    $("#modifyNickname_img").attr('src', '');
    infoList.eq(4).find("input").val(messagerInfo.borndate);
    $("#modifyBorndate_msg").empty();
    $("#modifyBorndate_img").attr('src', '');
    infoList.eq(6).find("input").val(messagerInfo.email);
    $("#modifyEmail_msg").empty();
    $("#modifyEmail_img").attr('src', '');
    infoList.eq(8).find("input").val(messagerInfo.phone);
    $("#modifyPhone_msg").empty();
    $("#modifyPhone_img").attr('src', '');
}

function requestModifyMessagerInfo() {
    let username = getCookie("username");
    let url = "user/requestModifyMessagerInfo.do";
    let data = {username: username};
    let showModifyInfo = $("#showMessagerInfo");
    let show

    let checkResult = checkNicknameResult & checkBorndateResult & checkEmailResult & checkPhoneResult;
    let flagModifyResult = flagModifyNickname | flagModifyBorndate | flagModifyEmail | flagModifyPhone;

    console.log(requestModifyMessagerInfo.name + " " + checkResult);
    // console.log(requestModifyMessagerInfo.name + " " + checkNicknameResult + " " + checkBorndateResult + " " +
    //             checkEmailResult + " " + checkPhoneResult);

    if(! flagModifyResult) {
        alert("信息没有变化，无需修改");
        return false;
    }
    if(! checkResult) {
        alert("信息输入有误，请重新输入");
        return false;
    }

    if(flagModifyNickname === true) {
        data.nickname = $(":input[name='nickname']").eq(1).val();
    }
    if(flagModifyBorndate === true) {
        data.borndate = $(":input[name='borndate']").eq(1).val();
    }
    if(flagModifyEmail === true) {
        data.email = $(":input[name='email']").eq(1).val();
    }
    if(flagModifyPhone === true) {
        data.phone = $(":input[name='phone']").eq(1).val();
    }

    console.log(data);

    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            alert("修改成功");
            if(flagModifyNickname === true) {
                delCookie("nickname");
                setCookie("nickname", result.data.nickname);
                $("#showNickname>span").text(result.data.nickname);
            }
        } else {
            alert(result.message);
        }
        showModifyInfo.click();
    })
}

function verifyPassword() {
    let checkImg = $("#modifyPassword_img");
    let checkmsg = $("#modifyPassword_msg");
    let inputBox = $(":input[name='originalPass']")[0];

    let inputPassVal = $(":input[name='originalPass']").val();
    let inputPasswordVal = $(":input[name='password']").eq(1).val();
    let url = "user/login.do";
    let username = getCookie("username");
    let data = {username:username, password: inputPassVal};

    console.log(verifyPassword.name + " " + inputPassVal);
    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            checkmsg.empty();
            inputBox.style.border="none";
            checkImg.attr('src', 'image/duigou.png');
            flagVerifyPass = true;
            if(inputPasswordVal !== null && inputPasswordVal !== "") {
                checkPassword();
            }
        } else {
            checkmsg.html("密码错误");
            inputBox.style.border="1px solid red";
            checkImg.attr('src', 'image/wrong.png');
            flagVerifyPass = false;
        }
    })
}

function requestModifyPassword() {
    let checkResult = flagVerifyPass & checkPasswordResult;
    console.log(requestModifyMessagerInfo.name + " " + flagVerifyPass + " " + checkPasswordResult);

    let inputPasswordVal = $(":input[name='password']").eq(1).val();
    let inputOrinalPasswordVal = $(":input[name='originalPass']").val();
    if(! checkResult || inputPasswordVal === inputOrinalPasswordVal) {
        alert("输入有误");
        return false;
    }

    let showHome = $("#showHome");
    let requestLogout = $("#btnLogout");

    let url = "user/requestModifyPassword.do";
    let username = getCookie("username");
    let data = {username: username, originalPassword: inputOrinalPasswordVal, newPassword: inputPasswordVal};

    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
            alert("修改成功");
            requestLogout.click();
        } else {
            alert(result.message);
            showHome.click();
        }
    })
}

function requestPublishMessage() {
    console.log(requestPublishMessage.name);

    let url = "message/publishMessage.do";
    let username = getCookie("username");
    let messageInfo = $(".newMessaage textarea");
    let data = {username: username, messageInfo: messageInfo.val()};
    console.log(messageInfo);

    if(null === messageInfo.val() || "" === messageInfo.val()) {
        // messageInfo.style.backgroundColor = "";
        alert("留言为空");
        return ;
    }

    $.getJSON(url, data, function (result) {
        if(result.state === 0) {
             // alert("留言成功");
             window.location.reload();
        } else {
            alert(result.message);
        }
    });
}