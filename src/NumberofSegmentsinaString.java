/*
    Count the number of segments in a string,
    where a segment is defined to be a contiguous sequence of non-space characters.
    Please note that the string does not contain any non-printable characters.

    Example:
    Input: "Hello, my name is John"
    Output: 5
 */
// a string index begins a segment if it is preceded by whitespace (or is the first index) and is not whitespace itself
public class NumberofSegmentsinaString {
    public int countSegments(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }
}
/*
    String trimmed = s.trim();
    if (trimmed.equals("")) {
        return 0;
    }
    return trimmed.split("\\s+").length;
 */
