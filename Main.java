import java.util.*;
class TeamStats
{
    String nameOfTeam;
    int pointsOfTeam;
    String [] lastFiveMatchResults;
    int totalPointsOfTeamsWithConsecutiveWinOrLose= 0;
    ArrayList<String> teamsWithConsecutiveWinOrLose = new ArrayList<>();
    public TeamStats(String nameOfTeam , int pointsOfTeam , String [] lastFiveMatchResults )
    {
        this.nameOfTeam = nameOfTeam;
        this.pointsOfTeam = pointsOfTeam;
        this.lastFiveMatchResults = lastFiveMatchResults;
    }
    public String getnameOfTeam()
    {
        return this.nameOfTeam;
    }
    public int getTeamPoints()
    {
        return this.pointsOfTeam;
    }

    public void successiveWinOrLose(ArrayList <TeamStats> teamsStats , String userInputOfWinOrLose , int n)
    {
        for (int teamIndex = 0; teamIndex < teamsStats.size(); teamIndex++) {
            int count = 1;
            for (int lastFiveMatchIndices = 0; lastFiveMatchIndices < teamsStats.get(teamIndex).lastFiveMatchResults.length - 1; lastFiveMatchIndices++) 
            {

                String previousMatchResult = teamsStats.get(teamIndex).lastFiveMatchResults[lastFiveMatchIndices];
                String nextMatchResult = teamsStats.get(teamIndex).lastFiveMatchResults[lastFiveMatchIndices + 1];

                if (previousMatchResult.equals(userInputOfWinOrLose) && nextMatchResult.equals(previousMatchResult)) 
                {
                    count++;
                }
            }
            if (count >= n) 
            {
                teamsWithConsecutiveWinOrLose.add(teamsStats.get(teamIndex).getnameOfTeam());
                totalPointsOfTeamsWithConsecutiveWinOrLose = totalPointsOfTeamsWithConsecutiveWinOrLose + teamsStats.get(teamIndex).getTeamPoints();
            }
        }

    }
    public void listOfTeamsWithConsecutiveWinOrLose()
    {
        try{
            if(teamsWithConsecutiveWinOrLose.size() == 0)
            {
                throw new ArithmeticException();
            }
            System.out.println("Filtered Teams are :");
            for (String teams : teamsWithConsecutiveWinOrLose)
            {
                System.out.println(teams);
            }
            System.out.println("Average of Filtered teams are :"+totalPointsOfTeamsWithConsecutiveWinOrLose/ teamsWithConsecutiveWinOrLose.size());
        }
        catch(ArithmeticException e )
        {
            System.out.println("No team is present with this consecutive win or lose status");
        }
    }
    public String toString()
    {

        return (this.nameOfTeam+" \t"+this.pointsOfTeam+" \t"+Arrays.toString(this.lastFiveMatchResults));
    }

}

public class Main 
{
    public static void main(String[] args) 
    {
        TeamStats stats = new TeamStats(" " , 0 , new String[]{"", "" , "" , "", ""});
        ArrayList<TeamStats> teamsStats = new ArrayList<>();
        teamsStats.add(new TeamStats("GT", 20, new String[]{"W", "W", "L", "L", "W"}));
        teamsStats.add(new TeamStats("LSG", 18, new String[]{"W", "L", "L", "W", "W"}));
        teamsStats.add(new TeamStats("RR", 16, new String[]{"W", "L", "W", "L", "L"}));
        teamsStats.add(new TeamStats("DC", 14, new String[]{"W", "W", "L", "W", "L"}));
        teamsStats.add(new TeamStats("RCB", 14, new String[]{"L", "W", "W", "L", "L"}));
        teamsStats.add(new TeamStats("KKR", 12, new String[]{"L", "W", "W", "L", "W"}));
        teamsStats.add(new TeamStats("PBKS", 12, new String[]{"L", "W", "L", "W", "L"}));
        teamsStats.add(new TeamStats("SRH", 12, new String[]{"W", "L", "L", "L", "L"}));
        teamsStats.add(new TeamStats("CSK", 8, new String[]{"L", "L", "W", "L", "W"}));
        teamsStats.add(new TeamStats("MI", 6, new String[]{"L", "W", "L", "W", "W"}));

        for (int teamIndex = 0; teamIndex < teamsStats.size(); teamIndex++)
        {
            System.out.println(teamsStats.get(teamIndex).toString());
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter W or L");
        String userInputOfWinOrLose = sc.next().toUpperCase();
        System.out.println("Enter the number for the times you want to know consecutives" + " " +userInputOfWinOrLose);
        int n = sc.nextInt();
        stats.successiveWinOrLose(teamsStats , userInputOfWinOrLose , n);
        stats.listOfTeamsWithConsecutiveWinOrLose();

}
}
