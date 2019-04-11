import static java.lang.Math.*;
import java.awt.* ;
import java.awt.geom.*;
import java.awt.event.* ;
import javax.swing.*;
import java.applet.*;
import java.util.*;
import java.io.*;

/** Klasa przechowywujaca dane wielokata.
* 
* @author Volha Hutouskaya
*/
public class Poly extends Polygon implements Shape, Cloneable, Serializable {
	private boolean active = false;
	private Color color = Color.BLACK;
	
	public Poly(int[] x, int[] y, int n) {
		xpoints = new int [n];
		ypoints = new int [n];
		for(int i = 0; i < n; i++) {
			this.xpoints[i] = x[i];
			this.ypoints[i] = y[i];
		}
		this.npoints = n;
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
	public void changeColor(Color c) {
		color = c;
	}
	public boolean isHit(int x, int y) {   
		return getBounds2D().contains(x, y);
	}
	public void addX(int x) {
		for(int i = 0; i < npoints; i++) {
			this.xpoints[i] += x;
		}           
	}
	public void addY(int y) {
		for(int i = 0; i < npoints; i++) {
			this.ypoints[i] += y;
		}    
	}
	public void scaling(float w) {
		for(int i = 0; i < npoints; i++) {
			this.xpoints[i] *= w;
			this.ypoints[i] *= w;
		}
	}
	public int findCenterX() {
		int center = 0;
		for(int i = 0; i < npoints; i++)
			center += xpoints[i];
		return center/npoints;
	}
	public int findCenterY() {
		int center = 0;
		for (int i = 0; i < npoints; i++)
			center += ypoints[i];
		return center/npoints;
	}
}