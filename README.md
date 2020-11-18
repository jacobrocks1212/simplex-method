# simplex-method
Implementation of the Simplex Method to solve standard form linear programs with full-rank assumption:

max **c**<sup>T</sup>**x**
  
s.t. A**x** = **b**
        
   **x** ≥ 0,


where **x**, **c** ∈ R<sup>n</sup>, **b** ∈ R<sup>m</sup>, A is an m × n matrix and rank(A) = m < n.


Linear programs may be solved with or without enforcing Bland's rule to prevent cycling.

Includes a command-line interface allowing users to 
1. Create your own LP     
2. Run example LP (successful)      
3. Run example LP (unbounded)    
4. Run example LP (cycling no Bland's rule)     
5. Run example LP (4 but using Bland's rule)
