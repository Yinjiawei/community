package happiness.jason.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001, "你找的问题不在了，要不要换个试试？"),
    TARGET_PARAM_NOT_FOUND(2002, "未为选中任何一个问题或评论进行回复"),
    NO_LOGIN(2003, "未登录不能进行评论，请先登录"),
    SYSTEM_ERROR(2004, "服务器冒烟啦！！！要不然你稍后再试试～"),
    COMMENT_TYPE_NOT_FOUND(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不在了，要不要换个试试？");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }
}
