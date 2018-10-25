package refactoring.kata.trip;

import org.junit.Test;
import refactoring.kata.exception.UserNotLoggedInException;
import refactoring.kata.user.User;

public class TripServiceTest {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;

    private User loggedInUser;

    @Test(expected = UserNotLoggedInException.class) public void
    should_throw_an_exception_when_a_not_logged_in_user_tries_to_view_the_trips_of_any_other_user() throws UserNotLoggedInException {
        loggedInUser = GUEST;
        new TestableTripService().getTripsByUser(UNUSED_USER);
    }

    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedInUser() {
            return loggedInUser;
        }
    }
}
