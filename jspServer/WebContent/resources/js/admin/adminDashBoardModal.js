$(function(){
    $("#userLogModal").hide();


	$(".dashboard_user_list").click(function(){
	    $.ajax({
	        url : "AlluserList",
	        type : "get",
	        success : function(result){
	
	            $.each(result, function(index, obj){
	                var userNo = $("<th>").text(obj.userNo);
	                var userId = $("<th>").text(obj.userId);
	                var banCount = $("<th>").text(obj.banCount);
	
	                var tr = $("<tr>").append(userNo,userId,banCount).addClass("userInfo");
	                $(".user_log_table").append(tr);
	            })
	
	            $("#userLogModal").fadeIn(200);
	        }
	    })
	
	})
	
	$(".dashboard_ban_user_list").click(function(){
		$.ajax({
			url : "banuserList",
			type : "get",
			success : function(result){
			
				$.each(result, function(index, obj){
					var userNo = $("<th>").text(obj.userNo);
	                var userId = $("<th>").text(obj.userId);
	                var banCount = $("<th>").text(obj.banCount);
	
	                var tr = $("<tr>").append(userNo,userId,banCount).addClass("userInfo");
	                $(".user_log_table").append(tr);
				})
				
				$("#userLogModal").fadeIn(200);
			}
		})
	})

})

$(".cancel_btn").click(function(){
    $("#userLogModal").fadeOut(200);
})

