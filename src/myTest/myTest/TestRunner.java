package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;



public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(myTest.ListAdapterTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
        System.out.println("Total tests run: 61.");
        System.out.println("End of the suite ListAdapterTester. Proceeding with the next one.\n");
    }
}