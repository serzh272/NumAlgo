package com.example.sergey.numalgo;

import android.print.PrinterId;

public class Fraction {
    private long numerator;
    private long denominator;
    public Fraction(){
        this.numerator = 0;
        this.denominator = 1;
    }

    public Fraction(long n, long d)
    {
        this.numerator = n;
        this.denominator = d;
        this.Normalize();
    }

    private void Normalize(){
        if (this.numerator == 0)
        {
            this.denominator = 1;
        }
        else
        {
            long nd = NOD(this.numerator, this.denominator);
            this.numerator /= nd;
            this.denominator /= nd;
        }
    }

    private static long NOD(long a, long b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        while (a != 0 && b != 0)
        {
            if (a > b)
            {
                a = a % b;
            }
            else
            {
                b = b % a;
            }
        }
        return a + b;
    }

    private static long NOK(long a, long b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        a = a / NOD(a, b);
        return a * b;
    }

    public String Write(){
        if (this.denominator == 1)
        {
            return "" + this.numerator;
        }
        else
        {
            return this.numerator + "/" + this.denominator;
        }
    }
    public void Read(String str){

    }
}
