include number.asm
include macros2.asm

.MODEL LARGE ;Modelo de Memoria
.386 ;Tipo de Procesador
.STACK 200h ;Bytes en el Stack

.DATA
	_cte_s0	db	"Ingrese un valor pivot mayor o igual a 1: ",'$', 42 dup (?)
	pivot	dd	?
	_1	dd	1.0
	_cte_s1	db	"Por favor, ingrese nuevamente un numero: ",'$', null dup (?)
	_2	dd	2.0
	_0	dd	0.0
	@contador	dd	?
	@auxPromedio	dd	?
	@pivot	dd	?
	_3	dd	3.0
	_4	dd	4.0
	_5	dd	5.0
	_6	dd	6.0
	_7	dd	7.0
	_8	dd	8.0
	@resultado	dd	?
	resul	dd	?
	_cte_s2	db	"El resultado es: ",'$', 17 dup (?)
	_cte_s3	db	"La lista esta vacia, resultado: 0",'$', 33 dup (?)
	_cte_s4	db	"El valor debe ser >=1",'$', 21 dup (?)
	_cte_s5	db	"No existen elementos para el calculo, el resultado es 0",'$', 55 dup (?)


.CODE

start:
	MOV EAX,@DATA
	MOV DS,EAX
	MOV ES,EAX

ETIQ00:
	displayString _cte_s0
	newline 1

ETIQ02:
	GetFloat pivot

	FLD pivot
	FLD _1
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ09

	DisplayFloat @errorPivot,1
	newline 1

	displayString _cte_s1
	newline 1

	GetFloat pivot

ETIQ09:
	FLD pivot
	FSTP @pivot

	FLD _0
	FSTP @contador

	FLD _0
	FSTP @auxPromedio

	FLD _2
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ19

	FLD @auxPromedio
	FLD _2
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [15]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [17]
	FSTP @contador

ETIQ19:
	FLD _3
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ26

	FLD @auxPromedio
	FLD _3
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [22]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [24]
	FSTP @contador

ETIQ26:
	FLD _4
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ33

	FLD @auxPromedio
	FLD _4
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [29]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [31]
	FSTP @contador

ETIQ33:
	FLD _5
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ40

	FLD @auxPromedio
	FLD _5
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [36]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [38]
	FSTP @contador

ETIQ40:
	FLD _6
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ47

	FLD @auxPromedio
	FLD _6
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [43]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [45]
	FSTP @contador

ETIQ47:
	FLD _7
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ54

	FLD @auxPromedio
	FLD _7
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [50]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [52]
	FSTP @contador

ETIQ54:
	FLD _8
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JA ETIQ61

	FLD @auxPromedio
	FLD _8
	FADD
	FSTP @auxPromedio
	FFREE

	FLD [57]
	FSTP @auxPromedio

	FLD @contador
	FLD _1
	FADD
	FSTP @contador
	FFREE

	FLD [59]
	FSTP @contador

ETIQ61:
	FLD @contador
	FLD _0
	FCOM
	FSTSW AX
	SAHF

	JNE ETIQ66

	DisplayFloat @errorMayor,1
	newline 1

	JMP ETIQfin

ETIQ66:
	FLD @auxPromedio
	FLD @contador
	FDIV
	FSTP @auxPromedio
	FFREE

	FLD [67]
	FSTP @resultado

	FLD @resultado
	FSTP resul

ETIQ70:
	displayString _cte_s2
	newline 1

ETIQ72:
	DisplayFloat resul,1
	newline 1


ETIQfin: 
	MOV EAX, 4C00h
	INT 21h

	END start