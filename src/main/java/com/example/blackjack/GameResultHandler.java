package com.example.blackjack;

public interface GameResultHandler {
    String determineResult(Player p, Dealer d);//Oyun mantığı servisten ayrılabilir. Kurallar tek bir yerde toplanabilir.
}
