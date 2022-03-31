// 네비게이션 호버이벤트
$("#main_nav .nav ul div").hover(function(){
    $(this).addClass("active");
    $("#main_nav .nav ul .active a").addClass("active");
}, function(){
    $(this).removeClass("active");
    $("#main_nav .nav ul div a").removeClass("active");
})

