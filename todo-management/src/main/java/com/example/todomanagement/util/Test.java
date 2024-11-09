package com.example.todomanagement.util;

public class Test {
    public static void main(String[] args) {
        String str = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6InRlc3QuY29tIiwiaWF0IjoxNzEwMjEzMjEzLCJleHAiOjE3MTAyMTM1MTN9.XrZLjaom1ebYPTJEw8KJOPyVKH5chHXSAGiLlE55eHQ";
        String[] arr = str.split(" ");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }
}
