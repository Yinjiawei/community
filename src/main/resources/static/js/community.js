$(function () {
    $(".btn-comment").on('click', function () {
        let questionId = $("#question_id").val();
        let content = $("#question_content").val();
        let data = JSON.stringify({"parentId": 1, "content": content, "type": 1});
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            url: "/comment",
            data: data,
            success: function (response) {
                console.log(response);
                if (response.code == 200) {
                    $("#comment_section").hide();
                } else if (response.code == 2003) {
                    let isAccept = confirm(response.message);
                    if (isAccept) {
                        window.open("https://github.com/login/oauth/authorize?client_id=f7f2611045cd95d27226&redirect_uri=http://localhost:8888/callback&scope=user&state=1");
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(response.message);
                }
            }
        });
    });
});