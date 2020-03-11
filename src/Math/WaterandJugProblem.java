package Math;

/*  365. Water and Jug Problem
    You are given two jugs with capacities x and y litres. There is an infinite amount of water supply available.
    You need to determine whether it is possible to measure exactly z litres using these two jugs.
    If z liters of water is measurable, you must have z litres of water contained within one or both buckets by the end.

    Operations allowed:
    Fill any of jugs completely with water.
    Empty any of the jugs.
    Pour water from one jug into another till the other jug is completely full or the first jug itself is empty.

    Example 1:
    Input: x = 3, y = 5, z = 4
    Output: True

    Example 2:
    Input: x = 2, y = 6, z = 5
    Output: False
 */
/*  Math: Time = O(n) Space = O(n)
    重复这样的步骤：
    1. 将容量多的杯子装满
    2. 装满后往另一个瓶子倒水直至另一个瓶子溢满
    3. 将溢满的瓶子倒空
    4. 将容量多的杯子里剩下的水继续倒入另一个杯子
    每次做最后一步时所剩下的水一定会满足：x*m+y*n，相当于求最大公约数：gcd(x,y)=d=mx+ny
    因此，当z=k*d -> z % gcd(x,y) == 0 且z<=x+y时答案为 true
 */
public class WaterandJugProblem {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        return z % gcd(x, y) == 0;
    }
    // 计算最大公约数（欧几里得迭代法）
    private int gcd(int a, int b) {
        if (b == 0) return a;
        // 假定第一个数较大；如果第二个数较大，在第二轮调用时会颠倒过来
        return gcd(b, a % b);
    }
}
