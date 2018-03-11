package search;

import java.lang.Integer;
import java.lang.String;
import java.lang.IndexOutOfBoundsException;

public class BinarySearchMissing {
    // Pre: forall i a[i] >= a[i + 1]
    private static int binSearch(int x, int[] a) {
        int l = -1, r = a.length;
        int m;
        // Inv: l >= l' && r <= r' && l < r
        //     r - l >= 1
        //     (r - l) <= (r' - l') / 2
        //     forall 0 <= i <= l: a[i] > x
        //     forall r <= j < a.length:  a[i] <= x
        while (l < r - 1) {
            m = (l + r) / 2;
            if (a[m] > x) {
                l = m;
            } else {
                r = m;
            }
        }
        // x = x'
        // a.length = a'.length
        // a[i] = a'[i] for i = 0...a.length - 1
        // Inv && r - l == 1
        // (a[a.size - 1] > x || a.size == 0) && l = a.size() - 1 && r == a.size ||
        // r < a.size && a[r] <= x && (r == 0 || a[r - 1] > x)
        if (r < a.length && a[r] == x) {
            return r;
        }
        return -r - 1;
    }
    //Post: x = x'
    //      a.length = a'.length
    //      a[i] = a'[i] for i = 0...a.length - 1
    //      r <= r' == a.length && r > l' == -1
    //      R: -r* - 1 such that (r* < a.length && a[r*] == x) ||
    //                           (a[0] > x && r* == a.length) ||
    //                           (a[r*] < x && a[r* - 1] > x)


    //Pre: forall i < j: a[i] >= a[j]
    //     l >= l' && r <= r' && l < r && l
    //     r - l >= 1
    //     (r - l) <= (r' - l') / 2
    //     forall 0 <= i <= l: a[i] > x
    //     forall r <= j < a.length:  a[i] <= x
    private static int recursiveBinSearch(int x, int[] a, int l, int r) {
        if (r - l <= 1) {
            if (r < a.length && a[r] == x) {
                return r;
            }
            return -r - 1;
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
    //      R: -r* - 1 such that (r* < a.length && a[r*] == x) ||
    //                           (a[0] > x && r* == a.length) ||
    //                           (a[r*] < x && a[r* - 1] > x)
    public static void main(String[] args) {
        try {
            int x = Integer.valueOf(args[0]);
            int[] a = new int[args.length - 1];
            for (int i = 0; i < a.length; ++i) {
                a[i] = Integer.valueOf(args[i + 1]);
            }
            System.out.println(String.valueOf(binSearch(x, a)));
            //System.out.println(String.valueOf(recursiveBinSearch(x, a, -1, a.length)));
        } catch (IndexOutOfBoundsException err) {
            System.out.println("You should insert x and array values");
        }

    }
}
