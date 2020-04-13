#include <stdio.h>

struct Point {
	int (*length)(void *);
};

int Point_length_(void *Point){
	return 10;
}

struct Point Point_(){
	struct Point Point={NULL};
	int (*length)(void *)=Point_length_;
	Point.length = length;
	return Point;
}

int main() {
	struct Point (*Point)()=Point_;
	struct Point p = Point();
	int length = p.length(&p);
	printf("%i", length);
	return 0;
}