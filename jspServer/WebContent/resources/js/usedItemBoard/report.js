
	function askcancel(){
		var cancel = confirm("글 작성을 취소하시겠습니까?");
		if(cancel){
			 window.close()
		}else{
			return;
		}
	}
	//ok버튼 누를시에 form전송을 하고나서 콘솔창 닫기 
	$(function(){
		$("#sendValue").on('click', function(){
			console.log($("#title").text())
			console.log($("#content").text())
			theForm = document.reportform;
			if(confirm("게시글을 신고 하시겠습니까?") ==true){
				if(theForm.title.value == "" && theForm.content.value == ""){
					
					alert("제목과 내용을 입력해주셔야 등록이 됩니다.")
					
				}else if(theForm.title.value == ""){
					alert("제목을 입력해주셔야 합니다.")
				}else if(theForm.content.value == ""){
					alert("내용을 입력해주셔야 합니다.")
				}
				if(theForm.title.value != "" && theForm.content.value != ""){
					var formData = $("#reportform").serialize();
					console.log(formData)
					$.ajax({
						type:"POST", 
						url : "insertUsedReportform.do", 
						data : formData, 
						success :function(result){
							console.log(result)
							if(result != null){
								alert("등록이 완료되었습니다")
								parent.opener.location.reload();
								window.open('', '_self', '').close();
							}
							
						}, 
						error: function(e){
							console.log(e)
						}
					})
					
				}
				
			}else{
				return;
			}
			
		})
	})