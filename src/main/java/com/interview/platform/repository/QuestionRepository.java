package com.interview.platform.repository;

import com.interview.platform.model.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * In-memory question database stored in an ArrayList.
 * Academic equivalent of a question table, loaded once at startup.
 */
@Repository
public class QuestionRepository {

    private final ArrayList<Question> questions = new ArrayList<>();

    @PostConstruct
    public void loadQuestions() {
        questions.clear();
        int id = 1;

        // Java (20)
        add(id++, "What is Java?", "Java", "Easy",
                "java", "programming language", "object-oriented", "platform independent", "jvm");
        add(id++, "What are the main features of Java?", "Java", "Easy",
                "object-oriented", "platform independent", "robust", "secure", "multithreaded");
        add(id++, "What is JVM?", "Java", "Easy",
                "java virtual machine", "bytecode", "runtime", "platform independent", "execution");
        add(id++, "What is JDK?", "Java", "Easy",
                "java development kit", "compiler", "tools", "jre", "development");
        add(id++, "What is JRE?", "Java", "Easy",
                "java runtime environment", "libraries", "jvm", "execute", "runtime");
        add(id++, "What is a class in Java?", "Java", "Easy",
                "blueprint", "class", "object", "fields", "methods");
        add(id++, "What is an object in Java?", "Java", "Easy",
                "instance", "object", "class", "state", "behavior");
        add(id++, "What is a constructor in Java?", "Java", "Easy",
                "constructor", "initialize", "object", "same name", "no return type");
        add(id++, "What is method overloading in Java?", "Java", "Medium",
                "overloading", "same name", "different parameters", "compile time", "polymorphism");
        add(id++, "What is method overriding in Java?", "Java", "Medium",
                "overriding", "subclass", "superclass", "runtime", "polymorphism");
        add(id++, "What is an interface in Java?", "Java", "Medium",
                "interface", "abstract methods", "implements", "multiple inheritance", "contract");
        add(id++, "What is exception handling in Java?", "Java", "Medium",
                "exception", "try", "catch", "finally", "throw");
        add(id++, "What is an ArrayList in Java?", "Java", "Easy",
                "arraylist", "dynamic", "collection", "resizable", "list");
        add(id++, "What is the difference between ArrayList and Array in Java?", "Java", "Medium",
                "array", "arraylist", "fixed size", "dynamic", "generics");
        add(id++, "What is String in Java?", "Java", "Easy",
                "string", "immutable", "character", "object", "heap");
        add(id++, "What is the difference between String, StringBuilder and StringBuffer?", "Java", "Medium",
                "string", "immutable", "stringbuilder", "stringbuffer", "thread-safe");
        add(id++, "What is the difference between JDK, JRE and JVM?", "Java", "Medium",
                "jdk", "jre", "jvm", "development", "runtime");
        add(id++, "What are access modifiers in Java?", "Java", "Medium",
                "public", "private", "protected", "default", "access");
        add(id++, "What is a package in Java?", "Java", "Easy",
                "package", "namespace", "organize", "import", "classes");
        add(id++, "What is garbage collection in Java?", "Java", "Medium",
                "garbage collection", "memory", "unused objects", "automatic", "heap");

        // OOP (14)
        add(id++, "What is Object-Oriented Programming?", "OOP", "Easy",
                "class", "object", "inheritance", "polymorphism", "encapsulation", "abstraction");
        add(id++, "What is encapsulation?", "OOP", "Easy",
                "encapsulation", "data hiding", "private", "getter", "setter");
        add(id++, "What is inheritance?", "OOP", "Easy",
                "inheritance", "extends", "reuse", "parent", "child");
        add(id++, "What is polymorphism?", "OOP", "Medium",
                "polymorphism", "many forms", "overloading", "overriding", "runtime");
        add(id++, "What is abstraction?", "OOP", "Easy",
                "abstraction", "hide details", "abstract class", "interface", "essential");
        add(id++, "What is the difference between a class and an object?", "OOP", "Easy",
                "class", "blueprint", "object", "instance", "memory");
        add(id++, "What is the difference between overloading and overriding?", "OOP", "Medium",
                "overloading", "compile time", "overriding", "runtime", "inheritance");
        add(id++, "Why do we use constructors in OOP?", "OOP", "Easy",
                "constructor", "initialize", "object", "default", "parameterized");
        add(id++, "What is an abstract class?", "OOP", "Medium",
                "abstract class", "abstract method", "cannot instantiate", "extends", "inheritance");
        add(id++, "Can a class implement multiple interfaces?", "OOP", "Medium",
                "interface", "implements", "multiple", "inheritance", "contract");
        add(id++, "What is data hiding?", "OOP", "Easy",
                "data hiding", "private", "encapsulation", "access", "security");
        add(id++, "What is a super keyword used for?", "OOP", "Medium",
                "super", "parent", "constructor", "method", "inheritance");
        add(id++, "What is this keyword in OOP/Java?", "OOP", "Easy",
                "this", "current object", "constructor", "reference", "instance");
        add(id++, "What is the difference between abstraction and encapsulation?", "OOP", "Hard",
                "abstraction", "hide complexity", "encapsulation", "data hiding", "implementation");

        // Data Structures (16)
        add(id++, "What is an array?", "Data Structures", "Easy",
                "array", "contiguous", "index", "fixed size", "elements");
        add(id++, "What is a linked list?", "Data Structures", "Easy",
                "linked list", "node", "pointer", "dynamic", "next");
        add(id++, "What is the difference between array and linked list?", "Data Structures", "Medium",
                "array", "linked list", "contiguous", "dynamic", "random access");
        add(id++, "What is a stack?", "Data Structures", "Easy",
                "stack", "lifo", "push", "pop", "top");
        add(id++, "What is a queue?", "Data Structures", "Easy",
                "queue", "fifo", "enqueue", "dequeue", "front");
        add(id++, "What is a tree data structure?", "Data Structures", "Medium",
                "tree", "hierarchical", "root", "child", "node");
        add(id++, "What is a binary tree?", "Data Structures", "Medium",
                "binary tree", "two children", "left", "right", "node");
        add(id++, "What is a binary search tree?", "Data Structures", "Medium",
                "bst", "left smaller", "right greater", "search", "ordered");
        add(id++, "What is a graph?", "Data Structures", "Medium",
                "graph", "vertices", "edges", "directed", "undirected");
        add(id++, "What is a hash table?", "Data Structures", "Medium",
                "hash table", "key", "value", "hash function", "collision");
        add(id++, "What is a heap?", "Data Structures", "Medium",
                "heap", "priority", "complete tree", "min heap", "max heap");
        add(id++, "What is a deque?", "Data Structures", "Medium",
                "deque", "double ended", "insert", "delete", "both ends");
        add(id++, "What is a circular queue?", "Data Structures", "Medium",
                "circular queue", "front", "rear", "modulo", "fifo");
        add(id++, "What is a doubly linked list?", "Data Structures", "Medium",
                "doubly linked list", "previous", "next", "node", "bidirectional");
        add(id++, "What is a trie?", "Data Structures", "Hard",
                "trie", "prefix", "string", "children", "search");
        add(id++, "When should you use a stack in a program?", "Data Structures", "Medium",
                "stack", "recursion", "undo", "parentheses", "lifo");

        // Algorithms (12)
        add(id++, "What is an algorithm?", "Algorithms", "Easy",
                "algorithm", "step by step", "problem", "finite", "solution");
        add(id++, "What is linear search?", "Algorithms", "Easy",
                "linear search", "sequential", "one by one", "o(n)", "array");
        add(id++, "What is binary search?", "Algorithms", "Medium",
                "binary search", "sorted", "middle", "divide", "o(log n)");
        add(id++, "What is time complexity?", "Algorithms", "Medium",
                "time complexity", "operations", "input size", "big o", "efficiency");
        add(id++, "What is space complexity?", "Algorithms", "Medium",
                "space complexity", "memory", "input size", "auxiliary", "big o");
        add(id++, "What is recursion?", "Algorithms", "Easy",
                "recursion", "function", "itself", "base case", "stack");
        add(id++, "What is bubble sort?", "Algorithms", "Easy",
                "bubble sort", "adjacent", "swap", "o(n^2)", "sorted");
        add(id++, "What is merge sort?", "Algorithms", "Medium",
                "merge sort", "divide", "conquer", "merge", "o(n log n)");
        add(id++, "What is quick sort?", "Algorithms", "Medium",
                "quick sort", "pivot", "partition", "divide", "average o(n log n)");
        add(id++, "What is insertion sort?", "Algorithms", "Easy",
                "insertion sort", "sorted part", "insert", "o(n^2)", "array");
        add(id++, "What is Dijkstra's algorithm used for?", "Algorithms", "Hard",
                "dijkstra", "shortest path", "graph", "weighted", "greedy");
        add(id++, "What is the difference between BFS and DFS?", "Algorithms", "Medium",
                "bfs", "queue", "dfs", "stack", "graph");

        // DBMS & SQL (16)
        add(id++, "What is DBMS?", "DBMS & SQL", "Easy",
                "dbms", "database", "manage", "data", "software");
        add(id++, "What is RDBMS?", "DBMS & SQL", "Easy",
                "rdbms", "relational", "tables", "rows", "columns");
        add(id++, "What is a primary key?", "DBMS & SQL", "Easy",
                "primary key", "unique", "not null", "identify", "table");
        add(id++, "What is a foreign key?", "DBMS & SQL", "Easy",
                "foreign key", "reference", "primary key", "relationship", "table");
        add(id++, "What is normalization?", "DBMS & SQL", "Medium",
                "normalization", "redundancy", "normal forms", "1nf", "2nf");
        add(id++, "What is SQL?", "DBMS & SQL", "Easy",
                "sql", "structured query language", "query", "database", "statements");
        add(id++, "What does the SELECT statement do?", "DBMS & SQL", "Easy",
                "select", "retrieve", "columns", "from", "table");
        add(id++, "What is a JOIN in SQL?", "DBMS & SQL", "Medium",
                "join", "combine", "tables", "common column", "inner");
        add(id++, "What is the difference between INNER JOIN and LEFT JOIN?", "DBMS & SQL", "Medium",
                "inner join", "matching", "left join", "all left", "null");
        add(id++, "What is GROUP BY used for?", "DBMS & SQL", "Medium",
                "group by", "aggregate", "count", "sum", "groups");
        add(id++, "What is the difference between WHERE and HAVING?", "DBMS & SQL", "Medium",
                "where", "rows", "having", "groups", "aggregate");
        add(id++, "What is the difference between DELETE and DROP?", "DBMS & SQL", "Medium",
                "delete", "rows", "drop", "table", "structure");
        add(id++, "What are SQL constraints?", "DBMS & SQL", "Medium",
                "constraints", "primary key", "foreign key", "unique", "not null");
        add(id++, "What is ACID in databases?", "DBMS & SQL", "Hard",
                "acid", "atomicity", "consistency", "isolation", "durability");
        add(id++, "What is an index in a database?", "DBMS & SQL", "Medium",
                "index", "search", "faster", "column", "performance");
        add(id++, "What is the difference between TRUNCATE and DELETE?", "DBMS & SQL", "Medium",
                "truncate", "delete", "rows", "where", "rollback");

        // Operating Systems (12)
        add(id++, "What is an operating system?", "Operating Systems", "Easy",
                "operating system", "resource", "hardware", "software", "interface");
        add(id++, "What is a process?", "Operating Systems", "Easy",
                "process", "program", "execution", "pcb", "memory");
        add(id++, "What is a thread?", "Operating Systems", "Easy",
                "thread", "lightweight", "process", "shared memory", "execution");
        add(id++, "What is the difference between process and thread?", "Operating Systems", "Medium",
                "process", "thread", "memory", "overhead", "communication");
        add(id++, "What is deadlock?", "Operating Systems", "Medium",
                "deadlock", "wait", "resources", "circular", "hold");
        add(id++, "What are deadlock necessary conditions?", "Operating Systems", "Hard",
                "mutual exclusion", "hold and wait", "no preemption", "circular wait", "deadlock");
        add(id++, "What is CPU scheduling?", "Operating Systems", "Medium",
                "scheduling", "cpu", "ready queue", "fcfs", "round robin");
        add(id++, "What is paging?", "Operating Systems", "Medium",
                "paging", "pages", "frames", "virtual memory", "non-contiguous");
        add(id++, "What is virtual memory?", "Operating Systems", "Medium",
                "virtual memory", "disk", "ram", "paging", "address space");
        add(id++, "What is memory management?", "Operating Systems", "Easy",
                "memory management", "allocation", "process", "protection", "ram");
        add(id++, "What is context switching?", "Operating Systems", "Medium",
                "context switch", "pcb", "process", "cpu", "state");
        add(id++, "What is a semaphore?", "Operating Systems", "Hard",
                "semaphore", "synchronization", "wait", "signal", "critical section");

        // Computer Networks (12)
        add(id++, "What is a computer network?", "Computer Networks", "Easy",
                "network", "devices", "communication", "share", "protocol");
        add(id++, "What is an IP address?", "Computer Networks", "Easy",
                "ip address", "unique", "host", "ipv4", "ipv6");
        add(id++, "What is TCP?", "Computer Networks", "Medium",
                "tcp", "connection-oriented", "reliable", "acknowledgement", "transport");
        add(id++, "What is UDP?", "Computer Networks", "Medium",
                "udp", "connectionless", "unreliable", "fast", "datagram");
        add(id++, "What is the difference between TCP and UDP?", "Computer Networks", "Medium",
                "tcp", "reliable", "udp", "faster", "connection");
        add(id++, "What is HTTP?", "Computer Networks", "Easy",
                "http", "hypertext", "web", "request", "response");
        add(id++, "What is HTTPS?", "Computer Networks", "Easy",
                "https", "ssl", "tls", "encryption", "secure");
        add(id++, "What is DNS?", "Computer Networks", "Easy",
                "dns", "domain", "ip address", "name resolution", "server");
        add(id++, "What is the OSI model?", "Computer Networks", "Medium",
                "osi", "seven layers", "application", "transport", "network");
        add(id++, "What is the TCP/IP model?", "Computer Networks", "Medium",
                "tcp/ip", "layers", "internet", "transport", "application");
        add(id++, "What is a MAC address?", "Computer Networks", "Easy",
                "mac address", "physical", "nic", "unique", "datalink");
        add(id++, "What is a router?", "Computer Networks", "Easy",
                "router", "packets", "network", "ip", "forward");

        // General Technical Interview (10) — total 112? Let me count...

        // Java 20 + OOP 14 = 34
        // DS 16 = 50
        // Algo 12 = 62
        // DBMS 16 = 78
        // OS 12 = 90
        // Networks 12 = 102
        // That's already 102. I'll add 8 general to make 110 or stop at 100.

        // I added 102 so far if my count is right. Let me recount after writing general as remaining to 100.

        add(id++, "What is problem solving in programming interviews?", "General Technical", "Easy",
                "problem solving", "understand", "algorithm", "example", "complexity");
        add(id++, "What are programming fundamentals every beginner should know?", "General Technical", "Easy",
                "variables", "loops", "conditions", "functions", "data types");
        add(id++, "What is Git?", "General Technical", "Easy",
                "git", "version control", "commit", "branch", "repository");
        add(id++, "What is GitHub used for?", "General Technical", "Easy",
                "github", "remote", "repository", "pull request", "collaboration");
        add(id++, "What is the software development life cycle?", "General Technical", "Medium",
                "sdlc", "requirement", "design", "implementation", "testing");
        add(id++, "What is debugging?", "General Technical", "Easy",
                "debugging", "bug", "error", "trace", "fix");
        add(id++, "What is software testing?", "General Technical", "Easy",
                "testing", "bugs", "unit test", "quality", "expected");
        add(id++, "How do you explain a college project in an interview?", "General Technical", "Medium",
                "problem", "technology", "features", "role", "challenges");
        add(id++, "What is the difference between frontend and backend?", "General Technical", "Easy",
                "frontend", "ui", "backend", "server", "database");
        add(id++, "What is an API?", "General Technical", "Easy",
                "api", "interface", "request", "response", "service");
    }

    private void add(int id, String text, String category, String difficulty, String... keywords) {
        questions.add(Question.of(id, text, category, difficulty, 2, keywords));
    }

    public ArrayList<Question> findAll() {
        return new ArrayList<>(questions);
    }

    public int size() {
        return questions.size();
    }

    public ArrayList<Question> findByCategory(String category) {
        if (category == null || category.isBlank() || isAll(category)) {
            return findAll();
        }
        String wanted = category.trim().toLowerCase(Locale.ROOT);
        return questions.stream()
                .filter(q -> q.getCategory().toLowerCase(Locale.ROOT).equals(wanted)
                        || ("mixed technical interview".equals(wanted))
                        || ("all categories".equals(wanted)))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> distinctCategories() {
        ArrayList<String> cats = new ArrayList<>();
        for (Question question : questions) {
            if (!cats.contains(question.getCategory())) {
                cats.add(question.getCategory());
            }
        }
        return cats;
    }

    private boolean isAll(String category) {
        String value = category.trim().toLowerCase(Locale.ROOT);
        return value.equals("all") || value.equals("all categories") || value.equals("mixed technical interview");
    }

    public List<Question> viewAll() {
        return Collections.unmodifiableList(questions);
    }
}
