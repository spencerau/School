//ARM Assembly printinteger.s Program

.section	.data

.section	.text

.global printx
.global printd

printx:
	//push {lr}
	//esp			//stack pointer
	//pop {r0}		//pops the hex integer arg in stack into r0
	//ldr r7, =0
	//ldrb r2, [r7]		//counter for stack size
	//ldr r2, =0
	stmfd sp!, {r3, fp, lr}	//loads parameters into stack, fp is the frame 
							//pointer and lr is the link register
							//stack is full descending type
	mov r3, #0			//moves a 0 into reg3, resetting it
	b loop1

	loop1:
		cmp r0, #0		//while loop for when the integer has no more bits
		beq loop2		//terminate while loop if int has no bits
		add r3, #1
		//mov r1, r0
		and r1, r0, #0xF//takes the LSB for hex in the integer, stores in r1
		push {r1}		//pushes the current hex value in r1 onto the stack
		lsr r0, r0 , #4 //logically shifts r0 right by 4 bits
		//add r3, #1		//increments the counter by 1 for stack size
		b loop1

	loop2:
		cmp r3, #0		//while loop that goes over size of stack
		beq done
		sub r3, #1
		pop {r1}		//pops the current MSB for hex onto r0
		cmp r1, #10		//compares it to 10 to find the hex value
		blt less		//branches if the hex value < 10
		b greater		//branches if the hex value >= 10
		//brlt less		//branches if the hex value < 10

		greater: 
			add r1, #87		//adds 87 to the value at r0 in order to make it the
							//ASCII value of the lowercase letter in hex
			b print
		
		less:
			add r0, #48		//adds 48 to the value at r0 in order to make it the
							//ASCII value of the number
			b print
		
		print:
			mov r0, r1	//moves hex char to r0 to print
			bl putchar
			cmp r3, #0
			bgt loop2
			b done
			//sub r3, #1	//decrements the stack size counter by 1

	done:
		ldmfd sp!, {r3, fp, pc}	//releases parameters from stack
		bx lr					//goes to the next C line


printd:

	//push {lr}
	//pop {r4}	//pops the signed 32 bit int parameter from stack into r4
/*
	ldrb r1, [r7]		//sets r1 as a counter
	ldr	r8, =1
	ldrb r2, [r8]
	ldrb r0, [r7]		//r0 stores the decimal value to be printed out

	ldr r0, =0
	ldr r1, =0
	ldr r2, =1
*/
	stmfd sp!, {r4-r9, fp, lr}	//stores the parameters unto the stack
	mov r4, #10		//uses r4 for the number to divide by
	mov r5, #0		//clears both r5, r6, and r7 by setting to 0
	mov r6, #0
	mov r7, #0		//sets reg7 as a counter	
	mov r8, r0		//stores the 32 bit num in both r8 and r9
	mov r9, r0

	cmp r8, #0
	beq printzero
	lsr r8, r8, #31	//logical shift right 31 bits to get MSB
	cmp r8, #1		//edge case for negative number; if MSB is 1 then negative
	bne loop3		//if positive, then start divison loop
	mov r0, #45		//45 is the ASCII '-' sign
	bl putchar

	cmp r9, #0x8	//edge case for if the num is MIN_VALUE
	beq min			//print out the MIN_VALUE
	b negative

	negative:		//edge case : negative number
		mov r8, #-1	//sets r8 to -1
		mul r9, r9, r8	//sets the num in r9 as a negative
		b loop3

	min:	//edge case : MIN_VALUE
		mov r0, #56	//first hex value for MV is '8'
		push {r0}
		ldr r9, =214748364	//hardcode the MIN_VALUE into r9
		add r7, #1	//iterates counter
		b loop3	

	loop3:
		cmp r9, #0				//while loop to divide by 10
		beq printdec		
		mov r6, r9				//stores value of num into r6
		ldr r1, =0x66666667	
		smull r1, r2, r1, r9	//smull interprets r1 and r9 as 2's comp signed 
								//ints; multiplies them, and places the least
								//significant 32 bits of the product into r1, 
								//and the most significant 32 bits into r2
		
		mov r1, r9, LSR #31		//logical shift right 31 bits for r1
		mov r2, r2, ASR #2		//arithmetic shift right 2 bits for r2
		add r9, r2, r1			//adds the 2 shifts, storing in r9 
		mul r5, r9, r4			//multiply r9 and r4, store val in r5
		sub r5, r6, r5			//stores the correct modulo val in r5		
		add r5, #48				//convert the modulo to ASCII representation
		push {r5}				//store the ASCII Modulo in the stack
		
		add r7, #1				//iterate counter
		b loop3

	printdec:		//loop that will pop each hex value from the stack, and
					//print them out via putchar()
		cmp r7, #0
		beq done2		
		pop {r0}
		sub r7, #1	//decrement counter for each hex value
		bl putchar
		b printdec

	printzero:
		ldr r0, =0
		bl putchar
		b done2

	done2:
		ldmfd sp!, {r4-r9, fp, pc}
		bx lr
//endprintd:

	mov r7, $1	//exit syscall
	svc $0		//wake kernel
	.end
