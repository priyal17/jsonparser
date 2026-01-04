package cc.projects.jsonparser.model;

public class Token {
	
	private final TokenType type;
	final int position;
	final String value;
	
	public Token(TokenType type, int position, String value){
		this.position = position;
		this.type = type;
		this.value = value;
	}
	
	
	public int getPosition() {
		return position;
	}


	public String getValue() {
		return value;
	}


	public String toString() {
		return "Token type : " + getType() + " position : " + position + " value : " + value;
	}


	public TokenType getType() {
		return type;
	}
	
	
	

}
