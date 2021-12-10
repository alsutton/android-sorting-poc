# Android .sort overflow demo

The good list has values which can be compared easily, and so
gets correctly sorted as -10, 5, 0, 16

The bad list has values which cause integer overflows and so 
gets incorrectly sorted as 2, -2147483647, -2147483646, 0 which
is not the order the values are added in, and not the reverse order,
but, instead, a repeatable order which is determined by the sorting
algorithm used.