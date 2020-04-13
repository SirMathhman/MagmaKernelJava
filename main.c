#include <stdio.h>

int main_(){
	printf("%s", "Hello World!");
	return 0;
}

int main(){
	int (*s)() = main_;
	return s();
}