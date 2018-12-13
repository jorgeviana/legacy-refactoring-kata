package refactoring.kata.trip;

import org.junit.Before;
import org.junit.Test;
import refactoring.kata.exception.UserNotLoggedInException;
import refactoring.kata.user.User;
import refactoring.kata.user.UserSession;

import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static refactoring.kata.user.UserBuilder.aUser;

public class TripServiceShould {

    private static final User GUEST = null;
    private static final User UNUSED_USER = null;
    private static final User REGISTERED_USER = new User();
    private static final User SOME_OTHER_USER = new User();

    private static final Trip MADAGASCAR = new Trip();

    private UserSession userSession = mock(UserSession.class);
    private TripDAO tripDAO = mock(TripDAO.class);

    private TripService tripService;

    @Before
    public void before() {
        tripService = new TripService(userSession, tripDAO);
    }

    @Test(expected = UserNotLoggedInException.class) public void
    throw_an_exception_when_a_not_logged_in_user_tries_to_view_the_trips_of_any_other_user() throws UserNotLoggedInException {
        given(userSession.getLoggedUser())
                .willReturn(GUEST);

        tripService.getTripsByUser(UNUSED_USER);
    }

    @Test public void
    not_show_any_trips_when_users_are_not_friends() throws UserNotLoggedInException {
        given(userSession.getLoggedUser())
                .willReturn(REGISTERED_USER);

        User otherUser = aUser()
                .withFriends(SOME_OTHER_USER)
                .withTrips(MADAGASCAR)
                .build();

        List<Trip> otherUserTrips = tripService.getTripsByUser(otherUser);

        assertThat(otherUserTrips).isEmpty();
    }

    @Test public void
    show_the_trips_of_a_friend() throws UserNotLoggedInException {
        given(userSession.getLoggedUser())
                .willReturn(REGISTERED_USER);

        User friend = aUser()
                .withFriends(REGISTERED_USER)
                .withTrips(MADAGASCAR)
                .build();

        given(tripDAO.tripsBy(friend))
                .willReturn(asList(MADAGASCAR));

        List<Trip> friendTrips = tripService.getTripsByUser(friend);

        assertThat(friendTrips).contains(MADAGASCAR);
    }
}
