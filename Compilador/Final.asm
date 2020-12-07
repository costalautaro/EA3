include number.asm
include macros2.asm

.MODEL LARGE ;Modelo de Memoria
.386 ;Tipo de Procesador
.STACK 200h ;Bytes en el Stack

.DATA
	_cte_s0	db	"Ingrese un valor pivot mayor o igual a 1: ",'$', 42 dup (?)
	pivot	dd	?
	_1	dd	1.0
	@pivot	dd	?
	_cte_s1	db	"El valor debe ser >=1",'$', 19 dup (?)
	_5	dd	5.0
	@contL	dd	2.0
	_cte_s2	db	"La lista tiene menos elementos que el indicado",'$', 44 dup (?)
	resul	dd	?
	_cte_s3	db	"El resultado es: ",'$', 17 dup (?)


.CODE

start:
	MOV EAX,@DATA
	MOV DS,EAX
	MOV ES,EAX

ETIQ00:
	displayString _cte_s0
	newline 1

	GetFloat pivot

	FLD pivot
	FSTP @pivot

	FLD @pivot
	FLD _1
	FCOM
	FSTSW AX
	SAHF

	JNA ETIQ08

	displayString _cte_s1
	newline 1

	JAE ETIQfin

ETIQ08:
ETIQ09:
	FLD @pivot
	FLD @contL
	FCOM
	FSTSW AX
	SAHF

	JAE ETIQ14

	displayString _cte_s2
	newline 1

	JMP ETIQfin

	FLD _0
	FSTP @auxResultado

	FLD _0
	FSTP @flagPivot

	FLD @flagPivot
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JAE ETIQ22

	FLD @auxResultado
	FLD _5
	FADD
	FSTP @auxResultado
	FFREE

	FLD [18]
	FSTP @auxResultado

	FLD @flagPivot
	FLD _1
	FADD
	FSTP @flagPivot
	FFREE

	FLD [20]
	FSTP @flagPivot

	FLD @flagPivot
	FLD @pivot
	FCOM
	FSTSW AX
	SAHF

	JAE ETIQ28

	FLD @auxResultado
	FLD _1
	FADD
	FSTP @auxResultado
	FFREE

	FLD [24]
	FSTP @auxResultado

	FLD @flagPivot
	FLD _1
	FADD
	FSTP @flagPivot
	FFREE

	FLD [26]
	FSTP @flagPivot

	FLD @auxRes
	FSTP resul

ETIQ29:
	displayString _cte_s3
	newline 1

ETIQ31:
	DisplayFloat resul,1
	newline 1


ETIQfin: 
	MOV EAX, 4C00h
	INT 21h

	END start