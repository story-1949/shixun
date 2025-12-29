interface Shape {
	void draw();
}

interface Color {
	void fill();
}
class Circle implements Shape{
	@Override
	public void draw() {
		System.out.println("画圆");
	}
}
class Square implements Shape{
	@Override
	public void draw() {
		System.out.println("画方");
	}
}
class Red implements Color {
	@Override
	public void fill() {
		System.out.println("填红色");
	}
}

abstract class AbstractFactory {
	public abstract  Shape getShape(String type);
	public abstract  Color getColor(String type) ;
}

class ShapeFactory extends AbstractFactory{
	@Override
	public  Shape getShape(String type) {
		if (type.equals("circle")) {
			return new Circle();
		} else if (type.equals("square")) {
			return new Square();
		}
		return null;
	}
	@Override
	public  Color getColor(String type)  {
		return null;
	}
}

class ColorFactory extends AbstractFactory{
	@Override
	public  Shape getShape(String type) {
		return null;
	}
	@Override
	public Color getColor(String type) {
		return new Red();
	}
}

class FactoryProducer {
	public static AbstractFactory getFactory(String type) {
		if (type.equals("shape"))
			return new ShapeFactory();
		else
			return new ColorFactory();
	}
}

public class App {
	public static void main(String[] args) {
		AbstractFactory sf = FactoryProducer.getFactory("shape");
		Shape s1 = sf.getShape("circle");
		s1.draw();
		
	}
}