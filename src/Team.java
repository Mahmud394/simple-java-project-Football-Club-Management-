import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;
    private Coach coach;
    private String formation;
    private String playingStyle;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.formation = "4-4-2";
        this.playingStyle = "Balanced";
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
        this.formation = coach.getFormation();
        this.playingStyle = coach.getPlayingStyle();
    }

    public void viewTeamPerformance() {
        System.out.println("\nTeam: " + name);
        System.out.println("Formation: " + formation);
        System.out.println("Playing Style: " + playingStyle);
        System.out.println("\nPlayers:");
        for (Player player : players) {
            player.viewStats();
        }
        if (coach != null) {
            System.out.println("\nCoach:");
            coach.viewCoachingDetails();
        }
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Coach getCoach() {
        return coach;
    }
}