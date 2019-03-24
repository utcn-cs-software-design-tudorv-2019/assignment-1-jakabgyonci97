package dal.entity;

public class User {
    public enum UserType {
        STUDENT{
            @Override
            public int getValue() {
                return 0;
            }
        },
        ADMIN{
            @Override
            public int getValue() {
                return 1;
            }
        };
        public abstract int getValue();
    }
    private int id;
    private String userName;
    private String password;
    private UserType userType;

    public User(){
        super();
    }

    public User(int id, String userName, String password, int userType) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        switch(userType){
            case 0: this.userType = UserType.STUDENT;break;
            case 1: this.userType = UserType.ADMIN;break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType.getValue();
    }

    public void setUserType(int userType) {
        switch(userType){
            case 0: this.userType = UserType.STUDENT;break;
            case 1: this.userType = UserType.ADMIN;break;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }
}
