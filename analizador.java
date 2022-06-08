package java_analizador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;

/**********************************************
La clase tipos proporciona una manera rapida de
Imprimir el tipo de la expresion y cuantos son
**********************************************/
class tipos
{
	private int cont;
	private String tipo;
	private String cualesSon;

	public tipos(int cont,String tipo,String cualesSon){
		setCont(cont);
		setTipo(tipo);
		setCualesSon(cualesSon);	
	}

	public void setCont(int cont){
		this.cont=cont;
	}

	public void setTipo(String tipo){
		this.tipo=tipo;
	}

	public void setCualesSon(String cualesSon){
		this.cualesSon=cualesSon;
	}

	public int getCont(){
		return cont;
	}

	public String getTipo(){
		return tipo;
	}

	public String getCualesSon(){
		return cualesSon;
	}

	public void verTipos(){
		System.out.println("Los " + getTipo() + " son " + getCont() + ":'"+getCualesSon() +"'");
	}
}
class lexer{

//Metodos que permitira hacer las operaciones de evaluacion de la expresiones

	private String reservadas[]={
		"main","end","public","class","if","for","while","else","do","switch","int","double","boolean","float","true","false","byte","char","null"
		}; 

	private Character opArit[]=  {
		'+','-','*','/','%'
		};
	private Character opRel[]=  {
		'<','>','='
		};
	private Character opLog[]=  {
		'!','|','&','^'
		};
	private Character sep[]=  {
			' ','\n','.',';',':',',','_',')','}'
			};
	public void instrucciones(){	
		System.out.println("**********************************************************");
		System.out.print(              "Analizador Lexico - Rommel Ojeda            \n");
		System.out.print(                   "Introduce las Expresion                \n");
		System.out.println("**********************************************************");
		System.out.println();
	}

	public Character[] leer(){
		String res="";
		int longitud=0;
		Character ch[];
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		try{
			System.out.print("Introduce la Expresion: ");
			do{
			res+=in.readLine();
			}while(!res.endsWith(" "));
		}catch(IOException ioe){
			System.out.println("Ocurrio la Sig. Excepcion " +ioe);
		}
		longitud=res.length();
		ch=new Character[longitud];
		for(int i=0;i<longitud;i++){
			ch[i]=res.charAt(i);
		}
	return ch;
	}

	//Digitos
	public void esDigito(Character ch[]){
		int cont=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			if(Character.isDigit(ch[i])){
				cont++;
				cualesSon+=ch[i].toString() + " ";
			}
		}
	tipos out=new tipos(cont,"Digitos",cualesSon);
	out.verTipos();
	}

	//Palabras Reservadas
	public void esReservada(Character ch[]){
		int ope=0;
		String cualesSon="";
		String reser[] = {"main","end","public","class","if","for","while","else","do","int","double","boolean","float","true",
		"false","byte","char","null"};
		for(int i=0;i<ch.length;i++){
			for(int j=0;j<reser.length;j++){
				if(ch[i].equals(reser[j])){
					ope++;
					cualesSon+=ch.toString() + " ";	
				}			
			}
		}
	tipos out=new tipos(ope,"Palabras Reservadas",cualesSon);
	out.verTipos();
	}

	//Operadores Aritmeticos
	public void esopAritmetico(Character ch[]){
		int ope=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			for(int j=0;j<opArit.length;j++){
				if(ch[i].equals(opArit[j])){
					ope++;
					cualesSon+=ch[i].toString() + " ";	
				}			
			}
		}
	tipos out=new tipos(ope,"Aritmericos",cualesSon);
	out.verTipos();
	}

	//Operadores Relacionales
	public void esopRelacional(Character ch[]){
		int ope=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			for(int j=0;j<opRel.length;j++){
				if(ch[i].equals(opRel[j])){
					ope++;
					cualesSon+=ch[i].toString() + " ";	
				}			
			}
		}
	tipos out=new tipos(ope,"Relacionales",cualesSon);
	out.verTipos();
	}

	//Operadores Logicos
	public void esopLogico(Character ch[]){
		int ope=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			for(int j=0;j<opLog.length;j++){
				if(ch[i].equals(opLog[j])){
					ope++;
					cualesSon+=ch[i].toString() + " ";	
				}			
			}
		}
	tipos out=new tipos(ope,"Logicos",cualesSon);
	out.verTipos();
	}

	//Separadores Genericos
	public void separadores(Character ch[]){
		int ope=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			for(int j=0;j<sep.length;j++){
				if(ch[i].equals(sep[j])){
					ope++;
					cualesSon+=ch[i].toString() + " ";	
				}			
			}
		}
	tipos out=new tipos(ope,"Separadores Genericos",cualesSon);
	out.verTipos();
	}

	//Identificador
	public void esIdentificador(Character ch[]){
		int Id=0;
		String cualesSon="";
		for(int i=0;i<ch.length;i++){
			if(Character.isJavaIdentifierStart(ch[i])){
				Id++;
				cualesSon+=ch[i].toString() + " ";
			}
		}
	tipos out=new tipos(Id,"Identificadores",cualesSon);
	out.verTipos();	
	}
}

class array{
	public static String[] funcionArray(Character[] in) {
		String palabra = "";
		int uS = 0;
		int c = 0;
		String[] array = new String[10];
		for (int i = uS; i < in.length; i++) {
			if (sepArray(in[i]) == true) {
				array[c] = palabra;
				c++;
				palabra = String.valueOf(in[i]);
				array[c] = palabra;
				uS = i;
				c++;
				palabra = "";
			} else {
				String palabr = String.valueOf(in[i]);
				palabra = palabra + palabr;
			}
	
		}
		array[c] = palabra;
		String[] clean = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			clean[i] = array[c];
		}
		return clean;
		}
	
	
		private static boolean sepArray(char c) {
			boolean encontrado = false;
			char sep[] = {' ','\n','.',';',':',',','_',')','}'};
			for (int i = 0; i < sep.length; i++) {
				if (sep[i] == c) {
	
					encontrado = true;
				} else {}
			return encontrado;
		}
		return false;
		}
}

class analizador{
	public static void main(String carls[]){	
		Character in[];
		lexer prueba=new lexer();
		prueba.instrucciones();
		in=prueba.leer();
		prueba.esIdentificador(in);
		prueba.esDigito(in);
		prueba.esReservada(in);
		prueba.esopAritmetico(in);
		prueba.esopRelacional(in);
		prueba.esopLogico(in);
		prueba.separadores(in);
	}
}
