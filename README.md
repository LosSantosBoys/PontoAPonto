# PontoAPonto

# Requisitos

É necessário que a API https://github.com/LosSantosBoys/PontoAPonto-api/tree/main esteja em execução para funcionamento do projeto.

# Executando o projeto

## Git clone

```
git clone https://github.com/LosSantosBoys/PontoAPonto.git
```

## Configurações

No arquivo <b>RetrofitClient</b> (app/src/main/java/com/lossantos/pontoaponto/api/RetrofitClient.kt)
é necessário alterar a BASE_URL para a mesma URL utilizada na API (http)

```
    private const val BASE_URL =
        "http://YOUR_URL:5000"
```
