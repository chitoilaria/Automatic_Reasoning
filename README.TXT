===========================
DESCRIZIONE DELL'ESECUZIONE
===========================

- Lanciare il file JAVA TheoremProver.jar
  presente all'interno della cartella Eseguibile

- Dal Menù, selezionare File -> Load File…

- Selezionare un file di testo (*.tpf)

==========================================================================================

- La grammatica per i file di input, è così definita:

	SIMBOLO NOT: -;

	SIMBOLO OR: |;

	COSTANTI: lettere minuscole a..e oppure i..t seguite da intero (facoltativo);

	VARIABILI: lettere minuscole u..z seguite da intero (facoltativo);

	PREDICATI: lettere maiuscole A..Z seguite da intero (facoltativo);

	FUNZIONI: lettere minuscole f..h seguite da intero (facoltativo);

- Per le precedenze, strutturare una singola linea di testo così:

	Elem1 > Elem2 > Elem3 > .. > ElemN

rispettando la grammatica, altrimenti non sarà possibile leggere il file.

- Per i pesi, strutturare singole linee di testo, una per oggetto così:

	Elem1 , valore
	Elem2 , valore
	Elem3 , valore
	Elem4 , valore
	..
	ElemN , valore

rispettando la grammatica, altrimenti i pesi non saranno considerati.

==========================================================================================

- Per il Javadoc, consultare il file index.html dentro la cartella Javadoc