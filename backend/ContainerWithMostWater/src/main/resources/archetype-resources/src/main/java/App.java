package $org.example;

/**
 * Hello world!
 */
public class App {

    public  int maxArea(int[] heights) {
        int left = 0;
        int right = heights.length - 1;
        int res = 0;
        int area = 0;

        while (left < right) {
            area = Math.min(heights[left], heights[right]) * (right - left);
            if (res < area) {
                res = area;
            }
            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
