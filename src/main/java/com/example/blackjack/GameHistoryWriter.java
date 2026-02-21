package com.example.blackjack;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class GameHistoryWriter {
        private static final String FILE_NAME = "game-history.txt";

    public static void write(GameState game) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true)) {

            String line = String.format(
                "%s | %s | %d | %d | %s%n",
                game.getId(),
                game.getResult(),
                game.getPlayer().calculateScore(),
                game.getDealer().calculateScore(),
                LocalDateTime.now()
            );

            fw.write(line);

        } catch (IOException e) {
            System.err.println("Oyun geçmişi yazılamadı: " + e.getMessage());
        }
    }
}
