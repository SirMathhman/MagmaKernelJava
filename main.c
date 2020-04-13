struct Point {
	int (*Point_length)(struct Point);
};

int Point_length_(void* Point){
	return 10;
}

struct Point Point_(int (*Point_length)(struct Point)){
	struct Point Point = {NULL};
	int (*Point_length)(struct Point) = Point_length_;
	Point.length = Point_length;
	return Point;
}

struct Point (*Point)()=Point_;