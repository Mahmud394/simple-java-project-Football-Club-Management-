import java.util.List;

public class Fan {
    public void viewPlayerStats(Player player) {
        player.viewStats();
    }

    public void viewMatchSchedule(List<Match> matches) {
        if (matches == null || matches.isEmpty()) {
            System.out.println("\nNo matches scheduled yet.");
            return;
        }
        
        System.out.println("\n=== FC Black Pearl Match Schedule ===");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-15s %-25s %-15s %-25s %-15s\n", 
                         "Date", "Match", "Type", "Venue", "Status");
        System.out.println("------------------------------------------------------------");
        
        for (Match match : matches) {
            String matchInfo = match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName();
            String venue = match.getStadium().getName();
            String status = match.isHomeMatch() ? "HOME" : "AWAY";
            
            System.out.printf("%-15s %-25s %-15s %-25s %-15s\n",
                match.getFormattedDate(),
                matchInfo,
                match.getMatchType(),
                venue,
                status);
        }
        System.out.println("------------------------------------------------------------");
    }

    public void viewStadiumDetails(Stadium stadium) {
        stadium.displayStadiumDetails();
    }

    public void viewAllPlayers(List<Player> players) {
        System.out.println("\n--- FC Black Pearl Players ---");
        for (Player player : players) {
            player.viewStats();
            System.out.println();
        }
    }
}