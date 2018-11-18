package com.example.sergey.numalgo;

import android.print.PrinterId;
import android.widget.Toast;

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
            this.Normalize();
            return this.numerator + "/" + this.denominator;
        }
    }
    public void Read(String str){
        if (str.indexOf('/') != -1)
        {
            String[] num_denom = str.split("/");
            if (Long.valueOf(num_denom[1]) != 0)
            {
                this.numerator = Long.valueOf(num_denom[0]);
                this.denominator = Long.valueOf(num_denom[1]);
                this.Normalize();
            }
            else
            {

            }
        }
        else
        {
             this.numerator = Long.valueOf(str);
             this.denominator = 1;
        }
    }
    public static Fraction Add(Fraction fr1, Fraction fr2){
        long nk = NOK(fr1.denominator, fr2.denominator);
        long n1 = fr1.numerator * (nk / fr1.denominator);
        long n2 = fr2.numerator * (nk / fr2.denominator);
        return new Fraction(n1 + n2, nk);
    }
    public static Fraction Add(Fraction fr1, long n){
        return new Fraction(fr1.numerator + n * fr1.denominator, fr1.denominator);
    }
    public static Fraction Add(long n, Fraction fr1){
        return Add(fr1, n);
    }
    public static Fraction Sub(Fraction fr1, Fraction fr2){
        long nk = NOK(fr1.denominator, fr2.denominator);
        long n1 = fr1.numerator * (nk / fr1.denominator);
        long n2 = fr2.numerator * (nk / fr2.denominator);
        return new Fraction(n1 - n2, nk);
    }
    public static Fraction Sub(Fraction fr1, long n){
        return new Fraction(fr1.numerator - n * fr1.denominator, fr1.denominator);
    }
    public static Fraction Sub(long n,Fraction fr1){
        return new Fraction(n * fr1.denominator - fr1.numerator  , fr1.denominator);
    }
    public static Fraction Mult(Fraction fr1, Fraction fr2){
        Fraction i = new Fraction(fr1.numerator, fr1.denominator);
        Fraction j = new Fraction(fr2.numerator, fr2.denominator);
        long l = j.numerator;
        j.numerator = i.numerator;
        i.numerator = l;
        j.Normalize();
        i.Normalize();
        return new Fraction(j.numerator * i.numerator, j.denominator * i.denominator);
    }
    public static Fraction Mult(Fraction fr1, long n){
        Fraction i = new Fraction(fr1.numerator, fr1.denominator);
        Fraction j = new Fraction(n, 1);
        return Mult(i, j);
    }
    public static Fraction Mult(long n, Fraction fr1){
        return Mult(fr1, n);
    }
    public static Fraction Invert(Fraction fr1){
        Fraction rez = new Fraction(fr1.numerator, fr1.denominator);
        if (rez.numerator != 0)
        {
            long n = rez.denominator;
            if (rez.numerator > 0)
            {
                rez.denominator = rez.numerator;
                rez.numerator = n;
            }
            else
            {
                rez.denominator = -rez.numerator;
                rez.numerator = -n;
            }
        }
        return rez;
    }
    public static Fraction Div(Fraction fr1, Fraction fr2){
        return Mult(fr1, Invert(fr2));
    }
    public static Fraction Div(Fraction fr1, long n){
        Fraction fr = new Fraction(1, n);
        return Mult(fr1, fr);
    }
    public static Fraction Div(long n,Fraction fr1){
        Fraction fr = new Fraction(n, 1);
        return Mult(fr, Invert(fr1));
    }
    public static boolean Equal(Fraction fr1,Fraction fr2){

        return fr1.numerator == fr2.numerator && fr1.denominator == fr2.denominator;
    }
}
