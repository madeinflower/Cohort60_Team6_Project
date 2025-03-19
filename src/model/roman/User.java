package model.roman;

import java.util.Objects;

/*** Author: Roman Romashko Date: 19.03.2025 ***/

public class User {
    public String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof User user)) return false;

        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
