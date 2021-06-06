#include <stdio.h>
#include <string.h>
#include <stdlib.h>

void memdump(char *p, int len) {

	char array[17];
	int hex = 16;
	
	int i;
  	for (i = 0; i < len; i++) {
		if ((i % hex) == 0) {
			if (i != 0) {
				printf(" %s\n", array);
			}
			printf("0x%08x", p+i); //prints hex address	
		}
		printf(" %02x", p[i]); //prints out individiaul hex bytess in pairs	
		if ((p[i] < 32) || (p[i] > 127)) {
			array[i % hex] = '.';
		} else {
			array[i % hex] = p[i];
		}
		array[(i % hex)+1] = '\0';
	}
	while ((i % hex) != 0) {
		printf("	");
		i++;
	}
	printf(" %s\n", array);
}

struct X {
	char a;
	int i;
	char b;
	int *p;
};

struct List {
	char *str;
	struct List *next;
};

int main() {
	char str[20];
	int a = 5;
	int b = -5;
	double y = 12;
	struct X x;
	struct List *head;

	x.a = 'A';
	x.i = 9;
	x.b = '0';
	x.p = &x.i;
	strcpy(str, "Hello world\n");
	printf("&x=0x%x\n", &x.a);
	printf("&y=0x%x\n", &y);

	memdump((char *) &x.a, 64);
	head = (struct List *)malloc(sizeof(struct List));
	head->str = strdup("Welcome ");
	head->next = (struct List *)malloc(sizeof(struct List));
	head->next->str = strdup("to ");
	head->next->next = (struct List *)malloc(sizeof(struct List));
	head->next->next->str = strdup("cs250");
	head->next->next->next = NULL;
	printf("head=0x%x\n", head);
	memdump((char *) head, 128);
}
	
