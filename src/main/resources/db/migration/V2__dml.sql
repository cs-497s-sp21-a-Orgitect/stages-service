
INSERT INTO process (name, description) VALUES
('Cancel Meal Plan', 'Use this process to cancel a meal plan for a student'),
('Laptop does not boot', 'Use this process to diagnose & fix a laptop');


WITH ins (process_name, action, duration, ordering) AS
( VALUES
    ('Cancel Meal Plan', 'Login to system and cancel student plan', 20, 0),
    ('Cancel Meal Plan', 'Refund funds to student account', 30, 1),
    ('Cancel Meal Plan', 'Sign that the request has been completed', 20, 2),
    ('Laptop does not boot', 'Diagnose Laptop', 60, 0),
    ('Laptop does not boot', 'Fix Laptop', 120, 1)
)

INSERT INTO stage (action, duration, ordering, process_id)
SELECT
    ins.action, ins.duration, ins.ordering, process.id
FROM
    process JOIN ins
        ON ins.process_name = process.name;