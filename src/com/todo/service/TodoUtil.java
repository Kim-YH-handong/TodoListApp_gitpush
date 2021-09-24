package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("\n"
				+ "--------일정 추가하기--------\n"
				+ "카테고리 입력 > ");
		category = sc.nextLine();
		
		System.out.printf("제목 입력 > ");
		title = sc.nextLine();
		
		if (list.isDuplicate(title)) {
			System.out.printf("[오류]이미 제목의 일정이 존재합니다!!");
			return;
		}
		
		System.out.printf("설명 입력 > ");
		desc = sc.nextLine();
		
		System.out.printf("마감일자 입력(yyyy/mm/dd) > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		int number = sc.nextInt();

		l.deleteItem(l.getList().get(number-1));
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("\n"
				+ "-------일정 수정-------\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		int number = sc.nextInt();
		TodoItem element = l.getList().get(number-1);
		System.out.printf("\n%d. [%s] %s - %s", number, element.getTitle(), element.getDesc(), element.getCurrent_date());
		
		//l.deleteItem(null);
		
		System.out.printf("\n새로운 일정 이름 > ");
		String new_title = sc.next().trim();
	
		System.out.printf("\n새로운 카테고리 > ");
		String new_category = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("[오류]일정 이름이 중복되었음!");
			return;
		}
		
		sc.nextLine();
		
		System.out.printf("\n새로운 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.printf("\n새로운 마감일 입력 (yyyy/mm/dd) > ");
		String new_end_date = sc.nextLine().trim();
		
		l.deleteItem(l.getList().get(number-1));
		TodoItem t = new TodoItem(new_category, new_title, new_description, new_end_date);
		l.addEditItem(number-1, t);
	}

	public static void listAll(TodoList l) {
		int i = 1;
		System.out.printf("[전체 목록, 총 %d개]", l.getList().size());
		for (TodoItem item : l.getList()) {
			System.out.printf("\n%d. [%s] %s - %s - %s - %s",i, item.getCategry(),item.getTitle(), item.getDesc(), item.getEnd_date(), item.getCurrent_date());
			i++;
		}
		System.out.println();
	}
	
	public static void findList(TodoList l, String keyword) {
		keyword = keyword.trim();
		ArrayList<String> list = new ArrayList<String>();
		int i = 1;
		
		for(TodoItem item: l.getList()) {
			if(item.toFindString().contains(keyword)){
				list.add(i+". " + "[" + item.getCategry() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getEnd_date() + " - " + item.getCurrent_date());
			}
			i++;
		}
		System.out.printf("[전체 목록, 총 %d개]\n", list.size());
		
		for(String item: list) {
			System.out.println(item);
		}
	}
	
	public static void findCate(TodoList l, String cateName) {
		cateName = cateName.trim();
		ArrayList<String> list = new ArrayList<String>();
		int i = 1;
		
		for(TodoItem item: l.getList()) {
			if(item.getCategry().contains(cateName)){
				list.add(i+". " + "[" + item.getCategry() + "] " + item.getTitle() + " - " + item.getDesc() + " - " + item.getEnd_date() + " - " + item.getCurrent_date());
			}
			i++;
		}
		System.out.printf("[전체 목록, 총 %d개]\n", list.size());
		
		for(String item: list) {
			System.out.println(item);
		}
	}
	
	public static void countCategory(TodoList l) {
		Set<String> set = new HashSet<String>();
		boolean first = true;
		
		for(TodoItem item: l.getList()) {
			set.add(item.getCategry());
		}
		
		for(String item: set) {
			if(first) {
				System.out.printf(item);
				first = false;
			}else {
				System.out.printf(" / %s", item);
			}
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.", set.size());
	}
	
	public static void saveList(TodoList l, String filename) {
		Writer w;
		try {
			w = new FileWriter(filename);
			
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("입력하신 정보가 모두 저장되었습니다!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		int i = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String oneline;
			while((oneline = br.readLine()) != null) {
				i++;
				String [] str = oneline.split("##");
				l.addItem(new TodoItem(str[0], str[1], str[2], str[3], str[4]));
			}
			
			System.out.printf("\n총 %d개의 일정을 읽어왔습니다!\n", i);
		}catch (FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}
		}
	}

