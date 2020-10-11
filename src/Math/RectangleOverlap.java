package Math;

/*  836. Rectangle Overlap
    An axis-aligned rectangle is represented as a list [x1, y1, x2, y2],
    where (x1, y1) is the coordinate of its bottom-left corner, and (x2, y2) is the coordinate of its top-right corner.
    Its top and bottom edges are parallel to the X-axis, and its left and right edges are parallel to the Y-axis.
    Two rectangles overlap if the area of their intersection is positive.
    To be clear, two rectangles that only touch at the corner or edges do not overlap.
    Given two axis-aligned rectangles rec1 and rec2, return true if they overlap, otherwise return false.

    Example 1:
    Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
    Output: true

    Example 2:
    Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
    Output: false

    Example 3:
    Input: rec1 = [0,0,1,1], rec2 = [2,2,3,3]
    Output: false

    Constraints:
        o rect1.length == 4
        o rect2.length == 4
        o -10^9 <= rec1[i], rec2[i] <= 10^9
        o rec1[0] <= rec1[2] and rec1[1] <= rec1[3]
        o rec2[0] <= rec2[2] and rec2[1] <= rec2[3]
 */

/*  Check Position

 */
public class RectangleOverlap {
    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] ||           // left
                 rec1[3] <= rec2[1] ||           // bottom
                 rec1[0] >= rec2[2] ||           // right
                 rec1[1] >= rec2[3]);            // top
     }
}

/*  Check Area

        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0]) &&      // width > 0
                Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));       // height > 0;
 */
