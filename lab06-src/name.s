@ARM assembly name program
.section	.data

askfirstname: .asciz "Enter your first name: "
asklastname: .asciz "Enter your last name: "

input: .asciz "%s"
output: .asciz "Hello, %s %s.\n"

firstName: .space 100
lastName: .space 100

.section	.text
.global		main

main:
		ldr r0, =askfirstname
		bl printf

		ldr r0, =input
		ldr r1, =firstName
		bl scanf

		ldr r0, =asklastname
		bl printf

		ldr r0, =input
		ldr r1, =lastName
		bl scanf

		ldr r0, =output
		ldr r1, =firstName
		ldr r2, =lastName
		bl printf

		mov r0, $0
		bl fflush

		mov r7, $1		@exit syscall
		svc $0			@wake kernel
		.end
