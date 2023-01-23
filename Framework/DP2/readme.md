This repository contains source code for the framework of this work; these classes are used to learn contextual vectors and sentence vectors, and to choose sentences to be used in the explanations.  

Java classes:

- Main.java: it is used to start the pipeline; it calls each class and methods used in this work.

- Configurazione.java: it is used to establish if sentences (and so, in turn, contextual vectors) must be split (learned) into unigrams, bigrams, or combination of unigrams and bigrams

- MatriceFC.java: class to generate and model sentence-context matrix.

- Matrice LF.java: class to generate and model lemma-sentence matrix.

- Matrice LC.java: class to ggenerate and model lemma-context matrix.

- VettoriContesto.java: class to generate and model contextual vectors.

- MatriceLocaleContesto.java: class to model, for each restaurant, all combinations of contexts and related scored 

- MatriceLocaliContestiFrasi.java: class to score, for each restaurant, and for each combination of contexts, the set of sentences to be chosen.

- Top10LocaliPerCombinazione.java: class to model the top10 of restaurant that could be suggested for each combination of contexts

- MatriceContestiItemFrasi: class to model which senteses to be chosen for each combination of contexts and items

- Locale.java: class to model a single restaurant 

- Report.java: class to generate report starting from report files

- Vettore frase: class to model sentence vectors

- Vettore contesto: class to model context vectors

- SimilaritaCoseno: class to model cosin simlarities between several kind of vectors








