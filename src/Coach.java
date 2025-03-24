import java.io.Serializable;

public class Coach implements Serializable {
    private String name;
    private int experienceYears;
    private String formation;
    private String playingStyle;
    private double salary;
    private int contractYears;

    public Coach(String name, int experienceYears, double salary, int contractYears) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.formation = "4-4-2";
        this.playingStyle = "Balanced";
        this.salary = salary;
        this.contractYears = contractYears;
    }

    public void viewPlayerPerformance(Player player) {
        player.viewStats();
    }

    public void viewTeamPerformance(Team team) {
        team.viewTeamPerformance();
    }

    public void setFormation(String formation) {
        this.formation = formation;
        System.out.println("Formation set to: " + formation);
    }

    public void setPlayingStyle(String playingStyle) {
        this.playingStyle = playingStyle;
        System.out.println("Playing style set to: " + playingStyle);
    }

    public void viewCoachingDetails() {
        System.out.println("\nCoach: " + name);
        System.out.println("Experience: " + experienceYears + " years");
        System.out.println("Current Formation: " + formation);
        System.out.println("Playing Style: " + playingStyle);
        System.out.println("Salary: $" + salary + "/year");
        System.out.println("Contract Years: " + contractYears);
    }

    public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getFormation() {
        return formation;
    }

    public String getPlayingStyle() {
        return playingStyle;
    }

    public double getSalary() {
        return salary;
    }

    public int getContractYears() {
        return contractYears;
    }
}