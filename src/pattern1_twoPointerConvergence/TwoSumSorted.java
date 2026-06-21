package pattern1_twoPointerConvergence;

import java.util.Arrays;

/**
 * <h1>Pattern 01: Two-Pointer Convergence — Variation 1: Sorted Target Isolation</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * When searching for a pair of elements that satisfy a linear equation or target value within a 
 * sequentially ordered dataset, we can model the options as a 2D matrix of coordinate pairs. 
 * By evaluating the sum of elements at the extreme outer boundaries (leftmost and rightmost indices), 
 * we can exploit the sorted nature of the dataset to prune an entire row or column of invalid paths 
 * in constant time, optimizing an O(N^2) search down to O(N).</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The input array or collection is explicitly sorted in ascending or descending order.</li>
 * <li>The problem requires finding two distinct elements that sum up to, or approximate, a specific target value.</li>
 * <li>The problem statement demands an optimized space complexity of O(1) auxiliary memory.</li>
 * </ul>
 * </p>
 * * <p><strong>Mathematical & System Mechanics:</strong><br>
 * Given a sorted array A, if <code>A[left] + A[right] > target</code>, then because the array is sorted, 
 * <code>A[k] + A[right]</code> will also be strictly greater than the target for any index k greater than left. 
 * Therefore, the element at the <code>right</code> pointer cannot form a valid pair with any remaining candidate 
 * inside the current tracking bounds, allowing us to safely decrement the right boundary (<code>right--</code>). 
 * Conversely, if the sum is too small, we increment the left boundary (<code>left++</code>).</p>
 * * <p><strong>Core Java & Hardware Guardrails:</strong>
 * <ul>
 * <li><strong>Thread Safety:</strong> This algorithm reads memory without executing structural mutations. It is naturally thread-safe and can run concurrently across parallel worker threads.</li>
 * <li><strong>Cache Locality:</strong> Operating directly on a primitive <code>int[]</code> maximizes CPU spatial prefetching. Consecutive items are loaded into fast L1 cache lines, avoiding the pointer-chasing overhead of wrapped object collections like <code>ArrayList&lt;Integer&gt;</code>.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 167:</strong> Two Sum II - Input Array Is Sorted (Medium) - <em>Direct application of the binary boundary step strategy.</em></li>
 * <li><strong>NeetCode 150:</strong> Two Sum II (Medium) - <em>Core foundation problem for sorted sequence lookups.</em></li>
 * <li><strong>LeetCode 1099:</strong> Two Sum Less Than K (Easy) - <em>Variation requiring tracking the maximum sum found that stays strictly below a threshold K.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Pair with Given Sum in a Sorted Array (Easy) - <em>Requires counting all valid pairs, including duplicate element handling.</em></li>
 * <li><strong>Coding Ninjas:</strong> Pair Sum in Sorted Array (Easy) - <em>Standard entry-level structural verification test.</em></li>
 * </ul>
 * </p>
 */

public class TwoSumSorted {
	
	public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        
        while (left < right) {
            int currentSum = numbers[left] + numbers[right];
            
            if (currentSum == target) {
                return new int[]{left + 1, right + 1}; //1-based indexing
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TwoSumSorted engine = new TwoSumSorted();
        int[] testInput = {2, 5, 7, 10, 11, 15};
        int target = 15;
        
        int[] result = engine.twoSum(testInput, target);
        System.out.println("Variation 1 (Two Sum Sorted) Result: " + Arrays.toString(result));
	}

}
