package pt_sp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Trida Router predstavujici jeden uzel v grafu
 */
public class Router {
    /* Id routeru */
    private int id;

    /* List vsech sousednich hran */
    private ArrayList <Edge> next;

    /* Mapa na sousedy routeru */
    Map<Router, Integer> adjacentNodes = new HashMap<>();

    /* Nejkratsi cesta */
    private List<Router> shortestPath = new LinkedList<>();

    /* Vzdalenost */
    private int distance = Integer.MAX_VALUE;

    /**
     * Konstruktor routeru
     * @param id ID routeru
     */
    public Router(int id) {
        this.id = id;
        this.next = new ArrayList<>();
    }

    /**
     * Prida do seznamu sousednich hran hranu
     * @param edge hrana k pridani
     */
    public void addNext(Edge edge) {
        this.next.add(edge);
    }

//    /**
//     * Resetuje seznam sousedu routeru
//     */
//    public void resetNext() {
//        this.next = new ArrayList<Edge>();
//    }

    /**
     * Prida cil se vzdalenosti
     * @param destination cil
     * @param size vzdalenost
     */
    public void addDestination(Router destination, int size) {
        adjacentNodes.put(destination, size);
    }


    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         GETTERY A SETTERY                                                    |
       +--------------------------------------------------------------------------------------------------------------+
    */

    /**
     * Vraci ID routeru
     * @return ID routeru
     */
    public int getId() {
        return id;
    }

    /**
     * Vraci seznam sousedu routeru
     * @return seznam sousedu routeru
     */
    public List<Edge> getNext() {
        return next;
    }

    /**
     * Vraci seznam s nejkratsi cestou
     * @return seznam s nejkratsi cestou
     */
    public List<Router> getShortestPath() {
        return shortestPath;
    }

    /**
     * Nastavi nejkratsi cestu
     * @param shortestPath nejkratsi cesta
     */
    public void setShortestPath(List<Router> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * Vraci vzdalenost
     * @return vzdalenost
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Nastavi vzdalenost
     * @param distance vzdalenost
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Vraci mapu se sousednimi routery
     * @return mapa se sousednimi routery
     */
    public Map<Router, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                              TOSTRING                                                        |
       +--------------------------------------------------------------------------------------------------------------+
    */

    @Override
    public String toString() {
        return "Router c." + this.id;
    }
}
