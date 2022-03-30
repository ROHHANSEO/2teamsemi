$("button[type=submit]").click(function(){
    if($("input[type=checkbox]").is(":checked")){
        alert("Y")
        $("input[type=checkbox]").val("Y");
    } else {
        alert("N")
        $("input[type=checkbox]").val("N");
    }
})