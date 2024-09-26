$(function(){
    $(".modal_view").hide();

	//회원목록
	$(".dashboard_user_list").click(function(){
	
			$(".userInfo").remove();
		
	    $.ajax({
	        url : "AlluserList",
	        type : "get",
	        success : function(result){
	
	            $.each(result, function(index, obj){
	                let userNo = $("<th>").text(obj.userNo);
	                let userId = $("<th>").text(obj.userId);
	                let banCount = $("<th>").text(obj.banCount);
	
	                let tr = $("<tr>").append(userNo,userId,banCount).addClass("userInfo");
	                $(".user_log_table").append(tr);
	            })
	
	            $("#userLogModal").fadeIn(200);
	        }
	    })
	
	})
	
    //정지회원목록
	$(".dashboard_ban_user_list").click(function(){
	
			$(".banUserInfo").remove();
		
		$.ajax({
			url : "banUserList",
			type : "get",
			success : function(result){
			
				$.each(result, function(index, obj){
					let userNo = $("<th>").text(obj.userNo);
	                let userId = $("<th>").text(obj.userId);
                    let userStatus = $("<th>").text(obj.status == "Y" ? "정상":"정지");
	
	                let tr = $("<tr>").append(userNo,userId,userStatus).addClass("banUserInfo");
	                $(".ban_user_log_table").append(tr);
				})
				
				$("#banUserLogModal").fadeIn(200);
			}
		})
	})

	//회원관리
    $(".dashboard_user_management").click(function(){
        $(".UserManagementInfo").remove();

        $.ajax({
            url : "AlluserList",
            type : "get",
            success : function(result){

                $.each(result,function(index, obj){
                    let userNo = $("<th>").text(obj.userNo);
	                let userId = $("<th>").text(obj.userId);
                    let userStatus = $("<th>").text(obj.status == "Y" ? "정상":obj.status == "B" ? "정지":"탈퇴");
	
	                let tr = $("<tr>").append(userNo,userId,userStatus).addClass("UserManagementInfo");
	                $(".management_user_list_table").append(tr);
                })

                $("#userManagementModal").fadeIn(200);

            }
        })
    })
    
    
    $(".search_btn").click(function(){
        var searchUserId = $(this).prev().val();
        if(searchUserId.length <= 0){
            alert("검색할 아이디를 입력해 주세요.");
        } else {
            $.ajax({
                url : "searchUserId",
                type : "post",
                data : {searchUserId : searchUserId},
                success : function(result){
                    $(".UserManagementInfo").remove();

                    $.each(result,function(index, obj){
                        let userNo = $("<th>").text(obj.userNo);
                        let userId = $("<th>").text(obj.userId);
                        let userStatus = $("<th>").text(obj.status == "Y" ? "정상":obj.status == "B" ? "정지":"탈퇴");
        
                        let tr = $("<tr>").append(userNo,userId,userStatus).addClass("UserManagementInfo");
                        $(".management_user_list_table").append(tr);
                    })
                }
            })
        }
    })

    $(".return_btn").click(function(){
        $(".modal_view").hide();
        $(".UserManagementInfo").remove();
        $(".userinfo").text(" ");

        $.ajax({
            url : "AlluserList",
            type : "get",
            success : function(result){

                $.each(result,function(index, obj){
                    let userNo = $("<th>").text(obj.userNo);
	                let userId = $("<th>").text(obj.userId);
                    let userStatus = $("<th>").text(obj.status == "Y" ? "정상":obj.status == "B" ? "정지":"탈퇴");
	
	                let tr = $("<tr>").append(userNo,userId,userStatus).addClass("UserManagementInfo");
	                $(".management_user_list_table").append(tr);
                })

                $("#userManagementModal").fadeIn(200);

            }
        })
        
    })


    $("#status_nomal").click(function(){
        var status = "Y";
        var userNo = $(this).parent().siblings("input").val();
        console.log(userNo);

        $.ajax({
            url : "statusChange",
            type : "get",
            data :{
                status : status,
                userNo : userNo
            },
            success : function(result){
                if(result == "success"){
                    alert("정상적으로 변경 되었습니다.");
                    $(".modal_view").fadeOut(200);
                    location.reload();
                } else {
                    alert("상태 변경에 실패하였습니다.")
                }
            }
        })
    })

    $("#status_secession").click(function(){
        var status = "N";
        var userNo = $(this).parent().siblings("input").val();
        console.log(userNo);

        $.ajax({
            url : "statusChange",
            type : "get",
            data :{
                status : status,
                userNo : userNo
            },
            success : function(result){
                if(result == "success"){
                    alert("정상적으로 변경 되었습니다.");
                    $(".modal_view").fadeOut(200);
                    location.reload();
                } else {
                    alert("상태 변경에 실패하였습니다.")
                }
            }
        })
    })

    $("#status_banned").click(function(){
        var status = "B";
        var userNo = $(this).parent().siblings("input").val();
        console.log(userNo);

        $.ajax({
            url : "statusChange",
            type : "get",
            data :{
                status : status,
                userNo : userNo
            },
            success : function(result){
                if(result == "success"){
                    alert("정상적으로 변경 되었습니다.");
                    $(".modal_view").fadeOut(200);
                    location.reload();
                } else {
                    alert("상태 변경에 실패하였습니다.")
                }
            }
        })
    })
    
})

$(document).on("click",".UserManagementInfo",function(){
    var userNo = $(this).children().eq(0).text();
    
    $.ajax({
        url : "selectUserManagement",
        type : "get",
        data : {userNo : userNo},
        success : function(result){
            $("#selectUserManagementModal .userNo").text(result.userNo);
            $("#selectUserManagementModal .userId").text(result.userId);
            $("#selectUserManagementModal .userNm").text(result.userName+"님");
            $("#userNo").val(result.userNo);

            $(".modal_view").fadeOut(200);
            $("#selectUserManagementModal").fadeIn(200);
        }
    })
})

$(".cancel_btn").click(function(){
    $(".modal_view").fadeOut(200);
})

