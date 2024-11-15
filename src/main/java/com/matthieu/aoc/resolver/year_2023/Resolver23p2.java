package com.matthieu.aoc.resolver.year_2023;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.matthieu.aoc.exception.PrepareDataException;
import com.matthieu.aoc.model.graph.Node;
import com.matthieu.aoc.model.matrix.Cell;
import com.matthieu.aoc.model.matrix.Matrix;
import com.matthieu.aoc.model.tuple.Duo;

public class Resolver23p2 extends Resolver23p1 {

    private Matrix<Node> graph;

    @Override
    public void prepareData(List<String> values) throws PrepareDataException {
        super.prepareData(values);
        this.graph = new Matrix<>(this.map.getWidth(), this.map.getHeight(), () -> null);

        buildGraph();
    }
	 
	@Override
	public boolean solve() throws Exception {
        List<Node> nodes = this.graph.cellStream().map(Cell::value).filter(Objects::nonNull).toList();
	    return true;
	}
	
    @Override
    public String get() {
        return "";
    }
	

    private void buildGraph() {
        this.graph.set(1, 0, new Node("Start"));
        this.graph.set(this.graph.getMaxX() - 1, this.graph.getMaxY(), new Node("Destination"));

        browseMaze(1, 0, 1, 0, this.graph.get(1, 0), 0);
    }

    private void browseMaze(int x, int y, int fromX, int fromY, Node origin, int distanceFromOrigin) {
        List<Duo<Integer, Integer>> neightbours = Matrix.getNeigthboursCrossCoords(x, y).stream()
                .filter(coords -> coords.x() >= 0 && coords.x() < this.map.getWidth()) // Filter out of bounds
                .filter(coords -> coords.y() >= 0 && coords.y() < this.map.getHeight()) // Filter out of bounds
                .filter(coords -> !(coords.x() == fromX && coords.y() == fromY)) // Filter from
                .filter(coords -> this.map.get(coords.x(), coords.y()) != '#') // Filter walls
                .toList();

        // We are at the destination
        if (graph.get(x, y) != null && graph.get(x, y).getName().equals("Destination")) {
            linkNodes(origin, graph.get(x, y), distanceFromOrigin);

        } else if (neightbours.size() == 1) {

                browseMaze(neightbours.get(0).x(), neightbours.get(0).y(), x, y, origin, distanceFromOrigin + 1);

        } else if (neightbours.size() > 1) {
            Node junction = Optional.ofNullable(graph.get(x, y)).orElseGet(() -> new Node("Junction"));

            linkNodes(origin, junction, distanceFromOrigin);

            // First time we see this junction
            if (graph.get(x, y) == null) {
                this.graph.set(x, y, junction);

                for (Duo<Integer, Integer> neightbour : neightbours) {
                    browseMaze(neightbour.x(), neightbour.y(), x, y, junction, 1);
                }
            }
        }
    }

    private static void linkNodes(Node a, Node b, int distance) {
        a.addDestination(b, distance);
        b.addDestination(a, distance);
    }

}
