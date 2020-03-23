$(function() {
    $(".btn-comment").on('click', function(){
        let questionId = $("#question_id").val();
        let content = $("#question_content").val();
        let data = JSON.stringify({"parentId": 1, "content":content, "type":1});
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            url: "/comment",
            data: data,
            success: function(response){
                console.log(response);
                if(response.code == 200){
                    $("#comment_section").hide();
                }else{
                    alert(response.message);
                }
            }
        });
    });
});