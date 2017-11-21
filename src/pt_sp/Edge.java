package pt_sp;

/**
 * Trida Edge predstavujici hrany v grafu
 */
public class Edge {
	/* Druhy router v hrane */
	private Router nextRouter;
	
	/* Velikost ohodnoceni hrany */
	private int size;

	/* ID hrany (= ID routeru, do ktereho vede) */
	private int id;
	
	/**
	 * Konstruktor hrany
	 * @param nextRouter druhy router hrany
	 * @param size velikost ohodnoceni hrany
	 */
	public Edge(int id, Router nextRouter, int size) {
		this.id = id;
		this.nextRouter = nextRouter;
		this.size = size;
	}


	/*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         GETTERY A SETTERY                                                    |
       +--------------------------------------------------------------------------------------------------------------+
    */

	/**
	 * Vraci ID hrany
	 * @return ID hrany
	 */
	public int getID() {
		return id;
	}

    /**
     * Vraci velikost hrany
     * @return velikost hrany
     */
	public int getSize() {
	    return this.size;
    }

    /**
     * Vraci router, co je na hranu pripojeny
     * @return router na hranu pripojeny
     */
	public Router getNextRouter() {
		return nextRouter;
	}
}
