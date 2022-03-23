
$(".deleteimg").hide();

// 게시글 체크
function checkform() {
    
    // 제목 미설정시 알림
    if(confirm("글을 등록하시겠습니까?") == true){
        let theForm = document.useditemform;
        // 제목과 설명을 적지 않을시
        if(theForm.title.value == "" && theForm.content.value == ""){
            alert("제목과 설명을 작성해 주세요")
            return $("#title").focus();
        }else if(theForm.title.value == ""){
            alert("제목을 작성해 주세요.")
            return $("#title").focus();
            // 설명을 적지 않을 시
        }else if(theForm.content.value == ""){
            alert("상품 설명을 작성해 주세요")
            return $("#content").focus();
            // 카테고리 설정 하지 않았을시 알림
        }else if($("#large").val() == '대분류'){
            alert("카테고리를 선택해 주세요");
            return $("#large").focus();
            // 이미지 미선택시 알림
        }else if($("#titleImg").is('[src]') == false){
            alert("이미지는 최소 1장이 필요합니다.")
            return $("#imagin").focus();
            // 가격 작성 하지 않을 시 알림
        }else if(theForm.price.value == ''){
            alert("가격을 작성해 주세요")
            return $("#price").focus();
        }

        theForm.submit();
    }
}

// 중분류 도출
$("#large").change(function() {
    let large = $("#large").val()
    console.log(large)
    
    $.ajax({
        url:"largeSelect.do",
        data:{
            large : large
        },
        type:"get",
        success:function(list){
            console.log("Ajax 통신 성공")
            $("#middle").empty()
            $("#small").text()
            if(list.length != 0){
                let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
                $("#middle").append(examplemiddle)
                $.each(list, function(index, obj){
                    let middle = $("<option>").text(obj.name).attr("value", obj.code)
                    console.log(obj.name)
                    $("#middle").append(middle)
                })
            }else if(list.length == 0){// 대분류로 끝날 때
                let examplemiddle = $("<option>").text("중분류").attr("value", "중분류")
                $("#middle").append(examplemiddle)
            }
        },
        error:function(){
            console.log("Ajax 통신 실패")
            
        }
    })
})

// 소분류 도출
$("#middle").change(function() {
    let middle = $("#middle").val()
    console.log(middle)
    
    $.ajax({
        url:"middleSelect.do",
        data:{
            middle : middle
        },
        type:"get",
        success:function(list){
            console.log("Ajax 통신 성공")
            $("#small").empty()
            if(list.length != 0){
                let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
                $("#small").append(examplesmall)
                $.each(list, function(index, obj){
                    console.log(obj.name)
                    let small = $("<option>").text(obj.name).attr("value", obj.code)
                    $("#small").append(small)
                })
            }else if(list.length == 0){ // 중분류로 끝날 때
                let examplesmall = $("<option>").text("소분류").attr("value", "소분류")
                $("#small").append(examplesmall)
            }
        },
        error:function(){
            console.log("Ajax 통신 실패")
            
        }
    })
})

//결제 방식을 두가지 모드 체크 해제 했을 경우 알림
$("#one").click(function(){
    if($("#one").is(':checked') == false & $("#two").is(':checked') == false){
        alert("결제방식은 한가지 이상이여야 합니다.")
        $("#two").focus().prop("checked",true)
    }
})
$("#two").click(function(){
    if($("#one").is(':checked') == false & $("#two").is(':checked') == false){
        alert("결제방식은 한가지 이상이여야 합니다.")
        $("#two").focus().prop("checked",true)
    }
})

// 제목 40자 제한
$(function(){
    $("#title").keydown(function(){
        var inputLength = $(this).val().length;
        var remain = 20-inputLength
        $("#countone").html(inputLength)
        
        if(remain <= 0){
            alert("제목은 20자만 가능합니다.")
            $("#title").focus();
        }
    })
})

// 상품 설명 4000자 제한
$(function(){
    $("#content").keydown(function(){
        var inputLength = $(this).val().length;
        var remain = 2000-inputLength
        $("#counter").html(inputLength)

        if(remain <= 0){
            alert("제목은 20자만 가능합니다.")
            $("#content").focus();
        }
    })
})


//let count = 0; // 1, 파일ID file1 fil2
let i = 1;
// 카운트로 파일 넘버를 고를 수 있고 카운트가 10이 될 시 알림 울림
$("#imagin").click(function() {
    console.log($("#file"+i).val())
    console.log(i)
    console.log($("#contentImg9").is('[src]'))
    if($("#contentImg9").is('[src]') == false){
        for(i; i <= 10 ; i++){
            if($("#file"+i).val() == ''){
                $("#file"+i).click();
                break;
            }
        }
    }
    if($("#contentImg9").is('[src]') === true){
        alert("이미지는 10장 까지만 가능합니다.")
    }
})

// 파일 로드 후 이미지를 보여줌
function loadImg(inputFile, num){
    console.log(inputFile.files.length )
    if(inputFile.files.length == 1){
        var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고
        reader.readAsDataURL(inputFile.files[0]);
        
        reader.onload = function(e){
         	switch(num){
            case 1 : $("#titleImg").show(); $("#delimg1").show(); $("#titleImg").attr("src", e.target.result); break;
            case 2 : $("#contentImg1").show(); $("#delimg2").show(); $("#contentImg1").attr("src", e.target.result); break;
            case 3 : $("#contentImg2").show(); $("#delimg3").show(); $("#contentImg2").attr("src", e.target.result); break;
            case 4 : $("#contentImg3").show(); $("#delimg4").show(); $("#contentImg3").attr("src", e.target.result); break;
            case 5 : $("#contentImg4").show(); $("#delimg5").show(); $("#contentImg4").attr("src", e.target.result); break;
            case 6 : $("#contentImg5").show(); $("#delimg6").show(); $("#contentImg5").attr("src", e.target.result); break;
            case 7 : $("#contentImg6").show(); $("#delimg7").show(); $("#contentImg6").attr("src", e.target.result); break;
            case 8 : $("#contentImg7").show(); $("#delimg8").show(); $("#contentImg7").attr("src", e.target.result); break;
            case 9 : $("#contentImg8").show(); $("#delimg9").show(); $("#contentImg8").attr("src", e.target.result); break;
            case 10 : $("#contentImg9").show(); $("#delimg10").show(); $("#contentImg9").attr("src", e.target.result); break;
            }
        }
    }/*else if(inputFile.files.length > 1){
        var reader = new FileReader(); // https://developer.mozilla.org/ko/docs/Web/API/FileReader 사이트 참고
        for(let i = 0 ; i < 10 ; i++){
            reader.readAsDataURL(inputFile.files[i]);
            switch(i){
            case 1 : $("#titleImg").show(); $("#titleImg").attr("src", e.target.result); break;
            case 2 : $("#contentImg1").show(); $("#delimg2").show(); $("#contentImg1").attr("src", e.target.result); break;
            case 3 : $("#contentImg2").show(); $("#contentImg2").attr("src", e.target.result); break;
            case 4 : $("#contentImg3").show(); $("#contentImg3").attr("src", e.target.result); break;
            case 5 : $("#contentImg4").show(); $("#contentImg4").attr("src", e.target.result); break;
            case 6 : $("#contentImg5").show(); $("#contentImg5").attr("src", e.target.result); break;
            case 7 : $("#contentImg6").show(); $("#contentImg6").attr("src", e.target.result); break;
            case 8 : $("#contentImg7").show(); $("#contentImg7").attr("src", e.target.result); break;
            case 9 : $("#contentImg8").show(); $("#contentImg8").attr("src", e.target.result); break;
            case 10 : $("#contentImg9").show(); $("#contentImg9").attr("src", e.target.result); break;
            }
        }
    }*/
}