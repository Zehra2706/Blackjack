package com.example.blackjack;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//REST API katmanı
@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "*") 

public class GameController {
  private final GameService service;
  public GameController(GameService s) { 
    this.service=s; 
  }
  
  //Yeni oyun başlatır.
  @PostMapping("/new")
  public GameState newGame() {
	  return service.newGame();
  }
   //Kart çekme işlemi.
  @PostMapping("/{id}/hit")
  public GameState hit(@PathVariable String id) {
	  return service.hit(id);
  }
  //Oyuncu durur.
  @PostMapping("/{id}/stand")
  public GameState stand(@PathVariable String id) {
	  return service.stand(id);
  }
  //Oyun durumunu döndürür.
  @GetMapping("/{id}")
  public GameState get(@PathVariable String id) {
	  return service.getGame(id);
}
}
