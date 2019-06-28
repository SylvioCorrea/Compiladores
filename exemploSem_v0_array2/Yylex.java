/* The following code was generated by JFlex 1.4.3 on 6/27/19, 10:41 PM */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 6/27/19, 10:41 PM from the specification file
 * <tt>exemploSem.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\45\1\2\2\0\1\3\22\0\1\45\1\0\1\44\1\0"+
    "\1\4\1\0\1\21\1\0\1\20\1\20\1\0\1\20\1\20\1\0"+
    "\1\20\1\0\12\1\1\0\1\20\1\0\1\20\1\20\2\0\1\7"+
    "\1\43\1\10\1\43\1\11\1\15\6\43\1\16\1\14\1\13\2\43"+
    "\1\6\1\17\1\5\6\43\1\20\1\0\1\20\1\0\1\12\1\0"+
    "\1\40\1\30\1\42\1\25\1\32\1\41\1\35\1\43\1\22\2\43"+
    "\1\31\1\37\1\23\1\26\2\43\1\34\1\33\1\24\1\27\1\36"+
    "\4\43\1\20\1\0\1\20\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\2\3\1\1\1\4\1\5\1\1"+
    "\10\4\1\1\1\6\2\0\1\7\1\4\1\10\10\4"+
    "\1\0\1\11\2\0\1\12\10\4\2\0\2\4\1\13"+
    "\3\4\1\14\1\15\1\4\2\0\6\4\2\0\1\16"+
    "\1\4\1\17\1\20\1\21\1\4\2\0\1\22\1\4"+
    "\2\0\1\23\1\24\2\0\1\25\1\26";

  private static int [] zzUnpackAction() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\114\0\46\0\162\0\230\0\276\0\46"+
    "\0\344\0\u010a\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8\0\u01ee"+
    "\0\u0214\0\u023a\0\u0260\0\u0286\0\u02ac\0\46\0\u02d2\0\276"+
    "\0\u02f8\0\u031e\0\u0344\0\u036a\0\u0390\0\u03b6\0\u03dc\0\u0402"+
    "\0\u023a\0\46\0\u0428\0\u044e\0\276\0\u0474\0\u049a\0\u04c0"+
    "\0\u04e6\0\u050c\0\u0532\0\u0558\0\u057e\0\u05a4\0\u05ca\0\u05f0"+
    "\0\u0616\0\276\0\u063c\0\u0662\0\u0688\0\276\0\276\0\u06ae"+
    "\0\u06d4\0\u06fa\0\u0720\0\u0746\0\u076c\0\u0792\0\u07b8\0\u07de"+
    "\0\u0804\0\u082a\0\276\0\u0850\0\276\0\276\0\276\0\u0876"+
    "\0\u089c\0\u08c2\0\276\0\u08e8\0\u090e\0\u0934\0\276\0\46"+
    "\0\u095a\0\u0980\0\46\0\46";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\5\7\1\2\5\7"+
    "\1\10\1\11\1\12\2\7\1\13\2\7\1\14\2\7"+
    "\1\15\1\16\1\7\1\17\1\20\1\7\1\21\2\7"+
    "\1\22\1\23\47\0\1\3\46\0\1\4\50\0\1\24"+
    "\10\0\1\25\30\0\1\7\3\0\13\7\2\0\22\7"+
    "\23\0\1\26\25\0\1\7\3\0\13\7\2\0\1\7"+
    "\1\27\15\7\1\30\2\7\3\0\1\7\3\0\13\7"+
    "\2\0\4\7\1\31\4\7\1\32\10\7\3\0\1\7"+
    "\3\0\13\7\2\0\4\7\1\33\15\7\3\0\1\7"+
    "\3\0\13\7\2\0\2\7\1\34\17\7\3\0\1\7"+
    "\3\0\13\7\2\0\10\7\1\35\11\7\3\0\1\7"+
    "\3\0\13\7\2\0\4\7\1\36\15\7\3\0\1\7"+
    "\3\0\13\7\2\0\16\7\1\37\3\7\3\0\1\7"+
    "\3\0\13\7\2\0\5\7\1\40\14\7\2\0\44\41"+
    "\1\42\1\41\45\0\1\23\6\0\1\43\52\0\1\44"+
    "\33\0\1\7\3\0\13\7\2\0\2\7\1\45\17\7"+
    "\3\0\1\7\3\0\13\7\2\0\5\7\1\46\14\7"+
    "\3\0\1\7\3\0\13\7\2\0\2\7\1\47\17\7"+
    "\3\0\1\7\3\0\13\7\2\0\4\7\1\50\15\7"+
    "\3\0\1\7\3\0\13\7\2\0\12\7\1\51\7\7"+
    "\3\0\1\7\3\0\13\7\2\0\2\7\1\52\17\7"+
    "\3\0\1\7\3\0\13\7\2\0\1\53\21\7\3\0"+
    "\1\7\3\0\13\7\2\0\1\54\21\7\3\0\1\7"+
    "\3\0\13\7\2\0\1\7\1\55\20\7\11\0\1\56"+
    "\55\0\1\57\27\0\1\7\3\0\13\7\2\0\6\7"+
    "\1\60\13\7\3\0\1\7\3\0\13\7\2\0\12\7"+
    "\1\61\7\7\3\0\1\7\3\0\13\7\2\0\7\7"+
    "\1\62\12\7\3\0\1\7\3\0\13\7\2\0\1\63"+
    "\4\7\1\64\14\7\3\0\1\7\3\0\13\7\2\0"+
    "\5\7\1\65\14\7\3\0\1\7\3\0\13\7\2\0"+
    "\3\7\1\66\16\7\3\0\1\7\3\0\13\7\2\0"+
    "\1\7\1\67\20\7\3\0\1\7\3\0\13\7\2\0"+
    "\20\7\1\70\1\7\12\0\1\71\42\0\1\72\41\0"+
    "\1\7\3\0\13\7\2\0\7\7\1\73\12\7\3\0"+
    "\1\7\3\0\13\7\2\0\5\7\1\74\14\7\3\0"+
    "\1\7\3\0\13\7\2\0\1\7\1\75\20\7\3\0"+
    "\1\7\3\0\13\7\2\0\20\7\1\76\1\7\3\0"+
    "\1\7\3\0\13\7\2\0\12\7\1\77\7\7\3\0"+
    "\1\7\3\0\13\7\2\0\2\7\1\100\17\7\13\0"+
    "\1\101\42\0\1\102\40\0\1\7\3\0\13\7\2\0"+
    "\10\7\1\103\11\7\3\0\1\7\3\0\13\7\2\0"+
    "\20\7\1\104\1\7\3\0\1\7\3\0\13\7\2\0"+
    "\13\7\1\105\6\7\3\0\1\7\3\0\13\7\2\0"+
    "\2\7\1\106\17\7\3\0\1\7\3\0\13\7\2\0"+
    "\1\7\1\107\20\7\3\0\1\7\3\0\13\7\2\0"+
    "\1\110\21\7\14\0\1\111\42\0\1\112\37\0\1\7"+
    "\3\0\13\7\2\0\2\7\1\113\17\7\3\0\1\7"+
    "\3\0\13\7\2\0\4\7\1\114\15\7\15\0\1\115"+
    "\44\0\1\116\34\0\1\7\3\0\13\7\2\0\1\7"+
    "\1\117\20\7\16\0\1\120\1\121\35\0\1\122\55\0"+
    "\1\123\47\0\1\124\26\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2470];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\3\1\1\11\13\1\2\0"+
    "\1\11\12\1\1\0\1\11\2\0\11\1\2\0\11\1"+
    "\2\0\6\1\2\0\6\1\2\0\2\1\2\0\1\1"+
    "\1\11\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[84];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
    yyline = 1;
  }


  public int getLine() {
      return yyline;
  }



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 150) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return Parser.STRING;
          }
        case 23: break;
        case 8: 
          { return Parser.IF;
          }
        case 24: break;
        case 3: 
          { yyline++;
          }
        case 25: break;
        case 4: 
          { yyparser.yylval = new ParserVal(yytext());
                     return Parser.IDENT;
          }
        case 26: break;
        case 20: 
          { yyparser.setDebug(true);
          }
        case 27: break;
        case 16: 
          { return Parser.STRUCT;
          }
        case 28: break;
        case 11: 
          { return Parser.BOOL;
          }
        case 29: break;
        case 13: 
          { return Parser.MAIN;
          }
        case 30: break;
        case 7: 
          { return Parser.AND;
          }
        case 31: break;
        case 5: 
          { return (int) yycharat(0);
          }
        case 32: break;
        case 21: 
          { yyparser.setDebug(false);
          }
        case 33: break;
        case 17: 
          { return Parser.RETURN;
          }
        case 34: break;
        case 18: 
          { return Parser.DSTRUCT;
          }
        case 35: break;
        case 22: 
          { yyparser.listarTS();
          }
        case 36: break;
        case 14: 
          { return Parser.DOUBLE;
          }
        case 37: break;
        case 19: 
          { return Parser.FUNCTION;
          }
        case 38: break;
        case 12: 
          { return Parser.VOID;
          }
        case 39: break;
        case 9: 
          { yyparser.yylval = new ParserVal(yytext());
             return Parser.LITERAL;
          }
        case 40: break;
        case 10: 
          { return Parser.INT;
          }
        case 41: break;
        case 1: 
          { System.err.println("Error: unexpected character '"+yytext()+"' na linha "+yyline); return YYEOF;
          }
        case 42: break;
        case 2: 
          { yyparser.yylval = new ParserVal(Integer.parseInt(yytext())); 
         return Parser.NUM;
          }
        case 43: break;
        case 6: 
          { 
          }
        case 44: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
