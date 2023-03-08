package model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static model.user.Score.LOSE;
import static model.user.Score.TIE;
import static model.user.Score.WIN;
import static model.user.Score.judge;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ScoreTest {

    @DisplayName("딜러와 플레이어의 카드 총 합에 따라 Score를 반환한다.")
    @Test
    void judgeTest() {
        // given, when, then
        assertAll(
                () -> assertThat(judge(21, 21)).isEqualTo(TIE),
                () -> assertThat(judge(20, 20)).isEqualTo(TIE),

                () -> assertThat(judge(19, 20)).isEqualTo(WIN),
                () -> assertThat(judge(22, 21)).isEqualTo(WIN),

                () -> assertThat(judge(21, 23)).isEqualTo(LOSE),
                () -> assertThat(judge(21, 20)).isEqualTo(LOSE)
        );
    }

}
