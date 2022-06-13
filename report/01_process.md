# Processo
Abbiamo adottato un processo di sviluppo ispirato a Scrum: questo framework è adatto a team di piccole dimensioni
(ideale per noi 3) e favorisce lo sviluppo di software che potrebbe presentare difficoltà inattese grazie al suo 
processo d'implementazione.

Il ruolo di "Product Owner" è stato ricoperto da Ismam Abu, che ha diretto i vari meeting, creato gli sprint e prodotto
dei report alla fine di essi.

## Meeting
Il processo "scrum" sfrutta dei meeting a breve e a lungo termine per discutere il breve e il lungo periodo, favorendo
una maggiore comprensione di ogni componente dell'applicazione da parte di tutti i membri.
Nel nostro caso, si è deciso di fare dei meeting giornalieri e settimanali, nel primo caso sono stati fatti per
sincronizzarsi sul lavoro degli altri, mentre i meeting settimanali per gestire i merge e le release delle varie 
versioni dell'applicazione. 
Tutti questi meeting sono stati svolti tramite il servizio di voice chat "Discord".

### Meeting giornalieri
Durante i meeting giornalieri, ogni membro ha discusso di:
1. funzionalità a cui stava lavorando quel determinato giorno, non entrando troppo nei dettagli
2. dubbi riguardo a delle scelte che aveva fatto o che avrebbe dovuto fare
3. proprie opinioni sul proprio lavoro e l'andamento generale dello sviluppo dell'applicazione

Dopo il meeting giornaliero, se serviva, si faceva una discussione più tecnica per gestire alcuni aspetti chiave 
dell'applicazione, in maniera da evitare che rimanessero dubbi in sospeso. Il meeting durava dai 30 minuti alle 2 ore,
non è stato fatto tutti i giorni (principalmente per motivi lavorativi) ma per un buon 80% eravamo sempre presenti.

### Meeting settimanali
Abbiamo svolto 4 sprint settimanali, della durata di una settimana ognuno, ogni sprint rappresenta una release della 
nostra applicazione.
Ogni sabato, al posto di fare il meeting giornaliero si è provvisto a fare *retrospective* dello sprint concluso per
andare a pianificare quello successivo. Oltre questo generalmente si procedeva a fare il merge del branch *dev* con il 
*main* cosicché venisse prodotta in automatico la release.

Lo sprint retrospective consiste in:
1. analizzare il livello di completamento delle issue associate allo sprint settimanale 
2. individuare delle issue che si sono rivelati più complicati del previsto

Lo sprint planning consiste in:
1. decidere quali issue avremmo dovuto inserire nello sprint successivo
2. creare su git le nuove issue con le relative specifiche discusse

Tipicamente i meeting settimanali duravano una mezza giornata abbondante, spesso anche una giornata intera.

## Suddivisione delle issue
Le issue che formavano poi le varie versioni, sono state individuate suddividendo gli obiettivi da raggiungere per
categoria, una volta ottenuti venivano assegnati alla persona a cui spettavano in base alla disponibilità, chiaramente
per molte issue si sono messe insieme due persone per poter sviluppare al meglio certe funzionalità.

## Tool
Gli strumenti e le tecnologie di cui ci siamo avvalsi durante lo sviluppo sono:
1. Framework "ScalaTest" per realizzare le unit test
2. GitHub, GitHub Actions, GitHub Project
3. Docker (local testing)
4. Gradle

## Repository GitHub
Per facilitare lo sviluppo dell'applicazione abbiamo utilizzato il DVCS "git" e organizzato il suo workflow nel
seguente modo:
1. il branch "main" è stato dedicato alle release principali dell'applicazione, rilasciate al termine degli sprint
settimanali
2. il branch "dev" è stato utilizzato per sincronizzare e unificare tutti gli altri branch
3. ogni feature ha avuto un proprio branch su cui hanno lavorato al più due persone contemporaneamente

La decisione di fare hosting della repository sul servizio "GitHub" ci ha permesso di utilizzare la tecnologia di
"Continuous Integration" per automatizzare il workflow attraverso le "GitHub Actions". Di seguito le nostre 
pipeline:
1. release in seguito al merge del dev al main
2. esecuzione dei test attraverso la creazione di Pull Request sui vari branch, i push falliscono se i test non passano
3. @HAMA SPIEGA IL RESTO

Si è sfruttato il GitHub Projects, questo è un foglio di calcolo con delle tabelle dove sono raggruppate le issue, 
questo schema permette di tenere traccia del product backlog e degli sprint backlog.
Le nostre colonne sono:
1. To do
2. In progress
3. Waiting for approval
4. Ready to merge
5. Done