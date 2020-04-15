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
            let $subCommentContainer = $("#comment-" + commentId);
            if ($subCommentContainer.children().length > 1) {
                return;
            }

            $.getJSON("/comment/" + commentId, function (data) {
                $.each(data.data.reverse(), function (key, comment) {
                    let gmtCreate = moment(comment.gmtCreate).format("YYYY-MM-DD");
                    let c = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments",
                        html: `<div class="media">
                                    <div class="media-left">
                                        <img class="media-object img-rounded avatar" src="${comment.user.avatarUrl}">
                                    </div>
                                    <div class="media-body">
                                        <h5 class="media-heading">
                                            <span>${comment.user.name}</span>
                                        </h5>
                                        <div>${comment.content}</div>
                                        <div class="menu">
                                            <span class="pull-right">${gmtCreate}</span>
                                        </div>
                                    </div>
                               </div>`
                    });
                    $subCommentContainer.prepend(c);
                });
            });
        }
    });

    /**
     * 点击点击啊tag
     */
    $(".publish-tag").on('click', function () {
        let previous = $("#tag").val();
        let newTag = $(this).text().trim();
        if (previous === newTag || previous.indexOf(',' + newTag) !== -1 || previous.indexOf(newTag + ',') === 0) {
            return;
        }
        if (previous) {
            $("#tag").val(previous + ',' + newTag);
        } else {
            $("#tag").val(newTag);
        }
    });

    /**
     * 打开关闭tag选择
     */

    $("#tag").on('focus', function () {
        $("#select_tag").show();
    });
    // $("#tag").on('blur', function(){
    //     $("#select_tag").hide();
    // });

    editormd("question_editor", {
        width  : "100%",
        height : 350,
        path: "/editormd/lib/",
        delay: 0,
        watch: false,
        placeholder: "请输入问题描述"
    });

    editormd.markdownToHTML("question_view", {

    });

});