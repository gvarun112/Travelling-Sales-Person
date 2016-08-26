package lastassignment;
import java.util.ArrayList;
public class Exhaustive {

    static int BestDistance = Integer.MAX_VALUE;
    static ArrayList<Integer> BestTour = new ArrayList<>();
    static int lastVisited,n=130;

    public static void main(String[] args) {
        ArrayList<Integer> Tour = new ArrayList<>();
        ArrayList<Integer> RC = new ArrayList<>();
        int length = 0;
        
        for( int i = 0 ; i < n ; i++) {
            RC.add(i);
        }
        
        ArrayList<ArrayList<Integer>> RCPerm = permutate(RC);
        ArrayList<Integer> Solution = new ArrayList<>();
        
        for(int i=0 ; i<RCPerm.size() ; i++){
            Solution.clear();
            Solution = TSP(Tour, RCPerm.get(i) , length);
        }
            
        System.out.println(Solution);
    }
    
    private static int Dist(int a , int b){
        return Math.abs(a-b);
    }
    
    private static ArrayList<Integer> TSP(ArrayList<Integer> Tour, ArrayList<Integer> RC, int length) {
        if (RC.isEmpty()) {
            if (length<BestDistance) {
                BestDistance = length;
                BestTour = Tour;
            }
        }
        else {
            for (int i = 0 ; i < RC.size() ; i++) {
                Tour.add(RC.get(i));
                length += Dist(RC.get(i),lastVisited);
                lastVisited = RC.get(i);
                RC.remove(i);
                TSP(Tour,RC,length);
            }
        }
        return BestTour;
    }
    private static ArrayList<ArrayList<Integer>> permutate(ArrayList<Integer> list) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();

    result.add(new ArrayList<>());
    ArrayList<ArrayList<Integer>> current = new ArrayList<>();
    ArrayList<Integer> temp = null;
        for (Integer list1 : list) {
            
            
            for (ArrayList<Integer> l : result) {
                current.clear();
                for (int j = 0; j < l.size()+1; j++) {
                    l.add(j, list1);
                    temp = new ArrayList<>(l);
                    current.add(temp);
                    l.remove(j);
                }
              //  temp.clear();
            }
            result = new ArrayList<>(current);
        }
    return result;
    }
}