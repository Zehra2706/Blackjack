package com.example.blackjack;
import java.util.UUID;

public class GameState {
   
	private String id;
	private Deck deck;
	private Player player;
	private Dealer dealer;
	private boolean finished;
	private String result;
	private String message;
	//Overload
    public GameState() {
        this.id = UUID.randomUUID().toString();
		this.deck = new Deck();
		this.player = new Player();
		this.dealer = new Dealer();
	} 
	//Parametreli constructor içinde this() kullanmak, ortak başlatma kodunu tekrar yazmadan nesnenin tutarlı şekilde oluşturulmasını sağlar.
	public GameState(Player player) {
		this();
        this.player = player;
    }
    // Oyunu başlatır.
    public static GameState createNew() {
		GameState g=new GameState();
		
		g.getPlayer().addCard(g.getDeck().drawCard());
		g.getPlayer().addCard(g.getDeck().drawCard());
		g.getDealer().addCard(g.getDeck().drawCard());
		g.getDealer().addCard(g.getDeck().drawCard());
		
		return g;
	}

    //Dışardan erişebilmek için olan getler ve setler

	public String getId() {
		return id;
	}
	public void setId(String id) {
	if (id == null || id.isBlank()) {
        throw new IllegalArgumentException("Game id boş olamaz");
    }
		this.id = id;
	}
	public Deck getDeck() {
		return deck;
	}
	public void setDeck(Deck deck) {
	if (deck == null) {
        throw new IllegalArgumentException("Deck null olamaz");
    }
		this.deck = deck;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
	if (player == null) {
        throw new IllegalArgumentException("Player null olamaz");
    }
		this.player = player;
	}
	public Dealer getDealer() {
		return dealer;
	}
	public void setDealer(Dealer dealer) {
	if (dealer == null) {
        throw new IllegalArgumentException("Dealer null olamaz");
    }
		this.dealer = dealer;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	if (finished && this.result == null) {
        this.message = "Oyun bitti ama sonuç belirlenmedi";
    }
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
	if (result == null || result.isBlank()) {
        throw new IllegalArgumentException("Sonuç boş olamaz");
    }
		this.result = result;
	}
	public String getMessage() { return message; }
    public void setMessage(String message) {     
	    if (message != null && message.length() > 200) {
            this.message = message.substring(0, 200);
        } else {
            this.message = message;
        } 
	}
}