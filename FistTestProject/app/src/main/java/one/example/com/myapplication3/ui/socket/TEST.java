package one.example.com.myapplication3.ui.socket;

import one.example.com.myapplication3.Logs;

public class TEST {
    public static void test() {
        byte b = 0;
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            b += i;
            sum += i;
        }
        Logs.iprintln("TAG", "he  b=" + b + "sum=" + sum);
        for (int i = 0; i < 1000; i++) {
            b -= i;
            sum -= i;
        }
        Logs.iprintln("TAG", "b=" + b + "sum=" + sum);
    }
}

 class Solution {

    public String addBinary(String a, String b) {
        int carry = 0;
        int sum = 0;
        int opa = 0;
        int opb = 0;
        StringBuilder result = new StringBuilder();
        while (a.length() != b.length()) {
            if (a.length() > b.length()) {
                b = "0" + b;
            } else {
                a = "0" + a;
            }
        }
        for (int i = a.length() - 1; i >= 0; i--) {
            opa = a.charAt(i) - '0';
            opb = b.charAt(i) - '0';
            sum = opa + opb + carry;
            if (sum >= 2) {
                result.append((char) (sum - 2 + '0'));
                carry = 1;
            } else {
                result.append((char) (sum + '0'));
                carry = 0;
            }
        }
        if (carry == 1) {
            result.append("1");
        }
        return result.reverse().toString();
    }
}