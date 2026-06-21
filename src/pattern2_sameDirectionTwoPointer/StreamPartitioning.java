package pattern2_sameDirectionTwoPointer;

import java.util.Arrays;

/**
 * <h1>Pattern 02: Same-Direction Two-Pointer — Variation 2: Stream Partitioning</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * Unlike sorted deduplication, the input array here is completely unsorted. The objective is to split the 
 * data stream into two distinct categories based on a conditional filter rule. The Writer pointer (<code>left</code>) 
 * anchors the boundary of the valid category, while the Scanner pointer (<code>right</code>) searches forward. 
 * Every time the scanner finds an element that passes the filter rule, it copies it over to the writer's position, 
 * pushing all filtered elements to the back of the array.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The input sequence is unsorted, and you cannot sort it because you must preserve the original relative order of elements.</li>
 * <li>You need to filter out or isolate a target value, or group numbers based on a condition (e.g., separating odd/even or zero/non-zero values).</li>
 * <li>The operation must run with O(1) auxiliary space, preventing the use of temporary target arrays.</li>
 * </ul>
 * </p>
 * * <p><strong>Core Java & Systems Cross-Questions:</strong>
 * <ul>
 * <li><strong>The ArrayList Trap:</strong> If this problem used an <code>ArrayList</code> and you called <code>list.remove(i)</code> inside the loop, the JVM would execute an underlying <code>System.arraycopy()</code> to shift all remaining elements one slot leftward. This drops your time complexity from an efficient O(N) down to an inefficient O(N^2). Working on raw primitive arrays avoids this penalty.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Unique Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 283 / NeetCode 150:</strong> Move Zeroes (Easy) — <em>Shifts all zeroes to the back of the array while preserving the relative order of all non-zero elements.</em></li>
 * <li><strong>LeetCode 27:</strong> Remove Element (Easy) — <em>Filters out a specific target value in-place, returning the count of the remaining elements.</em></li>
 * <li><strong>LeetCode 905:</strong> Sort Array By Parity (Easy) — <em>Partitions the array so that all even integers appear at the front, followed by all odd integers.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Segregate Even and Odd Numbers (Easy) — <em>Filters and groupings based on parity conditions within an unsorted primitive array.</em></li>
 * </ul>
 * </p>
 */
public class StreamPartitioning {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        
        // The Writer pointer tracks where the next non-zero element belongs
        int left = 0; 
        
        // The Scanner pointer explores every single element across the array sequence
        for (int right = 0; right < nums.length; right++) {
            // Filter Condition: If the scanner finds a non-zero element...
            if (nums[right] != 0) {
                // If pointers are at different positions, swap the elements to preserve non-zero values
                if (left != right) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                }
                left++; // Advance the writer boundary
            }
        }
    }

    public static void main(String[] args) {
        StreamPartitioning engine = new StreamPartitioning();
        int[] testInput = {0, 1, 0, 3, 12};
        
        engine.moveZeroes(testInput);
        System.out.println("Variation 2 (Move Zeroes) Result: " + Arrays.toString(testInput));
    }
}