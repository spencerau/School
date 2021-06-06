#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "myprintf.c"
#include <limits.h>

int main(int argc, char *argv[]) {
	int x = 0xFBA32D58;
	int d = 42;
	int min = INT_MIN;
	int max = INT_MAX;
	char c1 = 'A';
	char c2 = 'd';
	char *s1 = "teststring";
	char *s2 = "spencerau";

/*
	printf("Type in a number \n");
	scanf("%d", &n);
	getchar();
	printf("Type in a character \n");
	c = getchar();
	getchar();
	printf("Type in a string \n");
	fgets(s, 100, stdin);
	//getchar();
*/
	//myprintf("number: %x, char: %c, string: %s", x1, c1, s1);
	//myprintf("number: %d, char: %c, string: %s", d2, c2, s2);
	myprintf("char: %c\n", c1);	
	myprintf("string: %s\n", s2);
	myprintf("char: %c, string: %s\n", c2, s1);
	myprintf("min: %d\n", min);
	myprintf("max: %d\n", max);
	myprintf("number: %d\n", d);
	myprintf("hex: %x\n", x);
	//myprintf("test statement\n");
	return 0;
}
