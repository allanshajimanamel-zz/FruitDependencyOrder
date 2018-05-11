package com.fruit.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class represents a graph of {@link FruitNode}
 * @author User
 *
 */
public class Graph {
	private ArrayList<FruitNode> nodes = new ArrayList<FruitNode>();
	private HashMap<String, FruitNode> map = new HashMap<String, FruitNode>();

	public FruitNode getOrCreateNode(String name, int outgoingDependencies) {
		if (!map.containsKey(name)) {
			FruitNode node = new FruitNode(name, outgoingDependencies);
			nodes.add(node);
			map.put(name, node);
		}

		return map.get(name);
	}

	public ArrayList<FruitNode> getNodes() {
		return nodes;
	}
}
