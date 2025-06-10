package $org.example;

/**
 * Hello world!
 */
public class App {

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.trap(new int[] {1,1,0,1,1,1,1,1,1}));
    }
}
