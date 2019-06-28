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
public final static short LITERAL=263;
public final static short AND=264;
public final static short VOID=265;
public final static short MAIN=266;
public final static short IF=267;
public final static short DSTRUCT=268;
public final static short STRUCT=269;
public final static short FUNCTION=270;
public final static short RETURN=271;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    7,    0,    6,    6,    9,    9,    9,   13,   16,   10,
    2,    2,   14,   14,   18,   18,   19,   19,   15,   15,
   17,   17,   20,   20,   23,   24,   11,   26,   12,   28,
   12,   22,   22,   27,   27,   29,   30,   25,   25,    1,
    1,    1,    8,   31,   32,   32,   21,   21,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    4,    4,
    5,    5,
};
final static short yylen[] = {                            2,
    0,    3,    2,    0,    1,    1,    1,    0,    0,   12,
    1,    1,    1,    0,    3,    1,    2,    3,    2,    0,
    2,    0,    1,    2,    0,    0,    8,    0,    5,    0,
    5,    2,    1,    3,    1,    1,    0,    5,    0,    1,
    1,    1,    5,    3,    2,    0,    2,    7,    3,    3,
    3,    1,    3,    1,    3,    4,    3,    4,    1,    0,
    3,    1,
};
final static short yydefred[] = {                         1,
    0,    0,   40,   41,   42,    0,    0,    0,   28,    0,
    0,    5,    6,    7,    0,   30,   11,   12,    0,    0,
    0,    2,    3,   25,    0,    8,    0,    0,    0,    0,
   36,    0,   35,    0,    0,    0,    0,   33,    0,    0,
   31,    0,   37,   29,    0,   32,    0,   34,    0,    0,
    0,    0,   16,    0,   46,   43,    0,    0,   17,    0,
    0,   38,    0,   27,   18,    9,   15,    0,   52,    0,
    0,   44,    0,   45,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   47,    0,   22,    0,    0,    0,
   55,    0,   53,    0,    0,    0,    0,    0,   19,    0,
   56,    0,    0,   58,    0,   10,   21,   23,    0,    0,
    0,    0,   48,
};
final static short yydgoto[] = {                          1,
    9,   19,   73,   89,   90,   10,    2,   22,   11,   12,
   13,   14,   34,   51,   87,   75,  100,   52,   53,  107,
   74,   39,   30,   47,   28,   20,   32,   25,   33,   54,
   56,   63,
};
final static short yysindex[] = {                         0,
    0, -121,    0,    0,    0, -247, -246, -115,    0, -208,
 -121,    0,    0,    0, -105,    0,    0,    0, -197,  -30,
 -190,    0,    0,    0, -175,    0, -178, -175,   55, -171,
    0,  -21,    0,   60,   -1,   -9,   62,    0, -171, -175,
    0, -138,    0,    0,  -17,    0,  -16,    0, -149, -146,
   71,   70,    0,  -30,    0,    0,   56, -140,    0,   -4,
 -138,    0,    1,    0,    0,    0,    0,   33,    0,   84,
  -38,    0,   13,    0, -171,  -38, -132,  -38,    2,  -38,
  -38,  -38,  -38,  -38,    0, -171,    0,   16,   88,   96,
    0,    5,    0,   51,   16,  -29,  -84,    8,    0,  -23,
    0,  -38,   18,    0,  -38,    0,    0,    0,   16,    4,
   16,   21,    0,
};
final static short yyrindex[] = {                         0,
    0, -114,    0,    0,    0,    0,    0,    0,    0,    0,
 -114,    0,    0,    0,    0,    0,    0,    0,    0, -104,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   27,    0,
    0,  114,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  115,    0, -104,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   -7,    0,    0,
    0,    0,    0,    0,  -20,   71,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -20,    0,   -2,    0,  117,
    0,    0,    0,  -40,  -12,  -28,  -35,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   50,    0,
  -15,    0,    0,
};
final static short yygindex[] = {                         0,
    7,    0,   52,    0,    0,  148,    0,    0,    0,    0,
    0,   41,    0,    0,   74,    0,    0,    0,  100,    0,
  -70,    0,    0,    0,  108,    0,  135,    0,  124,    0,
    0,    0,
};
final static int YYTABLESIZE=280;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         51,
   51,   71,   51,   51,   49,   49,   84,   49,   49,   15,
   16,   50,   50,   83,   18,   50,   71,   24,   51,   20,
   51,   51,   40,   49,   24,   49,   49,   57,   57,  108,
   50,   57,   50,   54,   40,   54,   54,   41,   62,  112,
   71,   62,   93,   71,   83,  103,   57,   83,   50,   44,
   83,   54,   51,   54,   54,   83,   21,   49,   83,   26,
   27,   84,   81,   82,   50,   81,   82,   50,   81,   82,
   38,   85,   76,   81,   82,   29,   81,   82,   77,   46,
   57,   31,   35,   54,   51,   54,    3,    4,    5,   49,
   61,   43,   84,   61,   37,   84,   50,    7,   84,   42,
  104,  106,   45,   84,   20,   55,   84,   58,   57,   24,
   59,   60,   57,   61,   64,   86,   65,   54,   66,    3,
    4,    5,   79,   78,   91,   72,   86,   88,  101,   92,
   49,   94,   95,   96,   97,   98,    3,    4,    5,  102,
  110,   84,    3,    4,    5,  113,    6,    7,    8,   17,
    4,   26,   39,  109,   14,   13,  111,   59,   23,   99,
   67,   62,   36,   48,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   80,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   51,    0,   68,    0,
   51,   49,   69,   51,    0,   49,   51,    0,   50,    0,
   51,   49,   50,   68,   80,   49,   20,   69,   50,    0,
   20,   24,   50,   70,   57,   24,   20,  105,   57,   54,
   20,   24,    0,   54,   57,   24,   54,   68,   57,   54,
   68,   69,    0,   54,   69,   80,    0,   70,   80,    0,
   70,   80,    0,    0,    0,    0,   80,    0,    0,   80,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         40,
   41,   40,   43,   44,   40,   41,   91,   43,   44,  257,
  257,   40,   41,   43,    8,   44,   40,  123,   59,   40,
   61,   62,   44,   59,   40,   61,   62,   40,   41,  100,
   59,   44,   61,   41,   44,   43,   44,   59,   41,  110,
   40,   44,   41,   40,   43,   41,   59,   43,   42,   59,
   43,   59,   93,   61,   62,   43,  265,   93,   43,  257,
   91,   91,   61,   62,   93,   61,   62,   61,   61,   62,
   30,   59,   40,   61,   62,  266,   61,   62,   46,   39,
   93,  257,  261,   91,  125,   93,  258,  259,  260,  125,
   41,   93,   91,   44,   40,   91,  125,  269,   91,   40,
   93,  125,   41,   91,  125,  123,   91,  257,  125,  125,
  257,   41,  125,   44,   59,   75,  257,  125,  123,  258,
  259,  260,   71,   40,  257,  125,   86,   76,   41,   78,
  269,   80,   81,   82,   83,   84,  258,  259,  260,   44,
  123,   91,  258,  259,  260,  125,  268,  269,  270,  265,
  265,  125,  257,  102,   41,   41,  105,   41,   11,   86,
   61,   54,   28,   40,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  264,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  257,   -1,  257,   -1,
  261,  257,  261,  264,   -1,  261,  267,   -1,  257,   -1,
  271,  267,  261,  257,  264,  271,  257,  261,  267,   -1,
  261,  257,  271,  267,  257,  261,  267,  271,  261,  257,
  271,  267,   -1,  261,  267,  271,  264,  257,  271,  267,
  257,  261,   -1,  271,  261,  264,   -1,  267,  264,   -1,
  267,  264,   -1,   -1,   -1,   -1,  264,   -1,   -1,  264,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=271;
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
"STRING","LITERAL","AND","VOID","MAIN","IF","DSTRUCT","STRUCT","FUNCTION",
"RETURN",
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
"funcCmd : RETURN exp",
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
"exp : '(' exp ')'",
"exp : IDENT",
"exp : IDENT '.' IDENT",
"exp : IDENT '(' maybeExp ')'",
"exp : exp '=' exp",
"exp : exp '[' exp ']'",
"maybeExp : lExp",
"maybeExp :",
"lExp : lExp ',' exp",
"lExp : exp",
};

//#line 295 "exemploSem.y"

  private Yylex lexer;

  private TabSimb ts;
  private TabSimb auxTs;
  
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
    System.err.printf("Erro (linha: %2d) \tMensagem: %s\n", lexer.getLine(), error);
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

//#line 475 "Parser.java"
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
//#line 28 "exemploSem.y"
{ currClass = ClasseID.VarGlobal; }
break;
case 8:
//#line 38 "exemploSem.y"
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
//#line 51 "exemploSem.y"
{currClass = ClasseID.VarLocal;}
break;
case 10:
//#line 53 "exemploSem.y"
{
                ts = auxTs; /*tabela de simbolos global novamente*/
                currClass = ClasseID.VarGlobal;
            }
break;
case 11:
//#line 59 "exemploSem.y"
{yyval.obj = Tp_VOID;}
break;
case 17:
//#line 73 "exemploSem.y"
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
case 18:
//#line 83 "exemploSem.y"
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
//#line 114 "exemploSem.y"
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
case 26:
//#line 127 "exemploSem.y"
{
                    nodoAux.setLocais(ts);
                    ts = auxTs;
                    currClass = ClasseID.VarGlobal;
                }
break;
case 28:
//#line 135 "exemploSem.y"
{currentType = (TS_entry)val_peek(0).obj;}
break;
case 30:
//#line 140 "exemploSem.y"
{
                currentType = auxTs.pesquisa(val_peek(0).sval);
                if(currentType==null || currentType.getClasse()!=ClasseID.NomeStruct) {
                    yyerror("(sem) nao existe struct do tipo >" + val_peek(0).sval + "<.");
                    currentType = Tp_ERRO;
                }
            }
break;
case 36:
//#line 163 "exemploSem.y"
{
            TS_entry nodo = ts.pesquisa(val_peek(0).sval);
            if (nodo != null) {
                yyerror("(sem) variavel >" + val_peek(0).sval + "< jah declarado");
            } else {
                ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass));
            }
        }
break;
case 37:
//#line 173 "exemploSem.y"
{ currentType = new TS_entry("?", Tp_ARRAY, 
                                                   currClass, val_peek(1).ival, currentType); }
break;
case 40:
//#line 183 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 41:
//#line 184 "exemploSem.y"
{ yyval.obj = Tp_DOUBLE; }
break;
case 42:
//#line 185 "exemploSem.y"
{ yyval.obj = Tp_BOOL; }
break;
case 48:
//#line 199 "exemploSem.y"
{  if ( ((TS_entry)val_peek(4).obj) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)val_peek(4).obj).getTipo());
                             }
break;
case 49:
//#line 205 "exemploSem.y"
{ yyval.obj = validaTipo('+', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 50:
//#line 206 "exemploSem.y"
{ yyval.obj = validaTipo('>', (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 51:
//#line 207 "exemploSem.y"
{ yyval.obj = validaTipo(AND, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj); }
break;
case 52:
//#line 208 "exemploSem.y"
{ yyval.obj = Tp_INT; }
break;
case 53:
//#line 209 "exemploSem.y"
{ yyval.obj = val_peek(1).obj; }
break;
case 54:
//#line 210 "exemploSem.y"
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
case 55:
//#line 222 "exemploSem.y"
{
                        TS_entry nodo = ts.pesquisa(val_peek(2).sval);
                        if (nodo == null) {
                           nodo = auxTs.pesquisa(val_peek(2).sval);
                        }
                        if(nodo == null || nodo.getTipo().getTipo()!=Tp_STRUCT) {
                            yyerror("(sem) var <" + val_peek(2).sval + "> nao declarada");
                        } else {
                            nodo = nodo.getTipo().getLocais().pesquisa(val_peek(0).sval);
                        }
                        if(nodo == null) {
                            nodo = Tp_ERRO;
                        } else {
                            nodo = nodo.getTipo();
                        }
                        
                        yyval.obj = nodo;
                    }
break;
case 56:
//#line 242 "exemploSem.y"
{
                        TS_entry temp = auxTs.pesquisa(val_peek(3).sval);
                        boolean ok = false;
                        if(temp!=null && temp.getClasse()==ClasseID.NomeFuncao) {
                            ArrayList<TS_entry> paramList = temp.getParams();
                            ArrayList<TS_entry> arguments = (ArrayList<TS_entry>)val_peek(1).obj;
                            if(paramList.size()==arguments.size()) {
                                ok = true;
                                for(int i = 0; i<paramList.size(); i++) {
                                    if(paramList.get(i).getTipo()!=arguments.get(i).getTipo()) {
                                        /*Uma das expressoes tem tipo diferente do aceito pelo parametro*/
                                        ok = false;
                                        break;
                                    }
                                }
                            }
                        }
                        if(ok) {
                            yyval.obj = temp.getTipo();
                        } else {
                            yyval.obj = Tp_ERRO;
                        }
                    }
break;
case 57:
//#line 265 "exemploSem.y"
{  yyval.obj = validaTipo(ATRIB, (TS_entry)val_peek(2).obj, (TS_entry)val_peek(0).obj);  }
break;
case 58:
//#line 266 "exemploSem.y"
{  if ((TS_entry)val_peek(1).obj != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)val_peek(3).obj).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  yyval.obj = ((TS_entry)val_peek(3).obj).getTipoBase();
                         }
break;
case 59:
//#line 276 "exemploSem.y"
{yyval.obj = val_peek(0).obj;}
break;
case 60:
//#line 277 "exemploSem.y"
{yyval.obj = new ArrayList<TS_entry>();}
break;
case 61:
//#line 281 "exemploSem.y"
{
                ArrayList<TS_entry> temp = (ArrayList<TS_entry>)val_peek(2).obj;
                temp.add((TS_entry)val_peek(0).obj);
                yyval.obj = temp;
            }
break;
case 62:
//#line 287 "exemploSem.y"
{
                ArrayList<TS_entry> temp = new ArrayList<TS_entry>();
                temp.add((TS_entry)val_peek(0).obj);
                yyval.obj = temp;
            }
break;
//#line 878 "Parser.java"
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
