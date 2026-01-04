package cc.projects.jsonparser.service;

import cc.projects.jsonparser.exception.JSONParserException;
import cc.projects.jsonparser.model.Token;
import cc.projects.jsonparser.model.TokenType;

public class Lexer {

	private final String input;
	private int index;
	
	Lexer(String input){
		this.input = input;
		this.index = 0;
	}
	
	
	
	Token getToken() throws JSONParserException {
		
		
		skipWhiteSpace();
		
		
		if(index < input.length()) {
			char ch = input.charAt(index);
			
			
			switch(ch) {
				case '{': return new Token(TokenType.LEFT_BRACE, index++, null);
				case '}': return new Token(TokenType.RIGHT_BRACE, index++, null);
	            case '[': return new Token(TokenType.LEFT_BRACKET, index++, null);
	            case ']': return new Token (TokenType.RIGHT_BRACKET, index++, null);	
	            
	            case ':': return new Token(TokenType.COLON, index++, null);
				case ',': return new Token(TokenType.COMMA, index++, null);
	            case '"': return stringToken();
	            case 't': return keyword("true", TokenType.TRUE);
	            case 'f': return keyword("false", TokenType.FALSE);
	            case 'n': return keyword("null", TokenType.NULL);
	            default :
	            	if(isNumberChar(ch)) return numberToken();
	            	else throw new JSONParserException("Unexpected character: " + ch);
	            
	            
			}
		}
		else return new Token(TokenType.EOF, index, null);
	}
	
	
	
	private boolean isNumberChar(char ch) {
		
		return (ch - '0' >= 0 && ch - '0' <=9);
	}



	private Token numberToken() {
		
		int number = 0;
		
		int position = index;
		
		while(index < input.length() && isNumberChar(input.charAt(index))) {
			number = (number*10) + (input.charAt(index) - '0');
			index++;
		}
		
		return new Token(TokenType.NUMBER, position, new StringBuilder().append(number).toString());
	}



	private Token keyword(String word, TokenType type) throws JSONParserException {
		// TODO Auto-generated method stub
		if(input.startsWith(word, index)) {
			int start = index;
			index+= word.length();
			return new Token(type, start, word);
		}
		throw new JSONParserException("Invalid keyword at position : " + index);
	}



	private Token stringToken() throws JSONParserException {

		index++;
		
		StringBuilder sb = new StringBuilder();
		int position = index;
		
		while(index < input.length()) {
			char ch = input.charAt(index);
			
			if(ch == '"') {
				index++;
				return new Token(TokenType.STRING, position, sb.toString());
			}
			
			else if(ch == '\\') {
				index++;
				sb.append(getEscapeChar());
			}
			else {
				sb.append(ch);
				
			}
			index++;
		}
		
		throw new JSONParserException("Invalid unterminated String at : " + position);
	}



	private char getEscapeChar() throws JSONParserException {
		if(index < input.length()) {
			char ch = input.charAt(index);
			switch(ch) {
				case 'n': return '\n';
				case 't': return '\t';
				case '\\': return '\\';
				case '"' : return '"';
				default : throw new JSONParserException("Invalid escape character at: " + index);
			}
		}
		else throw new JSONParserException("Invalid escape character at: " + index);
	}



	public void skipWhiteSpace() {
		while(index < input.length() && Character.isWhitespace(input.charAt(index))) index++;
	}
	
	
	
	
	
}
