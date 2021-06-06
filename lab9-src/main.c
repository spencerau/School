#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#define READABLE_FILE "file_to_read.txt" /* File to be read during read operations */
#define BYTES_TO_READ_WRITE 819200 /* 800 KB */
#define MAX_BUFFER  1048576 /* 1 MB*/

/* Function for write without buffer */
void mywritec(char);

/* Functions for write with buffer */
void mywritebufsetup(int);
void myputc(char);
void mywriteflush(void);

/* Function for read without buffer */
int myreadc(void);

/* Functions for read with buffer */
void myreadbufsetup(int);
int mygetc(void);

/* Declare global variables for write operations here */
int fd_write = 1;

/* Declare global variables for read operations here */
int fd_read;

/* Main function starts */
int main()
{
    clock_t begin, end;
    int option, n, temp;
    const char *a="Writing byte by byte\n";
    const char *b="Writing using buffers\n";
    unsigned long i, bytes_to_write = BYTES_TO_READ_WRITE, bytes_to_read = BYTES_TO_READ_WRITE;
    char ch;
	int c;

    while(1)
    {
        printf("\n 1 - Write without buffering \n 2 - Write with buffering");
        printf("\n 3 - Read without buffering \n 4 - Read with buffering");
        printf(("\n 5 - Exit \n Enter the option: "));
        scanf("%d", &option);
        switch(option)
        {
            case 1: /* Write without buffer */
                begin = clock();
                for (i=0;i<bytes_to_write;i++)
                {
                    ch = a[i%strlen(a)];
                    mywritec(ch);
                }
                end = clock();
                printf("\n Time to write without buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 2:  /* Write with buffer */
                printf("\n Enter the buffer size in bytes: ");
                scanf("%d", &n);
                mywritebufsetup(n);
                begin = clock();
                for (i=0;i<bytes_to_write;i++)
                {
                    ch = b[i%strlen(b)];
                    myputc(ch);
                }
                mywriteflush();
                end = clock();
                printf("\n Time to write with buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 3:  /* Read without buffer */
                fd_read = open(READABLE_FILE, O_RDONLY);
                if(fd_read < 0)
                {
                    printf("\n Error opening the readable file \n");
                    return 1;
                }
                begin = clock();
                for (i=0;i<bytes_to_read;i++)
                {  /* you may need to modify this slightly to print the character received and also check for end of file */
					c = myreadc();
                    if(c == -1)
                    {
                        printf("\n End of file \n");
                        break;
                    }
					printf("%c", c);
                }
                end = clock();
                if(close(fd_read))
                {
                    printf("\n Error while closing the file \n ");
                }
                printf("\n Time to read without buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            case 4:  /* Read with buffer */
                printf("\n Enter the buffer size in bytes: ");
                scanf("%d", &n);
                myreadbufsetup(n);
                fd_read = open(READABLE_FILE, O_RDONLY);
                if(fd_read < 0)
                {
                    printf("\n Error opening the readable file \n");
                    return 1;
                }
                begin = clock();
                for (i=0;i<bytes_to_read;i++)
                { /* you may need to modify this slightly to print the character received and also check for end of file */
					c = mygetc();
                    if(c == -1)
                    {
                        printf("\n End of file \n");
                        break;
                    }
					printf("%c", c);
                }
                end = clock();
                if(close(fd_read))
                {
                    printf("\n Error while closing the file \n ");
                }
                printf("\n Time to read with buffering: %f secs\n",(double)(end - begin)/CLOCKS_PER_SEC);
                break;

            default:
                return 0;
        }
    }
}


/*---WRITE WITH/WITHOUT BUFFER---*/


	/* Use the system call write() to write char 'ch' to standard output (file descriptor 1) */

void mywritec(char ch) {
	write(fd_write, &ch, 1);
}


/* Define constant MAX_BUFFER to be 1048576 */
/* The constant will be the upper-bound on buffer size */
#define MAX_BUFFER 1048576
	
/* Declare global array of MAX_BUFFER characters named "write_buf" 
 * that will serve as an output buffer */
char write_buf[MAX_BUFFER];

/* Declare a global character pointer, wp, that will point to a location in the buffer */
char *wp;

/* Declare a global int, write_buf_size, that stores the size of the output buffer being used 
 * at the current time */
//use size_t and not int; also use an additional written_chars int
size_t write_buf_size;
size_t char_count;


/* Verify that n is a positive integer <= MAX_BUFFER,
 * and store n in the global variable write_buf_size */
/* Intialize wp to point to the first byte of buffer 
 * (using char_count int instead to increment through the buffer) */

void mywritebufsetup(int n) {
	if (n < 0 || n >= MAX_BUFFER) return;

	write_buf_size = n;
	char_count = 0;
}

void myputc(char ch) {
	/* Place character ch in the location given by wp, and incerment wp */
	write_buf[char_count] = ch;
	char_count++;
	/* If the buffer is ful (contains  write_buf_size char), write the entire buffer */
	if (char_count == write_buf_size) mywriteflush(); //just use mywriteflush() instead
}

/* Note: this function will be called after all calls to myputc() have been made */
/* if any char reamin in the write buffer, write them to standard output */

void mywriteflush() {
	write(fd_write, write_buf, char_count);
	char_count = 0;
}


/*---READ WITH/WITHOUT BUFFER---*/


/* Use read() system call to read a char from text file(file descriptor is 'fd_read') */

int myreadc(void) {
	/* If read() indicates end-of-file, return -1 to caller; otherwise return the char that was read in the low-order byte of the int(be careful to avoid 8? sign extension */
	int ch;
	if (read(fd_read, &ch, 1) == 0) return -1;
	return ch & 0xFF; // 1111 1111
}

/* Declare a global array of MAX_BUFFER char named "read_buf" that will server as an input buffer */
char read_buf[MAX_BUFFER];

char* rp; //point to location in buffer

size_t read_buf_size; //current size of buffer

ssize_t read_count; //how many bytes were read; ssize_t is size of blocks

void myreadbufsetup(int n) {
	/* Verify that n is a positive int <= MAX_BUFFER, and store n in global variable
	 * read_buf_size */
	if (n <= 0 || n > MAX_BUFFER) return;
	read_buf_size = n;
	/* Set read_count to zero */
	read_count = 0;
}

int mygetc() {
	/* If read_count <= 0, call read() to read up to read_buf_size bytes into 
	 * read_buf from text file (file descriptor is fd_read */
	if (read_count <= 0) {
		read_count = read(fd_read, read_buf, read_buf_size);
		/* Set rp to first location in buffer */
		/* if read_count == 0, return -1 for EOF */
		if (read_count == 0) return -1;
		rp = read_buf;
	}
	/* Extract next char from buffer, increment rp, and decrement read_count */
	char ch = *rp;
	rp++;
	read_count--;
	/* Return char from buffer in low-order byte of int */
	int c = (int)ch;
	return c & 0xFF;
}



