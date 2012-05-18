package com.uml.contradiction.model.sequence;

import java.util.LinkedList;
import java.util.List;

public class SequenceGraph {
	private static List<Interaction> interactions = new LinkedList<Interaction>();

	public static List<Interaction> getInteractions() {
		return interactions;
	}

	public static void setInteractions(List<Interaction> interactions) {
		SequenceGraph.interactions = interactions;
	}

	public static void clear() {
		interactions.clear();
	}
}
