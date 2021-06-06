#include <stdio.h>
#include <string.h>
#include <stdlib.h>

extern char* sub_string(char* in_string, int start_index, int end_index) {
	// substring to be returned
	char *out_string;

	// for loop that iteraterates through the *in_string and finds the 
	// substring
	int i = start_index-1;
	int j = 0;
	for (i; i < end_index; i++) {
		out_string[j] = in_string[i];
		j++;
		//out_string = strcat(out_string, in_string[i]);
	}

	return out_string;
}
/*
int main() {

	// variable declarations
	int start_index;
	int end_index;
	char *in_string = (char *)malloc(20*sizeof(char));
	char *out_string = (char *)malloc(20*sizeof(char));

	// code to receive inputs from user
	printf("Enter a string: \n");
	//fgets(in_string, (sizeof(char)*256), stdin);
	scanf("%s", in_string);
	printf("Enter the start index: \n");
	scanf("%d", &start_index);
	printf("Enter the end index: \n");
	scanf("%d", &end_index);
	
	// calls the sub_string.s function from Assembly
	out_string = sub_string(in_string, start_index, end_index);

	// code to print the sub_string
	printf("The substring of the given string is '%s'\n", out_string);
	
	return 0;
}*/
