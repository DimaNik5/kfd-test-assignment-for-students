# KFD Test Assignment: Library Management System

## Task Description

Create a console-based library management system. This assignment tests your understanding of **OOP principles** and **data structures** - the foundation skills needed for our Kotlin course.

### What to Build
A working console application that manages:
- **Books** (add, remove, search)
- **Users** (different types: Student, Faculty, Guest) 
- **Borrowing operations** (borrow, return, track overdue)

### Business Rules
- **Student**: max 3 books, 14 days
- **Faculty**: max 10 books, 30 days  
- **Guest**: max 1 library.book, 7 days
- Books cannot be borrowed if unavailable
- Users cannot exceed their borrowing limit

## Begin

At the beginning, there is only 1 user, the administrator. To log in, enter "admin".
he can add another user, which will allow him to log in through the client. However, in order for the information to be saved, you need to exit the program through the main menu.