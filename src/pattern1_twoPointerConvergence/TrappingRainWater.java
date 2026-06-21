package pattern1_twoPointerConvergence;

/**
 * <h1>Pattern 01: Two-Pointer Convergence — Variation 5: Dual-Boundary Elevation Trapping</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * This variation tracks dynamic boundaries across asymmetrical topological layouts. The volume of fluid that 
 * can be trapped above any single element is limited by the minimum of the tallest structures to its left and right, 
 * minus the element's own height. By placing convergence pointers at both ends and tracking the maximum wall heights 
 * on the fly, the algorithm can process the side with the shorter bounding wall safely, bypassing the need to know 
 * the exact heights on the opposite side of the array.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The problem input represents a 1D elevation map, terrain grid, or histogram distribution.</li>
 * <li>The calculation requires finding accumulated bounded regions or trapped metrics between structural elements.</li>
 * <li>The system requires an optimal O(N) runtime paired with a strict O(1) auxiliary memory footprint.</li>
 * </ul>
 * </p>
 * * <p><strong>Mathematical & System Mechanics:</strong><br>
 * At any point in execution, if <code>height[left] &lt; height[right]</code>, the water level at the left index is limited 
 * strictly by <code>leftMax</code> rather than <code>rightMax</code>, because we already have a reliable wall on the right 
 * that is equal to or taller than the current left side. This mathematical certainty allows us to calculate the trapped 
 * water at the current pointer index immediately and advance that pointer inward, maintaining a single-pass linear scan.</p>
 * * <p><strong>Core Java & Hardware Guardrails:</strong>
 * <ul>
 * <li><strong>Stack Frame Safety:</strong> All state variables (<code>left</code>, <code>right</code>, <code>leftMax</code>, <code>rightMax</code>, <code>totalWater</code>) are primitive integers allocated inside the thread's execution stack frame. This guarantees near-instant CPU read/write access and zero garbage collection footprint.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 42 / NeetCode 150:</strong> Trapping Rain Water (Hard) - <em>The classic multi-boundary elevation problem.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Trapping Rain Water (Hard) - <em>High-frequency test case for core runtime and space optimization.</em></li>
 * <li><strong>Coding Ninjas:</strong> Trapping Rainwater (Hard) - <em>Advanced evaluation checking for boundary tracking logic.</em></li>
 * <li><strong>LintCode 363:</strong> Trapping Rain Water (Hard) - <em>Cross-platform execution model matching identical system profiles.</em></li>
 * </ul>
 * </p>
 */
public class TrappingRainWater {
	public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWater;
    }

    public static void main(String[] args) {
        TrappingRainWater engine = new TrappingRainWater();
        int[] testInput = {4, 2, 0, 3, 2, 5};
        
        int result = engine.trap(testInput);
        System.out.println("Variation 5 (Trapping Rain Water) Result: " + result);
    }
}
