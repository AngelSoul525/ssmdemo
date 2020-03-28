$(function () {
    let spanRegister = $("#showRegister");

    barInit();

    spanRegister.click();
});

function barInit() {
    let username = getCookie("username");
    let barLinks = $(".bar_link>span");
    // barLinks.show();
    console.log(barInit.name);

    if(null != username) {
        barLinks.not(".noLogin").show();
    } else {
        barLinks.filter(".noLogin").show();
    }
}

function showRegister() {
    let divHome = $(".main_home");

    divHome.load()
}
