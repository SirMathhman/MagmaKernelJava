struct main{struct Point (*Point)(int,int,void *);
int (*exitCode)(void *);};
struct Point main_Point_(int  y ,int x ,void *main){return Point;}
int main_exitCode_(void *main){return 10;}int main(){struct main main={NULL,NULL};struct Point (*Point)(int,int,void *)=main_Point_;main.Point=Point;int (*exitCode)(void *)=main_exitCode_;main.exitCode=exitCode;return exitCode(&main);}