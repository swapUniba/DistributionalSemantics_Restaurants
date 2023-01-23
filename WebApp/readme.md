This repository contains source code for the web application of this work, in the restaurant domains.
Files produced by the the framweork are needed.

WebApp classes:

- ServletGestioneInfoUtente.java: class for the servlet which handles user information to be stored


- ServletGestioneRichiesta.java: class for the servlet of user request, which chooses the configurations and algorithms to be used

- ServletSalvataggioValutazione<1-4>.java: class for the servlet to store user preferences for the experiments:
-	1. evaluate the first explanation provided (which might be based on centroid or single sentences for each context)
-	2. evaluate the second explanation provided (complementary of the previous step)
-	3. comparison between the two explanations
-	4. compare between the most preferred one (at random if no preference is expressed) and a the fixed-lexicon baseline







