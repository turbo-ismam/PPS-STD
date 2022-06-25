# Design

## Design architetturale
L'architettura dell'applicazione sviluppata è basata sul pattern "Model-View-Controller", essendo questo uno dei maggiori pattern di riferimento per applicazioni
dotate di interfaccia grafica. Le componenti principali dunque sono:
1. Model
2. View
3. Controller

Partendo dai requisiti abbiamo dapprima sviluppato un diagramma che dovrebbe rappresentare il prototipo dell'applicazione, così da notare eventuali scelte incongruenti
o inconsistenti.



## Design nel dettaglio

### Componente "Model"
Questo componente contiene tutte le varie entità che sono presenti all'interno del gioco:
1. Tower
2. Enemie
3. Grid
4. Projectile
5. Player

Ognuna di queste classi contiene le definizioni di base di ogni entità che è presente all'interno dell'applicazione, ognuna modella il proprio comportamento in funzione di quello che deve fare e mantiene uno stato che sia capace di contenere le proprie informazioni più rilevanti. Per cercare di seguire il principio di *"favor immutability"*, quasi ogni elemento è stato costruito in modo che non sia possibile modificare il proprio stato, ma invece, di ricreare se stesso in un altro stato.

### Componente "Controller"
Questo componente contiene tra le cose più importanti:
1. GameController (è il controller di un game all'interno dell'applicazione, al suo interno vengono usate quasi tutte le entità presenti nel model attraverso i propri controllers)
1. UpdateManager: si occupa in generale del game loop, quindi effettuare gli aggiornamenti all'interno del game

Tutte le altre classi di controller sono utilizzate all'interno di questi due controller.
Tutti i controller delle entità del model wrappano quelle stesse entità e offrono metodi avanzati per passare informazioni all'esterno.
L'unica eccezzione è DrawingManager, questa classe serve solamente per disegnare le entità all'interno della griglia e viene usata solamente da UpdateManager.
Come per il model, anche qui si è cercato di seguire, quando possibile, il principio di *"favor immutability"*

### Componente "View"
Questo componente è particolare, è presente un nuovamente un livello del pattern MVC, quindi abbiamo:
1. ViewModel: rappresenta tutta la grafica dell'applicazione e contiene tutte le definizioni delle interfacce grafiche
2. ViewController: rappresenta il controller del model, al suo interno contiene principalmente tutti gli event listener associati ai vari elementi del ViewModel e 
permette di eseguire le operazioni che l'utente fa sull'interfaccia grafica.

C'è un package, chiamato EventHandlers che serve a gestire nello specifico poi ogni singolo evento.

Come il resto, queste classi sono state scritte sempre seguento il principio di *"favor immutability"*, in particolare in questo caso ulteriormente tutte le classi sono private e l'unico modo per poter ottenere un tipo è sfruttando il trait, mentre per istanziare un'istanza è necessario sfruttare l'apply del companion object.
