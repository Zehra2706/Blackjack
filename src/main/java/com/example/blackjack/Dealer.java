package com.example.blackjack;

public class Dealer extends AbstractPlayer implements Playable{
	
	//AbstactPlayerdeki fonksiyonları kullanır ve Otmatik oynama için fonksiyonu override eder.
    @Override
	public void play(Deck deck) {
		System.out .println("Dağıtıcı oynuyor....");
		
		//Blackjackdeki dealer kuralı: 17 olana kadar çeker.
		while(calculateScore()<17) {
			addCard(deck.drawCard());
		}
	   //Elini gösterir.
	   showhand();
	
	
	if(calculateScore()>21) {
		System.out.println("Dağıtıcı Kaybetti");
	  }
	else {
		System.out.println("Dağıtıcı durdu. Toplam: " + calculateScore() + "\n");
	  }
	}
	
	//Dağıtıcının bir kartı açık olmalı onu burda yapıyor.
	public void showFirstCard() {
	if(!gethand().isEmpty()) {
		System.out.println("Dağıtıcının açık kartı: " +gethand().get(0));
	 }
	else {
		System.out.println("Dağıtıcının kartı yok");
	  }
   }
}
