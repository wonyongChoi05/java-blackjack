package model.user;

public enum Score {

    WIN("승"),
    TIE("무승부"),
    LOSE("패");

    public static final int BUST_NUMBER = 21;

    private final String name;

    Score(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Score judge(final int dealerTotalValue, final int playerTotalValue) {
        if (dealerTotalValue > BUST_NUMBER || playerTotalValue > BUST_NUMBER) {
            return judgeOverBurst(dealerTotalValue, playerTotalValue);
        }

        return judgeBelowBurst(dealerTotalValue, playerTotalValue);
    }

    private static Score judgeOverBurst(final int dealerTotalValue, final int userTotalValue) {
        if (userTotalValue > BUST_NUMBER && dealerTotalValue > BUST_NUMBER) {
            return LOSE;
        }
        if (userTotalValue > BUST_NUMBER) return LOSE;
        return WIN;
    }

    private static Score judgeBelowBurst(final int dealerTotalValue, final int userTotalValue) {
        if (dealerTotalValue == userTotalValue) {
            return TIE;
        }
        if (dealerTotalValue < userTotalValue) return WIN;
        return LOSE;
    }
}
