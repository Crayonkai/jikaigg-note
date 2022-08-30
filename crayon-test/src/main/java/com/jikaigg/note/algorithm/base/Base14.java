package com.jikaigg.note.algorithm.base;

public class Base14 {
    public int[] plusOne(int[] digits) {
        int length = digits.length ;
        if(digits[length-1] == 9 && digits[0] == 9){
            int[] tmp = new int[length+1];
        }else if(digits[length-1] == 9){
            digits[length-2]+=1;
            digits[length-1] = 0;
        }else{
            digits[length-1]+=1;
        }

        return digits;
    }
    public int[] plusOne1(int[] digits) {


        return digits;
    }
}
