@ARM Assembly Main.s Program

.section	.data
askString:	.asciz "Enter a string: \n"
inputS:		.asciz "%s"

askIndex1:	.asciz "Enter the start index: \n"
askIndex2:	.asciz "Enter the end index: \n"
inputI:		.asciz "%d"

output:		.asciz "The substring of the given string is %s.\n"

string:		.space 100
index1:		.word 0
index2:		.word 0
subString:	.space 100

.section	.text
.globl		main
.globl		sub_string

main:
		ldr r0, =askString	@takes input for string
		bl printf
		ldr r0, =inputS
		ldr r1, =string
		bl scanf

		ldr r0, =askIndex1	@takes input for start index
		bl printf
		ldr r0, =inputI
		ldr r1, =index1
		bl scanf

		ldr r0, =askIndex2	@takes input for end index
		bl printf
		ldr r0, =inputI
		ldr r1, =index2
		bl scanf

		ldr r0, =string		@pushes parameters for sub_string onto
		ldr r1, =index1		@the reg
		ldr r2, =index2
		bl sub_string		@calls sub_string.c
		mov r1, r0			@result of sub_string.c stored in r0
		
		ldr r0, =output
		bl printf

		mov r0, $0
		bl fflush

		mov r7, $1		@exit syscall
		svc $0			@wake kernel
		.end

