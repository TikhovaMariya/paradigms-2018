package search;

import java.lang.Integer;
import java.lang.String;
import java.lang.IndexOutOfBoundsException;

public class BinarySearch {
    private static int binSearch(int x, int[] a) {
        int l = -1, r = a.length;
        int m;
        while (l < r - 1) {
            m = (l + r) / 2;
            if (a[m] > x) {
                l = m;
            } else {
                r = m;
            }
        }
        return r;
    }

    private static int recursiveBinSearch(int x, int[] a, int left, int right) {
        int middle = (left + right) / 2;
        if(right - left <= 1) {
            return right;
        }
        if (a[middle] > x) {
            return recursiveBinSearch(x, a, middle, right);
        }
        return recursiveBinSearch(x, a, left, middle);
    }

    public static void main(String[] args) {
        try {
            int x = Integer.valueOf(args[0]);
            int[] a = new int[args.length - 1];
            for (int i = 0; i < a.length; ++i) {
                a[i] = Integer.valueOf(args[i + 1]);
            }
            //System.out.println(String.valueOf(binSearch(x, a)));
            //System.out.println(String.valueOf(recursiveBinSearch(x, a, -1, a.length)));
        } catch (IndexOutOfBoundsException err) {
            System.out.println("You should insert x and array values");
        }

    }
}
