package com.qa.IMS.utils;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
	
	Scanner scan = new Scanner(System.in);
	ActorDAO actdao = new ActorDAO(); 
	
	public void start() {
		while(true) {
			System.out.println("CREATE(1) READ(2) UPDATE(3) DELETE(4) EXIT(5)");
			int select = scan.nextInt();
			scan.nextLine(); // capture the enter key 
			
			switch(select) {
			case 1: 
				create(); 
				break; 
			case 2:
				read(); 
				break;
			case 3: 
				update(); 
				break;
			case 4: 
				delete(); 
				break; 
			case 5: 
				System.out.println("Good bye");
				System.exit(0);
			}
		}
	}

	private void delete() {
		System.out.println("Enter an id");
		int id = scan.nextInt(); 
		actdao.delete(id);
	}

	private void update() {
		System.out.println("Enter an id");
		int id = scan.nextInt(); 
		scan.nextLine();
		System.out.println("First name? ");
		String firstName = scan.nextLine(); 
		System.out.println("Last name? ");
		String lastName = scan.nextLine();
		actdao.update(id, new Actor(firstName, lastName));
	}

	private void read() {
		System.out.println("Enter an ID ");
		int id = scan.nextInt(); 
		scan.nextLine();
		Actor a = null; 
		ResultSet rs = actdao.read(id);
		try {
			a = Actor.convert(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(a.toString());
		
	}

	private void create() {
		System.out.println("First name? ");
		String firstName = scan.nextLine(); 
		System.out.println("Last name? ");
		String lastName = scan.nextLine();
		actdao.create(new Actor(firstName, lastName));
	}
	
	
	

}