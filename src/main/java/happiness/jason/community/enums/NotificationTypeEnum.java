package happiness.jason.community.enums;

public enum  NotificationTypeEnum {
    REPLY_QUESTION(1, "回复了问题"),
    REPLY_COMMENT(2, "回复了评论");

    private Integer type;
    private String name;

    NotificationTypeEnum(Integer type, String name){
        this.type = type;
        this.name = name;
    }

    public Integer getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }

    public static String nameOfType(int type){
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()){
            if(notificationTypeEnum.getType() == type){
                return notificationTypeEnum.getName();
            }
        }

        return "";
    }
}
