package HW1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * »ç¿ë Software ¹× Tool
 * Java
 * - OS : Windows 10
 * - Compiler : java version "1.8.0_91"
 * 				Java(TM) SE Runtime Environment (build 1.8.0_91-b15)
 * C
 * - OS : ubuntu-14.04.3
 * - Compiler : gcc version 4.8.4 (Ubuntu 4.8.4-2ubuntu1~14.04.3) 
 */

public class Solution
{
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(new FileInputStream("test.ad"));

		FileWriter file = new FileWriter("test.c");
		file.write("#include <stdio.h>" + "\n");
		file.write("int main(){ " + "\n");

		String token;
		String listName = null;
		String resultName = null;
		
		while(sc.hasNextLine()){
			StringTokenizer str = new StringTokenizer(sc.nextLine());
			
			token = pureToken(str);

			if(CheckDef(token)){
				listName = pureToken(str);
				
				file.write("\t" +"long " + listName + "[] = {");
				int listSize = str.countTokens();
				
				for(int i=0; i < listSize; i++){
					file.write(pureToken(str));
				}
				
				file.write("};" + "\n");
				file.write("\t" +"int _AD_size = " + listSize + ";" + "\n");
			}
			if(CheckReduce(token)){
				
				String init_AD_i;
				
				for(int i=1; i <= str.countTokens(); i++){
					init_AD_i = pureToken(str);
					
					if(init_AD_i.equals("+")){
						init_AD_i = pureToken(str);
						resultName = pureToken(str);
						file.write("\t" +"int _AD_i = 0;\n");
						file.write("\t" +"long int " + resultName + " = " + init_AD_i + ";\n\n");
						file.write("\t" +"for (_AD_i = 0; _AD_i < _AD_size; _AD_i++) { " + 
								"\t" + "\n" + "\t\t" + resultName + " += " + listName + "[_AD_i];" + "\n"+ "\t" + "}" + "\n\n");
						break;
					}
					if(init_AD_i.equals("*")){
						init_AD_i = pureToken(str);
						resultName = pureToken(str);
						file.write("\t" +"int _AD_i = 0;\n");
						file.write("\t" +"long int " + resultName + " = " + init_AD_i + ";\n\n");
						file.write("\t" +"for (_AD_i = 0; _AD_i < _AD_size; _AD_i++) { " + 
								"\t" + "\n" + "\t\t" + resultName + " *= " + listName + "[_AD_i];" + "\n"+ "\t" + "}" + "\n\n");
						break;
					}
				}
			}
			if(CheckPrint(token)){
				file.write("\t" +"printf(" + '"' +"%ld"+ "\\" + "n" + '"' + ", " + resultName + ");" +"\n\n" );
				file.write("\t" +"return 0;" + "\n");
			}
			
		}
		
		file.write("}");
		file.close();
		sc.close();
	}

	private static String pureToken(StringTokenizer str)
	{
		String element;
		element = str.nextToken();
		element = element.replace("(", "");
		element = element.replace("[", "");
		element = element.replace("]", "");
		element = element.replace(")", "");
		return element;
	}

	private static boolean CheckPrint(String str)
	{
		if(str.equals("print")){
			return true;
		}
		return false;
	}

	private static boolean CheckReduce(String str)
	{
		if(str.equals("reduce")){
			return true;
		}
		return false;
	}

	private static boolean CheckDef(String str)
	{
		if(str.equals("def")){
			return true;
		}
		return false;
	}

}
