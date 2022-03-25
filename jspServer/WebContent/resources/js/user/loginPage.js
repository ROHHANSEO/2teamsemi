$("button[type=submit]").click(function(){
    if($("input[type=checkbox]").is(":checked")){
        $("input[type=checkbox").val("Y");
        console.log("y")
    } else {
        $("input[type=checkbox").val("N");
        console.log("y")
    }
})