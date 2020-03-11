package String;

/*  Strobogrammatic Number 3
    A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
    Write a function to count the total strobogrammatic numbers that exist in the range of low <= num.

    For example,
    Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

    Note: Because the range might be a large number, the low and high numbers are represented as string.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrobogrammaticNumber3 {
    public int strobogrammaticInRange(String low, String high){
        int res = 0;
        List<String> list = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++) {
            list.addAll(helper(i, i));
        }
        for (String str : list) {
            if ((str.length() == low.length() && str.compareTo(low) < 0) || (str.length() == high.length() && str.compareTo(high) > 0)) {
                continue;
            }
            res++;
        }
        return res;
    }
    private List<String> helper(int cur, int max) {
        if (cur == 0) return new ArrayList<>(Arrays.asList(""));
        if (cur == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> res = new ArrayList<>();
        List<String> center = helper(cur - 2, max);

        for (int i = 0; i < center.size(); i++) {
            String tmp = center.get(i);
            if (cur != max) {
                res.add("0" + tmp + "0");
            }
            res.add("1" + tmp + "1");
            res.add("6" + tmp + "9");
            res.add("8" + tmp + "8");
            res.add("9" + tmp + "6");
        }
        return res;
    }
}
