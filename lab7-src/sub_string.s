@ARM Assembly sub_string.s Program

.section	.data


.section	.text
.global		sub_string(char *in_string, int start_index, int end_index)

main:


	mov r7, $1	@exit syscall
	svc $0		@wake kernel
	.end
