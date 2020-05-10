**模运算**

- 中文名

  取模运算

- 公  式

  n = kp + r

- 求整数商

   c = a/b

- 计算模

   r = a - c*b

7 mod 4 = 3（商 = 1 或 2，1<2，取商=1）

-7 mod 4 = 1（商 = -1 或 -2，-2<-1，取商=-2）

7 mod -4 = -1（商 = -1或-2，-2<-1，取商=-2）

-7 mod -4 = -3（商 = 1或2，1<2，取商=1）

```java
public int reverse(int x) {//数字反转
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
```

