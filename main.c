val main : () => Int = {import stdio;import math;native val printf : (format : String, value : Any) => Void;native val powf : (base : Float, exponent : Float) => Float;native val sqrtf : (value : Float) => Float;val message = "Hello World!";printf("%s", message);return 0;}