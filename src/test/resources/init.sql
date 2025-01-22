CREATE table student (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            first_name VARCHAR(100),
                            last_name VARCHAR(100),
                            email VARCHAR(256)
);

CREATE table homework (
                             id INT PRIMARY KEY AUTO_INCREMENT,
                             description VARCHAR(300),
                             deadline DATE,
                             mark INT,
                             student_id INT,
                             FOREIGN KEY (student_id) REFERENCES student(id)
);

INSERT INTO student (first_name, last_name, email)
VALUES
    ('John', 'Doe', 'john.doe@example.com'),
    ('Jane', 'Smith', 'jane.smith@example.com'),
    ('Alice', 'Johnson', 'alice.johnson@example.com'),
    ('Bob', 'Brown', 'bob.brown@example.com'),
    ('Charlie', 'Davis', 'charlie.davis@example.com');

INSERT INTO homework (description, deadline, mark, student_id)
VALUES
    ('Art project on Impressionism', '2025-07-15', 60, 1),
    ('Research on Viking mythology', '2025-10-12', 60, 1),
    ('Biology presentation on ecosystems', '2025-03-22', 77, 1),
    ('Essay on modern poetry', '2025-11-05', 92, 2),
    ('Lab report on thermodynamics', '2025-06-19', 86, 2),
    ('Web development project in HTML/CSS', '2025-09-07', 99, 3),
    ('Essay on quantum computing', '2025-12-03', 56, 3),
    ('Creative writing assignment', '2025-08-28', 99, 4),
    ('History quiz on World War II', '2025-05-30', 89, 4),
    ('Research paper on renewable energy', '2025-04-15', 68, 5),
    ('Chemistry experiment on solutions', '2025-12-31', 71, 5);