##Task 1

###e)
Print screen of the report on Euromillions-play (Lab2)

The project passed the defined quality gate, although there was one bug, one security hotspots and 35 code smells.

###f)

| **Issue**          | **Problem description**                        | **How to solve**                               |
|:------------------:|:----------------------------------------------:|:----------------------------------------------:|
| Bug                | "Random" objects should be reused              | Save and re-use the "Random"                   |
| Vulnerability      | -	                                      | -                                              |
| Code smell (major) | "for" loop stop conditions should be invariant | Assign loop counter inside the "for" statement |
| Code smell (major) | Unused method parameters should be removed     | Remove this unused method parameter            |
| Code smell (major) | Standard outputs should not be used directly to log anything | Replace this use of System.out by a logger |

##Task 2

###a)
The project technical debt is the sum of the technical debt of every code smell in the project.
In my case it was 2h 32min, meaning that this is the time it would take someone to refactor the code.


