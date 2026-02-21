package com.example.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class AbstractPlayer {
    protected List<Card> hand = new ArrayList<>(); // oyuncunun elindeki kartlar listesi

	@JsonProperty("hand") // Json çıktısında hand olarak görülmesini sağlar.
    public List<Card> gethand(){
        return hand;
    }

    public void addCard(Card c){ // Kart ekler.
        hand.add(c);
    }
   
    public int getScore() {
    	 return calculateScore();
    }

    public boolean isBusted() { //21 i geçti mi kontrol eder.
    	return calculateScore()>21;
    }

    public int calculateScore() {
    	int score=0;
    	int aceCount=0; //As kartının özelliği için ayrı hesaplanır
    	
		//Oyuncunun elindeki tüm kartları tek tek dolaşır. As kartı varsa acecount'u artırır buna göre de score 10 puan düşürülür.
		//Çünkü as kartının özelliği
    	for(Card c: hand) {
    		score += c.getValue();
    		if(c.getRank()==Rank.ACE) {
    			aceCount++;
    		}
    	}
    	
		// As kartını 11 yerine 1 sayar.
    	while(score>21 && 0<aceCount) { 
    	  score -= 10;
    	  aceCount--;
    	}
    	return score;
    }
    //Kartları gösterir.Bunu konsol için yapar. 	
    public void showhand() {
        System.out.println("Kartlar: ");
    	for(Card c: hand) {
    		System.out.println("-" + c);
    	}
    		
    	System.out.println("Toplam puan: " + calculateScore());
    	System.out.println();
    	}

	//Dealer otomatik oynar. Player otomatik oynamaz bu yüzden burası abstract yazılmıştır.	
	//Bu fonksiyonu hem dealer hem player kullanır aynı metot farklı davranışlar uygullarlar bu bir polimorfizm örneğidir.
    public abstract void play(Deck deck);


}
