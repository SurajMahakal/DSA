package pattern1_twoPointerConvergence;

/**
 * <h1>Pattern 01: Two-Pointer Convergence — Variation 3: Greedy Boundary Optimization</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * This variation solves 2D dimensional optimization boundaries. The objective is to maximize an area product 
 * defined by the mathematical function: <code>Area = Width * Height</code>. By starting at the widest possible state 
 * (extreme boundaries), every subsequent convergence step decreases the <code>Width</code> component by exactly 1 unit. 
 * To find a larger area overall, the algorithm must compensate for losing width by searching for a larger 
 * <code>Height</code> value, leading to a clean, greedy pointer step logic.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The problem input is an array representing heights, coordinate walls, or vertical structures.</li>
 * <li>The objective requires maximizing a volume, container capacity, or bounded area product.</li>
 * <li>The constraints prevent using an O(N^2) nested loop approach due to high input scaling parameters (e.g., N = 10^5).</li>
 * </ul>
 * </p>
 * * <p><strong>Mathematical & System Mechanics:</strong><br>
 * The maximum liquid height a container can hold is strictly limited by its shorter vertical wall: 
 * <code>min(A[left], A[right])</code>. Moving the pointer that points to the taller wall can never increase the calculated area, 
 * since the area is constrained by the shorter wall and the width has just decreased. Therefore, to unlock a higher 
 * volume state, we must greedily move the pointer associated with the shorter wall inward.</p>
 * * <p><strong>Core Java & Hardware Guardrails:</strong>
 * <ul>
 * <li><strong>Math Call Overhead:</strong> Repeatedly invoking <code>Math.min()</code> and <code>Math.max()</code> inside a loop adds small method call overhead to the thread stack. In high-performance micro-benchmarks, replacing these with raw ternary operator expressions (<code>a &lt; b ? a : b</code>) helps the compiler inline the calculations directly into machine code.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 11 / NeetCode 150:</strong> Container With Most Water (Medium) - <em>The classic area-maximization problem driven by shorter wall constraints.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Max Water Between Two Towers (Medium) - <em>Alternative structural description requiring identical greedy optimization paths.</em></li>
 * <li><strong>Coding Ninjas:</strong> Container With Most Water (Medium) - <em>Standard test coverage targeting efficiency and edge tracking optimization.</em></li>
 * <li><strong>LintCode 383:</strong> Container With Most Water (Medium) - <em>Cross-platform verification suite matching identical tracking logic profiles.</em></li>
 * </ul>
 * </p>
 */

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxVolume = 0;
        
        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int currentVolume = width * currentHeight;
            
            maxVolume = Math.max(maxVolume, currentVolume);
            
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxVolume;
    }

    public static void main(String[] args) {
        ContainerWithMostWater engine = new ContainerWithMostWater();
        int[] testInput = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        
        int result = engine.maxArea(testInput);
        System.out.println("Variation 3 (Most Water) Result: " + result);
    }
}
