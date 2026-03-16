This system will manage walk-in customers for multiple service counters in a bank branch.

my application is a Queue management project, its a service that can be used by business to manage there queue, and make customer expirence more efficent and fast

token, user, service, queuelog

ERD

User
|
|
Service
|
| 1
|-----> Many
Ticket
|
|
QueueLog

Queue_Management_Project/
├── queue_management/ (Microservice 1 - Library/REST API on port 8080)
│ └── Installed to local Maven repo as dependency
└── admine_management/ (Microservice 2 - Admin UI on port 8081)
└── Successfully using queue_management as dependency
