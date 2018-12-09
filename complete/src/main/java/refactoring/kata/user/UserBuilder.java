package refactoring.kata.user;

import refactoring.kata.trip.Trip;

public class UserBuilder {
    private User[] friends;
    private Trip[] trips;

    public static UserBuilder aUser() {
        return new UserBuilder();
    }

    private UserBuilder() {}

    public UserBuilder withFriends(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        addFriends(user, friends);
        addTrips(user, trips);
        return user;
    }

    private void addTrips(User user, Trip... trips) {
        if (trips == null) return;

        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    private void addFriends(User user, User[] friends) {
        if (friends == null) return;

        for(User friend : friends) {
            user.addFriend(friend);
        }
    }
}
