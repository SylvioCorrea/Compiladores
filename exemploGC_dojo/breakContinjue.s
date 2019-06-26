.text

#	 nome COMPLETO e matricula dos componentes do grupo...
#

.GLOBL _start


_start:
	PUSHL $1
	POPL %EDX
	MOVL %EDX, _b
	PUSHL %EDX
	POPL %EDX
rot_01:
	PUSHL _b
	PUSHL $15
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_02
	PUSHL _b
	PUSHL $3
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETE  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_03
	JMP rot_01
	JMP rot_04
rot_03:
rot_04:
	PUSHL _b
	PUSHL _b
	PUSHL $1
	POPL %EDX
	POPL %EAX
	ADDL %EDX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _b
	POPL %EDX
	MOVL $_str_0Len, %EDX
	MOVL $_str_0, %ECX
	CALL _writeLit
	PUSHL _b
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL _b
	PUSHL $7
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETG  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_05
	JMP rot_02
	JMP rot_06
rot_05:
rot_06:
		# terminou o bloco...
	JMP rot_01   # terminou cmd na linha de cima
rot_02:
	PUSHL $1
	POPL %EDX
	MOVL %EDX, _a
	PUSHL %EDX
rot_10:
	PUSHL _a
	PUSHL $15
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETL  %AL
	PUSHL %EAX
	POPL %EAX   # desvia se falso...
	CMPL $0, %EAX
	JE rot_08
	JMP rot_09
rot_07:
	PUSHL _a
	PUSHL _a
	PUSHL $1
	POPL %EDX
	POPL %EAX
	ADDL %EDX, %EAX
	PUSHL %EAX
	POPL %EDX
	MOVL %EDX, _a
	JMP rot_10
rot_09:
	PUSHL _a
	PUSHL $3
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETE  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_11
	JMP rot_07
	JMP rot_12
rot_11:
rot_12:
	MOVL $_str_1Len, %EDX
	MOVL $_str_1, %ECX
	CALL _writeLit
	PUSHL _a
	POPL %EAX
	CALL _write
	CALL _writeln
	PUSHL _a
	PUSHL $7
	POPL %EAX
	POPL %EDX
	CMPL %EAX, %EDX
	MOVL $0, %EAX
	SETG  %AL
	PUSHL %EAX
	POPL %EAX
	CMPL $0, %EAX
	JE rot_13
	JMP rot_08
	JMP rot_14
rot_13:
rot_14:
		# terminou o bloco...
	JMP rot_07   # terminou cmd na linha de cima
rot_08:



#
# devolve o controle para o SO (final da main)
#
	mov $0, %ebx
	mov $1, %eax
	int $0x80


#
# Funcoes da biblioteca (IO)
#


_writeln:
	MOVL $__fim_msg, %ECX
	DECL %ECX
	MOVB $10, (%ECX)
	MOVL $1, %EDX
	JMP _writeLit
_write:
	MOVL $__fim_msg, %ECX
	MOVL $0, %EBX
	CMPL $0, %EAX
	JGE _write3
	NEGL %EAX
	MOVL $1, %EBX
_write3:
	PUSHL %EBX
	MOVL $10, %EBX
_divide:
	MOVL $0, %EDX
	IDIVL %EBX
	DECL %ECX
	ADD $48, %DL
	MOVB %DL, (%ECX)
	CMPL $0, %EAX
	JNE _divide
	POPL %EBX
	CMPL $0, %EBX
	JE _print
	DECL %ECX
	MOVB $'-', (%ECX)
_print:
	MOVL $__fim_msg, %EDX
	SUBL %ECX, %EDX
_writeLit:
	MOVL $1, %EBX
	MOVL $4, %EAX
	int $0x80
	RET
_read:
	MOVL $15, %EDX
	MOVL $__msg, %ECX
	MOVL $0, %EBX
	MOVL $3, %EAX
	int $0x80
	MOVL $0, %EAX
	MOVL $0, %EBX
	MOVL $0, %EDX
	MOVL $__msg, %ECX
	CMPB $'-', (%ECX)
	JNE _reading
	INCL %ECX
	INC %BL
_reading:
	MOVB (%ECX), %DL
	CMP $10, %DL
	JE _fimread
	SUB $48, %DL
	IMULL $10, %EAX
	ADDL %EDX, %EAX
	INCL %ECX
	JMP _reading
_fimread:
	CMPB $1, %BL
	JNE _fimread2
	NEGL %EAX
_fimread2:
	RET



#
# area de dados
#
.data
#
# variaveis globais
#
_a:	.zero 4
_b:	.zero 4
_c:	.zero 4

#
# area de literais
#
__msg:
	.zero 30
__fim_msg:
	.byte 0


_str_0:
	 .ascii " b =  "
_str_0Len = . - _str_0
_str_1:
	 .ascii " a =  "
_str_1Len = . - _str_1
