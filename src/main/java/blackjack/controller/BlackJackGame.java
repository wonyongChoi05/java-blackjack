package blackjack.controller;

import blackjack.domain.*;
import blackjack.exception.ResponseNotMatchException;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.stream.Collectors;

public class BlackJackGame {
    private final Dealer dealer;
    private final CardDeck cardDeck;
    private Players players;

    public BlackJackGame() {
        dealer = Dealer.getDealer();
        cardDeck = new CardDeck(CardFactory.createCardDeck());
    }

    public void run() {
        enrollPlayers();
        distributeCards();
        play();
        calculateResult();
    }

    private void enrollPlayers() {
        players = new Players(InputView.inputUserNames().stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    private void distributeCards() {
        OutputView.printDistributeConfirmMessage(dealer, players);

        dealer.receiveDistributedCards(cardDeck);

        players.receiveDistributedCardsAllPlayers(cardDeck);
        OutputView.printInitialPlayerCards(dealer, players);
    }

    private void play() {
        players.getPlayers()
                .forEach(this::playEachPlayerTurn);
        playDealerTurn();
    }

    private void playEachPlayerTurn(User player) {
        while (player.isReceivableOneMoreCard() && isResponseYesWithValidation(player)) {
            player.receiveOneMoreCard(cardDeck);
            OutputView.printUserCards(player);
        }
    }

    private boolean isResponseYesWithValidation(User player) {
        try {
            return Response.isYes(InputView.askOneMoreCard(player));
        } catch (ResponseNotMatchException e) {
            OutputView.printExceptionMessage(e.getMessage());
            return isResponseYesWithValidation(player);
        }
    }

    private void playDealerTurn() {
        if (dealer.isReceivableOneMoreCard()) {
            dealer.receiveOneMoreCard(cardDeck);
            OutputView.printDealerPlayConfirmMessage();
        }
    }

    private void calculateResult() {
        OutputView.printPlayerFinalScore(dealer, players);
        GameResult gameResult = GameResult.calculateGameResult(dealer, players);
        OutputView.printGameResult(gameResult);
    }
}