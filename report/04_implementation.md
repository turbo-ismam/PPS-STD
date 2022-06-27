# Implementazione

## Ismam Abu

### Setup del repository
Prima di iniziare a lavorare effettivamente sul progetto è stato necessario fare il setup del repository per quanto riguarda le azioni di CI/CD e di deploy.
E' stato fatto anche il setup di gradle e aggiunte le librerie che sicuramente sarebbero state utilizzate.
Queste operazioni sono state fatte da me in collaborazione con Hamado.
Successivamente a questo ho provvisto a fare la modellazione dell'applicazione attraverso un activity diagram ed un use-case diagram, grazie a ciò abbiamo creato le classi delle entità di base che sarebbero servite (mettendo solo le entità utili per il primo sprint).

### View
Nella prima settima ho lavorato sulle classi di view per fornire, già dopo il primo sprint, un'applicazione con cui l'utente potesse interagire, ho utilizzato le librerie "ScalaFX" e "JavaFX", realizzando le seguenti classi:
1. Package ViewModel:
    * **ApplicationViewModel:** è un trait che viene esteso da tutti i vari model della view
    * **GameViewModel e MainMenuViewModel:** sono le due classi di model che rappresentano le due scene dell'applicazione, la prima quella di gioco e la seconda quella del menù principale. Entrambe le classi contengono al loro interno le definizioni di tutte le varie entità grafiche del gioco, nello specifico sono bottoni, label e nel caso del *GameViewModel* abbiamo una griglia che è rappresentata da un canvas
1. Package ViewController:
    * **ViewModelController:** è un trait che viene esteso da tutti i controller della view
    * **MainMenuViewController e GameViewController** sono i controller dei model del package descritto in precedenza, hanno tutti in comune il metodo *hookupEvents*, questo si occupa di agganciare i vari action listener agli elementi del model associato.
1. Package EventHandlers:
    * **EventHandlers:** è un triat che viene esteso da tutti i vari event handlers della view, di base contiene le definizioni dei metodi di cui ha poi bisogno ogni classe che estende
    * **GameEventHandlers e MainMenuEventHandlers:** gestiscono gli eventi, ogni controller della view ha una propria classe per gestire i propri eventi. Gli eventi che abbiamo sempre sono *setScene* e *nothing*, tutti gli altri sono specifici della singola scena di gioco.

L'unico modo per accedere a queste classi è attraverso l'apply del proprio companion object.
L'entry point dell'intera applicazione è la classe **GameLauncher**, da essa viene creato il controller del main menù e successivamente lanciato.
Ogni volta che si vuole cambiare schermata, viene creato nell'action listener associato il controller della scena desiderata e successivamente lanciata.

### Grid
Ho lavorato alla creazione dell'entità Grid, questa rappresenta la griglia di gioco.
#### Grid Model
Per implementare in maniera ottimale questa entità ho realizzato un'interfaccia con un'unico metodo che ritora la griglia di gioco, questa griglia viene creata all'interno della classe che però è accessibile solamente dal proprio companion object. 
Questa classe chiama al proprio interno si occupa solamente di generare la griglia di gioco, rappresentata da un array bidimensionale.
#### Grid Controller
Questa classe wrappa al proprio interno il model della griglia e offre all'esterno tutta una serie di funzionalità per ottenere informazioni dalla griglia.
Ho scritto il controller in maniera che non restituisse con nessun metodo la griglia effettiva di gioco ma solamente le cose di cui un utilizzatore abbia effettivamente bisogno, quindi si possono leggere per dei tile partendo dalle loro coordinate, si può conoscere il numero di tile presenti e combinando queste informazioni è comunque possibile ottenere tutte i tile della griglia, ma appunto, ho preferito evitare di rendere pubblica direttamente la griglia.
#### Tile
L'unità alla base della griglia è il *tile*, questa è un'entità che rappresenta un blocco all'interno della griglia di gioco.
E' stato realizzato un trait che ne racchiude le funzionalità che possono essere usate dall'esterno e l'unico modo per istanziarlo è attraverso l'apply del companion object. 
Al suo interno, come campo di rilievo ha un'altro oggetto denominato *TileType*, questo è una classe che serve per poter gestire la tipologia dei vari tile e le informazioni associate ad essa (ad esempio i campi *buildable* e *color* sono direttamente dipendenti dalla tipologia di tile).
Per creare un TileType è necessario passare dall'applu del companion object, durante la creazione si passa il tipo desiderato (ottenuto da un'enumerazione) e viene generato l'oggetto richiesto. Tutti i tipi di tile sono nel package **Tiles**.
Aver progettato in questa maniera il tile (ovvero con il tipo come oggetto complesso) rende molto più modualare il tutto, in quanto è facile aggiungere nuove tipologie di tiles, essendo anche tutte case classes è molto comodo in quanto si può utilizzare il pattern matching.
#### PathMaker
Questa è una classe utilizzata dal model della grid per generare la matrice che rappresenta la griglia, il trait espone una serie di metodi per generare il tipo di griglia desiderata. L'utilizzatore chiama metodo execute che prende come parametro uno dei metodi per generare la mappa ed esegue in più delle azioni comuni a tutte le mappe (esegue i controlli sulla validazione di una certa mappa).
Questa classe si occupa anche di leggere da file le mappe, in quanto le tre di base sono salvate nelle resources e quelle custom nel file system del giocatore.
Per leggere da file system utilizziamo come formato il json, quindi il file deve essere prima letto e poi validato, per questi ed altri motivi ho deciso di utilizzare la libreria **Gson**. 
Il file viene letto tramite il suo path all'interno del file system attraverso un *BufferedReader*, i dati poi vengono prima serializzati come json e successivamente mappati come matrice.

### Player
Ho gestito l'entità che rappresenta un giocatore all'interno dell'applicazione.
Questa classe è abbastanza semplice e non ha oggetti complessi al proprio interno, viene chiamata quando viene creato e valorizzata quando viene creato un nuovo game.
Viene utilizzata dalle altre classi principalmente per leggere le informazioni riguardo il giocatore, come nome, vita, soldi e il contatore delle uccisioni.

### Test
Ho scritto i test relativi alla Grid e alla Configuration.

## Hamado Dene 
Il contributo sul progetto si è incentrato sull'implementazione delle torri e dei proiettili.
Le torri si dividono principalmente in due tipologie:
- Shooter tower
- Circular radius tower

Le differenza principale tra le due tipologie è che le torri di shooter identificano un nemico nel range
e gli sparano usando un proiettile. Se questo proiettile entra in collisione con il nemico, quest'ultimo 
subisce un danno. 
Le torri di tipo circular radius invece non utilizzano proiettili, ma fanno un danno ad area in un raggio circolare
(Per esempio una torre che spara fiamme). Quando un nemico entra nell'area, se la torre spara un colpo ad area,
i nemici prendono danno. 

I proiettili hanno una logica comune, ovvero che nel momento in cui vengono sparati dalla torre, escono con una
determinata velocità e se entrano in collisione con un nemico, gli infliggono un danno. Le differenze quindi stanno
nella velocità in cui si muovono, dalla dimensione del proiettile e dal danno che possono influire.

### Implementazione tower
L'implementazione della torre è suddivisa nel seguente modo:

- Implementazione del model che definisce la logica delle varie tipologie di torri.
- Un controller dalla quale tutte le torri vengono derivati.

### Model
Nel model viene definito la logica delle due tipologie di torri attraverso le due classi ShooterTower
e CircularRadiusTower. Queste due classi implementano il trait presente in TowerType. 
Il trait definisce le caratteristiche principali di una torre. 

#### ShooterTower
Questa classe definisce i metodi di tutte le torri che hanno come obiettivo quello d'individuare un obiettivo e sparare.
Nel costruttore prende come parametro un tipo di proiettile. 
Qualunque torre che spara dovrà dunque estendere questa classe.

Sono definiti i seguenti metodi:

- **findDistance(enemy: Enemy):** Dato un nemico, calcola la distanza tra la posizione della torre e il nemico stesso.
  Ritorna un valore che corrisponde alla distanza dal nemico.
- **inRange(enemy: Enemy):** Dato un nemico, verifica se il nemico si trova nel range che la torre può coprire.
- **chooseTarget():** Prende la lista di nemici vivi e presenti sulla mappa del gioco e dato la distanza calcolata con
  __findDistance(enemy: Enemy)__ e dall'esito della funzione __inRange(enemy: Enemy)__, 
  seleziona un nemico tra quelli presenti nel suo range.
- **attack():** Dato il nemico scelto con __chooseTarget()__, se quest’ultimo risulta ancora in vita, 
    chiama il metodo __fireAt(enemy: Enemy)__ che disporrà il proiettile per lo sparo al nemico.
- **fireAt()** Dato un nemico, prepara il proiettile per lo sparo. Il tipo di proiettile dipenderà dal tipo di torre.

#### CircularRadiusTower
Questa classe definisce i metodi di tutte le torri che sparano in un raggio circolare 
e non usano proiettili (es. Torre che spara fiamme). A differenza di shooterTower non viene scelto un target da colpire 
ma qualunque nemico passa nel raggio al momento dello sparo subisce un danno.

Sono definiti i seguenti metodi:

- **isColliding(radius: WayPoint, enemy: Enemy):** Prende in ingresso il raggio (espresso tramite WayPoint) della torre
   (considerando il range che può coprire con lo sparo) e uno specifico nemico. Ritorna true se il nemico è in collisione
   false altrimenti.
- **attack():** Dato la lista di nemici presenti sul ring di gioco,
  infligge danno a coloro che sono in collisione al momento dello sparo.
  Per capire se il nemico è in collisione o meno viene chiamato il metodo __isColliding(radius: WayPoint, enemy: Enemy).__

Queste due classi vengono poi estese dalle varie torri, 
che attraverso un override andranno a ridefinire alcuni parametri come la velocità di sparo, 
il range che la torre può coprire ect. 

Sono stati implementati tre torri possibili:

- **BaseTower:** Estende shooterTower. E’ la torre di base, che spara semplici proiettili e infliggono un danno ridotto.
- **CannonTower:** Estende shooterTower. Una torre cannone che spara proiettili di grosse dimensioni che infliggono un danno elevato.
- **FlameTower:** Estende CircularRadiusTower. Una torre che spara fiamme.

Per istanziare queste torri è definito una factory in TowerType che attraverso il metodo apply permette i generare una 
determinata tipologia di torre. Questo apply prende in ingresso un Enum che permette di capire la tipologia di torre 
da generare.

### Tower controller
Per la gestione delle torri è stato creato un tower controller, dalla quale ogni torre può essere derivato.
Quando si decide di creare una torre, viene istanziato un tower controller che prende come parametro:
- il tipo di torre che si vuole gestire (Ottenibile chiamando la factory delle torri)
- Un oggetto Player che specifica owner della torre
- La posizione in cui è stato buildato la torre
- un istanza di GameController per rimanere aggiornato sullo stato del gioco, in particolare di capire lo stato dei nemici sulla mappa.

Tutte le informazioni inerenti a una torre possono essere reperiti chiamando l'istanza di controller associato alla
torre creata. Inoltre ogni istanza di tower controller gestisce al suo interno i propri proiettili. 

### Implementazione proiettili
I proiettili vengono istanziati dalle shooter tower quando quest'ultimi decidono di sparare a un nemico. I proiettili 
possono essere di diversi tipi, ma la differenza sta nella dimensione del proiettile, dalla velocità che può assumere,
dal colore e dal danno che può infliggere.
Hanno però una logica comune che implica l'esistenza di un'unica implementazione effettuata tramite la classe __Projectile__.
Ogni tipologia di proiettile quindi estende la classe __Projectile__ modificando tramite override i valori,
specificando i nuovi valori che caratterizzano quello specifico proiettile.

#### Class Projectile
Questa classe dunque definisce la logica di un proiettile.
Per essere istanziato necessita di sapere:
- la posizione del target
- la posizione di origine (Ovvero da dove è stato sparato)
- la torre che ha sparato il proiettile
- il nemico da colpire e il controllore di torri. 

Implementa al suo interno le seguenti funzionalità:

- **CalculateDirection():** Permette di calcolare la direzione del proiettile 
   utilizzando la posizione del nemico e la posizione di origine del proiettile stesso.
- **isColliding(pos: WayPoint):** Prende una posizione, e verifica se il proiettile è il collisione con il nemico. 
    In caso affermativo ritorna true, altrimenti false
- **update(delta: Double):** Permette di aggiornare dinamicamente la posizione del proiettile. 
   Tramite il metodo __isColliding(pos: WayPoint)__, controlla se quest’ultimo è il collisione con il nemico. 
   In caso affermativo viene inflitto un danno al nemico.

### GameController
Il gameController è il main controller che gestisce lo stato del gioco. 
Si occupa d'inizializzare le entità principali necessari al funzionamento del gioco, come:
- Player
- GridController
- Wave e WaveScheduler
- Liste di torri e nemici che permettono di capire quali nemici e torri sono presenti sulla mappa.

Usando il GameController è possibile:
- Conoscere la lista di nemici presenti sulla mappa
- Conoscere la lista di torri piazzati sulla mappa
- Avere la lista di torri disponibili che si possono buildare
- Sapere quale torre è stata selezionata
- Capire se tutte le istanze sono state inizializzate, quindi se il gioco è partito.

I metodi principali che ho implementato sono:
- **onCellClicked(x: Double, y: Double):** Questa funzione viene chiamata quando si vuole buildare una torre sulla mappa.
  Prende come parametro le posizioni X e Y (Valorizzati in base al punto di click sulla mappa) e controlla se sono 
  rispettate determinate condizioni: 
  - Se è stata selezionata una torre e il tile cliccato è buildable e il giocatore ha abbastanza soldi, allora prova a
  buildare la torre. La build viene fatto facendo il clone della torre selezionata con le nuove posizioni e rivalorizzando 
  la variabile __selectedTower__. Inizialmente la variabile __selectedTower__ viene valorizzato quando il giocatore prima 
  di cliccare sulla mappa seleziona la torre che vuole piazzare usando il menu predisposto.
    - Se è già presente una torre in quella posizione, non può essere piazzato un altra torre.
  - Se il giocatore ha selezionato una torre ma al momento del click non ha abbastanza soldi, la build fallisce
  - Se non viene selezionata nessuna torre, la build fallisce. 
- **buildTower(tower: Tower):** Permette di valorizzare il parametro __selectedTower__ che tiene traccia della torre selezionata nei vari 
  stati del gioco.

### Update Manager
La classe UpdateManager si occupa di gestire il rendering dei componenti di gioco. Implementa una funzione __run()__ 
che attraverso un animationTimer chiama la funziona __update(delta: Double)__ ogni delta. La funziona __update(delta: Double)__ 
si occupa di chiamare update di tutti i componenti quali: enemy, tower, grid.

La variabile __delta__ corrisponde a ogni quanto tempo chiamare update.

### WayPoint
La classe WayPoint permette di gestire le posizioni 2D. 
Offre diverse funzionalità che permettono di eseguire operazioni molto velocemente sulle posizioni. 

### Test
Ho implementato i test relativi alle classi di GameController e Tower

## Vlad Mattiussi

### Enemy model
Nel mio primo sprint ho realizzato l'entità di base del modello caratterizzante dei nemici. Ho realizzato l’interfaccia e la relativa classe implementativa in “Enemy”. Ho implementato i nemici declinandoli in tre diversi tipi, ognuno con diverse caratteristiche di punti vita, velocità e danno. Se i suoi punti vita vengono portati a zero, esso deve essere eliminato e despawnato dalla mappa. Se riesce a raggiungere la fine del percorso, esso deve infliggere un certo ammontare di danno al giocatore ed, infine, despawnarsi. Sono presenti tre oggetti caratterizzanti i tre tipi di nemici, ognuno estende il trait “enemyType”. Nel momento dell’inizializzazione del nemico, uno di questi tre oggetti viene passato al costruttore del nemico, in questo modo incapsulando e definendo il suo tipo. Il nemico è implementato come una classe privata “EnemyImpl” all’interno dell'oggetto “Enemy”; in questo modo la classe rimane “protetta” e per poter creare e accedere ai metodi di Enemy, bisogna utilizzare il metodo “apply” dell’oggetto, il quale restituirà un oggetto di tipo “Enemy”.

### Enemy move
Obiettivo del secondo sprint era modellare il comportamento legato al movimento: il nemico deve muoversi seguendo un certo percorso e con una certa velocità. Questo è stato implementato nel metodo “move”: questo metodo viene chiamato ad ogni update, cioè ad ogni periodo di tempo delta; ad ogni chiamata l'entità avanza sul percorso di un certo spazio, in base alla sua velocità. Partendo dalla posizione di un certo tile, attraverso un algoritmo, viene cercata e identificata la prossima posizione corretta da seguire mantenendosi correttamente sul percorso; dopo l’identificazione il nemico avanza di un certo valore. Una volta raggiunto il tile identificato, l’algoritmo ricomincia cercando la prossima posizione; questo processo continua fino a quando il nemico raggiunge l’ultima posizione sul percorso, nella quale si despawna e vengono inflitti danni al giocatore. Una volta identificato il prossimo tile, attraverso l’utilizzo di una variabile, viene imposto una lock: fino a quando il nemico non raggiungerà quel tile, la ricerca di tile si interromperà, impedendo di identificare possibili nuove posizioni; questo viene fatto per impedire la sopraggiunta di comportamenti imprevisti dell’algoritmo, come ad esempio cambiare direzione quando ancora non si è raggiunti il tile di destinazione.

Successivamente, per mostrare correttamente il nemico ed il suo movimento in modo fluido, il rendering è stato implementato attraverso la nostra entità incaricata del disegno, “DrawingManager”; ad ogni chiamata di update, il nemico viene renderizzato.


### Wave e wave scheduler

Nel secondo e terzo sprint ho implementato la parte creazione e gestione di ondata.Ogni ondata possiede una lista interna “enemyList” che viene inizializzata nel momento di creazione della wave: in questo momento viene chiamato il metodo “populate” che serve a creare e inserire in lista i vari nemici, in base al numero di ondata. Ho, inoltre, implementato ’’WaveScheduler’’, una classe per la gestione della programmazione di ondata, la quale deve spawnare un certo numero e un certo tipo di nemico in base al numero di ondata attuale. Attraverso la chiamata del metodo ’’Update_check’’ ad ogni delta, vengono controllate due condizioni:
-   Se il nemico raggiunge la fine del percorso.
-   Se i punti vita del nemico raggiungono o superano lo zero.
    
Quando un nemico soddisfa una di queste due condizioni, esso viene despawnato. Quando tutti i nemici dell’ondata attuale si despawnano, viene inizializzata la nuova wave.
Ogni volta che viene inizializzata una nuova ondata, quella vecchia viene “distrutta” e sostituita da quest’ultima. Il contatore di wave si aggiorna ad ogni creazione di ondata, e in base a questo, il metodo “populate” presente in “WaveImpl” spawna un particolare numero e tipo di nemici. Si continua così fino a quando i punti vita del giocatore scendono a zero.

### Game Controller
Durante il terzo sprint ho collaborato sulla parte di Controller con Hamado per quanto riguarda le interazioni fra torri e nemici, con il corretto comportamento di questi ultimi, descritto in precedenza.

Una volta cliccato sul bottone di start, viene fatto partire il game loop e il gioco inizia, con la possibilità di piazzare torri e danneggiare i nemici in arrivo. Per una migliore gestione del gioco, torri e nemici presenti sulla mappa vengono aggiornati costantemente in due apposite liste.

### View
Durante l’ultimo sprint ho lavorato alla realizzazione del migliore rendering grafico dei nemici.

### Test
Ho scritto i test per le classi di Enemy e Wave.
