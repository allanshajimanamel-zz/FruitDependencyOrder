package com.fruit.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a Fruit in the graph with the count of outgoing
 * dependencies.
 *
 */

public class FruitNode {
	private String name;
	private int outgoingDependencies = 0;
	private List<FruitNode> parents;

	public FruitNode(String name, int outgoingDependencies) {
		super();
		this.name = name;
		this.outgoingDependencies = outgoingDependencies;
		this.parents = new ArrayList<FruitNode>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getOutgoingDependencies() {
		return outgoingDependencies;
	}

	public void setOutgoingDependencies(int outgoingDependencies) {
		this.outgoingDependencies = outgoingDependencies;
	}

	public List<FruitNode> getParents() {
		return parents;
	}

	public void setParents(List<FruitNode> parents) {
		this.parents = parents;
	}

	public void decrementDependencies(int value) {
		this.outgoingDependencies -= value;
	}

	public void addParent(FruitNode parent) {
		this.parents.add(parent);
	}

	@Override
	public String toString() {
		return "FruitNode [name=" + name + "]";
	}

}
