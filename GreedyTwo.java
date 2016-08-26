package lastassignment;
import java.util.ArrayList;
import java.util.Collections;
public class GreedyTwo {

    static int BestDistance = Integer.MAX_VALUE;
    static ArrayList<Integer> BestTour = new ArrayList<>();
    static ArrayList<Integer> Solution = new ArrayList<>();
    static int lastVisited,n=178,lowerBound;

    public static void main(String[] args) {
        ArrayList<Integer> Tour = new ArrayList<>();
        ArrayList<Integer> RC = new ArrayList<>();
        int length = 0;
        for( int i = 0 ; i < n ; i++) {
            RC.add(i);
        }
        
        ArrayList<Integer> sortedRC = RC;
        Collections.sort(sortedRC);
        lowerBound = RC.size()*Dist(sortedRC.get(0),sortedRC.get(1));
        
        ArrayList<ArrayList<Integer>> RCPerm = permutate(RC);
        for (ArrayList<Integer> RCPerm1 : RCPerm) {
            Solution.clear();
            TSP(Tour, RCPerm1, length);
        }
            
        for(int i=0 ; i<Solution.size() ; i++)
            System.out.println(Solution.get(i));
        System.out.println("BD: "+BestDistance);
    }
    
    private static int Dist(int a , int b){
        return Math.abs(a-b);
    }
    
    private static void TSP(ArrayList<Integer> Tour, ArrayList<Integer> RC, int length) {
        if(length>=BestDistance)
            ;
        else if (RC.isEmpty()) {
            if (length<BestDistance) {
                BestDistance = length;
                BestTour = Tour;
            }
            Solution = BestTour;
        }
        else {
            for (int i = 0 ; i < RC.size() ; i++) {
                Tour.add(RC.get(i));
                length += Dist(RC.get(i),lastVisited);
                lastVisited = RC.get(i);
                RC.remove(i);
                TSP(Tour,RC,length);
                if(length < lowerBound)
                    break;
            }
            Collections.sort(RC);
        }
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