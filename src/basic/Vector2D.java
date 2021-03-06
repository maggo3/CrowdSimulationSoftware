package basic;

public class Vector2D {

	public double x;
	public double y;
	
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector2D v) {
		x += v.x;
		y += v.y;
	}
	
	public void sub(Vector2D v) {
		x -= v.x;
		y -= v.y;
	}
	
	static public Vector2D substract(Vector2D target, Vector2D start) {
		return new Vector2D(target.x - start.x, target.y - start.y);
	}

	public double distance() {
		return (double) Math.sqrt(x*x + y*y);
	}

	public void normalize() {
		double d = distance();
		//Betrag?
		if ( d != 0 && d != 1) {
			div(d);
		}
	}

	public void div(double d) {
		x /= d;
		y /= d;
	}

	@Override
	public String toString() {
		return "Vector2D [x=" + x + ", y=" + y + "]";
	}

	public void multiply(double m) {
		x *= m;
		y *= m;
	}

	public void limit(double maxLength) {
		if ( distance() > maxLength) {
			normalize();
			multiply(maxLength);
		}
	}

	public void set(double x2, double y2) {
		this.x = x2;
		this.y = y2;
	}

	public double heading2D() {
		return Math.atan2(y, x);
	}

	public static Vector2D multScalar(Vector2D v, double w) {
		double x = v.x * w;
		double y = v.y * w;
		return new Vector2D(x,y);
	}
	
}
