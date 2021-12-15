package com.matthieu.aoc.resolver.year_2021;

import java.util.List;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.exception.SolveException;
import com.matthieu.aoc.model.graph.DijkstraSolver;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Matrix;

public class Resolver15p2 extends Resolver15p1 {

	private Matrix<Node> nodes;
	
	@Override
	public void prepareData(List<String> values) throws PrepareDataException {
		Matrix<Long> r = new Matrix<>(values, Long::parseLong);
		
		this.risks = new Matrix<>(r.getXSize() * 5, r.getYSize() * 5, () -> 0l);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				for (int y = 0; y < r.getYSize(); y++) {
					for (int x = 0; x < r.getXSize(); x++) {
						long newRisk = r.get(x, y) + i + j;
						
						newRisk = newRisk % 10 + (newRisk / 10);
						
						risks.set(x + j * r.getXSize(), y + i * r.getYSize(), newRisk);

					}
				}
			}
		}
		
		this.nodes = new Matrix<>(this.risks.getXSize(), this.risks.getYSize(), () -> new Node());
		
		nodes.forEach(this::linkNode);
	}
	
	@Override
	public boolean solve() throws SolveException {
		
		DijkstraSolver.calculateShortestPathFromSource(this.nodes.get(0, 0));
		
		this.minRisk = this.nodes.get(this.nodes.getMaxX(), this.nodes.getMaxY()).getDistance();
		
		return true;
	}
	
	private void linkNode(int x, int y, Node node) {
		
		nodes.forEachNeigthboursCross(x, y, (nx, ny, neightbourNode) -> node.addDestination(neightbourNode, this.risks.get(nx, ny).intValue()));
		
	}
}
