# To_Do_List_Application
This is to-do list applciation where you can add a to-do list within a to-do list and display any combination of to-do lists created with java 17.

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
  
