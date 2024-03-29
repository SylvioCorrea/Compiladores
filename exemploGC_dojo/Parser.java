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






//#line 3 "exemploGC.y"
  import java.io.*;
  import java.util.ArrayList;
  import java.util.Stack;
//#line 21 "Parser.java"




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
public final static short ID=257;
public final static short INT=258;
public final static short FLOAT=259;
public final static short BOOL=260;
public final static short NUM=261;
public final static short LIT=262;
public final static short VOID=263;
public final static short STRUCT=264;
public final static short MAIN=265;
public final static short READ=266;
public final static short WRITE=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short BREAK=270;
public final static short CONTINUE=271;
public final static short WHILE=272;
public final static short TRUE=273;
public final static short FALSE=274;
public final static short FOR=275;
public final static short EQ=276;
public final static short LEQ=277;
public final static short GEQ=278;
public final static short NEQ=279;
public final static short AND=280;
public final static short OR=281;
public final static short INC=282;
public final static short IDENT=283;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    5,    7,    4,    2,    2,    8,   10,   11,
    8,    1,    1,    1,   12,   13,   13,    9,    9,    6,
    6,   14,   14,   14,   16,   14,   14,   17,   18,   14,
   19,   14,   22,   24,   25,   14,   14,   14,   21,   21,
   23,   23,   26,   20,   20,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   15,   15,   15,   15,   15,   15,
};
final static short yylen[] = {                            2,
    0,    3,    0,    0,    9,    2,    0,    3,    0,    0,
    8,    1,    1,    1,    1,    3,    1,    4,    2,    2,
    0,    2,    3,    5,    0,    8,    5,    0,    0,    7,
    0,    7,    0,    0,    0,   12,    2,    2,    1,    0,
    1,    0,    0,    3,    0,    3,    1,    1,    1,    1,
    3,    2,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    2,    2,
};
final static short yydefred[] = {                         1,
    0,    0,   12,   13,   14,    0,    0,    0,    0,    0,
    0,    0,    2,    6,    9,    8,    0,    0,    0,    0,
    0,    3,   15,   17,    0,    0,    0,    0,    0,    0,
    0,   21,   16,    0,   11,    0,    0,   47,    0,    0,
    0,    0,    0,   28,   48,   49,    0,    0,    0,    0,
   21,    0,   20,    0,   66,    0,    0,    0,    0,   37,
   38,    0,    0,   67,   52,    0,    0,    5,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   22,    0,    0,    0,    0,    0,    0,   33,   51,
   23,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   55,   56,   57,    0,    0,    0,    0,    0,    0,
   27,   24,    0,    0,   29,    0,    0,    0,    0,    0,
   34,    0,   43,   32,   30,    0,   26,    0,    0,   44,
   35,    0,    0,   36,
};
final static short yydgoto[] = {                          1,
    7,    8,    2,   13,   28,   36,   52,    9,   21,   18,
   27,   24,   25,   53,   54,  107,   62,  119,  108,  124,
   89,  110,  121,  126,  132,  128,
};
final static short yysindex[] = {                         0,
    0, -209,    0,    0,    0, -265, -228, -231, -209,  -89,
  -23, -226,    0,    0,    0,    0,   -3, -245,   -1, -241,
  -16,    0,    0,    0,    2, -245,  -81,  -76, -241, -241,
   -5,    0,    0,    2,    0,    5,  -60,    0,   13,   21,
   23,    7,   12,    0,    0,    0,   32, -184,    8,    8,
    0,  -49,    0,   22,    0,    8, -179, -183,    8,    0,
    0,   40,    8,    0,    0,   46,  -33,    0,    8,    8,
    8,    8,    8,    8,    8,    8,    8,    8,    8,    8,
    8,    0,   84,   44,   45,   84,    8,   84,    0,    0,
    0,  -12,  -12,  -12,  -12,  106,   95,  -12,  -12,  -26,
  -26,    0,    0,    0,   36,   37,   53,   60,   57,   48,
    0,    0,    8,    5,    0,    8,   73, -166,    5,   84,
    0,   54,    0,    0,    0,   63,    0,    5,    8,    0,
    0,   64,    5,    0,
};
final static short yyrindex[] = {                         0,
    0, -151,    0,    0,    0,    0,    0,    0, -151,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   -2,    0,    0,    0,  -57,    0,    0,    0,    0,    0,
    0,    0,    0,  -55,    0,    9,   15,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  -32,    0,   81,   89,    0,  -31,    0,    0,
    0,  123,  131,  137,  143,  -36,  -35,  151,  157,  111,
  117,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   77,    0,  -14,    0,   80,
    0,    0,    0,    0,    0,    0,    0,    0,  100,    0,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
   -6,  136,    0,    0,    0,   96,    0,    0,    0,    0,
    0,  121,  129, -111,  390,    0,    0,    0,    0,    0,
   34,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=519;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         49,
   56,   19,  118,   18,   65,   64,   50,  125,   46,   39,
   81,   20,    3,    4,    5,   79,  130,   10,   45,   30,
   80,  134,   65,   64,   81,   45,   46,   39,   11,   79,
   77,   12,   78,   15,   80,   16,   19,   49,   17,   22,
   49,   23,   26,   31,   50,   29,   32,   50,    3,    4,
    5,   50,   57,   35,    6,   50,   50,   50,   81,   50,
   58,   50,   59,   79,   77,   60,   78,   19,   80,   18,
   61,   63,   64,   50,   50,   68,   50,   84,   85,   87,
   82,   76,   81,   75,  105,  106,   90,   79,   77,   51,
   78,   91,   80,   81,  111,  112,  113,  115,   79,   77,
  114,   78,  123,   80,  133,   76,  116,   75,   45,   81,
   45,    7,  127,  122,   79,   77,   76,   78,   75,   80,
   81,  129,   10,   40,   25,   79,   77,   51,   78,   31,
   80,   81,   76,    4,   75,   42,   79,   77,   41,   78,
   40,   80,   81,   76,   14,   75,   67,   79,   77,   33,
   78,   53,   80,   53,   76,   53,   75,   54,   34,   54,
    0,   54,  131,   60,    0,   76,    0,   75,    0,   53,
   53,   61,   53,    0,    0,   54,   54,   62,   54,    0,
    0,   60,   60,   63,   60,    0,    0,    0,    0,   61,
   61,   58,   61,    0,    0,   62,   62,   59,   62,    0,
    0,   63,   63,    0,   63,    0,    0,    0,    0,   58,
   58,    0,   58,    0,    0,   59,   59,    0,   59,    0,
    0,   55,    0,   37,    0,    0,    0,   38,    0,    0,
    0,    0,   39,   40,   41,    0,   42,   43,   44,   45,
   46,   47,   45,   65,   65,   64,   45,    0,   48,    0,
    0,   45,   45,   45,    0,   45,   45,   45,   45,   45,
   45,   37,    0,    0,   37,   38,    0,   45,   38,    0,
   39,   40,   41,    0,   42,   43,   44,   45,   46,   47,
   45,   46,    0,    0,    0,    0,   48,    0,    0,   48,
   50,   50,   50,   50,   50,   50,    0,   69,   70,   71,
   72,   73,   74,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   69,   70,   71,   72,   73,   74,    0,    0,    0,
    0,    0,   69,   70,   71,   72,   73,   74,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   69,   70,
   71,   72,   73,   74,    0,    0,    0,    0,    0,   69,
   70,   71,   72,   73,   74,    0,    0,    0,    0,    0,
   69,   70,   71,   72,   73,    0,    0,    0,    0,    0,
    0,   69,   70,   71,   72,    0,   53,   53,   53,   53,
   53,   53,   54,   54,   54,   54,   54,   54,   60,   60,
   60,   60,   60,   60,    0,    0,   61,   61,   61,   61,
   61,   61,   62,   62,   62,   62,   62,   62,   63,   63,
   63,   63,   63,   63,    0,    0,   58,   58,   58,   58,
   58,   58,   59,   59,   59,   59,   59,   59,   65,   66,
    0,    0,    0,    0,    0,   83,    0,    0,   86,    0,
    0,    0,   88,    0,    0,    0,    0,    0,   92,   93,
   94,   95,   96,   97,   98,   99,  100,  101,  102,  103,
  104,    0,    0,    0,    0,    0,  109,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  117,    0,    0,  120,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   88,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   61,   59,  114,   59,   41,   41,   40,  119,   41,   41,
   37,   18,  258,  259,  260,   42,  128,  283,   33,   26,
   47,  133,   59,   59,   37,   40,   59,   59,  257,   42,
   43,  263,   45,  123,   47,   59,   40,   33,  265,   41,
   33,  283,   59,  125,   40,   44,  123,   40,  258,  259,
  260,   37,   40,   59,  264,   41,   42,   43,   37,   45,
   40,   47,   40,   42,   43,   59,   45,  125,   47,  125,
   59,   40,  257,   59,   60,  125,   62,  257,  262,   40,
   59,   60,   37,   62,   41,   41,   41,   42,   43,  123,
   45,  125,   47,   37,   59,   59,   44,   41,   42,   43,
   41,   45,  269,   47,   41,   60,   59,   62,  123,   37,
  125,  263,   59,   41,   42,   43,   60,   45,   62,   47,
   37,   59,  125,   59,   44,   42,   43,  123,   45,   41,
   47,   37,   60,  125,   62,   59,   42,   43,   59,   45,
   41,   47,   37,   60,    9,   62,   51,   42,   43,   29,
   45,   41,   47,   43,   60,   45,   62,   41,   30,   43,
   -1,   45,  129,   41,   -1,   60,   -1,   62,   -1,   59,
   60,   41,   62,   -1,   -1,   59,   60,   41,   62,   -1,
   -1,   59,   60,   41,   62,   -1,   -1,   -1,   -1,   59,
   60,   41,   62,   -1,   -1,   59,   60,   41,   62,   -1,
   -1,   59,   60,   -1,   62,   -1,   -1,   -1,   -1,   59,
   60,   -1,   62,   -1,   -1,   59,   60,   -1,   62,   -1,
   -1,  282,   -1,  257,   -1,   -1,   -1,  261,   -1,   -1,
   -1,   -1,  266,  267,  268,   -1,  270,  271,  272,  273,
  274,  275,  257,  280,  281,  281,  261,   -1,  282,   -1,
   -1,  266,  267,  268,   -1,  270,  271,  272,  273,  274,
  275,  257,   -1,   -1,  257,  261,   -1,  282,  261,   -1,
  266,  267,  268,   -1,  270,  271,  272,  273,  274,  275,
  273,  274,   -1,   -1,   -1,   -1,  282,   -1,   -1,  282,
  276,  277,  278,  279,  280,  281,   -1,  276,  277,  278,
  279,  280,  281,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  276,  277,  278,  279,  280,  281,   -1,   -1,   -1,
   -1,   -1,  276,  277,  278,  279,  280,  281,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,  277,
  278,  279,  280,  281,   -1,   -1,   -1,   -1,   -1,  276,
  277,  278,  279,  280,  281,   -1,   -1,   -1,   -1,   -1,
  276,  277,  278,  279,  280,   -1,   -1,   -1,   -1,   -1,
   -1,  276,  277,  278,  279,   -1,  276,  277,  278,  279,
  280,  281,  276,  277,  278,  279,  280,  281,  276,  277,
  278,  279,  280,  281,   -1,   -1,  276,  277,  278,  279,
  280,  281,  276,  277,  278,  279,  280,  281,  276,  277,
  278,  279,  280,  281,   -1,   -1,  276,  277,  278,  279,
  280,  281,  276,  277,  278,  279,  280,  281,   49,   50,
   -1,   -1,   -1,   -1,   -1,   56,   -1,   -1,   59,   -1,
   -1,   -1,   63,   -1,   -1,   -1,   -1,   -1,   69,   70,
   71,   72,   73,   74,   75,   76,   77,   78,   79,   80,
   81,   -1,   -1,   -1,   -1,   -1,   87,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  113,   -1,   -1,  116,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  129,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=283;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,"ID","INT","FLOAT","BOOL","NUM","LIT",
"VOID","STRUCT","MAIN","READ","WRITE","IF","ELSE","BREAK","CONTINUE","WHILE",
"TRUE","FALSE","FOR","EQ","LEQ","GEQ","NEQ","AND","OR","INC","IDENT",
};
final static String yyrule[] = {
"$accept : prog",
"$$1 :",
"prog : $$1 dList mainF",
"$$2 :",
"$$3 :",
"mainF : VOID MAIN '(' ')' $$2 '{' lcmd $$3 '}'",
"dList : decl dList",
"dList :",
"decl : type ID ';'",
"$$4 :",
"$$5 :",
"decl : STRUCT IDENT '{' $$4 lcampos $$5 '}' ';'",
"type : INT",
"type : FLOAT",
"type : BOOL",
"id : IDENT",
"lid : lid ',' id",
"lid : id",
"lcampos : lcampos ';' type lid",
"lcampos : type lid",
"lcmd : lcmd cmd",
"lcmd :",
"cmd : exp ';'",
"cmd : '{' lcmd '}'",
"cmd : WRITE '(' LIT ')' ';'",
"$$6 :",
"cmd : WRITE '(' LIT $$6 ',' exp ')' ';'",
"cmd : READ '(' ID ')' ';'",
"$$7 :",
"$$8 :",
"cmd : WHILE $$7 '(' exp ')' $$8 cmd",
"$$9 :",
"cmd : IF '(' exp $$9 ')' cmd restoIf",
"$$10 :",
"$$11 :",
"$$12 :",
"cmd : FOR '(' expo $$10 ';' expol $$11 ';' expo $$12 ')' cmd",
"cmd : BREAK ';'",
"cmd : CONTINUE ';'",
"expo : exp",
"expo :",
"expol : exp",
"expol :",
"$$13 :",
"restoIf : ELSE $$13 cmd",
"restoIf :",
"exp : ID '=' exp",
"exp : NUM",
"exp : TRUE",
"exp : FALSE",
"exp : ID",
"exp : '(' exp ')'",
"exp : '!' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : exp '%' exp",
"exp : exp '>' exp",
"exp : exp '<' exp",
"exp : exp EQ exp",
"exp : exp LEQ exp",
"exp : exp GEQ exp",
"exp : exp NEQ exp",
"exp : exp OR exp",
"exp : exp AND exp",
"exp : ID INC",
"exp : INC ID",
};

//#line 259 "exemploGC.y"

  private Yylex lexer;

  private TabSimb ts = new TabSimb();
  private TabSimb aux;
	private TS_entry nodoAux;
	
	
  private int strCount = 0;
  private ArrayList<String> strTab = new ArrayList<String>();
	
  private Stack<Integer> pRot = new Stack<Integer>();
  private Stack<Integer> pRotSel = new Stack<Integer>();

  private int proxRot = 1;


  public static int ARRAY = 100;


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
    System.err.println ("Error: " + error + "  linha: " + lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }  

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void listarTS() { ts.listar();}

  public static void main(String args[]) throws IOException {

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
      yyparser.yyparse();
      // yyparser.listarTS();

    }
    else {
      // interactive mode
      System.out.println("\n\tFormato: java Parser entrada.cmm >entrada.s\n");
    }

  }

							
		void gcExpArit(int oparit) {
 				System.out.println("\tPOPL %EDX");
   			System.out.println("\tPOPL %EAX");

   		switch (oparit) {
     		case '+' : System.out.println("\tADDL %EDX, %EAX" ); break;
     		case '-' : System.out.println("\tSUBL %EDX, %EAX" ); break;
     		case '*' : System.out.println("\tIMULL %EDX, %EAX" ); break;
     		case '/':  System.out.println("\tMOVL %EAX, %EBX");
        	         System.out.println("\tMOVL %EDX, %EAX");
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     break;
     		case '%':  System.out.println("\tMOVL %EAX, %EBX");
        	         System.out.println("\tMOVL %EDX, %EAX");
           		     System.out.println("\tMOVL $0, %EDX");
           		     System.out.println("\tIDIVL %EBX");
           		     System.out.println("\tMOVL %EDX, %EAX");
           		     break;
    		}
   		System.out.println("\tPUSHL %EAX");
		}

	public void gcExpRel(int oprel) {

    System.out.println("\tPOPL %EAX");
    System.out.println("\tPOPL %EDX");
    System.out.println("\tCMPL %EAX, %EDX");
    System.out.println("\tMOVL $0, %EAX");
    
    switch (oprel) {
       case '<':  			System.out.println("\tSETL  %AL"); break;
       case '>':  			System.out.println("\tSETG  %AL"); break;
       case Parser.EQ:  System.out.println("\tSETE  %AL"); break;
       case Parser.GEQ: System.out.println("\tSETGE %AL"); break;
       case Parser.LEQ: System.out.println("\tSETLE %AL"); break;
       case Parser.NEQ: System.out.println("\tSETNE %AL"); break;
       }
    
    System.out.println("\tPUSHL %EAX");

	}


	public void gcExpLog(int oplog) {

	   	System.out.println("\tPOPL %EDX");
 		 	System.out.println("\tPOPL %EAX");

  	 	System.out.println("\tCMPL $0, %EAX");
 		  System.out.println("\tMOVL $0, %EAX");
   		System.out.println("\tSETNE %AL");
   		System.out.println("\tCMPL $0, %EDX");
   		System.out.println("\tMOVL $0, %EDX");
   		System.out.println("\tSETNE %DL");

   		switch (oplog) {
    			case Parser.OR:  System.out.println("\tORL  %EDX, %EAX");  break;
    			case Parser.AND: System.out.println("\tANDL  %EDX, %EAX"); break;
       }

    	System.out.println("\tPUSHL %EAX");
	}

	public void gcExpNot(){

  	 System.out.println("\tPOPL %EAX" );
 	   System.out.println("	\tNEGL %EAX" );
  	 System.out.println("	\tPUSHL %EAX");
	}

   private void geraInicio() {
			System.out.println(".text\n\n#\t nome COMPLETO e matricula dos componentes do grupo...\n#\n"); 
			System.out.println(".GLOBL _start\n\n");  
   }

   private void geraFinal(){
	
			System.out.println("\n\n");
			System.out.println("#");
			System.out.println("# devolve o controle para o SO (final da main)");
			System.out.println("#");
			System.out.println("\tmov $0, %ebx");
			System.out.println("\tmov $1, %eax");
			System.out.println("\tint $0x80");
	
			System.out.println("\n");
			System.out.println("#");
			System.out.println("# Funcoes da biblioteca (IO)");
			System.out.println("#");
			System.out.println("\n");
			System.out.println("_writeln:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $10, (%ECX)");
			System.out.println("\tMOVL $1, %EDX");
			System.out.println("\tJMP _writeLit");
			System.out.println("_write:");
			System.out.println("\tMOVL $__fim_msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJGE _write3");
			System.out.println("\tNEGL %EAX");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("_write3:");
			System.out.println("\tPUSHL %EBX");
			System.out.println("\tMOVL $10, %EBX");
			System.out.println("_divide:");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tIDIVL %EBX");
			System.out.println("\tDECL %ECX");
			System.out.println("\tADD $48, %DL");
			System.out.println("\tMOVB %DL, (%ECX)");
			System.out.println("\tCMPL $0, %EAX");
			System.out.println("\tJNE _divide");
			System.out.println("\tPOPL %EBX");
			System.out.println("\tCMPL $0, %EBX");
			System.out.println("\tJE _print");
			System.out.println("\tDECL %ECX");
			System.out.println("\tMOVB $'-', (%ECX)");
			System.out.println("_print:");
			System.out.println("\tMOVL $__fim_msg, %EDX");
			System.out.println("\tSUBL %ECX, %EDX");
			System.out.println("_writeLit:");
			System.out.println("\tMOVL $1, %EBX");
			System.out.println("\tMOVL $4, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tRET");
			System.out.println("_read:");
			System.out.println("\tMOVL $15, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $3, %EAX");
			System.out.println("\tint $0x80");
			System.out.println("\tMOVL $0, %EAX");
			System.out.println("\tMOVL $0, %EBX");
			System.out.println("\tMOVL $0, %EDX");
			System.out.println("\tMOVL $__msg, %ECX");
			System.out.println("\tCMPB $'-', (%ECX)");
			System.out.println("\tJNE _reading");
			System.out.println("\tINCL %ECX");
			System.out.println("\tINC %BL");
			System.out.println("_reading:");
			System.out.println("\tMOVB (%ECX), %DL");
			System.out.println("\tCMP $10, %DL");
			System.out.println("\tJE _fimread");
			System.out.println("\tSUB $48, %DL");
			System.out.println("\tIMULL $10, %EAX");
			System.out.println("\tADDL %EDX, %EAX");
			System.out.println("\tINCL %ECX");
			System.out.println("\tJMP _reading");
			System.out.println("_fimread:");
			System.out.println("\tCMPB $1, %BL");
			System.out.println("\tJNE _fimread2");
			System.out.println("\tNEGL %EAX");
			System.out.println("_fimread2:");
			System.out.println("\tRET");
			System.out.println("\n");
     }

     private void geraAreaDados(){
			System.out.println("");		
			System.out.println("#");
			System.out.println("# area de dados");
			System.out.println("#");
			System.out.println(".data");
			System.out.println("#");
			System.out.println("# variaveis globais");
			System.out.println("#");
			ts.geraGlobais();	
			System.out.println("");
	
    }

     private void geraAreaLiterais() { 

         System.out.println("#\n# area de literais\n#");
         System.out.println("__msg:");
	       System.out.println("\t.zero 30");
	       System.out.println("__fim_msg:");
	       System.out.println("\t.byte 0");
	       System.out.println("\n");

         for (int i = 0; i<strTab.size(); i++ ) {
             System.out.println("_str_"+i+":");
             System.out.println("\t .ascii \""+strTab.get(i)+"\""); 
	           System.out.println("_str_"+i+"Len = . - _str_"+i);  
	      }		
   }
   
//#line 662 "Parser.java"
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
//#line 31 "exemploGC.y"
{ geraInicio(); }
break;
case 2:
//#line 31 "exemploGC.y"
{ geraAreaDados(); geraAreaLiterais(); }
break;
case 3:
//#line 33 "exemploGC.y"
{ System.out.println("_start:"); }
break;
case 4:
//#line 34 "exemploGC.y"
{ geraFinal(); }
break;
case 8:
//#line 40 "exemploGC.y"
{  TS_entry nodo = ts.pesquisa(val_peek(1).sval);
    	                if (nodo != null) 
                            yyerror("(sem) variavel >" + val_peek(1).sval + "< jah declarada");
                        else ts.insert(new TS_entry(val_peek(1).sval, val_peek(2).ival)); }
break;
case 9:
//#line 44 "exemploGC.y"
{
                        nodoAux = ts.pesquisa(val_peek(1).sval);
                        if (nodo != null) {
                        	yyerror("(sem) nome >" + val_peek(1).sval + "< jah declarada");
                        } else {
		                     	nodoAux = new TS_entry(val_peek(1).sval, TP_STRUCT, ClasseID.NomeStruct);
		                     	ts.insert(nodoAux);
		                     	aux = ts;
		                     	ts = new TabSimb();
                       	}
                       	}
break;
case 10:
//#line 58 "exemploGC.y"
{
                       	nodoAux.setLocais(ts);
                       	ts = aux;
                       	}
break;
case 12:
//#line 65 "exemploGC.y"
{ yyval.ival = INT; }
break;
case 13:
//#line 66 "exemploGC.y"
{ yyval.ival = FLOAT; }
break;
case 14:
//#line 67 "exemploGC.y"
{ yyval.ival = BOOL; }
break;
case 15:
//#line 70 "exemploGC.y"
{ TS_entry nodo = ts.pesquisa(val_peek(0).sval);
	if (nodo != null)
		yyerror("(sem) variavel >" + $s1 + "c");
	else ts.insert(new TS_entry(val_peek(0).sval, currentType, currClass));
	}
break;
case 22:
//#line 90 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
               }
break;
case 23:
//#line 92 "exemploGC.y"
{ System.out.println("\t\t# terminou o bloco..."); }
break;
case 24:
//#line 95 "exemploGC.y"
{ strTab.add(val_peek(2).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				System.out.println("\tCALL _writeln"); 
                                strCount++;
				}
break;
case 25:
//#line 104 "exemploGC.y"
{ strTab.add(val_peek(0).sval);
                                System.out.println("\tMOVL $_str_"+strCount+"Len, %EDX"); 
				System.out.println("\tMOVL $_str_"+strCount+", %ECX"); 
                                System.out.println("\tCALL _writeLit"); 
				strCount++;
				}
break;
case 26:
//#line 112 "exemploGC.y"
{ 
			 System.out.println("\tPOPL %EAX"); 
			 System.out.println("\tCALL _write");	
			 System.out.println("\tCALL _writeln"); 
                        }
break;
case 27:
//#line 119 "exemploGC.y"
{
									System.out.println("\tPUSHL $_"+val_peek(2).sval);
									System.out.println("\tCALL _read");
									System.out.println("\tPOPL %EDX");
									System.out.println("\tMOVL %EAX, (%EDX)");
									
								}
break;
case 28:
//#line 127 "exemploGC.y"
{
					pRot.push(proxRot);  proxRot += 2;
					System.out.printf("rot_%02d:\n",pRot.peek());
				  }
break;
case 29:
//#line 131 "exemploGC.y"
{
			 							System.out.println("\tPOPL %EAX   # desvia se falso...");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1);
										}
break;
case 30:
//#line 136 "exemploGC.y"
{
				  		System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
							System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
							pRot.pop();
							}
break;
case 31:
//#line 142 "exemploGC.y"
{	
											pRotSel.push(proxRot);  proxRot += 2;
															
											System.out.println("\tPOPL %EAX");
											System.out.println("\tCMPL $0, %EAX");
											System.out.printf("\tJE rot_%02d\n", pRotSel.peek());
										}
break;
case 32:
//#line 151 "exemploGC.y"
{
											System.out.printf("rot_%02d:\n",pRotSel.peek()+1);
											pRotSel.pop();
										}
break;
case 33:
//#line 160 "exemploGC.y"
{
					pRot.push(proxRot);  proxRot += 4;
					System.out.printf("rot_%02d:\n",pRot.peek()+3); /*rotulo antes do teste*/

				  }
break;
case 34:
//#line 166 "exemploGC.y"
{

					System.out.println("\tPOPL %EAX   # desvia se falso...");
					System.out.println("\tCMPL $0, %EAX");
					System.out.printf("\tJE rot_%02d\n", (int)pRot.peek()+1); /*jump end*/

					System.out.printf("\tJMP rot_%02d\n", (int)pRot.peek()+2); /*jump cmd*/
					System.out.printf("rot_%02d:\n",pRot.peek());/*rotulo antes do incremento*/
				}
break;
case 35:
//#line 176 "exemploGC.y"
{
					System.out.printf("\tJMP rot_%02d\n", (int)pRot.peek()+3);
					System.out.printf("rot_%02d:\n",pRot.peek()+2); /*rotulo antes do comando*/
				}
break;
case 36:
//#line 181 "exemploGC.y"
{
					System.out.printf("\tJMP rot_%02d   # terminou cmd na linha de cima\n", pRot.peek());
					System.out.printf("rot_%02d:\n",(int)pRot.peek()+1);
					pRot.pop();
					}
break;
case 37:
//#line 186 "exemploGC.y"
{System.out.printf("\tJMP rot_%02d\n", pRot.peek()+1);}
break;
case 38:
//#line 187 "exemploGC.y"
{System.out.printf("\tJMP rot_%02d\n", pRot.peek());}
break;
case 42:
//#line 192 "exemploGC.y"
{
		System.out.println("\tPUSHL $1");
}
break;
case 43:
//#line 196 "exemploGC.y"
{
											System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
											System.out.printf("rot_%02d:\n",pRotSel.peek());
								
										}
break;
case 45:
//#line 204 "exemploGC.y"
{
		    System.out.printf("\tJMP rot_%02d\n", pRotSel.peek()+1);
			System.out.printf("rot_%02d:\n",pRotSel.peek());
				}
break;
case 46:
//#line 212 "exemploGC.y"
{  System.out.println("\tPOPL %EDX");
  				  System.out.println("\tMOVL %EDX, _"+val_peek(2).sval);
				System.out.println("\tPUSHL %EDX");
               }
break;
case 47:
//#line 216 "exemploGC.y"
{ System.out.println("\tPUSHL $"+val_peek(0).sval); }
break;
case 48:
//#line 217 "exemploGC.y"
{ System.out.println("\tPUSHL $1"); }
break;
case 49:
//#line 218 "exemploGC.y"
{ System.out.println("\tPUSHL $0"); }
break;
case 50:
//#line 219 "exemploGC.y"
{ System.out.println("\tPUSHL _"+val_peek(0).sval); }
break;
case 52:
//#line 221 "exemploGC.y"
{ gcExpNot(); }
break;
case 53:
//#line 223 "exemploGC.y"
{ gcExpArit('+'); }
break;
case 54:
//#line 224 "exemploGC.y"
{ gcExpArit('-'); }
break;
case 55:
//#line 225 "exemploGC.y"
{ gcExpArit('*'); }
break;
case 56:
//#line 226 "exemploGC.y"
{ gcExpArit('/'); }
break;
case 57:
//#line 227 "exemploGC.y"
{ gcExpArit('%'); }
break;
case 58:
//#line 229 "exemploGC.y"
{ gcExpRel('>'); }
break;
case 59:
//#line 230 "exemploGC.y"
{ gcExpRel('<'); }
break;
case 60:
//#line 231 "exemploGC.y"
{ gcExpRel(EQ); }
break;
case 61:
//#line 232 "exemploGC.y"
{ gcExpRel(LEQ); }
break;
case 62:
//#line 233 "exemploGC.y"
{ gcExpRel(GEQ); }
break;
case 63:
//#line 234 "exemploGC.y"
{ gcExpRel(NEQ); }
break;
case 64:
//#line 236 "exemploGC.y"
{ gcExpLog(OR); }
break;
case 65:
//#line 237 "exemploGC.y"
{ gcExpLog(AND); }
break;
case 66:
//#line 239 "exemploGC.y"
{ 
			System.out.println("\tPUSHL _"+val_peek(1).sval);
			System.out.println("\tPUSHL _"+val_peek(1).sval);
			System.out.println("\tPUSHL $1");
			gcExpArit('+');
			System.out.println("\tPOPL %EDX");
  			System.out.println("\tMOVL %EDX, _"+val_peek(1).sval);
			 }
break;
case 67:
//#line 247 "exemploGC.y"
{ 
			System.out.println("\tPUSHL _"+val_peek(0).sval);
			System.out.println("\tPUSHL $1");
			gcExpArit('+');
			System.out.println("\tPOPL %EDX");
  			System.out.println("\tMOVL %EDX, _"+val_peek(0).sval);
			System.out.println("\tPUSHL _"+val_peek(0).sval);
			}
break;
//#line 1126 "Parser.java"
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
