# event-control-

##Event control##

 - CRUD.
 - Listagem Paginada.
 - Pesquisa Por Id.
 - Pesquisas Diversas Paginadas (filtros): Nome, Por Local do Evento, Data de Início e Descrição.
 - Data de Início = Listar eventos que começam depois da data de início passada.
 - Heroku: https://event-poo-ac1.herokuapp.com/
 - Arquitetura em Camadas Usando DTOs.

#Dupla
 - Matheus Jacob Bendel - 190299
 - Antonio Canno de Araujo - 190929

 <details>
  <summary><strong>JSON DAS ENTIDADES</strong></summary>
  
  <br />
  
  <p align="left">

```json
EVENT
{
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
    "name": "Biaca",
    "email": "biancafeira@gmail.com",
    "balance": 500
}
```
  </p>
  
</details>