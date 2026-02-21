package com.example.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public final class Deck implements Shufflable{
	//Deste
	private final ArrayList<Card> cards =new ArrayList<>();
	
	//Constructor
	//	52 kart üretir ve karıştırır.
    public Deck(){
	for (Suit suit: Suit.values()) {
		for(Rank rank: Rank.values()) {
			cards.add(new Card(suit,rank));
		}
	 }
	 shuffle();
 }
    //Karıştırılabilir interfacesinden alınan fonksiyonu override ederek kartları karıştıran fonksiyon oluşturur.
    @Override
	public void shuffle() {
		Collections.shuffle(cards);
	}
    //Destenin en üstündeki kartı çeker.Odesteden çıkarır ve ilk kartı döndürür.
	public Card drawCard() {
	try {
        return cards.remove(0);
    } catch (IndexOutOfBoundsException e) {
        throw new IllegalStateException("Destede kart kalmadı", e);
    }
	}
	//Destede kaç kart kaldığını gösterir.
	public int remainingCards() {
		return cards.size();
	}
}