package refactoring.kata.trip;

import refactoring.kata.exception.DependendClassCallDuringUnitTestException;
import refactoring.kata.user.User;

import java.util.List;

public class TripDAO {

	public static List<Trip> findTripsByUser(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}

	public List<Trip> tripsBy(User user) {
		throw new DependendClassCallDuringUnitTestException(
				"TripDAO should not be invoked on an unit test.");
	}
}
