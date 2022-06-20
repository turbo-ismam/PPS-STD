# Design

## Design architetturale
L'architettura dell'applicazione sviluppata è basata sul pattern "Model-View-Presenter", essendo questo uno dei maggiori pattern di riferimento per applicazioni
dotate di interfaccia grafica. Le componenti principali dunque sono:
1. Model
2. View
3. Presenter

### Componente "Model"
Questo componente contiene tutte le varie entità che sono presenti all'interno del gioco:
1. Towers
2. Enemies
3. Grid
4. Projectiles
5. Player

Ognuna di queste classi contiene le definizioni di base di ogni entità che è presente all'interno dell'applicazione, ognuna modella il proprio comportamento in funzione di quello che deve fare e mantiene uno stato che sia capace di contenere le proprie informazioni più rilevanti. Per cercare di seguire il principio di *"favor immutability"*, ciascun elemento è stato costruito in modo che non sia possibile modificare il proprio stato, ma invece, di ricreare se stesso in un altro stato.

#### Grid
Questo model deve contenere le informazioni riguardo la griglia


### Componente "View"

### Componente "Controller"

## Design nel dettaglio

### Modello del dominio dell'applicazione

### Modello del gioco

### Aggiornamento dell'interfaccia grafica
