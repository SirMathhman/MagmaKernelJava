#include <stddef.h>
struct main{int (*exitCode)(void *);};
int main_exitCode_(void *main){return 10;}
int main(){struct main main={NULL};
int (*exitCode)(void *)=main_exitCode_;
main.exitCode=exitCode;return exitCode(&main);}