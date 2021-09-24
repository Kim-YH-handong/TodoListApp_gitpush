package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		TodoUtil.loadList(l, "todolist.txt");
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
			
			isList = false;
			String choice = sc.nextLine();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "asc":
				l.sortByName();
				isList = true;
				break;

			case "desc":
				l.sortByName();
				l.reverseList();
				isList = true;
				break;
				
			case "date":
				l.sortByDate();
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				isList = true;
				break;
				
			case "ls_cate":
				TodoUtil.countCategory(l);
				break;
				
			case "help":
				Menu.displaymenu();
				break;

			case "exit":
				TodoUtil.saveList(l, "todolist.txt");
				quit = true;
				break;
				
			default:
				if(choice.length() > 3) {
					if(choice.length()>8) {
						if(choice.substring(0, 9).equals("find_cate")) {
							TodoUtil.findCate(l, choice.substring(9, choice.length()));
							break;
						}else {
							System.out.println("위에 언급된 명령어를 입력해주세요(help - 도움말)");
						}
					}else if(choice.substring(0 ,4).equals("find")){
						TodoUtil.findList(l, choice.substring(4, choice.length()));
						break;
					}else{
						System.out.println("위에 언급된 명령어를 입력해주세요(help - 도움말)");
					}
				}else{
					System.out.println("위에 언급된 명령어를 입력해주세요(help - 도움말)");					
				}
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	}
}