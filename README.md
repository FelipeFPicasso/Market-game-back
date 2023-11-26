## API (Back-End)
*Descrição:*
- Este projeto é uma aplicação de um marketplace voltado para a revenda de jogos eletrônicos.
---
- *Tecnologias*
    - Framework
        - Spring Boot (3.1.4)
    - Linguagem
        - Kotlin (1.8.22)
    - Database
        - MySql 8.0
- *Instalação*
  - GIT clone https://github.com/FelipeFPicasso/market-games.git
  - Run MarketGameApplication.kt
    - ![image](https://github.com/FelipeFPicasso/market-games/assets/127898269/f34151fb-c668-4171-a4f2-14a85225d575)
---
## EndPoints
- *URL*
  - localhost:8080
- *Public*
  - URL/login
    - POST: /
  - URL/customer
    - POST: /
- *Private*
  - URL/customer
    - GET: /
    - GET: /{id}
    - PUT: /{id}
    - DELETE: /{id}
  - URL/game
    - GET: /
    - GET: /active
    - GET: /{id}
    - POST: /
    - PUT: /{id}
    - DELETE: /{id}
  - URL/purchase
    - POST: /
  - *Nível Admin*
    - URL/admin
      - GET: /report
---
- *Postman*
  - Collection
    - ![image](https://github.com/FelipeFPicasso/market-games/assets/127898269/29e46d2d-e951-4471-9c59-c349916d1d3e)
- *Xmind*
  - ![image](https://github.com/FelipeFPicasso/market-games/assets/127898269/1f7d0374-b7e4-4041-a21f-f98f4170ba75)




    







