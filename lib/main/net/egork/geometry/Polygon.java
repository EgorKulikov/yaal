package net.egork.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class Polygon {
	public final Point[] vertices;

	public Polygon(Point...vertices) {
		this.vertices = vertices;
	}

	public double square() {
		double sum = 0;
		for (int i = 1; i < vertices.length; i++)
			sum += (vertices[i].x - vertices[i - 1].x) * (vertices[i].y + vertices[i - 1].y);
		sum += (vertices[0].x - vertices[vertices.length - 1].x) * (vertices[0].y + vertices[vertices.length - 1].y);
		return Math.abs(sum) / 2;
	}
}
