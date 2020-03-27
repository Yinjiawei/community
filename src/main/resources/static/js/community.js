$(function () {
    /**
     * 提交回复（comment）
     */
    $(".btn-comment").on('click', function () {
        let questionId = $("#question_id").val();
        let content = $("#question_content").val();
        createComment(content, questionId, 1)
    });

    /**
     * 提交评论（sub comment）
     */
    $(".btn-sub-comment").on('click', function () {
        let commentId = $(this).data("id");
        let content = $("#input-" + commentId).val();
        createComment(content, commentId, 2)
    });

    /**
     * 添加回复或评论
     */
    function createComment(content, parentId, type) {
        if (!content) {
            alert("回复内容不能为空");
            return;
        }

        let data = JSON.stringify({"parentId": parentId, "content": content, "type": type});
        $.ajax({
            type: "POST",
            dataType: "json",
            contentType: "application/json",
            url: "/comment",
            data: data,
            success: function (response) {
                console.log(response);
                if (response.code == 200) {
                    window.location.reload();
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
    }

    /**
     * 展开关闭二级评论
     */
    $(".span-comment").on('click', function () {
        let commentId = $(this).data("id");
        $("#comment-" + commentId).toggleClass("in");
        $(this).toggleClass("active");

        // 显示所有的评论
        if ($(this).hasClass("active")) {
            $.getJSON("/comment/" + commentId, function (data) {
                let items = [];
                $.each(data.data, function (key, comment) {
                    let htmlElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse comments",
                        html: `
                            <div class="media">
                                <div class="media-left">
                                    <a href="#">
                                        <img class="media-object img-rounded avatar" src="${comment.user.avatarUrl}">
                                    </a>
                                </div>
                                <div class="media-body">
                                    <h5 class="media-heading">
                                        <span >${comment.user.name}</span>
                                    </h5>
                                    <div>${comment.content}</div>
                                    <div class="menu">
                                    </div>
                                </div>
                            </div>
                        `
                    });
                    items.push(htmlElement);
                });
                
                let $commentBody =$("<div/>", {
                    "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 collapse sub-comments",
                    "id": "comment-" + commentId,
                    html: items.join()
                })
                $("#comment-" + commentId).append($commentBody);
            });
        }
    });
});