# event-control

Repositorio destinado para AC2 de Programação Orientada a Objetos

 - CRUD.
 - Listagem Paginada.
 - Pesquisa Por Id.
 - Pesquisas Diversas Paginadas (filtros): Nome, Por Local do Evento, Data de Início e Descrição.
 - Data de Início = Listar eventos que começam depois da data de início passada.
 - Heroku: https://event-poo-ac1.herokuapp.com/
 - Arquitetura em Camadas Usando DTOs.

# Dupla
 - Matheus Jacob Bendel - 190299
 - Antonio Luís Canno de Araujo - 190929

 <details>
  <summary><strong>JSON DAS ENTIDADES</strong></summary>
  
  <br />
  
  <p align="left">

```json
EVENT
{
    "adminId": 1,
    "name": "Plugin: Desafios do 5G",
    "description": "Um evento 100% online, interativo e aberto ao público, com speakers internacionais.",
    "startDate": "2021-07-01",
    "endDate": "2021-07-07",
    "startTime": "08:00:00",
    "endTime": "22:30:00",
    "email": "desafio5g@puglin.com.br",
    "amountFreeTickets": 50,
    "amountPayedTickets": 250,
    "priceTicket": 5.00
}

ADMIN
{
    "name": "Sahara",
    "email": "sahara@gmail.com",
    "phoneNumber": "11959325987"
}

ATTEND
{
    "name": "Bianca",
    "email": "biancafeira@gmail.com",
    "balance": 500
}

PLACE
{
  "name": "FACENS",
  "adress": "Rodovia Senador José Ermírio de Moraes, 1425 - Jardim Constantino Matucci, Sorocaba"
}

TICKET
{
  "attendId": 2,
  "type": "PAYED"
}
```
  </p>
  
</details>

<details>
 <summary>Routes</summary>
 
 ## Events

- Get: /events
  - Busca todos os eventos, você pode passar os Query Params como `page` e `limit` para fazer paginação e seus detalhes são retornados nos `Headers`, e Query Params como `name`, `description`, `emailContact` ou `startDate` para filtrar os resultados ou fazer uma pesquisa.
  - Exemplo: /events?page=0&limit=1&name=Matheus
  - Exemplo: /events?page=0&limit=1&startDate=2021-03-20
  - Exemplo: /events?page=0&limit=1&name=Matheus

- Post: /events
  - Cria um novo evento.

 ```json
{
   "name": "Introdução Java",
   "description": "Palestra introdutoria sobre tecnologia Linguagem Java, crescimento, mercado e inovação",
   "startDate": "2021-05-30",
   "endDate": "2021-06-02",
   "startTime": "18:00:00",
   "endTime": "18:00:00",
   "email": "suporte@linguagemjava.com.br",
   "amountFreeTickets": "0",
   "amountPayedTickets": "100",
   "priceTicket": "50"
}
```

- Get: /events/{eventId}
  - Busca um evento pelo seu ID.
  
- Put: /events/{eventId}
  - Atualiza um evento pelo seu ID.

```json
{
  "name": "Introdução C",
  "description": "Palestra introdutoria sobre tecnologia Linguagem C, crescimento, mercado e inovação",
  "startDate": "2021-05-30",
  "endDate": "2021-06-02",
  "startTime": "18:00:00",
  "endTime": "18:00:00",
  "email": "suporte@linguagemc.com.br",
  "amountFreeTickets": "0",
  "amountPayedTickets": "100",
  "priceTicket": "50"
  "adminId": 1
}
```

- Delete: /events/{eventId}
  - Remove um evento pelo seu ID.

## Admins

- POST: /admins
  - Cria um admin.

```json
{
  "name": "Matheus Jacob Bendel",
  "email": "matheus.bendel@facens.com.br",
  "phoneNumber": "11999999999"
}
```

- PUT: /admins/{adminId}
  - Edita um admin.

```json
{
  "name": "Matheus Jacob Bendel",
  "email": "matheus.bendel@facens.br",
  "phoneNumber": "11997498752"
}
```

- GET: /admins/{adminId}
  - Busca um admin.

- GET: /admins
  - Busca vários admins.

- DELETE: /admins/{adminId}
  - Remove um admin.

## Attendees

- POST: /attendees
  - Cria um participante.

```json
{
  "name": "Matheus Jacob",
  "email": "matheus.bendel@facens.br"
}
```

- PUT: /attendees/{attendeeId}
  - Atualiza um participante.

```json
{
  "name": "Antonio Luis Canno de Araujo",
  "email": "190929@facens.br"
}
```

- GET: /attendees/{attendeeId}
  - Busca um participante.

- GET: /attendees
  - Busca vários participantes.

- DELETE: /attendees/{attendeeId}
  - Remove um participante.

```json
{
  "name": "Antonio Luis Canno de Araujo",
  "email": "190929@facens.br"
}
```

## Places

- POST: /places
  - Cria um lugar.

```json
{
  "name": "Casa do Joga10",
  "address": "Rua Monsenhor Benedito Mário Calazans"
}
```

- PUT: /places/{placeId}
  - Atualiza um lugar.

```json
{
   "name": "OXFORD",
   "address": "Lower Farm House, Lower Rd"
}
```

- GET: /places/{placeId}
  - Busca um lugar.

- GET: /places
  - Busca vários lugares.

- DELETE: /places/{placeId}
  - Remove um lugar.

## events/{eventId}/places

- POST: /events/{eventId}/places/{placeId}
  - Adiciona um lugar a um evento.

- DELETE: /events/{eventId}/places/{placeId}
  - Remove um lugar a um evento.

## events/{eventId}/tickets
  
- GET: /events/{eventId}/tickets
  - Busca os Tickets de um evento.
  
- POST: /events/{eventId}/tickets
  - Vende um ticket para um attendee.
  - Separação feita entre tickets pagos e gratuitos através do campo "type", onde FREE é gratuito e PAYED é pago.

```json
{
  "attendeId": 5,
  "type": 1
}
```

- DEL: /events/{eventId}/tickets
  - Remove ticket vendido.
  - Separação feita entre tickets pagos e gratuitos através do campo "type", onde 0 é gratuito e 1 é pago.

```json
{
  "attendeId": 5,
  "type": 0
}
```
 </detail>
