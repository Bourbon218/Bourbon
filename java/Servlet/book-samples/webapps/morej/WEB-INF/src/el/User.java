package el;

public class User {

    private String name;
    private boolean male;
    private User friend;
    
    
    public User(){        
    }
    public User(String name, boolean male){
        this.name = name;
        this.male = male;
    }
    
    
    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
