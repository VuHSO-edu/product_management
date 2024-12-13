import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ex2 {

    public static boolean isPalindrome(String str) {

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            queue.offer(ch);
            stack.push(ch);
        }

        while (!queue.isEmpty()) {
            if (queue.poll() != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "madam";
        System.out.println(isPalindrome(str));
    }
}
