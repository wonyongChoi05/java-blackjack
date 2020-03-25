package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardType;
import blackjack.domain.gamer.BettingMoney;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackResultTest {

    private Player player;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player = new Player("pobi");
        dealer = new Dealer();
    }

    @Test
    void getProfitRate() {
        assertThat(BlackJackResult.BLACKJACK.profit(BettingMoney.from(10000))).isEqualTo(Profit.from(15000));
        assertThat(BlackJackResult.WIN.profit(BettingMoney.from(10000))).isEqualTo(Profit.from(10000));
        assertThat(BlackJackResult.LOSE.profit(BettingMoney.from(10000))).isEqualTo(Profit.from(-10000));
        assertThat(BlackJackResult.DRAW.profit(BettingMoney.from(10000))).isEqualTo(Profit.from(0));
    }

    @ParameterizedTest
    @MethodSource("matchCards")
    @DisplayName("player에게 dealer를 가져와 승패 반환")
    void playerBusted(List<Card> playerCards, List<Card> dealerCards, BlackJackResult result) {
        for (Card card : playerCards) {
            player.draw(card);
        }
        for (Card card : dealerCards) {
            dealer.draw(card);
        }
        assertThat(BlackJackResult.judge(player, dealer)).isEqualTo(result);
    }

    private static Stream<Arguments> matchCards() {
        Card aceSpade = new Card(CardSymbol.ACE, CardType.SPADE);
        Card twoDiamond = new Card(CardSymbol.TWO, CardType.DIAMOND);
        Card jackHeart = new Card(CardSymbol.JACK, CardType.HEART);
        Card kingClover = new Card(CardSymbol.KING, CardType.CLOVER);
        List<Card> bustedCards = Arrays.asList(kingClover, jackHeart, twoDiamond);
        List<Card> blackJackCards = Arrays.asList(aceSpade, kingClover);
        List<Card> normalCards = Arrays.asList(jackHeart, kingClover);
        return Stream.of(
                Arguments.of(blackJackCards, blackJackCards, BlackJackResult.DRAW),
                Arguments.of(blackJackCards, normalCards, BlackJackResult.BLACKJACK),
                Arguments.of(bustedCards, bustedCards, BlackJackResult.LOSE),
                Arguments.of(normalCards, bustedCards, BlackJackResult.WIN),
                Arguments.of(normalCards, normalCards, BlackJackResult.DRAW),
                Arguments.of(normalCards, blackJackCards, BlackJackResult.LOSE));
    }
}