package Random;

/*  384. Shuffle an Array
    Shuffle a set of numbers without duplicates.

    Example:
    // Init an array with set 1, 2, and 3.
    int[] nums = {1,2,3};
    Solution solution = new Solution(nums);

    // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
    solution.shuffle();

    // Resets the array back to its original configuration [1,2,3].
    solution.reset();

    // Returns the random shuffling of array [1,2,3].
    solution.shuffle();
 */

import java.util.Random;

/*  Fisher-Yates Algorithm: Time = O(n) Space = O(n)
    swapping elements around within the array itself
    In each iteration of the algorithm, we generate a random integer between the current index and the last index.
    Then, we swap the elements at the current index and the chosen index - this simulates drawing (and removing)
    the element from the hat, as the next range from which we select a random index will not include the most
    recently processed one.
 */
public class ShuffleanArray {
    private int[] array;
    private int[] original;
    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public ShuffleanArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }
}

/*  Brute Force: Time = O(n^2) Space = O(n)

    private int[] array;
    private int[] original;

    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public ShuffleanArray(int[] nums) {
        array = nums;                   // array = {1, 2, 3}
        original = nums.clone();        // original = {1, 2, 3}
    }

    public int[] reset() {
        array = original;               // array = {1, 2, 3}
        original = original.clone();    // deep copy to make sure changes in array will not change original
        return array;
    }

    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();     // aux = {1, 2, 3}

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());   // removeIdx = 0-2
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }
        return array;
    }
 */
