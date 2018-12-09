package refactoring.kata.trip;

import org.junit.Test;
import refactoring.kata.user.User;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.kata.user.UserBuilder.aUser;

public class UserShould {

    private static final User FRIENDLY = aUser().build();

    @Test
    public void know_about_his_friends() {
        User user = aUser()
                .withFriends(FRIENDLY)
                .build();

        assertThat(user.isFriendsWith(FRIENDLY)).isTrue();
    }

    @Test
    public void know_about_when_someone_is_not_his_friend() {
        User aUserWithNoFriends = aUser()
                .build();

        assertThat(aUserWithNoFriends.isFriendsWith(FRIENDLY)).isFalse();
    }
}
