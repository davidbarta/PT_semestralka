package pt_sp;

import java.util.*;
import java.util.Map;

/**
 * Trida Graph predstavujici graf
 */
public class Graph {
	
	/* Hashovaci tabulka s routery */
	private Hashtable<Integer, Router> routers;
	
	/* Seznam hran v grafu */
	private List<Edge> edges;
	
	/* Hashset s routery */
	private Set<Router> nodes = new HashSet<>();

    /**
     * Konstruktor grafu
     * @param routers tabulka s routery grafu
     * @param edges seznam hran grafu
     */
	public Graph(Hashtable<Integer, Router> routers, List<Edge> edges) {
	    this.routers = routers;
	    this.edges = edges;
        makeGraph();
	}

    /**
     * Vytvori graf
     */
	private void makeGraph() {
        for (Edge e : edges) {
            routers.get(e.getID()).getNext().add(e);
        }

        Set<Integer> set = routers.keySet();
        for (Integer key : set) {
            nodes.add(routers.get(key));

            //kontrolni vypis
            //System.out.println(routers.get(key).getId() + ": " + routers.get(key).getNext().size());

            for (Edge e : routers.get(key).getNext()) {
                routers.get(key).addDestination(e.getNextRouter(), e.getSize());
            }
        }
    }
	
	/**
	 * Vypise do konzole seznam hran
	 * @param edges seznam hran
	 */
	public void printEdges(ArrayList<Edge> edges) {
		for(int i = 0; i< edges.size();i++) {
			System.out.print(edges.get(i).toString());
		}
		System.out.println();
	}


    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                             DFS                                                              |
       +--------------------------------------------------------------------------------------------------------------+
    */

    public static void DFS(Router start, Router end, ArrayList<Router> visited) {
        int current;
        int size;
        visited.add(start);

        if (start.equals(end)) {
            for (int i = 0; i < visited.size(); i++) {
                System.out.print(visited.get(i).toString() + " ");
            }
            System.out.println();
            visited.remove(start);
            return;
        }

        current = 0;
        size = start.getNext().size();
        while (current != size) {
            if (!(visited.contains(start.getNext().get(current).getNextRouter()))) {
                DFS(start.getNext().get(current).getNextRouter(), end, visited);
            }
            current++;
        }
        for (int i = 0; i < visited.size(); i++) {
            System.out.print(visited.get(i).toString() + " ");
        }
        System.out.println();
        visited.remove(start);
    }

	/*
       +--------------------------------------------------------------------------------------------------------------+
       |                                          KONEC DFS                                                           |
       +--------------------------------------------------------------------------------------------------------------+
    */


    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                          DIJKSTRA                                                            |
       +--------------------------------------------------------------------------------------------------------------+
    */

    /**
     * Vypocte nejkratsi cestu Dijkstra algoritmem a vrati graf s cestami
     * @param source router, od ktereho hledame cesty
     * @return graf s cestami
     */
    public void calculateShortestPathFromSource(Router source) {
        source.setDistance(0);

        Set<Router> settledNodes = new HashSet<>();
        Set<Router> unsettledNodes = new HashSet<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Router currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Router, Integer> adjacencyPair:
                    currentNode.getAdjacentNodes().entrySet()) {
                Router adjacentNode = adjacencyPair.getKey();
                Integer edgeWeight = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
    }

    /**
     * Vypocte nejkratsi vzdalenost mezi pocatecnim a koncovym routerem
     * @param evaluationNode pocatecni router
     * @param edgeWeigh vaha hrany
     * @param sourceNode koncovy router
     */
    private static void calculateMinimumDistance(Router evaluationNode,
                                                 Integer edgeWeigh, Router sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Router> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

    /**
     * Ziska router, ktery je pripojen hranou s nejmensim ohodnocenim
     * @param unsettledNodes Set s routery
     * @return router pripojen nejmensi hranou
     */
    private static Router getLowestDistanceNode(Set < Router > unsettledNodes) {
        Router lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Router node: unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * Vytiskne nejkratsi cesty v grafu
     * @param graph graf, kde jsou cesty
     * @param source router, od ktereho jsou nalezene cesty
     */
    public void printShortestPathFromSource(Graph graph, Router source) {
//        System.out.println("Size of graph nodes: " + graph.getNodes().size());
        for (Router router : graph.getNodes()) {
            int totalDist = router.getDistance();
            System.out.println("Minimum size of path from router " + source.getId() + " and router " + router.getId() + " is: " + totalDist);
            System.out.println("Path:");

            if(totalDist != 0){
                for (int i = 0; i < router.getShortestPath().size(); i++) {
                    System.out.print(router.getShortestPath().get(i).toString() + " --> ");
                }
                System.out.println(router.toString() + "\n");
            } else System.out.println("--- No path ---\n");
        }
    }

    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         KONEC DIJKSTRY                                                       |
       +--------------------------------------------------------------------------------------------------------------+
    */




    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         GETTERY A SETTERY                                                    |
       +--------------------------------------------------------------------------------------------------------------+
    */

	/**
	 * Vraci pocet hran v seznamu
	 * @return seznam hran
	 */
	public int getCountEdges(ArrayList<Edge> edges) {
		return edges.size();
	}
	
	/**
	 * Vraci seznam hran
	 * @return seznam hran
	 */
	public List<Edge> getEdges() {
		return edges;
	}

    /**
     * Vraci hash tabulku s routery
     * @return hash tabulka s routery
     */
    public Hashtable<Integer, Router> getRouters() {
        return routers;
    }

    /**
	 * Pridani routeru do grafu
	 * @param nodeA
	 */
	public void addNode(Router nodeA) {
        nodes.add(nodeA);
    }
	
	/**
     * Vraci SET s routery v grafu
	 * @return SET s routery v grafu
	 */
	public Set<Router> getNodes() {
		return nodes;
	}


	/*
       +--------------------------------------------------------------------------------------------------------------+
       |                                              TOSTRING                                                        |
       +--------------------------------------------------------------------------------------------------------------+
    */

//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		for (int i = 1; i <= routers.size(); i++)
//		{
//			builder.append("Router c."+i + " => ");
//			for (int j = 0; j < routers.get(i).getNext().size(); j++)
//			{
//				if(j != 0) builder.append(" || ");
//				builder.append(routers.get(i).getNext().get(j).toString());
//			}
//			builder.append("\n");
//		}
//		return builder.toString();
//	}
}