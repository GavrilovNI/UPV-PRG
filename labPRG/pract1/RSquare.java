package pract1; 
 
// Imports class Graph2D (from package graph2D).
import graph2D.Graph2D;
// Imports class Color from package java.awt for chaning
// the colours of the elements to be drawn.
import java.awt.Color;
/**
 * Drawing figures of type "Recursive Square".
 * From "Introduction to Programming in Java",
 * Sedgewick & Wayne, Princeton.
 * @author IIP-PRG-DSIC-ETSINF. pmarques
 * @version Academic year 2025-26
 */
public class RSquare {    
    /** There are no objects of this class. */
    private RSquare() { }
    
    /** Draws in the window gd a grey filled square with
     *  the ledge highlighted in blue, centred at ( cX, cY )
     *  and side length equal to s.
     */
    public static void drawCentSquare(Graph2D gd, double cX, double cY, double s) {
        delay();                                           
        // Computes the coordinates of the upper left corner (luX, luY)
        // of the square from its centre (cX, cY) and its side length s:
        double luX = cX - s/2; // TO COMPLETE 
        double luY = cY + s/2; // TO COMPLETE        
        // Draws a grey coloured solid rectangle, with the same vertical
        // and horizontal side length equal to s.
        // The upper left corner of the rectangle is at (luX, luY):
        gd.fillRect(luX, luY, s, s, Color.LIGHT_GRAY, 2);
        // Draws a non-filled rectangle with blue border, 
        // with the same vertical and horizontal side length equal to s.
        // The upper left corner of the rectangle is at (luX, luY):
        gd.drawRect(luX, luY, s, s, Color.BLUE, 2);
    }
    
    /** Waits 100 ms to continue the execution of the current thread. */
    private static void delay() {
        try { Thread.sleep(10); } catch (Exception e) { ; }
    }
    
    /** Draws in the window gd an RSquare-A figure
     *  of order n >= 1, with the central square centered at
     *  (cX, cY) and side length equal to s.
     */
    public static void rSquareA(Graph2D gd, int n, double cX, double cY, double s) {
        if(n <= 0)
            return;
        
        var halfS = s/2;
        rSquareA(gd, n-1, cX-halfS, cY-halfS, halfS);
        rSquareA(gd, n-1, cX-halfS, cY+halfS, halfS);
        rSquareA(gd, n-1, cX+halfS, cY-halfS, halfS);
        rSquareA(gd, n-1, cX+halfS, cY+halfS, halfS);
        drawCentSquare(gd, cX, cY, s);
    } 
    
    /** Draws an RSquare-A figure of order n >= 1
     * with side length equal to 1.0 and centered at (0, 0).
     */
    public static void rSquareA(int n) {
        
        Graph2D gd = new Graph2D(-1, 1, -1, 1);
        gd.setTitle("rSquareA. Order: " + n);
        rSquareA(gd, n, 0, 0, 1);
    }
    
    /** Draws in the window gd an RSquare-B figure
     *  of order n >= 1, with the central square centered at
     *  (cX, cY) and side length equal to $s$.
     */
    public static void rSquareB(Graph2D gd, int n, double cX, double cY, double s) {
        if(n <= 0)
            return;
        
        drawCentSquare(gd, cX, cY, s);
        var halfS = s/2;
        rSquareA(gd, n-1, cX-halfS, cY-halfS, halfS);
        rSquareA(gd, n-1, cX-halfS, cY+halfS, halfS);
        rSquareA(gd, n-1, cX+halfS, cY-halfS, halfS);
        rSquareA(gd, n-1, cX+halfS, cY+halfS, halfS);
    }     
    
    /** Draws an RSquare-B figure of order n >= 1
     * with side length equal to 1.0 and centered at (0, 0).
     */
    public static void rSquareB(int n) {
        
        Graph2D gd = new Graph2D(-1, 1, -1, 1);
        gd.setTitle("rSquareB. Order: " + n);
        rSquareB(gd, n, 0, 0, 1);
    }
}