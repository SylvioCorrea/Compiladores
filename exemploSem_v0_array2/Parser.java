//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 3 "exemploSem.y"
  import java.io.*;
  import java.util.ArrayList;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short IDENT=257;
public final static short INT=258;
public final static short DOUBLE=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short STRING=262;
public final static short NUMDOUBLE=263;
public final static short LITERAL=264;
public final static short AND=265;
public final static short VOID=266;
public final static short MAIN=267;
public final static short IF=268;
public final static short DSTRUCT=269;
public final static short STRUCT=270;
public final static short FUNCTION=271;
public final static short RETURN=272;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    8,    0,    7,    7,   10,   10,   10,   14,   17,   11,
    2,    2,    2,   15,   15,   19,   19,   20,   20,   16,
   16,   18,   18,   21,   21,   24,   25,   12,   27,   13,
   29,   13,   23,   23,   28,   28,   30,   31,   26,   26,
    1,    1,    1,    9,   32,   33,   33,   22,   22,    3,
    3,    3,    3,    3,    3,    3,   34,    3,    3,    3,
    3,   35,    6,    6,    4,    4,    5,    5,
};
final static short yylen[] = {                            2,
    0,    3,    2,    0,    1,    1,    1,    0,    0,   12,
    1,    1,    1,    1,    0,    3,    1,    2,    3,    2,
    0,    2,    0,    1,    3,    0,    0,    8,    0,    5,
    0,    5,    2,    1,    3,    1,    1,    0,    5,    0,
    1,    1,    1,    5,    3,    2,    0,    2,    7,    3,
    3,    3,    1,    1,    3,    1,    0,    4,    4,    3,
    4,    0,    4,    1,    1,    0,    3,    1,
};
final static short yydefred[] = {                         1,
    0,    0,   41,   42,   43,    0,    0,    0,   29,    0,
    0,    5,    6,    7,    0,   31,   13,   11,   12,    0,
    0,    0,    2,    3,   26,    0,    8,    0,    0,    0,
    0,   37,    0,   36,    0,    0,    0,    0,   34,    0,
    0,   32,    0,   38,   30,    0,   33,    0,   35,    0,
    0,    0,    0,   17,    0,   47,   44,    0,    0,   18,
    0,    0,   39,    0,   28,   19,    9,   16,    0,   53,
   54,    0,    0,   45,    0,   46,    0,    0,   57,    0,
    0,    0,    0,    0,    0,    0,   48,    0,   23,    0,
    0,    0,    0,    0,   55,    0,    0,    0,    0,    0,
   20,    0,   59,    0,    0,   58,    0,   61,    0,   10,
   22,   24,    0,   62,    0,    0,    0,    0,   25,   63,
   49,
};
final static short yydgoto[] = {                          1,
    9,   20,   75,   91,   92,  106,   10,    2,   23,   11,
   12,   13,   14,   35,   52,   89,   77,  102,   53,   54,
  111,   76,   40,   31,   48,   29,   21,   33,   26,   34,
   55,   57,   64,   93,  117,
};
final static short yysindex[] = {                         0,
    0, -132,    0,    0,    0, -250, -242, -106,    0, -234,
 -132,    0,    0,    0,  -85,    0,    0,    0,    0, -218,
  -44, -216,    0,    0,    0, -200,    0, -202, -200,   27,
 -128,    0,  -33,    0,   29,  -22,   59,   39,    0, -128,
 -200,    0, -111,    0,    0,  -35,    0,  -20,    0, -168,
 -155,   66,   64,    0,  -44,    0,    0,   50, -141,    0,
    6, -111,    0,  -27,    0,    0,    0,    0,  -36,    0,
    0,   84,   -7,    0,   -1,    0, -128,   -7,    0,   -7,
  -21,   -7,   -7,   -7,   -7,   -7,    0, -128,    0,   21,
   93,   91, -121,  -12,    0,   49,   21,  -13,  -89,    1,
    0,  -40,    0,   -7,   97,    0,   33,    0,   -7,    0,
    0,    0,   21,    0,  -26,   13, -121,   19,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0, -116,    0,    0,    0,    0,    0,    0,    0,    0,
 -116,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -102,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   36,
    0,    0,  116,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  117,    0, -102,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -38,    0,
    0,    0,    0,    0,    0,    0,  -39,  121,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -39,    0,  -32,
    0,  122,    0,    0,    0,  -16,   32,   40,   52,    0,
    0,    0,    0,    0,  -25,    0,    0,    0,    0,    0,
    0,    0,   56,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
   44,    0,   37,    0,    0,   47,  154,    0,    0,    0,
    0,    0,  -23,    0,    0,   78,    0,    0,    0,  105,
    0,  -67,    0,    0,    0,  113,    0,  140,    0,  129,
    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=286;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         73,
   21,   86,   56,   78,   56,   56,   15,   39,   68,   79,
   41,   68,   73,   73,   16,   64,   47,   64,   64,   95,
   56,   85,   56,   56,   52,   42,   52,   52,  107,   85,
   85,   22,   73,   64,  112,   64,   64,   25,   27,   83,
   84,   85,   52,   85,   52,   52,   28,  118,   83,   84,
   30,   19,   56,   88,   56,   85,   32,   87,   36,   83,
   84,   83,   84,   85,   88,   64,   38,   64,   43,   86,
   44,  119,   60,   83,   84,   60,   52,   86,   86,   46,
   51,   83,   84,   51,  110,   21,   51,   56,   59,   86,
   60,   86,   50,  108,   50,   50,   67,   74,   51,   67,
   51,   60,   41,   86,   58,   51,   61,   62,   65,   81,
   50,   86,   50,   50,   90,   66,   94,   45,   96,   97,
   98,   99,  100,   80,   60,    3,    4,    5,   67,    3,
    4,    5,   51,  103,  104,  105,    6,    7,    8,   86,
  113,    7,  114,  121,   50,  116,    3,    4,    5,    4,
   17,    3,    4,    5,   40,  115,   15,   14,   50,   18,
   27,   66,   65,  120,   24,  101,   68,   63,   37,   49,
    0,    0,    0,    0,    0,   82,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   69,   21,    0,    0,
   70,   21,   71,   21,    0,    0,   56,   72,   21,   69,
   69,  109,   21,   70,   70,   71,   71,    0,    0,   64,
   72,   72,    0,   82,    0,    0,    0,    0,   52,   69,
    0,   82,   82,   70,    0,   71,    0,    0,    0,    0,
    0,    0,    0,   82,    0,   82,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   82,    0,    0,
    0,    0,    0,    0,    0,   82,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   40,   91,   41,   40,   43,   44,  257,   31,   41,   46,
   44,   44,   40,   40,  257,   41,   40,   43,   44,   41,
   59,   43,   61,   62,   41,   59,   43,   44,   41,   43,
   43,  266,   40,   59,  102,   61,   62,  123,  257,   61,
   62,   43,   59,   43,   61,   62,   91,  115,   61,   62,
  267,    8,   91,   77,   93,   43,  257,   59,  261,   61,
   62,   61,   62,   43,   88,   91,   40,   93,   40,   91,
   93,   59,   41,   61,   62,   44,   93,   91,   91,   41,
   41,   61,   62,   44,  125,  125,   43,  123,  257,   91,
   59,   91,   41,   93,   43,   44,   41,  125,   59,   44,
   61,  257,   44,   91,  125,   62,   41,   44,   59,   73,
   59,   91,   61,   62,   78,  257,   80,   59,   82,   83,
   84,   85,   86,   40,   93,  258,  259,  260,  123,  258,
  259,  260,   93,   41,   44,  257,  269,  270,  271,   91,
  104,  270,   46,  125,   93,  109,  258,  259,  260,  266,
  257,  258,  259,  260,  257,  123,   41,   41,  270,  266,
  125,   41,   41,  117,   11,   88,   62,   55,   29,   41,
   -1,   -1,   -1,   -1,   -1,  265,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,  257,   -1,   -1,
  261,  261,  263,  263,   -1,   -1,  265,  268,  268,  257,
  257,  272,  272,  261,  261,  263,  263,   -1,   -1,  265,
  268,  268,   -1,  265,   -1,   -1,   -1,   -1,  265,  257,
   -1,  265,  265,  261,   -1,  263,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  265,   -1,  265,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  265,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=272;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'",null,"'+'","','",
null,"'.'",null,null,null,null,null,null,null,null,null,null,null,null,"';'",
null,"'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"IDENT","INT","DOUBLE","BOOL","NUM",
"STRING","NUMDOUBLE","LITERAL","AND","VOID","MAIN","IF","DSTRUCT","STRUCT",
"FUNCTION","RETURN",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList main",
"dList : decl dList",
"dList :",
"decl : defFunc",
"decl : defStruct",
"decl : declVar",
"$$2 :",
"$$3 :",
"defFunc : FUNCTION funcType IDENT $$2 '(' maybeParams ')' '{' $$3 funcDecList funcCmdList '}'",
"funcType : VOID",
"funcType : type",
"funcType : IDENT",
"maybeParams : lParams",
"maybeParams :",
"lParams : lParams ',' param",
"lParams : param",
"param : type IDENT",
"param : STRUCT IDENT IDENT",
"funcDecList : declVar funcDecList",
"funcDecList :",
"funcCmdList : funcCmdList funcCmd",
"funcCmdList :",
"funcCmd : cmd",
"funcCmd : RETURN exp ';'",
"$$4 :",
"$$5 :",
"defStruct : DSTRUCT IDENT '{' $$4 lcampos $$5 '}' ';'",
"$$6 :",
"declVar : type $$6 TArray Lid ';'",
"$$7 :",
"declVar : STRUCT IDENT $$7 Lid ';'",
"lcampos : lcampos declVar",
"lcampos : declVar",
"Lid : Lid ',' id",
"Lid : id",
"id : IDENT",
"$$8 :",
"TArray : '[' NUM ']' $$8 TArray",
"TArray :",
"type : INT",
"type : DOUBLE",
"type : BOOL",
"main : VOID MAIN '(' ')' bloco",
"bloco : '{' listacmd '}'",
"listacmd : listacmd cmd",
"listacmd :",
"cmd : exp ';'",
"cmd : IF '(' exp ')' '{' cmd '}'",
"exp : exp '+' exp",
"exp : exp '>' exp",
"exp : exp AND exp",
"exp : NUM",
"exp : NUMDOUBLE",
"exp : '(' exp ')'",
"exp : IDENT",
"$$9 :",
"exp : IDENT '.' $$9 restoStruct",
"exp : IDENT '(' maybeExp ')'",
"exp : exp '=' exp",
"exp : exp '[' exp ']'",
"$$10 :",
"restoStruct : IDENT '.' $$10 restoStruct",
"restoStruct : IDENT",
"maybeExp : lExp",
"maybeExp :",
"lExp : lExp ',' exp",
"lExp : exp",
};

//#line 361 "exemploSem.y"

  private Yylex lexer;

  private TabSimb ts;
  private TabSimb auxTs;
  private TabSimb nestedStructTs; //quando consultando nested structs, guarda o escopo que estava sendo utilizado
  
  private TS_entry returnType;
  
  private TS_entry nodoAux;

  public static TS_entry Tp_INT =  new TS_entry("int", null, ClasseID.TipoBase);
  public static TS_entry Tp_DOUBLE = new TS_entry("double", null,  ClasseID.TipoBase);
  public static TS_entry Tp_BOOL = new TS_entry("bool", null,  ClasseID.TipoBase);
  
  public static TS_entry Tp_VOID = new TS_entry("void", null, ClasseID.TipoBase);

  public static TS_entry Tp_ARRAY = new TS_entry("array", null,  ClasseID.TipoBase);

  public static TS_entry Tp_ERRO = new TS_entry("_erro_", null,  ClasseID.TipoBase);
  
  public static TS_entry Tp_STRUCT = new TS_entry("struct", null,  ClasseID.TipoBase);
  
  public static final int ARRAY = 1500;
  public static final int ATRIB = 1600;

  private String currEscopo;
  private ClasseID currClass;

  private TS_entry currentType;

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    //System.err.println("Erro (linha: "+ lexer.getLine() + ")\tMensagem: "+error);
    System.err.printf("Erro (linha: %2d) Mensagem: %s\n", lexer.getLine(), error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);

    ts = new TabSimb();
    auxTs = ts;

    //
    // não me parece que necessitem estar na TS
    // já que criei todas como public static...
    //
    ts.insert(Tp_ERRO);
    ts.insert(Tp_INT);
    ts.insert(Tp_DOUBLE);
    ts.insert(Tp_BOOL);
    ts.insert(Tp_ARRAY);
    ts.insert(Tp_STRUCT);
    

  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {
    System.out.println("\n\nVerificador semantico simples\n");
    

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Programa de entrada:\n");
        yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();

      yyparser.listarTS();

      System.out.print("\n\nFeito!\n");
    
  }


   TS_entry validaTipo(int operador, TS_entry A, TS_entry B) {
       
         switch ( operador ) {
              case ATRIB:
                    if ( (A == Tp_INT && B == Tp_INT)                        ||
                         ((A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE))) ||
                         (A == B) )
                         return A;
                     else
                         yyerror("(sem) tipos incomp. para atribuicao: "+ A.getTipoStr() + " = "+B.getTipoStr());
                    break;

              case '+' :
                    if ( A == Tp_INT && B == Tp_INT)
                          return Tp_INT;
                    else if ( (A == Tp_DOUBLE && (B == Tp_INT || B == Tp_DOUBLE)) ||
                                            (B == Tp_DOUBLE && (A == Tp_INT || A == Tp_DOUBLE)) ) 
                         return Tp_DOUBLE;     
                    else
                        yyerror("(sem) tipos incomp. para soma: "+ A.getTipoStr() + " + "+B.getTipoStr());
                    break;

             case '>' :
                     if ((A == Tp_INT || A == Tp_DOUBLE) && (B == Tp_INT || B == Tp_DOUBLE))
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op relacional: "+ A.getTipoStr() + " > "+B.getTipoStr());
                      break;

             case AND:
                     if (A == Tp_BOOL && B == Tp_BOOL)
                         return Tp_BOOL;
                      else
                        yyerror("(sem) tipos incomp. para op lógica: "+ A.getTipoStr() + " && "+B.getTipoStr());
                 break;
            }

            return Tp_ERRO;
           
     }

//#line 490 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 30 "exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 8:
//#line 40 "exemploSem.y"
{	
     			TS_entry temp = ts.pesquisa(val_peek(0).sval);
     			if(temp!=null) {
     				yyerror("(sem) nome >" + val_peek(0).sval + "< jah declarado");
     			} else {
     				TabSimb escopoFuncao = new TabSimb();
     				temp = new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.NomeFuncao);
     				temp.setLocais(escopoFuncao);
     				ts.insert(temp);
     				auxTs = ts;
     				ts = escopoFuncao; /*tabela de simbolos da funcao*/
     			}
     	    }
break;
case 9:
//#line 53 "exemploSem.y"
{currClass = ClasseID.VarLocal; returnType = (TS_entry)val_peek(6).obj;}
break;
case 10:
//#line 55 "exemploSem.y"
{
                ts = auxTs; /*tabela de simbolos global novamente*/
                currClass = ClasseID.VarGlobal;
            }
break;
case 11:
//#line 61 "exemploSem.y"
{yyval.obj = Tp_VOID;}
break;
case 13:
//#line 64 "exemploSem.y"
{
                TS_entry temp = ts.pesquisa(val_peek(0).sval);
                if(temp==null) {
                    yyerror("(sem) nao existe tipo >"+val_peek(0).sval+"<");
                } else {
                    yyval.obj = temp;
                }
            }
break;
case 18:
//#line 84 "exemploSem.y"
{
                TS_entry temp = ts.pesquisa(val_peek(0).sval);
                if(temp!=null) {
                    yyerror("(sem) nome >" + val_peek(0).sval + "< jah declarado");
                } else {
                    temp = new TS_entry(val_peek(0).sval, (TS_entry)val_peek(1).obj, ClasseID.NomeParam);
                    ts.insert(temp);
                }
            }
break;
case 19:
//#line 94 "exemploSem.y"
{
                TS_entry temp = ts.pesquisa(val_peek(0).sval); /*procura nome*/
                if(temp!=null) {
                    yyerror("(sem) nome >" + val_peek(0).sval + "< jah declarado");
                } else {
                    TS_entry structType = auxTs.pesquisa(val_peek(1).sval); /*procura tipo da struct nas declaracoes globais*/
                    if(structType==null || structType.getClasse()!=ClasseID.NomeStruct) {
                        yyerror("(sem) >"+val_peek(1).sval+"< nao eh um tipo de struct.");
                        structType = Tp_ERRO;
                    }
                    temp = new TS_entry(val_peek(1).sval, structType, ClasseID.NomeParam);
                    ts.insert(temp);
                }
            }
break;
case 25:
//#line 122 "exemploSem.y"
{
                if(returnType != (TS_entry)val_peek(1).obj) {
                    yyerror("(sem) tipo de retorno incompativel com o tipo declarado da funcao");
                }
            }
break;
case 26:
//#line 130 "exemploSem.y"
{
                    nodoAux = ts.pesquisa(val_peek(1).sval);
                    if (nodoAux != null) {
                        yyerror("(sem) nome >" + val_peek(1).sval + "< jah declarado");
                    } else {
                        nodoAux = new TS_entry(val_peek(1).sval, Tp_STRUCT, ClasseID.NomeStruct);
                        ts.insert(nodoAux);
                        auxTs = ts;
                        ts = new TabSimb();
                        currClass = ClasseID.CampoStruct;
                    }
                }
break;
case 27:
//#line 143 "exemploSem.y"
{
                    nodoAux.setLocais(ts);
                    ts = auxTs;
                    currClass = ClasseID.VarGlobal;
                }
break;
case 29:
//#line 151 "exemploSem.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 31:
//#line 156 "exemploSem.y"
{
                currentType = auxTs.pesquisa(val_peek(0).sval);
                if(currentType==null || currentType.getClasse()!=ClasseID.NomeStruct) {
                    yyerror("(sem) nao existe struct do tipo >" + val_peek(0).sval + "<.");
                    currentType = Tp_ERRO;
                }
            }
break;
case 37:
//#line 179 "exemploSem.y"
{
            TS_entry nodo = ts.pesquisa(val_peek(0).sval);
            if (nodo != null) {
                yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarado");
            } else {
                ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass));
            }
        }
break;
case 38:
//#line 189 "exemploSem.y"
{ currentType = new TS_entry("?", Tp_ARRAY, 
                                                   currClass, val_peek(1).ival, currentType); }
break;
case 41:
//#line 199 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 42:
//#line 200 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 43:
//#line 201 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 49:
//#line 215 "exemploSem.y"
{  if ( ((TS_entry)val_peek(4).obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(4).obj).getTipo());
                             }
break;
case 50:
//#line 221 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 51:
//#line 222 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 52:
//#line 223 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 53:
//#line 224 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 54:
//#line 225 "exemploSem.y"
{yyval.obj = Tp_DOUBLE;}
break;
case 55:
//#line 226 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 56:
//#line 227 "exemploSem.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
                    if (nodo == null) {
                       nodo = auxTs.pesquisa(val_peek(0).sval);
                       if(nodo == null) {
                           yyerror("(sem) var <" + val_peek(0).sval + "> nao declarada"); 
                           nodo = Tp_ERRO;   
                       }
                    }
                    yyval.obj = nodo.getTipo();
                  }
break;
case 57:
//#line 239 "exemploSem.y"
{
                TS_entry temp = ts.pesquisa(val_peek(1).sval);
                if(temp==null || temp.getTipo().getClasse()!=ClasseID.NomeStruct) {
                    yyerror("(sem) nao existe campo de nome >"+val_peek(1).sval+"<");
                    yyval.obj = Tp_ERRO;
                } else {
                    nestedStructTs = ts;
                    ts = temp.getTipo().getLocais();
                }
            }
break;
case 58:
//#line 250 "exemploSem.y"
{
                    ts = nestedStructTs;
                    yyval.obj = (TS_entry)val_peek(1).obj;
                    
                    
                    /*
                    TS_entry nodo = ts.pesquisa($1);
                    if (nodo == null) {
                       nodo = auxTs.pesquisa($1);
                    }
                    if(nodo == null || nodo.getTipo().getTipo()!=Tp_STRUCT) {
                        yyerror("(sem) var <" + $1 + "> nao declarada");
                    } else {
                        nodo = nodo.getTipo().getLocais().pesquisa($3);
                    }
                    if(nodo == null) {
                        nodo = Tp_ERRO;
                    } else {
                        nodo = nodo.getTipo();
                    }
                    
                    $$ = nodo;
                    */
                }
break;
case 59:
//#line 276 "exemploSem.y"
{
                        TS_entry temp = auxTs.pesquisa(val_peek(3).sval);
                        boolean ok = false;
                        if(temp!=null && temp.getClasse()==ClasseID.NomeFuncao) {
                            ArrayList<TS_entry> paramList = temp.getParams();
                            ArrayList<TS_entry> arguments = (ArrayList<TS_entry>)val_peek(1).obj;
                            int parSize = paramList.size();
                            int argSize = arguments.size();
                            if(parSize==argSize) {
                                ok = true;
                                for(int i = 0; i<parSize; i++) {
                                    if(paramList.get(i).getTipo()!=arguments.get(i)) {
                                        /*Uma das expressoes tem tipo diferente do aceito pelo parametro*/
                                        yyerror("(sem) tipo de expressao incompativel com tipo de parametro");
                                        ok = false;
                                        break;
                                    }
                                }
                            } else {
                                yyerror("(sem) a funcao requer "+parSize+" parametros, mas esta recebendo "+argSize);
                            }
                        } else {
                            yyerror("(sem) nao ha funcao com o nome >"+val_peek(3).sval+"<");
                        }
                        if(ok) {
                            yyval.obj = temp.getTipo();
                        } else {
                            yyval.obj = Tp_ERRO;
                        }
                    }
break;
case 60:
//#line 306 "exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 61:
//#line 307 "exemploSem.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
case 62:
//#line 318 "exemploSem.y"
{
                    TS_entry temp = ts.pesquisa(val_peek(1).sval);
                    if(temp==null || temp.getTipo().getClasse()!=ClasseID.NomeStruct) {
                        yyerror("(sem) nao existe campo de nome >"+val_peek(1).sval+"< ou este campo nao eh um struct");
                        yyval.obj = Tp_ERRO;
                    } else {
                        ts = temp.getTipo().getLocais();
                    }
                }
break;
case 63:
//#line 327 "exemploSem.y"
{yyval.obj = (TS_entry)val_peek(1).obj;}
break;
case 64:
//#line 330 "exemploSem.y"
{
                    TS_entry temp = ts.pesquisa(val_peek(0).sval);
                    if(temp==null) {
                        yyerror("(sem) nao existe campo de nome >"+val_peek(0).sval+"<");
                        yyval.obj = Tp_ERRO;
                    } else {
                        yyval.obj = temp.getTipo();
                    }
                    
                }
break;
case 65:
//#line 342 "exemploSem.y"
{yyval.obj = val_peek(0).obj;}
break;
case 66:
//#line 343 "exemploSem.y"
{yyval.obj = new ArrayList<TS_entry>();}
break;
case 67:
//#line 347 "exemploSem.y"
{
                ArrayList<TS_entry> temp = (ArrayList<TS_entry>)val_peek(2).obj;
                temp.add((TS_entry)val_peek(0).obj);
                yyval.obj = temp;
            }
break;
case 68:
//#line 353 "exemploSem.y"
{
                ArrayList<TS_entry> temp = new ArrayList<TS_entry>();
                temp.add((TS_entry)val_peek(0).obj);
                yyval.obj = temp;
            }
break;
//#line 971 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
