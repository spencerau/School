@ARM assembly compare program
.section	.data

askfirstnumber: .asciz "Enter the first number: "
asklastnumber: .asciz "Enter the second number: "

input: .asciz "%d"
outputEqual: .asciz "%d and %d are equal.\n"
outputDiff: .asciz "%d is strictly greater than %d by %d.\n"

firstNumber: .word 0
lastNumber: .word 0

.section	.text
.global		main

main:
		ldr r0, =askfirstnumber
		bl printf

		ldr r0, =input
		ldr r1, =firstNumber
		bl scanf

		ldr r0, =asklastnumber
		bl printf

		ldr r0, =input
		ldr r1, =lastNumber
		bl scanf

		ldr r0, =firstNumber
		ldr r1, =lastNumber

		ldr r4,[r0]
		ldr r5,[r1]

		cmp r4,r5
		blt lesser
		bgt greater
		beq equal

equal:
		ldr r0, =outputEqual
		mov r1,r4
		mov r2,r5
		bl printf
		b end

greater:
		ldr r0, =outputDiff
		mov r1,r4
		mov r2,r5
		sub r3,r4,r5
		bl printf
		b end

lesser:
		ldr r0, =outputDiff
		mov r1,r5
		mov r2,r4
		sub r3,r5,r4
		bl printf
		b end

end:
		mov r0, #0
		bl fflush

		mov r7, $1		@exit syscall
		svc $0			@wake kernel
		.end
