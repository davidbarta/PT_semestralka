package pt_sp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

/**
 * Trida Read nacitajici routery a hrany ze souboru
 */
public class Read 
{
	/* Cesta k souboru s daty */
	String path;
	
	/* Hashovaci tabulka s routery - klicem je ID routeru,
	 * hodnotou je samotny router */
	private static Hashtable<Integer, Router> routers;
	
	/* Seznam hran v grafu */
	private static ArrayList<Edge> edges;
	
	/**
	 * Konstruktor tridy read
	 * @param path cesta k souboru s daty
	 */
	public Read(String path) {
		this.path = path;
		routers = new Hashtable<>();
		edges = new ArrayList<>();
		readData(path);
	}

	/**
	 * Precte soubor a vrati seznam Stringu, kde kazdy String reprezentuje radek v souboru
	 * @param fileName nazev souboru
	 * @return data seznam Stringu
	 */
	private static void readData(String fileName) {
		List<String> data = new ArrayList<String>();
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
			
			while(sc.hasNext()) {
				String s = sc.nextLine();
				data.add(s);
			}
			sc.close();
		} catch(FileNotFoundException e) {
			System.out.println("Error while reading file.");
			e.printStackTrace();
			System.exit(0);
		}
		fillData(data);
	}
	
	/**
	 * Naplni hash tabulku "routers" a list "edges" daty
	 * @param data data k naplneni
	 */
	private static void fillData(List<String> data) {
		List<Integer> ids = new ArrayList<>();			//pole s ID routeru
		for (String s : data) {
			String[] values = s.split("-");
			int v1 = Integer.parseInt(values[0]);
			int v2 = Integer.parseInt(values[1]);
            int edgeSize = Integer.parseInt(values[2]);

            Router router1 = new Router(v1);
            Router router2 = new Router(v2);

            Edge edge1 = new Edge(router1.getId(), router2, edgeSize);
            Edge edge2 = new Edge(router2.getId(), router1, edgeSize);

            edges.add(edge1);
            edges.add(edge2);

			if(!ids.contains(v1)){
				ids.add(v1);
				routers.put(router1.getId(), router1);

			}
			
			if(!ids.contains(v2)){
				ids.add(v2);
				routers.put(router2.getId(), router2);

			}
		}
    }


    /*
       +--------------------------------------------------------------------------------------------------------------+
       |                                         GETTERY A SETTERY                                                    |
       +--------------------------------------------------------------------------------------------------------------+
    */

	/**
	 * Vraci hash tabulku s routery
	 * @return hash tabulka s routery
	 */
	public static Hashtable<Integer, Router> getRouters() {
		return routers;
	}

    /**
     * Vraci seznam s hranami
     * @return seznam s hranami
     */
    public static ArrayList<Edge> getEdges() {
        return edges;
    }
}

