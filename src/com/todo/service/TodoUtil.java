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
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("\n"
				+ "--------일정 추가하기--------\n"
				+ "제목 입력 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("[오류]이미 제목의 일정이 존재합니다!!");
			return;
		}
		sc.nextLine();
		
		System.out.printf("설명 입력 > ");
		desc = sc.nextLine();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("\n"
				+ "-------일정 삭제하기-------\n"
				+ "삭제할 일정 제목 > ");
		String title = sc.nextLine();

		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.printf("\n"
				+ "-------일정 수정-------\n"
				+ "수정하고 싶은 일정 이름 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("[오류]해당 일정과 같은 이름이 없음!");
			return;
		}

		System.out.printf("\n새로운 일정 이름 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("[오류]일정 이름이 중복되었음!");
			return;
		}
		
		sc.nextLine();
		
		System.out.printf("\n새로운 설명 입력 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("일정 수정 완료!");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.printf("\n[%s] %s - %s", item.getTitle(), item.getDesc(), item.getCurrent_date());
		}
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
				l.addItem(new TodoItem(str[0], str[1]));
			}
			
			System.out.printf("\n총 %d개의 일정을 읽어왔습니다!\n", i);
		}catch (FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}}}
