package pt_sp;

import java.util.*;

/**
 * Trida Main predstavujici hlavni (spousteci) tridu programu
 */
public class Main {
	
	/* Hashovaci tabulka s routery */
	private static Hashtable<Integer, Router> routers;

	private static ArrayList<Edge> edges;
	
	/**
	 * Spousteci metoda
	 * @param args argumenty prikazoveho radku (nevyuzity)
	 */
	public static void main(String[] args) {
		String s = "vstup3.txt";
		takeInformation(s);
//		Graph g = makeGraph();


        Graph g = new Graph(routers, edges);
//        g.toString();

        /*
            +--------------------------------------------------------------------------------------------------------------+
            |                                  TUTO CAST NEMAZAT, ALE IGNOROVAT                                            |
            +--------------------------------------------------------------------------------------------------------------+
        */
//		Router nodeA = new Router(1);
//		Router nodeB = new Router(2);
//		Router nodeC = new Router(3);
//		Router nodeD = new Router(4);
//		Router nodeE = new Router(5);
//		Router nodeF = new Router(6);
//
//		nodeA.addDestination(nodeB, 10);
//		nodeA.addDestination(nodeC, 15);
//
//		nodeB.addDestination(nodeD, 12);
//		nodeB.addDestination(nodeF, 15);
//
//		nodeC.addDestination(nodeE, 10);
//
//		nodeD.addDestination(nodeE, 2);
//		nodeD.addDestination(nodeF, 1);
//
//		nodeF.addDestination(nodeE, 5);
//
//		Graph graph = new Graph();
//
//		graph.addNode(nodeA);
//		graph.addNode(nodeB);
//		graph.addNode(nodeC);
//		graph.addNode(nodeD);
//		graph.addNode(nodeE);
//		graph.addNode(nodeF);
//
//        graph = Router.calculateShortestPathFromSource(graph, nodeA);

        /*
            +--------------------------------------------------------------------------------------------------------------+
            |                                       KONEC IGNOROVANE CASTI                                                 |
            +--------------------------------------------------------------------------------------------------------------+
        */

//        g.calculateShortestPathFromSource(g.getRouters().get(1));
//        g.printShortestPathFromSource(g, g.getRouters().get(1));

        /*
            +--------------------------------------------------------------------------------------------------------------+
            |                                           Testovani DFS                                                      |
            +--------------------------------------------------------------------------------------------------------------+
        */

        ArrayList<Router> visited = new ArrayList<>();

        Router A = new Router(1);
        Router B = new Router(2);
        Router C = new Router(3);
        Router D = new Router(4);
        Router E = new Router(5);

        A.getNext().add(new Edge(A.getId(), B, 1));
        A.getNext().add(new Edge(A.getId(), C, 1));
        B.getNext().add(new Edge(B.getId(), A, 1));
        B.getNext().add(new Edge(B.getId(), D, 1));
        C.getNext().add(new Edge(C.getId(), A, 1));
        C.getNext().add(new Edge(C.getId(), D, 1));
        C.getNext().add(new Edge(C.getId(), E, 1));
        D.getNext().add(new Edge(D.getId(), B, 1));
        D.getNext().add(new Edge(D.getId(), C, 1));
        D.getNext().add(new Edge(D.getId(), E, 1));
        E.getNext().add(new Edge(E.getId(), C, 1));
        E.getNext().add(new Edge(E.getId(), D, 1));

        System.out.println("Graf:");
        System.out.println("  A");
        System.out.println("  /\\");
        System.out.println(" C  B");
        System.out.println(" |\\ |");
        System.out.println(" | \\|");
        System.out.println(" E--D");

        System.out.println();

        g.DFS(A, E, visited);

        /*
            +--------------------------------------------------------------------------------------------------------------+
            |                                        Konec testovani DFS                                                   |
            +--------------------------------------------------------------------------------------------------------------+
        */

	}

	/**
	 * Zpracuje si informace ze souboru (vytvori seznam hran a tabulku routeru)
	 * @param s nazev vstupniho souboru
	 */
	private static void takeInformation(String s) {
		Read r = new Read(s);
		routers = r.getRouters();
		edges = r.getEdges();
		r=null;
	}



	/*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         GETTERY A SETTERY                                                    |
       +--------------------------------------------------------------------------------------------------------------+
    */

	/**
	 * Vraci hashovaci tabulku s routery
	 * @return hashovaci tabulka s routery
	 */
	public static Hashtable<Integer, Router> getRouters() {
		return routers;
	}
}