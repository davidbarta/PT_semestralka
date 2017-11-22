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
//		printhGraph(g);

        Graph g = new Graph(routers, edges);

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

        g = g.calculateShortestPathFromSource(g, g.getRouters().get(1));
        g.printShortestPathFromSource(g, g.getRouters().get(1));
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