package modulo.pl.hackerrank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class AlmostSortedIntervals {

    static class LinkedListMati<T> {
        LinkedListMatiNode<T> first = null, last = null;
        int size = 0;

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public T getFirst() {
            return first.val;
        }

        public T getLast() {
            return last.val;
        }

        public void add(T x) {
            size++;
            LinkedListMatiNode<T> n = new LinkedListMatiNode(x);
            if (first == null) {
                first = last = n;
            } else {
                last.next = n;
                n.prev = last;
                last = n;
            }
        }

        public void addAllBefore(LinkedListMati<T> li) {
            size += li.size;
            if (first == null) {
                first = li.first;
                last = li.last;
            } else if (li.first != null) {
                li.last.next = first;
                first.prev = li.last;
                first = li.first;
            }
        }

        public void removeLast() {
            size--;
            if (first == last) {
                first = last = null;
            } else {
                last = last.prev;
                last.next = null;
            }
        }
    }

    static class LinkedListMatiNode<T> {
        final T val;
        LinkedListMatiNode<T> prev, next;

        public LinkedListMatiNode(T val) {
            this.val = val;
            this.prev = this.next = null;
        }
    }

    static class D {
        public final int val;
        public final LinkedListMati<Integer> li;

        public D(int val, LinkedListMati<Integer> li) {
            this.val = val;
            this.li = li;
        }

        @Override
        public String toString() {
            if (li.isEmpty())
                return "D{val=" + val + "}";
            StringBuilder sb = new StringBuilder();
            LinkedListMatiNode<?> n = li.first;
            while (n != null) {
                sb.append(",").append(n.val);
                n = n.next;
            }
            return "D{val=" + val + ", li=" + sb.substring(1) + '}';
        }
    }

    static long solve(int[] arr) {
        int n = arr.length;
        long s = 0;
        D[] d = new D[n];
        int top = -1;
        for (int x : arr) {
            LinkedListMati<Integer> lg = new LinkedListMati<>();
            while (top >= 0 && d[top].val < x) {
                if (!lg.isEmpty()) {
                    int y = lg.getFirst();
                    while (!d[top].li.isEmpty() && (d[top].li.getLast() > y))
                        d[top].li.removeLast();
                }
                lg.addAllBefore(d[top--].li);
            }
            if (top >= 0) {
                while (!d[top].li.isEmpty() && d[top].li.getLast() > x)
                    d[top].li.removeLast();
                if (d[top].li.isEmpty())
                    top--;
            }
            lg.add(x);
            d[++top] = new D(x, lg);
            s += lg.size();
        }
        return s;
    }

    // slower solution to verify results
    static int solveOld(int[] arr) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        int s = 0;
        for (int x : arr) {
            while (true) {
                Integer k = tm.ceilingKey(x);
                if (k == null)
                    break;
                tm.remove(k);
            }
            Integer k = tm.floorKey(x);
            while (k != null) {
                if (tm.get(k) > x)
                    break;
                tm.put(k, x);
                k = tm.lowerKey(k);
                s++;
            }
            s++;
            tm.put(x, x);
        }
        return s;
    }

    @Test
    public void test1() {
        Assertions.assertEquals(8, solve(new int[]{3, 1, 2, 5, 4}));
    }

    @Test
    public void test2() {
        Assertions.assertEquals(7, solve(new int[]{1, 3, 2, 4}));
    }

    @Test
    public void test3() {
        Assertions.assertEquals(8, solve(new int[]{1, 3, 2, 5, 4}));
    }

    @Test
    public void test01() {
        Assertions.assertEquals(2202, solve(new int[]{1, 4, 3, 5, 9, 7, 10, 6, 2, 11, 8, 12, 13, 17, 14, 15, 16, 18, 19, 20, 21, 22, 24, 26, 27, 23, 25, 28, 29, 32, 31, 30, 33, 34, 37, 36, 35, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 52, 50, 49, 51, 53, 54, 55, 56, 57, 58, 59, 60, 62, 65, 63, 61, 68, 69, 64, 66, 71, 72, 67, 73, 70, 74, 75, 76, 77, 82, 79, 80, 81, 78, 83, 87, 84, 88, 85, 86, 89, 91, 90, 92, 93, 94, 95, 96, 99, 98, 100, 97}));
    }

    @Test
    public void test09() throws Exception {
        int a[];
        try (Scanner sc = new Scanner(new File("D:\\projekty\\Hackerrank\\testData\\AlmostSortedIntervals_i09.txt"))) {
            int n = Integer.valueOf(sc.nextLine());
            a = IntStream.generate(sc::nextInt).limit(n).toArray();
        }
        Assertions.assertEquals(2040290, solve(a));
    }

}
