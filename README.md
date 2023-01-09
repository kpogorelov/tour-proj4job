# Системные требования

1. jdk 17
2. Maven

# Требования к компоненту
Требования к компоненту находятся в документе https://www.notion.so/Java-test-task-80b74b85af1a4c3e8b592d53327e9622

# Инструкции по запуску проекта

Воспользуйтесь командой java -jar ....jar для запуска проекта на локальном компьютере

# API:
Компонент предоставляет следуюшие методы API:

1. Получить все истории тура (независимо от статуса публикации) для нескольких языков (например, en,ru). Если несколько языковых версий у истории попадают под фильтр, возвращает любую

````http request
GET http://localhost:9090/api/v1/tour/1/langColl?langColl=RU%2CEN
````


2. Получить только опубликованные истории тура для определённого языка (например, ru)

````http request
GET http://localhost:9090/api/v1/tour/1/lang/ru
````

3. Добавление тура и историй

````http request
POST http://localhost:9090/api/v1/tour 
````
и тело запроса будет ожидать json в виде:

````json
{
  "stories": [
    {
      "lang": "KZ",
      "title": "test title",
      "published": true,
      "order": 23
    }
  ]
}
````

                                                        





