package ru.gb.file.net.storage.streamapi;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User implements Comparable<User> {

    private String userName;

    private List<BigDecimal> orders;

    public User(String userName, List<BigDecimal> orders) {
        this.userName = userName;
        this.orders = orders;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public List<BigDecimal> getOrders() {
        return orders;
    }

    @Override
    public int compareTo(User user) {
        return userName.compareTo(user.getUserName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
