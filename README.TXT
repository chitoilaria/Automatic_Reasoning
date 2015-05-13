=============================
DESCRIPTION OF IMPLEMENTATION
=============================

- Run the Jar file TheoremProver.jar
  inside the jar folder

- From Menù, select File -> Load File…

- Select a text file (*.tpf)

========================================================================================

- The grammar used for the input file, is as following:

	NOT SYMBOL: -;

	OR SYMBOL: |;

	CONSTANTS: lowercase letters a..e or i..t followed by an integer (optional);

	VARIABLES: lowercase letters u..z followed by an integer (optional);

	PREDICATES: uppercase letters A..Z followed by an integer (optional);

	FUNCTIONS: lowercase letters f..h followed by an integer (optional);

- For the order, build a single text line as follow:

	Elem1 > Elem2 > Elem3 > .. > ElemN

according to the grammar, or the program can not read the file.

- For the weights, build a single text line, one by one for object as follow:

	Elem1 , value
	Elem2 , value
	Elem3 , value
	Elem4 , value
	..
	ElemN , value

according to the grammar, otherwise the weights are not considered.

========================================================================================

- To launch Javadoc, consult index.html file inside the Javadoc folder.