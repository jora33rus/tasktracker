1. Для запуска, установить все зависимости (pom.xml используя maven) 
2. Выполнить: docker-compose up -d
3. Доступ к БД:
host: localhost:5432
Name: tasktracker
Username: postgres
Password: password
4. Запустить приложение: mvn clean spring-boot:run
5. API Endpoints ( http://localhost:8080/swagger-ui.html )

Public:
POST /api/auth/sign-up     - Регистрация нового пользователя
POST /api/auth/sign-in     - Вход в систему (получение JWT)
POST /api/auth/refresh     - Обновление JWT токена

Need JWT:
GET  /api/get-admin        - Получить права администратора 
GET  /api/tasks            - Получить все свои задачи
POST /api/tasks            - Создать новую задачу
GET  /api/tasks/{id}       - Получить задачу по ID
PUT  /api/tasks/{id}       - Обновить задачу
PATCH /api/tasks/{id}/status - Изменить статус задачи
DELETE /api/tasks/{id}     - Удалить задачу
GET  /api/groups           - Получить все свои группы задач
POST /api/groups           - Создать новую группу
GET  /api/groups/{id}      - Получить группу по ID
PUT  /api/groups/{id}      - Переименовать группу
DELETE /api/groups/{id}    - Удалить группу

ADMIN
GET  /api/admin/users            - Все пользователи системы
GET  /api/admin/users/{id}       - Пользователь по ID
PUT  /api/admin/users/{id}       - Обновить пользователя
DELETE /api/admin/users/{id}     - Удалить пользователя
GET  /api/admin/tasks            - Все задачи в системе
GET  /api/admin/tasks/user/{id}  - Задачи конкретного пользователя
GET  /api/admin/groups           - Все группы в системе
GET  /api/admin/statistics       - Статистика (JPQL запросы)

6.Порядок использования: 
Шаг 1: Регистрация api/auth/sign-up . после регистрации
Шаг 2: Вход в систему /api/auth/sign-in
Шаг 3: Авторизация: нужно скопировать токен из ответа и нажать кнопку "авторизация", добавить значение в поле
Шаг 4: Выполнить проверку возможностей доступных пользователю
Шаг 5: Повторить шаги 1, 2, 3 создав пользователя, и авторизовавшись им, затем выполнить запрос  /api/get-admin для получения прав администраора 
Шаг 6: Выполнить проверку возможностей доступных администратору
