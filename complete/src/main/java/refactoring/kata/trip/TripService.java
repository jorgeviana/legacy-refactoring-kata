package refactoring.kata.trip;

import refactoring.kata.exception.UserNotLoggedInException;
import refactoring.kata.user.User;
import refactoring.kata.user.UserSession;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;

public class TripService {

    private static final User GUEST = null;

    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        checkUserIsLoggedIn();

        return user.isFriendsWith(loggedInUser()) ?
                tripsBy(user) :
                noTrips();
    }

    private void checkUserIsLoggedIn() throws UserNotLoggedInException {
        if (loggedInUser() == GUEST) {
            throw new UserNotLoggedInException();
        }
    }

    protected User loggedInUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> tripsBy(User user) {
        return TripDAO.findTripsByUser(user);
    }

    private List noTrips() {
        return EMPTY_LIST;
    }
}
