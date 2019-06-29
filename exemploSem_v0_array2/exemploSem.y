    
%{
  import java.io.*;
  import java.util.ArrayList;
%}


%token IDENT, INT, DOUBLE, BOOL, NUM, STRING, NUMDOUBLE
%token LITERAL, AND, VOID, MAIN, IF
%token DSTRUCT, STRUCT, FUNCTION, RETURN

%right '='
%nonassoc '>'
%left '+'
%left AND
%left '['  

%type <sval> IDENT
%type <ival> NUM
%type <dval> NUMDOUBLE
%type <obj> type
%type <obj> funcType
%type <obj> exp
%type <obj> maybeExp
%type <obj> lExp
%type <obj> restoStruct

%%

prog : { currClass = ClasseID.VarGlobal; } dList main ;

dList : decl dList | ;

decl : defFunc
     | defStruct
     | declVar
     ;

defFunc : FUNCTION funcType IDENT
     	    {	
     			TS_entry temp = ts.pesquisa($3);
     			if(temp!=null) {
     				yyerror("(sem) nome >" + $3 + "< jah declarado");
     			} else {
     				TabSimb escopoFuncao = new TabSimb();
     				temp = new TS_entry($3, (TS_entry)$2, ClasseID.NomeFuncao);
     				temp.setLocais(escopoFuncao);
     				ts.insert(temp);
     				auxTs = ts;
     				ts = escopoFuncao; //tabela de simbolos da funcao
     			}
     	    }
          '(' maybeParams ')' '{' {currClass = ClasseID.VarLocal; returnType = (TS_entry)$2;}
          funcDecList funcCmdList '}'
            {
                ts = auxTs; //tabela de simbolos global novamente
                currClass = ClasseID.VarGlobal;
            }
       ; 
       
funcType : VOID {$$ = Tp_VOID;}
         | type
         | IDENT
            {
                TS_entry temp = ts.pesquisa($1);
                if(temp==null) {
                    yyerror("(sem) nao existe tipo >"+$1+"<");
                } else {
                    $$ = temp;
                }
            }
         ;

//uma funcao pode ou nao ter parametros
maybeParams : lParams
            |
            ;

lParams : lParams ',' param
        | param
        ;
        
param : type IDENT
            {
                TS_entry temp = ts.pesquisa($2);
                if(temp!=null) {
                    yyerror("(sem) nome >" + $2 + "< jah declarado");
                } else {
                    temp = new TS_entry($2, (TS_entry)$1, ClasseID.NomeParam);
                    ts.insert(temp);
                }
            }
      | STRUCT IDENT IDENT
            {
                TS_entry temp = ts.pesquisa($3); //procura nome
                if(temp!=null) {
                    yyerror("(sem) nome >" + $3 + "< jah declarado");
                } else {
                    TS_entry structType = auxTs.pesquisa($2); //procura tipo da struct nas declaracoes globais
                    if(structType==null || structType.getClasse()!=ClasseID.NomeStruct) {
                        yyerror("(sem) >"+$2+"< nao eh um tipo de struct.");
                        structType = Tp_ERRO;
                    }
                    temp = new TS_entry($2, structType, ClasseID.NomeParam);
                    ts.insert(temp);
                }
            }
      ;


funcDecList : declVar funcDecList
            | 
            ;

funcCmdList : funcCmdList funcCmd
            | 
            ;

//TODO teste de tipo de retorno
funcCmd : cmd
        | RETURN exp ';'
            {
                if(returnType != (TS_entry)$2) {
                    yyerror("(sem) tipo de retorno incompativel com o tipo declarado da funcao");
                }
            }
        ;

defStruct : DSTRUCT IDENT '{'
                {
                    nodoAux = ts.pesquisa($2);
                    if (nodoAux != null) {
                        yyerror("(sem) nome >" + $2 + "< jah declarado");
                    } else {
                        nodoAux = new TS_entry($2, Tp_STRUCT, ClasseID.NomeStruct);
                        ts.insert(nodoAux);
                        auxTs = ts;
                        ts = new TabSimb();
                        currClass = ClasseID.CampoStruct;
                    }
                }       	
             lcampos
                {
                    nodoAux.setLocais(ts);
                    ts = auxTs;
                    currClass = ClasseID.VarGlobal;
                }
       	     '}' ';'
          ;

declVar : type {currentType = (TS_entry)$1;}
          TArray Lid ';'
        
        //declarando uma variavel de algum tipo de struct
        | STRUCT IDENT
            {
                currentType = auxTs.pesquisa($2);
                if(currentType==null || currentType.getClasse()!=ClasseID.NomeStruct) {
                    yyerror("(sem) nao existe struct do tipo >" + $2 + "<.");
                    currentType = Tp_ERRO;
                }
            }
          Lid ';'
        ;

lcampos : lcampos declVar
        | declVar
		;



// ===============================================================================================

Lid : Lid  ',' id 
    | id  
    ;

id : IDENT
        {
            TS_entry nodo = ts.pesquisa($1);
            if (nodo != null) {
                yyerror("(sem) variavel >" + $1 + "< jah declarado");
            } else {
                ts.insert(new TS_entry($1, currentType, currClass));
            }
        }
    ;

TArray : '[' NUM ']'  { currentType = new TS_entry("?", Tp_ARRAY, 
                                                   currClass, $2, currentType); }
          TArray
       |
       ;
 

             //
              // faria mais sentido reconhecer todos os tipos como ident! 
              // 
type : INT    { $$ = Tp_INT; }
     | DOUBLE  { $$ = Tp_DOUBLE; }
     | BOOL   { $$ = Tp_BOOL; }   
     ;



main :  VOID MAIN '(' ')' bloco ;

bloco : '{' listacmd '}';

listacmd : listacmd cmd
        |
         ;

cmd :  exp ';' 
      | IF '(' exp ')' '{' cmd '}'  {  if ( ((TS_entry)$3) != Tp_BOOL) 
                                     yyerror("(sem) expressão (if) deve ser lógica "+((TS_entry)$3).getTipo());
                             }     
       ;


exp : exp '+' exp { $$ = validaTipo('+', (TS_entry)$1, (TS_entry)$3); }
    | exp '>' exp { $$ = validaTipo('>', (TS_entry)$1, (TS_entry)$3); }
    | exp AND exp { $$ = validaTipo(AND, (TS_entry)$1, (TS_entry)$3); } 
    | NUM         { $$ = Tp_INT; }
    | NUMDOUBLE   {$$ = Tp_DOUBLE;} 
    | '(' exp ')' { $$ = $2; }
    | IDENT       { TS_entry nodo = ts.pesquisa($1);
                    if (nodo == null) {
                       nodo = auxTs.pesquisa($1);
                       if(nodo == null) {
                           yyerror("(sem) var <" + $1 + "> nao declarada"); 
                           nodo = Tp_ERRO;   
                       }
                    }
                    $$ = nodo.getTipo();
                  }
     
     | IDENT '.'
            {
                TS_entry temp = ts.pesquisa($1);
                if(temp==null || temp.getTipo().getClasse()!=ClasseID.NomeStruct) {
                    yyerror("(sem) nao existe campo de nome >"+$1+"<");
                    $$ = Tp_ERRO;
                } else {
                    nestedStructTs = ts;
                    ts = temp.getTipo().getLocais();
                }
            }
       restoStruct 
                {
                    ts = nestedStructTs;
                    $$ = (TS_entry)$3;
                    
                    
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
     
     | IDENT '(' maybeExp ')'
                    {
                        TS_entry temp = auxTs.pesquisa($1);
                        boolean ok = false;
                        if(temp!=null && temp.getClasse()==ClasseID.NomeFuncao) {
                            ArrayList<TS_entry> paramList = temp.getParams();
                            ArrayList<TS_entry> arguments = (ArrayList<TS_entry>)$3;
                            int parSize = paramList.size();
                            int argSize = arguments.size();
                            if(parSize==argSize) {
                                ok = true;
                                for(int i = 0; i<parSize; i++) {
                                    if(paramList.get(i).getTipo()!=arguments.get(i)) {
                                        //Uma das expressoes tem tipo diferente do aceito pelo parametro
                                        yyerror("(sem) tipo de expressao incompativel com tipo de parametro");
                                        ok = false;
                                        break;
                                    }
                                }
                            } else {
                                yyerror("(sem) a funcao requer "+parSize+" parametros, mas esta recebendo "+argSize);
                            }
                        } else {
                            yyerror("(sem) nao ha funcao com o nome >"+$1+"<");
                        }
                        if(ok) {
                            $$ = temp.getTipo();
                        } else {
                            $$ = Tp_ERRO;
                        }
                    }
     | exp '=' exp  {  $$ = validaTipo(ATRIB, (TS_entry)$1, (TS_entry)$3);  } 
     | exp '[' exp ']'  {  if ((TS_entry)$3 != Tp_INT) 
                              yyerror("(sem) indexador não é numérico ");
                           else 
                               if (((TS_entry)$1).getTipo() != Tp_ARRAY)
                                  yyerror("(sem) elemento não indexado ");
                               else 
                                  $$ = ((TS_entry)$1).getTipoBase();
                         } 
    ;

restoStruct : IDENT '.'
                {
                    TS_entry temp = ts.pesquisa($1);
                    if(temp==null || temp.getTipo().getClasse()!=ClasseID.NomeStruct) {
                        yyerror("(sem) nao existe campo de nome >"+$1+"< ou este campo nao eh um struct");
                        $$ = Tp_ERRO;
                    } else {
                        ts = temp.getTipo().getLocais();
                    }
                }
              restoStruct {$$ = (TS_entry)$3;}
              
            | IDENT
                {
                    TS_entry temp = ts.pesquisa($1);
                    if(temp==null) {
                        yyerror("(sem) nao existe campo de nome >"+$1+"<");
                        $$ = Tp_ERRO;
                    } else {
                        $$ = temp.getTipo();
                    }
                    
                }
            ;

maybeExp : lExp {$$ = $1;}
         | {$$ = new ArrayList<TS_entry>();}
         ;

lExp : lExp ',' exp
            {
                ArrayList<TS_entry> temp = (ArrayList<TS_entry>)$1;
                temp.add((TS_entry)$3);
                $$ = temp;
            }
     | exp
            {
                ArrayList<TS_entry> temp = new ArrayList<TS_entry>();
                temp.add((TS_entry)$1);
                $$ = temp;
            }
     ;
     
%%

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

