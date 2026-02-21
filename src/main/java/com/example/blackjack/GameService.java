package com.example.blackjack;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
@Service
public class GameService implements GameResultHandler {

	// oyunlar bellekte tutuluyor
	private final Map<String ,GameState > games = new ConcurrentHashMap<>();
	
    //Yeni oyun oluşturur.
	public GameState newGame() {
	    GameState g=GameState.createNew();
        games.put(g.getId(),g); 
	 	return g;
	}
	//Kart çekme mantığı
	public GameState hit(String gameId) {
    try {
        GameState g = games.get(gameId);
        if (g == null || g.isFinished()) return g;

        g.getPlayer().addCard(g.getDeck().drawCard());

        if (g.getPlayer().isBusted()) {
            g.setFinished(true);
            g.setResult("DEALER WIN");
			GameHistoryWriter.write(g);
        }
        return g;
    } catch (Exception e) {
        throw new RuntimeException("Hit işlemi sırasında hata oluştu", e);
    }
}
	
//Oyuncu durduğunda dealer oynar.
//try kullanma sebebim: dealer otomarik kart çekeceği için deste bitebilir. yaza sistemsel bir hata olabilir. 
public GameState stand(String gameId) {
	try {
		GameState g = games.get(gameId);
        if (g == null || g.isFinished()) return g;
		
		//Oyunda bir dealer oluştur bu dealer desteyı kullanarak oynasın.
        g.getDealer().play(g.getDeck());

        g.setResult(determineResult(g.getPlayer(), g.getDealer()));
        g.setFinished(true);

		GameHistoryWriter.write(g);
        return g;
    } catch (Exception e) {
        throw new RuntimeException("Stand işlemi sırasında hata oluştu", e);
    }
}

	//oyunun durumunu döndürür
	 public GameState getGame(String gameId) {
		 return games.get(gameId);
	 }

	 //Oyun sonucunu belirler.
     @Override
     public String determineResult(Player p, Dealer d) {
        if (p.isBusted()) {
            return "DEALER WIN";
        }

        if (d.isBusted()) {
            return "PLAYER WIN";
        }

        int ps = p.calculateScore();
        int ds = d.calculateScore();

        if (ps > ds) {
            return "PLAYER WIN";
        } else if (ds > ps) {
            return "DEALER WIN";
        } else {
            return "PUSH";
        }
    }	
}