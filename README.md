# Simple Turn-Based Battle (Beginner OOP Task)

Goal: Practice core OOP (abstraction, inheritance, encapsulation, polymorphism), enums, interfaces, basic collections (List, Map, Set, Queue), generics (optional), simple exception handling, and basic control flow. NO file/network/DB IO. Console `System.out.println` only if you wish when running.

Implement all TODOs across source files.

Recommended Order:
1. Enums (ElementType, StatusCondition, AbilityCategory)
2. Core domain classes (Character hierarchy, Ability, Effect types)
3. BattleContext + BattleEngine
4. Selection strategies (player placeholder, simple enemy AI)
5. Damage calculation strategy
6. Status + cooldown + energy handling
7. Main demo wiring

You may add helper methods if needed, but avoid premature complexity.

Key Java util types to use somewhere appropriately:
- List (ArrayList / List.of defensive copies)
- Map (HashMap / EnumMap)
- Set (HashSet / EnumSet)
- Queue (ArrayDeque) for turn order
- Optional (return Optional<Ability> or Optional<Character> where sensible)
- Random (for damage variance)
- Collections utility methods (e.g., unmodifiableList, sort)
- Comparator (for speed ordering)

Stretch (optional):
- Buffs that temporarily adjust stats
- Composite effects
- Simple repository (generic) for Characters
- Turn summary object
- Battle result object
- Simple AI improvement (e.g. pick weakest target)

Run (after you implement):
javac -d out $(find src -name "*.java")
java -cp out battle.Main

(No need for Maven or external libs.)

Happy coding!