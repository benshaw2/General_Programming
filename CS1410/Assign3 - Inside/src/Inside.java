/**
 * Assignment 3 for CS 1410
 * This program determines if points are contained within circles or rectangles.
 */
public class Inside {
    /**
     * This is the primary driver code to test the "inside" capabilities of the
     * various functions.
     */
    public static void main(String[] args) {
        double[] ptX = { 1, 2, 3, 4 };
        double[] ptY = { 1, 2, 3, 4 };
        double[] circleX = { 0, 5 };
        double[] circleY = { 0, 5 };
        double[] circleRadius = { 3, 3 };
        double[] rectLeft = { -2.5, -2.5 };
        double[] rectTop = { 2.5, 5.0 };
        double[] rectWidth = { 6.0, 5.0 };
        double[] rectHeight = { 5.0, 2.5 };

        /* Now we will generate the report of points and circles. */

        System.out.println("--- Report of Points and Circles ---");
        System.out.println();
        for (int i=0; i < 4; i++) {
            for (int j=0; j < 2; j++) {
                String msg = "";
                if (isPointInsideCircle(ptX[i], ptY[i], circleX[j], circleY[j], circleRadius[j])) {
                    msg += " is inside ";
                }
                else {
                    msg += " is outside ";
                }
                reportPoint(ptX[i], ptY[i]);
                System.out.print(msg);
                reportCircle(circleX[j], circleY[j], circleRadius[j]);

            }

        }
        System.out.println();

        /* Now we will run a report of the points and the rectangles. */

        System.out.println("--- Report of Points and Rectangles ---");
        System.out.println();
        for (int i=0; i < 4; i++) {
            for (int j=0; j < 2; j++) {
                String msg = "";
                if (isPointInsideRectangle(ptX[i], ptY[i], rectLeft[j], rectTop[j], rectWidth[j], rectHeight[j])) {
                    msg += " is inside ";
                }
                else {
                    msg += " is outside ";
                }
                reportPoint(ptX[i], ptY[i]);
                System.out.print(msg);
                reportRectangle(rectLeft[j],rectTop[j], rectWidth[j],rectHeight[j]);

            }

        }

    }

    public static void reportPoint(double x, double y){
        System.out.print("Point(" + x + ", " + y + ") ");
    }

    public static void reportCircle(double x, double y, double r){
        System.out.println("Circle(" + x + ", " + y + ") Radius: " + r);
    }

    public static void reportRectangle(double left, double top, double width, double height){
        double right = left + width;
        double bottom = top - height;
        System.out.println("Rectangle(" + left + ", " + top + ", " + right + ", " + bottom + ")");
    }

    public static boolean isPointInsideCircle(double ptX, double ptY, double circleX, double circleY, double circleRadius){
        double xDistSq = Math.pow((circleX - ptX), 2);
        double yDistSq = Math.pow((circleY - ptY), 2);
        double distance = Math.sqrt(xDistSq + yDistSq);
        return !(distance > circleRadius);

    }

    public static boolean isPointInsideRectangle(double ptX, double ptY, double rLeft, double rTop, double rWidth, double rHeight){
        boolean X = (rLeft <= ptX) && (ptX <= rLeft + rWidth);
        boolean Y = ptY <= rTop && (rTop - rHeight) <= ptY;
        return X && Y;
    }

}
