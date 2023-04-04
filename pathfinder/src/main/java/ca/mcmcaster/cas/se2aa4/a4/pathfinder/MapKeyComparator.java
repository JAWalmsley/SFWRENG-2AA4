package ca.mcmcaster.cas.se2aa4.a4.pathfinder;

import java.util.Comparator;
import java.util.Map;

public class MapKeyComparator implements Comparator<Node> {
    Map<Node, Float> compareMap;
    public MapKeyComparator(Map<Node, Float> compareSet) {
        this.compareMap = compareSet;
    }
    @Override
    public int compare(Node o1, Node o2) {
        float o1val = compareMap.get(o1);
        float o2val = compareMap.get(o2);
        if(o1val < o2val) {
            return -1;
        } else if(o1val > o2val) {
            return 1;
        } else {
            return 0;
        }
    }
    
}
