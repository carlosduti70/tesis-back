CREATE VIEW user_patient_info AS
SELECT
    u.id AS user_id,
    u.name AS user_name,
    u.last_name AS user_last_name,
    u.username,
    u.locked,
    u.disabled,
    p.id AS patient_id,
    p.name AS patient_name,
    p.last_name AS patient_last_name,
    p.age,
    p.date_diagnosis,
    p.address,
    p.stage
FROM
    users u
        JOIN
    patient p
    ON
        u.patient_id = p.id;

-- CREATE VIEW card_reminders_view AS
-- SELECT
--     c.id AS card_id,
--     c.date_time,
--     c.hour,
--     r.title
-- FROM
--     card c
--         JOIN
--     reminders r
--     ON
--             c.id = r.card_id;
