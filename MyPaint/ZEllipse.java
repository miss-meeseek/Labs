import static java.lang.Math.*;
import java.awt.* ;
import java.awt.geom.*;
import java.awt.event.* ;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;

/** Klasa przechowywujaca dane okrogow.
* 
* @author Volha Hutouskaya
*/
public class ZEllipse extends Ellipse2D.Float {
	private boolean active = false;
	private Color color = Color.BLACK;
	
	public ZEllipse(float x, float y, float width, float height) {
		setFrame(x, y, width, height);
	}
	public void changeActive() {
		active = !active;
	}
	public boolean isActive() {
		return active;
	}
	public Color getColor() {
		return color;
	}
	public void changeColor (Color c) {
		color = c;
	}
	public boolean isHit(int x, int y) {   
		return getBounds2D().contains(x, y);
	}
	public void addX(float x) {            
		this.x += x;
	}
	public void addY(float y) {
		this.y += y;
	}
	public void addWidth(float w) {
		this.width += w;
	}
	public void addHeight(float h) {
		this.height += h;
	}
}