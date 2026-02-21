package com.example.blackjack;

public class Player extends AbstractPlayer implements  Playable{

        @Override
        public void play(Deck deck) {
            System.out.println("Oyuncu oynuyor.");
        }

}