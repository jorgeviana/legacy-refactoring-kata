package refactoring.kata.trip;

import org.junit.Before;
import org.junit.Test;
import refactoring.kata.exception.UserNotLoggedInException;
import refactoring.kata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.kata.user.UserBuilder.aUser;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User SOME_OTHER_USER = new User();

    private static final Trip MADAGASCAR = new Trip();

    private User loggedInUser;

    private TestableTripService tripService;

    @Before
    public void before() {
        tripService = new TestableTripService();
    }

    @Test(expected = UserNotLoggedInException.class) public void
    should_throw_an_exception_when_a_not_logged_in_user_tries_to_view_the_trips_of_any_other_user() throws UserNotLoggedInException {
        loggedInUser = GUEST;

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test public void
    should_not_show_any_trips_when_users_are_not_friends() throws UserNotLoggedInException {
        loggedInUser = REGISTERED_USER;

        User otherUser = aUser()
                .withFriends(SOME_OTHER_USER)
                .withTrips(MADAGASCAR)
                .build();

        List<Trip> otherUserTrips = tripService.getTripsByUser(otherUser);

        assertThat(otherUserTrips).isEmpty();
    }

    @Test public void
    should_show_the_trips_of_a_friend() throws UserNotLoggedInException {
        loggedInUser = REGISTERED_USER;

        User friend = aUser()
                .withFriends(loggedInUser)
                .withTrips(MADAGASCAR)
                .build();

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips).contains(MADAGASCAR);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }

        @Override
        protected List<Trip> findTripsByUser(User user) {
            return user.trips();
        }
    }
}
