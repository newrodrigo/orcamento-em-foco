## Diagrama de Classes

```mermaid
classDiagram
  class Expense {
    - id: Long
    - description: String
    - situation: Situation
    - amount: Double
    - paymentDate: LocalDate
    - category: Category
    - provider: Provider
    - observation: String
  }
  class Provider {
    - id: Long
    - name: String
    - email: String
    - phone: String
  }
  class Category {
    - id: Long
    - description: String
  }
  class Situation {
    - OVERDUE
    - PAID
    - COMPLETED
  }

  Expense "1" -- "1" Category
  Expense "1" -- "1" Provider
```
