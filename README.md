
# HDI Challenge

Considerações: Já que estamos usando Tomcat, optei por usar códigos síncronos para facilitar o desenvolvimento.




## Documentação da API

#### Retorna os dados de um corretor.

```http
  GET /status/{document}
```

#### Atualiza o status de um corretor.

```http
  PUT /status/{document}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `active`      | `boolean` | **Obrigatório**. Status do corretor, ativo ou inativo. |


