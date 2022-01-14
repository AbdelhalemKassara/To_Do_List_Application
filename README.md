# To_Do_List_Application
This is to-do list applciation where you can add a to-do list within a to-do list and display any combination of to-do lists created with java 17.
 
## Why Was This Made
With most to-do list applications that you find online, you usually are limited in the number of sub-lists that you can have, and you can't view more than one list at a time. This is usually all you need for most applications where there are few tasks, but once the tasks start to increase your lists can start to get disogranized and you might start missing some important tasks. This application solves these issues by allowing you to create as many sublists as you like and lets you create tables where you can lists to them and view all the lists you added at the same time. 

There are also many additional features that I will be adding such as, the ability to combine lists as one list in the tables and view those combinations or single lists side by side, and the ability to view only the tasks within a set period of time from the current date (both fixed and dynamic), but I'll save those for the version with a user interface.

This program was based on a prototype that I made using google sheets and forums which I was using to store and view the dates of all assignments, tests, quizzes, Exams, homework, lectures, etc for all of my courses for the entire term.

## Description

* This is to-do list applciation where you can add a to-do list within a to-do list. 
* You can pick any set of lists and add them to a table so that you can display them side by side.
* It has a similar structure to computer file systems, but with the directories replaced with lists and the files replaced with tasks.
* A task is contained within a to-do list and contains a start date, end date, and the task.
* All to-do list leads to the user and contains sublists and tasks.
* The user contains everything that the to-do list has, and in addition to that contains a list of tables.
* Tables are contained within the user and contains to-do lists.


## How To Use
    addTask task|year|month|dayOfMonth|hour|minute 
    
    addTask stYear|stMonth|stDayOfMonth|stHour|stMin|task|year|month|dayOfMonth|hour|minute
This command adds a new task to the current list.

    addTable nameOfNewTable
This command adds a new table for the current user.

    addList nameOfNewList
This command adds a new sub-list to the current list.

    addListToTable nameOfTable|path
This command adds a list to a table, where the user provides the path from the current or root list to the desired list, and the name of an existing table.

    moveList pathToMove|PathNewLoc
This command moves a list given a path from the current list
    
    moveTask index|pathToNewList
This command moves a task in the current list given by its index (0 is the topmost element), to a path from the current or root list.

    deleteTable tableName
This command deletes a table.

    deleteTask index
This command deletes a task given by its index, where 0 is the topmost element in the list.

    deleteList listName
This command deletes a list from the current list.
    
    changeStDateTask index|year|month|dayOfMonth
    changeStDateTask index|hour|min
    changeStDateTask index|year|month|dayOfMonth|hour|min
This command changes the start date of task in the current list given the tasks index and the new date.

    changeTask index|task
This command changes the task portion of a task in the current list given the tasks index and new task.

    changeEndDateTask index|year|month|dayOfMonth
    changeEndDateTask index|hour|min
    changeEndDateTask index|year|month|dayOfMonth|hour|min
This command changes the end date of task in the current list given the tasks index and the new date.

    renameList oldName|newName
This command renames a sublist in the current list.

    renameTable oldName|newName
This command renames a table.

    listNames
This lists the names of all the sub-lists in the current list.

    printList
This prints both the sub-lists and tasks in the current list.

    printTable tableName
This prints the desired table.

    printSubLists
This prints the path to all the lists within the current list.
    
    printSubListsFromRoot
This prints the path to all the lists from the root list.

    printCurDir
This prints the path to the current list.

    removeListFromTable tableName|listName
This removes a list from a table.
    
    cl path
This command takes in a path which can be from the current or root list, and switches the current list to the desired list.

    save
This saves your lists.

    load
This loads your lists, this is also done automatically when you launch the program.
  
    exit
This exists the program.
  
    help
This gives a brief description of all the commands, and provides what their inputs are.
    
    about command
This gives a brief description of a desired command, and provides its inputs.



## Installation

You can download the .jar file from GitHub: <https://github.com/AbdelhalemKassara/To_Do_List_Application/releases/>
  
