package ac.um.ds.TwoStackExpressionEvaluation;

import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

// Copyright (C) Kamaledin Ghiasi-Shirazi, Ferdowsi Univerity of Mashhad, 2014 (1393 Hijri Shamsi)
//
// Author: Kamaledin Ghiasi-Shirazi


public class ExpressionEvaluator<T> {
    public double evaluateExpression(String s, HashMap<String, Double> variableValues) throws Exception {
        double val;
        Vector<Token> inTokens = new Vector<Token>();
        Vector<Token> postTokens = new Vector<Token>();
        tokenizeString(s, inTokens);
        infix2Postfix(inTokens, postTokens);
        val = evaluatePostfixExpression(postTokens, variableValues);
        // postTokens are a rearrangement of inTokens. So, we don't delete them
        for (int i = 0; i < inTokens.size(); i++) {
            inTokens.remove(i);
        }

        return val;
    }


    public String infix2Postfix(String s) throws Exception {
        int i;
        String out;
        Vector<Token> inTokens = new Vector<Token>();
        Vector<Token> postTokens = new Vector<Token>();
        tokenizeString(s, inTokens);
        infix2Postfix(inTokens, postTokens);
        out = tokenSeq2String(postTokens);

        // postTokens are a rearrangement of inTokens. So, we don't delete them
        for (i = 0; i < inTokens.size(); i++) {
            inTokens.remove(i);
        }

        return out;
    }


    public void tokenizeString(String s, Vector<Token> out) throws Exception//*******************************************************************
    {
        int i;
        char[] ch = new char[s.length() + 1];
        StringBuilder ss = new StringBuilder();

        s = s.toLowerCase();

//		for (i = 0; i < s.length(); i++)//tolowercase
//		{
//                    ch[i]=s.charAt(i);
//                    if((65<=ch[i] && ch[i]<=90) && !(97<=ch[i] && ch[i]<=122))
//                        ch[i]+=32;
//
//		}

//		ch[s.length()]='\0';
        ss.append(s);
        ss.append('\0');

        Token token = null;
        token = getNextToken(ss, token);
        while (token != null) {

            out.add(token);
            token = getNextToken(ss, token);

        }
    }


    public String tokenSeq2String(Vector<Token> vt) {
        int i;
        String s = "";
        for (i = 0; i < vt.size(); i++) {
            s += ' ';
            s += vt.get(i).toText();
        }
        return s;
    }


    public double evaluatePostfixExpression(Vector<Token> s, HashMap<String, Double> variableValues) {
        double second = 0.0;
        double first = 0.0;
        Stack<Double> stack = new Stack<>();
        int counter = 0;
        for(int i=0; i <s.size();i++){
            Token token = s.get(counter);
            if(token instanceof VariableName){
                stack.push(variableValues.get(((VariableName) token).mName));
            }
            else if(token instanceof NumericConstant){
                stack.push(((NumericConstant) token).mValue);
            }
            else if(token instanceof MathFunction){
                first = stack.pop();
                switch(((MathFunction) token).mName){
                    case "cos":
                        stack.push(((CosFunc) token).evaluate(first,second));
                        break;
                    case "sin":
                        stack.push(((SinFunc) token).evaluate(first, second));
//                        System.out.println("sin evaluate: "+((SinFunc) token).evaluate(first, second));
                        break;
                    case "ln":
                        stack.push(((LnFunc) token).evaluate(first, second));
                        break;
                    case "exp":
                        stack.push(((ExpFunc) token).evaluate(first, second));
                        break;
                }
            }
            else if (token instanceof Operator){
                switch(((Operator) token).mName){
                    case "+":
                         second = stack.pop();
                         first = stack.pop();
                        stack.push(((PlusOperator) token).evaluate(first,second));
                        break;
                    case "-":
                         second = stack.pop();
                         first = stack.pop();
                        stack.push(((MinusOperator) token).evaluate(first, second));
                        break;
                    case "*":
                        second = stack.pop();
                        first = stack.pop();
                        stack.push(((MultiplicationOperator) token).evaluate(first, second));
                        break;
                      case "^":
                         second = stack.pop();
                         first = stack.pop();
//                         System.out.println("tavan first: "+first);
//                         System.out.println("tavan second: "+second);
                         stack.push(((PowerOperator) token).evaluate(first, second));
//                         System.out.println("evaluate power: "+((PowerOperator) token).evaluate(first, second));
                        break;
                    case "/":
                        second = stack.pop();
                        first = stack.pop();
//                        System.out.println("taghsim first: "+first);
//                         System.out.println("taghsim second: "+second);
                        stack.push(((DivisionOperator) token).evaluate(first, second));
                        break;
                    case "_":
                        first = stack.pop();
//                        System.out.println("manfi first: "+first);
                        stack.push(((UnaryMinusOperator) token).evaluate(first, second));
//                        System.out.println("evaluate manfi: "+((UnaryMinusOperator) token).evaluate(first, second));
                        break;
                } 
            }
            counter++;
        }
        return stack.pop();
    }


    public void infix2Postfix(Vector<Token> infixExpression, Vector<Token> postfixExpression) throws Exception {
        
        Stack<Token> stack = new Stack<>();
        int count = 0 ;
        for( int i =0; i<infixExpression.size();i++){
            Token token = infixExpression.get(count);
            if(token instanceof NumericConstant){
                postfixExpression.add(token);
            }
            else if(token instanceof VariableName){
                postfixExpression.add(token);
            }
            else if(token instanceof MathFunction){
                stack.push(token);
            }
            else if(token instanceof Operator){
                while (!stack.isEmpty() && stack.peek() instanceof Operator && 
                        ((((Operator) token).mAssociativity.equals(Operator.Associativity.ASSOC_LEFT) && ((Operator) token).mPrecedence <= ((Operator) stack.peek()).mPrecedence) ||
                        (((Operator) token).mAssociativity.equals(Operator.Associativity.ASSOC_RIGHT) && ((Operator) token).mPrecedence < ((Operator) stack.peek()).mPrecedence)))
                {
                try{
                   postfixExpression.add(stack.pop());
                }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                }
                stack.push(token);
            }
            else if(token instanceof LeftParanthesis){
                stack.push(token);
            }
            else if(token instanceof RightParanthesis){
                try {
                    while(!stack.isEmpty() && stack.peek() instanceof LeftParanthesis == false){
                        postfixExpression.add(stack.pop());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Mismatched parenthesis");
                }
                if(stack.isEmpty())
                    throw new RuntimeException("Mismatched parenthesis");
                stack.pop();
            }
            count++;
        }
        while(!stack.isEmpty()){
            if(stack.peek() instanceof LeftParanthesis || stack.peek() instanceof RightParanthesis ){
                throw new RuntimeException("Mismatched parenthesis");
            }
            postfixExpression.add(stack.pop());
        }
    }
  
  
    public Token getNextToken(StringBuilder ss, Token lastToken) throws Exception {
        int state = 0;
        int length = ss.length();

        Operator op;
        String tokenStr = "";
        char ch = ' ';
        String FinalString = "";
        Stack<Character> charStack = new Stack<Character>();
        boolean dotIsSeen = false;

        while (true) {
            ch = ss.charAt(0);
            ss.deleteCharAt(0);
            switch (state) {
                // Initial State
                case 0:
                    if (ch <= '9' && ch >= '0') {
                        tokenStr += ch;
                        state = 1;
                        dotIsSeen = false;
                        break;
                    }
                    if ((ch <= (byte) 'z' && ch >= (byte) 'a') || (ch <= (byte) 'Z' && ch >= (byte) 'A')) {
                        tokenStr += ch;
                        state = 2;
                        break;
                    } else {
                        switch (ch) {
                            case '(':
                                return new LeftParanthesis();
                            case ')':
                                return new RightParanthesis();
                            case '^':
                                return new PowerOperator();
                            case '+':
                                return new PlusOperator();
                            case '*':
                                return new MultiplicationOperator();
                            case '/':
                                return new DivisionOperator();
                            case '-':
                                if (lastToken == null || lastToken instanceof LeftParanthesis || (lastToken instanceof Operator) && ((op = (Operator) lastToken) != null)) {
                                    return new UnaryMinusOperator();
                                } else {
                                    return new MinusOperator();
                                }
                            case '.':
                                state = 1;
                                dotIsSeen = true;
                                break;
                            case '\0':
                                return null;
                            case 10:
                            case 13:
                            case 32:
                            case (int) ('\t'):
                                break;
                            default: {
                                String buff = "UnAllowed character No:     ";
                                buff += '0' + ch / 100;
                                buff += '0' + (ch % 100) / 10;
                                buff += '0' + ch % 10;
                                buff = buff.substring(0, 28);
                                throw new Exception(buff);
                            }
                        }
                    }
                    break;

                // Number
                case 1:
                    if (ch == '.') {
                        if (dotIsSeen) {
                            throw new Exception("Numeric string with two dots!");
                        } else {
                            dotIsSeen = true;
                        }

                    } else if (ch > '9' || ch < '0') {
                        double val;
                        //ss.putback(ch);**********
                        ss.insert(0, ch);
                        val = Double.parseDouble(tokenStr);
                        return new NumericConstant(val);
                    }

                    tokenStr += (char) ch;
                    break;

                // String
                case 2:
                    if ((ch <= (byte) 'z' && ch >= (byte) 'a') || (ch <= (byte) 'Z' && ch >= (byte) 'A') || (ch <= '9' && ch >= '0'))
                        /*|| (ch=='_')*/ {
                        tokenStr += (char) ch;
                    } else {
                        //ss.putback(ch);*******
                        ss.insert(0, ch);
                        if (tokenStr.equals("sin")) {
                            return new SinFunc();
                        } else if (tokenStr.equals("cos")) {
                            return new CosFunc();
                        } else if (tokenStr.equals("exp")) {
                            return new ExpFunc();
                        } else if (tokenStr.equals("ln")) {
                            return new LnFunc();
                        } else {
                            return new VariableName(tokenStr);
                        }
                    }
                    break;
            }
        }
    }
}