package pattern2_sameDirectionTwoPointer;

/**
 * <h1>Pattern 02: Same-Direction Two-Pointer — Variation 1: Sorted De-Duplication</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * This variation takes advantage of an already ordered sequence. Because identical elements are 
 * guaranteed to sit adjacent to each other, the Scanner pointer (<code>right</code>) can evaluate 
 * the current element against the last recorded element written by the Writer pointer (<code>left</code>). 
 * If it's a duplicate, the scanner skips past it, creating a dead zone. If it's a unique value, 
 * the writer moves forward one slot and overwrites the dead zone with the new value.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The input collection is an array or list that is already sorted.</li>
 * <li>The problem statement explicitly demands an in-place modification with O(1) auxiliary memory space.</li>
 * <li>You need to restrict duplicate elements to a maximum frequency of K (e.g., at most 1 or 2 occurrences).</li>
 * </ul>
 * </p>
 * * <p><strong>Core Java & Memory Guardrails:</strong>
 * <ul>
 * <li><strong>Array Primitives:</strong> This logic updates raw indices on a primitive <code>int[]</code>. This ensures that memory writes happen entirely within the CPU's local L1 data cache lines, bypassing the pointer-chasing delays associated with object collections.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Unique Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 26 / NeetCode 150:</strong> Remove Duplicates from Sorted Array (Easy) — <em>The baseline de-duplication tracking problem where each element must be unique.</em></li>
 * <li><strong>LeetCode 80:</strong> Remove Duplicates from Sorted Array II (Medium) — <em>An advanced variation where elements can appear at most twice, requiring the scanner to evaluate elements against <code>left - 2</code>.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Count Distinct Elements in Sorted Array In-Place (Easy) — <em>Requires compacting unique elements to the front and returning the unique count prefix.</em></li>
 * <li><strong>LintCode 100:</strong> Remove Duplicates from Sorted Array (Easy) — <em>Cross-platform execution suite validating strict in-place array boundaries.</em></li>
 * </ul>
 * </p>
 */
public class SortedDeDuplication {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // The Writer anchor points to the last known valid index in the unique prefix
        int left = 0; 
        
        // The Scanner pointer explores the remaining suffix of the array
        for (int right = 1; right < nums.length; right++) {
            // If the scanner discovers a value that is NOT a duplicate of our last written value...
            if (nums[right] != nums[left]) {
                left++; // Step the writer forward to the next available slot
                nums[left] = nums[right]; // Overwrite the dead space with the unique element
            }
        }
        
        // Returning the length of the valid prefix (1-based count)
        return left + 1;
    }

    public static void main(String[] args) {
        SortedDeDuplication engine = new SortedDeDuplication();
        int[] testInput = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        
        int length = engine.removeDuplicates(testInput);
        System.out.println("Variation 1 Prefix Length: " + length);
        System.out.print("Compacted Array State: [");
        for (int i = 0; i < length; i++) {
            System.out.print(testInput[i] + (i < length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}