/*  177. Nth Highest Salary
    Write a SQL query to get the nth highest salary from the Employee table.
    +----+--------+
    | Id | Salary |
    +----+--------+
    | 1  | 100    |
    | 2  | 200    |
    | 3  | 300    |
    +----+--------+
    For example, given the above Employee table, the nth highest salary where n = 2 is 200.
    If there is no nth highest salary, then the query should return null.
    +---------------------+
    | getNthHighestSalary |
    +---------------------+
    | 200                 |
    +---------------------+
 */
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
DECLARE m INT;
SET m = n - 1;
    RETURN (
        SELECT DISTINCT Salary
        FROM Employee
        ORDER BY Salary DESC
        LIMIT m, 1
    );
end;