// 네비게이션 호버이벤트
$("#main_nav .nav ul div").hover(function(){
    $(this).addClass("active");
    $("#main_nav .nav ul .active a").addClass("active");
}, function(){
    $(this).removeClass("active");
    $("#main_nav .nav ul div a").removeClass("active");
})




//테스트

$(document).ready(function() {
	$('#cancel').hide();
	$('#fullpage').fullpage({
		//options here
		autoScrolling:true,
		scrollHorizontally: true
	});
});

// x버튼 클릭시 search비워주기
var cleansearch	 = function(obj) {
    obj.parentNode.querySelector('input').value = ""
}


// inputsearch 클릭시 x버튼 보이게 하기
$("#searchform").click(function changeli(){
	event.stopPropagation();
	$("#cancel").show();
})