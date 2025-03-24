import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Club {
    private String name;
    private List<Player> players;
    private List<Coach> coaches;
    private List<Team> teams;
    private List<Match> matches;
    private double finances;
    private Stadium homeStadium;
    private static final String CLUB_NAME = "FC Black Pearl";
    private static final String STADIUM_NAME = "FC Black Pearl Stadium";
    private static final String LOCATION = "FC Black Pearl Island";
    private static final int STADIUM_CAPACITY = 50000;

    public Club() {
        this.name = CLUB_NAME;
        this.players = new ArrayList<>();
        this.coaches = new ArrayList<>();
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.finances = 10000000;
        this.homeStadium = new Stadium(STADIUM_NAME, LOCATION, STADIUM_CAPACITY);
        loadData();
        
        Team mainTeam = new Team(CLUB_NAME);
        this.teams.add(mainTeam);
    }

    public void addPlayer(Player player) {
        double totalCost = player.getTransferFee() + (player.getWeeklySalary() * 52 * player.getContractYears());
        if (finances >= totalCost) {
            players.add(player);
            finances -= totalCost;
            savePlayers();
            System.out.println("Player added: " + player.getName());
            System.out.println("Transfer Fee: $" + player.getTransferFee());
            System.out.println("Weekly Salary: $" + player.getWeeklySalary());
            System.out.println("Contract Years: " + player.getContractYears());
            System.out.println("Total Cost: $" + totalCost);
        } else {
            System.out.println("Insufficient funds! Need: $" + totalCost + ", Available: $" + finances);
        }
        viewFinances();
    }

    public void removePlayer(Player player) {
        if (players.remove(player)) {
            double transferIncome = player.getTransferFee() * 0.8;
            finances += transferIncome;
            savePlayers();
            System.out.println("Player sold: " + player.getName());
            System.out.println("Transfer Income: $" + transferIncome);
        } else {
            System.out.println("Player not found!");
        }
        viewFinances();
    }

    public void editPlayerSalary(Scanner scanner) {
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        Player player = findPlayerByName(playerName);
        
        if (player != null) {
            try {
                System.out.println("Current weekly salary: $" + player.getWeeklySalary());
                System.out.print("Enter new weekly salary: $");
                double newSalary = scanner.nextDouble();
                scanner.nextLine();
                
                if (newSalary >= 0) {
                    java.lang.reflect.Field salaryField = Player.class.getDeclaredField("weeklySalary");
                    salaryField.setAccessible(true);
                    salaryField.set(player, newSalary);
                    savePlayers();
                    System.out.println("Salary updated successfully!");
                } else {
                    System.out.println("Salary cannot be negative!");
                }
            } catch (Exception e) {
                System.out.println("Error updating salary: " + e.getMessage());
            }
        } else {
            System.out.println("Player not found!");
        }
    }

    public void addCoach(Coach coach) {
        double totalCost = coach.getSalary() * coach.getContractYears();
        if (finances >= totalCost) {
            coaches.add(coach);
            finances -= totalCost;
            saveCoaches();
            System.out.println("Coach added: " + coach.getName());
            System.out.println("Annual Salary: $" + coach.getSalary());
            System.out.println("Contract Years: " + coach.getContractYears());
            System.out.println("Total Cost: $" + totalCost);
        } else {
            System.out.println("Insufficient funds! Need: $" + totalCost + ", Available: $" + finances);
        }
        viewFinances();
    }

    public void removeCoach(Coach coach) {
        if (coaches.remove(coach)) {
            double settlement = coach.getSalary() * coach.getContractYears() * 0.5;
            finances -= settlement;
            saveCoaches();
            System.out.println("Coach removed: " + coach.getName());
            System.out.println("Contract settlement paid: $" + settlement);
        } else {
            System.out.println("Coach not found!");
        }
        viewFinances();
    }

    public void scheduleMatch(Match match) {
        matches.add(match);
        if (match.isHomeMatch()) {
            finances += match.getTicketIncome();
            System.out.println("Ticket income added: $" + match.getTicketIncome());
        }
        saveMatches();
        viewFinances();
    }

    public void viewFinances() {
        System.out.println("\n=== FC Black Pearl Finances ===");
        System.out.println("Current Balance: $" + String.format("%,.2f", finances));
        
        double weeklyWages = players.stream().mapToDouble(Player::getWeeklySalary).sum();
        double annualWages = weeklyWages * 52;
        System.out.println("Weekly Player Wages: $" + String.format("%,.2f", weeklyWages));
        System.out.println("Annual Player Wages: $" + String.format("%,.2f", annualWages));
        
        double coachSalaries = coaches.stream().mapToDouble(Coach::getSalary).sum();
        System.out.println("Annual Coach Salaries: $" + String.format("%,.2f", coachSalaries));
        
        System.out.println("\nAssets:");
        System.out.println("Players: " + players.size());
        System.out.println("Coaches: " + coaches.size());
        System.out.println("Stadium: " + homeStadium.getName() + " (" + homeStadium.getCapacity() + " capacity)");
    }

    public void generateTeamReport() {
        System.out.println("\n=== Team Performance Report ===");
        for (Team team : teams) {
            team.viewTeamPerformance();
        }
    }

    public void generatePlayerReport() {
        System.out.println("\n=== Player Performance Report ===");
        for (Player player : players) {
            player.viewStats();
            System.out.println();
        }
    }

    public void manageClubOperations(Scanner scanner) {
        while (true) {
            System.out.println("\n=== FC Black Pearl Management System ===");
            System.out.println("1. Add Player");
            System.out.println("2. Remove Player");
            System.out.println("3. Edit Player Salary");
            System.out.println("4. Add Coach");
            System.out.println("5. Remove Coach");
            System.out.println("6. Schedule Match");
            System.out.println("7. View Finances");
            System.out.println("8. Generate Team Report");
            System.out.println("9. Generate Player Report");
            System.out.println("10. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    String playerName = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter transfer fee: $");
                    double transferFee = scanner.nextDouble();
                    System.out.print("Enter contract years: ");
                    int contractYears = scanner.nextInt();
                    System.out.print("Enter weekly salary: $");
                    double weeklySalary = scanner.nextDouble();
                    scanner.nextLine();
                    
                    Player newPlayer = new Player(playerName, position, transferFee, contractYears, weeklySalary);
                    addPlayer(newPlayer);
                    break;

                case 2:
                    System.out.print("Enter player name to remove: ");
                    String playerToRemove = scanner.nextLine();
                    Player player = findPlayerByName(playerToRemove);
                    if (player != null) {
                        removePlayer(player);
                    } else {
                        System.out.println("Player not found!");
                    }
                    break;

                case 3:
                    editPlayerSalary(scanner);
                    break;

                case 4:
                    System.out.print("Enter coach name: ");
                    String coachName = scanner.nextLine();
                    System.out.print("Enter experience years: ");
                    int experience = scanner.nextInt();
                    System.out.print("Enter annual salary: $");
                    double salary = scanner.nextDouble();
                    System.out.print("Enter contract years: ");
                    contractYears = scanner.nextInt();
                    scanner.nextLine();
                    
                    Coach newCoach = new Coach(coachName, experience, salary, contractYears);
                    addCoach(newCoach);
                    break;

                case 5:
                    System.out.print("Enter coach name to remove: ");
                    String coachToRemove = scanner.nextLine();
                    Coach coach = findCoachByName(coachToRemove);
                    if (coach != null) {
                        removeCoach(coach);
                    } else {
                        System.out.println("Coach not found!");
                    }
                    break;

                case 6:
                    System.out.print("Is this a home match? (yes/no): ");
                    boolean isHome = scanner.nextLine().equalsIgnoreCase("yes");
                    
                    Team homeTeam = new Team(CLUB_NAME);
                    System.out.print("Enter away team name: ");
                    String awayTeamName = scanner.nextLine();
                    Team awayTeam = new Team(awayTeamName);
                    
                    System.out.print("Enter match type (Friendly/League/Cup): ");
                    String matchType = scanner.nextLine();
                    
                    double ticketIncome = 0;
                    Stadium stadium;
                    if (isHome) {
                        stadium = homeStadium;
                        System.out.print("Enter expected ticket income: $");
                        ticketIncome = scanner.nextDouble();
                        scanner.nextLine();
                    } else {
                        System.out.print("Enter stadium name: ");
                        String stadiumName = scanner.nextLine();
                        System.out.print("Enter stadium location: ");
                        String location = scanner.nextLine();
                        System.out.print("Enter stadium capacity: ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();
                        stadium = new Stadium(stadiumName, location, capacity);
                    }
                    
                    Match newMatch = new Match(homeTeam, awayTeam, new Date(), ticketIncome, matchType, stadium, isHome);
                    scheduleMatch(newMatch);
                    break;

                case 7:
                    viewFinances();
                    break;

                case 8:
                    generateTeamReport();
                    break;

                case 9:
                    generatePlayerReport();
                    break;

                case 10:
                    saveData();
                    System.out.println("Exiting club management system...");
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public Player findPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(name)) {
                return player;
            }
        }
        return null;
    }

    public Coach findCoachByName(String name) {
        for (Coach coach : coaches) {
            if (coach.getName().equalsIgnoreCase(name)) {
                return coach;
            }
        }
        return null;
    }

    public void saveData() {
        savePlayers();
        saveCoaches();
        saveMatches();
    }

    public void loadData() {
        loadPlayers();
        loadCoaches();
        loadMatches();
    }

    public void savePlayers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("players.txt"))) {
            for (Player player : players) {
                writer.println(player.getName() + "," + 
                              player.getPosition() + "," + 
                              player.getGoals() + "," + 
                              player.getAssists() + "," + 
                              player.getMatchesPlayed() + "," + 
                              player.getTransferFee() + "," + 
                              player.getContractYears() + "," + 
                              player.getWeeklySalary());
            }
        } catch (IOException e) {
            System.out.println("Error saving players: " + e.getMessage());
        }
    }

    public void loadPlayers() {
        players.clear();
        File file = new File("players.txt");
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    Player player = new Player(
                        parts[0], 
                        parts[1], 
                        Double.parseDouble(parts[5]), 
                        Integer.parseInt(parts[6]), 
                        Double.parseDouble(parts[7]));
                    player.goals = Integer.parseInt(parts[2]);
                    player.assists = Integer.parseInt(parts[3]);
                    player.matchesPlayed = Integer.parseInt(parts[4]);
                    players.add(player);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
    }

    public void saveCoaches() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("coaches.txt"))) {
            for (Coach coach : coaches) {
                writer.println(coach.getName() + "," + 
                             coach.getExperienceYears() + "," + 
                             coach.getFormation() + "," + 
                             coach.getPlayingStyle() + "," + 
                             coach.getSalary() + "," + 
                             coach.getContractYears());
            }
        } catch (IOException e) {
            System.out.println("Error saving coaches: " + e.getMessage());
        }
    }

    public void loadCoaches() {
        coaches.clear();
        File file = new File("coaches.txt");
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Coach coach = new Coach(
                        parts[0], 
                        Integer.parseInt(parts[1]), 
                        Double.parseDouble(parts[4]), 
                        Integer.parseInt(parts[5]));
                    coach.setFormation(parts[2]);
                    coach.setPlayingStyle(parts[3]);
                    coaches.add(coach);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading coaches: " + e.getMessage());
        }
    }

    public void saveMatches() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("matches.txt"))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (Match match : matches) {
                writer.println(match.getHomeTeam().getName() + "," + 
                             match.getAwayTeam().getName() + "," + 
                             sdf.format(match.getDate()) + "," + 
                             match.getTicketIncome() + "," + 
                             match.getMatchType() + "," + 
                             match.getStadium().getName() + "," + 
                             match.getStadium().getLocation() + "," + 
                             match.getStadium().getCapacity() + "," + 
                             match.isHomeMatch());
            }
        } catch (IOException e) {
            System.out.println("Error saving matches: " + e.getMessage());
        }
    }

    public void loadMatches() {
        matches.clear();
        File file = new File("matches.txt");
        if (!file.exists()) {
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    Team homeTeam = new Team(parts[0]);
                    Team awayTeam = new Team(parts[1]);
                    Date date = sdf.parse(parts[2]);
                    double ticketIncome = Double.parseDouble(parts[3]);
                    String matchType = parts[4];
                    Stadium stadium = new Stadium(parts[5], parts[6], Integer.parseInt(parts[7]));
                    boolean isHome = Boolean.parseBoolean(parts[8]);
                    
                    Match match = new Match(homeTeam, awayTeam, date, ticketIncome, matchType, stadium, isHome);
                    matches.add(match);
                }
            }
            System.out.println("Loaded " + matches.size() + " matches.");
        } catch (IOException e) {
            System.out.println("Error loading matches: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing date in matches: " + e.getMessage());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Match> getMatches() {
        return matches;
    }
}