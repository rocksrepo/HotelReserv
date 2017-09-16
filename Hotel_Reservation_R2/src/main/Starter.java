package main;

import gui.Login;

public class Starter {
	
	public static boolean name(int num) {
		return (num&1)!=0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login().setVisible(true);
	}

}
