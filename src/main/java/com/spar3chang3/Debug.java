package com.spar3chang3;

public class Debug {
    public static void main(String[] args) {

        System.out.println("Debugged logpath: " + FileConfig.getLogPath());
        System.out.println("Debugged heappath: " + FileConfig.getHeapMetricPath());
    }
}
