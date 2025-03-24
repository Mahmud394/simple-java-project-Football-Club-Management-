import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Club fcBlackPearl = new Club();

        System.out.println("=== FC Black Pearl Management System ===");
        System.out.println("Home Stadium: FC Black Pearl Stadium");
        System.out.println("Location: FC Black Pearl Island");
        System.out.println("Initial Balance: $10,000,000\n");

        System.out.println("Select your role:");
        System.out.println("1. Club Manager");
        System.out.println("2. Coach");
        System.out.println("3. Player");
        System.out.println("4. Fan");
        System.out.print("Enter your choice (1-4): ");

        int role = scanner.nextInt();
        scanner.nextLine();

        switch (role) {
            case 1:
                fcBlackPearl.manageClubOperations(scanner);
                break;
            case 2:
                manageCoachOperations(scanner, fcBlackPearl);
                break;
            case 3:
                managePlayerOperations(scanner, fcBlackPearl);
                break;
            case 4:
                manageFanOperations(scanner, fcBlackPearl);
                break;
            default:
                System.out.println("Invalid role selection!");
        }

        scanner.close();
    }

    private static void manageCoachOperations(Scanner scanner, Club club) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        Coach coach = club.findCoachByName(name);
        if (coach == null) {
            System.out.println("Coach not registered in the system!");
            return;
        }

        while (true) {
            System.out.println("\n=== Coach Dashboard ===");
            System.out.println("1. View Team Performance");
            System.out.println("2. View Player Stats");
            System.out.println("3. Set Formation");
            System.out.println("4. Set Playing Style");
            System.out.println("5. View My Details");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Team team = new Team("FC Black Pearl");
                    for (Player player : club.getPlayers()) {
                        team.addPlayer(player);
                    }
                    team.setCoach(coach);
                    team.viewTeamPerformance();
                    break;
                case 2:
                    System.out.print("Enter player name: ");
                    String playerName = scanner.nextLine();
                    Player player = club.findPlayerByName(playerName);
                    if (player != null) {
                        coach.viewPlayerPerformance(player);
                    } else {
                        System.out.println("Player not found!");
                    }
                    break;
                case 3:
                    System.out.print("Enter new formation (e.g., 4-3-3): ");
                    String formation = scanner.nextLine();
                    coach.setFormation(formation);
                    break;
                case 4:
                    System.out.print("Enter playing style (Attacking/Defensive/Balanced): ");
                    String style = scanner.nextLine();
                    coach.setPlayingStyle(style);
                    break;
                case 5:
                    coach.viewCoachingDetails();
                    break;
                case 6:
                    System.out.println("Exiting coach dashboard...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void managePlayerOperations(Scanner scanner, Club club) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        Player player = club.findPlayerByName(name);
        if (player == null) {
            System.out.println("Player not registered in the system!");
            return;
        }

        while (true) {
            System.out.println("\n=== Player Dashboard ===");
            System.out.println("1. View My Stats");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    player.viewStats();
                    break;
                case 2:
                    System.out.println("Exiting player dashboard...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void manageFanOperations(Scanner scanner, Club club) {
        Fan fan = new Fan();
        club.loadMatches(); // Load matches when fan enters

        while (true) {
            System.out.println("\n=== Fan Dashboard ===");
            System.out.println("1. View All Players");
            System.out.println("2. View Player Stats");
            System.out.println("3. View Match Schedule");
            System.out.println("4. View Stadium Info");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    fan.viewAllPlayers(club.getPlayers());
                    break;
                case 2:
                    System.out.print("Enter player name: ");
                    String playerName = scanner.nextLine();
                    Player player = club.findPlayerByName(playerName);
                    if (player != null) {
                        fan.viewPlayerStats(player);
                    } else {
                        System.out.println("Player not found!");
                    }
                    break;
                case 3:
                    club.loadMatches(); // Refresh matches
                    fan.viewMatchSchedule(club.getMatches());
                    break;
                case 4:
                    fan.viewStadiumDetails(new Stadium("FC Black Pearl Stadium", "FC Black Pearl Island", 50000));
                    break;
                case 5:
                    System.out.println("Exiting fan dashboard...");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}