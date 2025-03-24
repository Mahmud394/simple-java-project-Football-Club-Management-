import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Match implements Serializable {
    private Team homeTeam;
    private Team awayTeam;
    private Date date;
    private int homeScore;
    private int awayScore;
    private double ticketIncome;
    private String matchType;
    private Stadium stadium;
    private boolean isHomeMatch;

    public Match(Team homeTeam, Team awayTeam, Date date, double ticketIncome, String matchType, Stadium stadium, boolean isHomeMatch) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.homeScore = 0;
        this.awayScore = 0;
        this.ticketIncome = ticketIncome;
        this.matchType = matchType;
        this.stadium = stadium;
        this.isHomeMatch = isHomeMatch;
    }

    public String getFormattedDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void displayMatchDetails() {
        System.out.println("\nMatch: " + homeTeam.getName() + " vs " + awayTeam.getName());
        System.out.println("Date: " + getFormattedDate());
        System.out.println("Type: " + matchType);
        System.out.println("Venue: " + stadium.getName() + " (" + stadium.getLocation() + ")");
        System.out.println("Status: " + (isHomeMatch ? "Home Game" : "Away Game"));
        if (isHomeMatch) {
            System.out.println("Ticket Income: $" + ticketIncome);
        }
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public Date getDate() {
        return date;
    }

    public double getTicketIncome() {
        return ticketIncome;
    }

    public String getMatchType() {
        return matchType;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public boolean isHomeMatch() {
        return isHomeMatch;
    }
}