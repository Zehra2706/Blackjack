package com.example.blackjack;

public class Card {

	private Suit suit;
	private Rank rank;
	
	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}
    //Dışardan erişebilmesi için yazılan get set methodları 
	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	//Kartın blackjack değerini döndürür.
    public int getValue(){
        return rank.getValue();
    }
	
	//Konsol için toString metodu
    @Override
	public String toString() {
		return "Kartın Suit: " +suit + "  Rank:" +rank+"  Value: " + getValue();
	}
}
