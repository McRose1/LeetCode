/*  178. Rank Scores
    SQL Schema
    Create table If Not Exists Scores (Id int, Score DECIMAL(3,2))
    Truncate table Scores
    insert into Scores (Id, Score) values ('1', '3.5')
    insert into Scores (Id, Score) values ('2', '3.65')
    insert into Scores (Id, Score) values ('3', '4.0')
    insert into Scores (Id, Score) values ('4', '3.85')
    insert into Scores (Id, Score) values ('5', '4.0')
    insert into Scores (Id, Score) values ('6', '3.65')

    Write a SQL query to rank scores.
    If there is a tie between two scores, both should have the same ranking.
    Note that after a tie, the next ranking number should be the next consecutive integer value.
    In other words, there should be no "holes" between ranks.

    +----+-------+
    | Id | Score |
    +----+-------+
    | 1  | 3.50  |
    | 2  | 3.65  |
    | 3  | 4.00  |
    | 4  | 3.85  |
    | 5  | 4.00  |
    | 6  | 3.65  |
    +----+-------+
    For example, given the above Scores table,
    your query should generate the following report (order by highest score):

    +-------+---------+
    | score | Rank    |
    +-------+---------+
    | 4.00  | 1       |
    | 4.00  | 1       |
    | 3.85  | 2       |
    | 3.65  | 3       |
    | 3.65  | 3       |
    | 3.50  | 4       |
    +-------+---------+
    Important Note: For MySQL solutions, to escape reserved words used as column names,
    you can use an apostrophe before and after the keyword. For example `Rank`.
 */

SELECT s1.Score, (SELECT COUNT(DISTINCT s2.Score) + 1
                        FROM Scores s2
                        WHERE s1.Score < s2.Score
    ) 'Rank'
FROM Scores s1
ORDER BY 'RANK' ASC

/* Window Function
   FUNCTION() OVER (PARTITION BY x ORDER BY y ASC/DESC)

   Frequently used functions:
   RANK(), ROW_NUMBER() and DENSE_RANK()
   *Window Function cannot be used within MySQL!!!
 */