package Tp1;

import java.util.ArrayList;

public class Rule {

	Boolean state ;
	ArrayList<String>p;
	ArrayList<String>c;
	int num;
	public Rule (int num,ArrayList<String>p,ArrayList<String>c) {
		this.num=num;
		this.p=p;
		this.c=c;
		this.state=true;
	}
}
