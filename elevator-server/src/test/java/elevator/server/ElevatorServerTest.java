package elevator.server;

import org.junit.Test;

import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;

public class ElevatorServerTest {

    @Test
    public void should_add_elevator_game() throws Exception {
        Player email = new Player("player@provider.com", "pseudo");
        ElevatorServer elevatorServer = new ElevatorServer();

        elevatorServer.addElevatorGame(email, new URL("http://127.0.0.1"));

        assertThat(elevatorServer.players()).hasSize(1).containsOnly(email);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_add_elevator_game_if_protocol_is_not_http() throws Exception {
        ElevatorServer elevatorServer = new ElevatorServer();

        elevatorServer.addElevatorGame(new Player("player@provider.com", "pseudo"), new URL("https://127.0.0.1"));

        fail();
    }

    @Test(expected = IllegalStateException.class)
    public void should_not_add_elevator_game_with_same_pseudo_twice() throws Exception {
        ElevatorServer elevatorServer = new ElevatorServer();

        elevatorServer.
                addElevatorGame(new Player("player@provider.com", "pseudo"), new URL("http://127.0.0.1")).
                addElevatorGame(new Player("player@provider.com", "pseudo"), new URL("http://127.0.0.1:8080/myApp"));
    }

}