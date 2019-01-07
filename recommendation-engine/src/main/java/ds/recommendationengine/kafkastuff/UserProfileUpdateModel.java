package ds.recommendationengine.kafkastuff;

public class UserProfileUpdateModel {
    private String type;
    private String action;
    private String userId;

    public UserProfileUpdateModel () {};

    public UserProfileUpdateModel(String type, String action, String userId) {
        super();
        this.type   = type;
        this.action = action;
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}