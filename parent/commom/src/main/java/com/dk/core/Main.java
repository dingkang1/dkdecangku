package com.dk.core;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class Main {
    public static void main(String[] args) {
        String code = "\ufffd\ufffd\ufffd" ;
        System.out.println(code);
        new A().a();
    }
}

class A {

    public void a(){
        new B().b();
    }
}

class B {
    public void b(){
        Class<?> callerClass = Reflection.getCallerClass(1);
        System.out.println(callerClass.getName());

    }
}