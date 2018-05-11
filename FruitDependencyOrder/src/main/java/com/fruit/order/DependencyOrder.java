package com.fruit.order;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fruit.model.FruitNode;
import com.fruit.model.Graph;

/**
 * The class is used to return in order an array of {@link FruitNode} by least
 * number of children nodes. The algorithm is a modification of topological
 * sort.
 * 
 * @author User
 *
 */
public class DependencyOrder {

	public FruitNode[] findDependencyOrder(Map<String, List<String>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		// Create the graph.
		Graph graph = createGraph(map);
		FruitNode[] order = orderDependencies(graph);
		return order;
	}

	/**
	 * Method to order the {@link FruitNode}s.
	 * 
	 * @param graph
	 *            input {@link Graph}
	 * @return an ordered array of {@link FruitNode}
	 */
	private FruitNode[] orderDependencies(Graph graph) {
		FruitNode[] order = new FruitNode[graph.getNodes().size()];
		int index = addNonDependent(graph.getNodes(), order, 0);

		int toBeProcessed = 0;

		while (toBeProcessed < order.length) {
			FruitNode current = order[toBeProcessed];
			/*
			 * We have a circular dependency since there are no remaining fruits with zero
			 * dependencies.
			 */
			if (current == null) {
				return null;
			}
			List<FruitNode> parentFruits = current.getParents();

			for (FruitNode parentFruit : parentFruits) {
				parentFruit.decrementDependencies(1);
			}

			index = addNonDependent(parentFruits, order, index);
			toBeProcessed++;
		}

		return order;
	}

	private int addNonDependent(List<FruitNode> fruits, FruitNode[] order, int offset) {
		for (FruitNode fruit : fruits) {
			if (fruit.getOutgoingDependencies() == 0) {
				order[offset] = fruit;
				offset++;
			}
		}
		return offset;
	}

	/**
	 * Method is used to initialize the graph using the input dependency structure
	 * given using a {@link Map}.
	 * 
	 * @param map
	 *            dependency structure
	 * @return {@link Graph} created from input dependency structure;
	 * 
	 * @throws {@link
	 *             IllegalArgumentException} if the dependency structure is
	 *             incomplete.
	 */
	private Graph createGraph(Map<String, List<String>> map) {
		Graph graph = new Graph();
		Set<String> visited = new HashSet<String>();
		for (String fruit : map.keySet()) {
			if (visited.contains(fruit)) {
				continue;
			}
			visited.add(fruit);
			updateGraph(graph, fruit, map, visited, null);
		}
		return graph;
	}

	private void updateGraph(Graph graph, String fruit, Map<String, List<String>> map, Set<String> visited,
			FruitNode parent) {
		visited.add(fruit);
		if (!map.containsKey(fruit)) {
			throw new IllegalArgumentException("Incomplete data given to process graph");
		}
		FruitNode fruitNode = graph.getOrCreateNode(fruit, map.get(fruit).size());
		if (parent != null) {
			fruitNode.addParent(parent);
		}
		for (String childFruits : map.get(fruit)) {
			updateGraph(graph, childFruits, map, visited, fruitNode);
		}
	}

}
