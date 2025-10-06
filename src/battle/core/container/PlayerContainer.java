package battle.core.container;

import java.util.*;
import battle.core.Character;

public class PlayerContainer {
    private ArrayList<Character>[] players;

    @SuppressWarnings("unchecked")
    public PlayerContainer(int number) {
        this.players = (ArrayList<Character>[]) new ArrayList[number];
        for (int i = 0; i < number; i++) {
            this.players[i] = new ArrayList<>();
        }
    }

    public PlayerContainer add(int index, Character player) {
        if( index < 0 || index >= this.players.length ) {
            System.err.println("Index out of bounds: " + index);
            return this;
        }
        this.players[index].add(player);
        return this;
    }

    public List<Character> get(int index) {
        if( index < 0 || index >= this.players.length ) {
            System.err.println("Index out of bounds: " + index);
            return Collections.emptyList();
        }
        return this.players[index];
    }

    public List<Character> getAll() {
        ArrayList<Character> allPlayers = new ArrayList<>();
        for (ArrayList<Character> group : this.players) {
            allPlayers.addAll(group);
        }
        return allPlayers;
    }
}