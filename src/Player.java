import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private String position;
    public int goals;
    public int assists;
    public int matchesPlayed;
    private String currentClub;
    private double transferFee;
    private int contractYears;
    private double weeklySalary;

    public Player(String name, String position, double transferFee, int contractYears, double weeklySalary) {
        this.name = name;
        this.position = position;
        this.goals = 0;
        this.assists = 0;
        this.matchesPlayed = 0;
        this.currentClub = "FC Black Pearl";
        this.transferFee = transferFee;
        this.contractYears = contractYears;
        this.weeklySalary = weeklySalary;
    }

    public void viewStats() {
        System.out.println("\nPlayer: " + name);
        System.out.println("Position: " + position);
        System.out.println("Current Club: " + currentClub);
        System.out.println("Goals: " + goals);
        System.out.println("Assists: " + assists);
        System.out.println("Matches Played: " + matchesPlayed);
        System.out.println("Weekly Salary: $" + weeklySalary);
        System.out.println("Contract Years: " + contractYears);
    }

    public void scoreGoal() {
        this.goals++;
        matchesPlayed++;
    }

    public void assistGoal() {
        this.assists++;
        matchesPlayed++;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public int getContractYears() {
        return contractYears;
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }
}