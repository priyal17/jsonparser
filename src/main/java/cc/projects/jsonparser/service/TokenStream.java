package cc.projects.jsonparser.service;

import cc.projects.jsonparser.exception.JSONParserException;
import cc.projects.jsonparser.model.Token;
import cc.projects.jsonparser.model.TokenType;

public class TokenStream {
	
	
	Lexer lexer;
	Token currentToken;
	
	
	TokenStream(Lexer lexer) throws JSONParserException{
		this.lexer = lexer;
		currentToken = lexer.getToken();
	}
	
	
	Token peek() {
		return currentToken; 
	}
	
	
	Token consume(TokenType expected) throws JSONParserException {
		if(expected!=null && expected!=currentToken.getType()) throw new JSONParserException(
				"Expected token type : " + expected + 
				" token type recieved : " + currentToken.getType() + 
				" at position : " + currentToken.getPosition()) ;
		Token temp = currentToken;
		currentToken = lexer.getToken();
		return temp;
		
	}


	public Token consume() throws JSONParserException {
		// TODO Auto-generated method stub
		Token temp = currentToken;
		currentToken = lexer.getToken();
		return temp;
	}
	

}
