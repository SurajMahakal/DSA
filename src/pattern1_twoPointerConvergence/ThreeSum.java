package pattern1_twoPointerConvergence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>Pattern 01: Two-Pointer Convergence — Variation 4: Multi-Pointer Anchor Reduction</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * Higher-order combinatorial problems (like 3Sum or 4Sum) normally require nested loops that take O(N^3) time. 
 * This variation cuts an entire order of complexity out of the equation by using an anchoring technique. 
 * By freezing a single element as a static anchor point via an outer loop, the problem for the remaining 
 * elements is reduced to an isolated 1D target lookup (Two Sum II), which can be solved cleanly using 
 * convergent pointers in O(N) time.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The target requires finding distinct groups of three or four elements that sum to a precise value (such as 0).</li>
 * <li>The problem requires that the output collection contain completely unique combinations, with zero duplicate groups.</li>
 * <li>The baseline array is unsorted initially, but sorting it does not break the requirements because we only need to return the data values rather than their original indices.</li>
 * </ul>
 * </p>
 * * <p><strong>Mathematical & System Mechanics:</strong><br>
 * The equation <code>A[i] + A[left] + A[right] = 0</code> can be rewritten as: <code>A[left] + A[right] = -A[i]</code>. 
 * Sorting the input array first takes O(N log N) time, which is heavily overshadowed by the main O(N^2) processing engine. 
 * To prevent duplicate groups without using a slow, memory-heavy <code>HashSet</code>, the code uses **In-Place Deduplication**: 
 * a series of <code>while</code> loops advance the pointers past identical values immediately after a match is recorded.</p>
 * * <p><strong>Core Java & Hardware Guardrails:</strong>
 * <ul>
 * <li><strong>Collections Boxing Trap:</strong> Packing primitive integers into <code>List&lt;Integer&gt;</code> results in auto-boxing overhead. Ensure that all internal calculations and pointer evaluations use raw primitive variables, only generating object lists when adding a valid result to your output list.</li>
 * <li><strong>Sorting Overhead:</strong> <code>Arrays.sort()</code> on primitives uses Dual-Pivot QuickSort, which takes O(N log N) time and O(log N) stack memory. If sorting an array of objects, Java uses TimSort, which requires O(N) auxiliary space on the heap.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 15 / NeetCode 150:</strong> 3Sum (Medium) - <em>The classic triplet isolation challenge using duplicate pruning.</em></li>
 * <li><strong>LeetCode 16:</strong> 3Sum Closest (Medium) - <em>Requires minimizing the absolute delta difference between the sum and a target.</em></li>
 * <li><strong>LeetCode 18:</strong> 4Sum (Medium) - <em>Uses two nested loops to anchor two values, reducing the remaining search space to a Two-Pointer loop.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Find Triplets with Zero Sum (Easy/Medium) - <em>A standard structure check frequently used in core engineering rounds.</em></li>
 * </ul>
 * </p>
 */

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;
        
        Arrays.sort(nums); // Prerequisite for convergent pointer mechanics
        
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // Skip identical anchors
            
            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            
            while (left < right) {
                int currentSum = nums[left] + nums[right];
                
                if (currentSum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    while (left < right && nums[left] == nums[left + 1]) left++; // Prune duplicates
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    
                    left++;
                    right--;
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum engine = new ThreeSum();
        int[] testInput = {-1, 0, 1, 2, -1, -4};
        
        List<List<Integer>> result = engine.threeSum(testInput);
        System.out.println("Variation 4 (3Sum) Result: " + result);
    }
}