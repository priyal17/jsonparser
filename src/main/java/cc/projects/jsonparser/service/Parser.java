package cc.projects.jsonparser.service;

import cc.projects.jsonparser.exception.JSONParserException;
import cc.projects.jsonparser.model.TokenType;


public class Parser {

	TokenStream tokenStream;
	
	public Parser(String input) throws JSONParserException{
		
		tokenStream = new TokenStream(new Lexer(input));
	}
	
	
	public int parse() throws JSONParserException  {
		
			parseObject();
			return 0;
		
		
	}


	private void parseObject() throws JSONParserException {
		// TODO Auto-generated method stub
		tokenStream.consume(TokenType.LEFT_BRACE);
		if(tokenStream.peek().getType()!= TokenType.RIGHT_BRACE)
			parseEntry();
		tokenStream.consume(TokenType.RIGHT_BRACE);

	}


	private void parseEntry() throws JSONParserException {
		// TODO Auto-generated method stub
		tokenStream.consume(TokenType.STRING).getValue();
		tokenStream.consume(TokenType.COLON);
		parseValue();
		if(tokenStream.peek().getType() == TokenType.COMMA) {
			tokenStream.consume();
			parseEntry();
			
		}
		else {
			return;
		}
		
	}


	private void parseValue() throws JSONParserException {
		switch (tokenStream.peek().getType()) {
        case LEFT_BRACE -> parseObject();
        case LEFT_BRACKET -> parseArray();
        case STRING -> tokenStream.consume();
        case NUMBER -> tokenStream.consume();
        case TRUE ->  tokenStream.consume(); 
        case FALSE ->  tokenStream.consume(); 
        case NULL ->  tokenStream.consume(); 
        default -> throw new JSONParserException("Invalid JSON value at : " + tokenStream.peek().getPosition() + " "+ tokenStream.peek().getType());
    };
		
	}


	private void parseArray() throws JSONParserException {
		
		tokenStream.consume(TokenType.LEFT_BRACKET);

        if (tokenStream.peek().getType() == TokenType.RIGHT_BRACKET) {
            tokenStream.consume();
           return;
        }

        while (true) {
            parseValue();
            if (tokenStream.peek().getType() == TokenType.COMMA) {
                tokenStream.consume();
            } else break;
        }

        tokenStream.consume(TokenType.RIGHT_BRACKET);
     
	}
	

}
