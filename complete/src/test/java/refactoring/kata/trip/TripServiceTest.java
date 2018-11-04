package refactoring.kata.trip;

import org.junit.Before;
import org.junit.Test;
import refactoring.kata.exception.UserNotLoggedInException;
import refactoring.kata.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();

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
        User otherUser = aUserWithTripsButNotFriendsWith(loggedInUser);

        List<Trip> otherUserTrips = tripService.getTripsByUser(otherUser);

        assertThat(otherUserTrips).isEmpty();
    }

    private User aUserWithTripsButNotFriendsWith(User user) {
        User otherUser = new User();
        otherUser.addTrip(new Trip());

        assertThat(otherUser.trips()).isNotEmpty();
        assertThat(otherUser.getFriends()).doesNotContain(user);

        return otherUser;
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }
    }
}
