package battle.arena;

import battle.core.Character;
import battle.core.container.*;
import battle.core.CharacterGenerator;
import battle.core.effect.EffectProvider;
import battle.core.ability.AbilityFactory;
import battle.core.context.BotController;
import java.util.*;

public class Arena {
    private int round;
    private int playerAmount;
    private PlayerContainer playerContainer;
    private EffectContainer effectContainer;
    private EffectProvider effectProvider;
    private Map<String, Object> abilityParams;
    private AbilityFactory abilityFactory = AbilityFactory.getInstance();

    public Arena(int playerAmount) {
        this.playerAmount = playerAmount;
        this.init();
    }

    private int init() {
        this.playerContainer = new PlayerContainer(playerAmount);
        this.effectContainer = new EffectContainer();
        this.round = 0;
        this.effectProvider = new EffectProvider(this.effectContainer);
        this.abilityParams = Map.of(
            "PROVIDER", this.effectProvider
        );

        
        for(int i = 0; i < playerAmount; i++) {
            Character player = CharacterGenerator.gen(0);
            player.addAbility(abilityFactory.create("Heal", abilityParams));
            player.addAbility(abilityFactory.create("Attack", abilityParams));
            player.addAbility(abilityFactory.create("DamageBoost", abilityParams));
            this.playerContainer.add(i, player);
        }
        return 0;
    }

    public int getAliveCount() {
        int count = 0;
        for (Character player : this.playerContainer.getAll()) {
            if (player.isAlive()) {
                count++;
            }
        }
        return count;
    }
    public int getRound() {
        return this.round;
    }

    private void render(Character currentPlayer) {
        clearConsole();
        System.out.println("================== Round " + this.round + " ==================");
        System.out.println("Alive Players: " + getAliveCount() + "/" + this.playerAmount);
        System.out.println("-------------------------------------------");
        for (Character player : this.playerContainer.getAll()) {
            String status = player.isAlive() ? "Alive" : "Fallen";
            String indicator = (player == currentPlayer && player.isAlive()) ? " ●" : "";
            System.out.printf("| %-15s | HP: %3d/%-3d | Energy: %3d/%-3d | ATK: %3d | Status: %-6s |%s\n",
                player.getName(), player.getHealth(), player.getMaxHealth(),
                player.getEnergy(), player.getMaxEnergy(), player.getDamage(), status, indicator);
        }
        System.out.println("===========================================");
    }

    private void clearConsole() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Ignore exceptions in clearing console
        }
    }

    private void announceWinner() {
        for (Character player : this.playerContainer.getAll()) {
            if (player.isAlive()) {
                System.out.println("\n===========================================");
                System.out.println("The winner is " + player.getName() + "!");
                System.out.println("===========================================");
                return;
            }
        }
        System.out.println("\nNo one survived the battle.");
    }

    public void run() {
        System.out.println();
        while (getAliveCount() > 1) {
            this.round++;
            List<Character> allPlayers = this.playerContainer.getAll();

            for (Character currentPlayer : allPlayers) {
                if (!currentPlayer.isAlive()) {
                    continue;
                }
                
                render(currentPlayer);
                System.out.println("\n" + currentPlayer.getName() + "'s turn...");
                
                try {
                    Thread.sleep(100); // Pause for player to see the state
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                BotController.apply(currentPlayer, allPlayers);
                currentPlayer.earnEnergy(25);
                this.effectContainer.applyAll(); // Process all generated effects
                try {
                    Thread.sleep(100); // Pause to see the result of the action
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        render(null); // Final render without any active player
        announceWinner();
    }
}