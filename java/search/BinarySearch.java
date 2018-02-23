package search;

import java.lang.Integer;
import java.lang.String;
import java.lang.IndexOutOfBoundsException;

public class BinarySearch {
    //Pre: forall i < j: a[i] >= a[j]
    private static int binSearch(int x, int[] a) {
        int l = -1, r = a.length;
        int m;
        //I: l >= l' && r <= r' && l < r
        //   forall 0 <= i <= l: a[i] > x
        //   forall r <= j < a.length:  a[i] <= x
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
    //Post: x = x'
    //      a.length = a'.length
    //      a[i] = a'[i] for i = 0...a.length - 1
    //      r <= r' == a.length && r > l' == -1
    //      R: r such that (r < a.length && a[r] <= x && a[r - 1] > x) ||
    //                     (r == a.length && (a.length < 1 || a[i] > x for i = 1...a.length))

    //Pre: forall i < j: a[i] >= a[j]
    private static int recursiveBinSearch(int x, int[] a, int l, int r) {
        if (r - l <= 1) {
            return r;
        }
        int m = (l + r) / 2;
        if (a[m] > x) {
            return recursiveBinSearch(x, a, m, r);
        }
        return recursiveBinSearch(x, a, l, m);
    }
    //Post: x = x'
    //      a.length = a'.length
    //      a[i] = a'[i] for i = 0...a.length - 1
    //      r <= r' == a.length && r > l' == -1
    //      R: r such that (r < a.length && a[r] <= x && a[r - 1] > x) ||
    //                     (r == a.length && (a.length < 1 || a[i] > x for i = 1'...r'))

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
