# FruitDependencyOrder

This was done as part of a interview for a company. The question was to give a order for a fruit name based dependency structure. The order that was needed was where the fruit name with no child dependincies appeared first and so on.

eg. for input:

apple 		 -> [banana, orange]
banana		 -> [strawberry]
strawberry 	 -> [orange]
orange 		 -> []
papaya 		 -> []

The result should be:

orange->papaya->strawberry->banana->apple


position for orange and papaya and be interchanged i.e. no order for ties is specified.

## Solution.

The main heart of my solution is in class DependencyOrder that uses a modification of Topological sort. 
