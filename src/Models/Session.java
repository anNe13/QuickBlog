package Models;


public class Session {
    private User user;
    private boolean isLoggedIn = false;

    public Session(User user) {
        this.user = user;
        this.isLoggedIn = true;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
