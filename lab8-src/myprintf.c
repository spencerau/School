#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//extern void printx();
//extern void printd();

int myprintf(const char * format, ...) { // ... = variable function
	int stackPointer = (int)&format; // pointer for the stack
	stackPointer += 4;
	int i = 0;
	while (format[i] != '\0') {
		if (format[i] == '%') {	// if %, access the stack to print out variable
			i++;
			if (format[i] == 'c') {
				char character = *(char *)stackPointer; // cast as character point.
				putchar(character);
			}
			else if (format[i] == '%') {
				putchar('%');
				stackPointer -= 4;
			}
			else if (format[i] == 's') {
				char *string = *(char **)stackPointer; // cast as string point.
				int j = 0;				
				while (string[j] != '\0') {
					putchar(string[j]);
					j++;
				}
			}
			else if (format[i] == 'x') {
				int hex = *(int *)stackPointer;
				printx(hex);
				//putchar('%');
			}
			else if (format[i] == 'd') {
				int dec = *(int *)stackPointer;				
				printd(dec);
				//putchar('%');
			}
			else {
				putchar(stackPointer); // if other types, just print out
			}
			stackPointer += 4; // iterate the stack point. by 4 bits
			i++;
		}
		else {
			putchar(format[i]);
			i++;
		}
	}
	//putchar('\n');		// change - commented this out
	return 0;
}
