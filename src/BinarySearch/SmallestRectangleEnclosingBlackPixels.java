package BinarySearch;

/*  302. Smallest Rectangle Enclosing Black Pixels
    An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
    The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically.
    Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

    Example:
    Input:
    [
      "0010",
      "0110",
      "0100"
    ]
    and x = 0, y = 2

    Output: 6
 */

public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        int row = image.length;
        int col = image[0].length;

        int left = findLeft(image, 0, y);
        int right = findRight(image, y, col - 1);
        int top = findTop(image, 0, x);
        int bottom = findBottom(image, x, row - 1);

        return (right - left + 1) * (bottom - top + 1);
    }

    private int findLeft(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBlackCol(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isBlackCol(image, start)) {
            return start;
        }
        return end;
    }

    private int findRight(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBlackCol(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isBlackCol(image, end)) {
            return end;
        }
        return start;
    }

    private boolean isBlackCol(char[][] image, int col) {
        for (char[] chars : image) {
            if (chars[col] == '1') {
                return true;
            }
        }
        return false;
    }

    private int findTop(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBlackRow(image, mid)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (isBlackRow(image, start)) {
            return start;
        }
        return end;
    }

    private int findBottom(char[][] image, int start, int end) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (isBlackRow(image, mid)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (isBlackRow(image, end)) {
            return end;
        }
        return start;
    }

    private boolean isBlackRow(char[][] image, int row) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[row][j] == '1') {
                return true;
            }
        }
        return false;
    }
}
