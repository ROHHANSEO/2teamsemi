function deletebye(index){
    console.log(index)
    let deleteE = document.getElementById("image"+index)
    console.log(deleteE)
    $("#image"+index).parent().remove()
    $("file1").empty()
}
    
// 인덱스 생성
let index = 0;
$(function(){
      //드래그 앤 드롭
      //$(".imagin").sortable();
    
      //이미지 등록
    $("#file1").change(function(e){
        //ul 내용 비워주기
        $('#sortimg').empty();

        var files = e.target.files;
        var arr = Array.prototype.slice.call(files);
        
        var maxFileCnt = 10;   // 첨부파일 최대 개수
        var attFileCnt = document.querySelectorAll('#file1').length;    // 기존 추가된 첨부파일 개수
        var remainFileCnt = maxFileCnt - attFileCnt;    // 추가로 첨부가능한 개수
        var curFileCnt = this.files.length;  // 현재 선택된 첨부파일 개수
        console.log(remainFileCnt)
        console.log(curFileCnt)
        // 첨부파일 개수 확인
        if (curFileCnt > maxFileCnt) {
            alert("첨부파일은 최대 " + maxFileCnt + "개 까지 첨부 가능합니다.");
            return false;
        }

        //업로드 가능 파일인지 체크
        for(var i=0; i<files.length; i++){
            if(!checkExtension(files[i].name,files[i].size)){
                return false;
            }
        }
        preview(arr);
        
        function checkExtension(fileName,fileSize){
            var maxSize = 10971520;  //10MB

            if(fileSize >= maxSize){
                alert('이미지 크기가 초과되었습니다.');
                $("#file1").val("");  //파일 초기화
                return false;
            }
            
            return true;
        }

        
        function preview(arr){
            arr.forEach(function(f){

                //li에 이미지 추가
                var str = '<li class="previewImg">';
                //str += '<span>'+fileName+'</span><br>';
                
                //이미지 파일 미리보기
                if(f.type.match('image.*')){
                    
                    //파일을 읽기 위한 FileReader객체 생성
                    var reader = new FileReader(); 
                    reader.onload = function (e) { 
                        
                        //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                        str += '<a id="image'+ index +'" class="image" onclick="deletebye(`'+index+'`);">'+"<span class='del'>x</span>"+'<img src="'+e.target.result+'" title="'+f.name+'" width=200 height=200></a>';
                        str += '</li>';
                        $(str).appendTo('#sortimg');
                        ++index;
                    } 
                    reader.readAsDataURL(f);
                }
            })
        }
    })


    })
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
        }else if(!$("#file1").val()){
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

