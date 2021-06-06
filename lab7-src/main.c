#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "sub_string.s"

int main() {

	// variable declarations
	int start_index;
	int end_index;
	char *in_string = (char *)malloc(256*sizeof(char));
	char *out_string = (char *)malloc((1+end_index-start_index)*sizeof(char));

	// code to receive inputs from user
	printf("Enter a string: \n");
	scanf("%s", in_string);
	printf("Enter the start index: \n");
	scanf("%d", &start_index);
	printf("Enter the end index: \n");
	scanf("%d", &end_index);
	
	// calls the sub_string.s function from Assembly
	out_string = sub_string(in_string, start_index, end_index);

	// code to print the sub_string
	printf("The substring of the given string is '%s'", out_string);
	
	return 0;
}
