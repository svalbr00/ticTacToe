package es.unileon.prg1.ticTacToe;

public class Player {
	
	private String name;
	private Mark mark;
	
	public Player(String name, Mark mark){
		this.name = name;
		this.mark = mark;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Mark getMark(){
		return this.mark;
	}
	
	public String toString(){
		return this.name;
	}
	
}
