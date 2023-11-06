package com.srh.api.model;

public class Tuple {
    String user;
    String location;

    public Tuple(String user, String location) {
        this.user = user;
        this.location = location;
    }

    // You should also implement equals() and hashCode() for Tuple to work correctly as a key in HashMap.
    // Java requires that if two objects are equal, they must have the same hash code.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return user.equals(tuple.user) && location.equals(tuple.location);
    }

    @Override
    public int hashCode() {
        return 31 * user.hashCode() + location.hashCode();
    }
}
