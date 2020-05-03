/*  175. Combine Two Tables
    SQL Schema:
    Create table Person (PersonId int, FirstName varchar(255), LastName varchar(255))
    Create table Address (AddressId int, PersonId int, City varchar(255), State varchar(255))
    Truncate table Person
    insert into Person (PersonId, LastName, FirstName) values ('1', 'Wang', 'Allen')
    Truncate table Address
    insert into Address (AddressId, PersonId, City, State) values ('1', '2', 'New York City', 'New York')

    Table: Person
    +-------------+---------+
    | Column Name | Type    |
    +-------------+---------+
    | PersonId    | int     |
    | FirstName   | varchar |
    | LastName    | varchar |
    +-------------+---------+
    PersonId is the primary key column for this table.

    Table: Address
    +-------------+---------+
    | Column Name | Type    |
    +-------------+---------+
    | AddressId   | int     |
    | PersonId    | int     |
    | City        | varchar |
    | State       | varchar |
    +-------------+---------+
    AddressId is the primary key column for this table.

    Write a SQL query for a report that provides the following information for each person in Person table,
    regardless if there is an address for each of those people:
    FirstName, LastName, City, State
 */

/*  Using outer join
    Considering there might not be an address information for every person, we should use outer join
    instead of the default inner join.
    Note: Using where clause to filter the records will fail if there is no address information for a person
    because it will not display the name information.
 */
    select FirstName, LastName, City, State
    from Person left join Address
    on Person.PersonId = Address.PersonId;