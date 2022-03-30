$(function(){
    $("#report_modal_container").hide();
    $(".input_hidden").hide();
})
    
    $(".block_board_list").click(function(){
        if($(this).hasClass("action")){
            $(".report_listcon").remove();
            $(".block_board_list").removeClass("action");
        } else {
            $(".report_listcon").remove();
            $(".block_board_list").removeClass("action");
            var boardNo=$(this).children(".board_no").text();
            var tr1 = "";
            var tr2 = "";
            console.log("no"+boardNo);
        
            $.ajax({
                url : "BlockBoardDetailList",
                type : "get",
                data : {boardNo : boardNo},
                success : function(result){
                    $.each(result, function(index, obj){
                        tr2 = "<tr class='report_listcon'><th class='block_no'>"+obj.blockNo+"</th><th>"+obj.categoryNm+"</th><td>"+obj.title+"</td><th>"+obj.upTime+"</th></tr>";
                        $(".no"+boardNo).after(tr2);
                    })
    
                    tr1 += "<tr class='report_listcon listcon_title'>";
                    tr1 += "<th>신고번호</th>"
                    tr1 += "<th>신고카테고리</th>"
                    tr1 += "<th>신고제목</th>"
                    tr1 += "<th>신고시간</th>"
                    tr1 += "</tr>"
    
                    $(".no"+boardNo).after(tr1).addClass("action");
                }
            })
        }
    })


    $(".selectSanctions").click(function(){
        $(".input_hidden").toggle();
    })


$(document).on("click",".report_listcon",function(){
    var blockNo = $(this).children(".block_no").text();
    $.ajax({
        url : "reportBoardDetail",
        type : "get",
        data : {blockNo : blockNo},
        success : function(result){
            $("#report_modal_container .report_title_name").text(result.title);
            $("#report_modal_container .content").text(result.content);
            $("#report_modal_container .block_no").val(blockNo);
            $("#report_modal_container").fadeIn(200);
        }
    })
})

$(".report_modal_cancel_btn").click(function(){
    $("#report_modal_container").fadeOut(200);
})

$(".report_sanctions_btn").click(function(){
    var blockNo = $("#report_modal_container .block_no").val();
    $.ajax({
        url : "reportSanctions",
        type : "post",
        data : {blockNo : blockNo},
        success : function(result){
            if(result == "success"){
                alert("정상적으로 제재 되었습니다.");
            } else {
                alert("에러발생");
            }
            $("#report_modal_container").fadeOut(200);
            location.reload();
        }
    })
})

$(".Sanctions").click(function(){

    var boardNo = [];
    $("input[name=boardNo]:checked").each(function(){
        var checkArr = $(this).val();
        boardNo.push(checkArr); 
        console.log(checkArr)
    });

    $.ajax({
        url : "selectSanctions",
        type : "post",
        traditional: true,
        data : {boardNo : boardNo},
        success : function(result){
            if(result == "success"){
                alert("정상적으로 제재 되었습니다.");
                location.reload();
            } else {
                alert("실패하였습니다.");
            }

        }
    })
})











