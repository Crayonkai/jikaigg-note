package com.jikaigg.note.algorithm.base;

public class Base9 {

    public static void main(String[] args) {
        int search = search("abc", "esdsdsabcd");
        System.out.println(search);

        String txt = "BBC ABCDAB CDABABCDABCDABDE";
        String pat = "ABCDABD";
        int[] next = new int[pat.length()];
        getNext(pat, next);
        System.out.println(KMPSearch(txt, pat, next));
    }

    /*
    * 暴力破解
    * */
    public static int search(String pat, String txt) {
        char[] patChar = pat.toCharArray();
        char[] txtChar = txt.toCharArray();
        int m = patChar.length;
        int n = txtChar.length;
        for (int i = 0; i < n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (patChar[j] != txtChar[i + j])
                    break;
            }
            if (j == m) return i;
        }
        return -1;
    }
    /*
     * 暴力破解
     * */
    public static int search1(String pat, String txt) {
        int m = pat.length();
        int n = txt.length();
        for (int i = 0; i < n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (pat.charAt(j) != txt.charAt(j + i))
                    break;
            }
            if (j == m) return i;
        }
        return -1;
    }

    /*
    * KMP算法
    * */
    public static int KMPSearch(String txt, String pat, int[] next) {
        int M = txt.length();
        int N = pat.length();
        int i = 0;
        int j = 0;
        while (i < M && j < N) {
            if (j == -1 || txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == N)
            return i - j;
        else
            return -1;
    }

    public static void getNext(String pat, int[] next) {
        int N = pat.length();
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < N - 1) {
            if (k == -1 || pat.charAt(j) == pat.charAt(k)) {
                ++k;
                ++j;
                next[j] = k;
            } else
                k = next[k];
        }
    }

}
