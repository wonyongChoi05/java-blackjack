package blackjack.domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParticipantsTest {

    @Test
    @DisplayName("참가자들은 딜러와 플레이어들로 구성된다.")
    void createParticipants() {
        Participants participants = new Participants(
            List.of(new Player("마루"), new Player("엔젤앤지")));

        assertThat(participants.getPlayers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어가 1명 미만일 시 오류 발생")
    void createPlayersNumberException() {
        List<Player> playerList = List.of();

        assertThatThrownBy(() -> new Participants(playerList))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("플레이어가 8명 초과일 시 오류 발생")
    void createPlayersNumberException2() {
        List<Player> playerList = List.of(new Player("1"), new Player("2"), new Player("3"),
            new Player("4"), new Player("5"), new Player("6"),
            new Player("7"), new Player("8"), new Player("9"));

        assertThatThrownBy(() -> new Participants(playerList))
            .isInstanceOf(IllegalArgumentException.class);
    }

}