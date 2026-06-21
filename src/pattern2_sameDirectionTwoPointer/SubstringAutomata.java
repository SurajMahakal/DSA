package pattern2_sameDirectionTwoPointer;

/**
 * <h1>Pattern 02: Same-Direction Two-Pointer — Variation 3: Substring Automata</h1>
 * * <p><strong>Core Philosophy:</strong><br>
 * This variation tracks alignments across two independent character streams simultaneously. 
 * Instead of moving both pointers down a single array, one pointer acts as a **Sequence Scout** traversing 
 * the primary text container, while the second pointer acts as a **Pattern Anchor** advancing down the target sequence. 
 * The pattern pointer only steps forward when the text pointer finds a matching character at its current position, 
 * providing a single-pass verification loop.</p>
 * * <p><strong>Pattern Recognition Triggers:</strong>
 * <ul>
 * <li>The problem involves evaluating relationships between two independent strings or sequences (e.g., structural alignment, matching subsets).</li>
 * <li>You need to verify if characters appear in the exact same relative order across both inputs, even if they are separated by other characters.</li>
 * <li>The execution must avoid nested loops ($O(N \times M)$) to prevent timing out on large inputs.</li>
 * </ul>
 * </p>
 * * <p><strong>Core Java & JVM Guardrails:</strong>
 * <ul>
 * <li><strong>String Range-Checking Overhead:</strong> Invoking <code>s.charAt(i)</code> inside a high-frequency loop forces the JVM to run internal index boundary checks on every single iteration step. For maximum performance in top-tech interview rounds, convert the strings into raw primitive character arrays (<code>s.toCharArray()</code>) before entering the matching loop to access elements directly.</li>
 * </ul>
 * </p>
 * * <p><strong>Target Unique Problem Registry:</strong>
 * <ul>
 * <li><strong>LeetCode 392 / NeetCode 150:</strong> Is Subsequence (Easy) — <em>Validates if a target string can be formed from a primary string by deleting characters without scrambling the remaining character order.</em></li>
 * <li><strong>LeetCode 28:</strong> Find the Index of the First Occurrence in a String (Medium) — <em>A foundational string matching algorithm that can be optimized using same-direction pointer comparisons.</em></li>
 * <li><strong>LeetCode 524:</strong> Longest Word in Dictionary through Deleting (Medium) — <em>Evaluates a list of strings against a target container using a sub-sequence pointer loop to find the longest match.</em></li>
 * <li><strong>GeeksforGeeks:</strong> Check for Subsequence (Easy) — <em>Determines structural string alignment across varying character distributions.</em></li>
 * </ul>
 * </p>
 */
public class SubstringAutomata {

    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null) return false;
        if (s.isEmpty()) return true; // An empty string is naturally a subsequence of any container
        
        // Convert to primitive character arrays to maximize cache speed and bypass charAt bounds checks
        char[] pattern = s.toCharArray();
        char[] text = t.toCharArray();
        
        int patternIdx = 0; // The Pattern Anchor pointer tracking matches in string s
        int textIdx = 0;    // The Sequence Scout pointer traversing string t
        
        // Loop runs until the scout reaches the end of the text, or the anchor completes the pattern match
        while (textIdx < text.length) {
            // If characters match, advance the pattern anchor pointer
            if (pattern[patternIdx] == text[textIdx]) {
                patternIdx++;
                
                // If the anchor pointer reaches the end of the pattern array, all characters have been matched in order
                if (patternIdx == pattern.length) {
                    return true;
                }
            }
            textIdx++; // The sequence scout moves forward on every iteration step
        }
        
        return false;
    }

    public static void main(String[] args) {
        SubstringAutomata engine = new SubstringAutomata();
        String pattern = "abc";
        String text = "ahbgdc";
        
        boolean result = engine.isSubsequence(pattern, text);
        System.out.println("Variation 3 (Is Subsequence) Result: " + result);
    }
}