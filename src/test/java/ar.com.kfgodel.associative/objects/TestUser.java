package ar.com.kfgodel.associative.objects;

/**
 * This class serves as a test object for storing a typical object
 * Created by kfgodel on 01/08/15.
 */
public class TestUser {

    private String loginId;
    private String name;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TestUser create(String loginId, String name) {
        TestUser user = new TestUser();
        user.setLoginId(loginId);
        user.setName(name);
        return user;
    }

}
