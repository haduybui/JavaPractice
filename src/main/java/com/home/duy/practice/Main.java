package com.home.duy.practice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.Math;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;
import java.util.function.BiConsumer;


public class Main {
    static String helloStringFromMain = "Static String from Main";
    static String inputString = "hello";

    public static void main(String[] args) {
        Main.run();
    }

    public static void run() {
        System.out.println("Reaching run() method" +
                "\nAdding \"Duy Bui\" into 'helloStringFromMain'"
        );
        //Processing static contents
        Main.setHelloString("Duy Bui");
        System.out.println(getHelloString());
        System.out.println("printing Hello String from main: " + getHelloString());

        System.out.println("Getting String from ChildOfMain.class");
        System.out.println(ChildOfMain.getChildOfMainStatisString());
        System.out.println("Testing lambda functions");

        Main.checkEnum(MyConstants.ADDITION, 2, 3);
        Main.checkEnum(MyConstants.SUBTRACTION, -10, 5);
        Main.checkEnum(MyConstants.PRINTTEXT, "SampleText");
        Main.checkEnum(MyConstants.PRINTTEXT);
        Main.checkEnum(MyConstants.GORANDOM, 169);

        characterCounter(inputString);
        characterCounterWithLambda(inputString);

        List<String> listString = new ArrayList<>();
        listString.forEach((element)->{System.out.println("This is k:" +element);});
        PrintDefault printDefault = ()->{System.out.println("Haha");};


        System.out.println("Playing with Maps: ");
        Map<String, String> myMap = new HashMap<>();
        myMap.put("Cat","Felix");
        myMap.put("Dog","Ruby");
        myMap.put("Owl","Hedwig");

        System.out.println("Using keySet() with Iterator<T>");
        Set<String> myKeys = myMap.keySet();
        Iterator<String> myKeysIterator = myKeys.iterator();
        while (myKeysIterator.hasNext()){
            System.out.println(myMap.get(myKeysIterator.next()));
        }

        System.out.println("Using Set<Map.Entry<K,V>>");
        Set<Map.Entry<String, String>> myMapEntrySet = myMap.entrySet();
        Iterator<Map.Entry<String, String>> myMapEntrySetIterator = myMapEntrySet.iterator();
        while (myMapEntrySetIterator.hasNext()){
            Map.Entry<String, String> myMapEntry = myMapEntrySetIterator.next();
            System.out.println("The key is: " +  myMapEntry.getKey() +" The Value is: "+myMapEntry.getValue());
        }

        System.out.println("Using forEach()");
        myMap.forEach((k,v)->System.out.println("Key: "+k+" Value: "+v));

        Map<String, Integer> charCounterMap = countCharacter("Hello");
        charCounterMap.forEach((k,v)-> System.out.println("char: "+k +" count: "+v));


        System.out.println("Trying to practice with Iterator");
        List<String> myList = new ArrayList<>();
        myList.add("String1");
        myList.add("String2");
        myList.add("String3");
        myList.add("String4");

        Iterator<String> stringIterator = myList.iterator();
        while (stringIterator.hasNext()){
            String elemntOfList = stringIterator.next();
            System.out.println(elemntOfList);
        }

        System.out.println("Doing something funny with Array and List to and fro");
        List<String> myListToArray = new ArrayList<>();
        myListToArray.add("RandomThing1");
        myListToArray.add("RandomThing2");
        myListToArray.add("RandomThing3");
        myListToArray.add("RandomThing4");

        String[] myArrayThatTakesList = new String[0];
        myArrayThatTakesList = myListToArray.toArray(myArrayThatTakesList);
        for(String element : myArrayThatTakesList){
            System.out.println(element);
        }

        String[] myStringArray = new String[0];

        String[] notRandomStringArray = {"NotRandomThing1","NotRandomThing2","NotRandomThing3"};

        List<String> notRandomListString = Arrays.asList(notRandomStringArray);
        notRandomListString.forEach((v)->{System.out.println(v);});

        //Testing Nested (static) class, Inner class and Anonymous class
        StringUtil stringUtil = new StringUtil();
        StringUtil.NestedStringUtil nestedStringUtil = new StringUtil.NestedStringUtil();
        StringUtil.InnerStringUtil innerStringUtil = (new StringUtil()).new InnerStringUtil();

        Map<String, String> myMapWithLambda = new HashMap<>();
        myMapWithLambda.put("a","haha");
        myMapWithLambda.put("b","hbhb");
        myMapWithLambda.put("c","hchc");
        myMapWithLambda.put("d","hdhd");

        myMapWithLambda.replaceAll((k,v) -> v.toUpperCase());
        myMapWithLambda.forEach((k,v)->System.out.println(k+"|"+v));


        //Start of concurrency and multithreading

        //Using runnable and thread to create concurrency
        MyRunnableClass myRunnableClass = new MyRunnableClass();
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++){
            threads[i] = new Thread(myRunnableClass);
            threads[i].start();
        }

        for(Thread thread : threads){
            try{
                //Block the main thread to stop before the other thread finishes
                thread.join();
            } catch (InterruptedException e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        //Using runnable and thread to create concurrency


        //Using ExecutorService, Callable<V> and Future<V>
        System.out.println("\n\n\n\tStarting to used ExecutorService, Executors, Callable and Future");

        ExecutorService es = Executors.newFixedThreadPool(5);
        Callable<Integer> myCallableClass = new MyCallableClass();
        Future<Integer>[] futures = new Future[5];

        for(int i = 0; i< 5; i++){
                futures[i] = es.submit(myCallableClass);
//            es.submit(myRunnableClass);
        }

        try{
            es.shutdown();
            es.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e){
            Throwable throwableExc = e.getCause();
            System.out.println("Exception caught: "+throwableExc.getMessage());
        }

        for ( Future<Integer> future : futures) {
            try {
                int value = future.get();
                System.out.println("From future element: " + value);
            } catch (ExecutionException e) {
                Throwable throwableExc = e.getCause();
                System.out.println(throwableExc.getMessage());
                throwableExc.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

        //End of concurrency and multithreading

        // START of FILE IO JAVA
        Main.creatFileWithAbsolutePath("haha.txt");
        // END OF FILE OJ JAVA

    }

    public static Map<String, Integer> countCharacter(String input){
        Map<String, Integer> charMap = new TreeMap<>();
        input = input.toLowerCase();
        String[] elements = input.split("");
        for(String element : elements){
            Integer receivedCounter = charMap.get(element);
            if(receivedCounter==null){
                int counter = 1;
                charMap.put(element, counter);
            } else{
                receivedCounter = charMap.get(element)+1;
                charMap.put(element, receivedCounter);
            }
        }

        return charMap;
    }

    public static void characterCounter(String inputString){
       Map<Character, Integer> characterMap = characterProcessor(inputString);
       Set<Map.Entry<Character, Integer>> entrySet = characterMap.entrySet();
       for(Map.Entry<Character, Integer> entry : entrySet){
           System.out.println("Character: "+ entry.getKey() + " count: "+entry.getValue());
       }
   }

    public static void characterCounterWithLambda(String inputString){
        Map<Character, Integer> characterMap = characterProcessor(inputString);
        characterMap.forEach((k,v)->{
            System.out.println("Character: "+ k + " count: "+v);
        });

    }

    public static Map<Character, Integer> characterProcessor(String inputString){
        Map<Character, Integer> characterMap = new HashMap<>();
        char[] charArray = inputString.toCharArray();
        for(char element : charArray){
            Integer retrievedCounter = characterMap.get(element);
            if(retrievedCounter==null){
                Integer counter = new Integer(1);
                characterMap.put(element, counter);
            } else{
                retrievedCounter = new Integer(retrievedCounter.intValue()+1);
                characterMap.put(element,retrievedCounter);
            }
        }
        return characterMap;
    }

    public static void setHelloString(String helloString) {
        Main.helloStringFromMain = helloString;
    }

    public static String getHelloString() {
        return Main.helloStringFromMain;
    }

    //define the functionality of the method instance
    public static double doAddition(double a, double b) {
        double result;
        //we define this to work - call it a method instance 'addition' with 1 method equate(double varA, double varB)
        MathEquation addition = (double x, double y) -> {
            return x + y;
        };
        result = addition.equate(1, 2);
        System.out.println("Sum between 1 and 2 is: " + addition.equate(1, 2));
        return result;
    }

    public static double doSubtraction(double a, double b) {
        double result;
        //we define this to work - call it an instance
        MathEquation difference = (double x, double y) -> {
            if ((x - y) >= 0) {
                return x - y;
            } else {
                return y - x;
            }
        };
        result = difference.equate(a, b);
        System.out.println("Sum between" + a + " and " + b + " is: " + result);
        return result;
    }

    public static void doPrintText(String text) {
        PrintText printText = (String input) -> {
            return input;
        };
        String result = printText.getText(text);
        System.out.println("Function method is String prinText.getText(String text): giving output: " + result);
    }

    public static void doRandom(Integer input){
        GenericFunction<Integer> genericFunction = (Integer integer) -> {
//            int castedInteger = (int) integer;
            System.out.println("Going Random, finding squareroot of: "+ integer +" is "+ Math.sqrt((double) integer));
        };
        genericFunction.doSomething(input);
    }

    public static void checkEnum(MyConstants enumConstant, double a, double b) {
        switch (enumConstant) {
            case ADDITION:
                doAddition(a, b);
                break;
            case SUBTRACTION:
                doSubtraction(a, b);
                break;
            default:
                System.out.println("Sorry cannot do anything");
        }
    }

    public static void checkEnum(MyConstants enumConstant, String inputString) {
        switch (enumConstant) {
            case PRINTTEXT:
                doPrintText(inputString);
                break;
            default:
                System.out.println("Sorry cannot do anything");
        }
    }

    public static void checkEnum(MyConstants enumConstant, int anyVar) {
        switch (enumConstant) {
            case GORANDOM:
                doRandom(anyVar);
                break;
            default:
                System.out.println("Sorry cannot do anything");
        }
    }

    public static void checkEnum(MyConstants enumConstant){
        switch (enumConstant){
            case PRINTTEXT:

        }
    }

    public static void creatFileWithAbsolutePath(String fileName){
        String text = "This is the String";
        String text1 = "1_This is the String";
        File file = new File("C:\\Users\\n633023.EMEA\\devlopment\\github\\JavaPractice\\src\\main\\resources\\"+fileName);
        if(!file.exists()){
            try{
                if(file.createNewFile()){
                    try(PrintStream printStream = new PrintStream(new FileOutputStream(file), true)){
                        printStream.print(text);
                    } catch (Exception e) {
                        System.out.println("There is something wrong, exception: " + e.getMessage());
                    }
                }
            } catch (Exception e){
                System.out.println("Exception found: " + e.getMessage());
            }
        } else {
            try(PrintStream printStream = new PrintStream(new FileOutputStream(file, true), true)){
                printStream.append(text1.concat("\n"));
            } catch (Exception e) {
                System.out.println("There is something wrong, exception: " + e.getMessage());
            }
        }
    }
}
//------------------------------------------------------------------------------------------------


//------------------------------------------------------------------------------------------------
class ChildOfMain {
    String string1;
    String string2;

    public ChildOfMain() {

    }

    public ChildOfMain(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    public static String childOfMainStaticString = "This string from childOfMain";

    public static String getChildOfMainStatisString() {
        return childOfMainStaticString;
    }
}
//------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------
interface MathEquation {
    double equate(double a, double b);

    default public void printSomething(String text) {
        System.out.println("Printing out: " + text);
    }
}
//------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------
interface PrintText {
    String getText(String text);
}
//------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------
interface GenericFunction<T> {
    public void doSomething(T anyVar);
}
//------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------
interface PrintDefault{
    public void printDefault();
}
//------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------
class TestLambdaUsage{
    public void printWithGenericFunction(BiConsumer<String, Integer> action){

    }
}
//------------------------------------------------------------------------------------------------

