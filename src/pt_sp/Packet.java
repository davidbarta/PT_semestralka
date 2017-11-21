package pt_sp;

import java.util.ArrayList;

/**
 * Trida predstavujici prenaseny packet
 */
public class Packet {
	
	/* ID routeru, odkud packet jde */
	int StartIndex;
	
	/* ID routeru, kam packet jde */
	int FinishIndex;
	
	/* Objem dat k preneseni (v MB) */
	int dataSize;
	
	/* ID routeru, kde se prave packet nachazi */
	ArrayList<Integer> actualID;
}