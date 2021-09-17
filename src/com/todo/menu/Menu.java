package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println("\n<ToDoList 사용법>");
        System.out.println("add - 항목 추가");
        System.out.println("del - 항목 삭제");
        System.out.println("edit - 항목 수정");
        System.out.println("ls - 전체 목록 보기");
        System.out.println("asc - 제목순 정렬");
        System.out.println("desc - 제목역순 정렬");
        System.out.println("date - 날짜순 정렬");
        System.out.println("exit - 종료");
        System.out.print("명령어(help - 도움말) > ");
    }
    
    public void prompt() {
    	
        System.out.println("<ToDoList 사용법>");
        System.out.println("add - 항목 추가");
        System.out.println("del - 항목 삭제");
        System.out.println("edit - 항목 수정");
        System.out.println("ls - 전체 목록 보기");
        System.out.println("asc - 제목순 정렬");
        System.out.println("desc - 제목역순 정렬");
        System.out.println("date - 날짜순 정렬");
        System.out.println("exit - 종료");
        System.out.println("명령어(help - 도움말) >");
    }
}
